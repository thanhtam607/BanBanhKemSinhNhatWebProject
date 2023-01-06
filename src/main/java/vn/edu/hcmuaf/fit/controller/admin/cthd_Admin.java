package vn.edu.hcmuaf.fit.controller.admin;

import vn.edu.hcmuaf.fit.model.Receipt;
import vn.edu.hcmuaf.fit.service.ReceiptService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "cthd_Admin", value = "/admin/cthd_Admin")
public class cthd_Admin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Receipt> listctsp = ReceiptService.getcthd(request.getParameter("mahd"));
        List<String> listStatus = new ArrayList<String>();
        listStatus.add("Chờ xử lý");
        listStatus.add("Đang chuẩn bị");
        listStatus.add("Đang giao");
        listStatus.add("Giao thành công");
        listStatus.add("Giao không thành công");
        request.setAttribute("listmahd", listctsp);
        request.setAttribute("listStatus", listStatus);
        request.getRequestDispatcher("receipt-details.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
