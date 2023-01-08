package vn.edu.hcmuaf.fit.controller.admin.Product;

import vn.edu.hcmuaf.fit.service.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
@MultipartConfig
@WebServlet(name = "AddImage", value = "/admin/AddImage")
public class AddImage extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String masp = request.getParameter("masp");
        Part p = request.getPart("newImg");
        String realPa = "img/product/" + masp;
        String filename = Path.of(p.getSubmittedFileName()).getFileName().toString();
        String location = ProductService.getLocation()+realPa+"/"+ filename;
        String newImg = realPa + "/" + filename;
        File file = new File(location);
        if(file.exists()){
            file.delete();
            ProductService.addImgForPro(masp, newImg);
        }
        else {
            p.write(location);

            ProductService.addImgForPro(masp, newImg);
        }
        response.sendRedirect("./ListProduct_Admin");

    }
}
