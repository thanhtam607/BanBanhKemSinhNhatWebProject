package vn.edu.hcmuaf.fit.controller;

import vn.edu.hcmuaf.fit.bean.User;
import vn.edu.hcmuaf.fit.model.FavoriteProduct;
import vn.edu.hcmuaf.fit.model.Order;
import vn.edu.hcmuaf.fit.model.Product;
import vn.edu.hcmuaf.fit.service.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "RemoveFavorites", value = "/RemoveFavorites")
public class RemoveFavorites extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        User auth = (User) session.getAttribute("auth");
        FavoriteProduct favoriteProduct = (FavoriteProduct) session.getAttribute("listFavorite");
        if(auth != null) {
            if (request.getParameter("masp") != null) {
                String maSP = request.getParameter("masp");
                Product product = ProductService.findById(maSP);
                if (product != null) {
                    if(favoriteProduct.getData().containsKey(maSP)){
                        favoriteProduct.getData().remove(maSP);
                    }
                }
                request.getRequestDispatcher("/favorites.jsp").forward(request, response);
            }
        }else{
            response.sendRedirect("/BanBanhKemSinhNhatWebProject/signin.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
