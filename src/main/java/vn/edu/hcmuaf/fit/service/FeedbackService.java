package vn.edu.hcmuaf.fit.service;

import vn.edu.hcmuaf.fit.db.DBConnect;
import vn.edu.hcmuaf.fit.model.Feedback;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FeedbackService {
    public static void addFeedback(Feedback fb){
        Statement statement = DBConnect.getInstall().get();
        String sql = "insert into FEEDBACKS(userName, email, feedback, fbDate) values('"+ fb.getName() + "', '"+ fb.getEmail() + "', '"+ fb.getFeedback()+ "', '"+ fb.getDate() +"');";
        try {
            statement.executeUpdate(sql);

        } catch (SQLException se) {
            se.printStackTrace();
        }
        System.out.println(sql);
    }
    public static List<Feedback> getListFeedback() throws SQLException {
        List<Feedback> list = new ArrayList<>();
        Statement stmt = DBConnect.getInstall().get();
        if(stmt != null){
            try {
                ResultSet rs = stmt.executeQuery("select id, userName, email, feedback, fbDate from FEEDBACKS");
                while(rs.next()){
                    list.add(new Feedback(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
                }
            } catch (SQLException e) {
            throw new RuntimeException(e);
        }}
        return list;
    }
    public static void main(String[] args) throws SQLException {
    }
}
