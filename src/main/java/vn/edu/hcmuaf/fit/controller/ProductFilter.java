package vn.edu.hcmuaf.fit.controller;

import vn.edu.hcmuaf.fit.model.Blog;
import vn.edu.hcmuaf.fit.model.Product;
import vn.edu.hcmuaf.fit.service.BlogService;
import vn.edu.hcmuaf.fit.service.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductFilter", value = "/ProductFilter")
public class ProductFilter extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> list = ProductService.getSize(request.getParameter("size"));
        request.setAttribute("list", list);
        request.getRequestDispatcher("/shop-product-banh-hoa.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
