package vn.edu.hcmuaf.fit.controller.admin.Product;

import vn.edu.hcmuaf.fit.bean.User;
import vn.edu.hcmuaf.fit.model.Log;
import vn.edu.hcmuaf.fit.service.LogService;
import vn.edu.hcmuaf.fit.service.ProductService;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "UpdateProduct", value = "/admin/Product/UpdateProduct")
public class UpdateProduct extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String masp = request.getParameter("proId");
        String tensp = request.getParameter("proname");
        int gia=0;
        int khoiLuong=0;
        if(request.getParameter("price")!= null) {
            gia = Integer.parseInt(request.getParameter("price"));
        }
        if(request.getParameter("weight")!=null){
            khoiLuong = Integer.parseInt(request.getParameter("weight"));
        }
        String loai = request.getParameter("loaiBanh");
        String kichthuoc = request.getParameter("size");
        String mota = request.getParameter("description");
        String noidung = request.getParameter("intro");
        ProductService.updateProduct(masp,  loai, tensp,kichthuoc, khoiLuong, mota, noidung, gia);
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
}