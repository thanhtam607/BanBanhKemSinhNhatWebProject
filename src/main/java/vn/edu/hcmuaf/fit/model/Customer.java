package vn.edu.hcmuaf.fit.model;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class Customer {
    private String MAKH;
    private String DIACHI;
    private String SDT;
    private int role;

    public Customer(String MAKH, String DIACHI, String SDT, int role) {

        this.MAKH = MAKH;
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

    public String getRole() throws UnsupportedEncodingException {
        if(this.role == 0){
            return URLEncoder.encode("Thường", "UTF-8");
        }else if(this.role == 1){
            return URLEncoder.encode("Admin", "UTF-8");
        }
        return URLEncoder.encode("Quản Lí", "UTF-8");
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
                ", DIACHI='" + DIACHI + '\'' +
                ", SDT='" + SDT + '\'' +
                '}';
    }
}
