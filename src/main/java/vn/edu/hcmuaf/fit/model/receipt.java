package vn.edu.hcmuaf.fit.model;

import java.text.NumberFormat;

public class receipt {
    String id;
    String date;
    String name;
    String address;
    int total;

    public receipt(String id, String date, String name, String address, int total) {
        this.id = id;
        this.date = date;
        this.name = name;
        this.address = address;
        this.total = total;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
    public static String formatNum(int price){
        NumberFormat vn = NumberFormat.getInstance();
        String result = vn.format(price);
        return result;
    }
}
