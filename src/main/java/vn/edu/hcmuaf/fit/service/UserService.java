package vn.edu.hcmuaf.fit.service;

import vn.edu.hcmuaf.fit.bean.User;
import vn.edu.hcmuaf.fit.db.DBConnect;
import vn.edu.hcmuaf.fit.db.JDBIConnector;
import vn.edu.hcmuaf.fit.model.Account;
import vn.edu.hcmuaf.fit.model.Order;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;


import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;


public class UserService {
    private static UserService instance;

    private UserService() {
    }

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }
    public User checkLogin(String account_email, String password) {
        List<User> users = JDBIConnector.get().withHandle(h ->
                h.createQuery("SELECT ACCOUNTS.account_id, ACCOUNTS.account_email, ACCOUNTS.account_pass, ACCOUNTS.account_name, ACCOUNTS.account_role, ACCOUNTS.account_status FROM ACCOUNTS WHERE ACCOUNTS.account_email = ?")
                        .bind(0, account_email)
                        .mapToBean(User.class)
                        .stream()
                        .collect(Collectors.toList())
        );
        if (users.size() != 1) return null;
        User user = users.get(0);
        if (!user.getAccount_pass().equals(hashPassword(password))
                ||!user.getAccount_email().equals(account_email)
        ) return null;
        return user;
    }
    public static String hashPassword(String password) {
        try {
            MessageDigest sha256 = null;
            sha256 = MessageDigest.getInstance("SHA-256");
            byte[] hash = sha256.digest(password.getBytes());
            BigInteger number = new BigInteger(1, hash);
            return number.toString(16);
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }
    public static User findById(String Id){
        List<User> list = getListAcc();
        for (User u: list) {
            if(Id.equals(u.getAccount_id())){
                return u;
            }

        }
        return null;
    }

    public static List<User> getListAcc(){
        List<User> list = new ArrayList<User>();
        Statement statement = DBConnect.getInstall().get();
        if(statement !=null){
            try{
                ResultSet rsAcc = statement.executeQuery("select ACCOUNT_ID, ACCOUNT_EMAIL, ACCOUNT_PASS, ACCOUNT_NAME, ACCOUNT_ROLE, ACCOUNT_STATUS from ACCOUNTS;");
                while(rsAcc.next()){
                    list.add(new User(rsAcc.getString(1), rsAcc.getString(2), rsAcc.getString(3), rsAcc.getString(4), rsAcc.getInt(5), rsAcc.getInt(6)));
                }
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
        else{
            System.out.println("Không có tai khoan");
        }
        return list;

    }
    public static String getLastMaTK(){
        Statement statement = DBConnect.getInstall().get();
        String result = "";
        if (statement != null)
            try {
                ResultSet rs = statement.executeQuery("SELECT ACCOUNTS.ACCOUNT_ID from ACCOUNTS ORDER BY ACCOUNT_ID DESC LIMIT 1");
                while (rs.next()){
                    result = rs.getString(1);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        else {
            System.out.println("Không có đơn hàng");
        }
        return  result;
    }
    public static boolean checkEmail(String email){
        List<User> list = getListAcc();
        List<String> listEmail = new ArrayList<String>();
        for(User a : list){
            listEmail.add(a.getAccount_email());
        }
        if (!listEmail.contains(email)) {
            return true;
        }
        return false;
    }
    public static void register(User acc){
        Statement stm = DBConnect.getInstall().get();
        String stt = getLastMaTK().substring(2);
        String ID = "AD" + (Integer.parseInt(stt) + 1);
        acc.setAccount_id(ID);
        if(stm!= null) {
            try {
                String sql = "insert into ACCOUNTS values('" + ID + "', '" + acc.getAccount_email() + "', '" + hashPassword(acc.getAccount_pass())  + "', '" + acc.getAccount_name() + "'," + acc.getAccount_role() +","+ acc.getAccount_status()+");";
                System.out.println(sql);
                stm.executeUpdate(sql);
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public static int randomCode(){
        return  (int) Math.floor(((Math.random() * 899999) + 100000));
    }
    public  static void sendMail(String toEmail, int code) throws MessagingException, UnsupportedEncodingException {
        String fromEmail= "group27web@gmail.com";
        String pass =  "imvwmzsvffvjtgpr";
        Properties props = new Properties();

        props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
        props.put("mail.smtp.port", "587"); //TLS Port
        props.put("mail.smtp.auth", "true"); //enable authentication
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");

        // get Session
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, pass);
            }
        });

        // compose message
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject("Xác minh tài khoản");
            message.setText("Mã xác nhận của bạn là: " + code);

            // send message
            Transport.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }
    public static void updatePass(String email, String pass)  {
        String sql = "UPDATE ACCOUNTS set ACCOUNT_PASS = '"+pass+"' where ACCOUNT_EMAIL like "+ "'"+email+"'";
        Statement stm  =  DBConnect.getInstall().get();
        try {
            stm.executeUpdate(sql);

        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    public static void updateProfileEmail(String email, User auth)  {
        if(email == null) return;
        String idACC = auth.getAccount_id();
        String sql = "UPDATE ACCOUNTS set ACCOUNT_EMAIL = '"+email+"' where ACCOUNT_ID = "+ "'"+idACC+"'";
        Statement stm  =  DBConnect.getInstall().get();
        try {
            stm.executeUpdate(sql);
            auth.setAccount_email(email);
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
    public static void updateProfileTenTk(String tentk,User auth)  {
        if(tentk == null) return;
        String idACC = auth.getAccount_id();
        String sql = "UPDATE ACCOUNTS set ACCOUNT_NAME = '"+tentk+"' where ACCOUNT_ID = "+ "'"+idACC+"'";
        Statement stm  =  DBConnect.getInstall().get();
        try {
            stm.executeUpdate(sql);
            auth.setAccount_name(tentk);
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
    public static void updateProfileAddress(String diachi, User auth)  {
        if(diachi == null) return;
        String idACC = auth.getAccount_id();
        String sql1 = "UPDATE CUSTOMERS set CUSTOMERS.CUSTOMER_ADDRESS = '"+diachi+"'where CUSTOMERS.CUSTOMER_ID = "+ "'"+idACC+"'";
        Statement stm  =  DBConnect.getInstall().get();
        try {
            stm.executeUpdate(sql1);
            CustomerService.getCusByIdAcc(auth.getAccount_id()).setDIACHI(diachi);
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
    public static void updateProfilePhoneNo(String sdt, User auth)  {
        if(sdt == null) return;
        String idACC = auth.getAccount_id();
        String sql1 = "UPDATE CUSTOMERS set CUSTOMERS.CUSTOMER_PHONE = '"+sdt+"' where CUSTOMERS.CUSTOMER_ID = "+ "'"+idACC+"'";
        Statement stm  =  DBConnect.getInstall().get();
        try {
            stm.executeUpdate(sql1);
            CustomerService.getCusByIdAcc(auth.getAccount_id()).setSDT(sdt);
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
    public static void main(String[] args) throws MessagingException, UnsupportedEncodingException, SQLException {
//        UserService userService = new UserService();
//       System.out.println(userService.checkLogin("thanhthuy@gmail.com", "123").toString());
//       System.out.println(userService.hashPassword("123"));
//       System.out.println(userService.hashPassword("456"));
//       System.out.println(userService.hashPassword("789"));
//       System.out.println(userService.hashPassword("nhom27"));
//        UserService.register(new Account("Thanh@gmail.com","12","Thanh"));

//        System.out.println(UserService.checkEmail("thanh@gmail.com"));
//        sendMail("thanhtamv14717@gmail.com", randomCode());
//        updatePass("thanhtamv14717@gmail.com", hashPassword("123"));
        User u = new User("AD11", "thanhthuy@gmai", "123", "Huy", 0, 0);
        UserService.register(u);
    }
}
