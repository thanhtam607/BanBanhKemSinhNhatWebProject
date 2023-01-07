package vn.edu.hcmuaf.fit.controller.admin.Product;

import vn.edu.hcmuaf.fit.service.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@MultipartConfig
@WebServlet(name = "UpdateImg", value = "/admin/Product/UpdateImg")
public class UpdateImg extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("../Edit_Product?idP=" + request.getParameter("masp")).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String masp = request.getParameter("masp");
        Part p = request.getPart("img");
        String realPa = "img/product/" + masp;
        String filename = Path.of(p.getSubmittedFileName()).getFileName().toString();
        String oldImg = request.getParameter("oldImg");
        String location = ProductService.getLocation()+realPa+"/"+ filename;
        String newImg = realPa + "/" + filename;
        File file = new File(location);
        if(file.exists()){
            file.delete();
            ProductService.upProductImg(oldImg, newImg);
        }
        else {
            p.write(location);

            ProductService.upProductImg(oldImg, newImg);
        }
        response.sendRedirect("../ListProduct_Admin");


    }
}
