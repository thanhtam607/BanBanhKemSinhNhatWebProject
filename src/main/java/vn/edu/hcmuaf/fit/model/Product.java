package vn.edu.hcmuaf.fit.model;

import java.text.DecimalFormat;
import java.util.List;

public class Product {
    String id;
    String name;
    String type;
    String size;
    int weight;
    String description;
    String introduction;
    List<Image> listImg;
    int price;
    List<Comment> comments;
    ProductDetail details;
    int status;
    Discount discount;


    public Product(String id, String name, String type, String size, int weight, String description, String introduction, List<Image> listImg, int price, List<Comment> comments,  ProductDetail details) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.size= size;
        this.weight = weight;
        this.description = description;
        this.introduction=introduction;
        this.listImg = listImg;
        this.price = price;
        this.comments = comments;
        this.details =details;
        this.discount=null;
        this.status=0;
    }
    public Product(String id, String name, String type, String size, int weight, String description, String introduction, List<Image> listImg, int price){
        this.id=id;
        this.name = name;
        this.type = type;
        this.size= size;
        this.weight = weight;
        this.description = description;
        this.introduction=introduction;
        this.listImg = listImg;
        this.price = price;
        this.status =0;
        this.discount = null;
    }
    public Product(String id, String name, String type, String size, int weight, String description, String introduction, List<Image> listImg, int price, List<Comment> comments, ProductDetail details, int status) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.size= size;
        this.weight = weight;
        this.description = description;
        this.introduction=introduction;
        this.listImg = listImg;
        this.price = price;
        this.comments = comments;
        this.details =details;
        this.status = status;
        this.discount=null;
    }

    public void settype(String type) {
        this.type = type;
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

    public List<Image> getListImg() {
        return listImg;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getWeight() {
        return weight;
    }


    public String getType() {
        return type;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getDescription() {
        return description;
    }

    public String getIntroduction() {
        return introduction;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public ProductDetail getDetail() {
        return details;
    }

    public void setListpd(ProductDetail details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", size='" + size + '\'' +
                ", weight='" + weight + '\'' +
                ", description='" + description + '\'' +
                ", introduction='" + introduction + '\'' +
                ", listImg=" + listImg +
                ", price=" + price +
                ", comments=" + comments +
                '}';
    }
    public void setHide(boolean hide){
        if(hide){
            this.status = 1;
        }
        else{
            this.status=0;
        }
    }
    public boolean isHide(){
        if(this.status==1){
            return true;
        }
        return false;
    }
    public boolean delete(){
        if(this.status==-1){
            return true;
        }
        return false;
    }

    public String getStatus(){
        if(getDetail().getInventory()>1){
            return "Còn hàng";
        }
        else{
            return "Hết hàng";
        }
    }
    public boolean isExistNumber(int slg){
        if(getDetail().getInventory() > slg) return true;
            return false;
    }

    public static String formatNum(int price){
        DecimalFormat df = new DecimalFormat("###,###,###");
        return df.format(price);

    }
    public int countImgs(){
        int res=0;
        for(Image img : getListImg()){
            if(!img.delete()){
                res++;
            }
        }
        return res;
    }
    public void setDiscount(Discount discount){
        this.discount=discount;
    }
    public Discount getDiscount(){
        return discount;
    }
    public int getPromotional(){
        if(this.discount != null){
            double promotional = this.price - (this.price*this.discount.getDiscount());
            return (int)Math.round( (1000 * promotional) / 1000);
        }
        else{
            return 0;
        }
    }

//    public static void main(String[] args) {
//
//        System.out.println(formatNum(1234567));
//    }
}