package vn.edu.hcmuaf.fit.controller.admin.Blog;

import vn.edu.hcmuaf.fit.bean.User;
import vn.edu.hcmuaf.fit.model.Blog;
import vn.edu.hcmuaf.fit.model.Log;
import vn.edu.hcmuaf.fit.service.BlogService;
import vn.edu.hcmuaf.fit.service.LogService;
import vn.edu.hcmuaf.fit.service.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UpdateBlog", value = "/admin/Blog/UpdateBlog")
public class UpdateBlog extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

            String idblog = request.getParameter("idblog");
            String dateblog = request.getParameter("dateblog");
            String content = request.getParameter("content");
            String category = request.getParameter("category");
            String season = request.getParameter("season");
            BlogService.updateBlog(idblog, dateblog, content, category, season);

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("auth");
        Log log = new Log();
        log.setLevel(2);
        log.setSrc(request.getServletPath());
        log.setContent("Sửa bài viết: " + idblog);
        log.setUser(user.getId());
        LogService.addLog(log);

            response.sendRedirect("../ListBlog-admin");
        }

    }
