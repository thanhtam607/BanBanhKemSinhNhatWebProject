package vn.edu.hcmuaf.fit.service;

import vn.edu.hcmuaf.fit.db.DBConnect;
import vn.edu.hcmuaf.fit.model.Blog;
import vn.edu.hcmuaf.fit.model.Comment;
import vn.edu.hcmuaf.fit.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ProductService {
    public static List<Product> getData(){
        List<Product> list= new LinkedList<Product>();
        Statement statement = DBConnect.getInstall().get();
        Statement stmt = DBConnect.getInstall().get();
        Statement stmt1 = DBConnect.getInstall().get();
        if(statement != null)
            try{
                ResultSet rs =  statement.executeQuery("SELECT sanpham.MaSP ,sanpham.TenSP,loaibanh.TenLB, sanpham.KichThuoc, sanpham.KhoiLuong, sanpham.MoTa, sanpham.NoiDung, sanpham.Gia  from sanpham, loaibanh where sanpham.MalB = loaibanh.MaLB");
                while(rs.next()){
                    ResultSet rsImg = stmt.executeQuery("SELECT anhsp.MaSP,anhsp.Anh from anhsp");
                    List<String> listImg = new LinkedList<String>();
                    List<String> listkt = new LinkedList<>();
                    ResultSet rsCmt = stmt1.executeQuery("SELECT MaSP, TAIKHOAN.TENTK,BinhLuan,NgayBL from Comments, TAIKHOAN where TAIKHOAN.ID = Comments.ID");
                    List<Comment> listCmts = new LinkedList<Comment>();
                    String s1 = rs.getString(1);
                    while (rsImg.next()){
                        String s2 = rsImg.getString(1);
                        if (s1.equals(s2)) {
                            listkt.add(rs.getString(4));
                            listImg.add(rsImg.getString(2));
                        }
                    }
                    while (rsCmt.next()){
                        String s2 = rsCmt.getString(1);
                        if(s1.equals(s2)){
                            listCmts.add(new Comment(rsCmt.getString(1), rsCmt.getString(2), rsCmt.getString(3), rsCmt.getString(4)));
                        }
                    }
                    Product p = new Product(rs.getString(1), rs.getString(2), rs.getString(3), listkt, rs.getString(5), rs.getString(6), rs.getString(7),listImg, rs.getInt(8),listCmts);
                    list.add(p);
                }
            }
            catch (SQLException e){
                throw new RuntimeException(e);
            }
        else{
            System.out.println("Không có sản phẩm");
        }
        return  list;
    }
    public static Product findById( String id){
        List<Product> list= getData();
        for(Product p: list){
            if(p.getId().equals(id)) {
                return p;
            }
        }
        return null;
    }
    public static List<Product> sanPhamBanChay(){
        List<Product> list= new LinkedList<Product>();
        Statement statement = DBConnect.getInstall().get();
        Statement stmt = DBConnect.getInstall().get();
        Statement stmt1 = DBConnect.getInstall().get();
        if(statement != null)
            try{
                ResultSet rs =  statement.executeQuery("SELECT sanpham.MaSP, sanpham.TenSP, sanpham.MaLB, sanpham.KichThuoc, sanpham.KhoiLuong, sanpham.MoTa, sanpham.NoiDung, sanpham.Gia FROM sanpham, cthd WHERE sanpham.MaSP = cthd.MASP GROUP BY MASP ORDER BY COUNT(CTHD.MASP) DESC, cthd.SL DESC LIMIT 5;");
                while(rs.next()){
                    ResultSet rsImg = stmt.executeQuery("SELECT anhsp.MaSP,anhsp.Anh from anhsp");
                    List<String> listImg = new LinkedList<String>();
                    List<String> listkt = new LinkedList<>();
                    ResultSet rsCmt = stmt1.executeQuery("SELECT MaSP, TAIKHOAN.TENTK,BinhLuan,NgayBL from Comments, TAIKHOAN where TAIKHOAN.ID = Comments.ID");
                    List<Comment> listCmts = new LinkedList<Comment>();
                    String s1 = rs.getString(1);
                    while (rsImg.next()){
                        String s2 = rsImg.getString(1);
                        if (s1.equals(s2)) {
                            listImg.add(rsImg.getString(2));
                        }
                    }
                    while (rsCmt.next()){
                        String s2 = rsCmt.getString(1);
                        if(s1.equals(s2)){
                            listCmts.add(new Comment(rsCmt.getString(1), rsCmt.getString(2), rsCmt.getString(3), rsCmt.getString(4)));
                        }
                    }
                    Product p = new Product(rs.getString(1), rs.getString(2), rs.getString(3), listkt, rs.getString(5), rs.getString(6), rs.getString(7),listImg, rs.getInt(8),listCmts);
                    list.add(p);
                }
            }
            catch (SQLException e){
                throw new RuntimeException(e);
            }
        else{
            System.out.println("Không có sản phẩm");
        }
        return  list;
    }
    public static List<Product> sanPhamMoi(){
        List<Product> list= new LinkedList<Product>();
        Statement statement = DBConnect.getInstall().get();
        Statement stmt = DBConnect.getInstall().get();
        Statement stmt1 = DBConnect.getInstall().get();
        if(statement != null)
            try{
                ResultSet rs =  statement.executeQuery("SELECT sanpham.MaSP, sanpham.TenSP, sanpham.MaLB, sanpham.KichThuoc, sanpham.KhoiLuong, sanpham.MoTa, sanpham.NoiDung, sanpham.Gia FROM sanpham ORDER BY MaSP DESC LIMIT 8;");
                while(rs.next()){
                    ResultSet rsImg = stmt.executeQuery("SELECT anhsp.MaSP,anhsp.Anh from anhsp");
                    List<String> listImg = new LinkedList<String>();
                    List<String> listkt = new LinkedList<>();
                    ResultSet rsCmt = stmt1.executeQuery("SELECT MaSP, TAIKHOAN.TENTK,BinhLuan,NgayBL from Comments, TAIKHOAN where TAIKHOAN.ID = Comments.ID");
                    List<Comment> listCmts = new LinkedList<Comment>();
                    String s1 = rs.getString(1);
                    while (rsImg.next()){
                        String s2 = rsImg.getString(1);
                        if (s1.equals(s2)) {
                            listImg.add(rsImg.getString(2));
                        }
                    }
                    while (rsCmt.next()){
                        String s2 = rsCmt.getString(1);
                        if(s1.equals(s2)){
                            listCmts.add(new Comment(rsCmt.getString(1), rsCmt.getString(2), rsCmt.getString(3), rsCmt.getString(4)));
                        }
                    }
                    Product p = new Product(rs.getString(1), rs.getString(2), rs.getString(3), listkt, rs.getString(5), rs.getString(6), rs.getString(7),listImg, rs.getInt(8),listCmts);
                    list.add(p);
                }
            }
            catch (SQLException e){
                throw new RuntimeException(e);
            }
        else{
            System.out.println("Không có sản phẩm");
        }
        return  list;
    }
    public static  void addComment(Comment cmt, String IDUser){
        Statement statement = DBConnect.getInstall().get();

        String sql = "insert into Comments(MaSP, ID,BinhLuan, NgayBL) values('"+ cmt.getMaSP() + "', '"+ IDUser + "', '"+ cmt.getBinhLuan()+ "', '"+ cmt.getDate() +"');";
                try {
        statement.executeUpdate(sql);

        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
    public static Comment getLastComment(String idProduct){
        Product p = findById(idProduct);
        List<Comment> list = p.getComments();
        return list.get(list.size()-1);
    }
    public static List<Product> getSize(String kichthuoc) {
        List<Product> list = getData();
        List<Product> rs = new LinkedList<>();
        for(Product p : list){
            if (p.getKichThuoc().get(0).equals(kichthuoc)) {
                rs.add(p);
            }
        }
        return rs;
    }
    public static void main(String[] args) {
//        List<Product> li = ProductService.getData();
//        for(Product p: li){
//            System.out.print(p.getName()+"\t");
//            System.out.println(p.getComments().size());
//
//        }
        System.out.println(getLastComment("B001").getBinhLuan());
       // addComment(new Comment("B002", "Thanh Tâm","Bánh mềm mịn vô cùng hòa quyện với  phần kem mịn màng, vị ngọt thanh vừa ăn lại có thêm phần tiramisu khá lạ miệng khiến cho người ăn cảm thấy thích thú.","2022/12/8"), "AD02");
    }


}