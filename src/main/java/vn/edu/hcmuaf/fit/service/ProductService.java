package vn.edu.hcmuaf.fit.service;

import vn.edu.hcmuaf.fit.db.DBConnect;
import vn.edu.hcmuaf.fit.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ProductService {
    public static List<Product> getData() {
        List<Product> list = new LinkedList<Product>();
        Statement statement = DBConnect.getInstall().get();
        Statement stmt = DBConnect.getInstall().get();
        Statement stmt1 = DBConnect.getInstall().get();
        Statement stmt2 = DBConnect.getInstall().get();
        ResultSet rsCmt;
        ProductDetails detail = new ProductDetails();
        if (statement != null)
            try {
                ResultSet rs = statement.executeQuery("SELECT distinct products.idProduct ,products.productName,typeOfCake.name, products.size, products.weight, products.description, products.introduction, products.price  from products, typeOfCake, sale where products.idType = typeOfCake.idType");
                while (rs.next()) {
                    ResultSet rsImg = stmt.executeQuery("SELECT productImgs.idProduct,productImgs.img from productImgs");
                    List<String> listImg = new LinkedList<String>();
                    rsCmt = stmt1.executeQuery("SELECT idProduct, TAIKHOAN.TENTK,BinhLuan,NgayBL, IdCmt from Comments, TAIKHOAN where TAIKHOAN.ID = Comments.ID");
                    List<Comment> listCmts = new LinkedList<Comment>();
                    ResultSet rspd = stmt2.executeQuery("select idProduct, quantity, inventory, dateOfManufacture, expirationDate from productDetails");
                    String s1 = rs.getString(1);
                    while (rsImg.next()) {
                        String s2 = rsImg.getString(1);
                        if (s1.equals(s2)) {
                            listImg.add(rsImg.getString(2));
                        }
                    }

                    while (rsCmt.next()) {
                        String s2 = rsCmt.getString(1);
                        if (s1.equals(s2)) {
                            listCmts.add(new Comment(rsCmt.getString(1), rsCmt.getString(2), rsCmt.getString(3), rsCmt.getString(4), rsCmt.getInt(5)));
                        }
                    }
                    while (rspd.next()) {
                        String s2 = rspd.getString(1);
                        if (s1.equals(s2)) {
                            detail =new ProductDetails(rspd.getString(1), rspd.getInt(2), rspd.getInt(3), rspd.getString(4), rspd.getString(5));
                        }
                    }
                    Product p = new Product(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6), rs.getString(7), listImg, rs.getInt(8), listCmts, detail);
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
        List<Product> list = getData();
        for (Product p : list) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        return null;
    }

    public static List<Product> getHotProduct() {
        List<Product> list = new LinkedList<Product>();
        Statement statement = DBConnect.getInstall().get();
        Statement stmt = DBConnect.getInstall().get();
        Statement stmt1 = DBConnect.getInstall().get();
        Statement stmt2 = DBConnect.getInstall().get();
        if (statement != null)
            try {
                ResultSet rs = statement.executeQuery("SELECT products.idProduct, products.productName, products.idType, products.size, products.weight, products.description, products.introduction, products.price FROM products, cthd WHERE products.idProduct = cthd.idProduct GROUP BY idProduct ORDER BY COUNT(CTHD.idProduct) DESC, cthd.SL DESC;");
                while (rs.next()) {
                    ResultSet rsImg = stmt.executeQuery("SELECT productImgs.idProduct,productImgs.img from productImgs");
                    List<String> listImg = new LinkedList<String>();

                    ResultSet rsCmt = stmt1.executeQuery("SELECT idProduct, TAIKHOAN.TENTK,BinhLuan,NgayBL,IdCmt from Comments, TAIKHOAN where TAIKHOAN.ID = Comments.ID");
                    List<Comment> listCmts = new LinkedList<Comment>();
                    ResultSet rspd = stmt2.executeQuery("select idProduct, quantity, inventory, dateOfManufacture, expirationDate from productDetails");
                    ProductDetails details = new ProductDetails();
                    String s1 = rs.getString(1);
                    while (rsImg.next()) {
                        String s2 = rsImg.getString(1);
                        if (s1.equals(s2)) {
                            listImg.add(rsImg.getString(2));
                        }
                    }
                    while (rsCmt.next()) {
                        String s2 = rsCmt.getString(1);
                        if (s1.equals(s2)) {
                            listCmts.add(new Comment(rsCmt.getString(1), rsCmt.getString(2), rsCmt.getString(3), rsCmt.getString(4), rsCmt.getInt(5)));
                        }
                    }
                    while (rspd.next()) {
                        String s2 = rspd.getString(1);
                        if (s1.equals(s2)) {
                            details = new ProductDetails(rspd.getString(1), rspd.getInt(2), rspd.getInt(3), rspd.getString(4), rspd.getString(5));
                        }
                    }
                    Product p = new Product(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6), rs.getString(7), listImg, rs.getInt(8), listCmts, details);
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

    public static List<Product> getNewProduct() {
        List<Product> list = new LinkedList<Product>();
        Statement statement = DBConnect.getInstall().get();
        Statement stmt = DBConnect.getInstall().get();
        Statement stmt1 = DBConnect.getInstall().get();
        Statement stmt2 = DBConnect.getInstall().get();
        if (statement != null)
            try {
                ResultSet rs = statement.executeQuery("SELECT products.idProduct, products.productName, products.idType, products.size, products.weight, products.description, products.introduction, products.price FROM products ORDER BY idProduct DESC ;");
                while (rs.next()) {
                    ResultSet rsImg = stmt.executeQuery("SELECT productImgs.idProduct,productImgs.img from productImgs");
                    List<String> listImg = new LinkedList<String>();
                    List<String> listkt = new LinkedList<>();
                    ResultSet rsCmt = stmt1.executeQuery("SELECT idProduct, TAIKHOAN.TENTK,BinhLuan,NgayBL, IdCmt from Comments, TAIKHOAN where TAIKHOAN.ID = Comments.ID");
                    List<Comment> listCmts = new LinkedList<Comment>();
                    ResultSet rspd = stmt2.executeQuery("select idProduct, quantity, inventory, dateOfManufacture, expirationDate from productDetails");
                    ProductDetails details = new ProductDetails();
                    String s1 = rs.getString(1);
                    while (rsImg.next()) {
                        String s2 = rsImg.getString(1);
                        if (s1.equals(s2)) {
                            listImg.add(rsImg.getString(2));
                        }
                    }
                    while (rsCmt.next()) {
                        String s2 = rsCmt.getString(1);
                        if (s1.equals(s2)) {
                            listCmts.add(new Comment(rsCmt.getString(1), rsCmt.getString(2), rsCmt.getString(3), rsCmt.getString(4), rsCmt.getInt(5)));
                        }
                    }
                    while (rspd.next()) {
                        String s2 = rspd.getString(1);
                        if (s1.equals(s2)) {
                            details =new ProductDetails(rspd.getString(1), rspd.getInt(2), rspd.getInt(3), rspd.getString(4), rspd.getString(5));
                        }
                    }
                    Product p = new Product(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6), rs.getString(7), listImg, rs.getInt(8), listCmts, details);
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
    public static void addComment(Comment cmt, String IDUser) {
        Statement statement = DBConnect.getInstall().get();

        String sql = "insert into Comments(idProduct, ID,BinhLuan, NgayBL) values('" + cmt.getIdProduct() + "', '" + IDUser + "', '" + cmt.getBinhLuan() + "', '" + cmt.getDate() + "');";
        try {
            statement.executeUpdate(sql);

        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    public static List<Product> findBySize(String size) {
        List<Product> list = getData();
        List<Product> rs = new LinkedList<>();
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

    public static List<Product> findByType(String type) {
        List<Product> res = new ArrayList<Product>();
        for (Product p : getData()) {
            if (p.getType().equals(type)) {
                res.add(p);
            }
        }
        return res;
    }

    public static List<Product> findByName(String key) {
        List<Product> res = new ArrayList<Product>();
        List<String> listId = new ArrayList<String>();
        Statement stm = DBConnect.getInstall().get();
        String sql = "select idProduct FROM products where productName like \"%" + key + "%\"; ";
        try {
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                listId.add(rs.getString(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for (Product p : getData()) {
            if (listId.contains(p.getId())) {
                res.add(p);
            }
        }
        return res;
    }

    public static List<Product> filterByPrice(int pricemin, int pricemax) {
        List<Product> res = new ArrayList<Product>();
        List<String> listId = new ArrayList<String>();
        Statement stm = DBConnect.getInstall().get();
        String sql = "select idProduct FROM products where price BETWEEN " + pricemin +  " AND  " + pricemax;
        try {
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                listId.add(rs.getString(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for (Product p : getData()) {
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
    public static void updateDetail(ProductDetails pDetail){
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
        String sql= "DELETE FROM comments WHERE IdCmt="+ id+";";
        try {
            statement.executeUpdate(sql);

        } catch (SQLException se) {
            se.printStackTrace();
        }

    }
    public static void deleteImange(String img){
        Statement statement = DBConnect.getInstall().get();
        String sql= "DELETE FROM productImgs WHERE img='"+ img+"';";

        try {
            statement.executeUpdate(sql);

        } catch (SQLException se) {
            se.printStackTrace();
        }

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
        String idImg;
        String sql;
        for(int i = 0; i< p.getListImg().size();i++){
            idImg = "ASP"+p.getId().substring(1)+"-"+(i+1);
            sql = "insert into productImgs values( '"+ idImg+"', '"+ p.getId()+"', '"+ p.getListImg().get(i)+"', 0);";
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
        String sql1= "DELETE FROM comments WHERE idProduct='"+ id+"';";
        String sql= "DELETE FROM productImgs WHERE idProduct='"+ id+"';";
        String sql2= "DELETE FROM products WHERE idProduct='"+ id+"';";
        String sql3= "DELETE FROM productDetails WHERE idProduct='"+ id+"';";
        removeSale(id);
        removeInfoOder(id);
        try {
            statement.executeUpdate(sql);
            statement.executeUpdate(sql1);
            statement.executeUpdate(sql3);
            statement.executeUpdate(sql2);
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
    public  static  void removeSale(String id){
        Statement statement = DBConnect.getInstall().get();
        String sql2= "DELETE FROM sale WHERE idProduct='"+ id+"';";

        try {
            statement.executeUpdate(sql2);

        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
    public  static  void removeInfoOder(String id){
        Statement statement = DBConnect.getInstall().get();
        String sql2= "DELETE FROM cthd WHERE idProduct='"+ id+"';";

        try {
            statement.executeUpdate(sql2);

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

    public static void main(String[] args) throws SQLException {



//            Product p = findById("B100");
//            addProDuct(p);
//        String s = "img/product/B001/banh1.jpg";
//        upProductImg("img/product/B001/banh5.jpg", s);
//        updateDetail("B001", 20,10,"12-1-2022", "13-2-2022");
//        updateProduct("B001", "LB01","Bánh cánh đồng hoa", "Vừa",500,"Bánh kem phong cách hoa Hàn Quốc, ngon và đẹp, chất lượng luôn tươi mới, nguyên liệu hoàn toàn cao cấp được chọn lọc kỹ càng, đảm bảo an toàn vệ sinh thực phẩm, bảo vệ sức khỏe cho người dùng đó chính là điều mà chúng tôi muốn mang lại cho quý khách hàng. Phần hoa của bánh sẽ được làm từ kem bơ, kem tươi tạo nên vị bánh cũng rất ấn tượng, ngọt dịu, không gây ngán. Các thợ làm bánh phải bắt từng bông hoa, để cho thật lạnh để hoa đông cứng rồi mới sắp xếp lên bánh cho hài hoà. Vì vậy cần rất nhiều thời gian, sự kiên nhẫn và cả sự khéo léo. Mỗi chiếc bánh thật sự là một tác phẩm nghệ thuật.","Trong những dịp lễ, ngày kỷ niệm hay sinh nhật, không cần một món quà quá khoa trương, đơn giản chỉ là một chiếc bánh kem được trang trí bằng những bông hoa sắc màu, bắt mắt cũng khiến bữa tiệc trở nên lung linh mà người nhận thì vui vẻ rồi. Bánh thích hợp tặng chị gái, mẹ, cô giáo.",450000);
//        List<Product> li = ProductService.getHotProduct();
//               for(Product p: li){
//            System.out.println(p.getId());
//          System.out.println(getHotProduct().size());
//          Product p = ProductService.findById("B001");
//          System.out.println(p.getListImg().get(0));
//        System.out.println(getLastComment("B001").getBinhLuan());
//       System.out.println(getPaginationPage(1).toString());
        // addComment(new Comment("B002", "Thanh Tâm","Bánh mềm mịn vô cùng hòa quyện với  phần kem mịn màng, vị ngọt thanh vừa ăn lại có thêm phần tiramisu khá lạ miệng khiến cho người ăn cảm thấy thích thú.","2022/12/8"), "AD02");
        System.out.print(getListSize().toString());
    }

}