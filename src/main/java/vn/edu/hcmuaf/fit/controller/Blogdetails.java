package vn.edu.hcmuaf.fit.controller;

import vn.edu.hcmuaf.fit.model.Blog;
import vn.edu.hcmuaf.fit.service.BlogService;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "BlogDetails", value = "/BlogDetails")
public class Blogdetails extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       Blog b = BlogService.findById(request.getParameter("id"));
        request.setAttribute("blog", b);
        List<Blog> list = BlogService.getData();
        List<String> listdm = BlogService.listcate();
        List<String> listcd = BlogService.listss();
        request.setAttribute("listDm", listdm);
        request.setAttribute("listCd", listcd);
        request.setAttribute("list", list);
        request.getRequestDispatcher("blog-details.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
