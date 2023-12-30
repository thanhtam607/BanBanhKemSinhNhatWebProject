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
                ResultSet rs = statement.executeQuery("SELECT CUSTOMERS.ID,  CUSTOMERS.ADDRESS,  CUSTOMERS.PHONE, ACCOUNTS.ROLE from CUSTOMERS, ACCOUNTS where ACCOUNTS.ID = CUSTOMERS.ID;");
                while(rs.next()){
                    listC.add(new Customer(rs.getString(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getInt(4)
                    ));
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

    public static void registerKH(Customer acc, User user){
        Statement stm = DBConnect.getInstall().get();
        String ID = user.getId();
        acc.setMAKH(ID);
        acc.setDIACHI("");
        acc.setSDT("");
        String sql = "";
        if(stm!= null) {
            try {
                 sql = "insert into CUSTOMERS values ('" +ID+ "', '"
                           + acc.getDIACHI() + "','" + acc.getSDT()+"');";
                stm.executeUpdate(sql);

            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public static Customer getCusByIdAcc(String idAcc){
        for(Customer c: getListCustomer()){
            if(c.getMAKH().equals(idAcc)) return c;
        }
        return null;
    }



    public static void main(String[] args) {



    }
}
