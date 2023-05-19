package vn.edu.hcmuaf.fit.controller.admin.Customers;

import vn.edu.hcmuaf.fit.bean.User;
import vn.edu.hcmuaf.fit.model.Comment;
import vn.edu.hcmuaf.fit.model.Customer;
import vn.edu.hcmuaf.fit.model.Log;
import vn.edu.hcmuaf.fit.model.Receipt;
import vn.edu.hcmuaf.fit.service.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "EditUser", value = "/admin/EditUser")
public class EditUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute("auth");

        String makh = request.getParameter("makh");

        List<String> listRole = new ArrayList<>();
        listRole.add(URLEncoder.encode("Thường", "UTF-8"));
        listRole.add(URLEncoder.encode("Admin", "UTF-8"));
        listRole.add(URLEncoder.encode("Quản Lí", "UTF-8"));
        request.setAttribute("listRole", listRole);

        List<Receipt> listctkh = ReceiptService.getReceiptByMakh(makh);
        List<Comment> listcmt = ReceiptService.getListComment(makh);

        request.setAttribute("listmakh", listctkh);
        request.setAttribute("listcmt", listcmt);
        request.setAttribute("mkh", makh);


        Log log = new Log();
        log.setLevel(2);
        log.setSrc(request.getServletPath());
        log.setContent("Sửa thông tin người dùng: "+ makh);
        log.setUser(user.getId());
        LogService.addLog(log);

        request.getRequestDispatcher("edit-user.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
