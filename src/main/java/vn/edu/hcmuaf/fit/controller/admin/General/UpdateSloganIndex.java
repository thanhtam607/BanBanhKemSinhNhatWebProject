package vn.edu.hcmuaf.fit.controller.admin.General;

import vn.edu.hcmuaf.fit.bean.User;
import vn.edu.hcmuaf.fit.model.Log;
import vn.edu.hcmuaf.fit.service.InforService;
import vn.edu.hcmuaf.fit.service.LogService;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "UpdateSloganIndex", value = "/admin/UpdateSloganIndex")
public class UpdateSloganIndex extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String idslogan1 = request.getParameter("idslogan1");
        String slogan1= request.getParameter("slogan1");
        String idslogan2 = request.getParameter("idslogan2");
        String slogan2= request.getParameter("slogan2");
        InforService.updateContent(idslogan1, slogan1);
        InforService.updateContent(idslogan2, slogan2);

        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute("auth");

        Log log = new Log();
        log.setLevel(2);
        log.setSrc(request.getServletPath());
        log.setContent("Cập nhật Slogan trên slider");
        log.setUser(user.getId());
        LogService.addLog(log);

        response.sendRedirect("general_Management.jsp");
    }
}
