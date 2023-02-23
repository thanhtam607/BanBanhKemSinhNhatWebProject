package vn.edu.hcmuaf.fit.controller.admin;

import vn.edu.hcmuaf.fit.service.ReceiptService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "adminRemoveOrder", value = "/admin/adminRemoveOrder")
public class adminRemoveOrder extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(true);
        String mahd = request.getParameter("mahd");

        ReceiptService.updateTonKhoWhenCancelOrder(mahd);
        ReceiptService.cancelOrder(mahd);

        response.sendRedirect("./ListReceipt_full_Admin");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
