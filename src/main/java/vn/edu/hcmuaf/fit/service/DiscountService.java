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
            ResultSet rs = stm.executeQuery("SELECT idProduct, discount, startDate, expiryDate from discount GROUP BY idProduct, startDate, expiryDate HAVING DATEDIFF(CURRENT_DATE, expiryDate) < 0;");
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
            PreparedStatement stm = con.prepareStatement("SELECT idProduct, discount, startDate, expiryDate from discount where idProduct= ? GROUP BY idProduct, startDate, expiryDate HAVING DATEDIFF(CURRENT_DATE, expiryDate) < 0;");
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

    public static void main(String[] args) {
        System.out.println(getListDiscount().size());
    }
}
