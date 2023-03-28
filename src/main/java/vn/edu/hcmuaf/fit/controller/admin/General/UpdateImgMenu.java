package vn.edu.hcmuaf.fit.controller.admin.General;

import vn.edu.hcmuaf.fit.service.InforService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
@MultipartConfig
@WebServlet(name = "UpdateImgMenu", value = "/admin/UpdateImgMenu")
public class UpdateImgMenu extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("maimgmenu");
        Part p = request.getPart("img");
        String path = request.getServletContext().getRealPath("img");
        String filename = Path.of(p.getSubmittedFileName()).getFileName().toString();
        if(!Files.exists(Path.of(path))){
            Files.createDirectories(Path.of(path));
        }
        String img = path + "/" + filename;
        p.write(img);
        InforService.updateContent(id, "img/" + filename);
        response.sendRedirect("general_Management.jsp");
    }
}
