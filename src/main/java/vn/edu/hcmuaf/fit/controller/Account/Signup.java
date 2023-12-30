package vn.edu.hcmuaf.fit.controller.Account;

import vn.edu.hcmuaf.fit.bean.User;
import vn.edu.hcmuaf.fit.model.Customer;
import vn.edu.hcmuaf.fit.model.Log;
import vn.edu.hcmuaf.fit.service.CustomerService;
import vn.edu.hcmuaf.fit.service.UserService;

import javax.mail.MessagingException;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Signup", value = "/Signup")
public class Signup extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String email = request.getParameter("email");
        if(request.getParameter("name")==null){
            PrintWriter out= response.getWriter();
            int code = UserService.randomCode();
            try {
                UserService.sendMail(email, "Xác nhận tài khoản", "Mã xác nhận của bạn là: " + code);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
            out.println(code);

        }
        else{
        String user = request.getParameter("name");
        String pass = request.getParameter("pass");

            if (!UserService.checkEmail(email)) {
            request.setAttribute("Error", "Email đã được sử dụng!!");
            request.getRequestDispatcher("signup.jsp").forward(request,response);
        }

        else {
            User newUser = new User(email, pass, user);
            Customer newCus = new Customer();
            UserService.register(newUser);
            CustomerService.registerKH(newCus,newUser);
                PrintWriter out= response.getWriter();
            String url = null;
            if (request.getParameter("saveLogin").equals("true")) {
                HttpSession session = request.getSession(true);
                session.setAttribute("auth", newUser);
                out.println("./Index");
            } else {
                out.println("signin.jsp");
            }

                Log log = new Log();
                log.setLevel(1);
                log.setUser(email);
                log.setSrc(request.getServletPath());
                log.setContent("Đăng ký tài khoản thành công");
        }
        }
    }
}
