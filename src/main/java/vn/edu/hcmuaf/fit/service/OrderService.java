package vn.edu.hcmuaf.fit.service;

import vn.edu.hcmuaf.fit.db.DBConnect;
import vn.edu.hcmuaf.fit.model.ItemProductInCart;
import vn.edu.hcmuaf.fit.model.Order;
import vn.edu.hcmuaf.fit.model.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OrderService {
    public static String getLastMaHD() {
        Statement statement = DBConnect.getInstall().get();
        String result = "";
        if (statement != null)
            try {
                ResultSet rs = statement.executeQuery("SELECT BILLS.ID from BILLS ORDER BY ID DESC LIMIT 1");
                while (rs.next()) {
                    result = rs.getString(1);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        else {
            System.out.println("Không có đơn hàng");
        }
        return result;
    }

    public static String getNewIdOrder(){
        String stt = getLastMaHD().substring(2);
        return "HD" + (Integer.parseInt(stt) + 1);
    }

    public static void addOrder(Order order) {
        Statement stm = DBConnect.getInstall().get();

        String sql = "INSERT INTO BILLS VALUES('" + getNewIdOrder() + "', '" + order.getUser().getId() + "', '" + order.getBuyDate() + "', '" + order.getNote() + "', " + order.getPro_bill() + ", " + order.getFee_bill() + ", 0 , 'aaaaaa')";
        if (stm != null) {
            try {
                stm.executeUpdate(sql);
            } catch (SQLException se) {
                se.printStackTrace();
            }
        } else {
            System.out.println("No find");
        }
        addCTHD(order);
        clearCart(order);

    }

    public static void addCTHD(Order order) {
        Statement stm = DBConnect.getInstall().get();
        String sql = "";

        for (ItemProductInCart item : order.getData()) {
            Product p = ProductService.findById(item.getSp().getId());
            int price = p.getPromotional() != 0 ? p.getPromotional() : p.getPrice();
            sql = "INSERT INTO BILL_DETAIL VALUES('" + order.getId() + "','" + item.getSp().getId() + "'," + item.getSoLgMua() + ",'" + item.getNote() + "'," + price + ");";
            try {
                stm.executeUpdate(sql);
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

    }

    public static void addGiaoHang(Order order) {
        Statement stm = DBConnect.getInstall().get();
        String sql = "";
        if (stm != null) {
            try {
                sql = "INSERT INTO DELIVERY VALUES('" + order.getId() + "','" + order.getGiaohang().getNgayGiao() + "','" + order.getGiaohang().getXa() + "','" + order.getGiaohang().getHuyen() + "',' " + order.getGiaohang().getDiachigiao() + "',' " + order.getGiaohang().getEmail() + "',' " + order.getGiaohang().getPhone() + "',' " + order.getGiaohang().getTenKH() + "');";
                stm.executeUpdate(sql);
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public static void clearCart(Order order) {
        order.getData().clear();
        CartService.removeAllCart(order.getUser().getId());
    }

    public static void updateTonKhoWhenAdd(Order order) {
        Statement stm = DBConnect.getInstall().get();

        if (stm != null) {
            try {
                for (ItemProductInCart item : order.getData()) {
                    Product p = item.getSp();
                    int solgConLai = p.getDetail().getInventory() - item.getSoLgMua();
                    String sql = "UPDATE productDetails set productDetails.inventory = " + solgConLai + " WHERE productDetails.idProduct ='" + p.getId() + "'";
                    stm.executeUpdate(sql);

                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

    }

}