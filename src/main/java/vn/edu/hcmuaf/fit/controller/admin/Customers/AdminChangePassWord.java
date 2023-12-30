package vn.edu.hcmuaf.fit.controller.admin.Customers;

import vn.edu.hcmuaf.fit.bean.User;
import vn.edu.hcmuaf.fit.service.UserService;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AdminChangePassWord", value = "/admin/AdminChangePassWord")
public class AdminChangePassWord extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(true);
        User auth = (User) session.getAttribute("auth");

        String email = request.getParameter("email");
        String oldpass = request.getParameter("oldpass");
        String newpass = request.getParameter("newpass");
        String confirmpass = request.getParameter("confirmpass");

        int loi = 0;
        if(oldpass != null && newpass != null && confirmpass !=  null ){
            if(!newpass.equals(confirmpass)) {
                PrintWriter out = response.getWriter();
                loi = 1;
                out.println(loi);
                out.flush();
                out.close();
            }
           else if(!auth.getPass().equals(UserService.hashPassword(oldpass))){
                PrintWriter out1 = response.getWriter();
                loi = 2;
                out1.println(loi);
                out1.flush();
                out1.close();
            }else{
                UserService.updatePass(email, UserService.hashPassword(newpass));
                auth.setPass(UserService.hashPassword(newpass));

                PrintWriter ou = response.getWriter();
                ou.println(loi);
                ou.flush();
                ou.close();
                request.getRequestDispatcher("edit-profile.jsp").forward(request, response);
            }


        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
