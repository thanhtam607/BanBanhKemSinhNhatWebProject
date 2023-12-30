package vn.edu.hcmuaf.fit.controller.Bills;

import vn.edu.hcmuaf.fit.bean.User;
import vn.edu.hcmuaf.fit.model.ItemProductInCart;
import vn.edu.hcmuaf.fit.model.Log;
import vn.edu.hcmuaf.fit.service.CartService;
import vn.edu.hcmuaf.fit.service.LogService;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "RemoveAllCart", value = "/RemoveAllCart")
public class RemoveAllCart extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            HttpSession session = request.getSession(true);
            User auth = (User) session.getAttribute("auth");
            List<ItemProductInCart> listItemC =null;
            if (auth != null) {
                CartService.removeAllCart(auth.getId());
            }
            session.setAttribute("itemCart", listItemC);



        Log log = new Log();
        log.setLevel(3);
        log.setSrc(request.getServletPath());
        log.setContent("Xóa tất cả sản phẩm trong giỏ hàng");
        log.setUser(auth.getId());
        LogService.addLog(log);

        response.sendRedirect("shoping-cart.jsp");
        }
}
