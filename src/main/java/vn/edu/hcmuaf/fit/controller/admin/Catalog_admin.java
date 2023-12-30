package vn.edu.hcmuaf.fit.controller.admin;

import vn.edu.hcmuaf.fit.model.TypeOfCake;
import vn.edu.hcmuaf.fit.service.ProductService;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


@WebServlet(name = "Catalog_admin", value = "/admin/Catalog_admin")
public class Catalog_admin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<TypeOfCake> listType = null;
        try {
            listType = ProductService.getListType();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("listType", listType);
        String sort = request.getParameter("sortValue");
        if (sort != null) {
            if (sort.equals("Theo loại")) {
                Collections.sort(listType, new Comparator<TypeOfCake>() {
                    @Override
                    public int compare(TypeOfCake o1, TypeOfCake o2) {
                        return o1.getName().compareTo(o2.getName());
                    }
                });
            }
        }
            request.getRequestDispatcher("catalog_Management.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
