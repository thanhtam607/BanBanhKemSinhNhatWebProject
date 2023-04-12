package vn.edu.hcmuaf.fit.controller.admin.Product;

import vn.edu.hcmuaf.fit.bean.User;
import vn.edu.hcmuaf.fit.model.Log;
import vn.edu.hcmuaf.fit.service.LogService;
import vn.edu.hcmuaf.fit.service.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@MultipartConfig
@WebServlet(name = "UpdateImg", value = "/admin/Product/UpdateImg")
public class UpdateImg extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("../Edit_Product?idP=" + request.getParameter("masp")).forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String masp = request.getParameter("masp");
        Part p = request.getPart("img");
        String realPa = request.getServletContext().getRealPath("img/product/" + masp);
        String filename = Path.of(p.getSubmittedFileName()).getFileName().toString();
        String oldImg = request.getParameter("oldImg");
//        String location = ProductService.getLocation()+realPa+"/"+ filename;
//        String newImg = realPa + "/" + filename;
        if(!Files.exists(Path.of(realPa))){
            Files.createDirectories(Path.of(realPa));
        }
        String img = realPa + "/" + filename;
            p.write(img);
            ProductService.upProductImg(oldImg, "img/product/" + masp+"/" + filename);
        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute("auth");

        Log log = new Log();
        log.setLevel(2);
        log.setSrc(request.getServletPath());
        log.setContent("Cập nhật hình ảnh sản phẩm: "+ masp);
        log.setUser(user.getId());
        LogService.addLog(log);

        response.sendRedirect("../Edit_Product?idP="+masp);


    }
}
