package vn.edu.hcmuaf.fit.controller;

import vn.edu.hcmuaf.fit.bean.User;
import vn.edu.hcmuaf.fit.model.Order;
import vn.edu.hcmuaf.fit.service.OrderService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AddNewOrder", value = "/AddNewOrder")
public class AddNewOrder extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        Order order =(Order) session.getAttribute("order");
        Order newOd = order;
        OrderService.addOrder(newOd);
        OrderService.addCTHD(newOd);
        response.getWriter().println(newOd.getId());
        response.getWriter().println(newOd.getNote());
        response.getWriter().println(newOd.getTrangthai());
        response.getWriter().println(newOd.getBuyDate());
        response.getWriter().println(newOd.getUser().getId());
        response.getWriter().println(newOd.getData().toString());
//        response.sendRedirect("checkout.jsp");
//        request.getRequestDispatcher("checkout.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
