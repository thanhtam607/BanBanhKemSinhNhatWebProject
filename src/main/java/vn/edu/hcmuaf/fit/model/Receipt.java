package vn.edu.hcmuaf.fit.model;

import java.text.NumberFormat;

public class Receipt {
    String id;
    String namecustomer;
    String makh;
    String namecake;
    String Sdate;
    String phone;
    String note;
    String Edate;
    String address;
    int total;
    double money;
    int state;

    public Receipt(String id, String namecustomer, String namecake, String phone, String sdate, String edate, String address, int total) {
        this.id = id;
        this.namecustomer = namecustomer;
        this.namecake = namecake;
        this.phone = phone;
        Sdate = sdate;
        Edate = edate;
        this.address = address;
        this.total = total;
    }

    public Receipt(String id, String makh, String sdate, String note, double money, int state) {
        this.id = id;
        this.makh = makh;
        Sdate = sdate;
        this.note = note;
        this.money = money;
        this.state = state;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getState() {
        if(this.state == 0){
            return "Chờ Xác Nhận";
        }else if(this.state == 1){
            return "Chuẩn Bị Hàng";
        }else if(this.state == 2){
            return "Đang Giao Hàng";
        }
        return "Giao Thành Công";
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMakh() {
        return makh;
    }

    public void setMakh(String makh) {
        this.makh = makh;
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

    @Override
    public String toString() {
        return "receipt{" +
                "id='" + id + '\'' +
                ", idcustomer='" + makh + '\'' +
                ", Sdate='" + Sdate + '\'' +
                ", ghichu='" + note + '\'' +
                ", thanhtien='" + money + '\'' +
                ", trangthai=" + getState() +
                '}';
    }
}