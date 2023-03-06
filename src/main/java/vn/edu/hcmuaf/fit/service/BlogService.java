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
                ResultSet rs = statement.executeQuery("SELECT  blogs.idblog, blogs.imgblog,blogs.title, blogs.date, blogs.content, blogs.category, blogs.season, blogs.status from BLOGS ");
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
        String sql = "UPDATE blogs set  DATE='" +date+ "', CONTENT= '"+ cont+ "', CATEGORY= '"+ category+ "', SEASON= '"+ season+ "' Where  IDBLOG= '" + idblog+ "' ;";
        try {
            statement.executeUpdate(sql);

        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
    public static void updateStatus(String id, int status) {
        Statement statement = DBConnect.getInstall().get();
        String sql = "UPDATE blogs set  status= " + status + " where blogs.idblog = '" + id + "'";
        try {
            statement.executeUpdate(sql);

        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
 public  static void deleteBlog(String idblog){
        Statement stm = DBConnect.getInstall().get();
        String sql = "DELETE FROM blogs WHERE IDBLOG = '" + idblog + "'";
     try {
         stm.executeUpdate(sql);

     } catch (SQLException se) {
         se.printStackTrace();
     }
 }
public static  void updateImgBlog(String img,String imgnew){
    Statement stm = DBConnect.getInstall().get();
    String sql = "UPDATE blogs SET IMGBLOG = '" + imgnew + "' WHERE IMGBLOG = '" + img +"'";
    try {
        stm.executeUpdate(sql);

    } catch (SQLException se) {
        se.printStackTrace();
    }
}
public static void  updateTitle(String idblog, String title ){
    Statement stm = DBConnect.getInstall().get();
    String sql = "UPDATE blogs SET TITLE = '" + title + "' WHERE IDBLOG = '" + idblog +"'";
    try {
        stm.executeUpdate(sql);

    } catch (SQLException se) {
        se.printStackTrace();
    }
}
public  static String getNewIdBlog(){
    String res ="";
    String sql= "SELECT max(idblog) from blogs";
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
        String sql= "SELECT max(idblog) from blogs";
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
//            List<Blog> li = BlogService.getData();
//        for(Blog b: li){
//            System.out.print(b.getCategory()+"\t");
//        }
//        List<Blog> l = BlogService.getDanhMuc("Đời Sống");
//        for(Blog b: l) {
//            System.out.println(b.getId());
//        }
//        Blog b1 = BlogService.findById("BL01");
//        String[] rs = b1.getCont().split("\\n");
//        for (int i = 0; i < rs.length; i++){
//            System.out.print(rs[i] + '\n');
//        }
//        System.out.println(BlogService.getMaxIdBlog());
//        System.out.println(BlogService.listss());
//        String rs = BlogService.listcate().get(0);
//        System.out.println(BlogService.ListCategory(rs));
    }
}
