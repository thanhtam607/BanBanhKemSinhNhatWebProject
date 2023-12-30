package vn.edu.hcmuaf.fit.controller.admin.General;

import vn.edu.hcmuaf.fit.bean.User;
import vn.edu.hcmuaf.fit.model.Log;
import vn.edu.hcmuaf.fit.service.InforService;
import vn.edu.hcmuaf.fit.service.LogService;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "UpdateShopInfor", value = "/admin/UpdateShopInfor")
public class UpdateShopInfor extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String idopendate = request.getParameter("idopendate");
        String opendate= request.getParameter("opendate");
        String idtimeopen= request.getParameter("idtimeopen");
        String timeopen= request.getParameter("timeopen");
        String idclosedate = request.getParameter("idclosedate");
        String closedate= request.getParameter("closedate");
        String idtimeopen2 = request.getParameter("idtimeopen2");
        String timeopen2 = request.getParameter("timeopen2");
        String idoffer = request.getParameter("idoffer");
        String offer= request.getParameter("offer");
        String idtimeoffer = request.getParameter("idtimeoffer");
        String timeoffer= request.getParameter("timeoffer");
        InforService.updateContent(idopendate, opendate);
        InforService.updateContent(idclosedate,closedate);
        InforService.updateContent(idtimeopen, timeopen);
        InforService.updateContent(idtimeopen2, timeopen2);
        InforService.updateContent(idoffer, offer);
        InforService.updateContent(idtimeoffer, timeoffer);

        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute("auth");

        Log log = new Log();
        log.setLevel(2);
        log.setSrc(request.getServletPath());
        log.setContent("Cập nhật thông tin Shop");
        log.setUser(user.getId());
        LogService.addLog(log);


        response.sendRedirect("general_Management.jsp");
    }
}
