package vn.edu.hcmuaf.fit.controller.Bills;

//import com.sun.org.apache.xpath.internal.operations.Or;

import vn.edu.hcmuaf.fit.bean.User;
import vn.edu.hcmuaf.fit.model.ItemProductInCart;
import vn.edu.hcmuaf.fit.model.Log;
import vn.edu.hcmuaf.fit.model.Product;
import vn.edu.hcmuaf.fit.service.CartService;
import vn.edu.hcmuaf.fit.service.LogService;
import vn.edu.hcmuaf.fit.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "AddToCart", value = "/AddToCart")
public class AddToCart extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        User auth = (User) session.getAttribute("auth");
        List<ItemProductInCart> listItemC = new ArrayList<>();
        int solgmua = Integer.parseInt(request.getParameter("soluong"));
        if(auth != null) {
            if (request.getParameter("masp") != null) {
                String maSP = request.getParameter("masp");
                Product product = ProductService.findById(maSP);
                if (product != null && product.isExistNumber(solgmua)) {
                        CartService.addToCart(auth.getId(), maSP, solgmua);
                        listItemC = CartService.findItemCartByIdUser(auth.getId());
                        session.setAttribute("itemCart", listItemC);
                }
//                request.getRequestDispatcher("/shoping-cart.jsp").forward(request, response);
            }
        }


        Log log = new Log();
        log.setLevel(1);
        log.setSrc(request.getServletPath());
        log.setContent("Thêm sản phẩm vào gi hàng");
        log.setUser(auth.getId());
        LogService.addLog(log);

        response.sendRedirect("shoping-cart.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
