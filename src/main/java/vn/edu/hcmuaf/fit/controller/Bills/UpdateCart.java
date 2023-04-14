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
import java.util.HashMap;
import java.util.List;

@WebServlet(name = "UpdateCart", value = "/UpdateCart")
public class UpdateCart extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        User auth = (User) session.getAttribute("auth");
        int solgmua = Integer.parseInt(request.getParameter("soluong"));
        if(auth != null) {
            if (request.getParameter("masp") != null) {
                String maSP = request.getParameter("masp");
                Product product = ProductService.findById(maSP);
                if (product != null) {
                    CartService.updateQty(auth.getId(),maSP,solgmua);
                    List<ItemProductInCart> listItemC = CartService.findItemCartByIdUser(auth.getId());
                    session.setAttribute("itemCart", listItemC);
                }

                Log log = new Log();
                log.setLevel(2);
                log.setSrc(request.getServletPath());
                log.setContent("Cập nhật giỏ hàng");
                log.setUser(auth.getId());
                LogService.addLog(log);

            }
    }
            response.sendRedirect("shoping-cart.jsp");
    }
}
