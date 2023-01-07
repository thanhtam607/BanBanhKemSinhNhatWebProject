package vn.edu.hcmuaf.fit.service;

import vn.edu.hcmuaf.fit.db.DBConnect;
import vn.edu.hcmuaf.fit.model.*;

import javax.sound.midi.Soundbank;
import java.sql.*;
import java.util.*;

public class ReceiptService {
    public static List<Receipt> getData() {
        List<Receipt> list = new LinkedList<Receipt>();
        Statement statement = DBConnect.getInstall().get();
        if (statement != null)
            try {
                ResultSet rs = statement.executeQuery("select hoadon.mahd, khachhang.TENKH, sanpham.TenSP, khachhang.sdt, hoadon.NGAYLAPHD, giaohang.NGAYGIAO, khachhang.DIACHI, hoadon.ghichu, sanpham.gia, hoadon.THANHTIEN,  hoadon.TRANGTHAI, khachhang.makh" +
                        "                        from sanpham, hoadon, khachhang, cthd, giaohang" +
                        "                        where hoadon.mahd = cthd.MAHD and cthd.MASP = sanpham.MaSP and giaohang.MAHD = hoadon.MAHD and khachhang.MAKH = hoadon.MAKH group by hoadon.MAHD ORDER BY hoadon.NGAYLAPHD desc;");
                while (rs.next()) {
                    Receipt rc = new Receipt(rs.getString(1), rs.getString(12), rs.getString(2), rs.getString(3),rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7),rs.getString(8), rs.getInt(9), rs.getInt(10), rs.getInt(11));
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
        if (statement != null)
            try {
                ResultSet rs = statement.executeQuery("SELECT MAHD, MAKH, NGAYLAPHD, GHICHU, THANHTIEN, TRANGTHAI FROM hoadon ORDER BY hoadon.MAHD DESC");
                while (rs.next()) {
                    Receipt rc = new Receipt(rs.getString(1), rs.getString(2),
                            rs.getString(3),rs.getString(4), rs.getInt(5), rs.getInt(6));
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
    public static List<CTHD> getListCTHD() {
        List<CTHD> list = new LinkedList<CTHD>();
        Statement statement = DBConnect.getInstall().get();
        Statement stmt = DBConnect.getInstall().get();
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

    public static List<Receipt> getcthd(String mahd) {
        List<Receipt> list = getData();
        List<Receipt> rs = new LinkedList<>();
        for(Receipt rc : list){
            if (rc.getId().equals(mahd)) {
                rs.add(rc);
            }
        }
        return rs;
    }
    public static List<Receipt> getctkh(String makh) {
        List<Receipt> list = getData();
        List<Receipt> rs = new LinkedList<>();
        for(Receipt rc : list){
            if (rc.getMakh().equals(makh)) {
                rs.add(rc);
            }
        }
        return rs;
    }
    public static List<CTHD> getcthdUser(String mahd) {
        List<CTHD> list = getListCTHD();
        List<CTHD> rs = new LinkedList<>();
        for(CTHD rc : list){
            if (rc.getMahd().equals(mahd)) {
                rs.add(rc);
            }
        }

        return rs;
    }

    public static List<Receipt> getReceiptByMakh(String makh){
        List<Receipt> list = getAllReceipt();
        List<Receipt> rs = new LinkedList<>();
        for(Receipt rc : list){
            if (rc.getMakh().equals(makh)) {
                rs.add(rc);
            }
        }

        return rs;
    }
    public static List<Ship> getListGiaoHang() {
        List<Ship> list = new LinkedList<Ship>();
        Statement statement = DBConnect.getInstall().get();
        Statement stmt = DBConnect.getInstall().get();
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
    public static String getDiaChiGiaoHang(String mahd) {
        List<Ship> list = getListGiaoHang();
        for(Ship s: list){
            if(s.getMahd().equals(mahd)) return s.getDiachigiao();
        }
        return null;
    }

    public static void cancelOrder(String mahd) {
        Statement statement = DBConnect.getInstall().get();
        Statement stmt = DBConnect.getInstall().get();
        String sql = "DELETE from cthd WHERE MAHD = '"+mahd+"'";
        String sql2 = "DELETE from giaohang WHERE MAHD = '"+mahd+"'";
        String sql3 = "DELETE from hoadon WHERE MAHD = '"+mahd+"'";
        if(statement!=null){
            try {
                statement.executeUpdate(sql);
                statement.executeUpdate(sql2);
                statement.executeUpdate(sql3);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else{
            System.out.println("not found");
        }


    }
    public static void main(String[] args) {
        cancelOrder("HD02");
////        List<CTHD> ls = getListCTHD();
//        List<CTHD> ls = getcthdUser("HD84");
////        List<Receipt> lr = getAllReceipt();
//        for(Ship r : ls){
////            List<Receipt> rc = getcthd(r.getId());
////            System.out.println(r.toString());
//            System.out.println(r.toString()+"\n");
//        }
    }
}
