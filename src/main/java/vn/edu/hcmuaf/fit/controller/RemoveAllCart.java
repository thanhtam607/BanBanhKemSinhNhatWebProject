package vn.edu.hcmuaf.fit.controller;

import vn.edu.hcmuaf.fit.bean.User;
import vn.edu.hcmuaf.fit.model.ItemProductInCart;
import vn.edu.hcmuaf.fit.model.Product;
import vn.edu.hcmuaf.fit.service.CartService;
import vn.edu.hcmuaf.fit.service.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
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
            response.sendRedirect("shoping-cart.jsp");
        }
}
