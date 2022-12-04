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
                ResultSet rs = statement.executeQuery("SELECT blog.mablog, blog.tieude, blog.mota, blog.anhblog, blog.ngaydang, blog.chude from blog");
                while (rs.next()) {
                    ResultSet rsImg = stmt.executeQuery("SELECT blog.mablog, blog.anhblog from blog");
                    List<String> listBlog = new LinkedList<String>();
                    while (rsImg.next()) {
                        String s1 = rs.getString(1);
                        String s2 = rsImg.getString(1);
                        if (s1.equals(s2)) {
                            listBlog.add(rsImg.getString(2));
                        }
                    }
                    Blog b = new Blog(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), listBlog);
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


    public static void main(String[] args) {
        List<Blog> li = BlogService.getData();
        for(Blog b: li){
            System.out.print(b.getCategory()+"\t");
            System.out.println(b.getTitle());

        }
    }
}
