package vn.edu.hcmuaf.fit.model;

public class ProductDetails {
    String id;
    int quantity;
    int inventory;
    String mfg;
    String ood;

    public ProductDetails(String id, int quantity, int inventory, String mfg, String ood) {
        this.id = id;
        this.quantity = quantity;
        this.inventory = inventory;
        this.mfg = mfg;
        this.ood = ood;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    public String getMfg() {
        return mfg;
    }

    public void setMfg(String mfg) {
        this.mfg = mfg;
    }

    public String getOod() {
        return ood;
    }

    public void setOod(String ood) {
        this.ood = ood;
    }
}
