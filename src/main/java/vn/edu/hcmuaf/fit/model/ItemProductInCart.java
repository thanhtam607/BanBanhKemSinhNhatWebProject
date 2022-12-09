package vn.edu.hcmuaf.fit.model;

public class ItemProductInCart {
    private String masp;
    private int soLgMua;
    private Product sp;
    private double price;
    private Order order;

    public ItemProductInCart() {
    }

    public ItemProductInCart(String masp, int soLgMua, Product sp, double price, Order order) {
        this.masp = masp;
        this.soLgMua = soLgMua;
        this.sp = sp;
        this.price = price;
        this.order = order;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getMasp() {
        return masp;
    }

    public void setMasp(String masp) {
        this.masp = masp;
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
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void quantityUp() {
        this.soLgMua++;
    }
    public void quantityUp(int solg) {
        setSoLgMua(this.soLgMua + solg);
    }
    public double giaSanPhamTrongGioHang(){
        double cost = 0;
        cost = this.getSp().getPrice() * this.getSoLgMua();
        return cost;
    }

    @Override
    public String toString() {
        return "ItemProductInCart{" +
                "masp='" + masp + '\'' +
                ", soLgMua=" + soLgMua +
                ", sp=" + sp +
                ", price=" + price +
                ", order=" + order +
                '}';
    }
}
