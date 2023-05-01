<%@ page import="java.util.List" %>
<%@ page import="vn.edu.hcmuaf.fit.model.Blog" %>
<%--<%@ page import="vn.edu.hcmuaf.fit.model.Receipt" %>--%>
<%--<%@ page import="vn.edu.hcmuaf.fit.model.Bill_Detail" %>--%>
<%--<%@ page import="vn.edu.hcmuaf.fit.model.Bill_Detail" %>--%>
<%--<%@ page import="vn.edu.hcmuaf.fit.service.InforService" %>--%>
<%@ page import="vn.edu.hcmuaf.fit.service.UserService" %>
<%@ page import="vn.edu.hcmuaf.fit.model.*" %>
<%@ page import="vn.edu.hcmuaf.fit.bean.User" %>
<%@ page import="vn.edu.hcmuaf.fit.service.*" %>
<%@ page contentType="text/html;charsetUTF-8" language="java" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Icon Font Stylesheet -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

    <!-- CSS -->
    <link rel="stylesheet" href="css/bootstrap-reboot.min.css">
    <link rel="stylesheet" href="css/bootstrap-grid.min.css">
    <link rel="stylesheet" href="css/magnific-popup.css">
    <link rel="stylesheet" href="css/jquery.mCustomScrollbar.min.css">
    <link rel="stylesheet" href="css/select2.min.css">
    <link rel="stylesheet" href="css/ionicons.min.css">
    <link href="css/style.css" rel="stylesheet">


    <!-- Favicons -->
    <link rel="icon" type="image/png" href="icon/favicon-32x32.png" sizes="32x32">
    <link rel="apple-touch-icon" href="icon/favicon-32x32.png">

    <!-- boostrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <%--    css --%>
    <link rel="stylesheet" href="css/admin.css">
    <!-- index css -->
    <link rel="stylesheet" href="./css/style.css">

    <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
    <link href="lib/tempusdominus/css/tempusdominus-bootstrap-4.min.css" rel="stylesheet"/>


    <meta name="description" content="">
    <meta name="keywords" content="">
    <meta name="author" content="Dmitry Volkov">
    <title>Admin |  <%=InforService.getInformation("NameShop").get(0).getContent()%></title>
</head>

<body>
<% User auth = (User) session.getAttribute("auth");%>
<jsp:include page="spinner.jsp"></jsp:include>
<!-- header -->
<header class="header">
    <div class="header__content">
        <!-- header logo -->
        <a href="ListReceipt_Admin" class="header__logo">
            <img src="../<%=InforService.getImgLogo().get(0).getContent()%>" alt="">
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
                <span><%= auth != null ? auth.getRoleName() : "Admin"%></span>
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
            <a href="../Index" class="nav-item nav-link"><i class="fa fa-arrow-alt-circle-right me-2"></i>Về trang
                chủ</a>
            <!--  -->
        </div>
    </nav>
</div>
<!-- Sidebar End -->

<!-- main content -->
<% Receipt receipt = (Receipt) request.getAttribute("receipt");
    List<Bill_Detail> receiptsDt = (List<Bill_Detail>) request.getAttribute("listcthdOfKH");
    List<String> statusName = (List<String>) request.getAttribute("listStatus");%>
<main class="main bg-white">
    <div class="container-fluid bg-white">
        <!-- main title -->
        <div class="col-12">
            <div class="main__title">
                <h2>Chỉnh sửa đơn hàng - <span id="idRe"><%=receipt.getId()%></span></h2>
            </div>
        </div>
        <div class="tab-content" id="myTabContent">
            <div class="tab-pane fade show active" id="tab-1" role="tabpanel" aria-labelledby="1-tab">
                <div class="col-12">
                    <div class="row">
                        <!-- details form -->
                        <div class="col-12 col-lg-6">
<%--                            <form action="EditOrderUpdateCustomer?id=<%=receipt.getId()%>" method="post" class="form form--profile">--%>
                            <form onsubmit="editOrderUpdateCustomer()" class="form form--profile">
                                <div class="row row--form">
                                    <h4 class="form__title">Thông tin khách hàng</h4>
                                    <div class="col-12 col-md-12 col-lg-12 col-xl-12">
                                        <div class="form__group">
                                            <input class="d-none" id="idRec2" name="idRec2" value="<%=receipt.getId()%>">
                                            <label class="form__label" for="usernameRE">Người nhận</label>
                                            <input id="usernameRE" type="text" name="usernameRE" class="form__input"
                                                   value="<%=ReceiptService.getListGiaoHang(receipt.getId()).getTenKH()%>">
                                        </div>
                                        <div class="form__group">
                                            <label class="form__label" for="phoneRE">SĐT người nhận</label>
                                            <input id="phoneRE" type="text" name="phoneRE" class="form__input"
                                                   value="<%=ReceiptService.getListGiaoHang(receipt.getId()).getPhone()%>" maxlength="10">
                                        </div>
                                        <div class="form__group">
                                            <label class="form__label" for="mailRE">Email người nhận</label>
                                            <input id="mailRE" type="email" name="mailRE" class="form__input"
                                                   value="<%=ReceiptService.getListGiaoHang(receipt.getId()).getEmail()%>">
                                        </div>
                                    </div>

                                    <div class="col-12 col-md-12 col-lg-12 col-xl-12">
                                        <div class="form__group">
                                            <label class="form__label" for="note">Ghi chú ĐH</label>
                                            <input type="text" id="note" name="note" class="form__input text-lowercase"
                                                   value="<%=receipt.getNote()%>">
                                        </div>
                                    </div>

                                    <div class="col-6">
<%--                                        <input class="form__btn" type="submit" value="Lưu thông tin">--%>
                                        <button onclick="editOrderUpdateCustomer()" type="button" class="form__btn flex-row-reverse">Lưu thay đổi</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div class="col-12 col-lg-6">
<%--                            <form action="EditOrderUpdateDelivery?id=<%=receipt.getId()%>" method="post" class="form form--profile">--%>
                            <form class="form form--profile">
                                <div class="row row--form">
                                    <h4 class="form__title">Thông tin giao hàng</h4>
                                    <div class="col-12 col-md-12 col-lg-12 col-xl-12">
                                        <div class="form__group">
                                            <label class="form__label" for="dayE">Ngày lập</label>
                                            <input type="datetime" id="dayE" class="form__input text-lowercase form-check-input is-invalid" readonly
                                                   value="<%=receipt.getExport_date()%>">
                                        </div>
                                    </div>
                                    <div class="col-12 col-md-12 col-lg-12 col-xl-12">
                                        <div class="form__group">
                                            <input class="d-none" id="idR" name="idR" value="<%=receipt.getId()%>">
                                            <label class="form__label" for="dayD">Ngày giao</label>
                                            <input type="datetime" id="dayD" name="dayD" class="form__input text-lowercase"
                                                   value="<%=receipt.getDelivery_date()%>">
                                        </div>
                                    </div>
                                    <div class="col-12 col-md-12 col-lg-12 col-xl-12">
                                        <div class="form__group">
                                            <label class="form__label" for="address">Địa chỉ giao</label>
                                            <input type="text" id="address" name="address" class="form__input text-lowercase"
                                                   value="<%=receipt.getAddress()%>">
                                        </div>
                                    </div>
                                    <div class="col-12 col-md-12 col-lg-12 col-xl-12">
                                        <div class="form__group">
                                            <label class="form__label" for="rights">Trạng thái ĐH</label>
                                            <select class="form__input state" id="rights" name="rights">
                                                <% for(String status : statusName){
                                                        if(status.equals(receipt.getStatusName())){%>
                                                <option selected value="<%=status%>"><%=status%></option>
                                                <% } else if (receipt.getStatus() == 3) {%>
                                                     <option disabled value="<%=status%>"><%=status%></option>
                                                <%} else if (receipt.getStatus() == 2) {%>
                                                <option <%=status.equals("Đã hủy") ? "disabled": " "%> value="<%=status%>"><%=status%></option>
                                                <%} else {%>
                                                <option value="<%=status%>"><%=status%></option>
                                                <%}}%>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="col-6 mt-lg-4">
<%--                                        <input class="form__btn" type="submit" value="Lưu thông tin">--%>
                                        <button onclick="editOrderUpdateDelivery()" type="button" class="form__btn flex-row-reverse">Lưu thay đổi</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div class="col-12">
                            <div class="table-responsive margin-top-20px col-12 pl-0">
                                <table id="table_bill_details" class="table text-start align-middle table-bordered table-hover mb-0">
                                    <thead>
                                    <tr class="text-black">
                                        <th scope="col">STT</th>
                                        <th scope="col">Mã sản phẩm</th>
                                        <th scope="col">Tên sản phẩm</th>
                                        <th scope="col">Ghi chú</th>
                                        <th scope="col">Số lượng</th>
                                        <th scope="col">Đơn giá</th>
                                        <th scope="col">Tổng</th>
                                        <th scope="col">Tùy chọn</th>
                                    </tr>
                                    </thead>
                                    <% for (int i = 0; i < receiptsDt.size(); i++){
                                        Bill_Detail rcs = receiptsDt.get(i);%>
                                    <tbody>
                                    <tr id="<%=i + 1%>">
                                        <td><%=i + 1%></td>
                                        <td><%=rcs.getMasp()%></td>
                                        <td><%=rcs.getNamePro()%></td>
                                        <td><%=rcs.getNote()%></td>
                                        <td><%=rcs.getSolg()%></td>
                                        <td><%=rcs.formatNum(rcs.getPrice())%></td>
                                        <td><%=rcs.formatNum(rcs.getToTalPrice())%></td>

                                        <td>
                                            <%if(receipt.getStatus() != 4 || receipt.getStatus() != 3){%>
                                            <button onclick="adminRemoveProInOrder('<%=i+1%>')" class=" main__table-btn main__table-btn--delete px-1">
                                                <i class="fas fa-trash text-center"></i>
                                            </button>
                                            <%}%>
                                        </td>
                                    </tr>

                                    </tbody>

                                    <!-- modal status -->
                                    <div id="modal-view" class="zoom-anim-dialog mfp-hide modal modal--view">
                                        <h6 class="modal__title">Thêm Sản Phẩm</h6>

                                        <div class="form form--profile">
                                            <div class="row row--form">
                                                <div class="col-12 col-md-12 col-lg-12 col-xl-12">
                                                    <div class="form__group">
                                                        <label class="form__label" for="msp">Mã Sản Phẩm</label>
                                                        <input class="d-none" id="idRec" name="idRec" value="<%=receipt.getId()%>">
                                                        <input id="msp" type="text" name="msp" class="form__input"
                                                               value="" placeholder="Vui lòng nhập chính xác mã sản phẩm..." required>
                                                        <p id="error" class="text-danger mb-2"></p>

                                                    </div>
                                                    <div class="form__group">
                                                        <label class="form__label" for="slg">Số lượng mua</label>
                                                        <input id="slg" type="number" name="slg" class="form__input"
                                                               value="1" min = "1">
                                                    </div>
                                                    <div class="form__group">
                                                        <label class="form__label" for="notes">Lời chúc trên bánh</label>
                                                        <input type="text" id="notes" name="notes" class="form__input text-lowercase"
                                                               placeholder="Chúc mừng sinh nhật....">
                                                    </div>
                                                </div>

                                            </div>
                                            <div class="modal__btns">
<%--                                                <button class="modal__btn modal__btn--apply" type="submit">--%>
<%--                                                    Xác nhận và đóng--%>
<%--                                                </button>--%>
                                                <button onclick="adminAddProInOrder()" class="modal__btn modal__btn--apply" type="submit">
                                                    Xác nhận và đóng
                                                </button>
                                                <button class="modal__btn modal__btn--dismiss" type="button">Quay lại</button>
                                            </div>
                                        </div>

                                    </div>
                                    <!-- end modal status -->
                                    <% } %>
                                </table>
                            </div>
                            <div class="col-5 margin-top-20px">
                                <label >Tổng tiền hàng: </label>
                                <span class="text-info text-uppercase" id="pro_bill"><%=receipt.formatNum((int) receipt.getPro_bill())%> VND</span>
                            </div>
                            <div class="col-5 margin-top-20px">
                                <label>Phí vận chuyển: </label>
                                <span class="text-info text-uppercase" id="fee"><%=receipt.formatNum((int) receipt.getFee_bill())%> VND</span>
                            </div>
                            <div class="col-5 margin-top-20px">
                                <label for="total">Tổng thanh toán: </label>
                                <span style="font-weight: 600; font-size: 30px;" class="text-danger text-uppercase" id="total"><%=receipt.formatNum(receipt.getMoney())%> VND</span>
                            </div>

                        </div>
                        <div class="col-12">
                            <div class="row">
                                <%if(receipt.getStatus() == 0 || receipt.getStatus() == 1 || receipt.getStatus() == 2){%>
                                <div class="col-12 col-lg-6">
                                    <a href="#modal-view"  type="button" class="form__btn open-modal">Thêm sản phẩm</a>
                                </div>
                                <div class="col-12 col-lg-6">
                                    <button onclick="save()" type="button" class="form__btn flex-row-reverse">Lưu thay đổi</button>
                                </div>
                                <%}%>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>


<!-- Back to Top -->
<a href="#" class="btn btn-lg btn-primary btn-lg-square back-to-top"><i class="bi bi-arrow-up"></i></a>

<!-- JS -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
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