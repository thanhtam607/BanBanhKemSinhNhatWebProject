package vn.edu.hcmuaf.fit.security;

import vn.edu.hcmuaf.fit.db.DBConnect;
import vn.edu.hcmuaf.fit.service.UserService;


import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;


public class KeyManager {
    public static void genKey(String userId) throws SQLException, NoSuchAlgorithmException, MessagingException, UnsupportedEncodingException {
        KeyPair keyPair = RSA.genKeyPair();
        PublicKey publicKey = RSA.genPublicKey(keyPair);
        PrivateKey privateKey = RSA.genPrivateKey(keyPair);
        insertPublicKey(userId, Base64.getEncoder().encodeToString(publicKey.getEncoded()));
        String subject = "Khóa riêng tư do người dùng tạo từ TiemBanhHanhPhuc";
        String mess = Base64.getEncoder().encodeToString(privateKey.getEncoded());
        String filename = "PvKey_TiemBanhHanhPhuc.xml";
        UserService.sendMailWithPrivateKey(UserService.getEmail(userId), subject, UserService.convertMessageToXML(mess, filename));

    }
    public static String getTimeNow(){
        Date today = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(today);
    }
    public static void updateStatusForKey(String userId, int status) throws SQLException {
        String sql = "UPDATE PUBLICKEY SET STATUS = ? WHERE USER_ID  = ? AND STATUS = 1";
        PreparedStatement stm = DBConnect.getInstall().getConn().prepareStatement(sql);
        stm.setInt(1,status);
        stm.setString(2, userId);
        stm.executeUpdate();
    }
    public static void updateExpireDateForKey(String userId) throws SQLException {
        String sql = "UPDATE PUBLICKEY SET EXPIREDDATE = ? WHERE USER_ID  = ? AND STATUS = 1";
        PreparedStatement stm = DBConnect.getInstall().getConn().prepareStatement(sql);
        stm.setString(1, getTimeNow());
        stm.setString(2, userId);
        stm.executeUpdate();
    }
    public static boolean userIsHasKey(String id) throws SQLException {
        String sql = "SELECT ID FROM PUBLICKEY WHERE USER_ID = ?";
        PreparedStatement stm = DBConnect.getInstall().getConn().prepareStatement(sql);
        stm.setString(1, id);
        ResultSet rs = stm.executeQuery();
        return rs.next();
    }
    public static void insertPublicKey(String userId, String publicKeyLink) throws SQLException {
        String sql = "INSERT INTO PUBLICKEY(USER_ID, PUBLICKEYLINK, CREATEDATE, STATUS) VALUES (?,?,?,1)";

        PreparedStatement stm = DBConnect.getInstall().getConn().prepareStatement(sql);
        stm.setString(1, userId);
        stm.setString(2, publicKeyLink);
        stm.setString(3, getTimeNow());
        stm.executeUpdate();
    }
    public static void updateMissingDateForKey(String userId,  String date) throws SQLException {
        String sql = "UPDATE PUBLICKEY SET MISSINGDATE = ? WHERE USER_ID  = ? AND STATUS = 1";
        PreparedStatement stm = DBConnect.getInstall().getConn().prepareStatement(sql);
        stm.setString(1,date);
        stm.setString(2, userId);
        stm.executeUpdate();
    }
    public static void updateReportDateForKey(String userId) throws SQLException {
        String sql = "UPDATE PUBLICKEY SET REPORTDATE = ? WHERE USER_ID  = ? AND STATUS = 1";
        PreparedStatement stm = DBConnect.getInstall().getConn().prepareStatement(sql);
        stm.setString(1, getTimeNow());
        stm.setString(2, userId);
        stm.executeUpdate();
    }
    public static void disableKey(String userId) throws SQLException {
       updateExpireDateForKey(userId);
       updateStatusForKey(userId, 2);
    }
    private static boolean checkReportedKey(String userId) throws SQLException {
        String sql = "SELECT PUBLICKEY.STATUS, MAX(PUBLICKEY.CREATEDATE) FROM PUBLICKEY WHERE USER_ID = ?";
        PreparedStatement stm = DBConnect.getInstall().getConn().prepareStatement(sql);
        stm.setString(1, userId);
        ResultSet rs = stm.executeQuery();
        while(rs.next()){
            if(rs.getInt(1) ==1) return true;
        }
        return false;
    }
    public static boolean userIsHasKeyActive(String id) throws SQLException {
        String sql = "SELECT ID FROM PUBLICKEY WHERE USER_ID = ? AND STATUS = 1";
        PreparedStatement stm = DBConnect.getInstall().getConn().prepareStatement(sql);
        stm.setString(1, id);
        ResultSet rs = stm.executeQuery();
        return rs.next();
    }

    public static void main(String[] args) {
        try {
            updateMissingDateForKey("AD12", "1/1/2024");
        }catch (SQLException e){
            if(e.getMessage().equals("Check constraint 'chk1' is violated.")){
                System.out.println("ngày không hợp lệ");}
            else{
                System.out.println(e.getMessage());
            }
        }
    }


}
