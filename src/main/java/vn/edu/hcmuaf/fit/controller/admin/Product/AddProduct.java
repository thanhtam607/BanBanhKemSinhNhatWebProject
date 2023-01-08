package vn.edu.hcmuaf.fit.controller.admin.Product;

import vn.edu.hcmuaf.fit.model.Product;
import vn.edu.hcmuaf.fit.service.ProductService;
import vn.edu.hcmuaf.fit.service.UploadFileHelper;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@MultipartConfig
@WebServlet(name = "AddProduct", value = "/admin/AddProduct")
public class AddProduct extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String masp ="B"+ (ProductService.getData().size()+1);
        String tensp = request.getParameter("tensp");
        String loai = request.getParameter("loaiBanh");
        String noidung = request.getParameter("noidung");
        String mota= request.getParameter("mota");
        String giasp =request.getParameter("gia");
        String kl = request.getParameter("khoiluong");
        String kichthuoc = request.getParameter("type");
        if(tensp== null || giasp == null|| kl==null){
            request.setAttribute("Error", "Vui lòng nhập đầy đủ thông tin.");
            request.getRequestDispatcher("add-product.jsp").forward(request,response);
        }
        else{
        int gia  = Integer.parseInt(giasp);
        int khoiLg = Integer.parseInt(kl);
        String realPa = "img/product/" + masp;
        String path= ProductService.getLocation()+realPa+"/";
        List<String> imgs = UploadFileHelper.uploadFile(path, request);
        List<String> dsanh=  new ArrayList<>();
        for (String i : imgs ) {
            dsanh.add(realPa+ i);
        }
        Product p = new Product(masp, tensp,loai,kichthuoc,khoiLg, mota,noidung,dsanh,gia);
        ProductService.addProDuct(p);
        response.sendRedirect("ListProduct_Admin"); }
    }
}