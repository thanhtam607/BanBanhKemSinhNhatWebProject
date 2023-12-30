package vn.edu.hcmuaf.fit.model.logistic;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "GetLeadTime", value = "/GetLeadTime")
public class GetLeadTime extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
//        System.out.println(controller.getCalculateFee(FROM_DISTRICT_ID,FROM_WARD_ID, "2270", "231013", 100, 100, 100, 100));
        String from_dist_id = "3695";
        String from_ward_id = "90737";
        String to_dist_id = request.getParameter("to_dist_id");
        String to_ward_id = request.getParameter("to_ward_id");
        int height = 12;
        int length = 20;
        int width = 20;
        int weight = 400;

        LogisticController controller = new LogisticController();
        String time = controller.getLeadTime(from_dist_id, from_ward_id, to_dist_id, to_ward_id, height, length, width, weight);
        String leadTime1 = time.replaceAll("[T]", " ");
        String leadTime2 = leadTime1.replaceAll("[Z]", " ");

        PrintWriter out = response.getWriter();
        out.println(leadTime2);
    }
}
