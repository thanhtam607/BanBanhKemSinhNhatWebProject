package vn.edu.hcmuaf.fit.model.logistic;

import vn.edu.hcmuaf.fit.model.Log;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "GetWardName", value = "/GetWardName")
public class GetWardName extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String distID = request.getParameter("distID");

        LogisticController controller = new LogisticController();
        List<Ward> wardList = controller.getWardByDistrictID(distID);

        PrintWriter out = response.getWriter();
        out.println("<option selected disabled value=\"0\">Chọn Xã/Phường</option>");
        for(Ward ward: wardList){
            out.println("<option value="+ward.getWardCode()+">"+ward.getWardName()+"</option>");
        }
    }
}
