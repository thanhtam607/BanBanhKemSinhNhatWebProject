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
import java.util.Base64;


public class KeyManager {
    public static void genKey(String userId) throws SQLException, NoSuchAlgorithmException, MessagingException, UnsupportedEncodingException {
        KeyPair keyPair = RSA.genKeyPair();
        if(userIsHasKey(userId)){
            updateExpireDateForKey(userId);
            updateStatusForKey(userId, 2);
        }
        PublicKey publicKey = RSA.genPublicKey(keyPair);
        PrivateKey privateKey = RSA.genPrivateKey(keyPair);
        insertPublicKey(userId, Base64.getEncoder().encodeToString(publicKey.getEncoded()));
        String subject = "Tạo khóa mới";
        String mess = Base64.getEncoder().encodeToString(privateKey.getEncoded());
        String filename = "yourfile.xml";
        UserService.sendMailWithPrivateKey(UserService.getEmail(userId), subject, UserService.convertMessageToXML(mess, filename));

    }
    private static void updateStatusForKey(String userId, int status) throws SQLException {
        String sql = "UPDATE PUBLICKEY SET STATUS = ? WHERE USER_ID  = ? AND STATUS = 1";
        PreparedStatement stm = DBConnect.getInstall().getConn().prepareStatement(sql);
        stm.setInt(1,status);
        stm.setString(2, userId);
        stm.executeUpdate();
    }
    private static void updateExpireDateForKey(String userId) throws SQLException {
        String sql = "UPDATE PUBLICKEY SET EXPIREDDATE = NOW() WHERE USER_ID  = ? AND STATUS = 1";
        PreparedStatement stm = DBConnect.getInstall().getConn().prepareStatement(sql);
        stm.setString(1, userId);
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
        String sql = "INSERT INTO PUBLICKEY(USER_ID, PUBLICKEYLINK, CREATEDATE, STATUS) VALUES (?,?,NOW(),1)";

        PreparedStatement stm = DBConnect.getInstall().getConn().prepareStatement(sql);
        stm.setString(1, userId);
        stm.setString(2, publicKeyLink);
        stm.executeUpdate();
    }

    public static void main(String[] args) throws SQLException, MessagingException, UnsupportedEncodingException, NoSuchAlgorithmException {

        System.out.println( userIsHasKey("114539523549397711833"));
    }



}
