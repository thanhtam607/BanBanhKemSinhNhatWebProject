<%@ page import="java.util.List" %>
<%@ page import="java.util.List" %>
<%@ page import="vn.edu.hcmuaf.fit.model.Receipt" %>
<%@ page import="vn.edu.hcmuaf.fit.model.Bill_Detail" %>
<%@ page import="vn.edu.hcmuaf.fit.bean.User" %>
<%@ page import="vn.edu.hcmuaf.fit.model.Bill_Detail" %>
<%@ page import="vn.edu.hcmuaf.fit.service.InforService" %>
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
            <a href="../Index" class="nav-item nav-link"><i class="fa fa-arrow-alt-circle-right me-2"></i>Về trang chủ</a>
            <!--  -->
        </div>
    </nav>
</div>
<!-- Sidebar End -->

<!-- main content -->
<main class="main bg-white">
    <div class="container-fluid bg-white">
        <div class="row">
            <!-- main title -->
            <div class="col-12">
                <div class="main__title">
                    <h2>Hồ sơ của tôi</h2>
                </div>
            </div>
            <!-- end main title -->

            <!-- form -->
            <div class="col-12">
                <div class="row">
                    <div class="col-10">
                        <form action="#" class="form form--profile">
                            <div class="row row--form">
                                <div class="col-12 col-md-6 col-lg-12 col-xl-6">
                                    <div class="form__group">
                                        <label class="form__label" for="name_user">Tên</label>
                                        <input type="text" id="name_user" name="name_user" class="form__input" placeholder="Thanh Thùy">
                                    </div>
                                </div>

                                <div class="col-12 col-md-6 col-lg-12 col-xl-6">
                                    <div class="form__group">
                                        <label class="form__label" for="phone">SĐT</label>
                                        <input type="tel" id="phone" name="phone" class="form__input" placeholder="0356402438"></input>
                                    </div>
                                </div>
                                <div class="col-12">
                                    <div class="form__group">
                                        <label class="form__label" for="email">Email</label>
                                        <input type="text" id="email" name="email" class="form__input" placeholder="Email"></input>
                                    </div>
                                </div>
                                <div class="col-12">
                                    <div class="form__group">
                                        <label class="form__label" for="address">Địa chỉ</label>
                                        <input type="text" id="address" name="address" class="form__input" placeholder="Địa chỉ"></input>
                                    </div>
                                </div>
                                <div class="col-12 col-md-6 col-lg-12 col-xl-6">
                                    <div class="form__group">
                                        <label class="form__label" for="rights">Quyền Hạn</label>
                                        <select class="form__input" id="rights" name="">
                                            <option selected value="0">Quản lí cấp cao</option>
                                            <option value="1">Admin</option>
                                        </select>
                                    </div>
                                </div>

                            </div>
                            <div class="col-6" style="padding: 0;">
                                <button type="submit" class="form__btn">Lưu thay đổi</button>
                            </div>

                        </form>
                    </div>
                    <!-- <div class="col-12 col-lg-6">
                        <div class="row row--form form__content">
                            <div class="col-12 col-sm-6">
                                <input type="text" class="form__input" placeholder="Giá bán">
                            </div>

                            <div class="col-12 col-sm-6 col-lg-3">
                                <input type="text" class="form__input" placeholder="Tình trạng">
                            </div>

                            <div class="col-12 col-sm-6 col-lg-3">
                                <input type="text" class="form__input" placeholder="Giao hàng">
                            </div>
                            <div class="col-12 col-sm-6 col-lg-3">
                                <input type="text" class="form__input" placeholder="Khối lượng">
                            </div>
                        </div>
                    </div> -->
                </div>

            </div>
            <!-- end form -->
        </div>
    </div>
</main>
<!-- end main content -->

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