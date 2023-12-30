package vn.edu.hcmuaf.fit.controller.admin.Blog;

import vn.edu.hcmuaf.fit.bean.User;
import vn.edu.hcmuaf.fit.model.Log;
import vn.edu.hcmuaf.fit.service.BlogService;
import vn.edu.hcmuaf.fit.service.LogService;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "RestoreBlog", value = "/admin/RestoreBlog")
public class RestoreBlog extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String idblog = request.getParameter("idblog");
        request.setAttribute("mbl", idblog);
        BlogService.restoreBlog(idblog);

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("auth");
        Log log = new Log();
        log.setLevel(1);
        log.setSrc(request.getServletPath());
        log.setContent("Khôi phục bài viết: " + idblog);
        log.setUser(user.getId());
        LogService.addLog(log);

        response.sendRedirect("./ListBlog-admin");
    }
}
