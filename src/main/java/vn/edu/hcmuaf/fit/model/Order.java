package vn.edu.hcmuaf.fit.model;

import vn.edu.hcmuaf.fit.bean.User;

import java.text.NumberFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class Order {
    private String id;
    private User user;
    private String buyDate;
    private double priceTotal;
    private String note;
    private int trangthai;
    private Ship giaohang;
    private HashMap<String, ItemProductInCart> data;
    public Order() {


    }

    public Order(String id, User user, String buyDate, double priceTotal, String note,
                 int trangthai, HashMap<String, ItemProductInCart> data, Ship gh) {
        this.id = id;
        this.user = user;
        this.buyDate = buyDate;
        this.priceTotal = priceTotal;
        this.note = note;
        this.trangthai = trangthai;
        this.data = data;
        this.giaohang = gh;
    }

    public Order(HashMap<String, ItemProductInCart> data) {
        this.data = data;
    }

    public ItemProductInCart get(String id) {
        return data.get(id);
    }

    public HashMap<String, ItemProductInCart> getData() {
        return data;
    }

    public void setData(HashMap<String, ItemProductInCart> data) {
        this.data = data;
    }

    public Ship getGiaohang() {
        return giaohang;
    }

    public void setGiaohang(Ship giaohang) {
        this.giaohang = giaohang;
    }

    public boolean remove(String id) {
        return data.remove(id) == null;
    }


    public double totalMoney() {
        this.priceTotal = 0;
        for (ItemProductInCart p : list()) {
            this.priceTotal += p.giaSanPhamTrongGioHang();
        }
        return this.priceTotal;
    }
    public Collection<ItemProductInCart> list(){
        return  data.values();
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
    public int totalProduct(){
        int total = 0;
        for (ItemProductInCart i: list()){
            total+= i.getSoLgMua();
        }
        return total;
    }
}
