package vn.edu.hcmuaf.fit.service;

import vn.edu.hcmuaf.fit.bean.User;
import vn.edu.hcmuaf.fit.db.DBConnect;
import vn.edu.hcmuaf.fit.model.CTHD;
import vn.edu.hcmuaf.fit.model.ItemProductInCart;
import vn.edu.hcmuaf.fit.model.Order;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderService {
    public static List<Order> getOrderList(){
        List<Order> orderList = new ArrayList<Order>();
        return  orderList;
    }
    public static List<CTHD> getCTHDList(){
        List<CTHD> cthdList = new ArrayList<CTHD>();
        return  cthdList;
    }
    public static void addOrder(Order order){
        Statement stm = DBConnect.getInstall().get();
        List<Order> listOrder = getOrderList();
        String mahd = "HD" + (listOrder.size() + 1);
        order.setId(mahd);
        if(stm!= null) {
            try {
                String sql = "insert into hoadon values ('" + mahd + "', '" + order.getUser().getId() + "', '" + order.getBuyDate()  + "', '" + order.getNote() + "'," + order.totalMoney() +","+0+");";
                stm.executeUpdate(sql);
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
    public static void addCTHD(Order order){
        Statement stm = DBConnect.getInstall().get();
        List<CTHD> cthdList = getCTHDList();
        if(stm!= null) {
            try {
                for(ItemProductInCart inCart: order.list()){
                    String sql = "insert into cthd values ('" + order.getId() + "', '" + inCart.getMasp() + "'," + inCart.getSoLgMua() +");";
                    stm.executeUpdate(sql);
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
//    public static void addGiaoHang(Order order){
//        Statement stm = DBConnect.getInstall().get();
//        List<CTHD> cthdList = getCTHDList();
//        if(stm!= null) {
//            try {
//                for(ItemProductInCart inCart: order.list()){
//                    String sql = "insert into cthd values ('" + order.getId() + "', '" + inCart.getMasp() + "'," + inCart.getSoLgMua() +");";
//                    stm.executeUpdate(sql);
//                }
//            } catch (SQLException se) {
//                se.printStackTrace();
//            }
//        }
//    }

}
