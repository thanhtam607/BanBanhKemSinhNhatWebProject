package vn.edu.hcmuaf.fit.model;

import vn.edu.hcmuaf.fit.bean.User;

import java.text.NumberFormat;
import java.util.Collection;
import java.util.HashMap;

public class FavoriteProduct {
    private String id;
    private User user;
    private HashMap<String, ItemProductInCart> data;
    public FavoriteProduct() {

    }
    public FavoriteProduct(HashMap<String, ItemProductInCart> data) {
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


    public boolean remove(String id) {
        return data.remove(id) == null;
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



    @Override
    public String toString() {
        return "FavoriteProduct{" +
                "id='" + id + '\'' +
                ", user=" + user +
                ", data=" + data +
                '}';
    }

    public static String formatNum(double num){
        NumberFormat vn = NumberFormat.getInstance();
        String result = vn.format(num);
        return result;
    }
    public int totalProduct(){

        return this.data.size() ;
    }
}
