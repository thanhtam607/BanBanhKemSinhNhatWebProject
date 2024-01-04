<%@ page import="java.util.List" %>
<%@ page import="vn.edu.hcmuaf.fit.model.Customer" %>
<%@ page import="vn.edu.hcmuaf.fit.model.Blog" %>
<%@ page import="java.util.List" %>
<%@ page import="vn.edu.hcmuaf.fit.model.Receipt" %>
<%@ page import="vn.edu.hcmuaf.fit.model.Bill_Detail" %>
<%@ page import="vn.edu.hcmuaf.fit.bean.User" %>
<%@ page import="vn.edu.hcmuaf.fit.service.ReceiptService" %>
<%@ page import="vn.edu.hcmuaf.fit.model.Bill_Detail" %>
<%@ page import="vn.edu.hcmuaf.fit.service.InforService" %>
<%@ page import="vn.edu.hcmuaf.fit.service.UserService" %>
<%@ page import="vn.edu.hcmuaf.fit.service.CustomerService" %>
<%@ page import="java.net.URLDecoder" %>
<%@ page contentType="text/html;charsetUTF-8" language="java" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Icon Font Stylesheet -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">
    <link rel="icon" href="../img/favicon.ico" type="image/x-icon"/>

    <!-- CSS -->
    <link rel="stylesheet" href="css/bootstrap-reboot.min.css">
    <link rel="stylesheet" href="css/bootstrap-grid.min.css">
    <link rel="stylesheet" href="css/magnific-popup.css">
    <link rel="stylesheet" href="css/jquery.mCustomScrollbar.min.css">
    <link rel="stylesheet" href="css/select2.min.css">
    <link rel="stylesheet" href="css/ionicons.min.css">
    <link href="css/style.css" rel="stylesheet">
    <!-- Google Web Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;600&family=Roboto:wght@500;700&display=swap"
          rel="stylesheet">


    <!-- Favicons -->
    <link rel="icon" type="image/png" href="icon/favicon-32x32.png" sizes="32x32">
    <link rel="apple-touch-icon" href="icon/favicon-32x32.png">

    <!-- boostrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <%--    css --%>
    <link rel="stylesheet" href="css/admin.css">
    <!--  css -->
    <link rel="stylesheet" href="./css/style.css">

    <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
    <link href="lib/tempusdominus/css/tempusdominus-bootstrap-4.min.css" rel="stylesheet"/>


    <meta name="description" content="">
    <meta name="keywords" content="">
    <meta name="author" content="Dmitry Volkov">
    <title>Admin |  <%=InforService.getInformation("NameShop").get(0).getContent()%>
    </title>
</head>

<body>
<% User auth = (User) session.getAttribute("auth");%>
<jsp:include page="spinner.jsp"></jsp:include>
<!-- header -->
<header class="header">
    <div class="header__content">
        <!-- header logo -->
        <a href="ListReceipt_Admin" class="header__logo">
            <img src="../img/logo_web.jpg" alt="">
        </a>
        <!-- end header logo -->

        <!-- header menu btn -->
        <button class="header__btn" type="button">
            <span></span>
            <span></span>
            <span></span>
        </button>
        <!-- end header menu btn -->
    </div>
</header>
<!-- end header -->

<!-- Sidebar Start -->
<div class="sidebar pe-4 pb-3">
    <nav class="navbar bg-pink navbar-dark">

        <div class="d-flex align-items-center ms-4 mb-4">
            <div class="position-relative">
                <i class="fa fa-user icon__user"></i>
                <div class="bg-success rounded-circle border border-2 border-white position-absolute end-0 bottom-0 p-1"></div>
            </div>
            <div class="ms-3">
                <h6 class="mb-0"><%= auth != null ? auth.getName() : "ADMIN"%>
                </h6>
                <span><%= auth != null ? URLDecoder.decode(auth.getRoleName(), "UTF-8") : "Admin"%></span>
            </div>
        </div>
        <div class="navbar-nav w-100">
            <a href="./ListReceipt_Admin" class="nav-item nav-link "><i class="fa fa-tachometer-alt me-2"></i>Tổng quan</a>
            <%--                    <a href="general_Management.jsp" class="nav-item nav-link"><i class="fa fa-user"></i>Quản lý chung</a>--%>
            <a href="./ListProduct_Admin" class="nav-item nav-link"><i class="fa fa-th me-2"></i>DS Sản Phẩm</a>

            <a href="./ListCustomer" class="nav-item nav-link"><i class="fa fa-th me-2"></i>DS Khách Hàng</a>
            <a href="./ListBlog-admin" class="nav-item nav-link"><i class="fa fa-th me-2"></i>DS Tin Tức</a>
            <a href="./ListReceipt_full_Admin" class="nav-item nav-link "><i class="fa fa-th me-2"></i>DS Đơn Hàng</a>
            <a href="feedbacks.jsp" class="nav-item nav-link"><i class="fa fa-th me-2"></i>DS Đánh giá</a>

            <a href="catalog_Management.jsp" class="nav-item nav-link"><i class="fa fa-file me-2"></i>QL danh mục</a>
            <a href="List_Discounts" class="nav-item nav-link"><i class="fa fa-birthday-cake me-2"></i>Khuyến mãi</a>
            <a href="../" class="nav-item nav-link"><i class="fa fa-arrow-alt-circle-right me-2"></i>Về trang
                chủ</a>
            <!--  -->
        </div>
    </nav>
</div>
<!-- Sidebar End -->

<!-- main content -->
<% Receipt receipt = (Receipt) request.getAttribute("receipt");
    List<Bill_Detail> receiptsDt = (List<Bill_Detail>) request.getAttribute("listcthdOfKH");
    String tenkh = (String) request.getAttribute("tenkh");%>
<main class="main bg-white">
    <div class="container-fluid bg-white">
        <!-- main title -->
        <div class="col-12">
            <div class="main__title">
                <h2>Chi tiết đơn hàng</h2>
            </div>
        </div>
        <div class="col-12 pt-2 pb-2 rounded bg-pink">
            <div class="col-12 d-flex justify-content-between form__content pl-0 pr-0">
                <h5 style="margin-bottom: 25px">Tên Tài Khoản: <%=tenkh%> -
                    SĐT: <%=CustomerService.getCusByIdAcc(receipt.getMakh()).getSDT()%>
                </h5>
                <h5 style="margin-bottom: 25px"> Mã ĐH: <%=receipt.getId()%>
                </h5>
            </div>
            <div class="col-12 d-flex form__content pl-0 pr-0">
                <div class="col-6 pl-0 pr-0">
                    <p class="pb-2 border-bottom px-3">Người
                        nhận: <%=ReceiptService.getGiaoHang(receipt.getId()).getTenKH() != null ? ReceiptService.getGiaoHang(receipt.getId()).getTenKH() : "" %>
                    </p>
                    <p class="pb-2 border-bottom px-3">SĐT người
                        nhận: <%=ReceiptService.getGiaoHang(receipt.getId()).getPhone() != null ? ReceiptService.getGiaoHang(receipt.getId()).getPhone() : ""%>
                    </p>
                    <p class="pb-2 border-bottom px-3">Email người
                        nhận: <%=ReceiptService.getGiaoHang(receipt.getId()).getEmail() != null ? ReceiptService.getGiaoHang(receipt.getId()).getEmail() : ""%>
                    </p>
                </div>
                <div class="col-6 pl-0 pr-0">
                    <p class="pb-2 border-bottom px-3">Ngày Lập: <%=receipt.getExport_date()%>
                    </p>
                    <p class="pb-2 border-bottom px-3">Ngày Giao Hàng: <%=receipt.getDelivery_date()%>
                    </p>
                    <p class="px-3">Địa Chỉ Giao: <%=receipt.getAddress()%>
                    </p>
                    <p class="px-3">Trạng Thái: <%=URLDecoder.decode(receipt.getStatusName(), "UTF-8")%>
                    </p>
                    <% if (receipt.isEdited()) { %>
                    <span class="text-danger font-italic small font-weight-bold">
                                    * Đơn hàng đã bị chỉnh sửa !!!
                    </span>
                    <% }%>
                </div>
            </div>
            <p style="font-weight: 600">Ghi Chú Chung: <%=receipt.getNote()%>
            </p>
            <div class="table-responsive margin-top-20px col-12 pl-0">
                <table class="table text-start align-middle table-bordered table-hover mb-0">
                    <thead>
                    <tr class="text-black">
                        <th scope="col">STT</th>
                        <th scope="col">Tên sản phẩm</th>
                        <th scope="col">Ghi chú</th>
                        <th scope="col">Số lượng</th>
                        <th scope="col">Đơn giá</th>
                        <th scope="col">Tổng</th>
                    </tr>
                    </thead>
                    <% for (int i = 0; i < receiptsDt.size(); i++) {
                        Bill_Detail rcs = receiptsDt.get(i);%>
                    <tbody>
                    <tr>
                        <td><%=i + 1%>
                        </td>
                        <td><%=rcs.getNamePro()%>
                        </td>
                        <td><%=rcs.getNote()%>
                        </td>
                        <td><%=rcs.getSolg()%>
                        </td>
                        <td><%=rcs.formatNum(rcs.getPrice())%>
                        </td>
                        <td><%=rcs.formatNum(rcs.getToTalPrice())%>
                        </td>
                    </tr>
                    </tbody>
                    <% } %>
                </table>
            </div>

            <div class="col-5 margin-top-20px">
                <label>Tổng tiền hàng: </label> &ensp;
                <span class="text-danger text-uppercase text-pink"><%=receipt.formatNum((int) receipt.getPro_bill())%> VND</span>
            </div>
            <div class="col-5 margin-top-20px">
                <label>Phí vận chuyển: </label> &ensp;
                <span class="text-danger text-uppercase text-pink"><%=receipt.formatNum((int) receipt.getFee_bill())%> VND</span>
            </div>
            <div class="col-5 margin-top-20px">
                <label>Tổng thanh toán: </label> &ensp;
                <span class="text-danger text-uppercase text-pink"><%=receipt.formatNum(receipt.getMoney())%> VND</span>
            </div>
            <%--          <% if(auth.getIsedit() == 1 || auth.getRole() == 2) { %>--%>
            <%--          <%if(receipt.getStatus() != 3){%>--%>
            <%--            <div class="main__table-btns">--%>
            <%--                <div class="col-5">--%>
            <%--                    <a href="AdminEditOrder?id=<%=receipt.getId()%>&tenkh=<%=UserService.findById(receipt.getMakh()).getName()%>"--%>
            <%--                       type="button" class="form__btn">Chỉnh sửa</a>--%>
            <%--                </div>--%>
            <%--            </div>--%>
            <%--          <%}%>--%>
            <%--          <% } else { %>--%>
            <%--          <% } %>--%>
        </div>

    </div>
    <!-- end form -->
    </div>
    </div>
</main>
<!-- end modal delete -->

<!-- Back to Top -->
<a href="#" class="btn btn-lg btn-primary btn-lg-square back-to-top"><i class="bi bi-arrow-up"></i></a>

<!-- JS -->
<script src="js/jquery-3.5.1.min.js"></script>
<script src="js/bootstrap.bundle.min.js"></script>
<script src="js/jquery.magnific-popup.min.js"></script>
<script src="js/jquery.mousewheel.min.js"></script>
<script src="js/jquery.mCustomScrollbar.min.js"></script>
<script src="js/select2.min.js"></script>
<script src="js/main.js"></script>
<script src="js/admin.js"></script>


</body>

</html>