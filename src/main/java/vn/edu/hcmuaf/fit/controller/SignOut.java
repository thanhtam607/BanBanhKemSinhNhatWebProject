package vn.edu.hcmuaf.fit.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "SignOut", value = "/doSignOut")
public class SignOut extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession(false);
        if(session != null){
            session.invalidate();
            response.sendRedirect("/BanBanhKemSinhNhatWebProject/signin.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
