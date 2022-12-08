package vn.edu.hcmuaf.fit.service;

import vn.edu.hcmuaf.fit.db.DBConnect;
import vn.edu.hcmuaf.fit.model.Comment;
import vn.edu.hcmuaf.fit.model.Product;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

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

                    ResultSet rsCmt = stmt1.executeQuery("SELECT MaSP, KHACHHANG.TENKH,BinhLuan,NgayBL from Comments, KHACHHANG where KHACHHANG.MAKH = Comments.MAKH");
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

                   Product p = new Product(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7),listImg, rs.getInt(8),listCmts);
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

    public static void main(String[] args) {
        List<Product> li = ProductService.getData();

        for(Product p: li){
            System.out.print(p.getName()+"\t");
            System.out.println(p.getComments().size());

        }
    }

}
