package vn.edu.hcmuaf.fit.bean;


import java.io.Serializable;

public class User implements Serializable {
    private String id;
    private String username;
    private String pass;
    private String tendn;
    private int role;

    public User(){

    }

    public User(String id, String username, String pass, String tendn, int role) {
        this.id = id;
        this.username = username;
        this.pass = pass;
        this.tendn = tendn;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public String getPass() {
        return pass;
    }

    public String getTendn() {
        return tendn;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setTendn(String tendn) {
        this.tendn = tendn;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public boolean checkRole(int i) {
        if(this.role == 1) return true;
        return false;
    }
    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", pass='" + pass + '\'' +
                ", tendn='" + tendn + '\'' +
                '}';
    }


}
