package vn.edu.hcmuaf.fit.model;

public class LoaiBanh {
    String maLB;
    String tenLB;
    public LoaiBanh(String MaLB, String tenLB){
        this.maLB=MaLB;
        this.tenLB=tenLB;
    }

    public String getMaLB() {
        return maLB;
    }

    public String getTenLB() {
        return tenLB;
    }

    public void setMaLB(String maLB) {
        this.maLB = maLB;
    }

    public void setTenLB(String tenLB) {
        this.tenLB = tenLB;
    }
}
