package vn.edu.hcmuaf.fit.service;

import vn.edu.hcmuaf.fit.bean.User;
import vn.edu.hcmuaf.fit.db.DBConnect;
import vn.edu.hcmuaf.fit.db.JDBIConnector;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;


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
    public User checkLogin(String email, String password) {
        List<User> users = JDBIConnector.get().withHandle(h ->
                h.createQuery("SELECT ACCOUNTS.id, ACCOUNTS.email, ACCOUNTS.pass, ACCOUNTS.name, ACCOUNTS.role, ACCOUNTS.status,ACCOUNTS.type  FROM ACCOUNTS WHERE ACCOUNTS.email = ?")
                        .bind(0, email)
                        .mapToBean(User.class)
                        .stream()
                        .collect(Collectors.toList())
        );
        if (users.size() != 1) return null;
        User user = users.get(0);
        if (!user.getPass().equals(hashPassword(password))
                ||!user.getEmail().equals(email)
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
        Statement stm = DBConnect.getInstall().get();
        User user;
        try {
            ResultSet rs = stm.executeQuery("SELECT ID, EMAIL, PASS, NAME, ROLE, STATUS, TYPE ,ISADD,ISEDIT,ISDELETE FROM accounts WHERE  accounts.ID = '"+ Id+"'");
            while(rs.next()) {
                user = new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6), rs.getString(7),rs.getInt(8), rs.getInt(9), rs.getInt(10));
                if (user != null) {
                    return user;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        return null;
    }

    public static List<User> getListAcc(){
        List<User> list = new ArrayList<User>();
        Statement statement = DBConnect.getInstall().get();
        if(statement !=null){
            try{
                ResultSet rsAcc = statement.executeQuery("select ID, EMAIL, PASS, NAME, ROLE, STATUS, TYPE, ISADD, ISEDIT, ISDELETE from ACCOUNTS;");
                while(rsAcc.next()){
                    list.add(new User(rsAcc.getString(1), rsAcc.getString(2), rsAcc.getString(3), rsAcc.getString(4), rsAcc.getInt(5), rsAcc.getInt(6), rsAcc.getString(7), rsAcc.getInt(8), rsAcc.getInt(9), rsAcc.getInt(10)));
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
    public static List<User> getListUser(){
        List<User> list = new ArrayList<User>();
        Statement statement = DBConnect.getInstall().get();
        if(statement !=null){
            try{
                ResultSet rsAcc = statement.executeQuery("select  ACCOUNTS.ID, ACCOUNTS.EMAIL, ACCOUNTS.PASS, ACCOUNTS.NAME, ACCOUNTS.ROLE, ACCOUNTS.STATUS, ACCOUNTS.TYPE, ACCOUNTS.ISADD, ACCOUNTS.ISEDIT, ACCOUNTS.ISDELETE from ACCOUNTS");
                while(rsAcc.next()){
                    list.add(new User(rsAcc.getString(1), rsAcc.getString(2), rsAcc.getString(3), rsAcc.getString(4), rsAcc.getInt(5), rsAcc.getInt(6), rsAcc.getString(7), rsAcc.getInt(8), rsAcc.getInt(9), rsAcc.getInt(10)));
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
                ResultSet rs = statement.executeQuery("SELECT ACCOUNTS.ID from ACCOUNTS ORDER BY ID DESC LIMIT 1");
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
            listEmail.add(a.getEmail());
        }
        if (!listEmail.contains(email)) {
            return true;
        }else
        return false;
    }
    public static void register(User acc){
        Statement stm = DBConnect.getInstall().get();
        String stt = getLastMaTK().substring(2);
        String ID = "AD" + (Integer.parseInt(stt) + 1);
        acc.setId(ID);
        if(stm!= null) {
            try {
                String sql = "insert into ACCOUNTS values('" + ID + "', '" + acc.getEmail() + "', '" + hashPassword(acc.getPass())  + "', '" + acc.getName() + "'," + acc.getRole() +","+ acc.getStatus()+","+acc.getType()+",0,0,0);";
                stm.executeUpdate(sql);

            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
    public static void addAccGG(User acc){
        Statement stm = DBConnect.getInstall().get();
        if(stm!= null) {
            try {
                String sql = "insert into ACCOUNTS(ID,EMAIL,NAME,ROLE, STATUS,TYPE, ISADD, ISEDIT, ISDELETE) values('" + acc.getId() + "', '" + acc.getEmail() + "', '" + acc.getName() + "'," + acc.getRole() +","+ acc.getStatus()+",'"+acc.getType()+"',0,0,0);";
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
        String sql = "UPDATE ACCOUNTS set PASS = '"+pass+"' where EMAIL like "+ "'"+email+"'";
        Statement stm  =  DBConnect.getInstall().get();
        try {
            stm.executeUpdate(sql);

        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    public static void updateProfile(String name, String sdt, String diachi, String mail, String id, User user)  {
        if(name == null || sdt == null || diachi == null || mail == null) return;
        String sql1 = "UPDATE CUSTOMERS, ACCOUNTS set CUSTOMERS.ADDRESS = '"+diachi+"', " +
                "CUSTOMERS.PHONE = '"+sdt+"', ACCOUNTS.EMAIL = '"+mail+"'," +
                " ACCOUNTS.NAME='" +name+"'" +
                " WHERE CUSTOMERS.id = '"+id+"' and CUSTOMERS.id = ACCOUNTS.id";
        Statement stm  =  DBConnect.getInstall().get();
        try {
            stm.executeUpdate(sql1);
            user.setEmail(mail);
            user.setName(name);
            CustomerService.getCusByIdAcc(id).setSDT(sdt);
            CustomerService.getCusByIdAcc(id).setDIACHI(diachi);
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
    public static void updateProfile(String name, String sdt, String diachi, String mail, User auth)  {
        if(name == null || sdt == null || diachi == null || mail == null) return;
        String idACC = auth.getId();
        String sql1 = "UPDATE CUSTOMERS, ACCOUNTS set CUSTOMERS.ADDRESS = '"+diachi+"', " +
                "CUSTOMERS.PHONE = '"+sdt+"', ACCOUNTS.EMAIL = '"+mail+"'," +
                " ACCOUNTS.NAME='" +name+"'" +
                " WHERE CUSTOMERS.id = '"+idACC+"' and CUSTOMERS.id = ACCOUNTS.id";
        Statement stm  =  DBConnect.getInstall().get();
        try {
            stm.executeUpdate(sql1);
            auth.setName(name);
            auth.setEmail(mail);
            CustomerService.getCusByIdAcc(auth.getId()).setDIACHI(diachi);
            CustomerService.getCusByIdAcc(auth.getId()).setSDT(sdt);
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
    public static User findByEmail(String email){
        for(User u: getListAcc()){
            if(!checkEmail(email) && u.getEmail().equals(email)){
                return u;
            }
        }
        return null;
    }
    public static void updateType(String email, String type)  {
        String sql = "UPDATE ACCOUNTS set TYPE = '"+type+"' where EMAIL like "+ "'"+email+"'";
        Statement stm  =  DBConnect.getInstall().get();
        try {
            stm.executeUpdate(sql);

        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
  public static List<User> getListEmployee(){
        List<User> listuser = UserService.getListUser();
        List<User> listemp = new LinkedList<>();
        for(User u: listuser){
            if(u.getRole() == 1){
                listemp.add(u);
            }
        }
        return listemp;
    }
    public static void updateAdd(String id, int n)  {
        String sql = "UPDATE ACCOUNTS set ISADD = '"+n+"' where ID = '"+id+"'";
        Statement stm  =  DBConnect.getInstall().get();
        try {
            stm.executeUpdate(sql);

        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
    public static void updateEdit(String id, int n)  {
        String sql = "UPDATE ACCOUNTS set ISEDIT = '"+n+"' where ID = '"+id+"'";
        Statement stm  =  DBConnect.getInstall().get();
        try {
            stm.executeUpdate(sql);

        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
    public static void updateDelete(String id, int n)  {
        String sql = "UPDATE ACCOUNTS set ISDELETE = '"+n+"' where ID = '"+id+"'";
        Statement stm  =  DBConnect.getInstall().get();
        try {
            stm.executeUpdate(sql);

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
        User user = new User();
        user.setId("fdfgrf");
        user.setEmail("dfdf");
        user.setType("fdfdcd");
        user.setName("dfw");
        addAccGG(user);


    }
}
