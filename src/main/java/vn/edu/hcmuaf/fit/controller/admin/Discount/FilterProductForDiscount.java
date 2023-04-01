package vn.edu.hcmuaf.fit.controller.admin.Discount;

import vn.edu.hcmuaf.fit.model.Discount;
import vn.edu.hcmuaf.fit.model.Image;
import vn.edu.hcmuaf.fit.model.Product;
import vn.edu.hcmuaf.fit.service.DiscountService;
import vn.edu.hcmuaf.fit.service.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "FilterProductForDiscount", value = "/admin/FilterProductForDiscount")
public class FilterProductForDiscount extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        List<Product> listProduct =ProductService.getListProductForAdmin();
        String loaiBanh = request.getParameter("loaiBanh");
        if(!loaiBanh.equals("Tất cả")){
            System.out.println(loaiBanh);
            listProduct =  ProductService.findByType(loaiBanh, ProductService.getListProductForAdmin());
        }

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
        request.setAttribute("type", request.getParameter("loaiBanh"));
        request.setAttribute("endPage", endPage);
        request.setAttribute("tag", page);
        request.setAttribute("listPa", listPa);
        PrintWriter out = response.getWriter();
        String image="";
        for (Product p: listPa){
            for(Image img :p.getListImg()){
                if(!img.delete()&& !img.isHide())
                {image=img.getImg();
                    break;}
            }
            out.println("<tr>\n" +
                    "                          <td class=\"check\" >\n" +
                    "                              <input class=\"form-check-input\" type=\"checkbox\" name=\"check\" value=\"\"></td>\n" +
                    "                          <td>\n" +
                    "                                      <img src=\"../"+image+"\" alt=\"\">\n" +
                    "                                        <a href=\"Edit_Product?idP="+p.getId()+"\">"+p.getName()+"</a>\n" +
                    "                              </td>\n" +
                    "                          <td>"+p.getType()+"</td>\n" +
                    "                          <td>"+p.getPrice()+"</td>\n" +
                    "                      </tr>");
        }

    }
}
