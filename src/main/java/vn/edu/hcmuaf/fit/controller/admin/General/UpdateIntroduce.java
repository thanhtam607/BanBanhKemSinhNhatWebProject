package vn.edu.hcmuaf.fit.controller.admin.General;

import vn.edu.hcmuaf.fit.service.InforService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UpdateIntroduce", value = "/admin/UpdateIntroduce")
public class UpdateIntroduce extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String id = request.getParameter("idintro");
        String content= request.getParameter("contentintro");
        InforService.updateContent(id,content);
        response.sendRedirect("general_Management.jsp");
    }
}
