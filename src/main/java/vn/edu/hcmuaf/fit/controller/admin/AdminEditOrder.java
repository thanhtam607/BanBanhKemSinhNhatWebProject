package vn.edu.hcmuaf.fit.controller.admin;

import vn.edu.hcmuaf.fit.model.Bill_Detail;
import vn.edu.hcmuaf.fit.model.Receipt;
import vn.edu.hcmuaf.fit.service.ReceiptService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "AdminEditOrder", value = "/admin/AdminEditOrder")
public class AdminEditOrder extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String madh = request.getParameter("id");
        request.setAttribute("listStatus", ReceiptService.getAllStatusNameOrder());

        String tenKH = request.getParameter("tenkh");
        Receipt receipt = ReceiptService.getReceiptByMahd(madh);
        List<Bill_Detail> listcthdOfKH = ReceiptService.getcthdUser(madh);
        request.setAttribute("listcthdOfKH", listcthdOfKH);
        request.setAttribute("receipt", receipt);
        request.setAttribute("tenkh", tenKH);

        request.getRequestDispatcher("edit-order.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
