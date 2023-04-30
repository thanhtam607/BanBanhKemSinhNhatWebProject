package vn.edu.hcmuaf.fit.model.logistic;

public class LogisticUser {
    private String id, name, email, emailVerifyAt, createAt, updateAt;

    public LogisticUser(String id, String name, String email, String emailVerifyAt, String createAt, String updateAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.emailVerifyAt = emailVerifyAt;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailVerifyAt() {
        return emailVerifyAt;
    }

    public void setEmailVerifyAt(String emailVerifyAt) {
        this.emailVerifyAt = emailVerifyAt;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }

    @Override
    public String toString() {
        return "LogisticUser{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", emailVerifyAt='" + emailVerifyAt + '\'' +
                ", createAt='" + createAt + '\'' +
                ", updateAt='" + updateAt + '\'' +
                '}';
    }
}
