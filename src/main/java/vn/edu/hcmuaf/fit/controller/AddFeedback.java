package vn.edu.hcmuaf.fit.controller;

import vn.edu.hcmuaf.fit.model.Feedback;
import vn.edu.hcmuaf.fit.service.FeedbackService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AddFeedback", value = "/AddFeedback")
public class AddFeedback extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("yourName");
        String email = request.getParameter("yourEmail");
        String fe = request.getParameter("feedback");
        String date = request.getParameter("date");
        Feedback fb = new Feedback(name, email,fe,date);
        FeedbackService.addFeedback(fb);

    }
}
