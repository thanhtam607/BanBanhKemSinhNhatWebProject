package vn.edu.hcmuaf.fit.controller.admin;

import vn.edu.hcmuaf.fit.model.Receipt;
import vn.edu.hcmuaf.fit.service.ReceiptService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "ListReceipt_Admin", value = "/admin/ListReceipt_Admin")
public class ListReceipt_Admin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Receipt> lr = null;
        try {
            lr = ReceiptService.getAllReceipt();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("listreceipt", lr);

        String doanhthuthangnay = Receipt.formatNum(ReceiptService.getDoanhThuThisMonth());
        request.setAttribute("doanhthuthangnay", doanhthuthangnay);

        String doanhthuhomnay = Receipt.formatNum(ReceiptService.getDoanhThuToDay());
        request.setAttribute("doanhthuhomnay", doanhthuhomnay);

        int solgSPbandcthangnay = ReceiptService.getNumberProToDay();
        request.setAttribute("solgSPbandcthangnay", solgSPbandcthangnay);

        int soDHhomnay = ReceiptService.getAllReceiptToDay().size();
        request.setAttribute("soDHhomnay", soDHhomnay);

        Map<String, Integer> map = ReceiptService.getAllCakeThisMonth();
        request.setAttribute("map-hot", map);

//        HttpSession session = request.getSession();
//        User auth= (User) session.getAttribute("auth");
//        if(auth==null || !auth.checkRole()){
//            request.setAttribute("error","Bạn không có quyền truy cập");
//            response.sendRedirect("../blank_page.jsp");
//            return;
//        }
        request.getRequestDispatcher("admin-web.jsp").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
