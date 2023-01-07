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
    public static String getLastMaKH(){
        Statement statement = DBConnect.getInstall().get();
        String result = "";
        if (statement != null)
            try {
                ResultSet rs = statement.executeQuery("SELECT khachhang.MAKH from khachhang ORDER BY MAKH DESC LIMIT 1");
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
    public static void registerKH(Customer acc, User user){
        Statement stm = DBConnect.getInstall().get();
        String stt = getLastMaKH().substring(2);
        String ID = "KH" + (Integer.parseInt(stt) + 1);
        acc.setMAKH(ID);
        acc.setDIACHI("TPHCM");
        acc.setSDT("035640789");
        String sql = " ";
        if(stm!= null) {
            try {
                 sql = "insert into khachhang values ('" + ID + "', '" + user.getTentk() + "', '"
                        + user.getId()  + "', '" + acc.getDIACHI() + "'," + acc.getSDT()+");";
                stm.executeUpdate(sql);

            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
    public static String getIDKhach(String idAcc){
        for(Customer c: getListCustomer()){
            if(c.getMATAIKHOAN().equals(idAcc)){
                return c.getMAKH();
            }
        }
        return null;
    }
    public static Customer getCusById(String makh){
        for(Customer c: getListCustomer()){
            if(c.getMAKH().equals(makh)){
                return c;
            }
        }
        return null;
    }
    public static Customer getCusByIdAcc(String idAcc){
        for(Customer c: getListCustomer()){
            if(c.getMATAIKHOAN().equals(idAcc)){
                return c;
            }
        }
        return null;
    }
    public static void main(String[] args) {
//    System.out.println(getCusByIdAcc("AD01"));
    }
}
