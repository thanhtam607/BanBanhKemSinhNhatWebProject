package vn.edu.hcmuaf.fit.controller;

import vn.edu.hcmuaf.fit.bean.User;
import vn.edu.hcmuaf.fit.model.Product;
import vn.edu.hcmuaf.fit.service.ProductService;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
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


        String sort = request.getParameter("sortValue");

        List<Product> listFilter = ProductService.getListProduct();

        if(session.getAttribute("filter")!=null){
            String filter = session.getAttribute("filter").toString();
            listFilter = ProductService.findBySize(filter, listFilter);
            if(listFilter.isEmpty()){
                listFilter = ProductService.findByType(filter, ProductService.getListProduct());

            }
        }
        if(session.getAttribute("filter")==null) {
            if (session.getAttribute("search")!= null) {
                listFilter = ProductService.findByName(session.getAttribute("search").toString(), ProductService.getListProduct());

            }
            if (session.getAttribute("search")== null){

                if (session.getAttribute("pricemin") != null && session.getAttribute("pricemax") != null){
                    String p_min = session.getAttribute("pricemin").toString();
                    String p_max = session.getAttribute("pricemax").toString();
                    listFilter = ProductService.filterByPrice(Integer.parseInt(p_min), Integer.parseInt(p_max), ProductService.getListProduct());
                }
            }
        }
        if(sort != null ){
            if(sort.equals("Giá từ thấp đến cao") ) {

                listFilter.sort((Product o1, Product o2) -> o1.getPrice() - o2.getPrice());
            }
            if(sort.equals("Giá từ cao đến thấp") ){
                listFilter.sort((Product o1, Product o2) -> o2.getPrice() - o1.getPrice());
            }

        }
        String numPage = request.getParameter("pageName");
        if(numPage == null){
            numPage = "1";
        }
        int page = Integer.parseInt(numPage);
        List<Product> listF = ProductService.getPaginationPageOwn(page, listFilter);
        int endPageFilter = listFilter.size() / 15;
        if(listFilter.size() % 15 != 0){
            endPageFilter++;
        }
        request.setAttribute("endPageFt", endPageFilter);
        request.setAttribute("tagPage", page);
        request.setAttribute("sortValue", sort);
        User auth = (User) session.getAttribute("auth");
        PrintWriter out = response.getWriter();
        for(Product p: listF) {
            String img = p.getListImg().get(0).getImg();
            String s = null;
            if(auth==null){
                s= "<li><a onclick=\"notLogged()\"><i class=\"fa fa-heart\"></i></a></li>\n"+
                        "    <li><a onclick=\"notLogged()\"><i class=\"fa fa-shopping-cart\"></i></a></li>\n";
            }
            else{
                s= "  <li><a href=\"AddToFavorite?masp="+p.getId()+"><i class=\"fa fa-heart\"></i></a></li>\n"+

                        "  <li><a onclick=\"addToCartI('"+p.getId()+"')\"><i class=\"fa fa-shopping-cart\"></i></a></li>\n" ;
            }


            out.println("<div class=\"col-lg-4 col-md-6 col-sm-6\">\n" +
                    "                            <div class=\"product__item\">\n" +
                    "                               <div class=\"product__item__pic set-bg\" data-setbg=\""+img+ "\" style=\"background-image: url("+img+");\">\n" +
                    "                                    <ul class=\"product__item__pic__hover\">\n" +
                                                            s+
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

        out.close();
    }

}
