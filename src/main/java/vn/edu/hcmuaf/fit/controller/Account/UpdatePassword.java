package vn.edu.hcmuaf.fit.controller.Account;

import vn.edu.hcmuaf.fit.bean.User;
import vn.edu.hcmuaf.fit.model.Log;
import vn.edu.hcmuaf.fit.service.LogService;
import vn.edu.hcmuaf.fit.service.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "UpdatePassword", value = "/UpdatePassword")
public class UpdatePassword extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Log log = new Log();
        log.setLevel(2);
        log.setSrc(request.getServletPath());
        HttpSession session = request.getSession(true);
        User user =(User) session.getAttribute("auth");

        if(request.getParameter("oldPass")!=null){
            log.setUser(user.getId());
            String oldPass = request.getParameter("oldPass");
            String p = user.getPass();
            PrintWriter out = response.getWriter();
           if( p.equals(UserService.hashPassword(oldPass))){
               out.println(1);
           }
           else{
               out.println(0);
               log.setContent("Đặt lại mật khẩu thất bại");
           }
        }
        else{
            String pass= request.getParameter("password");
            String email = request.getParameter("email");
            log.setUser(email);
            UserService.updatePass(email, UserService.hashPassword(pass));
            user.setPass(UserService.hashPassword(pass));
            log.setContent("Đặt lại mật khẩu thành công");
        }
        LogService.addLog(log);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
