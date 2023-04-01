package vn.edu.hcmuaf.fit.controller.admin;

import vn.edu.hcmuaf.fit.model.TypeOfCake;
import vn.edu.hcmuaf.fit.service.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "deleteTypeCake", value = "/admin/deleteTypeCake")
public class deleteTypeCake extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String id = request.getParameter("idType");
        ProductService.deleteType(id);
        response.sendRedirect("./Catalog_admin");
    }
}