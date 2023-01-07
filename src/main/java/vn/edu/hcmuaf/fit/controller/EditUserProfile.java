package vn.edu.hcmuaf.fit.controller;

import vn.edu.hcmuaf.fit.bean.User;
import vn.edu.hcmuaf.fit.model.Customer;
import vn.edu.hcmuaf.fit.model.Order;
import vn.edu.hcmuaf.fit.service.CustomerService;
import vn.edu.hcmuaf.fit.service.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "EditUserProfile", value = "/EditUserProfile")
public class EditUserProfile extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(true);
        User auth = (User) session.getAttribute("auth");
        Customer customer = CustomerService.getCusByIdAcc(auth.getId());

        String ten = request.getParameter("ten");
        String diachi = request.getParameter("diachi");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");

        UserService.updateProfileEmail(email, auth);

        UserService.updateProfileTenTk(ten, auth);

        UserService.updateProfileAddress(diachi, auth);

        UserService.updateProfilePhoneNo(phone, auth);

        response.sendRedirect("editUserProfile.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
