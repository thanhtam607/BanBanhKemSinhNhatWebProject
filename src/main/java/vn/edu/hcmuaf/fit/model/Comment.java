package vn.edu.hcmuaf.fit.model;

public class Comment {
    String maSP;
    String khachHang;
    String binhLuan;
    String date;
    public Comment(String maSP, String khachHang, String binhLuan, String date){
        this.maSP = maSP;
        this.khachHang = khachHang;
        this.binhLuan = binhLuan;
        this.date = date;
    }

    public String getMaSP() {
        return maSP;
    }

    public String getKhachHang() {
        return khachHang;
    }

    public String getBinhLuan() {
        return binhLuan;
    }

    public String getDate() {
        return date;
    }
}
