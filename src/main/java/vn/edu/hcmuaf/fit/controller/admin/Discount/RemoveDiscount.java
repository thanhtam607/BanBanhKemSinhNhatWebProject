package vn.edu.hcmuaf.fit.controller.admin.Discount;

import vn.edu.hcmuaf.fit.model.Discount;
import vn.edu.hcmuaf.fit.service.DiscountService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "RemoveDiscount", value = "/admin/RemoveDiscount")
public class RemoveDiscount extends  HttpServlet{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        DiscountService.removeDiscount(id);
        response.sendRedirect("List_Discounts");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
