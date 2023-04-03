package vn.edu.hcmuaf.fit.controller.admin;

import vn.edu.hcmuaf.fit.service.ReceiptService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "EditOrderUpdateCustomer", value = "/admin/EditOrderUpdateCustomer")
public class EditOrderUpdateCustomer extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String nameCus = request.getParameter("username");
        String phoneCus = request.getParameter("phone");
        String note = request.getParameter("note");
        String id = request.getParameter("id");
        ReceiptService.updateInfoCustomerInBill(id, nameCus, phoneCus);
        ReceiptService.updateNoteInBill(id, note);
        response.sendRedirect("./AdminEditOrder?id="+ request.getParameter("id"));
    }
}
