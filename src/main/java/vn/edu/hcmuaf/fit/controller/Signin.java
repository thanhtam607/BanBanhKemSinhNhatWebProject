package vn.edu.hcmuaf.fit.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "Signin", value = "/doSignin")
public class Signin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.getWriter().println("Hello");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uname = request.getParameter("username");
        String pass = request.getParameter("pass");

        if(uname.equals(null) || pass.equals(null)){
            request.setAttribute("Error", "Please fill again");
        }
        else if(uname.equals("nhom27@gmail.com") && pass.equals("123")){
            response.sendRedirect("/BanBanhKemSinhNhatWebProject/index.jsp");
        }
        else{
            request.setAttribute("Error", "Username or password is wrong");
            request.getRequestDispatcher("/admin/signin.jsp").forward(request, response);
        }
    }
}
