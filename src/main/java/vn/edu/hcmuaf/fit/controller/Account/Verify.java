package vn.edu.hcmuaf.fit.controller.Account;



import vn.edu.hcmuaf.fit.service.UserService;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "Verify", value = "/Verify")
public class Verify extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("vẻi");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String userId = request.getParameter("userId");
        try {
            PrintWriter out= response.getWriter();
            int code = UserService.randomCode();
            UserService.sendMail(UserService.getEmail(userId), "Xác minh tài khoản",
                    "Mã xác nhận tài khoản của bạn là: "+ code);
            out.println(code);
        } catch (MessagingException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
