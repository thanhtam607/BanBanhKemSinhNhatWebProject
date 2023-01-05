package vn.edu.hcmuaf.fit.service;

import vn.edu.hcmuaf.fit.db.DBConnect;
import vn.edu.hcmuaf.fit.model.Comment;
import vn.edu.hcmuaf.fit.model.Product;
import vn.edu.hcmuaf.fit.model.ProductDetails;
import vn.edu.hcmuaf.fit.model.receipt;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class receiptService {
    public static List<receipt> getData() {
        List<receipt> list = new LinkedList<receipt>();
        Statement statement = DBConnect.getInstall().get();
        if (statement != null)
            try {
                ResultSet rs = statement.executeQuery("select hoadon.MAKH, hoadon.NGAYLAPHD, khachhang.TENKH, khachhang.DIACHI, hoadon.THANHTIEN from khachhang, hoadon where khachhang.MAKH = hoadon.MAKH ORDER BY hoadon.NGAYLAPHD desc;");
                while (rs.next()) {
                    receipt rc = new receipt(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5));
                    list.add(rc);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        else {
            System.out.println("Không có  hóa đơn");
        }
        return list;
    }

    public static void main(String[] args) {
        List<receipt> lr = receiptService.getData();
        for(receipt r : lr){
            System.out.println(r.getAddress());
        }
    }
}
