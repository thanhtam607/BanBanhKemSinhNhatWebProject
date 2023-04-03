package vn.edu.hcmuaf.fit.model;

public class Account {
    String email;
    String name;
    String password;
    int role;
    public Account(String email, String password, String name ){
        this.email = email;
        this.password = password;
        this.name = name;
        this.role=0;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;

    }

    public String getPassword() {
        return password;
    }


    public String getName() {
        return name;

    }

    public void setPassword(String password) {
        this.password = password;
    }


    public void setName(String Name) {
        this.name = name;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getRole() {
        return role;

    }


}
