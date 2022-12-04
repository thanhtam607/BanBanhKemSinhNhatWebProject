package vn.edu.hcmuaf.fit.model;

import java.util.List;

public class Blog {
    String id;
    String category;
    String cont;
    String img;
    String date;
    String title;
    List<String> listBlog;

    public Blog(String id, String category, String cont, String img, String date, String title, List<String> listBlog) {
        this.id = id;
        this.category = category;
        this.cont = cont;
        this.img = img;
        this.date = date;
        this.title = title;
        this.listBlog = listBlog;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getListBlog() {
        return listBlog;
    }

    public void setListBlog(List<String> listBlog) {
        this.listBlog = listBlog;
    }
}
