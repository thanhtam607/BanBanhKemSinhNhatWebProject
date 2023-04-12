package vn.edu.hcmuaf.fit.controller.admin.Blog;

import vn.edu.hcmuaf.fit.bean.User;
import vn.edu.hcmuaf.fit.model.Blog;
import vn.edu.hcmuaf.fit.model.Log;
import vn.edu.hcmuaf.fit.service.BlogService;
import vn.edu.hcmuaf.fit.service.LogService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "EditBlog", value = "/admin/EditBlog")
public class EditBlog extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Blog blog = BlogService.findById(request.getParameter("idB"));
        request.setAttribute("blg",blog);
        List<String> listdm = BlogService.listcate();
        List<String> listcd = BlogService.listss();
        request.setAttribute("listDm", listdm);
        request.setAttribute("listCd", listcd);

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("auth");
        Log log = new Log();
        log.setLevel(2);
        log.setSrc(request.getServletPath());
        log.setContent("Sửa bài viết" + request.getParameter("idB"));
        log.setUser(user.getId());
        LogService.addLog(log);

        request.getRequestDispatcher("edit-blog.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
