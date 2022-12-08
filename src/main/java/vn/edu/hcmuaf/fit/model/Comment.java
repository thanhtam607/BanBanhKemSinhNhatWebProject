package vn.edu.hcmuaf.fit.model;

public class Comment {
    String maSP;
    String khachhang;
    String binhLuan;
    String date;
    public Comment(String maSP, String khachhang, String binhLuan, String date){
        this.maSP = maSP;
        this.khachhang = khachhang;
        this.binhLuan = binhLuan;
        this.date = date;
    }

    public String getMaSP() {
        return maSP;
    }

    public String getkhachhang() {
        return khachhang;
    }

    public String getBinhLuan() {
        return binhLuan;
    }

    public String getDate() {
        return date;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public void setkhachhang(String khachhang) {
        this.khachhang = khachhang;
    }

    public void setBinhLuan(String binhLuan) {
        this.binhLuan = binhLuan;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "maSP='" + maSP + '\'' +
                ", khachhang='" + khachhang + '\'' +
                ", binhLuan='" + binhLuan + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
