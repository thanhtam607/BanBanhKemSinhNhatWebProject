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
            updateStatusForKey(userId, 2);
        }
        PublicKey publicKey = RSA.genPublicKey(keyPair);
        PrivateKey privateKey = RSA.genPrivateKey(keyPair);
        insertPublicKey(userId, Base64.getEncoder().encodeToString(publicKey.getEncoded()));
        String subject = "Tạo khóa mới";
        String mess = "Khóa tài khoản của bạn là: " + Base64.getEncoder().encodeToString(privateKey.getEncoded());
        UserService.sendMail(UserService.getEmail(userId), subject, mess) ;

    }
    private static void updateStatusForKey(String userId, int status) throws SQLException {
        String sql = "update PublicKey set Status = ? where User_Id  = ? and status = 1";
        PreparedStatement stm = DBConnect.getInstall().getConn().prepareStatement(sql);
        stm.setInt(1,2);
        stm.setString(2, userId);
        stm.executeUpdate();
    }
    public static boolean userIsHasKey(String id) throws SQLException {
        String sql = "select id from PublicKey where User_Id = ?";
        PreparedStatement stm = DBConnect.getInstall().getConn().prepareStatement(sql);
        stm.setString(1, id);
        ResultSet rs = stm.executeQuery();

        return rs.next();
    }
    public static void insertPublicKey(String userId, String publicKeyLink) throws SQLException {
        String sql = "";
        if(!userIsHasKey(userId)){
            sql = "insert into PublicKey(User_id, publickeylink, createDate, expiredDate, status)" +
                    " values(?,?,now(), ?,1)";
        }else{
            sql = "insert into PublicKey(User_id, publickeylink, createDate, MissingDate, expiredDate, status)" +
                    " values(?,?,now(), CURDATE(),?,1)";
        }
        PreparedStatement stm = DBConnect.getInstall().getConn().prepareStatement(sql);
        stm.setString(1, userId);
        stm.setString(2, publicKeyLink);
        stm.setString(3, "2099/12/31");
        stm.executeUpdate();
    }

    public static void main(String[] args) throws SQLException, MessagingException, UnsupportedEncodingException, NoSuchAlgorithmException {

        System.out.println( userIsHasKey("114539523549397711833"));
    }



}
