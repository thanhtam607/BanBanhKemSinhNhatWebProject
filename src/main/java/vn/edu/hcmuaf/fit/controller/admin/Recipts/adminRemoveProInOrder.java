package vn.edu.hcmuaf.fit.controller.admin.Recipts;

import vn.edu.hcmuaf.fit.bean.User;
import vn.edu.hcmuaf.fit.service.ReceiptService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "adminRemoveProInOrder", value = "/admin/adminRemoveProInOrder")
public class adminRemoveProInOrder extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(true);
        User auth = (User) session.getAttribute("auth");

        String id = request.getParameter("id");
        String msp = request.getParameter("msp");
        int slg = Integer.parseInt(request.getParameter("slg"));
        if(ReceiptService.getListMaSpCTHD(id).size() == 1 || ReceiptService.getReceiptByMahd(id).getStatus() == 3
        || ReceiptService.getReceiptByMahd(id).getStatus() == 4){
            PrintWriter out = response.getWriter();
            out.println(1);
            out.close();
        }else{
            ReceiptService.deleteProInCTHD(id, msp, slg);
        }
//            response.sendRedirect("./AdminEditOrder?id="+id);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
