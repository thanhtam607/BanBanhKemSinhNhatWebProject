package vn.edu.hcmuaf.fit.controller.admin.Recipts;

import vn.edu.hcmuaf.fit.bean.User;
import vn.edu.hcmuaf.fit.model.Log;
import vn.edu.hcmuaf.fit.service.LogService;
import vn.edu.hcmuaf.fit.service.ReceiptService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "adminRemoveOrderCTKH", value = "/admin/adminRemoveOrderCTKH")
public class adminRemoveOrderCTKH extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(true);
        String mhd = request.getParameter("mahd");
        String mh = request.getParameter("makhCTKH");

        ReceiptService.updateTonKhoWhenCancelOrder(mhd);
        ReceiptService.cancelOrder(mhd);

        User user = (User) session.getAttribute("auth");

        Log log = new Log();
        log.setLevel(3);
        log.setSrc(request.getServletPath());
        log.setContent("Hủy đơn hàng: "+ mhd);
        log.setUser(user.getId());
        LogService.addLog(log);

        response.sendRedirect("./EditUser?makh="+ mh );

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
