package vn.edu.hcmuaf.fit.controller;

import vn.edu.hcmuaf.fit.model.Product;
import vn.edu.hcmuaf.fit.service.ProductService;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ListProduct", value = "/ListProduct")
public class ListProduct extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> listdiscount = ProductService.getDiscountProduct();
        request.setAttribute("listdiscount", listdiscount);
        String numPage = request.getParameter("page");
        String sort = request.getParameter("sortValue");
        List<Product> listPro = ProductService.getListProduct();
        if(sort != null ){
            if(sort.equals("Giá từ thấp đến cao") ) {
                listPro.sort((Product o1, Product o2) -> o1.getPrice() - o2.getPrice());
            }
            if(sort.equals("Giá từ cao đến thấp") ){
                listPro.sort((Product o1, Product o2) -> o2.getPrice() - o1.getPrice());
            }

        }
        if(numPage == null){
            numPage = "1";
        }
        int page = Integer.parseInt(numPage);
        List<Product> list = ProductService.getPaginationPageOwn(page, listPro);
        int totalProduct = ProductService.getToTalProduct();
        int endPage = totalProduct / 15;
        if(totalProduct % 15 != 0){
            endPage++;
        }

        request.setAttribute("endPage", endPage);
        request.setAttribute("tag", page);
        request.setAttribute("list", list);
        List<Product> listHotProduct = ProductService.getHotProduct();
        request.setAttribute("listBanChay", listHotProduct);
        request.getRequestDispatcher("shop-product.jsp").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
