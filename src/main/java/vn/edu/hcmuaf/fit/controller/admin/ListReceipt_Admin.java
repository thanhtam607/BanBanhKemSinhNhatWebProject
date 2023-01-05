package vn.edu.hcmuaf.fit.controller.admin;

import vn.edu.hcmuaf.fit.model.receipt;
import vn.edu.hcmuaf.fit.service.receiptService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ListReceipt_Admin", value = "/admin/ListReceipt_Admin")
public class ListReceipt_Admin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<receipt> lr = receiptService.getData();
        request.setAttribute("listreceipt", lr);
        request.getRequestDispatcher("admin-web.jsp").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
