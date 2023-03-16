package vn.edu.hcmuaf.fit.controller.admin;

import vn.edu.hcmuaf.fit.model.TypeOfCake;
import vn.edu.hcmuaf.fit.service.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "addTypeCake", value = "/admin/addTypeCake")
public class addTypeCake extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String name = request.getParameter("nameType");
        String id = ProductService.idMaxType();
        TypeOfCake toc = new TypeOfCake(id, name);
        ProductService.addTyofcake(toc);
        response.sendRedirect("./Catalog_admin");
    }
}
