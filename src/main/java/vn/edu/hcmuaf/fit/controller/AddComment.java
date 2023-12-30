package vn.edu.hcmuaf.fit.controller;

import vn.edu.hcmuaf.fit.bean.User;
import vn.edu.hcmuaf.fit.model.Comment;
import vn.edu.hcmuaf.fit.model.Log;
import vn.edu.hcmuaf.fit.service.LogService;
import vn.edu.hcmuaf.fit.service.ProductService;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "AddComment", value = "/AddComment")
public class AddComment extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            String cmt = request.getParameter("cmt");
            HttpSession session = request.getSession(true);
            String maSp = (String) session.getAttribute("idProduct");
            String date = request.getParameter("date");
            User user =(User) session.getAttribute("auth");
            Comment newCmt = new Comment(maSp, user.getName(),cmt,date);
            ProductService.addComment(newCmt,user.getId());


            PrintWriter out = response.getWriter();
            out.println(" <div class=\"comment\">\n" +
                    "                                <h6 style=\"margin-bottom: 10px;\">"+newCmt.getkhachhang()+"</h6>\n" +
                    "                                <i class=\"fa fa-calendar-o\"></i> <span style=\"font-size: 13px; color: rgb(179, 178, 178);\">"+newCmt.getDate()+"</span>\n" +
                    "                                <p >"+newCmt.getBinhLuan()+"</p>\n" +
                    "                                </div>");

        out.close();

        Log log = new Log();
        log.setLevel(1);
        log.setSrc(request.getServletPath());
        log.setContent("Thêm bình luận về sản phẩm: "+ maSp);
        log.setUser(user.getId());
        LogService.addLog(log);


    }
}
