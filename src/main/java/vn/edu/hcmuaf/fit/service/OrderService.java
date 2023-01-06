package vn.edu.hcmuaf.fit.service;

import vn.edu.hcmuaf.fit.bean.User;
import vn.edu.hcmuaf.fit.db.DBConnect;
import vn.edu.hcmuaf.fit.model.Blog;
import vn.edu.hcmuaf.fit.model.CTHD;
import vn.edu.hcmuaf.fit.model.ItemProductInCart;
import vn.edu.hcmuaf.fit.model.Order;

import java.sql.*;
import java.util.*;

public class OrderService {
    public static int getOrderNumber(){
        Statement statement = DBConnect.getInstall().get();
        int result = 0;
        if (statement != null)
            try {
                ResultSet rs = statement.executeQuery("SELECT count(MAHD) from hoadon");
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
//    public static List<O> getCTHDList(){
//        List<CTHD> cthdList = new ArrayList<CTHD>();
//        return  cthdList;
//    }
    public static void addOrder(Order order){
        Statement stm = DBConnect.getInstall().get();
        String mahd = "HD" + (getOrderNumber() + 1);
        order.setId(mahd);
        String makh = CustomerService.getIDKhach(order.getUser().getId());
        String sql = "INSERT INTO HOADON VALUES('" + mahd + "', '" + makh + "', '"
                + order.getBuyDate()  + "', '" + order.getNote() + "',"
                +order.totalMoney()+ "," + order.getTrangthai()+");";
        if(stm!= null) {
            try {
                stm.executeUpdate(sql);
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }else{
            System.out.println("No find");
        }
    }
    public static void addCTHD(Order order){
        Statement stm = DBConnect.getInstall().get();
        String sql = "";
        if(stm!= null) {
            try {
                for (Map.Entry<String, ItemProductInCart> entry : order.getData().entrySet()) {
                     sql = "INSERT INTO CTHD VALUES('" + order.getId() + "','" + entry.getValue().getSp().getId() + "'," + entry.getValue().getSoLgMua() +");";
                    stm.executeUpdate(sql);
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
    public static void clearCart(Order order){
        order.getData().clear();
        order.setBuyDate(null);
    }
    public static void addGiaoHang(){
        Statement stm = DBConnect.getInstall().get();
        if(stm!= null) {
            try {
                    String sql = "INSERT INTO CTHD VALUES('HD01', 'B001', 2);";
                    stm.executeUpdate(sql);
                    System.out.println("thanh cong");
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
//        addGiaoHang();
    }
}
