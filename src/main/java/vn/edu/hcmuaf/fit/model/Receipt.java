package vn.edu.hcmuaf.fit.model;

import java.text.NumberFormat;
import java.util.List;

public class Receipt {
    String id;
    String namecustomer;
    String makh;
    String namecake;
    String export_date;
    String phone;
    String delivery_date;
    String address;
    String note;
    int price;
    int total;
    int money;
    int status;
    int state;
    List<Comment> commentList;
    String TenTK;
    int role;
    String email;
    String createBy;

    public Receipt() {
    }

    public Receipt(String id, String makh, String namecustomer, String namecake, String phone, String export_date, String delivery_date, String address, String note, int price, int total, int status, List<Comment> commentList, String TenTK, int role, String email) {
        this.id = id;
        this.makh = makh;
        this.namecustomer = namecustomer;
        this.namecake = namecake;
        this.phone = phone;
        this.export_date = export_date;
        this.delivery_date = delivery_date;
        this.address = address;
        this.note = note;
        this.price = price;
        this.total = total;
        this.state = status;
        this.commentList = commentList;
        this.TenTK = TenTK;
        this.role = role;
        this.email =  email;
    }

    public Receipt(String id, String makh, String export_date, String note, int money, int state) {
        this.id = id;
        this.makh = makh;
        this.export_date = export_date;
        this.note = note;
        this.money = money;
        this.state = state;
    }
    public Receipt(String id, String makh, String export_date, String note, int money, int state, String dc, String createBy) {
        this.id = id;
        this.makh = makh;
        this.export_date = export_date;
        this.note = note;
        this.money = money;
        this.state = state;
        this.address = dc;
        this.createBy = createBy;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
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

    public String getStatusName() {
        if(this.state == 0){
            return "Chờ Xác Nhận";
        }else if(this.state == 1){
            return "Gói Hàng";
        }else if(this.state == 2){
            return "Đang Giao";
        }else if(this.state == 3){
        return "Giao Thành Công";
        }
        return "Đã hủy";
    }
    public boolean checkState(){
        if(this.state == 0 || this.state == 1) return true;
        return false;
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

    public String getExport_date() {
        return export_date;
    }

    public void setExport_date(String export_date) {

        export_date = export_date;
    }

    public String getDelivery_date() {
        return delivery_date;
    }

    public void setDelivery_date(String delivery_date) {
        delivery_date = delivery_date;
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

    public String getTenTK() {
        return TenTK;
    }

    public void setTenTK(String tenTK) {
        TenTK = tenTK;
    }


    public String getRoleName() {
        if(this.role == 0){
            return "Thường";
        }else if(this.role == 1){
            return "Admin";
        }
        return "Quản Lí";
    }
    public void setRole(int role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static String formatNum(int price){
        NumberFormat vn = NumberFormat.getInstance();
        String result = vn.format(price);
        return result;
    }



}