package vn.edu.hcmuaf.fit.controller.Account;

import vn.edu.hcmuaf.fit.bean.User;
import vn.edu.hcmuaf.fit.model.Log;
import vn.edu.hcmuaf.fit.service.LogService;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "SignOut", value = "/doSignOut")
public class SignOut extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession(true);
        if(session != null){
            User user = (User) session.getAttribute("auth");
            session.invalidate();

            response.sendRedirect("./signin.jsp");

            Log log = new Log();
            log.setLevel(1);
            log.setUser(user.getId());
            log.setSrc(request.getServletPath());
            log.setContent("Đăng xuất khỏi trang web");
            LogService.addLog(log);


        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
