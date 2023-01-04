package vn.edu.hcmuaf.fit.controller.admin;

import vn.edu.hcmuaf.fit.model.receipt;
import vn.edu.hcmuaf.fit.service.receiptService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ListReceipt_full_Admin", value = "/admin/ListReceipt_full_Admin")
public class ListReceipt_full_Admin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<receipt> lr = receiptService.getData();
        request.setAttribute("listreceipt-full", lr);
        request.getRequestDispatcher("list-order.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
