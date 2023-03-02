package vn.edu.hcmuaf.fit.controller.Account;

import vn.edu.hcmuaf.fit.bean.User;
import vn.edu.hcmuaf.fit.model.Account;
import vn.edu.hcmuaf.fit.model.Customer;
import vn.edu.hcmuaf.fit.service.CustomerService;
import vn.edu.hcmuaf.fit.service.UserService;

import javax.mail.MessagingException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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
                UserService.sendMail(email, code);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
            out.println(code);

        }
        else{
        String user = request.getParameter("name");
        String pass = request.getParameter("pass");
        String repass = request.getParameter("repass");
        if(!pass.equals(repass)){
            request.setAttribute("Error", "Mật khẩu nhập lại không khớp!");
            request.getRequestDispatcher("signup.jsp").forward(request,response);
        }
        else if (!UserService.checkEmail(email)) {
            request.setAttribute("Error", "Email đã được sử dụng!!");
            request.getRequestDispatcher("signup.jsp").forward(request,response);
        }

        else {
            User newUser = new User(email, pass, user);
            Customer newCus = new Customer();
            UserService.register(newUser);
            newCus.setTENKH(newUser.getAccount_name());
            CustomerService.registerKH(newCus,newUser);

            String url = null;
            if (request.getParameter("save-login") != null) {
                HttpSession session = request.getSession(true);
                session.setAttribute("auth", newUser);
                url = "./Index";
            } else {
                url = "signin.jsp";
            }
            response.sendRedirect(url);
        }
        }
    }
}
