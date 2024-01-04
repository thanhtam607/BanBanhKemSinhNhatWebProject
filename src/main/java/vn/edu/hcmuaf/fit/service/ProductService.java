package vn.edu.hcmuaf.fit.service;

import vn.edu.hcmuaf.fit.db.DBConnect;
import vn.edu.hcmuaf.fit.model.*;

import java.sql.*;
import java.util.*;

public class ProductService {
    static Connection con = DBConnect.getInstall().getConn();
    static Statement state = DBConnect.getInstall().get();

    public static List<Product> getData() {
        List<Product> list = new LinkedList<>();

        if (con != null) {
            try {
                String query = "SELECT DISTINCT products.idProduct, products.productName, TYPEOFCAKE.name,\n" +
                        "                    products.size, products.weight, products.description, products.introduction,\n" +
                        "                    products.price, products.STATUS \n" +
                        "                    FROM products, TYPEOFCAKE\n" +
                        "                    WHERE products.idType = TYPEOFCAKE.idType";

                try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
                    ResultSet rs = preparedStatement.executeQuery();
                    while (rs.next()) {
                        String productId = rs.getString(1);
                        List<Image> listImg = getImagesForProduct( productId);
                        List<Comment> listCmts = getCommentsForProduct( productId);
                        ProductDetail detail = getProductDetail( productId);

                        Product p = new Product(productId, rs.getString(2), rs.getString(3), rs.getString(4),
                                rs.getInt(5), rs.getString(6), rs.getString(7), listImg, rs.getInt(8), listCmts, detail, rs.getInt(9));

                        list.add(p);
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("Không có kết nối đến cơ sở dữ liệu");
        }
        return list;
    }

    private static List<Image> getImagesForProduct( String productId) throws SQLException {
        String query = "SELECT idImg, img, status FROM productImgs WHERE idProduct = ?";
        List<Image> listImg = new LinkedList<>();

        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setString(1, productId);
            ResultSet rsImg = preparedStatement.executeQuery();
            while (rsImg.next()) {
                listImg.add(new Image(rsImg.getString(1), productId, rsImg.getString(2), rsImg.getInt(3)));
            }
        }
        return listImg;
    }

    private static List<Comment> getCommentsForProduct(String productId) throws SQLException {
        String query = "SELECT ACCOUNTS.ID, ACCOUNTS.NAME, comment, date, IdCmt, Comments.STATUS " +
                "FROM Comments JOIN ACCOUNTS ON ACCOUNTS.ID = Comments.ID " +
                "WHERE idProduct = ? AND Comments.STATUS = 0";
        List<Comment> listCmts = new LinkedList<>();

        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setString(1, productId);
            ResultSet rsCmt = preparedStatement.executeQuery();
            while (rsCmt.next()) {
                listCmts.add(new Comment(rsCmt.getString(1), rsCmt.getString(2), rsCmt.getString(3),
                        rsCmt.getString(4), rsCmt.getInt(5), rsCmt.getInt(6)));
            }
        }
        return listCmts;
    }

    private static ProductDetail getProductDetail(String productId) throws SQLException {
        String query = "SELECT quantity, inventory, dateOfManufacture, expirationDate FROM productDetails WHERE idProduct = ?";
        ProductDetail detail = null;

        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setString(1, productId);
            ResultSet rspd = preparedStatement.executeQuery();
            if (rspd.next()) {
                detail = new ProductDetail(productId, rspd.getInt(1), rspd.getInt(2),
                        rspd.getString(3), rspd.getString(4));
            }
        }
        return detail;
    }

    public static Product findById(String id) {
        Product p = null;
        try {
            PreparedStatement ps = con.prepareStatement("SELECT distinct products.idProduct ,products.productName,TYPEOFCAKE.name, products.size, products.weight, products.description, products.introduction, products.price, products.STATUS  from products, TYPEOFCAKE where products.idType = TYPEOFCAKE.idType and products.idProduct = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                p = new Product(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6), rs.getString(7), findImagesByIdProduct(rs.getString(1)), rs.getInt(8), findCommentsByIdProduct(rs.getString(1)), findPDetailByIdProduct(rs.getString(1)), rs.getInt(9));
                p.setDiscount(DiscountService.findByIdProduct(id));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return p;
    }

    //    public static List<Product> getHotProduct() {
//        List<Product> list = new ArrayList<>();
//        Statement statement = DBConnect.getInstall().get();
//        if (statement != null)
//            try {
//                ResultSet rs = statement.executeQuery("SELECT products.idProduct,products.STATUS ,sum(BILL_DETAIL.AMOUNT)as total FROM products, BILL_DETAIL WHERE products.idProduct = BILL_DETAIL.idProduct GROUP BY products.idProduct ,products.STATUS ORDER BY total DESC;");
//                while (rs.next()) {
//                    int status = rs.getInt(2);
//                    if(status==0) {
//                        Product p = findById(rs.getString(1)) ;
//                        list.add(p);
//                    }
//                }
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//        else {
//            System.out.println("Không có sản phẩm");
//        }
//
//        return list;
//    }
    public static List<Product> getHotProduct() {
        List<Product> productList = new ArrayList<>();
        try (
                Statement statement = con.createStatement()) {

            if (statement != null) {
                String query = "SELECT p.idProduct, p.STATUS, SUM(bd.AMOUNT) AS total " +
                        "FROM products p " +
                        "JOIN BILL_DETAIL bd ON p.idProduct = bd.idProduct " +
                        "GROUP BY p.idProduct, p.STATUS " +
                        "ORDER BY total DESC";

                try (ResultSet rs = statement.executeQuery(query)) {
                    while (rs.next()) {
                        int status = rs.getInt("STATUS");
                        if (status == 0) {
                            Product product = findById(rs.getString("idProduct"));
                            productList.add(product);
                        }
                    }
                }
            } else {
                System.out.println("Không có kết nối đến cơ sở dữ liệu");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return productList;
    }

    public static List<Product> getNewProduct(List<Product> list) {
        Collections.sort(list, new IDComparator());
        return list;
    }

    public static void addComment(Comment cmt, String IDUser) {

        String sql = "insert into Comments(idProduct, ID,comment, date, STATUS) values('" + cmt.getIdProduct() + "', '" + IDUser + "', '" + cmt.getBinhLuan() + "', '" + cmt.getDate() + " '," + cmt.getStatus() + ");";
        try {
            state.executeUpdate(sql);

        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    public static List<Product> findBySize(String size, List<Product> list) {
        List<Product> rs = new ArrayList<>();
        for (Product p : list) {
            if (p.getSize().equals(size)) {
                rs.add(p);
            }
        }
        return rs;
    }

    public static int getToTalProduct() {
        return getData().size();
    }

    public static List<Product> getPaginationPageOwn(int page, List<Product> data) {

        List<Product> result = new ArrayList<>();
        int begin = (page - 1) * 15;
        int endList = begin + 15;
        if (begin > data.size() - 15) {
            endList = data.size();
        }
        for (int i = begin; i < endList; i++) {
            result.add(data.get(i));
        }
        return result;
    }

    public static List<TypeOfCake> getListType() throws SQLException {
        List<TypeOfCake> res = new ArrayList<>();
//        Statement stm = DBConnect.getInstall().get();
        ResultSet rs = state.executeQuery("SELECT idType, name, status FROM TYPEOFCAKE;");
        while (rs.next()) {
            res.add(new TypeOfCake(rs.getString(1), rs.getString(2), rs.getInt(3)));
        }
        return res;
    }

    public static List<Product> findByType(String type, List<Product> list) {
        List<Product> res = new ArrayList<Product>();
        for (Product p : list) {
            if (p.getType().equals(type)) {
                res.add(p);
            }
        }
        return res;
    }

    public static List<Product> findByName(String key, List<Product> listP) {
        List<Product> res = new ArrayList<Product>();
        List<String> listId = new ArrayList<String>();
//        Statement stm = DBConnect.getInstall().get();
        String sql = "select idProduct, STATUS FROM products where productName like \"%" + key + "%\"; ";
        try {
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                listId.add(rs.getString(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for (Product p : listP) {
            if (listId.contains(p.getId())) {
                res.add(p);
            }
        }
        return res;
    }

    public static List<Product> filterByPrice(int pricemin, int pricemax, List<Product> listP) {
        List<Product> res = new ArrayList<Product>();
        List<String> listId = new ArrayList<String>();
        try {
            PreparedStatement ps = con.prepareStatement("select idProduct FROM products where price BETWEEN ? and ?;");
            ps.setInt(1, pricemin);
            ps.setInt(2, pricemax);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listId.add(rs.getString(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for (Product p : listP) {
            if (listId.contains(p.getId())) {
                res.add(p);
            }
        }
        return res;
    }

    public static void updateProduct(String idProduct, String idType, String productName, String size, int weight, String description, String introduction, int price) {
//        Statement statement = DBConnect.getInstall().get();
        String sql = "UPDATE products set  idType='" + idType + "', productName= '" + productName + "', size= '" + size + "', weight= " + weight + ", description = '" + description + "', introduction= '" + introduction + "', price= " + price + " where idProduct = '" + idProduct + "';";

        try {
            state.executeUpdate(sql);

        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    public static void updateDetail(ProductDetail pDetail) {
//        Statement statement = DBConnect.getInstall().get();
        String sql = "UPDATE productDetails set  quantity= " + pDetail.getQuantity() + ", inventory= " + pDetail.getInventory() + ", dateOfManufacture = '" + pDetail.getMfg() + "', expirationDate= '" + pDetail.getOod() + "' where idProduct = '" + pDetail.getId() + "';";
        try {
            state.executeUpdate(sql);

        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    public static void deleteCommemt(String id) {
//        Statement statement = DBConnect.getInstall().get();
        String sql = "UPDATE comments set STATUS = -1 WHERE IdCmt=" + id + ";";
        try {
            state.executeUpdate(sql);

        } catch (SQLException se) {
            se.printStackTrace();
        }

    }

    public static void deleteImage(String img) {
//        Statement statement = DBConnect.getInstall().get();
        String sql = "UPDATE productImgs set status = -1 where img = '" + img + "';";
        try {
            state.executeUpdate(sql);

        } catch (SQLException se) {
            se.printStackTrace();
        }
        System.out.println(sql);
    }

    public static void upProductImg(String oldImg, String newImg) {
//        Statement statement = DBConnect.getInstall().get();
        String sql = "UPDATE productImgs set  img= '" + newImg + "' where img = '" + oldImg + "';";
        try {
            state.executeUpdate(sql);
        } catch (SQLException se) {
            se.printStackTrace();
        }

    }

    public static void addProDuct(Product p) {
//        Statement statement = DBConnect.getInstall().get();
        String sql = "insert into products values('" + p.getId() + "', '" + p.getType() + "', '" + p.getName() + "', '" + p.getSize() + "',"
                + p.getWeight() + ",'" + p.getDescription() + "', '" + p.getIntroduction() + "'," + p.getPrice() + ",0);";
        String sql1 = "insert into productDetails(idProduct) values('" + p.getId() + "');";
        try {
            state.executeUpdate(sql);
            state.executeUpdate(sql1);
        } catch (SQLException se) {
            se.printStackTrace();
        }
        addImg(p);
    }

    public static void addImg(Product p) {
//        Statement statement2 = DBConnect.getInstall().get();
        String sql = "";
        for (Image img : p.getListImg()) {

            sql = "insert into productImgs values( '" + img.getId() + "', '" + p.getId() + "', '" + img.getImg() + "', 0);";
            try {
                state.executeUpdate(sql);
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public static void addImgForPro(String idProduct, String img) {
        Product p = ProductService.findById(idProduct);
//        Statement statement= DBConnect.getInstall().get();
        String idImg = "ASP" + p.getId().substring(1) + "-" + (p.getListImg().size() + 1);
        String sql = "insert into productImgs values( '" + idImg + "', '" + p.getId() + "', '" + img + "',0);";

        try {
            state.executeUpdate(sql);
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    public static void removeProduct(String id) {
//        Statement st = DBConnect.getInstall().get();
        String sql = "UPDATE products set STATUS = -1 where idProduct = '" + id + "';";
        try {
            state.executeUpdate(sql);
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    public static String getMaxId() {
        String res = "";
        String sql = "SELECT max(idProduct) from products ";
//        Statement st = DBConnect.getInstall().get();
        try {
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                res = rs.getString(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String s = "B" + (Integer.parseInt(res.substring(1)) + 1);
        return s;
    }

    public static List<String> getListSize() {
//        Statement statement = DBConnect.getInstall().get();
        List<String> res = new ArrayList<>();

        try {
            ResultSet rs = state.executeQuery("select distinct size from products");
            while (rs.next()) {
                res.add(rs.getString(1));
            }

        } catch (SQLException se) {
            se.printStackTrace();
        }
        return res;
    }

    public static List<Product> getListProductRemove() {
        List<Product> list = new ArrayList<>();
        for (Product p : getData()) {
            if (p.delete()) {
                list.add(p);
            }
        }
        return list;
    }

    public static void restoreProduct(String id) {
//        Statement statement = DBConnect.getInstall().get();
        String sql = "UPDATE products set STATUS = 0 where idProduct = '" + id + "';";
        try {
            state.executeUpdate(sql);
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    public static void hideProduct(String id) {
//        Statement statement = DBConnect.getInstall().get();
        String sql = "UPDATE products set STATUS = 1 where idProduct = '" + id + "';";
        try {
            state.executeUpdate(sql);
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    public static void UnHidenProduct(String id) {
//        Statement statement = DBConnect.getInstall().get();
        String sql = "UPDATE products set STATUS = 0 where idProduct = '" + id + "';";
        try {
            state.executeUpdate(sql);
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    public static List<Product> getListProduct() {
        List<Product> list = new ArrayList<>();
        List<Image> listImg;
        List<Comment> listCmts;
        ProductDetail detail;
        if (state != null)
            try {
                ResultSet rs = state.executeQuery("SELECT distinct products.idProduct ,products.productName,TYPEOFCAKE.name, products.size, products.weight, products.description, products.introduction, products.price, products.STATUS  from products, TYPEOFCAKE where products.idType = TYPEOFCAKE.idType and products.STATUS=0");
                while (rs.next()) {
                    String idProduct = rs.getString(1);
                    detail = findPDetailByIdProduct(idProduct);
                    listCmts = findCommentsByIdProduct(idProduct);
                    listImg = findImagesByIdProduct(idProduct);
                    Product p = new Product(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6), rs.getString(7), listImg, rs.getInt(8), listCmts, detail, rs.getInt(9));
                    p.setDiscount(DiscountService.findByIdProduct(rs.getString(1)));
                    list.add(p);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        return list;
    }

    public static List<Product> getListProductForAdmin() {
        List<Product> res = new ArrayList<>();
        for (Product p : ProductService.getData()) {
            if (!p.delete()) {
                res.add(p);
            }
        }
        return res;
    }

    public static void hideImg(String img) {
//        Statement statement = DBConnect.getInstall().get();
        String sql = "UPDATE productImgs set STATUS = 1 where img = '" + img + "';";
        try {
            state.executeUpdate(sql);
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    public static void UnHidenImg(String img) {
//        Statement statement = DBConnect.getInstall().get();
        String sql = "UPDATE productImgs set STATUS = 0 where img = '" + img + "';";
        try {
            state.executeUpdate(sql);
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    public static List<Product> getDiscountProduct() {
        List<Product> res = new ArrayList<Product>();
        for (Discount d : DiscountService.getListDiscount()) {
            Product p = findById(d.getIdProduct());
            p.setDiscount(d);
            res.add(p);
        }
        return res;
    }

    public static List<Product> getProductsNotDiscount() {
        List<Product> res = new ArrayList<Product>();
        List<String> discounts = new ArrayList<>();
        for (Product product : getDiscountProduct()) {
            discounts.add(product.getId());
        }
        for (Product p : getListProductForAdmin()) {
            if (!discounts.contains(p.getId())) {
                res.add(p);
            }
        }
        return res;
    }

    public static List<Comment> findCommentsByIdProduct(String idProduct) {
        List<Comment> list = new ArrayList<>();
        try {
            PreparedStatement stm = con.prepareStatement("SELECT idProduct, ACCOUNTS.NAME,comment,date, IdCmt, Comments.STATUS from Comments, ACCOUNTS where ACCOUNTS.ID = Comments.ID and idProduct=?");
            stm.setString(1, idProduct);
            ResultSet rsCmt = stm.executeQuery();
            while (rsCmt.next()) {
                list.add(new Comment(rsCmt.getString(1), rsCmt.getString(2), rsCmt.getString(3), rsCmt.getString(4), rsCmt.getInt(5), rsCmt.getInt(6)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public static List<Image> findImagesByIdProduct(String idProduct) {
        List<Image> list = new ArrayList<>();
        try {
            PreparedStatement stm = con.prepareStatement("SELECT idImg, productImgs.idProduct,productImgs.img, status from productImgs where idProduct=?");
            stm.setString(1, idProduct);
            ResultSet rsImg = stm.executeQuery();
            while (rsImg.next()) {
                list.add(new Image(rsImg.getString(1), rsImg.getString(2), rsImg.getString(3), rsImg.getInt(4)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public static ProductDetail findPDetailByIdProduct(String idProduct) {
        ProductDetail detail = new ProductDetail();
        try {
            PreparedStatement stm = con.prepareStatement("select idProduct, quantity, inventory, dateOfManufacture, expirationDate from productDetails where idProduct=?");
            stm.setString(1, idProduct);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                detail = new ProductDetail(rs.getString(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return detail;
    }

    public static String idMaxType() {
        String res = "";
        String sql = "SELECT max(idType) from TYPEOFCAKE";
//        Statement statement = DBConnect.getInstall().get();
        try {
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                res = rs.getString(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String s = "";
        int n = Integer.parseInt(res.substring(2));
        if (n < 9) {
            s = "LB0" + (n + 1);
        } else {
            s = "LB" + (n + 1);
        }
        return s;
    }

    public static void addTypePro(String id, String type) {
//        Statement stm = DBConnect.getInstall().get();
        String sql = "INSERT into TYPEOFCAKE VALUES('" + id + "', '" + type + "')";
        try {
            state.executeUpdate(sql);
        } catch (SQLException se) {
            se.printStackTrace();
        }

    }

    public static void updateType(String id, String newtype) {
//        Statement stm = DBConnect.getInstall().get();
        String sql = "UPDATE TYPEOFCAKE SET name = '" + newtype + "' WHERE idType = '" + id + "'";
        try {
            state.executeUpdate(sql);

        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    public static void deleteType(String id) {
//        Statement stm = DBConnect.getInstall().get();
        String sql = "DELETE FROM TYPEOFCAKE WHERE idType = '" + id + "'";
        try {
            state.executeUpdate(sql);

        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    public static void addTyofcake(TypeOfCake toc) {
//        Statement stm = DBConnect.getInstall().get();
        String sql = "INSERT INTO TYPEOFCAKE VALUES('" + toc.getIdType() + "', '" + toc.getName() + "')";
        try {
            state.executeUpdate(sql);
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }


    public static void main(String[] args) {
    }

}

class IDComparator implements Comparator<Product> {
    public int compare(Product p1, Product p2) {
        return p2.getId().compareTo(p1.getName());
    }
}

