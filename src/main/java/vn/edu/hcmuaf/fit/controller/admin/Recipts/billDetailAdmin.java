package vn.edu.hcmuaf.fit.controller.admin.Recipts;

import vn.edu.hcmuaf.fit.model.Bill_Detail;
import vn.edu.hcmuaf.fit.model.Receipt;
import vn.edu.hcmuaf.fit.service.ReceiptService;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "Bill_detail_Admin", value = "/admin/Bill_detail_Admin")
public class billDetailAdmin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tenKH = request.getParameter("tenkh");
        Receipt receipt = ReceiptService.getReceiptByMahd(request.getParameter("mahd"));
        List<Bill_Detail> listcthdOfKH = ReceiptService.getBill_DetailUser(request.getParameter("mahd"));
        request.setAttribute("listcthdOfKH", listcthdOfKH);
        request.setAttribute("receipt", receipt);
        request.setAttribute("tenkh", tenKH);
        request.getRequestDispatcher("receipt-details.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
