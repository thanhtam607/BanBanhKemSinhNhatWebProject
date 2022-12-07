package vn.edu.hcmuaf.fit.service;

import vn.edu.hcmuaf.fit.db.DBConnect;
import vn.edu.hcmuaf.fit.model.Blog;
import vn.edu.hcmuaf.fit.model.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class BlogService {
    public static List<Blog> getData() {
        List<Blog> list = new LinkedList<Blog>();
        Statement statement = DBConnect.getInstall().get();
        Statement stmt = DBConnect.getInstall().get();

        if (statement != null)
            try {
                ResultSet rs = statement.executeQuery("SELECT  blog.mablog, blog.tieude,blog.mota, blog.anhblog, blog.ngaydang, blog.danhmuc, blog.chude from blog ");
                while (rs.next()) {
                    ResultSet rsdetails = stmt.executeQuery("SELECT ctblog.mablog, ctblog.demuc, ctblog.chitiet from ctblog");
                    List<String> listdemuc = new LinkedList<String>();
                    List<String> listchitiet = new LinkedList<String>();
                    List<String> listdanhmuc = new LinkedList<String>();
                    List<String> listchude = new LinkedList<String>();
                    while (rsdetails.next()) {
                        String s1 = rs.getString(1);
                        String s2 = rsdetails.getString(1);
                        if (s1.equals(s2)) {
                            listchude.add(rs.getString(7));
                            listdanhmuc.add(rs.getString(6));
                            listdemuc.add(rsdetails.getString(2));
                            listchitiet.add(rsdetails.getString(3));

                        }
                    }

                    Blog b = new Blog(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), listdanhmuc, listchude,  listdemuc, listchitiet);
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
    public static List<Blog> FindByDanhMuc(String danhmuc) {
        List<Blog> list = getData();
        List<Blog> rs = new LinkedList<>();
        for(Blog b : list){
                if (b.getListdanhmuc().get(0).equals(danhmuc)) {
                    rs.add(b);
                }
        }
        return rs;
    }


    public static void main(String[] args) {
        List<Blog> li = BlogService.getData();
//        for(Blog b: li){
//            System.out.print(b.getCategory()+"\t");
//            System.out.println(b.getTitle());
//
//        }
        List<Blog> l = BlogService.FindByDanhMuc("Đời sống");
        for(Blog b: l) {
            System.out.println(b.getId());
        }
    }
}
