package vn.edu.hcmuaf.fit.controller.admin.Distcount;

import vn.edu.hcmuaf.fit.model.Discount;
import vn.edu.hcmuaf.fit.model.Product;
import vn.edu.hcmuaf.fit.service.DiscountService;
import vn.edu.hcmuaf.fit.service.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "List_Discounts", value = "/admin/List_Discounts")
public class List_Discounts extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Discount> listDiscount = DiscountService.getListDiscount();
        List<Product> listProduct = new ArrayList<>();
        for (Discount d : listDiscount) {
            listProduct.add(ProductService.findById(d.getIdProduct()));
        }
        String numPage = request.getParameter("page");
        if(numPage == null || numPage.equals("0")){
            numPage = "1";
        }
        int page = Integer.parseInt(numPage);
        List<Product> listPa = ProductService.getPaginationPageOwn(page, listProduct);
        int totalProduct = ProductService.getToTalProduct();
        int endPage = totalProduct / 15;
        if(totalProduct % 15 != 0){
            endPage++;
        }
        request.setAttribute("listpro", listProduct);
        request.setAttribute("endPage", endPage);
        request.setAttribute("tag", page);
        request.setAttribute("listPa", listPa);
        request.getRequestDispatcher("discounts.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Discount> listDiscount = DiscountService.getListDiscount();
        List<Product> listProduct = new ArrayList<>();
        for (Discount d : listDiscount) {
            listProduct.add(ProductService.findById(d.getIdProduct()));
        }
        String numPage = request.getParameter("page");
        if(numPage == null || numPage.equals("0")){
            numPage = "1";
        }
        int page = Integer.parseInt(numPage);
        List<Product> listPa = ProductService.getPaginationPageOwn(page, listProduct);
        int totalProduct = ProductService.getToTalProduct();
        int endPage = totalProduct / 15;
        if(totalProduct % 15 != 0){
            endPage++;
        }
        request.setAttribute("listpro", listProduct);
        request.setAttribute("endPage", endPage);
        request.setAttribute("tag", page);
        request.setAttribute("listPa", listPa);
        request.getRequestDispatcher("discounts.jsp").forward(request,response);
    }
}
