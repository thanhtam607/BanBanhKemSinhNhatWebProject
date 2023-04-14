package vn.edu.hcmuaf.fit.controller.admin.Customers;

import vn.edu.hcmuaf.fit.bean.User;
import vn.edu.hcmuaf.fit.service.ReceiptService;
import vn.edu.hcmuaf.fit.service.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AdminUpdateProfile", value = "/admin/AdminUpdateProfile")
public class AdminUpdateProfile extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(true);
        User auth = (User) session.getAttribute("auth");

        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");


        UserService.updateProfile(username, phone, address, email, auth);
        request.getRequestDispatcher("edit-profile.jsp").forward(request, response);
    }
}
