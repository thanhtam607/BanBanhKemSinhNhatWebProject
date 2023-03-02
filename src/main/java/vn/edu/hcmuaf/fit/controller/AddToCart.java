package vn.edu.hcmuaf.fit.controller;

//import com.sun.org.apache.xpath.internal.operations.Or;
import vn.edu.hcmuaf.fit.bean.User;
import vn.edu.hcmuaf.fit.model.Customer;
import vn.edu.hcmuaf.fit.model.ItemProductInCart;
import vn.edu.hcmuaf.fit.model.Order;
import vn.edu.hcmuaf.fit.model.Product;
import vn.edu.hcmuaf.fit.service.CartService;
import vn.edu.hcmuaf.fit.service.CustomerService;
import vn.edu.hcmuaf.fit.service.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@WebServlet(name = "AddToCart", value = "/AddToCart")
public class AddToCart extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        User auth = (User) session.getAttribute("auth");

        int solgmua = Integer.parseInt(request.getParameter("soluong"));
        if(auth != null) {
            if (request.getParameter("masp") != null) {
                String maSP = request.getParameter("masp");
                Product product = ProductService.findById(maSP);
                if (product != null && product.isExistNumber(solgmua)) {
                    if(request.getParameter("solgmua") != null){
                        solgmua = Integer.parseInt(request.getParameter("solgmua"));
                    }
                    Order order = new Order();
                    if (session.getAttribute("order") == null) {

                        HashMap<String, ItemProductInCart> listItems = new HashMap<>();
                        ItemProductInCart item = new ItemProductInCart();
                        item.setSoLgMua(solgmua);
                        item.setSp(product);
                        listItems.put(maSP, item);
                        order.setData(listItems);
                        order.setUser(auth);
                        order.setTrangthai(0);
                        order.setGiaohang(null);
                        session.setAttribute("order", order);

                    } else {
                         order = (Order) session.getAttribute("order");
                        HashMap<String, ItemProductInCart> listItems = order.getData();
                        ItemProductInCart item = listItems.get(maSP);
                        if(item != null){
                            item.quantityUp(solgmua);
                        }else{
                            item = new ItemProductInCart();
                            item.setSp(product);
                            item.setSoLgMua(solgmua);
//                            item.setPrice(product.getPrice());
                            listItems.put(maSP, item);
                        }
                        session.setAttribute("order", order);

                    }
                }
//                request.getRequestDispatcher("/shoping-cart.jsp").forward(request, response);



            }
        }

            response.sendRedirect("shoping-cart.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
