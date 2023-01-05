package vn.edu.hcmuaf.fit.model;

import java.text.NumberFormat;

public class receipt {
    String id;
    String namecustomer;
    String namecake;
    String Sdate;
    String phone;
    String Edate;
    String address;
    int total;

    public receipt(String id, String namecustomer, String namecake,String phone, String sdate, String edate, String address, int total) {
        this.id = id;
        this.namecustomer = namecustomer;
        this.namecake = namecake;
        this.phone = phone;
        Sdate = sdate;
        Edate = edate;
        this.address = address;
        this.total = total;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNamecustomer() {
        return namecustomer;
    }

    public void setNamecustomer(String namecustomer) {
        this.namecustomer = namecustomer;
    }

    public String getNamecake() {
        return namecake;
    }

    public void setNamecake(String namecake) {
        this.namecake = namecake;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSdate() {
        return Sdate;
    }

    public void setSdate(String sdate) {
        Sdate = sdate;
    }

    public String getEdate() {
        return Edate;
    }

    public void setEdate(String edate) {
        Edate = edate;
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
