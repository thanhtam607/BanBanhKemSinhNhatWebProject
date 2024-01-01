package vn.edu.hcmuaf.fit.controller.Account;



import vn.edu.hcmuaf.fit.service.UserService;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String userId = request.getParameter("userId");
        HttpSession session = request.getSession(true);
        PrintWriter out = response.getWriter();
        if (session.getAttribute("otp") == null) {
            try {

                int code = UserService.randomCode();

                session.setAttribute("otp", code);
                UserService.sendMail(UserService.getEmail(userId), "Xác minh tài khoản",
                        "Mã xác nhận tài khoản của bạn là: " + code);
                out.println(code);
            } catch (MessagingException | SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                int inputOtp = Integer.parseInt(request.getParameter("otp"));
                int otp = (int) session.getAttribute("otp");
                if (inputOtp == otp) out.println(1);
                else out.println(0);

            } catch (Exception e) {
                out.println(0);
            }
        }
    }
}
