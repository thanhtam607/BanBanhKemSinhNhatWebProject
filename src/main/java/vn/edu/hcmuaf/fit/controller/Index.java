package vn.edu.hcmuaf.fit.controller;

import vn.edu.hcmuaf.fit.bean.User;
import vn.edu.hcmuaf.fit.model.Blog;
import vn.edu.hcmuaf.fit.model.Product;
import vn.edu.hcmuaf.fit.service.BlogService;
import vn.edu.hcmuaf.fit.service.ProductService;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "Index", value = "/Index")
public class Index extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Index nef Thun");
        System.out.println( BlogService.getData());
        String nameSerlet = "Index";
        request.setAttribute("nameSerlet", nameSerlet);
        Blog b = BlogService.findById(request.getParameter("id"));
        request.setAttribute("blog", b);
        List<Blog> list = BlogService.getData();
        request.setAttribute("list", list);
        List<Product> listHotProduct = ProductService.getHotProduct();
        request.setAttribute("listBanChay", listHotProduct);
        List<Product> listNewProduct = ProductService.getNewProduct(ProductService.getListProduct());
        request.setAttribute("listNewProduct", listNewProduct);
        HttpSession session = request.getSession(true);
        User auth = (User) session.getAttribute("auth");
        if(auth == null){
            session.setAttribute("userNeedsKey", true);
        }
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
