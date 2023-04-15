package vn.edu.hcmuaf.fit.service;

import vn.edu.hcmuaf.fit.db.DBConnect;
import vn.edu.hcmuaf.fit.model.*;

import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReceiptService {
    public static List<Receipt> getData() {
        List<Receipt> list = new ArrayList<Receipt>();
        Statement statement = DBConnect.getInstall().get();
        Statement stmt1 = DBConnect.getInstall().get();
        String sql = "select BILLS.ID, ACCOUNTS.NAME, products.productName, " +
                "CUSTOMERS.PHONE, BILLS.EXPORT_DATE, DELIVERY.DELIVERY_DATE," +
                " DELIVERY.ADDRESS, BILLS.NOTES, products.price, BILLS.TOTAL_BILL, " +
                " BILLS.STATUS, CUSTOMERS.ID, products.idProduct,ACCOUNTS.ROLE, ACCOUNTS.EMAIL\n" +
                "from products, BILLS, CUSTOMERS, BILL_DETAIL, DELIVERY, ACCOUNTS where BILLS.ID = BILL_DETAIL.ID and BILL_DETAIL.idProduct = products.idProduct and DELIVERY.ID = BILLS.ID and CUSTOMERS.ID = BILLS.CUSTOMER_ID and ACCOUNTS.ID = CUSTOMERS.ID \n" +
                "group by BILLS.ID ORDER BY BILLS.EXPORT_DATE desc;";
        if (statement != null)
            try {
                ResultSet rs = statement.executeQuery(sql);
                while (rs.next()) {
                    Receipt rc = new Receipt(rs.getString(1),
                            rs.getString(2), rs.getString(3),
                            rs.getString(4), rs.getString(5),
                            rs.getString(6), rs.getString(7),
                            rs.getString(8), rs.getInt(9),
                            rs.getInt(10), rs.getInt(11),
                            rs.getString(12), rs.getInt(14),
                            rs.getString(15));
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

    public static List<Receipt> getAllReceipt() {
        List<Receipt> list = new ArrayList<Receipt>();
        Statement statement = DBConnect.getInstall().get();
        Statement stmt1 = DBConnect.getInstall().get();
        if (statement != null)
            try {
                ResultSet rs = statement.executeQuery("SELECT  ID, CUSTOMER_ID, EXPORT_DATE, NOTES, TOTAL_BILL, STATUS, CREATE_BY FROM BILLS ORDER BY BILLS.EXPORT_DATE DESC");
                while (rs.next()) {
                    ResultSet rsDiaChiGiao = stmt1.executeQuery("SELECT DELIVERY.ADDRESS, DELIVERY.ID FROM DELIVERY");
                    String diachi = "";
                    while (rsDiaChiGiao.next()) {
                        String s2 = rsDiaChiGiao.getString(2);
                        if (rs.getString(1).equals(s2)) {
                            diachi = rsDiaChiGiao.getString(1);
                        }
                    }
                    Receipt rc = new Receipt(rs.getString(1), rs.getString(2),
                            rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6), diachi, rs.getString(7));
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

    public static List<Receipt> getAllReceiptToDay() {
        List<Receipt> list = new ArrayList<Receipt>();
        Statement statement = DBConnect.getInstall().get();
        if (statement != null)
            try {
                ResultSet rs = statement.executeQuery("SELECT  ID, CUSTOMER_ID, EXPORT_DATE, NOTES, TOTAL_BILL, STATUS FROM BILLS\n" +
                        "WHERE date(EXPORT_DATE) = date(CURRENT_DATE) \n" +
                        "and MONTH(EXPORT_DATE) = MONTH(CURRENT_DATE) and YEAR(EXPORT_DATE) = YEAR(CURRENT_DATE) and  STATUS != 4\n" +
                        "ORDER BY BILLS.ID DESC");
                while (rs.next()) {
                    Receipt rc = new Receipt(rs.getString(1), rs.getString(2),
                            rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6));
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

    public static List<Receipt> getAllReceiptThisMonth() {
        List<Receipt> list = new ArrayList<Receipt>();
        Statement statement = DBConnect.getInstall().get();
        if (statement != null)
            try {
                ResultSet rs = statement.executeQuery("SELECT  ID, CUSTOMER_ID, EXPORT_DATE, NOTES, TOTAL_BILL,  STATUS FROM BILLS\n" +
                        "WHERE MONTH(EXPORT_DATE) = month(CURRENT_DATE) and YEAR(EXPORT_DATE) = YEAR(CURRENT_DATE)\n" +
                        "ORDER BY BILLS.ID DESC");
                while (rs.next()) {
                    Receipt rc = new Receipt(rs.getString(1), rs.getString(2),
                            rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6));
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
                ResultSet rs = statement.executeQuery("SELECT products.productName, sum(BILL_DETAIL.AMOUNT) as slg\n" +
                        "from products, bills, BILL_DETAIL\n" +
                        "WHERE  bills.ID = BILL_DETAIL.ID and products.idProduct = BILL_DETAIL.idProduct \n" +
                        "and bills.STATUS != 4 and month(bills.EXPORT_DATE) = MONTH(CURRENT_DATE) " +
                        "and year(bills.EXPORT_DATE) =YEAR(CURRENT_DATE)\n" +
                        "GROUP BY  products.idProduct\n HAVING slg > 0\n" +
                        "ORDER BY slg DESC LIMIT 10");
                while (rs.next()) {
                    map.put(rs.getString(1), rs.getInt(2));
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
                ResultSet rs = statement.executeQuery("SELECT BILLS.ID, sum(BILL_DETAIL.AMOUNT) FROM  BILL_DETAIL, BILLS\n" +
                        "WHERE date(EXPORT_DATE) = date(CURRENT_DATE) and MONTH(EXPORT_DATE) = month(CURRENT_DATE) and YEAR(EXPORT_DATE) = YEAR(CURRENT_DATE)\n" +
                        "and BILLS.ID =  BILL_DETAIL.ID and BILLS.STATUS != 4");
                while (rs.next()) {
                    result = rs.getInt(2);
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
        int rs = 0;
        for (Receipt r : getAllReceiptToDay()) {
            if (r.getStatus() != 4) {
                rs += r.getMoney();
            }
        }
        return rs;
    }

    public static int getDoanhThuThisMonth() {
        int rs = 0;
        for (Receipt r : getAllReceiptThisMonth()) {
            if (r.getStatus() != 4) {
                rs += r.getMoney();
            }
        }
        return rs;
    }

    public static List<Bill_Detail> getListCTHD() {
        List<Bill_Detail> list = new ArrayList<>();
        Statement statement = DBConnect.getInstall().get();
        if (statement != null)
            try {
                ResultSet rs = statement.executeQuery("SELECT BILL_DETAIL.ID, BILL_DETAIL.idProduct, products.productName, products.price, BILL_DETAIL.AMOUNT, BILL_DETAIL.NOTES from BILLS, BILL_DETAIL, products\n" +
                        "WHERE BILL_DETAIL.ID = BILLS.ID and BILL_DETAIL.idProduct = products.idProduct ORDER BY BILL_DETAIL.ID DESC ");
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


    public static List<Receipt> getctkh(String makh) {
        List<Receipt> list = getData();
        List<Receipt> rs = new ArrayList<>();
        for (Receipt rc : list) {
            if (rc.getMakh().equals(makh)) {
                rs.add(rc);
            }
        }
        return rs;
    }

    public static List<Bill_Detail> getcthdUser(String mahd) {
        List<Bill_Detail> list = getListCTHD();
        List<Bill_Detail> rs = new ArrayList<>();
        for (Bill_Detail rc : list) {
            if (rc.getMahd().equals(mahd)) {
                rs.add(rc);
            }
        }

        return rs;
    }

    public static Receipt getReceiptByMahd(String mhd) {
        List<Receipt> list = getData();
        for (Receipt rc : list) {
            if (rc.getId().equals(mhd)) {
                return rc;
            }
        }

        return null;
    }

    public static List<Receipt> getReceiptByMakh(String makh) {
        List<Receipt> list = getAllReceipt();
        List<Receipt> rs = new ArrayList<>();
        for (Receipt rc : list) {
            if (rc.getMakh().equals(makh)) {
                rs.add(rc);
            }
        }

        return rs;
    }

    public static List<String> getAllStatusNameOrder() {
        List<String> list = new ArrayList<>();
        list.add("Chờ Xác Nhận"); //0
        list.add("Gói Hàng");//1
        list.add("Đang Giao");//2
        list.add("Giao Thành Công");//3
        list.add("Đã hủy");//4
        return list;
    }


    public static Delivery getListGiaoHang(String id) {
        List<Delivery> list = new ArrayList<Delivery>();
        Statement statement = DBConnect.getInstall().get();
        if (statement != null)
            try {
                ResultSet rs = statement.executeQuery("SELECT ID, DELIVERY_DATE, ADDRESS,EMAIL,PHONE,NAMECUSTOMER  from DELIVERY where ID =" + "'" + id + "'");
                while (rs.next()) {
                    Delivery delivery = new Delivery(rs.getString(1), rs.getString(6),
                            rs.getString(2), rs.getString(3),
                            rs.getString(4), rs.getString(5));
                    list.add(delivery);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        else {
            System.out.println("Không có ");
        }
        return list.get(0);
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
        List<Bill_Detail> billDetailList = getcthdUser(mahd);

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
        List<Bill_Detail> billDetailList = getcthdUser(mahd);

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
                        "WHERE DELIVERY.id = '" + id + "'";
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
        String sql = "UPDATE ACCOUNTS set ROLE = " + role + " where ACCOUNTS.id = '" + id + "'";
        try {
            statement.executeUpdate(sql);

        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    public static void addCTHD(String id, String msp, int slg, String notes) {
            List<String> listMsp = getListMaSpCTHD(id);
            if(msp != null && ProductService.findById(msp) != null) {
                Statement stm = DBConnect.getInstall().get();
                String sql1, sql2 = "";
                int oldTotal = getReceiptByMahd(id).getTotal();
                int newTotal = oldTotal + (ProductService.findById(msp).getPrice() * slg);
                getReceiptByMahd(id).setTotal(newTotal);

                sql1 = "INSERT INTO BILL_DETAIL VALUES('" + id + "','" + msp + "'," + slg + ",'" + notes + "');";
                sql2 = "UPDATE BILLS set BILLS.TOTAL_BILL = " + newTotal + " WHERE BILLS.ID ='" + id + "'";
                String sql3 = "UPDATE bill_detail set bill_detail.AMOUNT = bill_detail.AMOUNT+" + slg + "" +
                        " WHERE bill_detail.idProduct = '" + msp + "' and bill_detail.ID = '" + id + "'";
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

        int oldTotal = getReceiptByMahd(id).getTotal();
        int newTotal = oldTotal - (ProductService.findById(msp).getPrice() * slg);
        getReceiptByMahd(id).setTotal(newTotal);
        sql = "DELETE FROM bill_detail WHERE bill_detail.ID = '"+id+"' and bill_detail.idProduct = '"+msp+"'";

        sql2 = "UPDATE BILLS set BILLS.TOTAL_BILL = " + newTotal + " WHERE BILLS.ID ='" + id + "'";
        try {
            if(getListMaSpCTHD(id).size() != 1){
                stm.executeUpdate(sql);
                stm.executeUpdate(sql2);
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }


    }
//    public static void saveAdd(String id, String msp, int slg) {
//        Statement stm = DBConnect.getInstall().get();
//       String sql = "";
//       int oldTotal = getReceiptByMahd(id).getTotal();
//        int newTotal = oldTotal + (ProductService.findById(msp).getPrice() * slg);
//        getReceiptByMahd(id).setTotal(newTotal);
//
//        sql = "UPDATE BILLS set BILLS.TOTAL_BILL = " + newTotal + " WHERE BILLS.ID ='" + id + "'";
//        try {
//           stm.executeUpdate(sql);
//        } catch (SQLException se) {
//            se.printStackTrace();
//        }
//    }
//    public static void saveRemove(String id, String msp, int slg) {
//        Statement stm = DBConnect.getInstall().get();
//       String sql = "";
//       int oldTotal = getReceiptByMahd(id).getTotal();
//        int newTotal = oldTotal - (ProductService.findById(msp).getPrice() * slg);
//        getReceiptByMahd(id).setTotal(newTotal);
//
//        sql = "UPDATE BILLS set BILLS.TOTAL_BILL = " + newTotal + " WHERE BILLS.ID ='" + id + "'";
//        try {
//           stm.executeUpdate(sql);
//        } catch (SQLException se) {
//            se.printStackTrace();
//        }
//    }

    public static void main(String[] args) {
//        addCTHD("HD13", "B054", 2, "CMSN");
//        System.out.println(getListCTHD("HD13"));
//        deleteProInCTHD("HD13", "B054", 6);
        System.out.println(getListMaSpCTHD("HD16"));
    }


}
