package vn.edu.hcmuaf.fit.service;

import vn.edu.hcmuaf.fit.db.DBConnect;
import vn.edu.hcmuaf.fit.model.Product;
import vn.edu.hcmuaf.fit.model.ProductDiscount;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ProductDiscountService {
    public static List<ProductDiscount> getData() {
        List<ProductDiscount> list = new LinkedList<ProductDiscount>();
        Statement statement = DBConnect.getInstall().get();
        Statement stmt = DBConnect.getInstall().get();
        if (statement != null)
            try {
                ResultSet rs = statement.executeQuery("SELECT distinct products.idProduct ,products.productName,typeOfCake.name, products.price, sale.sale  from products, typeOfCake, sale where products.idType = typeOfCake.idType and products.idProduct = sale.idProduct");
                while (rs.next()) {
                    ResultSet rsImg = stmt.executeQuery("SELECT productImgs.idProduct,productImgs.img from productImgs");
                    List<String> listImg = new LinkedList<String>();
                    String s1 = rs.getString(1);
                    while (rsImg.next()) {
                        String s2 = rsImg.getString(1);
                        if (s1.equals(s2)) {
                            listImg.add(rsImg.getString(2));
                        }
                    }
                    ProductDiscount p = new ProductDiscount(rs.getString(1), rs.getString(3), rs.getString(2), rs.getInt(4),rs.getDouble(5), listImg);
                    list.add(p);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        else {
            System.out.println("Không có sản phẩm");
        }
        return list;
    }
    public static List<ProductDiscount> getDiscountProduct() {
        List<ProductDiscount> res = new ArrayList<ProductDiscount>();
        List<String> listId = new ArrayList<String>();
        Statement stm = DBConnect.getInstall().get();
        String sql = "SELECT idProduct from sale GROUP BY idProduct, tungay, denngay HAVING DATEDIFF(CURRENT_DATE, denngay) < 0;";
        try {
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                listId.add(rs.getString(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for (ProductDiscount p : getData()) {
            if (listId.contains(p.getId())) {
                res.add(p);
            }
        }
        return res;
    }

    public static void main(String[] args) throws SQLException {
        List<ProductDiscount> li = ProductDiscountService.getDiscountProduct();
        for(ProductDiscount p: li){
            System.out.println(p.getDiscount());
//          System.out.println(getListType().size());
        }
//        System.out.println(getLastComment("B001").getBinhLuan());
//       System.out.println(getPaginationPage(1).toString());
        // addComment(new Comment("B002", "Thanh Tâm","Bánh mềm mịn vô cùng hòa quyện với  phần kem mịn màng, vị ngọt thanh vừa ăn lại có thêm phần tiramisu khá lạ miệng khiến cho người ăn cảm thấy thích thú.","2022/12/8"), "AD02");
    }
}
