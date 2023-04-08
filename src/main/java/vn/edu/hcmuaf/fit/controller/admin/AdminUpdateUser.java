package vn.edu.hcmuaf.fit.controller.admin;

import vn.edu.hcmuaf.fit.bean.User;
import vn.edu.hcmuaf.fit.model.Log;
import vn.edu.hcmuaf.fit.service.LogService;
import vn.edu.hcmuaf.fit.service.ReceiptService;
import vn.edu.hcmuaf.fit.service.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AdminUpdateUser", value = "/admin/AdminUpdateUser")
public class AdminUpdateUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");

        String makh = request.getParameter("makh");
        String r = request.getParameter("role");

        int role = 0;
        if(r.equals("Thường")){
            role = 0;
        } else if (r.equals("Admin")){
            role = 1;
        } else if (r.equals("Quản Lí")){
            role = 2;
        }
        ReceiptService.updateRole(role, makh);

        UserService.updateProfile(username, phone, address, email, makh);


        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute("auth");

        Log log = new Log();
        log.setLevel(2);
        log.setSrc(request.getServletPath());
        log.setContent("Cập nhật thông tin và quyền cho người dùng: "+ makh);
        log.setUser(user.getId());
        LogService.addLog(log);
        response.sendRedirect("./EditUser?makh="+ request.getParameter("makh"));
    }
}
