package vn.edu.hcmuaf.fit.controller.Account;

import vn.edu.hcmuaf.fit.bean.User;
import vn.edu.hcmuaf.fit.security.KeyManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

@WebServlet(name = "ReportKey", value = "/ReportKey")
public class ReportKey extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(true);
        User auth = (User) session.getAttribute("auth");
        String missing = request.getParameter("missing");
        PrintWriter out = response.getWriter();
        try {
            KeyManager.updateMissingDateForKey(auth.getId(),missing);
            KeyManager.updateReportDateForKey(auth.getId());
            KeyManager.disableKey(auth.getId());
            out.println(1);

        } catch (SQLException e) {
            if(e.getMessage().contains("chk1"))
            out.println(2);
            else
            out.println(3);
        }

    }
}
