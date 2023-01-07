package vn.edu.hcmuaf.fit.model;

import java.text.NumberFormat;
import java.util.List;

public class Receipt {
    String id;
    String namecustomer;
    String makh;
    String namecake;
    String Sdate;
    String phone;
    String Edate;
    String address;
    String note;
    int price;
    int total;
    int money;
    int status;
    int state;
    List<Comment> commentList;
    public Receipt(String id, String makh, String namecustomer, String namecake,String phone, String sdate, String edate, String address,  String note, int price, int total, int status, List<Comment> commentList) {
        this.id = id;
        this.makh = makh;
        this.namecustomer = namecustomer;
        this.namecake = namecake;
        this.phone = phone;
        this.Sdate = sdate;
        this.Edate = edate;
        this.address = address;
        this.note = note;
        this.price = price;
        this.total = total;
        this.state = state;
        this.commentList = commentList;
    }

    public Receipt(String id, String makh, String sdate, String note, int money, int state) {
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

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
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
    public int getStateInt() {
        return this.state;
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


    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public static String formatNum(int price){
        NumberFormat vn = NumberFormat.getInstance();
        String result = vn.format(price);
        return result;
    }
    public boolean checkState(){
        if(this.state == 0 || this.state == 1) return true;
        return false;
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