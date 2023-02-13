package vn.edu.hcmuaf.fit.controller.admin.Blog;

import vn.edu.hcmuaf.fit.model.Blog;
import vn.edu.hcmuaf.fit.service.BlogService;

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
        Blog blog = BlogService.findById("idB");
        request.setAttribute("blog",blog);
        List<String> listdm = new ArrayList<String>();
        listdm.add("Đời sống");
        listdm.add("Kỉ niệm");
        listdm.add("Khác");
        List<String> listcd = new ArrayList<String>();
        listcd.add("Hiện đại");
        listcd.add("Bảo quản bánh");
        listcd.add("Chọn bánh");
        listcd.add("Ý nghĩa bánh");
        listcd.add("Khác");
        request.setAttribute("listDm", listdm);
        request.setAttribute("listCd", listcd);
        request.getRequestDispatcher("edit-blog.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
