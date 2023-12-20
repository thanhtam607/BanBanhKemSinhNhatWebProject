package vn.edu.hcmuaf.fit.controller.Account;

import vn.edu.hcmuaf.fit.bean.User;
import vn.edu.hcmuaf.fit.model.Log;
import vn.edu.hcmuaf.fit.service.LogService;
import vn.edu.hcmuaf.fit.service.UserService;

import javax.mail.MessagingException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "ForgotPassword", value = "/ForgotPassword")
public class ForgotPassword extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String email = request.getParameter("email");
        PrintWriter out= response.getWriter();
        Log log = new Log();
        log.setLevel(1);
        log.setUser(email);
        log.setSrc(request.getServletPath());
        int status = 0;
        if(UserService.checkEmail(email)) {
           status=1;
            log.setContent("Yêu cầu cấp lại mật khẩu không thành thành công (tài khoản không tồn tại)");
        }
        else{
            int code = UserService.randomCode();
            try {
                UserService.sendMail(email, "Xác nhận tài khoản", "Mã xác nhận của bạn là: " + code);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
            status=code;
            log.setContent("Yêu cầu cấp lại mật khẩu thành công");
        }
        out.println(status);
        LogService.addLog(log);


}


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }}