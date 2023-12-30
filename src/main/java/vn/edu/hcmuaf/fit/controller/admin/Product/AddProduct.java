package vn.edu.hcmuaf.fit.controller.admin.Product;

import vn.edu.hcmuaf.fit.bean.User;
import vn.edu.hcmuaf.fit.model.Image;
import vn.edu.hcmuaf.fit.model.Log;
import vn.edu.hcmuaf.fit.model.Product;
import vn.edu.hcmuaf.fit.service.LogService;
import vn.edu.hcmuaf.fit.service.ProductService;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
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
        String masp =ProductService.getMaxId();

        String tensp = request.getParameter("tensp");
        String loai = request.getParameter("loaiBanh");
        String noidung = request.getParameter("noidung");
        String mota= request.getParameter("mota");
        String giasp =request.getParameter("gia");
        String kl = request.getParameter("khoiluong");
        String kichthuoc = request.getParameter("type");
        if(tensp.isEmpty() || giasp.isEmpty()|| kl.isEmpty()){
            request.setAttribute("Error", "Vui lòng nhập đầy đủ thông tin.");
            request.getRequestDispatcher("add-product.jsp").forward(request,response);
        }
        else{
        int gia  = Integer.parseInt(giasp);
        int khoiLg = Integer.parseInt(kl);
        String realPa = request.getServletContext().getRealPath("img/product/" + masp);
        List<Image> listImg=  new ArrayList<>();
//            File file = new File(ProductService.getLocation() + realPa + "/");
//            file.mkdir();
            int i =1;
        for (Part part : request.getParts()) {
                if (part.getName().equalsIgnoreCase("upload")) {
                    String filename = Path.of(part.getSubmittedFileName()).getFileName().toString();
                    if(!Files.exists(Path.of(realPa))){
                        Files.createDirectories(Path.of(realPa));
                    }
//                    String location = ProductService.getLocation() + realPa + "/" + filename;
                    String newImg = realPa + "/" + filename;
//                    part.write(location);
                    part.write(newImg);

                    String idImg = "ASP"+masp.substring(1)+"-"+(i);
                    String img = "img/product/" + masp+"/" + filename;
                    listImg.add(new Image(idImg, masp, img, 0));
                    i++;
                    }
            }
        Product p = new Product(masp, tensp,loai,kichthuoc,khoiLg, mota,noidung,listImg,gia);
        ProductService.addProDuct(p);

            HttpSession session = request.getSession(true);
            User user = (User) session.getAttribute("auth");

            Log log = new Log();
            log.setLevel(1);
            log.setSrc(request.getServletPath());
            log.setContent("Thêm sản phẩm: "+ p.getId());
            log.setUser(user.getId());
            LogService.addLog(log);
        response.sendRedirect("ListProduct_Admin"); }
    }
}
