package vn.edu.hcmuaf.fit.controller.admin;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "approval_Bill", value = "/admin/approval_Bill")
public class approval_Bill extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> listSize = new ArrayList<String>();
        listSize.add("Chờ Xác Nhận");
        listSize.add("Chuẩn Bị Hàng");
        listSize.add("Đang Giao Hàng");
        listSize.add("Giao Thành Công");
        request.setAttribute("listSize", listSize);
        request.getRequestDispatcher("edit-product.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
