package vn.edu.hcmuaf.fit.service;

import vn.edu.hcmuaf.fit.db.DBConnect;
import vn.edu.hcmuaf.fit.model.*;

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
                ResultSet rs = statement.executeQuery("select hoadon.mahd, sanpham.TenSP, khachhang.TENKH,khachhang.sdt, khachhang.DIACHI, hoadon.NGAYLAPHD, giaohang.NGAYGIAO, hoadon.THANHTIEN from sanpham, hoadon, khachhang, cthd, giaohang where hoadon.mahd = cthd.MAHD and cthd.MASP = sanpham.MaSP and giaohang.MAHD = hoadon.MAHD and khachhang.MAKH = hoadon.MAKH ORDER BY hoadon.NGAYLAPHD desc;");
                while (rs.next()) {
                    receipt rc = new receipt(rs.getString(1), rs.getString(3), rs.getString(2),rs.getString(4), rs.getString(6), rs.getString(7), rs.getString(5), rs.getInt(8));
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
    public static List<receipt> getcthd(String mahd) {
        List<receipt> list = getData();
        List<receipt> rs = new LinkedList<>();
        for(receipt rc : list){
            if (rc.getId().equals(mahd)) {
                rs.add(rc);
            }
        }
        return rs;
    }
    public static void main(String[] args) {
        List<receipt> lr = receiptService.getcthd("HD05");
        for(receipt r : lr){
            System.out.println(r.getAddress());
        }
    }
}
