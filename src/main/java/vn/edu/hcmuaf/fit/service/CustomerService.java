package vn.edu.hcmuaf.fit.service;

import vn.edu.hcmuaf.fit.bean.User;
import vn.edu.hcmuaf.fit.db.DBConnect;
import vn.edu.hcmuaf.fit.model.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomerService {
    public static List<Customer> getListCustomer(){
        List<Customer> listC = new ArrayList<Customer>();
        Statement statement = DBConnect.getInstall().get();
        if(statement !=null){
            try{
                ResultSet rs = statement.executeQuery("SELECT MAKH, TENKH, MATAIKHOAN, DIACHI, SDT from khachhang");
                while(rs.next()){
                    listC.add(new Customer(rs.getString(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getString(5)));
                }
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
        else{
            System.out.println("Không có khách hàng");
        }
        return listC;

    }
    public static String getIDKhach(String idAcc){
        for(Customer c: getListCustomer()){
            if(c.getMATAIKHOAN().equals(idAcc)){
                return c.getMAKH();
            }
        }
        return null;
    }

    public static void main(String[] args) {
//    System.out.println(getIDKhach("AD01"));
    }
}
