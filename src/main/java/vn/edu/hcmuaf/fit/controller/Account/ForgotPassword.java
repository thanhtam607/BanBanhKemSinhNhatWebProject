package vn.edu.hcmuaf.fit.controller.Account;

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
        int status = 0;
        if(UserService.checkEmail(email)) {
           status=1;
        }
        else{
            int code = UserService.randomCode();
            try {
                UserService.sendMail(email, code);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
            status=code;
        }
        out.println(status);


}


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }}