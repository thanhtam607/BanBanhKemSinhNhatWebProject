package vn.edu.hcmuaf.fit.controller.admin.Blog;

import vn.edu.hcmuaf.fit.bean.User;
import vn.edu.hcmuaf.fit.model.Log;
import vn.edu.hcmuaf.fit.service.BlogService;
import vn.edu.hcmuaf.fit.service.LogService;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
@MultipartConfig
@WebServlet(name = "UpdateImgBlog", value = "/admin/UpdateImgBlog")
public class UpdateImgBlog extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("../EditBlog?idB=" + request.getParameter("idblog")).forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String idblog = request.getParameter("idblog");
            Part p = request.getPart("img");
            String path = request.getServletContext().getRealPath("img/blog/" + idblog);
            String filename = Path.of(p.getSubmittedFileName()).getFileName().toString();
             String oldImg = request.getParameter("oldImg");
            if(!Files.exists(Path.of(path))){
                Files.createDirectories(Path.of(path));
              }
        String img = path + "/" + filename;
            p.write(img);
        BlogService.updateImgBlog(oldImg, "img/blog/" + idblog+"/" + filename);

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("auth");
        Log log = new Log();
        log.setLevel(2);
        log.setSrc(request.getServletPath());
        log.setContent("Sửa hình ảnh bài viết: " + idblog);
        log.setUser(user.getId());
        LogService.addLog(log);

        response.sendRedirect("./EditBlog?idB=" + request.getParameter("idblog"));
    }
}
