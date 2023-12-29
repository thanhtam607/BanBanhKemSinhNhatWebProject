<%@ page import="vn.edu.hcmuaf.fit.bean.User" %>
<%@ page import="vn.edu.hcmuaf.fit.model.Receipt" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="vn.edu.hcmuaf.fit.service.InforService" %>
<%@ page import="vn.edu.hcmuaf.fit.service.UserService" %>
<%@ page import="java.net.URLDecoder" %>
<!DOCTYPE html>
<%@ page contentType="text/html;charsetUTF-8" language="java" pageEncoding="utf-8" %>
<html lang="xzz">
<meta http-equiv="Content-Type" charset="UTF-8">

<head>
    <meta charset="utf-8">
    <title>Admin | <%=InforService.getInformation("NameShop").get(0).getContent()%>
    </title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="" name="keywords">
    <meta content="" name="description">

    <!-- Favicon -->
    <link rel="icon" href="../img/favicon.ico" type="image/x-icon"/>

    <!-- Google Web Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;600&family=Roboto:wght@500;700&display=swap"
          rel="stylesheet">


    <!-- Icon Font Stylesheet -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

    <!-- Libraries Stylesheet -->
    <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
    <link href="lib/tempusdominus/css/tempusdominus-bootstrap-4.min.css" rel="stylesheet"/>

    <!-- Customized Bootstrap Stylesheet -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Template Stylesheet -->
    <link href="css/style.css" rel="stylesheet">
</head>

<body>
<% User auth = (User) session.getAttribute("auth");%>
<div class="container-fluid position-relative d-flex p-0">
    <jsp:include page="spinner.jsp"></jsp:include>


    <!-- Sidebar Start -->
    <div class="sidebar pe-4 pb-3">
        <nav class="navbar bg-pink navbar-dark">
            <!--  -->
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
                <a href="./ListReceipt_Admin" class="nav-item nav-link active"><i class="fa fa-tachometer-alt me-2"></i>Tổng
                    quan</a>
                <%--            <a href="general_Management.jsp" class="nav-item nav-link"><i class="fa fa-user"></i>Quản lý chung</a>--%>
                <a href="./ListProduct_Admin" class="nav-item nav-link"><i class="fa fa-th me-2"></i>DS Sản Phẩm</a>

                <a href="./ListCustomer" class="nav-item nav-link"><i class="fa fa-th me-2"></i>DS Khách Hàng</a>
                <a href="./ListBlog-admin" class="nav-item nav-link "><i class="fa fa-th me-2"></i>DS Tin Tức</a>
                <a href="./ListReceipt_full_Admin" class="nav-item nav-link "><i class="fa fa-th me-2"></i>DS Đơn
                    Hàng</a>
                <a href="feedbacks.jsp" class="nav-item nav-link"><i class="fa fa-th me-2"></i>DS Đánh giá</a>
                <a href="catalog_Management.jsp" class="nav-item nav-link"><i class="fa fa-file me-2"></i>QL danh
                    mục</a>
                <a href="List_Discounts" class="nav-item nav-link"><i class="fa fa-birthday-cake me-2"></i>Khuyến
                    mãi</a>

                <a href="../Index" class="nav-item nav-link"><i class="fa fa-arrow-alt-circle-right me-2"></i>Về trang
                    chủ</a>
                <!--  -->
            </div>
        </nav>
    </div>
    <!-- Sidebar End -->


    <!-- Content Start -->
    <div class="content">
        <!-- Navbar Start -->
        <nav class="navbar navbar-expand bg-pink navbar-dark sticky-top px-4 py-0">
            <a href="./ListReceipt_Admin" class="navbar-brand d-flex d-lg-none me-4">
                <h2 class="text-primary mb-0"><i class="fa fa-user-edit"></i></h2>
            </a>
            <a href="#" class="sidebar-toggler flex-shrink-0">
                <i class="fa fa-bars"></i>
            </a>
            <form class="d-none d-md-flex ms-4">
                <input class="form-control bg-pink " type="search" placeholder="Tìm Kiếm">
            </form>
            <div class="navbar-nav align-items-center ms-auto">

                <div class="nav-item dropdown">
                    <%String url = auth != null ? "#" : "../signin.jsp";%>
                    <a href="<%=url%>"
                       class="nav-link <%=auth != null ?"dropdown-toggle":""%>" <%=auth != null ? "data-bs-toggle=\"dropdown\"" : ""%>>
                        <i class="fa fa-user icon__user__small"></i>
                        <span class="d-none d-lg-inline-flex"><%= auth != null ? auth.getName() : "Đăng nhập"%></span>
                    </a>
                    <%if (auth != null) {%>
                    <div class="dropdown-menu dropdown-menu-end rounded-0 rounded-bottom m-0">
                        <% User user = UserService.findById(auth.getId()); %>
                        <% if (user.getRole() == 2) { %>
                        <a href="general_Management.jsp" class="dropdown-item">Quản lí cửa hàng</a>
                        <a href="AdminDecentralization.jsp" class="dropdown-item">Phân quyền cấp cao</a>
                        <a href="ListLog" class="dropdown-item">Nhật ký hoạt động</a>
                        <% } else { %>
                        <% } %>
                        <a href="edit-profile.jsp" class="dropdown-item">Hồ sơ của tôi</a>
                        <a href="./doSignOut" method="get" class="dropdown-item">Đăng xuất</a>
                    </div>
                    <%}%>
                </div>
            </div>
        </nav>
        <!-- Navbar End -->


        <!-- Sale & Revenue Start -->
        <div class="container-fluid pt-4 px-4">
            <div class="row g-4">

                <div class="col-sm-6 col-xl-3">
                    <div class="bg-pink rounded d-flex align-items-center justify-content-between p-4">
                        <i class="fa fa-chart-bar fa-3x text-primary"></i>
                        <div class="ms-3">
                            <p class="mb-2">Đơn hàng hôm nay</p>
                            <h6 class="mb-0"><%= request.getAttribute("soDHhomnay")%>
                            </h6>
                        </div>
                    </div>
                </div>

                <div class="col-sm-6 col-xl-3">
                    <div class="bg-pink rounded d-flex align-items-center justify-content-between p-4">
                        <i class="fa fa-chart-area fa-3x text-primary"></i>
                        <div class="ms-3">
                            <p class="mb-2">Sản phẩm bán được hôm nay</p>
                            <h6 class="mb-0"><%= request.getAttribute("solgSPbandcthangnay")%>
                            </h6>
                        </div>
                    </div>
                </div>
                <div class="col-sm-6 col-xl-3">
                    <div class="bg-pink rounded d-flex align-items-center justify-content-between p-4">
                        <i class="fa fa-chart-line fa-3x text-primary"></i>
                        <div class="ms-3">
                            <p class="mb-2">Doanh thu hôm nay</p>
                            <h6 class="mb-0"><%= request.getAttribute("doanhthuhomnay")%>
                            </h6>
                        </div>
                    </div>
                </div>
                <div class="col-sm-6 col-xl-3">
                    <div class="bg-pink rounded d-flex align-items-center justify-content-between p-4">
                        <i class="fa fa-chart-pie fa-3x text-primary"></i>
                        <div class="ms-3">
                            <p class="mb-2">Doanh thu tháng này</p>
                            <h6 class="mb-0"><%= request.getAttribute("doanhthuthangnay")%>
                            </h6>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Sale & Revenue End -->

        <!-- Recent Sales Start -->
        <% User user = UserService.findById(auth.getId()); %>
        <div class="container-fluid pt-4 px-4">
            <div class="bg-pink text-center rounded p-4">
                <div class="d-flex align-items-center justify-content-between mb-4">
                    <h6 class="mb-0">Bán gần đây</h6>
                    <a href="./ListReceipt_full_Admin">Xem tất cả</a>
                </div>
                <div class="table-responsive">
                    <table class="table text-start align-middle table-bordered table-hover mb-0">
                        <thead>
                        <tr class="text-black">
                            <th scope="col">STT</th>
                            <th scope="col">Ngày đặt hàng</th>
                            <th scope="col">Khách hàng</th>
                            <th scope="col">Địa chỉ</th>
                            <th scope="col">Thành tiền</th>
                            <th scope="col">Trạng thái ĐH</th>
                            <th scope="col">Chi tiết</th>
                        </tr>
                        </thead>
                        <tbody>
                        <% List<Receipt> receiptList = (List<Receipt>) request.getAttribute("listreceipt");
                            for (int i = 0; i < 5; i++) {
                                Receipt rc = receiptList.get(i);%>
                        <tr>
                            <td><%=i + 1%>
                            </td>
                            <td><%=rc.getExport_date()%>
                            </td>
                            <td><%=UserService.findById(rc.getMakh()).getName()%>
                            </td>
                            <td><%=rc.getAddress()%>
                            </td>
                            <td><%=rc.formatNum(rc.getMoney())%> VND</td>
                            <td>
                                <div class="main__table-text"><%=URLDecoder.decode(rc.getStatusName(), "UTF-8")%>
                                </div>
                            </td>
                            <td>
                                <a href="Bill_detail_Admin?mahd=<%=rc.getId()%>&tenkh=<%=UserService.findById(rc.getMakh()).getName()%>"
                                   class=" main__table-btn main__table-btn--edit px-1">
                                    <i class="fas fa-info-circle text-center"></i>
                                </a>
                                <% if (user.getIsedit() == 1 || user.getRole() == 2) { %>
                                <%if (rc.getStatus() == 0) {%>
                                <a href="#modal-status<%=i%>"
                                   class="main__table-btn main__table-btn--banned open-modal">
                                    <i class="fa fa-check"></i>
                                </a>
                                <%}%>
                                <%if (rc.getStatus() == 0 || rc.getStatus() == 1 || rc.getStatus() == 5) {%>
                                <a href="#modal-delete<%=i%>"
                                   class="main__table-btn main__table-btn--delete open-modal">
                                    <i class="fa fa-trash"></i>
                                </a>
                                <%}%>
                                <% } %>
                            </td>
                        </tr>
                        </tbody>
                        <!-- modal delete -->
                        <div id="modal-delete<%=i%>" class="zoom-anim-dialog mfp-hide modal">
                            <h6 class="modal__title">Hủy Đơn Hàng</h6>

                            <p class="modal__text">Bạn có chắc muốn hủy đơn hàng này?</p>

                            <div class="modal__btns">
                                <a href="adminRemoveOrder?mahd=<%=rc.getId()%>" class="modal__btn modal__btn--apply"
                                   type="button">
                                    Hủy Đơn Hàng
                                </a>
                                <button class="modal__btn modal__btn--dismiss" type="button">Quay lại</button>
                            </div>
                        </div>
                        <!-- end modal delete -->
                        <!-- modal status -->
                        <div id="modal-status<%=i%>" class="zoom-anim-dialog mfp-hide modal">
                            <h6 class="modal__title">Thông Báo</h6>

                            <p class="modal__text">Bạn có chắc muốn xác nhận đơn hàng này?</p>

                            <div class="modal__btns">
                                <a href="adminChangeStateOrder?mahd=<%=rc.getId()%>"
                                   class="modal__btn modal__btn--apply" type="button">
                                    Xác nhận
                                </a>
                                <button class="modal__btn modal__btn--dismiss" type="button">Quay lại</button>
                            </div>
                        </div>
                        <!-- end modal status -->
                        <% } %>
                    </table>
                </div>
            </div>
        </div>
        <!-- Recent Sales End -->

        <div class="container-fluid pt-4 px-4">
            <div class="col-sm-12 col-xl-6">
                <div class="bg-pink rounded h-100 p-4">
                    <h6 class="mb-4 bg-pink">Sản phẩm bán chạy trong tháng này </h6>
                    <ul class="list-group">
                        <%
                            Map<String, Integer> map = (Map<String, Integer>) request.getAttribute("map-hot");

                            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                        %>
                        <li class="list-group-item bg-pink" style="display: flex; justify-content: space-between">
                            <span><%=entry.getKey()%></span>
                            <span><%=entry.getValue()%></span></li>

                        <% }%>
                    </ul>
                </div>
            </div>
        </div>
        <!-- Widgets Start -->

        <!-- Widgets End -->


        <!-- Footer Start -->
        <div class="container-fluid pt-4 px-4">
            <div class="bg-pink rounded-top p-4">
                <div class="row">
                    <div class="col-12 col-sm-6 text-center text-sm-start">
                        &copy; <a href="#">Lập trình web thầy Phan Đình Long Khoa CNTT - ĐH Nông Lâm TP HCM</a>
                    </div>
                    <div class="col-12 col-sm-6 text-center text-sm-end">
                        <a href="#">Nhóm 27</a>
                    </div>
                </div>
            </div>
        </div>
        <!-- Footer End -->
    </div>
    <!-- Content End -->

    <!-- Back to Top -->
    <a href="#" class="btn btn-lg btn-primary btn-lg-square back-to-top"><i class="bi bi-arrow-up"></i></a>
</div>

<!-- JavaScript Libraries -->
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="lib/chart/chart.min.js"></script>
<script src="lib/easing/easing.min.js"></script>
<script src="lib/waypoints/waypoints.min.js"></script>
<script src="lib/owlcarousel/owl.carousel.min.js"></script>
<script src="lib/tempusdominus/js/moment.min.js"></script>
<script src="lib/tempusdominus/js/moment-timezone.min.js"></script>
<script src="lib/tempusdominus/js/tempusdominus-bootstrap-4.min.js"></script>

<!-- Template Javascript -->
<script src="./js/main.js"></script>
</body>

</html>