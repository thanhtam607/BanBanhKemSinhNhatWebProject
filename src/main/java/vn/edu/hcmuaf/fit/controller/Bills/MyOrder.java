package vn.edu.hcmuaf.fit.controller.Bills;

import vn.edu.hcmuaf.fit.bean.User;
import vn.edu.hcmuaf.fit.model.Receipt;
import vn.edu.hcmuaf.fit.service.ReceiptService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "MyOrder", value = "/MyOrder")
public class MyOrder extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(true);
//        Order order =(Order) session.getAttribute("order");
        User auth = (User) session.getAttribute("auth");

        if(auth != null){
            List<Receipt> receipts = null;
            try {
                receipts = ReceiptService.getReceiptByMakh(auth.getId());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            session.setAttribute("listRe", receipts);

            request.getRequestDispatcher("order.jsp").forward(request, response);
        }else{
            response.sendRedirect("signin.jsp");

        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    public static void main(String[] args) {
//        ReceiptService rs = new ReceiptService();
//        Order order = new Order(OrderService.getNewIdOrder(), );
//        System.out.println(rs.getReceiptByMahd("AD11"));
    }
}