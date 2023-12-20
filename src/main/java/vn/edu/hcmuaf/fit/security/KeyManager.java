package vn.edu.hcmuaf.fit.security;

import vn.edu.hcmuaf.fit.db.DBConnect;

import java.io.FileOutputStream;
import java.io.IOException;
import java.security.PublicKey;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.bouncycastle.openssl.PEMWriter;


public class KeyManager {
    public static void genKey(String userId) throws SQLException {
        if(!userIsHasKey(userId)){

        }else{
            updateStatusForKey(userId, 2);

        }

    }
    private static void updateStatusForKey(String userId, int status) throws SQLException {
        String sql = "update PublicKey set Status = ? where User_Id  = ? and status = 1";
        PreparedStatement stm = DBConnect.getInstall().getConn().prepareStatement(sql);
        stm.setInt(1,2);
        stm.setString(2, userId);
        stm.executeUpdate();
    }
    private static boolean userIsHasKey(String id) throws SQLException {
        String sql = "select id from PublicKey where User_Id = ?";
        PreparedStatement stm = DBConnect.getInstall().getConn().prepareStatement(sql);
        stm.setString(1, id);
        ResultSet rs = stm.executeQuery();
        return rs.next();
    }
    public static void insertPublicKey(String userId, String publicKeyLink) throws SQLException {
        String sql = "insert into PublicKey(User_id, publickeylink, createDate, expiredDate, status)" +
                " values(?,?,now(), ?,1)";
        PreparedStatement stm = DBConnect.getInstall().getConn().prepareStatement(sql);
        stm.setString(1, userId);
        stm.setString(2, publicKeyLink);
        stm.setString(3, "31-12-2099");
        stm.executeUpdate();
    }

    private static void savePublicKeyToFile(PublicKey publicKey, String filename) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(filename);
             PEMWriter pemWriter = new PEMWriter(fos)) {

            pemWriter.writeObject(publicKey);
        }
    }

}
