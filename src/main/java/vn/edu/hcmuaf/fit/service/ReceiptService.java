package vn.edu.hcmuaf.fit.service;

import vn.edu.hcmuaf.fit.db.DBConnect;
import vn.edu.hcmuaf.fit.model.*;

import java.sql.*;
import java.util.*;

public class ReceiptService {
    public static List<Receipt> getData() {
        List<Receipt> list = new LinkedList<Receipt>();
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
                    List<Comment> listCmts = new LinkedList<Comment>();
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
        List<Receipt> list = new LinkedList<Receipt>();
        Statement statement = DBConnect.getInstall().get();
        Statement stmt1 = DBConnect.getInstall().get();
        if (statement != null)
            try {
                ResultSet rs = statement.executeQuery("SELECT BILL_ID, CUSTOMER_ID, EXPORT_DATE, NOTES, TOTAL_BILL, BILL_STATUS FROM BILLS ORDER BY BILLS.BILL_ID DESC");
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
        List<Receipt> list = new LinkedList<Receipt>();
        Statement statement = DBConnect.getInstall().get();
        if (statement != null)
            try {
                ResultSet rs = statement.executeQuery("SELECT BILL_ID, CUSTOMER_ID, EXPORT_DATE, NOTES, TOTAL_BILL, BILL_STATUS FROM BILLS\n" +
                        "WHERE EXPORT_DATE = CURRENT_DATE and BILL_STATUS != 4\n" +
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
        List<Receipt> list = new LinkedList<Receipt>();
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

    public static int getNumberProThisMonth() {
        int result = 0;
        Statement statement = DBConnect.getInstall().get();
        if (statement != null)
            try {
                ResultSet rs = statement.executeQuery("SELECT BILLS.BILL_ID, sum(BILL_DETAIL.AMOUNT) FROM BILL_DETAIL, BILLS\n" +
                        "WHERE MONTH(EXPORT_DATE) = month(CURRENT_DATE) and YEAR(EXPORT_DATE) = YEAR(CURRENT_DATE)\n" +
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

    public static List<CTHD> getListCTHD() {
        List<CTHD> list = new LinkedList<CTHD>();
        Statement statement = DBConnect.getInstall().get();
        Statement stmt = DBConnect.getInstall().get();
        if (statement != null)
            try {
                ResultSet rs = statement.executeQuery("SELECT BILL_DETAIL.BILL_ID, BILL_DETAIL.idProduct, products.productName, products.price, BILL_DETAIL.AMOUNT, BILL_DETAIL.NOTES from BILLS, BILL_DETAIL, products\n" +
                        "WHERE BILL_DETAIL.BILL_ID = BILLS.BILL_ID and BILL_DETAIL.idProduct = products.idProduct ORDER BY BILL_DETAIL.BILL_ID DESC ");
                while (rs.next()) {
                    ResultSet rsImg = stmt.executeQuery("SELECT productImgs.idProduct,productImgs.img from productImgs");
                    List<String> listImg = new LinkedList<String>();

                    while (rsImg.next()) {
                        String s2 = rsImg.getString(1);
                        if (rs.getString(2).equals(s2)) {
                            listImg.add(rsImg.getString(2));
                        }
                    }
                    CTHD cthd = new CTHD(rs.getString(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getInt(5), listImg,
                            rs.getInt(4),
                            rs.getString(6));
                    list.add(cthd);
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
        List<Receipt> rs = new LinkedList<>();
        for (Receipt rc : list) {
            if (rc.getMakh().equals(makh)) {
                rs.add(rc);
            }
        }
        return rs;
    }

    public static List<CTHD> getcthdUser(String mahd) {
        List<CTHD> list = getListCTHD();
        List<CTHD> rs = new LinkedList<>();
        for (CTHD rc : list) {
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
        List<Receipt> rs = new LinkedList<>();
        for (Receipt rc : list) {
            if (rc.getMakh().equals(makh)) {
                rs.add(rc);
            }
        }

        return rs;
    }


    public static List<Ship> getListGiaoHang() {
        List<Ship> list = new LinkedList<Ship>();
        Statement statement = DBConnect.getInstall().get();
        if (statement != null)
            try {
                ResultSet rs = statement.executeQuery("SELECT BILL_ID, DELIVERY_DATE, DELIVERY_ADDRESS from DELIVERY");
                while (rs.next()) {
                    Ship ship = new Ship(rs.getString(1), rs.getString(2), rs.getString(3));
                    list.add(ship);
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
        List<CTHD> cthdList = getcthdUser(mahd);

        if (stm != null) {
            try {
                for (CTHD cthd : cthdList) {
                    String msp = cthd.getMasp();
                    int sl = cthd.getSolg();
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
        List<CTHD> cthdList = getcthdUser(mahd);

        if (stm != null) {
            try {
                for (CTHD cthd : cthdList) {
                    String msp = cthd.getMasp();
                    int sl = cthd.getSolg();
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
        String sql = "UPDATE ACCOUNTS set  ACCOUNT_ROLE = " + role + " where ACCOUNTS.id = '" + id + "'";

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
