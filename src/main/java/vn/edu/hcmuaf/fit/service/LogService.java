package vn.edu.hcmuaf.fit.service;

import vn.edu.hcmuaf.fit.db.DBConnect;
import vn.edu.hcmuaf.fit.model.Discount;
import vn.edu.hcmuaf.fit.model.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
}
