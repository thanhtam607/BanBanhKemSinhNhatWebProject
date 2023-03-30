package vn.edu.hcmuaf.fit.controller.admin.General;

import vn.edu.hcmuaf.fit.service.InforService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UpdateGeneral_Infor", value = "/admin/UpdateGeneral_Infor")
public class UpdateGeneral_Infor extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String idaddress = request.getParameter("idaddress");
        String address= request.getParameter("address");
        String idemail = request.getParameter("idemail");
        String email= request.getParameter("email");
        String idphone = request.getParameter("idphone");
        String phone= request.getParameter("phone");
        InforService.updateContent(idaddress, address);
        InforService.updateContent(idemail, email);
        InforService.updateContent(idphone, phone);
        response.sendRedirect("general_Management.jsp");
    }
}
