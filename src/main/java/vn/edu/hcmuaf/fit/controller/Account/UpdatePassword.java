package vn.edu.hcmuaf.fit.controller.Account;

import vn.edu.hcmuaf.fit.bean.User;
import vn.edu.hcmuaf.fit.service.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "UpdatePassword", value = "/UpdatePassword")
public class UpdatePassword extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if(request.getParameter("oldPass")!=null){
            HttpSession session = request.getSession(true);
            User user =(User) session.getAttribute("auth");
            String oldPass = request.getParameter("oldPass");
            String p = user.getPass();
            PrintWriter out = response.getWriter();
           if( p.equals(UserService.hashPassword(oldPass))){
               out.println(1);
//               out.flush();
//               out.close();
           }
           else{
               out.println(0);
//               out.flush();
//               out.close();
           }
        }
        else{
            String pass= request.getParameter("password");
            String email = request.getParameter("email");
            UserService.updatePass(email, UserService.hashPassword(pass));
        }



    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
