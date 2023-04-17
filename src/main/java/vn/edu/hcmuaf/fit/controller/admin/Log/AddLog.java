package vn.edu.hcmuaf.fit.controller.admin.Log;

import vn.edu.hcmuaf.fit.model.Log;
import vn.edu.hcmuaf.fit.service.LogService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "AddLog", value = "/AddLog")
public class AddLog  extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String content = request.getParameter("content");
        String src = request.getParameter("src");
        int level = Integer.parseInt(request.getParameter("level"));
        Log log = new Log();
        log.setSrc(src);
        log.setContent(content);
        log.setUser(email);
        log.setLevel(level);
        LogService.addLog(log);
    }

}
