package vn.edu.hcmuaf.fit.controller.admin.Product;

import vn.edu.hcmuaf.fit.bean.User;
import vn.edu.hcmuaf.fit.model.Log;
import vn.edu.hcmuaf.fit.model.Product;
import vn.edu.hcmuaf.fit.service.LogService;
import vn.edu.hcmuaf.fit.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Edit_Product", value = "/admin/Edit_Product")
public class Edit_Product extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Product p = ProductService.findById(request.getParameter("idP"));
        request.setAttribute("product",p);
        List<String> listSize = new ArrayList<String>();
        listSize.add("Bé");
        listSize.add("Nhỏ");
        listSize.add("Vừa");
        listSize.add("Lớn");
        request.setAttribute("listSize", listSize);

        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute("auth");

        Log log = new Log();
        log.setLevel(2);
        log.setSrc(request.getServletPath());
        log.setContent("Cập nhật thông tin sản phẩm: "+ p.getId());
        log.setUser(user.getId());
        LogService.addLog(log);
        if (user.getIsedit() == 1 || user.getRole() == 2) {
            request.getRequestDispatcher("edit-product.jsp").forward(request, response);
        } else{
            request.getRequestDispatcher("error_page.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
