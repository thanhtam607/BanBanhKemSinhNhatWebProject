package vn.edu.hcmuaf.fit.controller;


import vn.edu.hcmuaf.fit.model.SignUser;
import vn.edu.hcmuaf.fit.security.KeyManager;
import vn.edu.hcmuaf.fit.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "AddPublicKey", value = "/AddPublicKey")
public class AddPublicKey extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String userId = request.getParameter("userId");
        String publicKey = request.getParameter("publicKey");

        List<SignUser> listKey = UserService.getListKey(userId);
        PrintWriter out = response.getWriter();

        // Kiểm tra xem publicKey có giá trị không
        if (publicKey != null && !publicKey.isEmpty()) {
            for (SignUser u : listKey) {
                // Kiểm tra xem publicKey có trùng với một khóa đã có trong danh sách không
                if (publicKey.equals(u.getPbkey())) {
                    out.println(1);
                    return;
                }
            }

            // Nếu publicKey không trùng với bất kỳ khóa nào trong danh sách, thêm nó vào danh sách
            try {
                if (KeyManager.userIsHasKey(userId)) {
                    KeyManager.updateExpireDateForKey(userId);
                    KeyManager.updateStatusForKey(userId, 2);
                }
                KeyManager.insertPublicKey(userId, publicKey);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            // Nếu publicKey không hợp lệ (null hoặc rỗng), có thể xử lý thông báo hoặc mã lỗi tùy thuộc vào yêu cầu
            out.println("Public key không hợp lệ!");
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
