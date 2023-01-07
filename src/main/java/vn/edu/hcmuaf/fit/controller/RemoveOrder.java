package vn.edu.hcmuaf.fit.controller;

import vn.edu.hcmuaf.fit.model.Order;
import vn.edu.hcmuaf.fit.service.ReceiptService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "RemoveOrder", value = "/RemoveOrder")
public class RemoveOrder extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(true);
        Order order =(Order) session.getAttribute("order");

        String mahd = request.getParameter("mahd");
        ReceiptService.cancelOrder(mahd);

        response.sendRedirect("order.jsp");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
