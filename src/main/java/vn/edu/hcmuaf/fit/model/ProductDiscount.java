package vn.edu.hcmuaf.fit.model;

import java.text.NumberFormat;
import java.util.List;

public class ProductDiscount {
    String id;
     String type;
     String name;
     int price;
     double discount;
     List<String> listImg;

    public ProductDiscount(String id, String type, String name, int price,  double discount, List<String> listImg) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.price = price;
        this.discount = discount;
        this.listImg = listImg;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public List<String> getListImg() {
        return listImg;
    }

    public void setListImg(List<String> listImg) {
        this.listImg = listImg;
    }

    @Override
    public String toString() {
        return "ProductDiscount{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", discount=" + discount +
                ", listImg=" + listImg +
                '}';
    }
    public static String formatNum(int price){
        NumberFormat vn = NumberFormat.getInstance();
        String result = vn.format(price);
        return result;
    }
}
