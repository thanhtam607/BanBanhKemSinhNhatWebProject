package vn.edu.hcmuaf.fit.model;

import java.text.NumberFormat;
import java.util.List;

public class Product {
    String id;
    String name;
    String loaiBanh;
     List<String> kichThuoc;
    String khoiLuong;
    String moTa;
    String noiDung;
    List<String> listImg;
    int price;
    List<Comment> comments;

    public Product() {
    }


    public Product(String id, String name, String loaiBanh, List<String> kichThuoc, String khoiLuong, String moTa, String noiDung, List<String> listImg, int price, List<Comment> comments) {
        this.id = id;
        this.name = name;
        this.loaiBanh = loaiBanh;
        this.kichThuoc= kichThuoc;
        this.khoiLuong = khoiLuong;
        this.moTa = moTa;
        this.noiDung=noiDung;
        this.listImg = listImg;
        this.price = price;
        this.comments = comments;
    }


    public void setLoaiBanh(String loaiBanh) {
        this.loaiBanh = loaiBanh;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getListImg() {
        return listImg;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getKhoiLuong() {
        return khoiLuong;
    }


    public String getLoaiBanh() {
        return loaiBanh;
    }

    public List<String> getKichThuoc() {
        return kichThuoc;
    }

    public void setKichThuoc(List<String> kichThuoc) {
        this.kichThuoc = kichThuoc;
    }

    public String getMoTa() {
        return moTa;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public List<Comment> getComments() {
        return comments;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", loaiBanh='" + loaiBanh + '\'' +
                ", kichThuoc='" + kichThuoc + '\'' +
                ", khoiLuong='" + khoiLuong + '\'' +
                ", moTa='" + moTa + '\'' +
                ", noiDung='" + noiDung + '\'' +
                ", listImg=" + listImg +
                ", price=" + price +
                ", comments=" + comments +
                '}';
    }

    public static String formatNum(int price){
        NumberFormat vn = NumberFormat.getInstance();
        String result = vn.format(price);
        return result;
    }

    public static void main(String[] args) {
        System.out.println(formatNum(1234567));
    }
}