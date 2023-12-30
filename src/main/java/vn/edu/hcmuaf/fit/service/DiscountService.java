package vn.edu.hcmuaf.fit.service;

import vn.edu.hcmuaf.fit.db.DBConnect;
import vn.edu.hcmuaf.fit.model.Discount;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DiscountService {
    static Connection con = DBConnect.getInstall().getConn();
    public static List<Discount> getListDiscount(){
        List<Discount> list = new ArrayList<>();
        Statement stm = DBConnect.getInstall().get();
        try{
            ResultSet rs = stm.executeQuery("SELECT id,idProduct, discount, startDate, expiryDate from discounts GROUP BY id, idProduct, discount, startDate, expiryDate HAVING DATEDIFF(CURRENT_DATE, expiryDate) < 0;");
            while (rs.next()){
                list.add(new Discount(rs.getInt(1),rs.getString(2), rs.getDouble(3), rs.getString(4),rs.getString(5)));
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    public static Discount findByIdProduct(String idProduct){
        Discount dis = null;
        try {
            PreparedStatement stm = con.prepareStatement("SELECT id,idProduct, discount, startDate, expiryDate from discounts where idProduct= ? GROUP BY id,idProduct, discount, startDate, expiryDate  HAVING DATEDIFF(CURRENT_DATE, expiryDate) < 0;");
            stm.setString(1,idProduct);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                dis = (new Discount(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getString(4),rs.getString(5)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return dis;
    }
    public static void addDiscount(Discount discount){
        try{
            PreparedStatement stm = con.prepareStatement("INSERT INTO discounts(idProduct, discount, startDate, expiryDate) VALUES(?,?,?,?);");
            stm.setString(1, discount.getIdProduct());
            stm.setDouble(2, discount.getDiscount());
            stm.setString(3, discount.getStartDate());
            stm.setString(4, discount.getExpiryDate());
            stm.executeUpdate();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void addListDiscounts(List<Discount> discounts){
        for (Discount disc: discounts) {
            addDiscount(disc);
        }
    }
    public static void removeDiscount(int id){
        try{
            PreparedStatement stm = con.prepareStatement("delete from discounts where id=?;");
            stm.setInt(1, id);
            stm.executeUpdate();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {

    }
}
