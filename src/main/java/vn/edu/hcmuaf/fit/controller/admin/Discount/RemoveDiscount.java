package vn.edu.hcmuaf.fit.controller.admin.Discount;

import vn.edu.hcmuaf.fit.bean.User;
import vn.edu.hcmuaf.fit.model.Log;
import vn.edu.hcmuaf.fit.service.DiscountService;
import vn.edu.hcmuaf.fit.service.LogService;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "RemoveDiscount", value = "/admin/RemoveDiscount")
public class RemoveDiscount extends  HttpServlet{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        DiscountService.removeDiscount(id);

        String idPro = request.getParameter("idProduct");

        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute("auth");

        Log log = new Log();
        log.setLevel(3);
        log.setSrc(request.getServletPath());
        log.setContent("Xóa khuyến mãi sản phẩm: "+ idPro);
        log.setUser(user.getId());
        LogService.addLog(log);
        response.sendRedirect("List_Discounts");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
