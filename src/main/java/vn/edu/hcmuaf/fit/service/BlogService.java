package vn.edu.hcmuaf.fit.service;

import vn.edu.hcmuaf.fit.db.DBConnect;
import vn.edu.hcmuaf.fit.model.Blog;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class BlogService {
    public static List<Blog> getData() {
        List<Blog> list = new LinkedList<Blog>();
        Statement statement = DBConnect.getInstall().get();

        if (statement != null)
            try {
                ResultSet rs = statement.executeQuery("SELECT  blog.idblog, blog.imgblog,blog.title, blog.date, blog.content, blog.category, blog.season, blog.status from blog ");
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
            if (b.getCategory().equals(category)) {
                rs.add(b);
            }
        }
        return rs;
    }
    public static List<Blog> ListSeason(String season) {
        List<Blog> list = getData();
        List<Blog> rs = new LinkedList<>();
        for(Blog b : list){
            if (b.getSeason().equals(season)) {
                rs.add(b);
            }
        }
        return rs;
    }
    public static void updateBlog(String MactB, String DeMuc, String ChiTiet, String DeMucS, String ChiTietS){
        Statement statement = DBConnect.getInstall().get();
        String sql = "UPDATE ctblog set  DEMUC='" +DeMuc+ "', CHITIET= '"+ ChiTiet+ "' Where DEMUC='" +DeMucS+ "' and CHITIET= '"+ ChiTietS+ "' and  MACTB= '" + MactB+ "' ;";
        try {
            statement.executeUpdate(sql);

        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
    public static void updateStatus(String id, int status) {
        Statement statement = DBConnect.getInstall().get();
        String sql = "UPDATE blog set  status= " + status + " where blog.idblog = '" + id + "'";
        try {
            statement.executeUpdate(sql);

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
        Blog b1 = BlogService.findById("BL01");
        System.out.println(b1.getCategory());
    }
}
