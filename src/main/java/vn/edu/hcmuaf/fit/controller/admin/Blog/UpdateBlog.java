package vn.edu.hcmuaf.fit.controller.admin.Blog;

import vn.edu.hcmuaf.fit.model.Blog;
import vn.edu.hcmuaf.fit.service.BlogService;
import vn.edu.hcmuaf.fit.service.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UpdateBlog", value = "/admin/Blog/UpdateBlog")
public class UpdateBlog extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
     int i = 5;
     while(i < 1){
            String idB = "idB" + i;
            String idctB = "idctB" + i;
            String topic = "topic" + i;
            String content = "content" + i;
            String topic_after = "topic_after" + i;
            String content_after = "content_after" + i;

            String mablog = request.getParameter(idB);
            String mactb = request.getParameter(idctB);
            String demuc = request.getParameter(topic);
            String chitiet = request.getParameter(content);
            String demucs = request.getParameter(topic_after);
            String chitiets = request.getParameter(content_after);
            BlogService.updateBlog(mactb, demuc, chitiet, demucs, chitiets);
        }
            response.sendRedirect("../ListBlog-admin");
        }

    public static void main(String[] args) {
        for(int i = 1;  i < 5; i++) {
            String idB = "idB" + i;
            System.out.println("idB" + (i + 1));
        }
    }
    }
