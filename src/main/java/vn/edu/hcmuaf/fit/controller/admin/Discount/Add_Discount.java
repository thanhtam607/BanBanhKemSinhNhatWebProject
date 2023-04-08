package vn.edu.hcmuaf.fit.controller.admin.Discount;

import vn.edu.hcmuaf.fit.bean.User;
import vn.edu.hcmuaf.fit.model.Discount;
import vn.edu.hcmuaf.fit.model.Image;
import vn.edu.hcmuaf.fit.model.Log;
import vn.edu.hcmuaf.fit.model.Product;
import vn.edu.hcmuaf.fit.service.DiscountService;
import vn.edu.hcmuaf.fit.service.LogService;
import vn.edu.hcmuaf.fit.service.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Add_Discount", value = "/admin/Add_Discount")
public class Add_Discount extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> listProduct = ProductService.getProductsNotDiscount();
        request.setAttribute("listProduct", listProduct);
        String numPage = request.getParameter("page");
        if(numPage == null || numPage.equals("0")){
            numPage = "1";
        }
        int page = Integer.parseInt(numPage);
        List<Product> listPa = new ArrayList<>();
        int begin = (page - 1) * 10;
        int endList = begin + 10;
        if (begin > listProduct.size() - 10) {
            endList = listProduct.size();
        }
        for (int i = begin; i < endList; i++) {
            listPa.add(listProduct.get(i));
        }
        int totalProduct = listProduct.size();
        int endPage = totalProduct / 10;
        if(totalProduct % 10 != 0){
            endPage++;
        }



        request.setAttribute("loaiBanh", "Tất cả");
        request.setAttribute("endPage", endPage);
        request.setAttribute("tag", page);
        request.setAttribute("listPa", listPa);
        request.getRequestDispatcher("add_discount.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        List<Product> listProduct= new ArrayList<>();
        double discount=Double.parseDouble(request.getParameter("discount"))/100;
        String start = request.getParameter("start").replace("-","/");
        String end = request.getParameter("end").replace("-","/");
        if(request.getParameter("loaiBanh")!=null){
        String loaiBanh= request.getParameter("loaiBanh");
        listProduct = ProductService.findByType(loaiBanh, ProductService.getDiscountProduct());
        }else{
            String[] listId= request.getParameter("ids").split(",");
            for(String id: listId){
                listProduct.add(ProductService.findById(id));
            }
        }
        List<Discount> listDiscount = new ArrayList<>();
        for(Product p: listProduct){
            listDiscount.add(new Discount(p.getId(), discount,start,end));
        }
        DiscountService.addListDiscounts(listDiscount);
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("auth");

        Log log = new Log();
        log.setLevel(1);
        log.setSrc(request.getServletPath());
        log.setContent("Thêm khuyến mãi sản phẩm");
        log.setUser(user.getId());
        LogService.addLog(log);

    }
}
