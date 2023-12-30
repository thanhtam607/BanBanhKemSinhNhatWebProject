package vn.edu.hcmuaf.fit.model;

public class Discount {
    int id;
    String idProduct;
    double discount;
    String startDate;
    String expiryDate;
    public Discount(String idProduct, double discount, String startDate, String expiryDate){
        this.idProduct = idProduct;
        this.discount = discount;
        this.startDate = startDate;
        this.expiryDate = expiryDate;
    }
    public Discount(int id, String idProduct, double discount, String startDate, String expiryDate){
        this.id=id;
        this.idProduct = idProduct;
        this.discount = discount;
        this.startDate = startDate;
        this.expiryDate = expiryDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    public String getIdProduct() {
        return idProduct;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getDiscount() {
        return discount;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }
    public String getPercentage(){
        return (this.discount*100) +"%";
    }
}
