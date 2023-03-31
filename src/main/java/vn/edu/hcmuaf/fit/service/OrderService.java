package vn.edu.hcmuaf.fit.service;

import vn.edu.hcmuaf.fit.bean.User;
import vn.edu.hcmuaf.fit.db.DBConnect;
import vn.edu.hcmuaf.fit.model.*;

import java.sql.*;
import java.util.*;

public class OrderService {
    public static String getLastMaHD(){
        Statement statement = DBConnect.getInstall().get();
        String result = "";
        if (statement != null)
            try {
                ResultSet rs = statement.executeQuery("SELECT BILLS.ID from BILLS ORDER BY ID DESC LIMIT 1");
                while (rs.next()){
                    result = rs.getString(1);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        else {
            System.out.println("Không có đơn hàng");
        }
        return  result;
    }

    public static void addOrder(Order order){
        Statement stm = DBConnect.getInstall().get();
        String stt = getLastMaHD().substring(2);
        String mahd = "HD" + (Integer.parseInt(stt) + 1);
        order.setId(mahd);
        String makh = order.getUser().getId();
        String sql = "INSERT INTO BILLS VALUES('" + mahd + "', '" + makh + "', '"
                + order.getBuyDate()  + "', '" + order.getNote() + "',"
                +order.getPriceTotal()+ "," + order.getTrangthai()+", null);";
        if(stm!= null) {
            try {
                stm.executeUpdate(sql);
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }else{
            System.out.println("No find");
        }
        addCTHD(order);
        clearCart(order);

    }
    public static void addCTHD(Order order){
        Statement stm = DBConnect.getInstall().get();
        String sql="";
            for (ItemProductInCart item: order.getData()) {

                sql = "INSERT INTO BILL_DETAIL VALUES('" + order.getId() + "','" +item.getSp().getId() + "'," + item.getSoLgMua() + ",'" +item.getNote()+"');";
            try {
                    stm.executeUpdate(sql);
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

    }
    public static void addGiaoHang(Order order){
        Statement stm = DBConnect.getInstall().get();
        String sql = "";
        if(stm!= null) {
            try {
                 sql = "INSERT INTO DELIVERY VALUES('" + order.getId() + "','" + order.getGiaohang().getNgayGiao() + "',' "+ order.getGiaohang().getDiachigiao() + "',' "+ order.getGiaohang().getEmail() + "',' "+ order.getGiaohang().getPhone() + "',' " + order.getGiaohang().getTenKH()+"');";
                stm.executeUpdate(sql);
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
    public static void clearCart(Order order){
        order.getData().clear();
        CartService.removeAllCart(order.getUser().getId());

    }
    public static void updateTonKhoWhenAdd(Order order){
        Statement stm = DBConnect.getInstall().get();

        if(stm!= null) {
            try {
                for(ItemProductInCart item: order.getData()){
                    Product p = item.getSp();
                    int solgConLai = p.getDetail().getInventory() - item.getSoLgMua();
                    String sql = "UPDATE productDetails set productDetails.inventory = "+solgConLai+" WHERE productDetails.idProduct ='"+p.getId()+"'";
                    stm.executeUpdate(sql);

                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

}
