package vn.edu.hcmuaf.fit.model;

public class SignUser {
    private String id;
    private String id_user;
    private String pbkey;
    private String createDate;
    private String expiredDate;
    private int status;

    public SignUser(String id, String id_user, String pbkey, String createDate, String expiredDate, int status) {
        this.id = id;
        this.id_user = id_user;
        this.pbkey = pbkey;
        this.createDate = createDate;
        this.expiredDate = expiredDate;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getPbkey() {
        return pbkey;
    }

    public void setPbkey(String pbkey) {
        this.pbkey = pbkey;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getExpireDate() {
        return expiredDate;
    }

    public void setExpiredDate(String expiredDate) {
        this.expiredDate = expiredDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "SignUser{" +
                "id='" + id + '\'' +
                ", id_user='" + id_user + '\'' +
                ", pbkey='" + pbkey + '\'' +
                ", createDate='" + createDate + '\'' +
                ", expiredDate='" + expiredDate + '\'' +
                ", status=" + status +
                '}';
    }
}