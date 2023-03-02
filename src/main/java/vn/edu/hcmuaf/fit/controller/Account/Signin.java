package vn.edu.hcmuaf.fit.controller.Account;

import vn.edu.hcmuaf.fit.bean.User;
import vn.edu.hcmuaf.fit.model.Customer;
import vn.edu.hcmuaf.fit.model.ItemProductInCart;
import vn.edu.hcmuaf.fit.service.CartService;
import vn.edu.hcmuaf.fit.service.CustomerService;
import vn.edu.hcmuaf.fit.service.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
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
        User user = UserService.getInstance().checkLogin(uname, pass);

        if(user==null){
            request.setAttribute("Error", "Tên đăng nhập hoặc mật khẩu không đúng!!!");
            request.getRequestDispatcher("/signin.jsp").forward(request, response);
        }else{
            if(user.checkStatus()){
                request.setAttribute("Error", "Tài Khoản Của Bạn Đã Bị Khóa! Không Thể Đăng Nhập!!");
                request.getRequestDispatcher("/signin.jsp").forward(request, response);
            }
            HttpSession session = request.getSession(true);
            session.setAttribute("auth", user);
            Customer customer = CustomerService.getCusByIdAcc(user.getAccount_id());
            session.setAttribute("cust", customer);

            List<ItemProductInCart> listItemCart = CartService.findItemCartByIdUser(user.getAccount_id());
            session.setAttribute("itemCart",listItemCart);
            response.sendRedirect(request.getContextPath() + "/Index");
        }


    }
}
