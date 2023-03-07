package vn.edu.hcmuaf.fit.controller.admin.Product;

import vn.edu.hcmuaf.fit.model.Product;
import vn.edu.hcmuaf.fit.service.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@WebServlet(name = "ListProduct_Admin", value = "/admin/ListProduct_Admin")
public class ListProduct_Admin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> list = ProductService.getListProductForAdmin();
        request.setAttribute("listpro", list);
        String sort = request.getParameter("sortValue");
        if (sort != null) {
            if (sort.equals("Sắp xếp theo tên")) {
                Collections.sort(list, new Comparator<Product>() {
                    @Override
                    public int compare(Product o1, Product o2) {
                        return o1.getName().compareTo(o2.getName());
                    }
                });
            }
            if (sort.equals("Sắp xếp theo loại")) {
                Collections.sort(list, new Comparator<Product>() {
                    @Override
                    public int compare(Product o1, Product o2) {
                        return o1.getType().compareTo(o2.getType());
                    }
                });
            }
            if (sort.equals("Giá từ cao đến thấp")) {
                list.sort((Product o1, Product o2) -> o2.getPrice() - o1.getPrice());
            }
            if (sort.equals("Giá từ thấp đến cao")) {
                list.sort((Product o1, Product o2) -> o1.getPrice() - o2.getPrice());
            }
        }

        String numPage = request.getParameter("page");
        if(numPage == null || numPage.equals("0")){
            numPage = "1";
        }
        int page = Integer.parseInt(numPage);
        List<Product> listPa = ProductService.getPaginationPageOwn(page, list);
        int totalProduct = ProductService.getToTalProduct();
        int endPage = totalProduct / 15;
        if(totalProduct % 15 != 0){
            endPage++;
        }

        request.setAttribute("endPage", endPage);
        request.setAttribute("tag", page);
        request.setAttribute("listPa", listPa);
        request.getRequestDispatcher("list-products.jsp").forward(request,response);

    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    public static void main(String[] args) {

    }
}

