package vn.edu.hcmuaf.fit.controller.Account;

import vn.edu.hcmuaf.fit.bean.User;
import vn.edu.hcmuaf.fit.model.Customer;
import vn.edu.hcmuaf.fit.model.ItemProductInCart;
import vn.edu.hcmuaf.fit.model.Log;
import vn.edu.hcmuaf.fit.security.KeyManager;
import vn.edu.hcmuaf.fit.service.CartService;
import vn.edu.hcmuaf.fit.service.CustomerService;
import vn.edu.hcmuaf.fit.service.LogService;
import vn.edu.hcmuaf.fit.service.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "Signin", value = "/doSignin")
public class Signin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uname = request.getParameter("username");
        String pass = request.getParameter("pass");
        if( request.getParameter("pass").isEmpty()){
            request.setAttribute("Error", "Mật khẩu không được để trống!!!");
            request.getRequestDispatcher("/signin.jsp").forward(request, response);
        }else {
            User user = UserService.getInstance().checkLogin(uname, pass);
            Log log = new Log();
            log.setSrc(request.getServletPath() );
            if (user == null) {
                log.setLevel(2);
                log.setUser(uname);
                log.setContent("Nhập sai thông tin đăng nhập");
                request.setAttribute("Error", "Tên đăng nhập hoặc mật khẩu không đúng!!!");
                request.getRequestDispatcher("/signin.jsp").forward(request, response);
            } else {
                log.setLevel(1);
                log.setUser(user.getId());
                if (user.checkStatus()) {
                    request.setAttribute("Error", "Tài Khoản Của Bạn Đã Bị Khóa! Không Thể Đăng Nhập!!");
                    request.getRequestDispatcher("/signin.jsp").forward(request, response);
                    log.setContent("Đăng nhập vào trang web thất bại(do tài khoản bị khóa)");
                }
                HttpSession session = request.getSession(true);
                session.setAttribute("auth", user);
                Customer customer = CustomerService.getCusByIdAcc(user.getId());
                session.setAttribute("cust", customer);

                String previousPageUrl = (String) session.getAttribute("previousPageUrl");
                try {
                    session.setAttribute("userNeedsKey", KeyManager.userIsHasKey(user.getId()));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                if (previousPageUrl != null && !previousPageUrl.contains("/signin.jsp") && !previousPageUrl.contains("/doSignin")) {
//                    response.getWriter().println(previousPageUrl);
                    response.sendRedirect(previousPageUrl);
                } else {
                    response.sendRedirect("./");
                }

                log.setContent("Đăng nhập thành công vào trang web");
                List<ItemProductInCart> listItemCart = CartService.findItemCartByIdUser(user.getId());
                session.setAttribute("itemCart", listItemCart);

            }
            LogService.addLog(log);
        }

    }
}
