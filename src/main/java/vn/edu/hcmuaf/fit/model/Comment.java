package vn.edu.hcmuaf.fit.model;

public class Comment {
    String idProduct;
    String khachhang;
    String binhLuan;
    String date;
    int idcmt;
    int status;
    public Comment(String idProduct, String khachhang, String binhLuan, String date, int idcmt, int status){
        this.idProduct = idProduct;
        this.khachhang = khachhang;
        this.binhLuan = binhLuan;
        this.date = date;
        this.idcmt =idcmt;
        this.status = status;
    }
    public Comment(String idProduct, String khachhang, String binhLuan, String date){
        this.idProduct = idProduct;
        this.khachhang = khachhang;
        this.binhLuan = binhLuan;
        this.date = date;
        this.status = 0;
    }

    public String getIdProduct() {
        return idProduct;
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

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    public void setKhachhang(String khachhang) {
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
                "idProduct='" + idProduct + '\'' +
                ", khachhang='" + khachhang + '\'' +
                ", binhLuan='" + binhLuan + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

    public int getStatus() {
        return status;
    }
    public void setStatus(int status){
        this.status = status;
    }
}