package vn.edu.hcmuaf.fit.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "Signup", value = "/Signup")
public class Signup extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user = request.getParameter("username");
        String pass = request.getParameter("pass");
        String repass = request.getParameter("repass");
        if(!pass.equals(repass)){
            request.getRequestDispatcher("signup.jsp").forward(request,response);
        }

        response.sendRedirect("index.jsp");
    }

}