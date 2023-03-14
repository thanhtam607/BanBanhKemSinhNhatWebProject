<%--
  Created by IntelliJ IDEA.
  User: maith
  Date: 2/23/2023
  Time: 7:46 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="vn.edu.hcmuaf.fit.model.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="vn.edu.hcmuaf.fit.model.ProductDetail" %>
<%@ page import="vn.edu.hcmuaf.fit.controller.ListProduct" %>
<html>
<%@ page contentType="text/html;charsetUTF-8" language="java" pageEncoding="utf-8"%>
<html lang="xzz">
<meta http-equiv="Content-Type" charset="UTF-8">
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


    <!-- Favicons -->
    <link rel="icon" type="image/png" href="icon/favicon-32x32.png" sizes="32x32">
    <link rel="apple-touch-icon" href="icon/favicon-32x32.png">

    <!-- boostrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <%--    css --%>
    <link rel="stylesheet" href="css/admin.css">
    <!-- index css -->
    <link rel="stylesheet" href="./css/style.css" >

    <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
    <link href="lib/tempusdominus/css/tempusdominus-bootstrap-4.min.css" rel="stylesheet" />


    <meta name="description" content="">
    <meta name="keywords" content="">
    <meta name="author" content="Dmitry Volkov">
    <title>Admin | Shop Bánh Kem</title>
</head>

<body>
<!-- header -->
<header class="header">
    <div class="header__content">
        <!-- header logo -->
        <a href="./ListReceipt_Admin" class="header__logo">
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
                <h6 class="mb-0">Thanh Thùy</h6>
                <span>Admin</span>
            </div>
        </div>
        <div class="navbar-nav w-100">
            <a href="./ListReceipt_Admin" class="nav-item nav-link "><i class="fa fa-tachometer-alt me-2"></i>Tổng quan</a>
            <a href="./ListProduct_Admin" class="nav-item nav-link"><i class="fa fa-th me-2"></i>DS Sản Phẩm</a>
            <a href="./ListCustomer" class="nav-item nav-link"><i class="fa fa-th me-2"></i>DS Khách Hàng</a>
            <a href="./ListBlog-admin" class="nav-item nav-link"><i class="fa fa-th me-2"></i>DS Tin Tức</a>
            <a href="./ListReceipt_full_Admin" class="nav-item nav-link"><i class="fa fa-th me-2"></i>DS Đơn Hàng</a>
            <a href="add-product.jsp" class="nav-item nav-link"><i class="fa fa-birthday-cake me-2"></i>Thêm Sản Phẩm</a>
            <a href="add-blog.jsp" class="nav-item nav-link"><i class="fa fa-blog me-2"></i>Thêm Tin Tức</a>
            <a href="feedbacks.jsp" class="nav-item nav-link"><i class="fa fa-th me-2"></i>DS Đánh giá</a>
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
                    <h2>Danh mục sản phẩm</h2>
                    <span class="main__title-stat">10 danh mục</span>

                    <div class="main__title-wrap">
                        <!-- filter sort -->
                        <div class="filter" id="filter__sort">
                            <span class="filter__item-label">Sắp xếp:</span>

                            <div class="filter__item-btn dropdown-toggle" role="navigation" id="filter-sort" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <input type="button" value="Tên">
                                <span></span>
                            </div>

                            <ul class="filter__item-menu dropdown-menu scrollbar-dropdown" aria-labelledby="filter-sort">
                                <li>Ngày sản xuất</li>
                                <li>Tên</li>
                                <li>Ngày hết hạn</li>
                            </ul>
                        </div>
                        <!-- end filter sort -->

                        <!-- search -->
                        <form action="#" class="main__title-form">
                            <input type="text" placeholder="Tìm sản phẩm..">
                            <button type="button">
                                <i class="fa fa-search"></i>
                            </button>
                        </form>
                        <!-- end search -->
                    </div>
                </div>
            </div>
            <!-- end main title -->

            <!-- users -->
            <div class="col-12 bg-pink">
                <div class="main__table-wrap">
                    <table class="main__table">
                        <thead>
                        <tr>
                            <th>STT</th>
                            <th>Tên danh mục</th>
                            <th>Trạng thái</th>
                            <th>Tùy chọn</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>
                                <div class="main__table-text">1</div>
                            </td>
                            <td>
                                <div class="main__user">
                                    <div class="main__meta">
                                        <h3>Bánh kem</h3>
                                    </div>
                                </div>
                            </td>
                            <td>
                                <div class="main__table-text">Còn hiệu lực</div>
                            </td>
                            <td>
                                <div class="main__table-btns">

                                    <a href="#modal-edit" class="main__table-btn main__table-btn--edit open-modal">
                                        <i class="fa fa-edit"></i>
                                    </a>
                                    <a href="#modal-delete" class="main__table-btn main__table-btn--delete open-modal">
                                        <i class="fa fa-trash"></i>
                                    </a>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class="main__table-text">2</div>
                            </td>
                            <td>
                                <div class="main__user">
                                    <div class="main__meta">
                                        <h3>Bánh kem</h3>
                                    </div>
                                </div>
                            </td>
                            <td>
                                <div class="main__table-text">Còn hiệu lực</div>
                            </td>
                            <td>
                                <div class="main__table-btns">

                                    <a href="#modal-edit" class="main__table-btn main__table-btn--edit open-modal">
                                        <i class="fa fa-edit"></i>
                                    </a>
                                    <a href="#modal-delete" class="main__table-btn main__table-btn--delete open-modal">
                                        <i class="fa fa-trash"></i>
                                    </a>
                                </div>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                    <div class="paginator-wrap">
                        <span  href="#modal-add"  class="open-modal" type="button">Thêm danh mục</span>
                    </div>
                    <!-- modal add -->
                    <div id="modal-add" class="zoom-anim-dialog mfp-hide modal" style="height: 370px">
                        <h6 class="modal__title">Thêm danh mục</h6>
                        <input value = "LB01" style="display: none">
                        <label class="form__label" for="add-name" style="color: white;">Tên danh mục</label>
                        <input id="add-name" type="text" name="add-name" class="form__input" placeholder="Thêm tên danh mục">
                        <label class="form__label" style="color: white">Trạng thái</label>
                        <select class="form-select form__input" name="status" >
                            <option>Còn hiệu lực</option>
                            <option>Hết hiệu lực</option>
                        </select>
                        <div class="modal__btns">
                            <button class="modal__btn modal__btn--apply" type="button">Thêm</button>
                            <button class="modal__btn modal__btn--dismiss" type="button">Quay lại</button>
                        </div>
                    </div>
                    <!-- end modal add -->
                    <!-- modal delete -->
                    <div id="modal-delete" class="zoom-anim-dialog mfp-hide modal" style="height: 200px">
                        <h6 class="modal__title">Xóa danh mục</h6>
                        <p class="modal__text">Bạn có chắc muốn xóa danh mục này?</p>
                        <div class="modal__btns">
                            <button class="modal__btn modal__btn--apply" type="button">Xóa</button>
                            <button class="modal__btn modal__btn--dismiss" type="button">Quay lại</button>
                        </div>
                    </div>
                    <!-- end modal delete -->
                    <!-- modal edit -->
                    <div id="modal-edit" class="zoom-anim-dialog mfp-hide modal" style="height: 370px">
                        <h6 class="modal__title">Sửa danh mục</h6>
                        <label class="form__label" for="name" style="color: white;">Tên danh mục</label>
                        <input id="name" type="text" name="name" class="form__input" value="Bánh kem">
                        <label class="form__label" style="color: white">Trạng thái</label>
                        <select class="form-select form__input" name="status" >
                            <option>Còn hiệu lực</option>
                            <option>Hết hiệu lực</option>
                        </select>
                        <div class="modal__btns">
                            <button class="modal__btn modal__btn--apply" type="button">Sửa</button>
                            <button class="modal__btn modal__btn--dismiss" type="button">Quay lại</button>
                        </div>
                    </div>
                    <!-- end modal edit -->
                </div>
            </div>
            <!-- end users -->
        </div>
    </div>
</main>
<!-- end main content -->





<!-- JS -->
<script src="js/jquery-3.5.1.min.js"></script>
<script src="js/bootstrap.bundle.min.js"></script>
<script src="js/jquery.magnific-popup.min.js"></script>
<script src="js/jquery.mousewheel.min.js"></script>
<script src="js/jquery.mCustomScrollbar.min.js"></script>
<script src="js/select2.min.js"></script>
<script src="js/admin.js"></script>


</body>

</html>
