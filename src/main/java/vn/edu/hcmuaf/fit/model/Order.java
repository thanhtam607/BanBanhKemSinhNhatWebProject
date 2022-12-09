package vn.edu.hcmuaf.fit.model;

import vn.edu.hcmuaf.fit.bean.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class Order {
    private String id;
    private User user;
    private String buyDate;
    private double priceTotal;
    private String note;
    private HashMap<String, ItemProductInCart> data;
    public Order() {

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

//    public int put(ItemProductInCart item) {
//        if (data.containsKey(item.getId())) {
//            data.get(item.getId()).quantityUp();
//        } else {
//            data.put(item.getId(), item);
//        }
//        return data.get(item.getId()).getSolgMua();
//    }

//    public int put(String id, int solgMua) {
//        if (data.containsKey(id)) {
//            data.get(id).quantityUp(solgMua);
//        }
//        return data.get(id).getSolgMua();
//    }

    public boolean remove(String id) {
        return data.remove(id) == null;
    }


    public double totalMoney(Order o) {
        this.priceTotal = 0;
        for (ItemProductInCart p : o.list()) {
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
}
