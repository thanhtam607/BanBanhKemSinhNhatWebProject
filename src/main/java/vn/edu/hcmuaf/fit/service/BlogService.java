package vn.edu.hcmuaf.fit.service;

import vn.edu.hcmuaf.fit.db.DBConnect;
import vn.edu.hcmuaf.fit.model.Blog;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
public class BlogService {
    public static List<Blog> getData() {
        List<Blog> list = new LinkedList<Blog>();
        Statement statement = DBConnect.getInstall().get();
        if (statement != null)
            try {
                ResultSet rs = statement.executeQuery("SELECT  BLOGS.ID, BLOGS.IMG,BLOGS.TITLE, BLOGS.DATE, BLOGS.CONTENT, BLOGS.CATEGORY, BLOGS.SEASON, BLOGS.STATUS from BLOGS ");
                while(rs.next()) {
                    Blog b = new Blog(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8));
                    list.add(b);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        else {
            System.out.println("Không có tin tức");
        }
        return list;
    }
    public static Blog findById(String id){
        List<Blog> list = getData();
        for(Blog b : list){
            if(b.getId().equals(id)){
                return b;
            }
        }
        return null;
    }
    public static List<Blog> ListCategory(String category) {
        List<Blog> list = getData();
        List<Blog> rs = new LinkedList<>();
        for(Blog b : list){
            if (category.equals(b.getCategory())) {
                rs.add(b);
            }
        }
        return rs;
    }
    public static List<Blog> ListSeason(String season) {
        List<Blog> list = getData();
        List<Blog> rs = new LinkedList<>();
        for(Blog b : list){
            if (season.equals(b.getSeason())) {
                rs.add(b);
            }
        }
        return rs;
    }
    public static void updateBlog(String idblog, String date, String cont, String category, String season){
        Statement statement = DBConnect.getInstall().get();
        String sql = "UPDATE BLOGS set  DATE='" +date+ "', CONTENT= '"+ cont+ "', CATEGORY= '"+ category+ "', SEASON= '"+ season+ "' Where  ID= '" + idblog+ "' ;";
        try {
            statement.executeUpdate(sql);
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
    public static void updateStatus(String id, int status) {
        Statement statement = DBConnect.getInstall().get();
        String sql = "UPDATE BLOGS set  STATUS= " + status + " where BLOGS.id = '" + id + "'";
        try {
            statement.executeUpdate(sql);
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
 public  static void deleteBlog(String idblog){
        Statement stm = DBConnect.getInstall().get();
        String sql = "DELETE FROM BLOGS WHERE ID = '" + idblog + "'";
     try {
         stm.executeUpdate(sql);
     } catch (SQLException se) {
         se.printStackTrace();
     }
 }
public static  void updateImgBlog(String img,String imgnew){
    Statement stm = DBConnect.getInstall().get();
    String sql = "UPDATE BLOGS SET IMG = '" + imgnew + "' WHERE IMG = '" + img +"'";
    try {
        stm.executeUpdate(sql);

    } catch (SQLException se) {
        se.printStackTrace();
    }
}
public static void  updateTitle(String idblog, String title ){
    Statement stm = DBConnect.getInstall().get();
    String sql = "UPDATE BLOGS SET TITLE = '" + title + "' WHERE ID = '" + idblog +"'";
    try {
        stm.executeUpdate(sql);
    } catch (SQLException se) {
        se.printStackTrace();
    }
}
public  static String getNewIdBlog(){
    String res ="";
    String sql= "SELECT max(ID) from blogs";
    Statement statement = DBConnect.getInstall().get();
    try {
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            res = rs.getString(1);
        }
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    String s = "";
    int n = Integer.parseInt(res.substring(2));
    if(n < 9) {
       s  = "BL0" + (n + 1);
    } else {
        s = "BL" + (n + 1);
    }
    return s;
}
    public  static String getMaxIdBlog(){
        String res ="";
        String sql= "SELECT max(ID) from blogs";
        Statement statement = DBConnect.getInstall().get();
        try {
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                res = rs.getString(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
      return res;
    }
public static List<String> listcate(){
        List<String> rs = new ArrayList<>();
        List<Blog> listb =  BlogService.getData();
        for(Blog b : listb){
            if (!rs.contains(b.getCategory())){
                rs.add(b.getCategory());
            }
        }
        return rs;
}
    public static List<String> listss(){
        List<String> rs = new ArrayList<>();
        List<Blog> listb =  BlogService.getData();
        for(Blog b : listb){
            if (!rs.contains(b.getSeason())){
                rs.add(b.getSeason());
            }
        }
        return rs;
    }
public static void addBlog(Blog b){
        Statement stm = DBConnect.getInstall().get();
        String sql = "INSERT INTO BLOGS VALUES('" + b.getId() + "', '" + b.getImg() + "', '" + b.getTitle() + "','" + b.getDate() + "','" + b.getCont() + "','" + b.getCategory() + "','" + b.getSeason() + "',0);";
    try {
        stm.executeUpdate(sql);
    } catch (SQLException se) {
        se.printStackTrace();
    }
}
    public static void main(String[] args) {
//
    }
}
