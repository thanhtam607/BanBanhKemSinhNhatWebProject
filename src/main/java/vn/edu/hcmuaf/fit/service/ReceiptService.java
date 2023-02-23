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
        String sql = "select hoadon.mahd, khachhang.TENKH, sanpham.TenSP, khachhang.sdt, hoadon.NGAYLAPHD, giaohang.NGAYGIAO, giaohang.DIACHIGIAO, hoadon.ghichu, sanpham.gia, hoadon.THANHTIEN,  hoadon.STATUS, khachhang.makh, sanpham.masp, taikhoan.TENTK,taikhoan.role, taikhoan.email\n" +
                "from sanpham, hoadon, khachhang, cthd, giaohang, taikhoan where hoadon.mahd = cthd.MAHD and cthd.MASP = sanpham.MaSP and giaohang.MAHD = hoadon.MAHD and khachhang.MAKH = hoadon.MAKH and taikhoan.id = khachhang.makh \n" +
                "group by hoadon.MAHD ORDER BY hoadon.NGAYLAPHD desc;";
        if (statement != null)
            try {
                ResultSet rs = statement.executeQuery(sql);
                while (rs.next()) {
                    ResultSet rsCmt = stmt1.executeQuery("SELECT MaSP, TAIKHOAN.TENTK,BinhLuan,NgayBL, IdCmt from Comments, TAIKHOAN where TAIKHOAN.ID = Comments.ID");
                    List<Comment> listCmts = new LinkedList<Comment>();
                    String s1 = rs.getString(13);
                    while (rsCmt.next()) {
                        String s2 = rsCmt.getString(1);
                        if (s1.equals(s2)) {
                            listCmts.add(new Comment(rsCmt.getString(1), rsCmt.getString(2), rsCmt.getString(3), rsCmt.getString(4), rsCmt.getInt(5)));
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
                ResultSet rs = statement.executeQuery("SELECT MAHD, MAKH, NGAYLAPHD, GHICHU, THANHTIEN, STATUS FROM hoadon ORDER BY hoadon.MAHD DESC");
                while (rs.next()) {
                    ResultSet rsDiaChiGiao = stmt1.executeQuery("SELECT giaohang.DIACHIGIAO, giaohang.MAHD FROM giaohang");
                    String diachi = "";
                    while (rsDiaChiGiao.next()){
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
                ResultSet rs = statement.executeQuery("SELECT MAHD, MAKH, NGAYLAPHD, GHICHU, THANHTIEN, STATUS FROM hoadon\n" +
                        "WHERE NGAYLAPHD = CURRENT_DATE\n" +
                        "ORDER BY hoadon.MAHD DESC");
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
                ResultSet rs = statement.executeQuery("SELECT MAHD, MAKH, NGAYLAPHD, GHICHU, THANHTIEN, STATUS FROM hoadon\n" +
                        "WHERE MONTH(NGAYLAPHD) = month(CURRENT_DATE) and YEAR(NGAYLAPHD) = YEAR(CURRENT_DATE)\n" +
                        "ORDER BY hoadon.MAHD DESC");
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
                ResultSet rs = statement.executeQuery("SELECT hoadon.MAHD, sum(cthd.SL) FROM cthd, hoadon\n" +
                        "WHERE MONTH(NGAYLAPHD) = month(CURRENT_DATE) and YEAR(NGAYLAPHD) = YEAR(CURRENT_DATE)\n" +
                        "and hoadon.MAHD = cthd.MAHD");
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
        Statement stmt1 = DBConnect.getInstall().get();
        if (statement != null)
            try {
                ResultSet rs = statement.executeQuery("SELECT cthd.MAHD, cthd.MASP, sanpham.TenSP, sanpham.Gia, cthd.SL from hoadon, cthd, sanpham\n" +
                        "WHERE cthd.MAHD = hoadon.MAHD and cthd.MASP = sanpham.MaSP ORDER BY cthd.MAHD DESC ");
                while (rs.next()) {
                    ResultSet rsImg = stmt.executeQuery("SELECT anhsp.MaSP,anhsp.Anh from anhsp");
                    List<String> listImg = new LinkedList<String>();

                    while (rsImg.next()) {
                        String s2 = rsImg.getString(1);
                        if (rs.getString(2).equals(s2)) {
                            listImg.add(rsImg.getString(2));
                        }
                    }
                    CTHD cthd = new CTHD(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(5), listImg, rs.getInt(4));
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


    public static List<Ship> getListGiaoHang(){
        List<Ship> list = new LinkedList<Ship>();
        Statement statement = DBConnect.getInstall().get();
        if (statement != null)
            try {
                ResultSet rs = statement.executeQuery("SELECT MAHD, NGAYGIAO, DIACHIGIAO from giaohang");
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
                String sql = "UPDATE hoadon set hoadon.STATUS = 4 WHERE hoadon.MAHD ='" + mahd + "'";
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
                String sql = "UPDATE hoadon set hoadon.STATUS = 0 WHERE hoadon.MAHD ='" + mahd + "'";
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
                    String sql = "UPDATE ctsp set ctsp.tonKho = " + solgConLai + " WHERE ctsp.MaSP ='" + msp + "'";
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
                    String sql = "UPDATE ctsp set ctsp.tonKho = " + solgConLai + " WHERE ctsp.MaSP ='" + msp + "'";
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
                String sql = "UPDATE hoadon set hoadon.STATUS = 2 WHERE hoadon.MAHD ='" + mahd + "'";
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
                String sql = "UPDATE hoadon set hoadon.STATUS = 1 WHERE hoadon.MAHD ='" + mahd + "'";
                stm.executeUpdate(sql);
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public static List<String> getMahd(String makh) {
        List<String> rs = new LinkedList<>();
        List<Receipt> list = ReceiptService.getData();
        for (Receipt r : list) {
            if (makh.equals(r.getMakh()))
                rs.add(r.getId());
        }

        return rs;
    }


    public static void updateStatus(String id) {
        Statement statement = DBConnect.getInstall().get();
        int status = UserService.findById(id).getStatus();
        if(status != -1) {
            status = -1;
        }else{
            status = 0;
        }
        String sql = "UPDATE taikhoan set  status= " + status + " where taikhoan.id = '" + id + "'";

        try {
            statement.executeUpdate(sql);

        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
    public static void updateRole(int role, String id) {
        Statement statement = DBConnect.getInstall().get();
        String sql = "UPDATE taikhoan set  role= " + role + " where taikhoan.id = '" + id + "'";

        try {
            statement.executeUpdate(sql);

        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    public static int getDoanhThuToDay() {
        int rs = 0;
        for (Receipt r : getAllReceiptToDay()) {
            rs += r.getMoney();
        }
        return rs;
    }

    public static int getDoanhThuThisMonth() {
        int rs = 0;
        for (Receipt r : getAllReceiptThisMonth()) {
            rs += r.getMoney();
        }
        return rs;
    }


    public static void main(String[] args) {
        for(Receipt r: getData()){
            System.out.println(r.getCommentList().toString());
        }

    }


}
