package vn.edu.hcmuaf.fit.controller;

import com.google.protobuf.TextFormat;
import vn.edu.hcmuaf.fit.bean.User;
import vn.edu.hcmuaf.fit.model.Comment;
import vn.edu.hcmuaf.fit.model.Product;
import vn.edu.hcmuaf.fit.service.ProductService;
import vn.edu.hcmuaf.fit.service.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
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
//          String userID = request.getParameter("userId");
            User user =(User) session.getAttribute("auth");
            Comment newCmt = new Comment(maSp, user.getAccount_name(),cmt,date);
            ProductService.addComment(newCmt,user.getAccount_id());


            PrintWriter out = response.getWriter();
            out.println(" <div class=\"comment\">\n" +
                    "                                <h6 style=\"margin-bottom: 10px;\">"+newCmt.getkhachhang()+"</h6>\n" +
                    "                                <i class=\"fa fa-calendar-o\"></i> <span style=\"font-size: 13px; color: rgb(179, 178, 178);\">"+newCmt.getDate()+"</span>\n" +
                    "                                <p >"+newCmt.getBinhLuan()+"</p>\n" +
                    "                                </div>");

    }
}
