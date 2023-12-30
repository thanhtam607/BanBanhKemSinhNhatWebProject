package vn.edu.hcmuaf.fit.controller.admin.General;

import vn.edu.hcmuaf.fit.bean.User;
import vn.edu.hcmuaf.fit.model.Log;
import vn.edu.hcmuaf.fit.service.InforService;
import vn.edu.hcmuaf.fit.service.LogService;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@MultipartConfig
@WebServlet(name = "UpdateImgFooter", value = "/admin/UpdateImgFooter")
public class UpdateImgFooter extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("mafooter");
        Part p = request.getPart("img");
        String path = request.getServletContext().getRealPath("img");
        String filename = Path.of(p.getSubmittedFileName()).getFileName().toString();
        if(!Files.exists(Path.of(path))){
            Files.createDirectories(Path.of(path));
        }
        String img = path + "/" + filename;
        p.write(img);
        InforService.updateContent(id, "img/" + filename);

        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute("auth");

        Log log = new Log();
        log.setLevel(2);
        log.setSrc(request.getServletPath());
        log.setContent("Cập nhật hình ảnh trong footer");
        log.setUser(user.getId());
        LogService.addLog(log);


        response.sendRedirect("general_Management.jsp");
    }
}
