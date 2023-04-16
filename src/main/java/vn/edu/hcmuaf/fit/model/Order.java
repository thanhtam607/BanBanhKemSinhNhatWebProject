package vn.edu.hcmuaf.fit.model;

import vn.edu.hcmuaf.fit.bean.User;
import vn.edu.hcmuaf.fit.service.CartService;

import java.text.NumberFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class  Order {
    private String id;
    private User user;
    private String buyDate;
    private double priceTotal;
    private String note;
    private int trangthai;
    private Delivery giaohang;
    List<ItemProductInCart> data;
    public Order() {


    }

    public Order(String id, User user, String buyDate, double priceTotal, String note,
                 int trangthai, List<ItemProductInCart> data  , Delivery gh) {
        this.id = id;
        this.user = user;
        this.buyDate = buyDate;
        this.priceTotal = priceTotal;
        this.note = note;
        this.trangthai = trangthai;
        this.data = data;
        this.giaohang = gh;
}
    public Order( User user, List<ItemProductInCart> data, String buyDate, double priceTotal,
                  String note, Delivery gh ) {
        this.id = null;
        this.user = user;
        this.buyDate = buyDate;
        this.priceTotal = priceTotal;
        this.note = note;
        this.trangthai = 0;
        this.data = data;
        this.giaohang = gh;
    }

    public Order(List<ItemProductInCart> data) {
        this.data = data;
        this.trangthai = 0;
    }


    public List<ItemProductInCart> getData() {
        return data;
    }

    public void setData(List<ItemProductInCart> data) {
        this.data = data;
    }

    public Delivery getGiaohang() {
        return giaohang;
    }

    public void setGiaohang(Delivery giaohang) {
        this.giaohang = giaohang;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(String buyDate) {
        this.buyDate = buyDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", user=" + user +
                ", buyDate='" + buyDate + '\'' +
                ", priceTotal=" + priceTotal +
                ", note='" + note + '\'' +
                ", data=" + data +
                '}';
    }
    public static String formatNum(double num){
        NumberFormat vn = NumberFormat.getInstance();
        String result = vn.format(num);
        return result;
    }
    public double getPriceTotal(){
        return this.priceTotal;
    }
    public void setPriceTotal(double priceToTal){
        this.priceTotal = priceToTal;
    }
}
