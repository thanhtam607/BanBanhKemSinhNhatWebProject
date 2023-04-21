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
                        if(rs.getInt(7)!= -1) {
                            listLog.add(new Log(rs.getInt(1), rs.getInt(2), userN, rs.getString(5), rs.getString(4).substring(1), rs.getString(6), rs.getInt(7)));
                        }
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
            }
            return listLog;
        }
        public static List<Log> findByUserName(String userName, List<Log> list){
            List<Log> res = new ArrayList<>();
          for(Log log : list){
              if(userName.equals(log.getUser()))
                  res.add(log);
          }
            return res;
        }
    public static String getDate(String datetime){
        String[] date = datetime.split(" ");
        return  date[0];
    }
        public static List<Log> findByDate(String date, List<Log> list){
            List<Log> res = new ArrayList<>();
            String[] d = getDate(date).split("-");
            String userN;
            User user;
            try {
                PreparedStatement stm = con.prepareStatement("SELECT ID, LEVEL, USER, SRC, CONTENT, CREATE_AT, STATUS  FROM logs WHERE year(CREATE_AT)= ? AND MONTH(CREATE_AT) =? AND DAY(CREATE_AT) = ?; ");
                stm.setString(1,d[0]);
                stm.setString(2,d[1]);
                stm.setString(3,d[2]);

                ResultSet rs = stm.executeQuery();
                while(rs.next()){
                    userN = rs.getString(3);
                    user = UserService.findById(userN);
                    if(user != null){
                        userN = user.getName();
                    }
                    Log log = new Log(rs.getInt(1), rs.getInt(2), userN, rs.getString(5), rs.getString(4).substring(1), rs.getString(6), rs.getInt(7));
                    for(Log l: list){
                        if(log.getId()== l.getId()){
                            res.add(log);
                        }
                    }
                    }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return res;
        }
    public static List<Log> findByDate(String fromDate, String toDate, List<Log> list){
        List<Log> res = new ArrayList<>();
        String userN;
        User user;
        if(!fromDate.equals("0") && !toDate.equals("0")){
        try {
            PreparedStatement stm = con.prepareStatement("SELECT ID, LEVEL, USER, SRC, CONTENT, CREATE_AT, STATUS  FROM logs WHERE CREATE_AT between ? and ?;");
            stm.setString(1,fromDate);
            stm.setString(2, toDate);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                userN = rs.getString(3);
                user = UserService.findById(userN);
                if(user != null){
                    userN = user.getName();
                }
                Log log = new Log(rs.getInt(1), rs.getInt(2), userN, rs.getString(5), rs.getString(4).substring(1), rs.getString(6), rs.getInt(7));
                for(Log l: list){
                    if(log.getId()== l.getId()){
                        res.add(log);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }return res;
        }
        else if(!fromDate.equals("0") && toDate.equals("0")){
            try {
                PreparedStatement stm = con.prepareStatement("SELECT ID, LEVEL, USER, SRC, CONTENT, CREATE_AT, STATUS  FROM logs WHERE CREATE_AT >= ?;");
                stm.setString(1,fromDate);

                ResultSet rs = stm.executeQuery();
                while(rs.next()){
                    userN = rs.getString(3);
                    user = UserService.findById(userN);
                    if(user != null){
                        userN = user.getName();
                    }
                    Log log = new Log(rs.getInt(1), rs.getInt(2), userN, rs.getString(5), rs.getString(4).substring(1), rs.getString(6), rs.getInt(7));
                    for(Log l: list){
                        if(log.getId()== l.getId()){
                            res.add(log);
                        }
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return res;
        }
        else if(fromDate.equals("0") && !toDate.equals("0")){
            try {
                PreparedStatement stm = con.prepareStatement("SELECT ID, LEVEL, USER, SRC, CONTENT, CREATE_AT, STATUS  FROM logs WHERE CREATE_AT <= ?;");
                stm.setString(1,toDate);

                ResultSet rs = stm.executeQuery();
                while(rs.next()){
                    userN = rs.getString(3);
                    user = UserService.findById(userN);
                    if(user != null){
                        userN = user.getName();
                    }
                    Log log = new Log(rs.getInt(1), rs.getInt(2), userN, rs.getString(5), rs.getString(4).substring(1), rs.getString(6), rs.getInt(7));
                    for(Log l: list){
                        if(log.getId()== l.getId()){
                            res.add(log);
                        }
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return res;
        }
        else{
            return list;
        }

    }
    public static List<Log> findByContent(String content, List<Log> list){
        List<Log> res = new ArrayList<>();
        String userN;
        User user;
        try {
            PreparedStatement stm = con.prepareStatement("SELECT ID, LEVEL, USER, SRC, CONTENT, CREATE_AT, STATUS  FROM logs WHERE CONTENT like ?;");
            stm.setString(1,"%"+content+"%");

            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                userN = rs.getString(3);
                user = UserService.findById(userN);
                if(user != null){
                    userN = user.getName();
                }Log log = new Log(rs.getInt(1), rs.getInt(2), userN, rs.getString(5), rs.getString(4).substring(1), rs.getString(6), rs.getInt(7));
                for(Log l: list){
                    if(log.getId()== l.getId()){
                        res.add(log);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }
    public static List<Log> findByLevel(int level, List<Log> list){
        List<Log> res = new ArrayList<>();
        String userN;
        User user;
        try {
            PreparedStatement stm = con.prepareStatement("SELECT ID, LEVEL, USER, SRC, CONTENT, CREATE_AT, STATUS  FROM logs WHERE LEVEL = ?;");
            stm.setInt(1,level);

            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                userN = rs.getString(3);
                user = UserService.findById(userN);
                if(user != null){
                    userN = user.getName();
                }
                Log log = new Log(rs.getInt(1), rs.getInt(2), userN, rs.getString(5), rs.getString(4).substring(1), rs.getString(6), rs.getInt(7));
               for(Log l: list){
                   if(log.getId()== l.getId()){
                       res.add(log);
                   }
               }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return res;
    }
    public static List<String> getListUser(){
            List<String> res = new ArrayList<>();
        String userN;
        User user;
        try {
            PreparedStatement stm = con.prepareStatement("SELECT distinct USER FROM logs ");
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                userN = rs.getString(1);
                user = UserService.findById(userN);
                if(user != null){
                    userN = user.getName();
                }
                res.add(userN);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }
    public static List<String> getListContent(){
        List<String> res = new ArrayList<>();
        try {
            PreparedStatement stm = con.prepareStatement("  SELECT DISTINCT if(content like '%:%', SUBSTRING(CONTENT,1,CHARACTER_LENGTH(CONTENT)-6), content) FROM logs  ");
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                res.add(rs.getString(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }
    public static void removeListLog(String[] logIdArr){
        for(String id:logIdArr){
            removeLog(id);
        }
    }
    public static void removeLog(String id){
        try{
            PreparedStatement stm = con.prepareStatement("UPDATE logs set STATUS = -1 WHERE id=?");
            stm.setInt(1, Integer.parseInt(id));
            stm.executeUpdate();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }





    public static void main(String[] args) {

    }
}
