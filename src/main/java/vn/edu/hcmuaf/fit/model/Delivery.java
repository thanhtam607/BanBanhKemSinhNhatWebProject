package vn.edu.hcmuaf.fit.model;

public class Delivery {
    private String mahd;
    private String tenKH;
    private String ngayGiao;
    private String diachigiao;
    private String huyen;
    private String xa;
    private String email;
    private String phone;

    public Delivery(){

    }

    public Delivery(String mahd, String ngayGiao,  String xa,String huyen,String diachigiao, String email, String phone, String tenKH) {
        this.mahd = mahd;
        this.ngayGiao = ngayGiao;
        this.xa = xa;
        this.huyen = huyen;
        this.diachigiao = diachigiao;
        this.email = email;
        this.phone = phone;
        this.tenKH = tenKH;
    }


    public String getHuyen() {
        return huyen;
    }

    public String getXa() {
        return xa;
    }

    public void setHuyen(String huyen) {
        this.huyen = huyen;
    }

    public void setXa(String xa) {
        this.xa = xa;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMahd() {
        return mahd;
    }

    public void setMahd(String mahd) {
        this.mahd = mahd;
    }

    public String getNgayGiao() {
        return ngayGiao;
    }

    public void setNgayGiao(String ngayGiao) {
        this.ngayGiao = ngayGiao;
    }

    public String getDiachigiao() {
        return diachigiao;
    }

    public void setDiachigiao(String diachigiao) {
        this.diachigiao = diachigiao;
    }

    @Override
    public String toString() {
        return "Delivery{" +
                "mahd='" + mahd + '\'' +
                ", tenKH='" + tenKH + '\'' +
                ", ngayGiao='" + ngayGiao + '\'' +
                ", diachigiao='" + diachigiao + '\'' +
                ", huyen='" + huyen + '\'' +
                ", xa='" + xa + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
