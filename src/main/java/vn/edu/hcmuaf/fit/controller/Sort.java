package vn.edu.hcmuaf.fit.controller;

import vn.edu.hcmuaf.fit.model.Product;
import vn.edu.hcmuaf.fit.service.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "Sort", value = "/Sort")
public class Sort extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(true);
        String filter = session.getAttribute("filter").toString();
        List<Product> listFilter = ProductService.findBySize(filter);
        String sort = request.getParameter("sortValue");

        if(listFilter.isEmpty()){
            listFilter = ProductService.findByType(filter);
        }
        if(listFilter.isEmpty()){
            listFilter = ProductService.findByName(request.getParameter("key"));

        }
        if(sort != null ){
            if(sort.equals("Giá từ thấp đến cao") ) {

                listFilter.sort((Product o1, Product o2) -> o1.getPrice() - o2.getPrice());
            }
            if(sort.equals("Giá từ cao đến thấp") ){
                listFilter.sort((Product o1, Product o2) -> o2.getPrice() - o1.getPrice());
            }

        }
        PrintWriter out = response.getWriter();
        for(Product p: listFilter) {
            String img = p.getListImg().get(0);
            out.println("<div class=\"col-lg-4 col-md-6 col-sm-6\">\n" +
                    "                            <div class=\"product__item\">\n" +
                    "                               <div class=\"product__item__pic set-bg\" data-setbg=\""+img+ "\" style=\"background-image: url("+img+");\">\n" +
                    "                                    <ul class=\"product__item__pic__hover\">\n" +
                    "                                        <li><a href=\"AddToFavorite?masp="+p.getId()+"\"><i class=\"fa fa-heart\"></i></a></li>\n" +
                    "                                        <li><a href=\"AddToCart?masp="+p.getId()+"\"><i class=\"fa fa-shopping-cart\"></i></a></li>\n" +
                    "                                    </ul>\n" +
                    "                                </div>\n" +
                    "                                <div class=\"product__item__text\">\n" +
                    "                                    <h6><a href=\"ProductDetail?id="+p.getId()+"\">"+p.getName()+"</a></h6>\n" +
                    "                                    <h5>"+ p.formatNum(p.getPrice())+" VND</h5>\n" +
                    "                                </div>\n" +
                    "                            </div>\n" +
                    "                        </div>"
            );
        }

    }
}
