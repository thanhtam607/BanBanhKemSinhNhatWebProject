package vn.edu.hcmuaf.fit.service;

import vn.edu.hcmuaf.fit.db.DBConnect;
import vn.edu.hcmuaf.fit.model.*;

import java.sql.*;
import java.util.*;
public class ProductService {
    static Connection con = DBConnect.getInstall().getConn();
    public static List<Product> getData()    {
        List<Product> list = new LinkedList<Product>();
        Statement statement = DBConnect.getInstall().get();
        Statement stmt = DBConnect.getInstall().get();
        Statement stmt1 = DBConnect.getInstall().get();
        Statement stmt2 = DBConnect.getInstall().get();
        ResultSet rsCmt;
        ProductDetail detail = new ProductDetail();
        if (statement != null)
            try {
                ResultSet rs = statement.executeQuery("SELECT distinct products.idProduct ,products.productName,typeOfCake.name, products.size, products.weight, products.description, products.introduction, products.price, STATUS  from products, typeOfCake where products.idType = typeOfCake.idType");
                while (rs.next()) {
                    ResultSet rsImg = stmt.executeQuery("SELECT idImg, productImgs.idProduct,productImgs.img, status from productImgs");
                    List<Image> listImg = new LinkedList<Image>();
                    rsCmt = stmt1.executeQuery("SELECT idProduct, ACCOUNTS.ACCOUNT_NAME,comment,date, IdCmt, Comments.STATUS from Comments, ACCOUNTS where ACCOUNTS.ACCOUNT_ID = Comments.ID");
                    List<Comment> listCmts = new LinkedList<Comment>();
                    ResultSet rspd = stmt2.executeQuery("select idProduct, quantity, inventory, dateOfManufacture, expirationDate from productDetails");
                    String s1 = rs.getString(1);
                    while (rsImg.next()) {
                        String s2 = rsImg.getString(2);
                        if (s1.equals(s2)) {
                            listImg.add(new Image(rsImg.getString(1), s2,rsImg.getString(3), rsImg.getInt(4)));
                        }
                    }

                    while (rsCmt.next()) {
                        String s2 = rsCmt.getString(1);
                        int status = rsCmt.getInt(6);
                        if (s1.equals(s2) && status==0) {
                            listCmts.add(new Comment(rsCmt.getString(1), rsCmt.getString(2), rsCmt.getString(3), rsCmt.getString(4), rsCmt.getInt(5), rsCmt.getInt(6)));
                        }
                    }
                    while (rspd.next()) {
                        String s2 = rspd.getString(1);
                        if (s1.equals(s2)) {
                            detail =new ProductDetail(rspd.getString(1), rspd.getInt(2), rspd.getInt(3), rspd.getString(4), rspd.getString(5));
                        }
                    }
                    Product p = new Product(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6), rs.getString(7), listImg, rs.getInt(8),listCmts, detail, rs.getInt(9));
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

    public static Product findById(String id) {
        Product p = null;
        try {
            PreparedStatement ps = con.prepareStatement("SELECT distinct products.idProduct ,products.productName,typeOfCake.name, products.size, products.weight, products.description, products.introduction, products.price, STATUS  from products, typeOfCake where products.idType = typeOfCake.idType and products.idProduct = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                p = new Product(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6), rs.getString(7), findImagesByIdProduct(rs.getString(1)), rs.getInt(8), findCommentsByIdProduct(rs.getString(1)), findPDetailByIdProduct(rs.getString(1)), rs.getInt(9));
                p.setDiscount(DiscountService.findByIdProduct(id));
                }
            }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return p;
    }
    public static List<Product> getHotProduct() {
        List<Product> list = new ArrayList<>();
        Statement statement = DBConnect.getInstall().get();
        if (statement != null)
            try {
                ResultSet rs = statement.executeQuery("SELECT products.idProduct,products.STATUS ,sum(BILL_DETAIL.AMOUNT)as total FROM products, BILL_DETAIL WHERE products.idProduct = BILL_DETAIL.idProduct GROUP BY products.idProduct ORDER BY total DESC;");
                while (rs.next()) {
                    int status = rs.getInt(2);
                    if(status==0) {
                        Product p = findById(rs.getString(1)) ;
                        list.add(p);
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        else {
            System.out.println("Không có sản phẩm");
        }

        return list;
    }

    public static List<Product> getNewProduct(List<Product> list ) {
        Collections.sort(list, new IDComparator());
        return list;
    }
    public static void addComment(Comment cmt, String IDUser) {
        Statement statement = DBConnect.getInstall().get();

        String sql = "insert into Comments(idProduct, ID,comment, date, STATUS) values('" + cmt.getIdProduct() + "', '" + IDUser + "', '" + cmt.getBinhLuan() + "', '" + cmt.getDate() + " '," + cmt.getStatus() +");";
        try {
            statement.executeUpdate(sql);

        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    public static List<Product> findBySize(String size,List<Product> list) {
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
//        0 15 30 45 60 75 90
        return result;
    }

    public static List<TypeOfCake> getListType() throws SQLException {
        List<TypeOfCake> res = new ArrayList<>();
        Statement stm = DBConnect.getInstall().get();
        ResultSet rs = stm.executeQuery("SELECT idType, name FROM typeOfCake;");
        while (rs.next()) {
            res.add(new TypeOfCake(rs.getString(1), rs.getString(2)));
        }
        return res;
    }

    public static List<Product> findByType(String type,List<Product> list) {
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
        Statement stm = DBConnect.getInstall().get();
        String sql = "select idProduct, STATUS FROM products where productName like \"%" + key + "%\"; ";
        try {
            ResultSet rs = stm.executeQuery(sql);
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
        try{
            PreparedStatement ps = con.prepareStatement("select idProduct FROM products where price BETWEEN ? and ?;");
            ps.setInt(1,pricemin);
            ps.setInt(2, pricemax);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                listId.add(rs.getString(1));
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for (Product p : listP) {
            if (listId.contains(p.getId())) {
                res.add(p);
            }
        }
        return res;
    }
    public static void updateProduct(String idProduct,String idType, String productName, String size, int weight, String description, String introduction, int price ){
        Statement statement = DBConnect.getInstall().get();
        String sql = "UPDATE products set  idType='" +idType+ "', productName= '"+ productName+ "', size= '" + size+ "', weight= "+ weight+", description = '"+ description + "', introduction= '"+ introduction+"', price= "+price+" where idProduct = '"+idProduct+"';";

        try {
            statement.executeUpdate(sql);

        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
    public static void updateDetail(ProductDetail pDetail){
        Statement statement = DBConnect.getInstall().get();
        String sql = "UPDATE productDetails set  quantity= "+ pDetail.getQuantity()+ ", inventory= " + pDetail.getInventory()+ ", dateOfManufacture = '"+ pDetail.getMfg() + "', expirationDate= '"+ pDetail.getOod()+"' where idProduct = '"+pDetail.getId()+"';";
        try {
            statement.executeUpdate(sql);

        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
    public static void deleteCommemt(String id){
        Statement statement = DBConnect.getInstall().get();
        String sql= "UPDATE comments set STATUS = -1 WHERE IdCmt="+ id+";";
        try {
            statement.executeUpdate(sql);

        } catch (SQLException se) {
            se.printStackTrace();
        }

    }
    public static void deleteImage(String img){
        Statement statement = DBConnect.getInstall().get();
        String sql= "UPDATE productImgs set status = -1 where img = '"+img+"';";
        try {
            statement.executeUpdate(sql);

        } catch (SQLException se) {
            se.printStackTrace();
        }
        System.out.println(sql);
    }
    public static void upProductImg(String oldImg, String newImg){
        Statement statement = DBConnect.getInstall().get();
        String sql = "UPDATE productImgs set  img= '"+ newImg+ "' where img = '"+oldImg+"';";
        try {
            statement.executeUpdate(sql);
        } catch (SQLException se) {
            se.printStackTrace();
        }

    }
    public static void addProDuct(Product p){
        Statement statement = DBConnect.getInstall().get();
        String sql = "insert into products values('" + p.getId() + "', '" + p.getType() + "', '" + p.getName() + "', '" + p.getSize() + "',"
                + p.getWeight()+",'"+ p.getDescription() + "', '"+ p.getIntroduction()+"',"+ p.getPrice()+",0);";
        String sql1 = "insert into productDetails(idProduct) values('"+ p.getId()+"');" ;
        try {
            statement.executeUpdate(sql);
            statement.executeUpdate(sql1);
        } catch (SQLException se) {
            se.printStackTrace();
        }
        addImg(p);
    }
    public static  void addImg(Product p){
        Statement statement2 = DBConnect.getInstall().get();
        String sql="";
        for(Image img : p.getListImg()){

            sql = "insert into productImgs values( '"+ img.getId()+"', '"+ p.getId()+"', '"+ img.getImg()+"', 0);";
            try {
                statement2.executeUpdate(sql);
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
    public static void addImgForPro(String idProduct, String img){
        Product p = ProductService.findById(idProduct);
        Statement statement= DBConnect.getInstall().get();
        String idImg = "ASP"+p.getId().substring(1)+"-"+(p.getListImg().size()+1);
        String sql = "insert into productImgs values( '"+ idImg+"', '"+ p.getId()+"', '"+ img+"',0);";

        try {
            statement.executeUpdate(sql);
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
    public static  void removeProduct(String id){
        Statement statement = DBConnect.getInstall().get();
        String sql = "UPDATE products set STATUS = -1 where idProduct = '"+id+"';";
        try {
            statement.executeUpdate(sql);
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
    public static String getMaxId(){
        String res ="";
        String sql= "SELECT max(idProduct) from products ";
        Statement statement = DBConnect.getInstall().get();
        try {
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                res = rs.getString(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String s = "B" + (Integer.parseInt(res.substring(1))+1);
        return s;
    }
    public static List<String> getListSize(){
        Statement statement = DBConnect.getInstall().get();
        List<String> res = new ArrayList<>();

        try {
            ResultSet rs = statement.executeQuery("select distinct size from products");
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
        for(Product p : getData()){
            if(p.delete()){
                list.add(p);
            }
        }
        return list;
    }
    public static  void restoreProduct(String id){
        Statement statement = DBConnect.getInstall().get();
        String sql = "UPDATE products set STATUS = 0 where idProduct = '"+id+"';";
        try {
            statement.executeUpdate(sql);
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
    public static  void hideProduct(String id){
        Statement statement = DBConnect.getInstall().get();
        String sql = "UPDATE products set STATUS = 1 where idProduct = '"+id+"';";
        try {
            statement.executeUpdate(sql);
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
    public static  void UnHidenProduct(String id){
        Statement statement = DBConnect.getInstall().get();
        String sql = "UPDATE products set STATUS = 0 where idProduct = '"+id+"';";
        try {
            statement.executeUpdate(sql);
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
    public static List<Product> getListProduct(){
        List<Product> list = new ArrayList<>();
        Statement statement = DBConnect.getInstall().get();
        List<Image> listImg ;
        List<Comment> listCmts ;
        ProductDetail detail ;
        if (statement != null)
            try {
                ResultSet rs = statement.executeQuery("SELECT distinct products.idProduct ,products.productName,typeOfCake.name, products.size, products.weight, products.description, products.introduction, products.price, STATUS  from products, typeOfCake where products.idType = typeOfCake.idType and products.STATUS=0");
                while (rs.next()) {
                    String idProduct = rs.getString(1);
                    detail = findPDetailByIdProduct(idProduct);
                    listCmts = findCommentsByIdProduct(idProduct);
                    listImg = findImagesByIdProduct(idProduct);
                    Product p = new Product(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6), rs.getString(7), listImg, rs.getInt(8),listCmts, detail, rs.getInt(9));
                    p.setDiscount(DiscountService.findByIdProduct(rs.getString(1)));
                    list.add(p);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        return list;
    }
    public static List<Product> getListProductForAdmin(){
        List<Product> res = new ArrayList<>();
        for(Product p: ProductService.getData()){
            if(!p.delete()){
                res.add(p);
            }
        }
        return res;
    }
    public static  void hideImg(String img){
        Statement statement = DBConnect.getInstall().get();
        String sql = "UPDATE productImgs set STATUS = 1 where img = '"+img+"';";
        try {
            statement.executeUpdate(sql);
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
    public static  void UnHidenImg(String img){
        Statement statement = DBConnect.getInstall().get();
        String sql = "UPDATE productImgs set STATUS = 0 where img = '"+img+"';";
        try {
            statement.executeUpdate(sql);
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    public static List<Product> getDiscountProduct() {
        List<Product> res = new ArrayList<Product>();
        for (Discount d: DiscountService.getListDiscount()) {
            Product p = findById(d.getIdProduct());
            p.setDiscount(d);
            res.add(p);
        }
        return res;
    }
    public static List<Product> getProductsNotDiscount() {
        List<Product> res = new ArrayList<Product>();
        List<String> discounts = new ArrayList<>();
        for(Product product: getDiscountProduct()){
            discounts.add(product.getId());
        }
        for (Product p : getListProductForAdmin()) {
            if(!discounts.contains(p.getId())){ res.add(p);}
        }
        return res;
    }
    public static List<Comment> findCommentsByIdProduct(String idProduct){
        List<Comment> list = new ArrayList<>();
        try{
        PreparedStatement stm = con.prepareStatement("SELECT idProduct, ACCOUNTS.ACCOUNT_NAME,comment,date, IdCmt, Comments.STATUS from Comments, ACCOUNTS where ACCOUNTS.ACCOUNT_ID = Comments.ID and idProduct=?");
        stm.setString(1,idProduct);
        ResultSet rsCmt = stm.executeQuery();
        while(rsCmt.next()){
            list.add(new Comment(rsCmt.getString(1), rsCmt.getString(2), rsCmt.getString(3), rsCmt.getString(4), rsCmt.getInt(5), rsCmt.getInt(6)));
        }
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
        return list;
    }
    public static List<Image> findImagesByIdProduct(String idProduct){
        List<Image> list = new ArrayList<>();
        try {
            PreparedStatement stm = con.prepareStatement("SELECT idImg, productImgs.idProduct,productImgs.img, status from productImgs where idProduct=?");
            stm.setString(1, idProduct);
            ResultSet rsImg = stm.executeQuery();
            while (rsImg.next()) {
                list.add(new Image(rsImg.getString(1), rsImg.getString(2), rsImg.getString(3), rsImg.getInt(4)));
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    public static ProductDetail findPDetailByIdProduct(String idProduct){
        ProductDetail detail= new ProductDetail();
        try{
            PreparedStatement stm = con.prepareStatement("select idProduct, quantity, inventory, dateOfManufacture, expirationDate from productDetails where idProduct=?");
            stm.setString(1, idProduct);
            ResultSet rs= stm.executeQuery();
            while (rs.next()) {
                detail = new ProductDetail(rs.getString(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5));
            }
            }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return detail;
    }

    public static String idMaxType(){
        String res ="";
        String sql= "SELECT max(idType) from typeofcake";
        Statement statement = DBConnect.getInstall().get();
        try {
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                res = rs.getString(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String s = "";
        int n = Integer.parseInt(res.substring(2));
        if(n < 9) {
            s  = "LB0" + (n + 1);
        } else {
            s = "LB" + (n + 1);
        }
        return s;
    }
    public static void addTypePro(String id, String type){
        Statement stm = DBConnect.getInstall().get();
        String sql = "INSERT into typeofcake VALUES('" + id + "', '"+ type+"')";
        try {
            stm.executeUpdate(sql);
        } catch (SQLException se) {
            se.printStackTrace();
        }

    }
    public static void updateType(String id, String newtype){
        Statement stm = DBConnect.getInstall().get();
        String sql = "UPDATE typeofcake SET name = '" + newtype + "' WHERE idType = '" + id +"'";
        try {
            stm.executeUpdate(sql);

        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
    public  static void deleteType(String id){
        Statement stm = DBConnect.getInstall().get();
        String sql = "DELETE FROM typeofcake WHERE idType = '" + id + "'";
        try {
            stm.executeUpdate(sql);

        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
    public static void addTyofcake(TypeOfCake toc){
        Statement stm = DBConnect.getInstall().get();
        String sql = "INSERT INTO typeofcake VALUES('" + toc.getIdType() + "', '" + toc.getName() + "')";
        try {
            stm.executeUpdate(sql);
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }


}
class IDComparator implements Comparator<Product> {
    public int compare(Product p1, Product p2) {
        return p2.getId().compareTo(p1.getName());
    }
}

