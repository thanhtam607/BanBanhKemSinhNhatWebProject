package vn.edu.hcmuaf.fit.model;

public class Comment {
    String maSP;
    String khachhang;
    String binhLuan;
    String date;
    int idcmt;
    public Comment(String maSP, String khachhang, String binhLuan, String date, int idcmt){
        this.maSP = maSP;
        this.khachhang = khachhang;
        this.binhLuan = binhLuan;
        this.date = date;
        this.idcmt =idcmt;
    }
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

    public int getIdcmt() {
        return idcmt;
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

    public void setIdcmt(int idcmt) {
        this.idcmt = idcmt;
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