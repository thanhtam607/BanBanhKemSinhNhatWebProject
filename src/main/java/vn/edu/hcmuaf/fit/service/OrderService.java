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
                ResultSet rs = statement.executeQuery("SELECT hoadon.MAHD from hoadon ORDER BY MAHD DESC LIMIT 1");
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
    public static void updateTonKhoWhenAdd(Order order){
        Statement stm = DBConnect.getInstall().get();

        if(stm!= null) {
            try {
                for(Map.Entry<String, ItemProductInCart> entry : order.getData().entrySet()){
                    Product p = entry.getValue().getSp();
                    int solgConLai = p.getDetail().getInventory() - entry.getValue().getSoLgMua();
                    String sql = "UPDATE ctsp set ctsp.tonKho = "+solgConLai+" WHERE ctsp.MaSP ='"+p.getId()+"'";
                    stm.executeUpdate(sql);

                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
//        addGiaoHang();
        String a = getLastMaHD().substring(2);
        System.out.println(Integer.parseInt(a)+1);
    }
}
