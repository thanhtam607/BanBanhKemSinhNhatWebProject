package vn.edu.hcmuaf.fit.service;

import vn.edu.hcmuaf.fit.db.DBConnect;
import vn.edu.hcmuaf.fit.model.Discount;
import vn.edu.hcmuaf.fit.model.Product;
import vn.edu.hcmuaf.fit.model.ProductDetail;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DiscountService {
    static Connection con = DBConnect.getInstall().getConn();
    public static List<Discount> getListDiscount(){
        List<Discount> list = new ArrayList<>();
        Statement stm = DBConnect.getInstall().get();
        try{
            ResultSet rs = stm.executeQuery("SELECT idProduct, discount, startDate, expiryDate from discounts GROUP BY idProduct, startDate, expiryDate HAVING DATEDIFF(CURRENT_DATE, expiryDate) < 0;");
            while (rs.next()){
                list.add(new Discount(rs.getString(1), rs.getDouble(2), rs.getString(3),rs.getString(4)));
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    public static Discount findByIdProduct(String idProduct){
        Discount dis = null;
        try {
            PreparedStatement stm = con.prepareStatement("SELECT idProduct, discount, startDate, expiryDate from discounts where idProduct= ? GROUP BY idProduct, startDate, expiryDate HAVING DATEDIFF(CURRENT_DATE, expiryDate) < 0;");
            stm.setString(1,idProduct);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                dis = (new Discount(rs.getString(1), rs.getDouble(2), rs.getString(3),rs.getString(4)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return dis;
    }
    public List<Discount> createListDiscount(List<String> listIdProducts, double discount, String startDate, String endDate){
        List<Discount> res = new ArrayList<>();
        for(String id : listIdProducts){
            res.add(new Discount(id, discount, startDate, endDate));
        }
        return res;
    }
    public static void addDiscount(Discount discount){
        try{
            PreparedStatement stm = con.prepareStatement("INSERT INTO discounts(idProduct, discount, startDate, expiryDate) VALUES(?,?,?,?)");
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

    public static void main(String[] args) {

    }
}
