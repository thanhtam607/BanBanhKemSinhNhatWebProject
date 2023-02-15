package vn.edu.hcmuaf.fit.controller.admin.Blog;

import vn.edu.hcmuaf.fit.service.BlogService;
import vn.edu.hcmuaf.fit.service.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UpdateBlog", value = "/UpdateBlog")
public class UpdateBlog extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String mablog = request.getParameter("idB");
        String demuc = request.getParameter("demuc");
        String chitiet = request.getParameter("chitiet");
        BlogService.updateBlog(mablog, demuc, chitiet);
        response.sendRedirect("./EditBlog?idB="+ request.getParameter("idB"));
    }
}
