package vn.edu.hcmuaf.fit.bean;


import java.io.Serializable;

public class User implements Serializable {
    private String account_id;
    private String account_email;
    private String account_pass;
    private String account_name;
    private int account_role;
    private int account_status;

    public User(){

    }

    public User(String account_id, String account_email, String account_pass, String account_name, int account_role, int account_status) {
        this.account_id = account_id;
        this.account_email = account_email;
        this.account_pass = account_pass;
        this.account_name = account_name;
        this.account_role = account_role;
        this.account_status = account_status;
    }

    public User(String account_email, String account_pass, String account_name){
        this.account_id = null;
        this.account_email = account_email;
        this.account_pass = account_pass;
        this.account_name = account_name;
        this.account_role=0;
        this.account_status=0;
    }

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

    public String getAccount_email() {
        return account_email;
    }

    public void setAccount_email(String account_email) {
        this.account_email = account_email;
    }

    public String getAccount_pass() {
        return account_pass;
    }

    public void setAccount_pass(String account_pass) {
        this.account_pass = account_pass;
    }

    public String getAccount_name() {
        return account_name;
    }

    public void setAccount_name(String account_name) {
        this.account_name = account_name;
    }

    public int getAccount_role() {
        return account_role;
    }

    public void setAccount_role(int account_role) {
        this.account_role = account_role;
    }

    public int getAccount_status() {
        return account_status;
    }

    public void setAccount_status(int account_status) {
        this.account_status = account_status;
    }

    public String getRoleName() {
        if(this.account_role == 0){
            return "Thường";
        }else if(this.account_role == 1){
            return "Admin";
        }
        return "Quản Lí";
    }

    public String getStatusName() {
        if(this.account_status == 0) return "Hoạt Động";
        return "Khóa";
    }

    public boolean checkRole() {
        if(this.account_role == 1 || this.account_role == 2) return true;
        return false;
    }
    public boolean checkStatus() {
        if(this.account_status == -1) return true;
        return false;
    }

}
