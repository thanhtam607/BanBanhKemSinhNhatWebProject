package vn.edu.hcmuaf.fit.controller.admin.General;

import vn.edu.hcmuaf.fit.service.InforService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UpdateSocialNetwork", value = "/admin/UpdateSocialNetwork")
public class UpdateSocialNetwork extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String idfacebook = request.getParameter("idfacebook");
        String facebook= request.getParameter("facebook");
        String idmess = request.getParameter("idmess");
        String mess= request.getParameter("mess");
        String idmap = request.getParameter("idmap");
        String map= request.getParameter("map");
        String idinsta = request.getParameter("idinsta");
        String insta= request.getParameter("insta");
        InforService.updateContent(idfacebook, facebook);
        InforService.updateContent(idmap, map);
        InforService.updateContent(idmess, mess);
        InforService.updateContent(idinsta, insta);
        response.sendRedirect("general_Management.jsp");
    }
}
