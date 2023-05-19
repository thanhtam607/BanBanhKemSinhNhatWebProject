package vn.edu.hcmuaf.fit.controller.admin.Recipts;

import vn.edu.hcmuaf.fit.bean.User;
import vn.edu.hcmuaf.fit.model.Log;
import vn.edu.hcmuaf.fit.model.logistic.LogisticController;
import vn.edu.hcmuaf.fit.service.LogService;
import vn.edu.hcmuaf.fit.service.ReceiptService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "EditOrderUpdateDelivery", value = "/admin/EditOrderUpdateDelivery")
public class EditOrderUpdateDelivery extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String dayD = request.getParameter("dayD");
        String address = request.getParameter("address");
        String id = request.getParameter("id");
        String stName = request.getParameter("state");

        List<String> statusName = ReceiptService.getAllStatusNameOrder();
        int st = 0;
        if(stName.equals(statusName.get(0))){
            st = 0;
        } else if (stName.equals(statusName.get(1))){
            st = 1;
        } else if (stName.equals(statusName.get(2))){
            st = 2;
        }else if (stName.equals(statusName.get(3))){
            st = 3;
        }else if (stName.equals(statusName.get(4))){
            st = 4;
        }
        ReceiptService.updateDeliveryInBill(id, dayD, address);

        if(st == 2){
            LogisticController controller = new LogisticController();
            controller.registerTranport(controller.FROM_DISTRICT_ID, controller.FROM_WARD_ID, ReceiptService.getListGiaoHang(id).getHuyen(), ReceiptService.getListGiaoHang(id).getXa(), 100, 100,100, 400);
            ReceiptService.updateState(id, st);
        }else{
            ReceiptService.updateState(id, st);
        }

        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute("auth");

        Log log = new Log();
        log.setLevel(2);
        log.setSrc(request.getServletPath());
        log.setContent("Sửa thông tin giao hàng của đơn hàng: "+ id);
        log.setUser(user.getId());
        LogService.addLog(log);
        request.setAttribute("id", id);
//        response.sendRedirect("./AdminEditOrder?id="+ id);
    }
}
