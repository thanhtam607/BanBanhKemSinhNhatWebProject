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
        OrderService.addOrder(order);
        response.getWriter().println(order.getId());
        response.getWriter().println(order.getNote());
        response.getWriter().println(order.getTrangthai());
        response.getWriter().println(order.getBuyDate());
        response.getWriter().println(order.getUser().getTentk());
        response.getWriter().println(order.getData().toString());
//        request.getRequestDispatcher("checkout.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
