package vn.edu.hcmuaf.fit.db;

import java.sql.*;

public class DBConnect {
    //    static String URL = "jdbc:mysql://192.168.149.128:3306/banbanhkem";
    static String URL = "jdbc:mysql://103.9.159.203:3306/banbanhkem";
    static String user = "root";
    static String pass = "12345";
    static Connection conn;
    static DBConnect install;

    private DBConnect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, user, pass);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static DBConnect getInstall() {
        if (install == null)
            install = new DBConnect();
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

    public static Connection getConn() {

        return conn;
    }

    public static void main(String[] args) {
    }
}
