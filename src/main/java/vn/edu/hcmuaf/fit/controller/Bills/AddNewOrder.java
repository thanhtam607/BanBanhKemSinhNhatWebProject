package vn.edu.hcmuaf.fit.controller.Bills;

import vn.edu.hcmuaf.fit.bean.User;
import vn.edu.hcmuaf.fit.model.*;
import vn.edu.hcmuaf.fit.security.RSA;
import vn.edu.hcmuaf.fit.service.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
        List<ItemProductInCart> listItemC = (List<ItemProductInCart>) session.getAttribute("itemCart");

        double price_pro_bill = 0;
        for (ItemProductInCart inCart : listItemC) {
            price_pro_bill += inCart.getPrice();
        }

        String ten = request.getParameter("ten");
        String diachi = request.getParameter("diachi");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String ghichu = request.getParameter("ghichu");
        String totalBill = request.getParameter("totalBill");
        String fee = request.getParameter("fee");

        String huyen = request.getParameter("huyen");
        String xa = request.getParameter("xa");

        String leadTime = request.getParameter("leadTime");

        if (ghichu != null && request.getParameter("haveDisk").equals("true")) {
            ghichu += ", " + request.getParameter("haveDiskName");
        } else if (ghichu == null && request.getParameter("haveDisk").equals("true")) {
            ghichu += request.getParameter("haveDiskName");
            ghichu.replace(',', ' ');
        }


        String notes = request.getParameter("note");
        String[] notesForDetail = notes.split("/,");


        Date today = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String todayFM = formatter.format(today);


        Delivery gh = new Delivery();
        gh.setDiachigiao(diachi);
        gh.setHuyen(huyen);
        gh.setXa(xa);
        gh.setNgayGiao(leadTime);
        gh.setEmail(email);
        gh.setPhone(phone);
        gh.setTenKH(ten);


        Order order = new Order(auth, listItemC, todayFM, Double.parseDouble(totalBill), ghichu, gh, price_pro_bill, Double.parseDouble(fee));
        order.setId(OrderService.getNewIdOrder());

        for (int i = 0; i < notesForDetail.length; i++) {
            order.getData().get(i).setNote(notesForDetail[i]);
        }
        gh.setMahd(order.getId());
//        hash order here
        List<Bill_Detail> billDetailList = new ArrayList<>();
        for (ItemProductInCart item : order.getData()) {
            Product p = ProductService.findById(item.getSp().getId());
            int price = p.getPromotional() != 0 ? p.getPromotional() : p.getPrice();
            billDetailList.add(new Bill_Detail(order.getId(), item.getSp().getId(), item.getSoLgMua(), item.getNote(), price));

        }
        Receipt receipt = new Receipt(order.getId(), order.getUser().getId(), todayFM, ghichu, price_pro_bill, Double.parseDouble(fee), billDetailList, gh);

//==  hash order here
        String cypherText = "";
        String privateKey = request.getParameter("keyContent");
        String publicKey = ReceiptService.getPbKeyString(receipt, UserService.getListKey(order.getUser().getId()));
        PrintWriter out = response.getWriter();
        try {
            if(RSA.areKeyPairsMatching(privateKey, publicKey)) {
                cypherText = ReceiptService.createCypherText(receipt, privateKey);

                OrderService.addOrder(order, cypherText);
                OrderService.addGiaoHang(order);

                OrderService.updateTonKhoWhenAdd(order);
                session.setAttribute("itemCart", null);


                Log log = new Log();
                log.setLevel(1);
                log.setSrc(request.getServletPath());
                log.setContent("Thêm đơn đặt hàng mới");
                log.setUser(auth.getId());
                LogService.addLog(log);


                response.sendRedirect("MyOrder");

            }else{
                out.println(1);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}