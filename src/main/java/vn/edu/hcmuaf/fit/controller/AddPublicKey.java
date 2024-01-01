package vn.edu.hcmuaf.fit.controller;


import vn.edu.hcmuaf.fit.security.KeyManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "AddPublicKey", value = "/AddPublicKey")
public class AddPublicKey extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String userId = request.getParameter("userId");
        String publicKey = request.getParameter("publicKey");
        try {
            if(KeyManager.userIsHasKey(userId)){
                KeyManager.updateExpireDateForKey(userId);
                KeyManager.updateStatusForKey(userId, 2);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            KeyManager.insertPublicKey(userId, publicKey);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
