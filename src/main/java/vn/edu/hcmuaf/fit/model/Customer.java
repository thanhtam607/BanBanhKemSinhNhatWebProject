package vn.edu.hcmuaf.fit.model;

import vn.edu.hcmuaf.fit.bean.User;

public class Customer {
    private String MAKH;
    private String TENKH;
    private String MATAIKHOAN;
    private String DIACHI ;
    private String SDT;

    public Customer(String MAKH, String TENKH, String MATAIKHOAN, String DIACHI, String SDT) {

        this.MAKH = MAKH;
        this.TENKH = TENKH;
        this.MATAIKHOAN = MATAIKHOAN;
        this.DIACHI = DIACHI;
        this.SDT = SDT;
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

    public String getMATAIKHOAN() {
        return MATAIKHOAN;
    }

    public void setMATAIKHOAN(String MATAIKHOAN) {
        this.MATAIKHOAN = MATAIKHOAN;
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

}
