package vn.edu.hcmuaf.fit.controller.admin;

import vn.edu.hcmuaf.fit.service.BlogService;
import vn.edu.hcmuaf.fit.service.ReceiptService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "HideBlog", value = "/admin/HideBlog")
public class HideBlog extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String mablog = request.getParameter("mablog");
        String status = request.getParameter("stt");
        int sta = Integer.parseInt(status);
        request.setAttribute("mbl", mablog);
        request.setAttribute("st", status);
        BlogService.updateStatus(mablog, sta);
        response.sendRedirect("./ListBlog-admin");
    }
}
