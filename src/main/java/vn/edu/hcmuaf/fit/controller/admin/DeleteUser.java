package vn.edu.hcmuaf.fit.controller.admin;

import vn.edu.hcmuaf.fit.service.ReceiptService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "DeleteUser", value = "/admin/DeleteUser")
public class DeleteUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(true);

        String makh = request.getParameter("makh");

        ReceiptService.deleteCustomer(makh);

        response.sendRedirect("ListCustomer");
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
