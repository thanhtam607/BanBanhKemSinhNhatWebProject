package vn.edu.hcmuaf.fit.controller.admin.Blog;

import vn.edu.hcmuaf.fit.bean.User;
import vn.edu.hcmuaf.fit.model.Blog;
import vn.edu.hcmuaf.fit.model.Log;
import vn.edu.hcmuaf.fit.service.BlogService;
import vn.edu.hcmuaf.fit.service.LogService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@MultipartConfig
@WebServlet(name = "AddBlog", value = "/admin/AddBlog")
public class AddBlog extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute("auth");
        if (user.getIsadd() == 1 || user.getRole() == 2) {
            request.getRequestDispatcher("add-blog.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("error_page.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String idblog = BlogService.getMaxIdBlog();
        String idnew = BlogService.getNewIdBlog();
        String title = request.getParameter("title");
        Part p = request.getPart("img");
        String path = request.getServletContext().getRealPath("img/blog/" + idblog);
        String filename = Path.of(p.getSubmittedFileName()).getFileName().toString();
        if(!Files.exists(Path.of(path))){
            Files.createDirectories(Path.of(path));
        }
        String img = path + "/" + filename;
        p.write(img);
        String date = request.getParameter("datetime");
        String category = request.getParameter("category");
        String season = request.getParameter("season");
        String content = request.getParameter("content");
        if(title.isEmpty() || date.isEmpty()|| content.length() < 100){
            request.setAttribute("Error", "Vui lòng nhập đầy đủ thông tin.");
            request.getRequestDispatcher("add-blog.jsp").forward(request,response);
        }
        int i = 0;
        String imgblog = "img/blog/" + idblog+"/" + filename;
        Blog b = new Blog(idnew, imgblog, title, date, content, category, season, i);
        BlogService.addBlog(b);
        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute("auth");
        //lưu log
        Log log = new Log();
        log.setLevel(1);
        log.setSrc(request.getServletPath());
        log.setContent("Thêm bài viết: " + idnew);
        log.setUser(user.getId());
        LogService.addLog(log);

       response.sendRedirect("./ListBlog-admin");

    }
}
