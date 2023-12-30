package vn.edu.hcmuaf.fit.model;

public class Feedback {
    int id;
    private String name;
    private String email;
    private String feedback;
    private String date;
    public Feedback(int id, String name, String email, String feedback, String date){
        this.id = id;
        this.name=name;
        this.email=email;
        this.feedback=feedback;
        this.date=date;
    }
    public Feedback( String name, String email, String feedback, String date) {
        this.name = name;
        this.email = email;
        this.feedback = feedback;
        this.date = date;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getFeedback() {
        return feedback;
    }

    public String getDate() {
        return date;
    }
}
