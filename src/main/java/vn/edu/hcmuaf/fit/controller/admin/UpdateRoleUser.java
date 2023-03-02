package vn.edu.hcmuaf.fit.controller.admin;

import vn.edu.hcmuaf.fit.service.ReceiptService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UpdateRole", value = "/admin/UpdateRole")
public class UpdateRoleUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String makh = request.getParameter("makh");
        String r = request.getParameter("role");
        int role = 0;
        if(r.equals("Thường")){
            role = 0;
        } else if (r.equals("Admin")){
            role = 1;
        } else if (r.equals("Quản Lí")){
            role = 2;
        }
        ReceiptService.updateRole(role, makh);
        response.sendRedirect("./EditUser?makh="+ request.getParameter("makh"));
    }
}
