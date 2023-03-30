package vn.edu.hcmuaf.fit.controller.admin.General;

import vn.edu.hcmuaf.fit.service.InforService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UpdateSloganIndex", value = "/admin/UpdateSloganIndex")
public class UpdateSloganIndex extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String idslogan1 = request.getParameter("idslogan1");
        String slogan1= request.getParameter("slogan1");
        String idslogan2 = request.getParameter("idslogan2");
        String slogan2= request.getParameter("slogan2");
        InforService.updateContent(idslogan1, slogan1);
        InforService.updateContent(idslogan2, slogan2);
        response.sendRedirect("general_Management.jsp");
    }
}
