package vn.edu.hcmuaf.fit.model;

import java.text.NumberFormat;

public class Bill_Detail {
    private String mahd;
    private String masp;
    private String namePro;
    private int solg;
    private int price;
    private String note;


    public Bill_Detail(String mahd, String masp, String namePro, int price, int solg, String note) {
        this.mahd = mahd;
        this.masp = masp;
        this.solg = solg;
        this.note = note;
        this.price = price;
        this.namePro = namePro;

    }
    public Bill_Detail(String mahd, String masp,int solg, String note) {
        this.mahd = mahd;
        this.masp = masp;
        this.solg = solg;
        this.note = note;

    }
    public Bill_Detail(String mahd, String masp,int solg, String note, int price) {
        this.mahd = mahd;
        this.masp = masp;
        this.solg = solg;
        this.note = note;
        this.price = price;
    }

    public int getToTalPrice(){
        int res = 0;
        res = this.price *this.solg;
        return res;
    }

    public String getNamePro() {
        return namePro;
    }

    public void setNamePro(String namePro) {
        this.namePro = namePro;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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
        return "Bill_Detail{" +
                "mahd='" + mahd + '\'' +
                ", masp='" + masp + '\'' +
                ", solg=" + solg +
                ", price=" + price +
                ", note='" + note + '\'' +
                "\n"+
                '}';
    }
}
