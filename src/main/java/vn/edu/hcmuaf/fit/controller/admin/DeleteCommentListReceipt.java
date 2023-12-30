package vn.edu.hcmuaf.fit.controller.admin;

import vn.edu.hcmuaf.fit.bean.User;
import vn.edu.hcmuaf.fit.model.Log;
import vn.edu.hcmuaf.fit.service.LogService;
import vn.edu.hcmuaf.fit.service.ProductService;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "DeleteCommentListReceipt", value = "/admin/DeleteCommentListReceipt")
public class DeleteCommentListReceipt extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idCmt = request.getParameter("idCmt");
        ProductService.deleteCommemt(idCmt);
        String id = request.getParameter("id");

        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute("auth");

        Log log = new Log();
        log.setLevel(3);
        log.setSrc(request.getServletPath());
        log.setContent("Xóa bình luận số: "+ id);
        log.setUser(user.getId());
        LogService.addLog(log);

        response.sendRedirect("../admin/EditUser?makh="+request.getParameter("makh"));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
