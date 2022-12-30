package vn.edu.hcmuaf.fit.controller;

import vn.edu.hcmuaf.fit.bean.User;
import vn.edu.hcmuaf.fit.model.FavoriteProduct;
import vn.edu.hcmuaf.fit.model.ItemProductInCart;
import vn.edu.hcmuaf.fit.model.Order;
import vn.edu.hcmuaf.fit.model.Product;
import vn.edu.hcmuaf.fit.service.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.HashMap;

@WebServlet(name = "AddToFavorite", value = "/AddToFavorite")
public class AddToFavorite extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        User auth = (User) session.getAttribute("auth");
        if(auth != null) {
            if (request.getParameter("masp") != null) {
                String maSP = request.getParameter("masp");
                Product product = ProductService.findById(maSP);
                if (product != null) {
                    if (session.getAttribute("listFavorite") == null) {
                        FavoriteProduct listLove = new FavoriteProduct();
                        HashMap<String, ItemProductInCart> listItems = new HashMap<>();
                        ItemProductInCart item = new ItemProductInCart();
                        item.setMasp(maSP);
                        item.setSp(product);
                        item.setPrice(product.getPrice());
                        listItems.put(maSP, item);
                        listLove.setData(listItems);
                        listLove.setUser(auth);
                        session.setAttribute("listFavorite", listLove);

                    }else {
                        FavoriteProduct listFavorite = (FavoriteProduct) session.getAttribute("listFavorite");
                        HashMap<String, ItemProductInCart> listItems = listFavorite.getData();
                        ItemProductInCart item = new ItemProductInCart();
                        item.setSp(product);
                        item.setMasp(maSP);
                        item.setPrice(product.getPrice());
                        listItems.put(maSP, item);
                        session.setAttribute("listFavorite", listFavorite);
                    }
                }
                response.sendRedirect(request.getContextPath() + "/favorites.jsp");
            }
        }else{
            response.sendRedirect(request.getContextPath() +"/favorites.jsp");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}