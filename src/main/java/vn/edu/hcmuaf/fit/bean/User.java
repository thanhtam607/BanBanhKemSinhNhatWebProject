package vn.edu.hcmuaf.fit.bean;


import java.io.Serializable;

public class User implements Serializable {
    private String id;
    private String email;
    private String pass;
    private String tentk;
    private int role;

    public User(){

    }

    public User(String id, String email, String pass, String tentk, int role) {
        this.id = id;
        this.email = email;
        this.pass = pass;
        this.tentk = tentk;
        this.role = role;
    }
    public User(String email, String pass, String tentk ){
        this.id = null;
        this.email = email;
        this.pass = pass;
        this.tentk =tentk;
        this.role=0;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getTentk() {
        return tentk;
    }

    public void setTentk(String tentk) {
        this.tentk = tentk;
    }

    public int getRole() {
        return role;
    }
    public String getRoleName() {
        if(this.role == 0){
            return "Thường";
        }else if(this.role == 1){
            return "Admin";
        }
        return "Khóa";
    }

    public void setRole(int role) {
        this.role = role;
    }

    public boolean checkRole(int i) {
        if(this.role == i) return true;
        return false;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", pass='" + pass + '\'' +
                ", tentk='" + tentk + '\'' +
                ", role=" + role +
                '}';
    }
}
