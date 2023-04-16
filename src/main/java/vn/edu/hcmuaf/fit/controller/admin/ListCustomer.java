package vn.edu.hcmuaf.fit.controller.admin;

import vn.edu.hcmuaf.fit.model.Customer;
import vn.edu.hcmuaf.fit.model.Product;
import vn.edu.hcmuaf.fit.service.CustomerService;
import vn.edu.hcmuaf.fit.service.ProductService;
import vn.edu.hcmuaf.fit.service.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@WebServlet(name = "ListCustomer", value = "/admin/ListCustomer")
public class ListCustomer extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Customer> listCus = CustomerService.getListCustomer();
        request.setAttribute("listCus", listCus);
        String sort = request.getParameter("sortValue");
        if (sort != null) {
            if (sort.equals("Sắp xếp theo tên")) {
                Collections.sort(listCus, new Comparator<Customer>() {
                    @Override
                    public int compare(Customer o1, Customer o2) {
                        return UserService.findById(o1.getMAKH()).getName().compareTo(UserService.findById(o2.getMAKH()).getName());
                    }
                });
            }
            if (sort.equals("Sắp xếp theo địa chỉ")) {
                Collections.sort(listCus, new Comparator<Customer>() {
                    @Override
                    public int compare(Customer o1, Customer o2) {
                        return o1.getDIACHI().compareTo(o2.getDIACHI());
                    }
                });
            }
        }
        request.getRequestDispatcher("list-customers.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
