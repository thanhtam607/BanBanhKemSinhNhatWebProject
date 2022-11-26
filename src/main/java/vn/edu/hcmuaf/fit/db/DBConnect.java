package vn.edu.hcmuaf.fit.db;

import java.sql.*;

public class DBConnect {
    String URL = "jdbc:mysql://localhost:3306/banbanhkem";
    String user= "root";
    String pass= "";
    Connection conn;
    static DBConnect install;
    private DBConnect(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, user, pass);
        }
        catch (ClassNotFoundException | SQLException e){
            throw  new RuntimeException(e);
        }
    }
    public static DBConnect getInstall(){
        if(install==null)
            install= new DBConnect();
        return install;
    }
    public Statement get() {
        if (conn == null) return null;

        try {

            return conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        } catch (SQLException e) {
            return null;
        }

    }

    public static void main(String[] args) {

    }
}
