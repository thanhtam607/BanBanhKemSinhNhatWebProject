package vn.edu.hcmuaf.fit.controller.Bills;

import vn.edu.hcmuaf.fit.bean.User;
import vn.edu.hcmuaf.fit.model.ItemProductInCart;
import vn.edu.hcmuaf.fit.model.Log;
import vn.edu.hcmuaf.fit.model.Order;
import vn.edu.hcmuaf.fit.model.Product;
import vn.edu.hcmuaf.fit.service.CartService;
import vn.edu.hcmuaf.fit.service.LogService;
import vn.edu.hcmuaf.fit.service.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@WebServlet(name = "RemoveToCart", value = "/RemoveToCart")
public class RemoveToCart extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        User auth = (User) session.getAttribute("auth");
        List<ItemProductInCart> listItemC = (List<ItemProductInCart>) session.getAttribute("itemCart");
        if (auth != null) {
            if (request.getParameter("masp") != null) {
                String maSP = request.getParameter("masp");
                Product product = ProductService.findById(maSP);
                if (product != null) {
                    CartService.removeToCart(auth.getId(),maSP);
                    listItemC= CartService.findItemCartByIdUser(auth.getId());


                }

                Log log = new Log();
                log.setLevel(3);
                log.setSrc(request.getServletPath());
                log.setContent("Xóa sản phẩm khỏi giỏ hàng");
                log.setUser(auth.getId());
                LogService.addLog(log);

            }
        }
        session.setAttribute("itemCart", listItemC);
        response.sendRedirect("shoping-cart.jsp");
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
