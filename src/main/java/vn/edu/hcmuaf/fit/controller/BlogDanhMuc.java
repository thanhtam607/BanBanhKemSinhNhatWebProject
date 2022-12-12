package vn.edu.hcmuaf.fit.controller;

import vn.edu.hcmuaf.fit.model.Blog;
import vn.edu.hcmuaf.fit.service.BlogService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "BlogDanhMuc", value = "/BlogDanhMuc")
public class BlogDanhMuc extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Blog> listdm = BlogService.getDanhMuc(request.getParameter("danhmuc"));
        request.setAttribute("list", listdm);
        request.getRequestDispatcher("BlogFilter.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
