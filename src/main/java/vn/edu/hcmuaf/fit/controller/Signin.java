package vn.edu.hcmuaf.fit.controller;

import vn.edu.hcmuaf.fit.bean.User;
import vn.edu.hcmuaf.fit.service.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "Signin", value = "/doSignin")
public class Signin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uname = request.getParameter("username");
        String pass = request.getParameter("pass");
        User user = UserService.getInstance().checkLogin(uname, pass);
        if(user==null){
            request.setAttribute("Error", "Tên đăng nhập hoặc mật khẩu không đúng!!!");
            request.getRequestDispatcher("/signin.jsp").forward(request, response);
        }else{
            HttpSession session = request.getSession(true);
            session.setAttribute("auth", user);
            response.sendRedirect(request.getContextPath() + "/Index");
        }
    }
}
