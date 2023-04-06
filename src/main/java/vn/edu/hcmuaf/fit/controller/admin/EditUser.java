package vn.edu.hcmuaf.fit.controller.admin;

import vn.edu.hcmuaf.fit.model.Customer;
import vn.edu.hcmuaf.fit.model.Receipt;
import vn.edu.hcmuaf.fit.service.CustomerService;
import vn.edu.hcmuaf.fit.service.ProductService;
import vn.edu.hcmuaf.fit.service.ReceiptService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "EditUser", value = "/admin/EditUser")
public class EditUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String makh = request.getParameter("makh");

        List<String> listRole = new ArrayList<>();
        listRole.add("Thường");
        listRole.add("Admin");
        listRole.add("Quản Lí");
        request.setAttribute("listRole", listRole);

        List<Receipt> listctkh = ReceiptService.getctkh(makh);

        request.setAttribute("listmakh", listctkh);
        request.setAttribute("mkh", makh);

        request.getRequestDispatcher("edit-user.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
