package vn.edu.hcmuaf.fit.service;

import vn.edu.hcmuaf.fit.db.DBConnect;
import vn.edu.hcmuaf.fit.model.Account;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AccountService {
    public static List<Account> getListAcc(){
        List<Account> list = new ArrayList<Account>();
        Statement statement = DBConnect.getInstall().get();
        if(statement !=null){
            try{
                ResultSet rsAcc = statement.executeQuery("select USERNAME, PASSWORD from khachhang");
                while(rsAcc.next()){
                    list.add(new Account(rsAcc.getString(1), rsAcc.getString(2)));
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
    public static void register(Account acc){
        Statement stm = DBConnect.getInstall().get();
        List<Account> list = getListAcc();
        if(stm!= null) {

            try {

                String maKH = "KH" + (list.size() + 1);
                String sql = "insert into khachhang values ('" + maKH + "', '" + acc.getUsername() + "', '" + acc.getUsername() + "', '" + acc.getPassword() + "', 'diachi','0992030323');";
                stm.executeUpdate(sql);

            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        List<Account> list = getListAcc();
        System.out.println(list.size());
        register(new Account("thanhtam", "123"));
    }
}
