package vn.edu.hcmuaf.fit.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Product {
    String id;
    String name;
    String loaiBanh;
    String kichThuoc;
    String khoiLuong;
    String moTa;
    String noiDung;
    List<String> listImg;
    int price;
    List<Comment> comments;

    public Product() {
    }

    public Product(String id, String name,String loaiBanh,String kichThuoc, String khoiLuong, String moTa, String noiDung, List<String> listImg, int price, List<Comment> comments) {
        this.id = id;
        this.name = name;
        this.loaiBanh = loaiBanh;
        this.kichThuoc=kichThuoc;
        this.khoiLuong = khoiLuong;
        this.moTa = moTa;
        this.noiDung=noiDung;
        this.listImg = listImg;
        this.price = price;
        this.comments = comments;
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

    public String getKichThuoc() {
        return kichThuoc;
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
}
