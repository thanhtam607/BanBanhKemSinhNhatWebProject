package vn.edu.hcmuaf.fit.service;

import vn.edu.hcmuaf.fit.db.DBConnect;
import vn.edu.hcmuaf.fit.model.Feedback;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FeedbackService {
    public static void addFeedback(Feedback fb){
        Statement statement = DBConnect.getInstall().get();
        String sql = "insert into danhgia(tenNguoiDung, email, danhgia, ngayDanhGia) values('"+ fb.getName() + "', '"+ fb.getEmail() + "', '"+ fb.getFeedback()+ "', '"+ fb.getDate() +"');";
        try {
            statement.executeUpdate(sql);

        } catch (SQLException se) {
            se.printStackTrace();
        }
        System.out.println(sql);

    }

    public static void main(String[] args) {
//        addFeedback(new Feedback("Thanh Tâm","thanhtamv14717@gmail.com","fjdsk","12/1/2022"));
    }
}
