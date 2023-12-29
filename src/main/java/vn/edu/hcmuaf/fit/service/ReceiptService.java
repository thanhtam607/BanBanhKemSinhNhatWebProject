package vn.edu.hcmuaf.fit.service;

import vn.edu.hcmuaf.fit.db.DBConnect;
import vn.edu.hcmuaf.fit.model.*;
import vn.edu.hcmuaf.fit.security.RSA;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class ReceiptService {
    public static ResultSet getResultSetListReceipt() {
        ResultSet resultSet = null;
        Statement statement = DBConnect.getInstall().get();
        if (statement != null) {
            try {
                resultSet = statement.executeQuery("SELECT BILLS.ID, BILLS.CUSTOMER_ID, BILLS.EXPORT_DATE, BILLS.NOTES, BILLS.PRO_BILL, BILLS.FEE_BILL, BILLS.STATUS FROM BILLS ORDER BY BILLS.EXPORT_DATE DESC");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return resultSet;
    }

    public static List<Comment> getListComment(String id) {
        List<Comment> list = new ArrayList<Comment>();
        Statement statement = DBConnect.getInstall().get();
        if (statement != null)
            try {
                ResultSet rsCmt = statement.executeQuery("SELECT idProduct, ACCOUNTS.NAME,comment,date, IdCmt, Comments.STATUS from Comments, ACCOUNTS where ACCOUNTS.ID = Comments.ID and ACCOUNTS.ID = '" + id + "'");
                while (rsCmt.next()) {
                    list.add(new Comment(rsCmt.getString(1), rsCmt.getString(2), rsCmt.getString(3), rsCmt.getString(4), rsCmt.getInt(5), rsCmt.getInt(6)));
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        else {
            System.out.println("Không có");
        }
        return list;
    }

    public static List<Receipt> getAllReceipt() throws Exception {
        List<Receipt> list = new ArrayList<Receipt>();
        ResultSet rs = getResultSetListReceipt();
        while (rs.next()) {
//                   ================== tạo đối tượng receiptForHash để tến hành xacs thực đơn hang =====================
            Delivery delivery = getGiaoHang(rs.getString(1));
            List<Bill_Detail> billDetailList = getBill_DetailForHash(rs.getString(1));

            Receipt rc = new Receipt(rs.getString(1), rs.getString(2),
                    rs.getString(3), delivery.getNgayGiao(), rs.getString(4), rs.getInt(5),
                    rs.getInt(6), rs.getInt(7), delivery.getDiachigiao());


            Receipt receiptForHash = new Receipt(rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getDouble(5),
                    rs.getDouble(6),
                    billDetailList,
                    delivery);
//                    get order from mhd = HD16 =>
            if (compareDates(receiptForHash.getExport_date(), "2023-01-09 00:00:00") > 0) {
                if (rs.getInt(7) != 4) {
                    if (!verifyOrderWhenLoad(receiptForHash, getCypherTextOfOrder(rs.getString(1)), UserService.getListKey(rs.getString(2)))) {
                        updateState(receiptForHash.getId(), 5);
                        rc.setStatus(5);
                        // Gửi mail báo lỗi
                        sendMailWhenErr(receiptForHash);
                        // Gửi mail báo lỗi
                    }
// tạo đối tượng receiptForHash để tến hành xacs thực đơn hang
                }
            }

            list.add(rc);
        }

        return list;
    }


    public static void sendMailWhenErr(Receipt receiptForHash) throws SQLException, MessagingException, UnsupportedEncodingException {
        String adminEmail = UserService.getEmail("AD01");
        String customerEmail = UserService.getEmail(receiptForHash.getMakh());
        String errorCusSubject = "Lỗi Trong Quá Trình Xử Lý Đơn Hàng";
        String errorAdminSubject = "Có sự thay đổi trong CSDL!!!";

        String adminErrorMessage = "Chào " + UserService.getNameUser("AD01") + ",\n\n"
                + "Chúng tôi muốn thông báo rằng có sự thay đổi đáng kể trong đơn hàng có mã " + receiptForHash.getId() + ". "
                + "Vui lòng kiểm tra và xử lý vấn đề ngay.\n\n"
                + "Trân trọng,\n" + "Tiệm bánh Hạnh Phúc";

        String customerErrorMessage = "Chào " + UserService.getNameUser(receiptForHash.getMakh()) + ",\n\n"
                + "Chúng tôi xin thông báo rằng đơn hàng của bạn có mã " + receiptForHash.getId() + " đã có một số thay đổi. "
                + "Để đảm bảo đơn hàng của bạn được xử lý đúng cách, vui lòng kiểm tra và xác nhận lại đơn hàng hoặc liên hệ với chúng tôi nếu cần hỗ trợ.\n\n"
                + "Trân trọng,\n" + "Tiệm bánh Hạnh Phúc";

        UserService.sendMail(adminEmail, errorAdminSubject, adminErrorMessage);
        UserService.sendMail(customerEmail, errorCusSubject, customerErrorMessage);
//                        gửi mail báo lỗi
    }


    //    tạo cypherText cho từng đơn hàng
    public static String createCypherText(Receipt receipt, String privateKeyString) throws Exception {
        String hashOrder = RSA.hashObject(receipt);
        System.out.println("đoa hash ban dâu : " + hashOrder);
        return RSA.encrypt(hashOrder, RSA.getPrivateKeyFromString(privateKeyString));
    }

    //    so sánh Hash o1 và o2
    public static boolean verifyOrderWhenLoad(Receipt receipt, String cypherText, List<SignUser> signUserList) throws Exception {
        String fstHash = "";
        String hashOrder = "";
        String publickeyString = getPbKeyString(receipt, signUserList);
        if (cypherText.isEmpty()) {
            return false;
        } else {
            fstHash = RSA.decrypt(cypherText, RSA.getPublicKeyFromString(publickeyString));
            hashOrder = RSA.hashObject(receipt);
            System.out.println("đoa hash lu sau : " + hashOrder);
            return fstHash.equals(hashOrder);
        }

    }

    public static String getPbKeyString(Receipt receipt, List<SignUser> signUserList) {
        for (SignUser s : signUserList) {
            if (s.getExpireDate() != null) {
                if (compareDates(receipt.getExport_date(), s.getCreateDate()) > 0 && compareDates(receipt.getExport_date(), s.getExpireDate()) < 0) {
                    return s.getPbkey();
                }
            } else {
                return s.getPbkey();
            }
        }
        return null;
    }

    public static int compareDates(String day1, String day2) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            Date date1 = dateFormat.parse(day1);
            Date date2 = dateFormat.parse(day2);

            return date1.compareTo(date2);
        } catch (ParseException e) {
            return 0; // Trả về false nếu có lỗi
        }

    }

    public static List<Receipt> getAllReceiptToDay() {
        List<Receipt> list = new ArrayList<Receipt>();
        Statement statement = DBConnect.getInstall().get();
        if (statement != null)
            try {
                ResultSet rs = statement.executeQuery("SELECT ID, CUSTOMER_ID, EXPORT_DATE, NOTES, sum(BILLS.PRO_BILL+ BILLS.FEE_BILL) as total\n" +
                        "FROM BILLS\n" +
                        "WHERE date(EXPORT_DATE) = date(CURRENT_DATE)\n" +
                        "and MONTH(EXPORT_DATE) = MONTH(CURRENT_DATE)\n" +
                        "and YEAR(EXPORT_DATE) = YEAR(CURRENT_DATE)\n" +
                        "GROUP BY ID, CUSTOMER_ID, EXPORT_DATE, NOTES\n" +
                        "ORDER BY BILLS.ID DESC");
                while (rs.next()) {
                    Receipt rc = new Receipt(rs.getString(1), rs.getString(2),
                            rs.getString(3), rs.getString(4), rs.getInt(5));
                    list.add(rc);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        else {
            System.out.println("Không có  hóa đơn");
        }
        return list;
    }


    public static Map<String, Integer> getAllCakeThisMonth() {
        Map<String, Integer> map = new HashMap<>();
        Statement statement = DBConnect.getInstall().get();
        if (statement != null)
            try {
                ResultSet rs = statement.executeQuery("SELECT products.idProduct,products.productName, sum(BILL_DETAIL.AMOUNT) as slg\n" +

                        "from products, BILLS, BILL_DETAIL\n" +
                        "WHERE  BILLS.ID = BILL_DETAIL.ID and products.idProduct = BILL_DETAIL.idProduct \n" +
                        "and BILLS.STATUS != 4 and month(BILLS.EXPORT_DATE) = MONTH(CURRENT_DATE) " +
                        "and year(BILLS.EXPORT_DATE) =YEAR(CURRENT_DATE)\n" +
                        "GROUP BY  products.idProduct, products.productName HAVING slg > 0\n" +
                        "ORDER BY slg DESC LIMIT 10");
                while (rs.next()) {
                    map.put(rs.getString(2), rs.getInt(3));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        else {
            System.out.println("Không có  hóa đơn");
        }
        LinkedHashMap<String, Integer> sortedMap = map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
        return sortedMap;
    }

    public static int getNumberProToDay() {
        int result = 0;
        Statement statement = DBConnect.getInstall().get();
        if (statement != null)
            try {
                ResultSet rs = statement.executeQuery("SELECT sum(BILL_DETAIL.AMOUNT) FROM  BILL_DETAIL, BILLS\n" +
                        "WHERE date(EXPORT_DATE) = date(CURRENT_DATE) and MONTH(EXPORT_DATE) = month(CURRENT_DATE) and YEAR(EXPORT_DATE) = YEAR(CURRENT_DATE)\n" +
                        "and BILLS.ID =  BILL_DETAIL.ID and BILLS.STATUS != 4");
                while (rs.next()) {
                    result = rs.getInt(1);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        else {
            System.out.println("Không có  hóa đơn");
        }
        return result;
    }

    public static int getDoanhThuToDay() {
        int result = 0;
        Statement statement = DBConnect.getInstall().get();
        if (statement != null)
            try {
                ResultSet rs = statement.executeQuery("SELECT sum(BILLS.PRO_BILL+ BILLS.FEE_BILL) as total\n" +
                        "FROM BILLS\n" +
                        "WHERE date(EXPORT_DATE) = date(CURRENT_DATE) \n" +
                        "and MONTH(EXPORT_DATE) = MONTH(CURRENT_DATE) \n" +
                        "and YEAR(EXPORT_DATE) = YEAR(CURRENT_DATE) \n" +
                        "and  STATUS != 4\n" +
                        "ORDER BY BILLS.ID DESC");
                while (rs.next()) {
                    result = rs.getInt(1);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        else {
            System.out.println("Không có ");
        }
        return result;
    }

    public static int getDoanhThuThisMonth() {
        int result = 0;
        Statement statement = DBConnect.getInstall().get();
        if (statement != null)
            try {
                ResultSet rs = statement.executeQuery("SELECT sum(BILLS.PRO_BILL+ BILLS.FEE_BILL) as total FROM BILLS\n" +
                        "  WHERE MONTH(EXPORT_DATE) = month(CURRENT_DATE) and YEAR(EXPORT_DATE) = YEAR(CURRENT_DATE) and BILLS.STATUS != 4\n" +
                        "ORDER BY BILLS.ID DESC");
                while (rs.next()) {
                    result = rs.getInt(1);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        else {
            System.out.println("Không có  hóa đơn");
        }
        return result;
    }


    public static List<String> getListMaSpCTHD(String id) {
        List<String> list = new ArrayList<>();
        Statement statement = DBConnect.getInstall().get();
        if (statement != null)
            try {
                ResultSet rs = statement.executeQuery("SELECT BILL_DETAIL.idProduct \n" +
                        "from BILL_DETAIL WHERE BILL_DETAIL.ID = '" + id + "' ORDER BY BILL_DETAIL.ID DESC");
                while (rs.next()) {
                    list.add(rs.getString(1));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        else {
            System.out.println("Không có msp");
        }
        return list;
    }


    public static List<Bill_Detail> getBill_DetailUser(String mahd) {
        List<Bill_Detail> list = new ArrayList<>();
        Statement statement = DBConnect.getInstall().get();
        if (statement != null)
            try {
                ResultSet rs = statement.executeQuery("SELECT BILL_DETAIL.ID, BILL_DETAIL.idProduct, products.productName,BILL_DETAIL.PRICE , BILL_DETAIL.AMOUNT, BILL_DETAIL.NOTES from BILLS, BILL_DETAIL, products\n" +
                        "                        WHERE BILL_DETAIL.ID = BILLS.ID and BILL_DETAIL.idProduct = products.idProduct \n" +
                        "and BILLS.ID = '" + mahd + "'");
                while (rs.next()) {

                    Bill_Detail billDetail = new Bill_Detail(rs.getString(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getInt(4),
                            rs.getInt(5),
                            rs.getString(6));
                    list.add(billDetail);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        else {
            System.out.println("Không có cthd");
        }
        return list;
    }

    public static List<Bill_Detail> getBill_DetailForHash(String mahd) {
        List<Bill_Detail> list = new ArrayList<>();
        Statement statement = DBConnect.getInstall().get();
        if (statement != null)
            try {
                ResultSet rs = statement.executeQuery("SELECT BILL_DETAIL.ID, BILL_DETAIL.idProduct,BILL_DETAIL.PRICE , BILL_DETAIL.AMOUNT, BILL_DETAIL.NOTES from BILLS, BILL_DETAIL, products\n" +
                        "                        WHERE BILL_DETAIL.ID = BILLS.ID and BILL_DETAIL.idProduct = products.idProduct \n" +
                        "and BILLS.ID = '" + mahd + "'");
                while (rs.next()) {

                    Bill_Detail billDetail = new Bill_Detail(rs.getString(1),
                            rs.getString(2),
                            rs.getInt(4),
                            rs.getString(5),
                            rs.getInt(3));
                    list.add(billDetail);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        else {
            System.out.println("Không có cthd");
        }
        return list;
    }

    public static Receipt getReceiptByMahd(String mhd) {
        Receipt result = new Receipt();
        Statement statement = DBConnect.getInstall().get();
        if (statement != null)
            try {
                ResultSet rs = statement.executeQuery("SELECT  ID, CUSTOMER_ID, EXPORT_DATE, NOTES, PRO_BILL, " +
                        "FEE_BILL, STATUS FROM BILLS WHERE BILLS.ID ='" + mhd + "'");
                while (rs.next()) {
                    Delivery delivery = getGiaoHang(rs.getString(1));
                    result = new Receipt(rs.getString(1), rs.getString(2),
                            rs.getString(3), delivery.getNgayGiao(),
                            rs.getString(4), rs.getInt(5),
                            rs.getInt(6), rs.getInt(7), delivery.getDiachigiao());
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        else {
            System.out.println("Không có  hóa đơn");
        }
        return result;
    }

    public static List<Receipt> getReceiptByMakh(String makh) throws Exception {
        List<Receipt> result = new ArrayList<>();
        ResultSet rs = getResultSetListReceipt();
        while (rs.next()) {
            if (rs.getString(2).equals(makh)) {
//                   ================== tạo đối tượng receiptForHash để tến hành xacs thực đơn hang =====================
                Delivery delivery = getGiaoHang(rs.getString(1));
                List<Bill_Detail> billDetailList = getBill_DetailForHash(rs.getString(1));

                Receipt re = new Receipt(rs.getString(1), rs.getString(2),
                        rs.getString(3), delivery.getNgayGiao(), rs.getString(4), rs.getInt(5),
                        rs.getInt(6), rs.getInt(7), delivery.getDiachigiao());

                Receipt receiptForHash = new Receipt(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getDouble(5),
                        rs.getDouble(6),
                        billDetailList,
                        delivery);
                //                    get order from mhd = HD16 =>
                if (compareDates(receiptForHash.getExport_date(), "2023-01-09 00:00:00") > 0) {
                    if (rs.getInt(7) != 4) {
                        if (!verifyOrderWhenLoad(receiptForHash, getCypherTextOfOrder(rs.getString(1)), UserService.getListKey(rs.getString(2)))) {
                            updateState(receiptForHash.getId(), 5);
                            re.setStatus(5);
//                      Gửi mail báo lỗi
                            sendMailWhenErr(receiptForHash);
//                        gửi mail báo lỗi
                        }
//                   ================== tạo đối tượng receiptForHash để tến hành xacs thực đơn hang =====================
                    }
                }
                result.add(re);

            }
        }
        return result;
    }


    public static List<String> getAllStatusNameOrder() {
        List<String> list = new ArrayList<>();
        list.add("Chờ Xác Nhận"); //0
        list.add("Gói Hàng");//1
        list.add("Đang Giao");//2
        list.add("Giao Thành Công");//3
        list.add("Đã hủy");//4
        list.add("Đã bị chỉnh sửa");//5
        return list;
    }


    public static Delivery getGiaoHang(String id) {
        Delivery delivery = new Delivery();
        Statement statement = DBConnect.getInstall().get();
        if (statement != null)
            try {
                ResultSet rs = statement.executeQuery("SELECT ID, DELIVERY_DATE, WARD_ID, DISTRICT_ID, ADDRESS,EMAIL,PHONE,NAMECUSTOMER  from DELIVERY where ID =" + "'" + id + "'");
                while (rs.next()) {
                    delivery = new Delivery(rs.getString(1), rs.getString(2),
                            rs.getString(3), rs.getString(4),
                            rs.getString(5), rs.getString(6),
                            rs.getString(7), rs.getString(8));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        else {
            System.out.println("Không có ");
        }
        return delivery;
    }

    public static String getCypherTextOfOrder(String id) {
        Statement statement = DBConnect.getInstall().get();
        String cypherTxt = "";
        if (statement != null)
            try {
                ResultSet rs = statement.executeQuery("SELECT BILLS.CipherText FROM BILLS WHERE BILLS.ID = '" + id + "'");
                while (rs.next()) {
                    cypherTxt = rs.getString(1);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        else {
            System.out.println("Không có ");
        }
        return cypherTxt;
    }


    public static void cancelOrder(String mahd) {
        Statement stm = DBConnect.getInstall().get();
        if (stm != null) {
            try {
                String sql = "UPDATE BILLS set BILLS.STATUS = 4 WHERE BILLS.ID ='" + mahd + "'";
                stm.executeUpdate(sql);
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public static void buyAgain(String mahd) {
        Statement stm = DBConnect.getInstall().get();
        if (stm != null) {
            try {
                String sql = "UPDATE BILLS set BILLS.STATUS = 0 WHERE BILLS.ID ='" + mahd + "'";
                stm.executeUpdate(sql);
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public static void updateTonKhoWhenCancelOrder(String mahd) {
        Statement stm = DBConnect.getInstall().get();
        List<Bill_Detail> billDetailList = getBill_DetailUser(mahd);

        if (stm != null) {
            try {
                for (Bill_Detail billDetail : billDetailList) {
                    String msp = billDetail.getMasp();
                    int sl = billDetail.getSolg();
                    Product p = ProductService.findById(msp);
                    int solgConLai = p.getDetail().getInventory() + sl;
                    String sql = "UPDATE productDetails set productDetails.inventory = " + solgConLai + " WHERE productDetails.idProduct ='" + msp + "'";
                    stm.executeUpdate(sql);
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public static void updateTonKhoWhenBuyAgain(String mahd) {
        Statement stm = DBConnect.getInstall().get();
        List<Bill_Detail> billDetailList = getBill_DetailUser(mahd);

        if (stm != null) {
            try {
                for (Bill_Detail billDetail : billDetailList) {
                    String msp = billDetail.getMasp();
                    int sl = billDetail.getSolg();
                    Product p = ProductService.findById(msp);
                    int solgConLai = p.getDetail().getInventory() - sl;
                    String sql = "UPDATE productDetails set productDetails.inventory = " + solgConLai + " WHERE productDetails.idProduct ='" + msp + "'";
                    stm.executeUpdate(sql);
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }


    public static void updateState(String mahd, int st) {
        Statement stm = DBConnect.getInstall().get();
        if (stm != null) {
            try {
                String sql = "UPDATE BILLS set BILLS.STATUS = " + st + " WHERE BILLS.ID ='" + mahd + "'";
                stm.executeUpdate(sql);
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public static void updateInfoCustomerInBill(String id, String name, String phone, String mail) {
        Statement stm = DBConnect.getInstall().get();
        if (stm != null) {
            try {
                String sql = "UPDATE DELIVERY set DELIVERY.NAMECUSTOMER = '" + name + "', " +
                        "DELIVERY.PHONE = '" + phone + "', DELIVERY.EMAIL = '" + mail + "' " +
                        "WHERE DELIVERY.ID = '" + id + "'";
                stm.executeUpdate(sql);
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public static void updateDeliveryInBill(String id, String deliveryDate, String address) {
        Statement stm = DBConnect.getInstall().get();
        if (stm != null) {
            try {
                String sql = "UPDATE DELIVERY set DELIVERY_DATE = '" + deliveryDate + "', ADDRESS = '" + address + "' WHERE DELIVERY.ID = '" + id + "'";
                stm.executeUpdate(sql);
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public static void updateDeliveryDateInBill(String id, String deliveryDate) {
        Statement stm = DBConnect.getInstall().get();
        if (stm != null) {
            try {
                String sql = "UPDATE DELIVERY set DELIVERY_DATE = '" + deliveryDate + "' WHERE DELIVERY.ID = '" + id + "'";
                stm.executeUpdate(sql);
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public static String convertDate(long timestamp) {
        LocalDateTime dateTime = LocalDateTime.ofEpochSecond(timestamp, 0, java.time.ZoneOffset.UTC);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = dateTime.format(formatter);
        return formattedDateTime;
    }

    public static void updateNoteInBill(String id, String note) {
        Statement stm = DBConnect.getInstall().get();
        if (stm != null) {
            try {
                String sql = "UPDATE BILLS set NOTES = '" + note + "' WHERE BILLS.ID = '" + id + "'";
                stm.executeUpdate(sql);
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }


    public static void updateStatusUser(String id) {
        Statement statement = DBConnect.getInstall().get();
        int status = UserService.findById(id).getStatus();
        if (status != -1) {
            status = -1;
        } else {
            status = 0;
        }
        String sql = "UPDATE ACCOUNTS set  STATUS = " + status + " where ACCOUNTS.ID = '" + id + "'";

        try {
            statement.executeUpdate(sql);

        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    public static void updateRole(int role, String id) {
        Statement statement = DBConnect.getInstall().get();
        String sql = "UPDATE ACCOUNTS set ROLE = " + role + " where ACCOUNTS.ID = '" + id + "'";
        try {
            statement.executeUpdate(sql);

        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    public static void addCTHD(String id, String msp, int slg, String notes) {
        List<String> listMsp = getListMaSpCTHD(id);
        if (msp != null && ProductService.findById(msp) != null) {
            Statement stm = DBConnect.getInstall().get();
            String sql1, sql2 = "";
            int oldTotal = (int) getReceiptByMahd(id).getPro_bill();
            Product p = ProductService.findById(msp);
            int price = p.getPromotional() != 0 ? p.getPromotional() : p.getPrice();

            int newTotal = oldTotal + (price * slg);
            System.out.println(price);
            getReceiptByMahd(id).setPro_bill(newTotal);

            sql1 = "INSERT INTO BILL_DETAIL VALUES('" + id + "','" + msp + "'," + slg + ",'" + notes + "'," + price + ");";
            System.out.println(sql1);
            sql2 = "UPDATE BILLS set BILLS.PRO_BILL = " + newTotal + " WHERE BILLS.ID ='" + id + "'";
            String sql3 = "UPDATE BILL_DETAIL set BILL_DETAIL.AMOUNT = BILL_DETAIL.AMOUNT+" + slg + "" +
                    " WHERE BILL_DETAIL.idProduct = '" + msp + "' and BILL_DETAIL.ID = '" + id + "'";
            try {
                if (listMsp.contains(msp)) {
                    stm.executeUpdate(sql3);
                } else {
                    stm.executeUpdate(sql1);
                }
                stm.executeUpdate(sql2);
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

    }


    public static void deleteProInCTHD(String id, String msp, int slg) {
        Statement stm = DBConnect.getInstall().get();
        String sql, sql2 = "";
        Product p = ProductService.findById(msp);
        int price = p.getPromotional() != 0 ? p.getPromotional() : p.getPrice();
        int oldTotal = (int) getReceiptByMahd(id).getPro_bill();
        int newTotal = oldTotal - (price * slg);
        getReceiptByMahd(id).setPro_bill(newTotal);
        sql = "DELETE FROM BILL_DETAIL WHERE BILL_DETAIL.ID = '" + id + "' and BILL_DETAIL.idProduct = '" + msp + "'";

        sql2 = "UPDATE BILLS set BILLS.PRO_BILL = " + newTotal + " WHERE BILLS.ID ='" + id + "'";
        try {
            if (getListMaSpCTHD(id).size() != 1) {
                stm.executeUpdate(sql);
                stm.executeUpdate(sql2);
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }


    }

    public static void main(String[] args) throws Exception {
        Delivery gh = new Delivery();
        gh.setDiachigiao("HCM");
        gh.setNgayGiao("2022-10-14 00:00:00");
        gh.setEmail("email");
        gh.setPhone("");
        gh.setTenKH("");
        gh.setHuyen("1533");
        gh.setXa("21208");

        List<Bill_Detail> billDetailList = new ArrayList<>();
        billDetailList.add(new Bill_Detail("HD15", "B001", 2, "cmsn", 500000));
        billDetailList.add(new Bill_Detail("HD15", "B003", 2, "cmsn em", 2000000));

        Receipt receipt = new Receipt("HD15", "AD01", "2022-10-12 00:00:00", "bánh", 500000.0, Double.parseDouble("21000"), billDetailList, gh);


//        System.out.println(cypherText + " :cypherText");
//        System.out.println(compareOrderHash(receipt, cypherText, "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAzVump7D2ex/8jvpLILCg5+09QGyxkELGE7pqqNbM39yKlwfCICcO17Xw/TfhIEARoeuCrSFjXqGD3fl+N6iR7mU38DXcRruTYjc1aE2UQExKZM0wmqvzbOcLJqDbnJyoDhDhveXJXN07qypq5ZhQk8CJdeZW+5nanHRQqfFSq18wT/wXbgNA0Z1WYyS0du2sDm4svzEqNWxoExTcacdwlPYoMRm+h4JQMJc2fF4dGkKgLFXBv78jbeHlmWCmhG4ZOxhoUsT0oITIdgSUzTtE46Iq4n+NIFfI/MDC8xY2IRTp+N0zBQQfojgGfKvRjpfhJBktDXAtkwu1vlKdb5wvsQIDAQAB"));

//        String hash1 = RSA.hashObject(receipt);
//        String cypher = ReceiptService.createCypherText(receipt, "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDNW6ansPZ7H/yO+ksgsKDn7T1AbLGQQsYTumqo1szf3IqXB8IgJw7XtfD9N+EgQBGh64KtIWNeoYPd+X43qJHuZTfwNdxGu5NiNzVoTZRATEpkzTCaq/Ns5wsmoNucnKgOEOG95clc3TurKmrlmFCTwIl15lb7mdqcdFCp8VKrXzBP/BduA0DRnVZjJLR27awObiy/MSo1bGgTFNxpx3CU9igxGb6HglAwlzZ8Xh0aQqAsVcG/vyNt4eWZYKaEbhk7GGhSxPSghMh2BJTNO0Tjoirif40gV8j8wMLzFjYhFOn43TMFBB+iOAZ8q9GOl+EkGS0NcC2TC7W+Up1vnC+xAgMBAAECggEAEiTzbCScGpPgbkeaJLjGdseE3nDcHXlw4+RYMTPNZUgorKCAW/PngVKviMgLqZxPkoVrBd4J2B4gNxgouUU5I52o3B3+KPfA1XA4VU9pdmbnOdHRZhEkUG53k0GqNcO3q4p9T5iCeqSHdZ+pU0DqeauM2bBw5gkZgjJawDE00zRMyqmczlbUrgX1K8QXyGxiPKwBvrm1xo9LuP0ROtlUbmk7bWiceEKOes8SoqVbom6nM9x9FTmfSdPleZvI/L4AUhn2YGz5axiVMcLIMOGE5NID8BDCeYZIAH4VBn+BgzYfATXeIN/f1iBbQvQGkfHdyQe4Ak40MxKdLcawGnCVkwKBgQDX3m/Z9vC+yah+PMl0yTz1C0ioiVo2AeIhTV2V8XNLxPAwSPF/j7Mczd8GYFIYrXtx/gQ2MSIeDOLDErmYj8xQntasVh6RrZObyGGaSHtxPoq2QTyczI7wrjucuG2I6Hbde2EjZ7bnbYcMMBeEhJXe14zlDhCYThfpH7NTWtajKwKBgQDziPvIwH/n1PQmhl6g8MWp23T8+5KJ0b5jUdPp9q3g32xjh92JLkZ94tEPB9ItgW8E3aBO72zJdDhOvHHuegPci3G/awy9iGrOc2mj0JrgeRWQsy4XgdclVMWsbRRUD7eGyI82ERah71EXJ6D8/2DJKWaLxVwoFGVBB8KkEM56kwKBgQCdRyaNUxrilsCZgSZyXXB5RQhiKvi8IrEj/F80MSAE028AbxTtEMC6OtDEr4cBDdzEGAPvi+rEpDTPViQ9IIAHOUNFetV45FOIi/140FiIEL41veAwieWXPAqYy9KajmyUQCFtD1K22Rks6xknZwZevwObm5zBqxxGsN8R2zxI0wKBgCDvUpW4qtOQciBPpbaAOUsaWbzozM2EpwnJhC4s5SD3NjJTqnsl3fSiWwF8Xkk3KutuiYKDUKQB6qeDRqkoqSMHXK+1a3iqXT9kTlIQIRHgbjEuejpFnfH3f/AspEYmBfzdXLjEINpvgfjurA/q/YzW435dh3QIJhVgVktX1oSvAoGBAIAa0pMnuDW2qFImIM4LdPbSNUSLicQZnRXnYkX+c87cA+D1ubD6uZyo5spkenFJJnusJBsgtfLuCfYiY/h5csr2AfsiH10gERHSZvqHVd/I/xMG5p8II8T19WDFbOPtBY//aqjwU2CrcbayNJG3H+smVkTqxT76lWED+UYHdZ3q");
//        String plan = RSA.decrypt(cypher, RSA.getPublicKeyFromString("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAzVump7D2ex/8jvpLILCg5+09QGyxkELGE7pqqNbM39yKlwfCICcO17Xw/TfhIEARoeuCrSFjXqGD3fl+N6iR7mU38DXcRruTYjc1aE2UQExKZM0wmqvzbOcLJqDbnJyoDhDhveXJXN07qypq5ZhQk8CJdeZW+5nanHRQqfFSq18wT/wXbgNA0Z1WYyS0du2sDm4svzEqNWxoExTcacdwlPYoMRm+h4JQMJc2fF4dGkKgLFXBv78jbeHlmWCmhG4ZOxhoUsT0oITIdgSUzTtE46Iq4n+NIFfI/MDC8xY2IRTp+N0zBQQfojgGfKvRjpfhJBktDXAtkwu1vlKdb5wvsQIDAQAB"));


//        String hash1 = RSA.hashObject(receipt);
//        String cypher = "x/pJB9qcMcPy14FqMf6q34XjfhPz2ZN7LZQfq/zIXSuX32VBpKsctq2F3hlEivuiVCAjVzRu230KKA9dPE14OROCQpzBAjKwJzQRV57QuJKxZ2iUSfJH+fHS2lmjdlNGiEfV5Jx+zuin1cbT1n0vG2bGsoCPVI49cNPs+/UiwiMCKxRullx2gZIzEIhiN5S8GI1wr76BPcmQE9HEr80qpd1POI27pJTurkc7GFjjDqzxg5WOTMuK5GkLgm+cn1rLbqfELXGvhlU5luzjVE2AoWnxm89K1J+AM2RgznX98f5vW/mID4rsQ0bjr+J95SAB57D1n4vdauhVDsddDhAI1Aps5DwWJgZKVYWvmXqVwvn5RShQmz9kRGQIY87ue9uZBkb9khNsHCe6sOJ8moy6V4Wdd0iUM65Z/tPNMMSQ7g5mENy3JS2oKUwpVe56j0Si";
//        String plan = RSA.decrypt(cypher, RSA.getPublicKeyFromString("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtmIwuvBDwLxdHDcmKL1E0v/4VYaUhlizEwhWbx/62AUpEkRFmMtv9cn8izHPi2Lg1hpkJ1l9tDTFDWwC2QPO0mPMVo8TIqZvtYkuizP2WfZiuZKkuZHOS8RNBkTdAsHDCgFuJyXZIkx1RbeVazUY3zWSv47eNKBQsLnYaxITZy4AX7qz8MtC7CbYiUb5Kfh8hkIiZHmuWEkArfiTT8qgL+0j/f2e7GRn8CqIskUazrxgviR+BbWM9x9x941wCFAvDqCC0KOqc0s0kfOMJGLyLeUKglOyRvuCd4AWxVh5O80szFKLaP2ydjOOk97qKicL2cxJYFERUzy+jylGovH2JQIDAQAB"));
//        System.out.println("hash : "+hash1);
//        System.out.println("cypher : "+cypher);
//        System.out.println("hash fts : "+plan);
//        System.out.println(plan.equals(hash1));

//        cy pher nef : x/pJB9qcMcPy14FqMf6q34XjfhPz2ZN7LZQfq/zIXSuX32VBpKsctq2F3hlEivuiVCAjVzRu230KKA9dPE14OROCQpzBAjKwJzQRV57QuJKxZ2iUSfJH+fHS2lmjdlNGiEfV5Jx+zuin1cbT1n0vG2bGsoCPVI49cNPs+/UiwiMCKxRullx2gZIzEIhiN5S8GI1wr76BPcmQE9HEr80qpd1POI27pJTurkc7GFjjDqzxg5WOTMuK5GkLgm+cn1rLbqfELXGvhlU5luzjVE2AoWnxm89K1J+AM2RgznX98f5vW/mID4rsQ0bjr+J95SAB57D1n4vdauhVDsddDhAI1Aps5DwWJgZKVYWvmXqVwvn5RShQmz9kRGQIY87ue9uZBkb9khNsHCe6sOJ8moy6V4Wdd0iUM65Z/tPNMMSQ7g5mENy3JS2oKUwpVe56j0Si
//        list key: [SignUser{id='3', id_user='103384613610226111940', pbkey='MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtmIwuvBDwLxdHDcmKL1E0v/4VYaUhlizEwhWbx/62AUpEkRFmMtv9cn8izHPi2Lg1hpkJ1l9tDTFDWwC2QPO0mPMVo8TIqZvtYkuizP2WfZiuZKkuZHOS8RNBkTdAsHDCgFuJyXZIkx1RbeVazUY3zWSv47eNKBQsLnYaxITZy4AX7qz8MtC7CbYiUb5Kfh8hkIiZHmuWEkArfiTT8qgL+0j/f2e7GRn8CqIskUazrxgviR+BbWM9x9x941wCFAvDqCC0KOqc0s0kfOMJGLyLeUKglOyRvuCd4AWxVh5O80szFKLaP2ydjOOk97qKicL2cxJYFERUzy+jylGovH2JQIDAQAB', createDate='2023-12-29 11:53:13', expiredDate='null', status=1}]
//        publickeyString MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtmIwuvBDwLxdHDcmKL1E0v/4VYaUhlizEwhWbx/62AUpEkRFmMtv9cn8izHPi2Lg1hpkJ1l9tDTFDWwC2QPO0mPMVo8TIqZvtYkuizP2WfZiuZKkuZHOS8RNBkTdAsHDCgFuJyXZIkx1RbeVazUY3zWSv47eNKBQsLnYaxITZy4AX7qz8MtC7CbYiUb5Kfh8hkIiZHmuWEkArfiTT8qgL+0j/f2e7GRn8CqIskUazrxgviR+BbWM9x9x941wCFAvDqCC0KOqc0s0kfOMJGLyLeUKglOyRvuCd4AWxVh5O80szFKLaP2ydjOOk97qKicL2cxJYFERUzy+jylGovH2JQIDAQAB
//        fstHash 298e00c2c63ea896890ae5158e3ab047587ce93da5ca3c050965f914fd6eeede
//        hashOrder c73d3a4aef81cde5eb126ddfcaa2f8ff946d694c9899c6263b716344120f7c28
    }


}