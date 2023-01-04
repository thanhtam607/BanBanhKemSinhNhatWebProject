package vn.edu.hcmuaf.fit.controller.admin;

import vn.edu.hcmuaf.fit.model.Blog;
import vn.edu.hcmuaf.fit.service.BlogService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ListBlog-admin", value = "/admin/ListBlog-admin")
public class ListBlog_admin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Blog> list = BlogService.getData();
        request.setAttribute("list", list);
        request.getRequestDispatcher("list-blogs.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
