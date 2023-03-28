package vn.edu.hcmuaf.fit.service;

import vn.edu.hcmuaf.fit.db.DBConnect;
import vn.edu.hcmuaf.fit.model.*;

import java.sql.*;
import java.util.*;

public class ReceiptService {
    public static List<Receipt> getData() {
        List<Receipt> list = new ArrayList<Receipt>();
        Statement statement = DBConnect.getInstall().get();
        Statement stmt1 = DBConnect.getInstall().get();
        String sql = "select BILLS.BILL_ID, CUSTOMERS.CUSTOMER_NAME, products.productName, CUSTOMERS.CUSTOMER_PHONE, BILLS.EXPORT_DATE, DELIVERY.DELIVERY_DATE, DELIVERY.DELIVERY_ADDRESS, BILLS.NOTES, products.price, BILLS.TOTAL_BILL,  BILLS.BILL_STATUS, CUSTOMERS.CUSTOMER_ID, products.idProduct, ACCOUNTS.ACCOUNT_NAME,ACCOUNTS.ACCOUNT_ROLE, ACCOUNTS.ACCOUNT_EMAIL\n" +
                "from products, BILLS, CUSTOMERS, BILL_DETAIL, DELIVERY, ACCOUNTS where BILLS.BILL_ID = BILL_DETAIL.BILL_ID and BILL_DETAIL.idProduct = products.idProduct and DELIVERY.BILL_ID = BILLS.BILL_ID and CUSTOMERS.CUSTOMER_ID = BILLS.CUSTOMER_ID and ACCOUNTS.ACCOUNT_ID = CUSTOMERS.CUSTOMER_ID \n" +
                "group by BILLS.BILL_ID ORDER BY BILLS.EXPORT_DATE desc;";
        if (statement != null)
            try {
                ResultSet rs = statement.executeQuery(sql);
                while (rs.next()) {
                    ResultSet rsCmt = stmt1.executeQuery("SELECT idProduct, ACCOUNTS.ACCOUNT_NAME,comment,date, IdCmt, Comments.STATUS from Comments, ACCOUNTS where ACCOUNTS.ACCOUNT_ID = Comments.ID");
                    List<Comment> listCmts = new ArrayList<Comment>();
                    String s1 = rs.getString(13);
                    while (rsCmt.next()) {
                        String s2 = rsCmt.getString(1);
                        if (s1.equals(s2)) {
                            listCmts.add(new Comment(rsCmt.getString(1), rsCmt.getString(2), rsCmt.getString(3), rsCmt.getString(4), rsCmt.getInt(5), rsCmt.getInt(6)));
                        }
                    }
                    Receipt rc = new Receipt(rs.getString(1),
                            rs.getString(12), rs.getString(2),
                            rs.getString(3), rs.getString(4),
                            rs.getString(5), rs.getString(6),
                            rs.getString(7), rs.getString(8),
                            rs.getInt(9), rs.getInt(10),
                            rs.getInt(11), listCmts,
                            rs.getString(14), rs.getInt(15),
                            rs.getString(16));
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

    public static List<Receipt> getAllReceipt() {
        List<Receipt> list = new ArrayList<Receipt>();
        Statement statement = DBConnect.getInstall().get();
        Statement stmt1 = DBConnect.getInstall().get();
        if (statement != null)
            try {
                ResultSet rs = statement.executeQuery("SELECT BILL_ID, CUSTOMER_ID, EXPORT_DATE, NOTES, TOTAL_BILL, BILL_STATUS FROM BILLS ORDER BY BILLS.EXPORT_DATE DESC");
                while (rs.next()) {
                    ResultSet rsDiaChiGiao = stmt1.executeQuery("SELECT DELIVERY.DELIVERY_ADDRESS, DELIVERY.BILL_ID FROM DELIVERY");
                    String diachi = "";
                    while (rsDiaChiGiao.next()) {
                        String s2 = rsDiaChiGiao.getString(2);
                        if (rs.getString(1).equals(s2)) {
                            diachi = rsDiaChiGiao.getString(1);
                        }
                    }
                    Receipt rc = new Receipt(rs.getString(1), rs.getString(2),
                            rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6), diachi);
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
                ResultSet rs = statement.executeQuery("SELECT BILL_ID, CUSTOMER_ID, EXPORT_DATE, NOTES, TOTAL_BILL, BILL_STATUS FROM BILLS\n" +
                        "WHERE date(EXPORT_DATE) = date(CURRENT_DATE) \n" +
                        "and MONTH(EXPORT_DATE) = MONTH(CURRENT_DATE) and YEAR(EXPORT_DATE) = YEAR(CURRENT_DATE) and BILL_STATUS != 4\n" +
                        "ORDER BY BILLS.BILL_ID DESC");
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
                ResultSet rs = statement.executeQuery("SELECT BILL_ID, CUSTOMER_ID, EXPORT_DATE, NOTES, TOTAL_BILL, BILL_STATUS FROM BILLS\n" +
                        "WHERE MONTH(EXPORT_DATE) = month(CURRENT_DATE) and YEAR(EXPORT_DATE) = YEAR(CURRENT_DATE)\n" +
                        "ORDER BY BILLS.BILL_ID DESC");
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
    public static Map<String,Integer> getAllCakeThisMonth() {
        Map<String,Integer> map = new HashMap<>();
        Statement statement = DBConnect.getInstall().get();
        if (statement != null)
            try {
                ResultSet rs = statement.executeQuery("SELECT products.productName, sum(bill_detail.AMOUNT) as slg\n" +
                        "from products, bills, bill_detail\n" +
                        "WHERE  bills.BILL_ID = bill_detail.BILL_ID and products.idProduct = bill_detail.idProduct \n" +
                        "and bills.BILL_STATUS != 4 and month(bills.EXPORT_DATE) = MONTH(CURRENT_DATE) and year(bills.EXPORT_DATE) =YEAR(CURRENT_DATE)\n" +
                        "GROUP BY  products.idProduct\n" +
                        "ORDER BY slg DESC");
                while (rs.next()) {
                   map.put(rs.getString(1), rs.getInt(2));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        else {
            System.out.println("Không có  hóa đơn");
        }
        return map;
    }
    public static int getNumberProToDay() {
        int result = 0;
        Statement statement = DBConnect.getInstall().get();
        if (statement != null)
            try {
                ResultSet rs = statement.executeQuery("SELECT BILLS.BILL_ID, sum(BILL_DETAIL.AMOUNT) FROM BILL_DETAIL, BILLS\n" +
                        "WHERE date(EXPORT_DATE) = date(CURRENT_DATE) and MONTH(EXPORT_DATE) = month(CURRENT_DATE) and YEAR(EXPORT_DATE) = YEAR(CURRENT_DATE)\n" +
                        "and BILLS.BILL_ID = BILL_DETAIL.BILL_ID and BILLS.BILL_STATUS != 4");
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

    public static List<Bill_Detail> getListCTHD() {
        List<Bill_Detail> list = new ArrayList<>();
        Statement statement = DBConnect.getInstall().get();
        if (statement != null)
            try {
                ResultSet rs = statement.executeQuery("SELECT BILL_DETAIL.BILL_ID, BILL_DETAIL.idProduct, products.productName, products.price, BILL_DETAIL.AMOUNT, BILL_DETAIL.NOTES from BILLS, BILL_DETAIL, products\n" +
                        "WHERE BILL_DETAIL.BILL_ID = BILLS.BILL_ID and BILL_DETAIL.idProduct = products.idProduct ORDER BY BILL_DETAIL.BILL_ID DESC ");
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
        list.add("Chờ Xác Nhận");
        list.add("Chờ Lấy Hàng");
        list.add("Đang Giao");
        list.add( "Giao Thành Công");
        list.add( "Đã hủy");

        return list;
    }


    public static List<Delivery> getListGiaoHang() {
        List<Delivery> list = new ArrayList<Delivery>();
        Statement statement = DBConnect.getInstall().get();
        if (statement != null)
            try {
                ResultSet rs = statement.executeQuery("SELECT BILL_ID, DELIVERY_DATE, DELIVERY_ADDRESS,DELIVERY_EMAIL,DELIVERY_PHONE,DELIVERY_NAME  from DELIVERY");
                while (rs.next()) {
                    Delivery delivery = new Delivery(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),rs.getString(6));
                    list.add(delivery);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        else {
            System.out.println("Không có ");
        }
        return list;
    }


    public static void cancelOrder(String mahd) {
        Statement stm = DBConnect.getInstall().get();
        if (stm != null) {
            try {
                String sql = "UPDATE BILLS set BILLS.BILL_STATUS = 4 WHERE BILLS.BILL_ID ='" + mahd + "'";
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
                String sql = "UPDATE BILLS set BILLS.BILL_STATUS = 0 WHERE BILLS.BILL_ID ='" + mahd + "'";
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

    public static void updateStateMove(String mahd) {
        Statement stm = DBConnect.getInstall().get();
        if (stm != null) {
            try {
                String sql = "UPDATE BILLS set BILLS.BILL_STATUS = 2 WHERE BILLS.BILL_ID ='" + mahd + "'";
                stm.executeUpdate(sql);
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public static void updateStateAccept(String mahd) {
        Statement stm = DBConnect.getInstall().get();
        if (stm != null) {
            try {
                String sql = "UPDATE BILLS set BILLS.BILL_STATUS = 1 WHERE BILLS.BILL_ID ='" + mahd + "'";
                stm.executeUpdate(sql);
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }


    public static void updateStatus(String id) {
        Statement statement = DBConnect.getInstall().get();
        int status = UserService.findById(id).getAccount_status();
        if (status != -1) {
            status = -1;
        } else {
            status = 0;
        }
        String sql = "UPDATE ACCOUNTS set  ACCOUNT_STATUS = " + status + " where ACCOUNTS.ACCOUNT_ID = '" + id + "'";

        try {
            statement.executeUpdate(sql);

        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    public static void updateRole(int role, String id) {
        Statement statement = DBConnect.getInstall().get();
        String sql = "UPDATE ACCOUNTS set  ACCOUNT_ROLE = " + role + " where ACCOUNTS.account_id = '" + id + "'";
        try {
            statement.executeUpdate(sql);

        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    public static int getDoanhThuToDay() {
        int rs = 0;
        for (Receipt r : getAllReceiptToDay()) {
            if (r.getStateInt() != 4) {
                rs += r.getMoney();
            }
        }
        return rs;
    }

    public static int getDoanhThuThisMonth() {
        int rs = 0;
        for (Receipt r : getAllReceiptThisMonth()) {
            if (r.getStateInt() != 4) {
                rs += r.getMoney();
            }
        }
        return rs;
    }


    public static void main(String[] args) {
        for (Receipt r : getData()) {
            System.out.println(r.getCommentList().toString());
        }

    }


}
