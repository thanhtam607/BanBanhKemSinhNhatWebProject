package vn.edu.hcmuaf.fit.controller.Bills;

import vn.edu.hcmuaf.fit.bean.User;
import vn.edu.hcmuaf.fit.model.*;
import vn.edu.hcmuaf.fit.service.LogService;
import vn.edu.hcmuaf.fit.service.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@WebServlet(name = "AddToFavorite", value = "/Favorite")
public class AddToFavorite extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        User auth = (User) session.getAttribute("auth");
        List<Product> listHotProduct = ProductService.getHotProduct();
        request.setAttribute("listBanChay", listHotProduct);

        if(auth != null) {
            if (request.getParameter("masp") != null) {
                String maSP = request.getParameter("masp");
                Product product = ProductService.findById(maSP);
                FavoriteProduct listFavorite = new FavoriteProduct();
                HashMap<String, ItemProductInCart> listItems = new HashMap<>();

                ItemProductInCart item = new ItemProductInCart();
                listFavorite.setData(listItems);
                if (product != null) {
                    item.setSp(product);
                    item.setCustomer_id(auth.getId());
//                    item.setPrice(product.getPrice());

                    if (session.getAttribute("listFavorite") == null) {

                        listItems.put(maSP, item);
                        listFavorite.setData(listItems);
                        listFavorite.setUser(auth);


                    }else {
                        listFavorite = (FavoriteProduct) session.getAttribute("listFavorite");
                        listItems = listFavorite.getData();
                        listItems.put(maSP, item);

                    }

                }



                Log log = new Log();
                log.setLevel(1);
                log.setSrc(request.getServletPath());
                log.setContent("Thêm sản phẩm " + maSP+" vào danh mục yêu thích");
                log.setUser(auth.getId());
                LogService.addLog(log);

                session.setAttribute("listFavorite", listFavorite);


            }

        }

        if(session.getAttribute("listFavorite")!=null){
            FavoriteProduct list = (FavoriteProduct) session.getAttribute("listFavorite");
            if(list.list().isEmpty()){
                response.sendRedirect("ListProduct");
            }
            else {
                request.getRequestDispatcher("favorites.jsp").forward(request, response);
            }
        }

        else{

            response.sendRedirect("ListProduct");
        }

//        response.sendRedirect("favorites.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}