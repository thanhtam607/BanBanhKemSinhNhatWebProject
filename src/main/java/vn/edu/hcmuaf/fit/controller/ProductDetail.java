package vn.edu.hcmuaf.fit.controller;

import vn.edu.hcmuaf.fit.model.Product;
import vn.edu.hcmuaf.fit.service.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductDetail", value = "/ProductDetail")
public class ProductDetail extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Product product = ProductService.findById(request.getParameter("id"));
        if(product.isHide()|| product.delete()){
            request.getRequestDispatcher("blank_page.jsp").forward(request,response);
        }else{
            request.setAttribute("getDetail", product);
            List<Product> listsplq = ProductService.findByType(product.getType(), ProductService.getListProduct());
            request.setAttribute("splq", listsplq);
            request.getRequestDispatcher("shop-details.jsp").forward(request,response);}
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}