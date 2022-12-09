package vn.edu.hcmuaf.fit.model;

public class ProductDetailData {
    private String masp;
    private int soLg;
    private int tonKho;
    private String ngaySX;
    private String ngayHH;

    public ProductDetailData(String masp, int soLg, int tonKho, String ngaySX, String ngayHH) {
        this.masp = masp;
        this.soLg = soLg;
        this.tonKho = tonKho;
        this.ngaySX = ngaySX;
        this.ngayHH = ngayHH;
    }

    public String getMasp() {
        return masp;
    }

    public void setMasp(String masp) {
        this.masp = masp;
    }

    public int getSoLg() {
        return soLg;
    }

    public void setSoLg(int soLg) {
        this.soLg = soLg;
    }

    public int getTonKho() {
        return tonKho;
    }

    public void setTonKho(int tonKho) {
        this.tonKho = tonKho;
    }

    public String getNgaySX() {
        return ngaySX;
    }

    public void setNgaySX(String ngaySX) {
        this.ngaySX = ngaySX;
    }

    public String getNgayHH() {
        return ngayHH;
    }

    public void setNgayHH(String ngayHH) {
        this.ngayHH = ngayHH;
    }

    @Override
    public String toString() {
        return "ProductDetail{" +
                "masp='" + masp + '\'' +
                ", soLg=" + soLg +
                ", tonKho=" + tonKho +
                ", ngaySX='" + ngaySX + '\'' +
                ", ngayHH='" + ngayHH + '\'' +
                '}';
    }


}
