package vn.edu.hcmuaf.fit.model;

import java.text.NumberFormat;
import java.util.List;

public class CTHD {
    private String mahd;
    private String masp;
    private String tensp;
    private int solg;
    private List<String> anhsp;
    private int price;


    public CTHD(String mahd, String masp, String tensp, int solg, List<String> anhsp, int price) {
        this.mahd = mahd;
        this.masp = masp;
        this.tensp = tensp;
        this.solg = solg;
        this.anhsp = anhsp;
        this.price = price;
    }
    public int getToTalPrice(){
        int res = 0;
        res = this.price*this.solg;
        return res;
    }
    public double getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public List<String> getAnhsp() {
        return anhsp;
    }

    public void setAnhsp(List<String> anhsp) {
        this.anhsp = anhsp;
    }



    public String getMahd() {
        return mahd;
    }

    public void setMahd(String mahd) {
        this.mahd = mahd;
    }

    public String getMasp() {
        return masp;
    }

    public void setMasp(String masp) {
        this.masp = masp;
    }

    public int getSolg() {
        return solg;
    }

    public void setSolg(int solg) {
        this.solg = solg;
    }
    public static String formatNum(int price){
        NumberFormat vn = NumberFormat.getInstance();
        String result = vn.format(price);
        return result;
    }
    @Override
    public String toString() {
        return "CTHD{" +
                "mahd='" + mahd + '\'' +
                ", masp='" + masp + '\'' +
                ", tensp='" + tensp + '\'' +
                ", solg=" + solg +
                ", anhsp=" + anhsp +
                ", price=" + price +
                '}';
    }
}
