package vn.edu.hcmuaf.fit.controller.admin.Product;

import vn.edu.hcmuaf.fit.service.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "AddProduct", value = "/admin/AddProduct")
public class AddProduct extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> listSize = new ArrayList<String>();
        listSize.add("Bé");
        listSize.add("Nhỏ");
        listSize.add("Vừa");
        listSize.add("Lớn");
        request.setAttribute("listSize", listSize);
        String masp = request.getParameter("masp");
        Part p = request.getPart("img");
        String realPa = "img/product/" + masp;
        String filename = Path.of(p.getSubmittedFileName()).getFileName().toString();
        String location = ProductService.getLocation()+realPa+"/"+ filename;
        String newImg1 = realPa + "/" + filename;

        String id ="B"+ ProductService.getData().size()+1;
        List<String> imgs = new ArrayList<String>();
        p.write(location);
        request.getRequestDispatcher("add-product.jsp").forward(request,response);
    }
}
