package vn.edu.hcmuaf.fit.controller;

import vn.edu.hcmuaf.fit.bean.User;
import vn.edu.hcmuaf.fit.model.Account;
import vn.edu.hcmuaf.fit.service.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
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
            UserService.register(newUser);
            String url = null;
            if(request.getParameter("save-login") != null){
                HttpSession session = request.getSession(true);
                session.setAttribute("auth", newUser);
                url = "./Index";
            }
            else{
                url = "signin.jsp";
            }
            response.sendRedirect(url);
        }
    }
}
