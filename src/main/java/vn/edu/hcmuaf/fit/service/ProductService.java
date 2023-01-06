package vn.edu.hcmuaf.fit.service;

import vn.edu.hcmuaf.fit.db.DBConnect;
import vn.edu.hcmuaf.fit.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ProductService {
    public static List<Product> getData() {
        List<Product> list = new LinkedList<Product>();
        Statement statement = DBConnect.getInstall().get();
        Statement stmt = DBConnect.getInstall().get();
        Statement stmt1 = DBConnect.getInstall().get();
        Statement stmt2 = DBConnect.getInstall().get();
        if (statement != null)
            try {
                ResultSet rs = statement.executeQuery("SELECT distinct sanpham.MaSP ,sanpham.TenSP,loaibanh.TenLB, sanpham.KichThuoc, sanpham.KhoiLuong, sanpham.MoTa, sanpham.NoiDung, sanpham.Gia  from sanpham, loaibanh, giamgia where sanpham.MalB = loaibanh.MaLB");
                while (rs.next()) {
                    ResultSet rsImg = stmt.executeQuery("SELECT anhsp.MaSP,anhsp.Anh from anhsp");
                    List<String> listImg = new LinkedList<String>();
                    ResultSet rsCmt = stmt1.executeQuery("SELECT MaSP, TAIKHOAN.TENTK,BinhLuan,NgayBL from Comments, TAIKHOAN where TAIKHOAN.ID = Comments.ID");

                    List<Comment> listCmts = new LinkedList<Comment>();
                    ResultSet rspd = stmt2.executeQuery("select masp, solg, tonkho, ngaysx, ngayhh from ctsp");
                   ProductDetails detail = new ProductDetails();
                    String s1 = rs.getString(1);

                    while (rsImg.next()){
                        String s2 = rsImg.getString(1);
                        if(s1.equals(s2)){
                            listImg.add(rsImg.getString(2));
                        }
                    }
                    while (rsCmt.next()) {
                        String s2 = rsCmt.getString(1);
                        if (s1.equals(s2)) {
                            listCmts.add(new Comment(rsCmt.getString(1), rsCmt.getString(2), rsCmt.getString(3), rsCmt.getString(4)));
                        }
                    }
                    while (rspd.next()) {
                        String s2 = rspd.getString(1);
                        if (s1.equals(s2)) {
                           detail =new ProductDetails(rspd.getString(1), rspd.getInt(2), rspd.getInt(3), rspd.getString(4), rspd.getString(5));
                        }
                    }
                    Product p = new Product(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), listImg, rs.getInt(8), listCmts, detail);
                    list.add(p);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        else {
            System.out.println("Không có sản phẩm");
        }
        return list;
    }

    public static Product findById(String id) {
        List<Product> list = getData();
        for (Product p : list) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        return null;
    }

    public static List<Product> getHotProduct() {
        List<Product> list = new LinkedList<Product>();
        Statement statement = DBConnect.getInstall().get();
        Statement stmt = DBConnect.getInstall().get();
        Statement stmt1 = DBConnect.getInstall().get();
        Statement stmt2 = DBConnect.getInstall().get();
        if (statement != null)
            try {
                ResultSet rs = statement.executeQuery("SELECT sanpham.MaSP, sanpham.TenSP, sanpham.MaLB, sanpham.KichThuoc, sanpham.KhoiLuong, sanpham.MoTa, sanpham.NoiDung, sanpham.Gia FROM sanpham, cthd WHERE sanpham.MaSP = cthd.MASP GROUP BY MASP ORDER BY COUNT(CTHD.MASP) DESC, cthd.SL DESC;");
                while (rs.next()) {
                    ResultSet rsImg = stmt.executeQuery("SELECT anhsp.MaSP,anhsp.Anh from anhsp");
                    List<String> listImg = new LinkedList<String>();

                    ResultSet rsCmt = stmt1.executeQuery("SELECT MaSP, TAIKHOAN.TENTK,BinhLuan,NgayBL from Comments, TAIKHOAN where TAIKHOAN.ID = Comments.ID");
                    List<Comment> listCmts = new LinkedList<Comment>();
                    ResultSet rspd = stmt2.executeQuery("select masp, solg, tonkho, ngaysx, ngayhh from ctsp");
                    ProductDetails details = new ProductDetails();
                    String s1 = rs.getString(1);
                    while (rsImg.next()) {
                        String s2 = rsImg.getString(1);
                        if (s1.equals(s2)) {
                            listImg.add(rsImg.getString(2));
                        }
                    }
                    while (rsCmt.next()) {
                        String s2 = rsCmt.getString(1);
                        if (s1.equals(s2)) {
                            listCmts.add(new Comment(rsCmt.getString(1), rsCmt.getString(2), rsCmt.getString(3), rsCmt.getString(4)));
                        }
                    }
                    while (rspd.next()) {
                        String s2 = rspd.getString(1);
                        if (s1.equals(s2)) {
                            details = new ProductDetails(rspd.getString(1), rspd.getInt(2), rspd.getInt(3), rspd.getString(4), rspd.getString(5));
                        }
                    }
                    Product p = new Product(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), listImg, rs.getInt(8), listCmts, details);
                    list.add(p);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        else {
            System.out.println("Không có sản phẩm");
        }
        return list;
    }

    public static List<Product> getNewProduct() {
        List<Product> list = new LinkedList<Product>();
        Statement statement = DBConnect.getInstall().get();
        Statement stmt = DBConnect.getInstall().get();
        Statement stmt1 = DBConnect.getInstall().get();
        Statement stmt2 = DBConnect.getInstall().get();
        if (statement != null)
            try {
                ResultSet rs = statement.executeQuery("SELECT sanpham.MaSP, sanpham.TenSP, sanpham.MaLB, sanpham.KichThuoc, sanpham.KhoiLuong, sanpham.MoTa, sanpham.NoiDung, sanpham.Gia FROM sanpham ORDER BY MaSP DESC ;");
                while (rs.next()) {
                    ResultSet rsImg = stmt.executeQuery("SELECT anhsp.MaSP,anhsp.Anh from anhsp");
                    List<String> listImg = new LinkedList<String>();
                    List<String> listkt = new LinkedList<>();
                    ResultSet rsCmt = stmt1.executeQuery("SELECT MaSP, TAIKHOAN.TENTK,BinhLuan,NgayBL from Comments, TAIKHOAN where TAIKHOAN.ID = Comments.ID");
                    List<Comment> listCmts = new LinkedList<Comment>();
                    ResultSet rspd = stmt2.executeQuery("select masp, solg, tonkho, ngaysx, ngayhh from ctsp");
                    ProductDetails details = new ProductDetails();
                    String s1 = rs.getString(1);
                    while (rsImg.next()) {
                        String s2 = rsImg.getString(1);
                        if (s1.equals(s2)) {
                            listImg.add(rsImg.getString(2));
                        }
                    }
                    while (rsCmt.next()) {
                        String s2 = rsCmt.getString(1);
                        if (s1.equals(s2)) {
                            listCmts.add(new Comment(rsCmt.getString(1), rsCmt.getString(2), rsCmt.getString(3), rsCmt.getString(4)));
                        }
                    }
                    while (rspd.next()) {
                        String s2 = rspd.getString(1);
                        if (s1.equals(s2)) {
                            details =new ProductDetails(rspd.getString(1), rspd.getInt(2), rspd.getInt(3), rspd.getString(4), rspd.getString(5));
                        }
                    }
                    Product p = new Product(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), listImg, rs.getInt(8), listCmts, details);
                    list.add(p);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        else {
            System.out.println("Không có sản phẩm");
        }
        return list;
    }
    public static void addComment(Comment cmt, String IDUser) {
        Statement statement = DBConnect.getInstall().get();

        String sql = "insert into Comments(MaSP, ID,BinhLuan, NgayBL) values('" + cmt.getMaSP() + "', '" + IDUser + "', '" + cmt.getBinhLuan() + "', '" + cmt.getDate() + "');";
        try {
            statement.executeUpdate(sql);

        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    public static Comment getLastComment(String idProduct) {
        Product p = findById(idProduct);
        List<Comment> list = p.getComments();
        return list.get(list.size() - 1);
    }

    public static List<Product> findBySize(String kichthuoc) {
        List<Product> list = getData();
        List<Product> rs = new LinkedList<>();
        for (Product p : list) {
            if (p.getKichThuoc().equals(kichthuoc)) {
                rs.add(p);
            }
        }
        return rs;
    }

    public static int getToTalProduct() {
        return getData().size();
    }

    public static List<Product> getPaginationPageOwn(int page, List<Product> data) {

        List<Product> result = new ArrayList<>();
        int begin = (page - 1) * 15;
        int endList = begin + 15;
        if (begin > data.size() - 15) {
            endList = data.size();
        }
        for (int i = begin; i < endList; i++) {
            result.add(data.get(i));
        }
//        0 15 30 45 60 75 90
        return result;
    }

    public static List<LoaiBanh> getListType() throws SQLException {
        List<LoaiBanh> res = new ArrayList<>();
        Statement stm = DBConnect.getInstall().get();
        ResultSet rs = stm.executeQuery("SELECT MALB, TenLB FROM loaibanh;");
        while (rs.next()) {
            res.add(new LoaiBanh(rs.getString(1), rs.getString(2)));
        }
        return res;
    }

    public static List<Product> findByType(String type) {
        List<Product> res = new ArrayList<Product>();
        for (Product p : getData()) {
            if (p.getLoaiBanh().equals(type)) {
                res.add(p);
            }
        }
        return res;
    }

    public static List<Product> findByName(String key) {
        List<Product> res = new ArrayList<Product>();
        List<String> listId = new ArrayList<String>();
        Statement stm = DBConnect.getInstall().get();
        String sql = "select MASP FROM sanpham where TenSP like \"%" + key + "%\"; ";
        try {
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                listId.add(rs.getString(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for (Product p : getData()) {
            if (listId.contains(p.getId())) {
                res.add(p);
            }
        }
        return res;
    }

    public static List<Product> filterByPrice(int pricemin, int pricemax) {
        List<Product> res = new ArrayList<Product>();
        List<String> listId = new ArrayList<String>();
        Statement stm = DBConnect.getInstall().get();
        String sql = "select MASP FROM sanpham where Gia BETWEEN " + pricemin +  " AND  " + pricemax;
        try {
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                listId.add(rs.getString(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for (Product p : getData()) {
            if (listId.contains(p.getId())) {
                res.add(p);
            }
        }
        return res;
    }


    public static void main(String[] args) throws SQLException {
        List<Product> li = ProductService.getHotProduct();
        List<Product> lir = ProductService.getData();

        List<LoaiBanh> lb = getListType();
//        System.out.println(lb);
        for(Product p: lir){
                   System.out.println(p.getListImg());
//          System.out.println(getListType().size());
           }
//        System.out.println(getLastComment("B001").getBinhLuan());
//       System.out.println(getPaginationPage(1).toString());
        // addComment(new Comment("B002", "Thanh Tâm","Bánh mềm mịn vô cùng hòa quyện với  phần kem mịn màng, vị ngọt thanh vừa ăn lại có thêm phần tiramisu khá lạ miệng khiến cho người ăn cảm thấy thích thú.","2022/12/8"), "AD02");
    }

}
