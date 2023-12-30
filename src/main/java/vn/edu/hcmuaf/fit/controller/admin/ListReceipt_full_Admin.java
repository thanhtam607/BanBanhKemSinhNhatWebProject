package vn.edu.hcmuaf.fit.controller.admin;

import vn.edu.hcmuaf.fit.model.Receipt;
import vn.edu.hcmuaf.fit.service.ReceiptService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@WebServlet(name = "ListReceipt_full_Admin", value = "/admin/ListReceipt_full_Admin")
public class ListReceipt_full_Admin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Receipt> lr = null;
        try {
            lr = ReceiptService.getAllReceipt();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("listreceipt-full", lr);
        String sort = request.getParameter("sortValue");
        if (sort != null) {
            if (sort.equals("Theo ngày đặt")) {
                Collections.sort(lr, new Comparator<Receipt>() {
                    @Override
                    public int compare(Receipt o1, Receipt o2) {
                        return o2.getDelivery_date().compareTo(o1.getDelivery_date());
                    }
                });
            }
            if (sort.equals("Theo đơn giá")) {
                lr.sort((Receipt o1, Receipt o2) -> o2.getMoney() - o1.getMoney());
            }
        }
            request.getRequestDispatcher("list-order.jsp").forward(request, response);

    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
