package vn.edu.hcmuaf.fit.model;

public class Account {
    String username;
    String password;
    public Account(String username, String password ){
        this.password = password;
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
