package vn.edu.hcmuaf.fit.service;

import vn.edu.hcmuaf.fit.bean.User;
import vn.edu.hcmuaf.fit.db.DBConnect;
import vn.edu.hcmuaf.fit.model.Blog;
import vn.edu.hcmuaf.fit.model.CTHD;
import vn.edu.hcmuaf.fit.model.ItemProductInCart;
import vn.edu.hcmuaf.fit.model.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class OrderService {
    public static int getOrderListNumber(){
        Statement statement = DBConnect.getInstall().get();
        int result = 0;
        if (statement != null)
            try {
                ResultSet rs = statement.executeQuery("SELECT count(*) from hoadon");
                while (rs.next()){
                    result = rs.getInt(1);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        else {
            System.out.println("Không có đơn hàng");
        }
        return  result;
    }
    public static List<CTHD> getCTHDList(){
        List<CTHD> cthdList = new ArrayList<CTHD>();
        return  cthdList;
    }
    public static void addOrder(Order order){
        Statement stm = DBConnect.getInstall().get();
        String mahd = "HD" + (getOrderListNumber() + 1);
        order.setId(mahd);
        if(stm!= null) {
            String sql = "insert into hoadon(MAHD, MAKH, GHICHU, THANHTIEN, TRANGTHAI) values ('" + order.getId() + "', '" + order.getUser().getId() + "', '" + order.getNote()  +  "'," + order.totalMoney() +","+order.getTrangthai()+");";
            try {
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
    public static void main(String[] args) {
        System.out.println(getOrderListNumber());
    }
}
