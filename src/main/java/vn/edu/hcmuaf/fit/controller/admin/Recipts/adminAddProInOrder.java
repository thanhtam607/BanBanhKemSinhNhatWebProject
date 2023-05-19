package vn.edu.hcmuaf.fit.controller.admin.Recipts;

import vn.edu.hcmuaf.fit.bean.User;
import vn.edu.hcmuaf.fit.model.Receipt;
import vn.edu.hcmuaf.fit.service.OrderService;
import vn.edu.hcmuaf.fit.service.ProductService;
import vn.edu.hcmuaf.fit.service.ReceiptService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "adminAddProInOrder", value = "/admin/adminAddProInOrder")
public class adminAddProInOrder extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(true);
        User auth = (User) session.getAttribute("auth");

        String idRec = request.getParameter("idRec");
        String msp = request.getParameter("msp");
        int slg = Integer.parseInt(request.getParameter("slg"));
        String notes = request.getParameter("notes");

        String namePro = ProductService.findById(msp).getName();
        int pricePro = ProductService.findById(msp).getPromotional() != 0 ? ProductService.findById(msp).getPromotional() : ProductService.findById(msp).getPrice();
        int totalPrice = pricePro * slg;
        int index = ReceiptService.getListMaSpCTHD(idRec).size() + 1;

        PrintWriter out = response.getWriter();

            if(ReceiptService.getListMaSpCTHD(idRec).contains(msp)){
                ReceiptService.addCTHD(idRec, msp, slg, notes);
                out.println(1);
            } else if (ReceiptService.getReceiptByMahd(idRec).getStatus() == 3 || ReceiptService.getReceiptByMahd(idRec).getStatus() == 4) {
                out.println(2);
            } else {
//
                    out.println("                                 <tr id=\""+index+"\">\n" +
                            "                                        <td>"+index+"</td>\n" +
                            "                                        <td>"+msp+"</td>\n" +
                            "                                        <td>"+namePro+"</td>\n" +
                            "                                        <td>"+notes+"</td>\n" +
                            "                                        <td>"+slg+"</td>\n" +
                            "                                        <td>"+Receipt.formatNum(pricePro)+"</td>\n" +
                            "                                        <td>"+Receipt.formatNum(totalPrice)+"</td>\n" +
                            "\n" +
                            "                                        <td>\n" +
                            "                                            <button onclick=\"adminRemoveProInOrder('"+index+"')\" class=\" main__table-btn main__table-btn--delete px-1\">\n" +
                            "                                                <i class=\"fas fa-trash text-center\"></i>\n" +
                            "                                            </button>\n" +
                            "                                        </td>\n" +
                            "                                    </tr>");
                ReceiptService.addCTHD(idRec, msp, slg, notes);

            }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
