package vn.edu.hcmuaf.fit.controller.admin.Product;

import vn.edu.hcmuaf.fit.bean.User;
import vn.edu.hcmuaf.fit.model.Log;
import vn.edu.hcmuaf.fit.service.LogService;
import vn.edu.hcmuaf.fit.service.ProductService;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "hideImage", value = "/admin/hideImage")
public class hideImage extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String img = request.getParameter("img");
        ProductService.hideImg(img);

        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute("auth");

        Log log = new Log();
        log.setLevel(3);
        log.setSrc(request.getServletPath());
        log.setContent("Ẩn hình ảnh: "+ img);
        log.setUser(user.getId());
        LogService.addLog(log);

        request.getRequestDispatcher("Edit_Product?idP="+request.getParameter("idP")).forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}