package vn.edu.hcmuaf.fit.controller.admin.Product;

import vn.edu.hcmuaf.fit.bean.User;
import vn.edu.hcmuaf.fit.model.Log;
import vn.edu.hcmuaf.fit.service.LogService;
import vn.edu.hcmuaf.fit.service.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "DeleteProduct", value = "/admin/DeleteProduct")
public class DeleteProduct extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String masp = request.getParameter("masp");
        ProductService.removeProduct(masp);

        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute("auth");

        Log log = new Log();
        log.setLevel(3);
        log.setSrc(request.getServletPath());
        log.setContent("Xóa sản phẩm: "+ masp);
        log.setUser(user.getId());
        LogService.addLog(log);

        response.sendRedirect("ListProduct_Admin");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }
}
