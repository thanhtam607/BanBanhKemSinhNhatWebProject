package vn.edu.hcmuaf.fit.model;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.NumberFormat;
import java.util.List;

public class Receipt {
    private String id;
    private String makh;
    private String phone;
    private String address;
    private String note;
    private String export_date;
    private String delivery_date;
    private int money;
    private int status;
    private String email;
    private double fee_bill;
    private double pro_bill;
    List<Bill_Detail> billDetailList;
    Delivery delivery;


    public Receipt() {
    }

    public Receipt(String id, String makh, String export_date, String note, int status) {
        this.id = id;
        this.makh = makh;
        this.export_date = export_date;
        this.note = note;
        this.status = status;
    }
    public Receipt(String id, String makh, String export_date, String delivery_date, String note,double pro_bill,double fee_bill, int status, String dc) {
        this.id = id;
        this.makh = makh;
        this.export_date = export_date;
        this.delivery_date = delivery_date;
        this.note = note;
        this.pro_bill = pro_bill;
        this.fee_bill = fee_bill;
        this.status = status;
        this.address = dc;
    }
//    Receipt nafy dùng để mã hoá lúc tạo đơn hàng
    public Receipt(String id, String makh, String export_date, String note, double pro_bill, double fee_bill, List<Bill_Detail> billDetailList, Delivery delivery) {
        this.id = id;
        this.makh = makh;
        this.export_date = export_date;
        this.note = note;
        this.pro_bill = pro_bill;
        this.fee_bill = fee_bill;
        this.billDetailList = billDetailList;
        this.delivery = delivery;
    }

    public String getExport_date() {
        return export_date;
    }

    public void setExport_date(String export_date) {
        this.export_date = export_date;
    }

    public String getDelivery_date() {
        return delivery_date;
    }

    public void setDelivery_date(String delivery_date) {
        this.delivery_date = delivery_date;
    }

    public double getFee_bill() {
        return fee_bill;
    }

    public void setFee_bill(double fee_bill) {
        this.fee_bill = fee_bill;
    }

    public double getPro_bill() {
        return pro_bill;
    }

    public void setPro_bill(double pro_bill) {
        this.pro_bill = pro_bill;
    }


    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getMoney() {
        return (int) (this.fee_bill + this.pro_bill);
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getStatusName() throws UnsupportedEncodingException {

        if(this.status == 0){
            return URLEncoder.encode("Chờ Xác Nhận", "UTF-8");
        }else if(this.status == 1){
            return URLEncoder.encode("Gói Hàng", "UTF-8");
        }else if(this.status == 2){
            return URLEncoder.encode("Đang Giao", "UTF-8");
        }else if(this.status == 3){
            return URLEncoder.encode("Giao Thành Công", "UTF-8");
        }else if(this.status == 5){
            return URLEncoder.encode("Đã bị chỉnh sửa", "UTF-8");
        }
        return URLEncoder.encode("Đã hủy", "UTF-8");
    }
    public boolean checkState(){
        if(this.status == 0 || this.status == 1 || this.status == 5) return true;
        return false;
    }
    public boolean checkEdited(){
        if(this.status == 5) return true;
        return false;
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


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    @Override
    public String toString() {
        return "{" +
                "id='" + id + '\'' +
                ", makh='" + makh + '\'' +
                ", note='" + note + '\'' +
                ", export_date='" + export_date + '\'' +
                ", fee_bill=" + fee_bill +
                ", pro_bill=" + pro_bill +
                ", billDetailList=" + billDetailList +
                ", delivery=" + delivery +
                '}';
    }
}