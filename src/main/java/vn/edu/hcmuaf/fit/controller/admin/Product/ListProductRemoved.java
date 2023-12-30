package vn.edu.hcmuaf.fit.controller.admin.Product;

import vn.edu.hcmuaf.fit.model.Product;
import vn.edu.hcmuaf.fit.service.ProductService;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ListProductRemoved", value = "/admin/ListProductRemoved")
public class ListProductRemoved extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> list = ProductService.getListProductRemove();
        request.setAttribute("listpro", list);

        String numPage = request.getParameter("page");
        if(numPage == null || numPage.equals("0")){
            numPage = "1";
        }
        int page = Integer.parseInt(numPage);
        List<Product> listPa = ProductService.getPaginationPageOwn(page, list);
        request.setAttribute("tag", page);
        request.setAttribute("listPa", listPa);
        request.getRequestDispatcher("products-Removed.jsp").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    }
}
