package vn.edu.hcmuaf.fit.model;

import java.io.Serializable;
import java.util.List;

public class Blog implements Serializable {
    String id;
    String category;
    String cont;
    String img;
    String date;
    List<String> listdanhmuc;
    List<String> listchude;
    List<String> listdemuc;
    List<String> listchitiet;

    public Blog(String id, String category,   String cont, String img, String date, List<String> listdanhmuc,  List<String> listchude, List<String> listdemuc, List<String> listchitiet) {
        this.id = id;
        this.category = category;
        this.cont = cont;
        this.img = img;
        this.date = date;
        this.listdanhmuc = listdanhmuc;
        this.listchude = listchude;
        this.listdemuc = listdemuc;
        this.listchitiet = listchitiet;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCont() {
        return cont;
    }

    public void setCont(String cont) {
        this.cont = cont;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<String> getListdanhmuc() {
        return listdanhmuc;
    }

    public void setListdanhmuc(List<String> listdanhmuc) {
        this.listdanhmuc = listdanhmuc;
    }

    public List<String> getListchude() {
        return listchude;
    }

    public void setListchude(List<String> listchude) {
        this.listchude = listchude;
    }

    public List<String> getListdemuc() {
        return listdemuc;
    }

    public void setListdemuc(List<String> listBlog) {
        this.listdemuc = listdemuc;
    }

    public List<String> getListchitiet() {
        return listchitiet;
    }

    public void setListchitiet(List<String> listchitiet) {
        this.listchitiet = listchitiet;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id='" + id + '\'' +
                ", category='" + category + '\'' +
                ", cont='" + cont + '\'' +
                ", img='" + img + '\'' +
                ", date='" + date + '\'' +
                ", listdanhmuc=" + listdanhmuc +
                ", listchude=" + listchude +
                ", listdemuc=" + listdemuc +
                ", listchitiet=" + listchitiet +
                '}';
    }
}
