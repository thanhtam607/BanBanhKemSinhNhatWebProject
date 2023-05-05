package vn.edu.hcmuaf.fit.model;

import java.text.NumberFormat;

public class ItemProductInCart {
    int id;
    private String customer_id;
    private int soLgMua;
    private Product sp;
    private Order order;
    private String note;
    public ItemProductInCart() {
    }

    public ItemProductInCart(int id,String customer_id, int soLgMua, Product sp, Order order) {
        this.id = id;
        this.customer_id=customer_id;
        this.soLgMua = soLgMua;
        this.sp = sp;
        this.order = order;
    }
    public ItemProductInCart(int id, String customer_id, Product sp,int soLgMua) {
        this.id = id;
        this.customer_id = customer_id;
        this.soLgMua = soLgMua;
        this.sp = sp;
        this.order = new Order();
    }
    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
    public Order getOrder() {
        return order;
    }
    public void setOrder(Order order) {
        this.order = order;
    }
    public String getCustomer_id() {
        return customer_id;
    }
    public void setCustomer_id(String idUser) {
        this.customer_id = customer_id;
    }
    public int getSoLgMua() {
        return soLgMua;
    }
    public void setSoLgMua(int soLgMua) {
        this.soLgMua = soLgMua;
    }
    public Product getSp() {
        return sp;
    }
    public void setSp(Product sp) {
        this.sp = sp;
    }
    public double getPrice() {
        double price = 0;
        if(getSp().getPromotional()!=0){
            price = getSp().getPromotional()*getSoLgMua();
        }
        else{
            price = getSp().getPrice()*getSoLgMua();
        }
        return price;
    }


    @Override
    public String toString() {
        return "ItemProductInCart{" +
                "customer_id ='" + customer_id + '\'' +
                ", soLgMua=" + soLgMua +
                ", sp=" + sp +

                ", order=" + order +
                '}';
    }
    public static String formatNum(double num){
        NumberFormat vn = NumberFormat.getInstance();
        String result = vn.format(num);
        return result;
    }
    public void setNote(String s){
        this.note = s;
    }
    public String getNote(){
        return this.note;
    }
}
