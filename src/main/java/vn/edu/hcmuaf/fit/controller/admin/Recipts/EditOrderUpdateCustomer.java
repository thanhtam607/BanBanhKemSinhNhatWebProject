package vn.edu.hcmuaf.fit.controller.admin.Recipts;

import vn.edu.hcmuaf.fit.bean.User;
import vn.edu.hcmuaf.fit.model.Log;
import vn.edu.hcmuaf.fit.model.Product;
import vn.edu.hcmuaf.fit.service.LogService;
import vn.edu.hcmuaf.fit.service.ProductService;
import vn.edu.hcmuaf.fit.service.ReceiptService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "EditOrderUpdateCustomer", value = "/admin/EditOrderUpdateCustomer")
public class EditOrderUpdateCustomer extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String usernameRE = request.getParameter("usernameRE");
        String phoneRE = request.getParameter("phoneRE");
        String mailRE = request.getParameter("mailRE");
        String note = request.getParameter("note");
        String id = request.getParameter("id");



        ReceiptService.updateInfoCustomerInBill(id, usernameRE, phoneRE, mailRE);
        ReceiptService.updateNoteInBill(id, note);

        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute("auth");

        Log log = new Log();
        log.setLevel(2);
        log.setSrc(request.getServletPath());
        log.setContent("Sửa thông tin đơn hàng: "+ id);
        log.setUser(user.getId());
        LogService.addLog(log);

        response.sendRedirect("./AdminEditOrder?id="+ request.getParameter("id"));
    }
}
