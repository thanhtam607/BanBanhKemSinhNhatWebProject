package vn.edu.hcmuaf.fit.model;

import java.util.Collection;
import java.util.HashMap;

public class Cart {
    private HashMap<String, Product> data;
    public Cart() {

    }
    public Cart(HashMap<String, Product> data) {
        this.data = data;
    }

    public Product get(String id) {
        return data.get(id);
    }

    public HashMap<String, Product> getData() {
        return data;
    }

    public void setData(HashMap<String, Product> data) {
        this.data = data;
    }

    public int put(Product item) {
        if (data.containsKey(item.getId())) {
            data.get(item.getId()).quantityUp();
        } else {
            data.put(item.getId(), item);
        }
        return data.get(item.getId()).getSolgMua();
    }

    public int put(String id, int solgMua) {
        if (data.containsKey(id)) {
            data.get(id).quantityUp(solgMua);
        }
        return data.get(id).getSolgMua();
    }

    public boolean remove(String id) {
        return data.remove(id) == null;
    }

    public double totalMoney() {
        double total = 0;
        for (Product p : data.values()) {
            total += (p.getPrice() * p.getSolgMua());
        }
        return total;
    }
    public Collection<Product> list(){
        return data.values();
    }

}
