package vn.edu.hcmuaf.fit.controller;

import vn.edu.hcmuaf.fit.bean.User;
import vn.edu.hcmuaf.fit.model.Cart;
import vn.edu.hcmuaf.fit.model.Product;
import vn.edu.hcmuaf.fit.service.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.HashMap;

@WebServlet(name = "AddToCart", value = "/AddToCart")
public class AddToCart extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String maSP = request.getParameter("masp");
        Product product = ProductService.findById(maSP);

//        int solgmua = Integer.parseInt(request.getParameter("solgmua"));
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        User auth = (User) session.getAttribute("auth");
        if(auth != null){
            HashMap<String, Product> listP = new HashMap<>();

            if(cart == null){
                cart = new Cart();
                listP.put(maSP, product);
                cart.setData(listP);
            }
            if(cart.get(maSP) != null){
                cart.setData(listP);
                cart.put(maSP, 1);
//                cart.list().add(product);
                response.sendRedirect("/BanBanhKemSinhNhatWebProject/shoping-cart.jsp");
            }else{
                Product p = ProductService.findById(maSP);
//                cart.list().add(p);
                cart.setData(listP);
                if(p != null) cart.put(p);
                response.sendRedirect("/BanBanhKemSinhNhatWebProject/shoping-cart.jsp");
            }
            session.setAttribute("cart", cart);
        }
        else{
            response.sendRedirect("/BanBanhKemSinhNhatWebProject/signin.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
