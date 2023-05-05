package vn.edu.hcmuaf.fit.controller.admin.Recipts;

import vn.edu.hcmuaf.fit.bean.User;
import vn.edu.hcmuaf.fit.model.Bill_Detail;
import vn.edu.hcmuaf.fit.model.Log;
import vn.edu.hcmuaf.fit.model.Receipt;
import vn.edu.hcmuaf.fit.service.LogService;
import vn.edu.hcmuaf.fit.service.ReceiptService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminEditOrder", value = "/admin/AdminEditOrder")
public class AdminEditOrder extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String madh = request.getParameter("id");
        request.setAttribute("listStatus", ReceiptService.getAllStatusNameOrder());

        String tenKH = request.getParameter("tenkh");
        Receipt receipt = ReceiptService.getReceiptByMahd(madh);
        List<Bill_Detail> listcthdOfKH = ReceiptService.getBill_DetailUser(madh);
        request.setAttribute("listcthdOfKH", listcthdOfKH);
        request.setAttribute("receipt", receipt);
        request.setAttribute("tenkh", tenKH);


        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute("auth");
        if (user.getIsedit() == 1 || user.getRole() == 2) {
            Log log = new Log();
            log.setLevel(2);
            log.setSrc(request.getServletPath());
            log.setContent("Sửa thông tin đơn hàng: " + madh);
            log.setUser(user.getId());
            LogService.addLog(log);


            request.getRequestDispatcher("edit-order.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("error_page.jsp").forward(request, response);

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
