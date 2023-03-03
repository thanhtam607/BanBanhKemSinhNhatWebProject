package vn.edu.hcmuaf.fit.service;

import vn.edu.hcmuaf.fit.db.DBConnect;
import vn.edu.hcmuaf.fit.model.ItemProductInCart;
import vn.edu.hcmuaf.fit.model.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class CartService {
     public static List<ItemProductInCart> findItemCartByIdUser(String customer_id){
         List<ItemProductInCart> list = new ArrayList<>();
         try{
             PreparedStatement ps = DBConnect.getInstall().getConn().prepareStatement("select id, customer_id, idProduct, quantity from cartItems where customer_id =?");
             ps.setString(1,customer_id);
             ResultSet rs = ps.executeQuery();
             while (rs.next()){
                 list.add(new ItemProductInCart(rs.getInt(1), rs.getString(2),ProductService.findById(rs.getString(3)),rs.getInt(4)));
             }
         }catch (SQLException e) {
             throw new RuntimeException(e);
         }
         return list;
     }
    public static ItemProductInCart findItemCart(String customer_id,String idProduct){
        ItemProductInCart item = null;
        try{
            PreparedStatement ps = DBConnect.getInstall().getConn().prepareStatement("select id, customer_id, idProduct, quantity from cartItems where customer_id =? and idProduct= ?");
            ps.setString(1,customer_id);
            ps.setString(2, idProduct);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                item = new ItemProductInCart(rs.getInt(1), rs.getString(2),ProductService.findById(rs.getString(3)),rs.getInt(4));
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return item;
    }
    public static void updateQty(String cusId, String idProduct, int qty){
         String sql = "update cartItems set quantity = ? where customer_id =? and idProduct= ?";
         try{
             PreparedStatement ps = DBConnect.getInstall().getConn().prepareStatement(sql);
             ps.setInt(1, qty);
             ps.setString(2,cusId);
             ps.setString(3,idProduct);
             ps.executeUpdate();
         }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void addItemCart(String cusId, String idProduct, int qty){
        String sql = "insert into cartItems(customer_id,idProduct, quantity) values(?,?,?) ";
        try{
            PreparedStatement ps = DBConnect.getInstall().getConn().prepareStatement(sql);
            ps.setString(1,cusId);
            ps.setString(2,idProduct);
            ps.setInt(3, qty);
            ps.executeUpdate();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
     public static void addToCart(String cusId, String idProduct, int qty){
         ItemProductInCart item = findItemCart(cusId, idProduct);
         if(item != null){
            updateQty(cusId,idProduct,qty+item.getSoLgMua());
        }else{
            addItemCart(cusId,idProduct,qty);
         }
         System.out.println(1234);
     }
     public static void removeToCart(int id){
         String sql = "delete from cartItems where id=? ";
         try{
             PreparedStatement ps = DBConnect.getInstall().getConn().prepareStatement(sql);
             ps.setInt(1,id);
             ps.executeUpdate();
         }catch (SQLException e) {
             throw new RuntimeException(e);
         }
     }
     public static double totalPrice(List<ItemProductInCart> list){
         double res = 0;
         for(ItemProductInCart item : list){
             res +=item.getPrice();
         }
         return res;
     }
    public static String formatNum(double num){
        NumberFormat vn = NumberFormat.getInstance();
        String result = vn.format(num);
        return result;
    }
    public static void main(String[] args) {
//       addToCart("AD03", "B004",4);
    }
}
