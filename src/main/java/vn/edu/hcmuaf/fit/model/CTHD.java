package vn.edu.hcmuaf.fit.model;

public class CTHD {
    private String mahd;
    private String masp;
    private int solg;

    public CTHD(String mahd, String masp, int solg) {
        this.mahd = mahd;
        this.masp = masp;
        this.solg = solg;
    }

    public String getMahd() {
        return mahd;
    }

    public void setMahd(String mahd) {
        this.mahd = mahd;
    }

    public String getMasp() {
        return masp;
    }

    public void setMasp(String masp) {
        this.masp = masp;
    }

    public int getSolg() {
        return solg;
    }

    public void setSolg(int solg) {
        this.solg = solg;
    }
}
