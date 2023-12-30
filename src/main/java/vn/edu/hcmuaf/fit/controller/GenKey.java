package vn.edu.hcmuaf.fit.controller;



import vn.edu.hcmuaf.fit.security.KeyManager;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

@WebServlet(name = "GenKey", value = "/GenKey")
public class GenKey extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String userId = request.getParameter("userId");
        try {
            KeyManager.genKey(userId);
        } catch (SQLException | NoSuchAlgorithmException | MessagingException e) {
            throw new RuntimeException(e);
        }
    }

}
