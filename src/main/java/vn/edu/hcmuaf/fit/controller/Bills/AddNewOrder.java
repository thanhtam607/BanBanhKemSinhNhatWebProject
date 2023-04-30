package vn.edu.hcmuaf.fit.controller.Bills;

import vn.edu.hcmuaf.fit.bean.User;
import vn.edu.hcmuaf.fit.model.ItemProductInCart;
import vn.edu.hcmuaf.fit.model.Log;
import vn.edu.hcmuaf.fit.model.Order;
import vn.edu.hcmuaf.fit.model.Delivery;
import vn.edu.hcmuaf.fit.service.CartService;
import vn.edu.hcmuaf.fit.service.LogService;
import vn.edu.hcmuaf.fit.service.OrderService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@WebServlet(name = "AddNewOrder", value = "/AddNewOrder")
public class AddNewOrder extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(true);
        User auth = (User) session.getAttribute("auth");
        List<ItemProductInCart> listItemC =(List<ItemProductInCart>) session.getAttribute("itemCart");

        String ten = request.getParameter("ten");
        String diachi = request.getParameter("diachi");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String ghichu = request.getParameter("ghichu");
        String totalBill = request.getParameter("totalBill");

        if(ghichu!=null && request.getParameter("haveDisk")!=null){
            ghichu +=", "+ request.getParameter("haveDisk");
        }
        String notes = request.getParameter("note");
        String[] notesForDetail = notes.split("/,");

        Date today = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String todayFM = formatter.format(today);

        Delivery gh = new Delivery();
        gh.setDiachigiao(diachi);
        gh.setNgayGiao(todayFM);
        gh.setEmail(email);
        gh.setPhone(phone);
        gh.setTenKH(ten);


        Order order = new Order(auth, listItemC, todayFM,Double.parseDouble(totalBill), ghichu,gh);

        if(notesForDetail!=null){
        for(int i =0; i< notesForDetail.length ;i++){
            order.getData().get(i).setNote(notesForDetail[i]);
        }}
        OrderService.addOrder(order);
        OrderService.addGiaoHang(order);

//        OrderService.updateTonKhoWhenAdd(order);
        session.setAttribute("itemCart",null);



        Log log = new Log();
        log.setLevel(1);
        log.setSrc(request.getServletPath());
        log.setContent("Thêm đơn đặt hàng mới");
        log.setUser(auth.getId());
        LogService.addLog(log);


        response.sendRedirect("MyOrder");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
