package vn.edu.hcmuaf.fit.controller.admin.Product;

import vn.edu.hcmuaf.fit.bean.User;
import vn.edu.hcmuaf.fit.model.Log;
import vn.edu.hcmuaf.fit.model.ProductDetail;
import vn.edu.hcmuaf.fit.service.LogService;
import vn.edu.hcmuaf.fit.service.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UpdateDetails", value = "/admin/Product/UpdateDetails")
public class UpdateDetails extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        int soLuong = 0;
        int tonKho = 0;
        String masp = request.getParameter("masp");
        if(request.getParameter("soluong")!=null){
            soLuong = Integer.parseInt(request.getParameter("soluong"));
        }
        if(request.getParameter("tonkho")!=null){
            tonKho = Integer.parseInt(request.getParameter("tonkho"));
        }
        String ngaysx = request.getParameter("ngaysx");
        String ngayhh = request.getParameter("ngayhh");
        if(ngaysx==null){
            ngaysx= ProductService.findById(masp).getDetail().getMfg();
        }
        if(ngayhh == null){
            ngayhh= ProductService.findById(masp).getDetail().getOod();
        }
        ProductService.updateDetail(new ProductDetail(masp,soLuong,tonKho,ngaysx,ngayhh));
        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute("auth");

        Log log = new Log();
        log.setLevel(2);
        log.setSrc(request.getServletPath());
        log.setContent("Cập nhật thông tin sản phẩm: "+ masp);
        log.setUser(user.getId());
        LogService.addLog(log);

        response.sendRedirect("../ListProduct_Admin");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
