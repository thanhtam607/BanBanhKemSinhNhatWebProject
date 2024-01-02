package vn.edu.hcmuaf.fit.controller.Account;



import vn.edu.hcmuaf.fit.bean.User;
import vn.edu.hcmuaf.fit.security.KeyManager;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
        HttpSession session = request.getSession(true);
        User auth = (User) session.getAttribute("auth");
        try {
            if(KeyManager.userIsHasKeyActive(auth.getId())){
                KeyManager.disableKey(auth.getId());
            }
            KeyManager.genKey(auth.getId());
        } catch (SQLException | NoSuchAlgorithmException | MessagingException e) {
            throw new RuntimeException(e);
        }
    }

}
