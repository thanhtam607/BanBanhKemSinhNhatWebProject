package vn.edu.hcmuaf.fit.service;

import vn.edu.hcmuaf.fit.db.DBConnect;
import vn.edu.hcmuaf.fit.model.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class ProductService {
    public static List<Product> getData(){
        List<Product> list= new LinkedList<Product>();
        Statement statement = DBConnect.getInstall().get();
        if(statement != null)
            try{
                ResultSet rs =  statement.executeQuery("SELECT sanpham.MaSP ,sanpham.TenSP, sanpham.Gia, sanpham.KhoiLuong, sanpham.KichThuoc, anhsp.Anh\n" +
                        "from sanpham, anhsp where sanpham.MaSP = anhsp.MaSP GROUP BY sanpham.MaSP ;\n");

                while(rs.next()){

                    Product p = new Product(  rs.getString(1),rs.getString(2),rs.getString(6), rs.getInt(3));
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
//        ProductService productService = new ProductService();
        List<Product> li = ProductService.getData();
        System.out.println(li.size());
        for(Product p: li){
            System.out.println(p.getName());
        }
    }


}
