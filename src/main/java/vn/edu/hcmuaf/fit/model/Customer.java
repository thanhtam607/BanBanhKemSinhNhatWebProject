package vn.edu.hcmuaf.fit.model;

import vn.edu.hcmuaf.fit.bean.User;

public class Customer {
    private String MAKH;
    private String TENKH;
    private String DIACHI;
    private String SDT;
    private int role;

    public Customer(String MAKH, String TENKH, String DIACHI, String SDT, int role) {

        this.MAKH = MAKH;
        this.TENKH = TENKH;
        this.DIACHI = DIACHI;
        this.SDT = SDT;
        this.role = role;
    }

    public Customer() {

    }

    public String getMAKH() {
        return MAKH;
    }

    public void setMAKH(String MAKH) {
        this.MAKH = MAKH;
    }

    public String getTENKH() {
        return TENKH;
    }

    public void setTENKH(String TENKH) {
        this.TENKH = TENKH;
    }

    public String getDIACHI() {
        return DIACHI;
    }

    public void setDIACHI(String DIACHI) {
        this.DIACHI = DIACHI;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getRole() {
        if(this.role == 0){
            return "Thường";
        }else if(this.role == 1){
            return "Admin";
        }
        return "Quản Lí";
    }
    public int getRoleNo() {
        return this.role;
    }


    public void setRole(int role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "MAKH='" + MAKH + '\'' +
                ", TENKH='" + TENKH + '\'' +
                ", DIACHI='" + DIACHI + '\'' +
                ", SDT='" + SDT + '\'' +
                '}';
    }
}
