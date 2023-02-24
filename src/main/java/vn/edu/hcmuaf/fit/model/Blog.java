package vn.edu.hcmuaf.fit.model;

import java.io.Serializable;
import java.util.List;

public class Blog implements Serializable {
    String id;
    String img;
    String title;
    String date;
    String cont;
    String category;
    String season;
    int status;

    public Blog(String id, String img, String title, String date, String cont, String category, String season, int status) {
        this.id = id;
        this.img = img;
        this.title = title;
        this.date = date;
        this.cont = cont;
        this.category = category;
        this.season = season;
        this.status = status;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
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


    public int getStatus() {
        return status;
    }
    public String getStatusString() {
        String stt = "";
        if(this.status == -1){
            stt = "Ẩn tin tức";
        }
        else {
            stt = "Bỏ ẩn tin tức";
        }
        return stt;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
