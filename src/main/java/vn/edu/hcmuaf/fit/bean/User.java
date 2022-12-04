package vn.edu.hcmuaf.fit.bean;


import java.io.Serializable;

public class User implements Serializable {
    private String id;
    private String username;
    private String pass;
    private String fullname;

    public User(){

    }

    public User(String id, String username, String pass, String fullname) {
        this.id = id;
        this.username = username;
        this.pass = pass;
        this.fullname = fullname;
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

    public String getFullname() {
        return fullname;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", pass='" + pass + '\'' +
                ", fullname='" + fullname + '\'' +
                '}';
    }
}
