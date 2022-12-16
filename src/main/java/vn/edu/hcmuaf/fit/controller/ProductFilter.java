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
        List<Product> listHotProduct = ProductService.sanPhamBanChay();
        request.setAttribute("listBanChay", listHotProduct);
        List<Product> listFilter = ProductService.findBySize(request.getParameter("filter"));
        String title = request.getParameter("title");

        if(listFilter.isEmpty()){
            listFilter = ProductService.findByType(request.getParameter("filter"));
        }
        if(listFilter.isEmpty()){
            listFilter = ProductService.findByName(request.getParameter("key"));
            title= "Kết quả tìm kiếm '" + request.getParameter("key")+"'";
        }
        //================================= phan trang ================================

        String numPage = request.getParameter("pageName");
        if(numPage == null){
            numPage = "1";
        }
        int page = Integer.parseInt(numPage);

        int endPageFilter = listFilter.size() / 15;
        if(listFilter.size() % 15 != 0){
            endPageFilter++;
        }
        request.setAttribute("endPageFt", endPageFilter);
        request.setAttribute("tagPage", page);
        List<Product> listF = ProductService.getPaginationPageOwn(page, listFilter);
//================================= phan trang ================================
        request.setAttribute("listFilter", listFilter);
        request.setAttribute("title",title);
        request.getRequestDispatcher("product-Filter.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
