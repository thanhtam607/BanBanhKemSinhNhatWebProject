package vn.edu.hcmuaf.fit.service;

import vn.edu.hcmuaf.fit.bean.User;
import vn.edu.hcmuaf.fit.db.DBConnect;
import vn.edu.hcmuaf.fit.model.Discount;
import vn.edu.hcmuaf.fit.model.Log;
import vn.edu.hcmuaf.fit.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LogService {
    static Connection con = DBConnect.getInstall().getConn();
        public static void addLog(Log log){
            try {
                PreparedStatement stm = con.prepareStatement("insert into logs( LEVEL, USER, SRC, CONTENT, CREATE_AT, STATUS) VALUES(?,?,?,?,CURRENT_TIMESTAMP(),0)");
                stm.setInt(1,log.getLevel());
                stm.setString(2, log.getUser());
                stm.setString(3,log.getSrc());
                stm.setString(4, log.getContent());
                stm.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        public static List<Log> getListLog(){
            Statement stm = DBConnect.getInstall().get();
            List<Log> listLog = new ArrayList<>();
            String userN;
            User user;
            if (stm != null)
                try {
                    ResultSet rs = stm.executeQuery("SELECT ID, LEVEL, USER, SRC, CONTENT, CREATE_AT, STATUS  FROM logs;");
                    while (rs.next()) {
                        userN = rs.getString(3);
                        user = UserService.findById(userN);
                        if(user != null){
                            userN = user.getName();
                        }
                        listLog.add(new Log(rs.getInt(1), rs.getInt(2), userN, rs.getString(5), rs.getString(4).substring(1), rs.getString(6), rs.getInt(7)));
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
            }
            return listLog;
        }
}
