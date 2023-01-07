package vn.edu.hcmuaf.fit.controller.admin;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
 @MultipartConfig
@WebServlet(name = "UploadFileServlet", value = "/UploadFileServlet")
public class UploadFileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("../Edit_Product?idP="+request.getParameter("masp")).forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String img = request.getParameter("img");
    }
}
