package vn.edu.hcmuaf.fit.controller.admin.Product;

import vn.edu.hcmuaf.fit.bean.User;
import vn.edu.hcmuaf.fit.model.Log;
import vn.edu.hcmuaf.fit.service.LogService;
import vn.edu.hcmuaf.fit.service.ProductService;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
@MultipartConfig
@WebServlet(name = "AddImage", value = "/admin/AddImage")
public class AddImage extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String masp = request.getParameter("masp");
        Part p = request.getPart("newImg");
        String realPa = request.getServletContext().getRealPath("img/product/" + masp);
        String filename = Path.of(p.getSubmittedFileName()).getFileName().toString();
//        String location = ProductService.getLocation()+realPa+"/"+ filename;
        String img = realPa + "/" + filename;
        File file = new File(img);

        if(file.exists()){
            file.delete();

            ProductService.addImgForPro(masp, "img/product/" + masp+"/" + filename);
        }
        else {
            p.write(img);

            ProductService.addImgForPro(masp, "img/product/" + masp+"/" + filename);
        }
        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute("auth");

        Log log = new Log();
        log.setLevel(1);
        log.setSrc(request.getServletPath());
        log.setContent("Thêm hình ảnh cho sản phẩm: "+ masp);
        log.setUser(user.getId());
        LogService.addLog(log);
        response.sendRedirect("../Edit_Product?idP="+ masp);

    }
}
