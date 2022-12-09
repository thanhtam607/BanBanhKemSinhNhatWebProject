package vn.edu.hcmuaf.fit.model;

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
    int solgMua;
    List<Comment> comments;
    List<ProductDetailData> productDetailsList;


    public Product() {
    }

    public Product(String id, String name, List<String> listImg, int price, int solgMua) {
        this.id = id;
        this.name = name;
        this.listImg = listImg;
        this.price = price;
        this.solgMua = solgMua;
    }

    public Product(String id, String name, String loaiBanh, String kichThuoc, String khoiLuong, String moTa, String noiDung, List<String> listImg, int price, List<Comment> comments) {
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

    public int getSolgMua() {
        return solgMua;
    }

    public void setSolgMua(int solgMua) {
        this.solgMua = solgMua;
    }

    public void setLoaiBanh(String loaiBanh) {
        this.loaiBanh = loaiBanh;
    }

    public List<ProductDetailData> getProductDetailsList() {
        return productDetailsList;
    }

    public void setProductDetailsList(List<ProductDetailData> productDetailsList) {
        this.productDetailsList = productDetailsList;
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
    public void quantityUp() {
        this.solgMua++;
    }
    public void quantityUp(int solg) {
        setSolgMua(this.solgMua + solg);
    }
    public double giaSanPhamTrongGioHang(){
        double cost = 0;
        cost = this.getPrice() * this.getSolgMua();
        return cost;
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