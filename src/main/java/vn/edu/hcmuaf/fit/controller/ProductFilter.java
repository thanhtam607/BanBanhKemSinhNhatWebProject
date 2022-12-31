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
        String p_min = request.getParameter("pricemin");
        String p_max = request.getParameter("pricemax");


        List<Product> listFilter = ProductService.findBySize(request.getParameter("filter"));
        String title = request.getParameter("title");
        if(listFilter.isEmpty()){
            listFilter = ProductService.findByType(request.getParameter("filter"));
        }
        if(listFilter.isEmpty()){
            listFilter = ProductService.findByName(request.getParameter("key"));
            title= "Kết quả tìm kiếm '" + request.getParameter("key")+"'";
        }
        int min=0;
        int max=10000000;
        if(p_min != null){
            min = Integer.parseInt(p_min);
        }
        if( p_max != null) {
           max=Integer.parseInt(p_max);
        }
        if(listFilter.isEmpty()){
            listFilter = ProductService.filterByPrice(min, max);
        }
        String sort = request.getParameter("sortValue");
        if(sort != null ){
            if(sort.equals("Giá từ thấp đến cao") ) {
                listFilter.sort((Product o1, Product o2) -> o1.getPrice() - o2.getPrice());
            }
            if(sort.equals("Giá từ cao đến thấp") ){
                listFilter.sort((Product o1, Product o2) -> o2.getPrice() - o1.getPrice());
            }


        }
        //================================= phan trang ================================

        String numPage = request.getParameter("pageName");
        if(numPage == null){
            numPage = "1";
        }
        int page = Integer.parseInt(numPage);
        List<Product> listF = ProductService.getPaginationPageOwn(page, listFilter);
        int endPageFilter = listFilter.size() / 15;
        if(listFilter.size() % 15 != 0){
            endPageFilter++;
        }
        request.setAttribute("endPageFt", endPageFilter);
        request.setAttribute("tagPage", page);
        request.setAttribute("sortValue", sort);
//================================= phan trang ================================
        request.setAttribute("title", title);
        request.setAttribute("listFilter", listF);
        request.getRequestDispatcher("product-Filter.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
