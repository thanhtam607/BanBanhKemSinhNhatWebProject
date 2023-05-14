package vn.edu.hcmuaf.fit.model.logistic;

import vn.edu.hcmuaf.fit.service.CartService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "GetFee", value = "/GetFee")
public class GetFee extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String from_dist_id = "3695";
        String from_ward_id = "90737";
        String to_dist_id = request.getParameter("to_dist_id");
        String to_ward_id = request.getParameter("to_ward_id");
        int height = 12;
        int length = 20;
        int width = 20;
        int weight = 400;

        LogisticController controller = new LogisticController();
        double feeOfBill = controller.getCalculateFee(from_dist_id, from_ward_id, to_dist_id, to_ward_id, height, length, width, weight);

        PrintWriter out = response.getWriter();
        out.println(CartService.formatNum(feeOfBill));
//        int height = Integer.parseInt(request.getParameter(""));
//        int length = Integer.parseInt(request.getParameter(""));
//        int width = Integer.parseInt(request.getParameter(""));
//        int weight = Integer.parseInt(request.getParameter(""));


    }
}
