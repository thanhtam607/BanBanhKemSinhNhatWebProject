package vn.edu.hcmuaf.fit.model;

public class Image {
    String id;
    String idProduct;
    String img;
    int status;
    public Image(String id, String idProduct, String img, int status){
        this.id=id;
        this.idProduct = idProduct;
        this.img = img;
        this.status = status;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getIdProduct() {
        return idProduct;
    }

    public String getImg() {
        return img;
    }

    public boolean isHide() {
        if(this.status==1){
            return true;
        }
        return false;
    }
}
