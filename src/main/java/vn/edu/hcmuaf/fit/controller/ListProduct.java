package vn.edu.hcmuaf.fit.controller;

import vn.edu.hcmuaf.fit.model.Product;
import vn.edu.hcmuaf.fit.service.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ListProduct", value = "/ListProduct")
public class ListProduct extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String numPage = request.getParameter("page");
        if(numPage == null){
            numPage = "1";
        }
        int page = Integer.parseInt(numPage);
        List<Product> list = ProductService.getPaginationPageOwn(page);
        int totalProduct = ProductService.getToTalProduct();
        int endPage = totalProduct / 15;
        if(totalProduct % 15 != 0){
            endPage++;
        }
        request.setAttribute("endPage", endPage);
        request.setAttribute("tag", page);
        request.setAttribute("list", list);


        request.getRequestDispatcher("shop-product.jsp").forward(request,response);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
