package vn.edu.hcmuaf.fit.model;

public class Log {
    int id;
    int level;
    String user;
    String src;
    String content;
    String createAt;
    int status;
    public Log(){};
    public Log(int id, int level, String user, String content, String createAt, int status){
        this.id=id;
        this.level=level;
        this.user=user;
        this.content=content;
        this.createAt=createAt;
        this.status=status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
    public void setUser(String user) {
        this.user = user;
    }
    public String getUser() {
        return user;
    }
    public int getLevel() {
        return level;
    }
    public int getStatus() {
        return status;
    }
    public String getContent() {
        return content;
    }
    public String getCreateAt() {
        return createAt;
    }
    public String getSrc() {
        return src;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }
    public void setLevel(int level) {
        this.level = level;
    }
    public void setSrc(String src) {
        this.src = src;
    }
    public void setStatus(int status) {
        this.status = status;
    }
}
