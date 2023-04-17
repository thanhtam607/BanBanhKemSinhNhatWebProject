package vn.edu.hcmuaf.fit.bean;


import java.io.Serializable;

public class User implements Serializable {
    private String  id;
    private String  email;
    private String  pass;
    private String  name;
    private int  role;
    private int  status;
    private  String type;
    private int isadd;
    private int isedit;
    private int isdelete;

    public User(){

    }

    public User(String  id, String  email, String  pass, String  name, int  role, int  status, String type, int isadd, int isedit, int isdelete) {
         this.id =  id;
         this.email =  email;
         this.pass =  pass;
         this.name =  name;
         this.role =  role;
         this.status =  status;
         this.type=type;
        this.isadd = isadd;
        this.isedit = isedit;
        this.isdelete = isdelete;
    }

    public User(String  email, String  pass, String  name){
         this.id = null;
         this.email =  email;
         this.pass =  pass;
         this.name =  name;
         this.role=0;
         this.status=0;
        this.type=null;
    }



    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getRoleName() {
        if(this.role == 0){
            return "Thường";
        }else if(this.role == 1){
            return "Admin";
        }
        return "Quản Lí";
    }

    public String getStatusName() {
        if(this.status == 0) return "Hoạt Động";
        return "Khóa";
    }

    public boolean checkRole() {
        if(this.role == 1 || this.role == 2) return true;
        return false;
    }
    public boolean checkStatus() {
        if(this.status == -1) return true;
        return false;
    }
    public int getIsadd() {
        return isadd;
    }

    public void setIsadd(int isadd) {
        this.isadd = isadd;
    }

    public int getIsedit() {
        return isedit;
    }

    public void setIsedit(int isedit) {
        this.isedit = isedit;
    }

    public int getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(int isdelete) {
        this.isdelete = isdelete;
    }
}
