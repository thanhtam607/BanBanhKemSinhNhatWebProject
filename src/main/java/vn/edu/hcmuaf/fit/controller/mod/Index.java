package vn.edu.hcmuaf.fit.controller.mod;

import vn.edu.hcmuaf.fit.bean.Blog;
import vn.edu.hcmuaf.fit.service.BlogService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "Index", value = "/Index")
public class Index extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Blog b = BlogService.findById(request.getParameter("id"));
        request.setAttribute("blog", b);
        List<Blog> list = BlogService.getData();
        request.setAttribute("list", list);
        request.getRequestDispatcher("index.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
