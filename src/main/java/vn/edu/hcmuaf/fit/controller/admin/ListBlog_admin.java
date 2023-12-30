package vn.edu.hcmuaf.fit.controller.admin;

import vn.edu.hcmuaf.fit.model.Blog;
import vn.edu.hcmuaf.fit.service.BlogService;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@WebServlet(name = "ListBlog-admin", value = "/admin/ListBlog-admin")
public class ListBlog_admin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Blog> list = BlogService.getData();
        request.setAttribute("list", list);
        String sort = request.getParameter("sortValue");
        if (sort != null) {
            if (sort.equals("Theo tiêu đề")) {
                Collections.sort(list, new Comparator<Blog>() {
                    @Override
                    public int compare(Blog o1, Blog o2) {
                        return o1.getTitle().compareTo(o2.getTitle());
                    }
                });
            }
            if (sort.equals("Theo danh mục")) {
                Collections.sort(list, new Comparator<Blog>() {
                    @Override
                    public int compare(Blog o1, Blog o2) {
                        return o1.getCategory().compareTo(o2.getCategory());
                    }
                });
            }
            if (sort.equals("Theo ngày đăng")) {
                Collections.sort(list, new Comparator<Blog>() {
                    @Override
                    public int compare(Blog o1, Blog o2) {
                        return o1.getDate().compareTo(o2.getDate());
                    }
                });
            }
        }
        request.getRequestDispatcher("list-blogs.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
