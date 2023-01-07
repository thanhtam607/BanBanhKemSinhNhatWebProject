package vn.edu.hcmuaf.fit.model;

import java.util.Date;

public class Ship {
    private String mahd;
    private String ngayGiao;
    private String diachigiao;

    public Ship(String mahd, String ngayGiao, String diachigiao) {
        this.mahd = mahd;
        this.ngayGiao = ngayGiao;
        this.diachigiao = diachigiao;
    }

    public String getMahd() {
        return mahd;
    }

    public void setMahd(String mahd) {
        this.mahd = mahd;
    }

    public String getNgayGiao() {
        return ngayGiao;
    }

    public void setNgayGiao(String ngayGiao) {
        this.ngayGiao = ngayGiao;
    }

    public String getDiachigiao() {
        return diachigiao;
    }

    public void setDiachigiao(String diachigiao) {
        this.diachigiao = diachigiao;
    }
}
