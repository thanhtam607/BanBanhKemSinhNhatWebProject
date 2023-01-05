<%@ page import="vn.edu.hcmuaf.fit.bean.User" %>
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


    <!-- Favicons -->
    <link rel="icon" type="image/png" href="icon/favicon-32x32.png" sizes="32x32">
    <link rel="apple-touch-icon" href="icon/favicon-32x32.png">

    <!-- boostrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <%--  admin  css --%>
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
<% User auth = (User) session.getAttribute("auth");%>
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
                <h6 class="mb-0"><%= auth != null ? auth.getTentk():"ADMIN"%></h6>
                <span>Admin</span>
            </div>
        </div>
        <div class="navbar-nav w-100">
            <a href="./ListReceipt_Admin" class="nav-item nav-link "><i class="fa fa-tachometer-alt me-2"></i>Tổng quan</a>
            <a href="./ListProduct_Admin" class="nav-item nav-link"><i class="fa fa-th me-2"></i>DS Sản Phẩm</a>
            <a href="./customers.jsp" class="nav-item nav-link active"><i class="fa fa-th me-2"></i>DS Khách Hàng</a>
            <a href="./ListBlog-admin" class="nav-item nav-link "><i class="fa fa-th me-2"></i>DS Blog</a>
            <a href="add-product.jsp" class="nav-item nav-link"><i class="fa fa-birthday-cake me-2"></i>Thêm Sản Phẩm</a>
            <a href="add-blog.jsp" class="nav-item nav-link"><i class="fa fa-blog me-2"></i>Thêm blog</a>
            <a href="../Index" class="nav-item nav-link"><i class="fa fa-arrow-alt-circle-right me-2"></i>Về trang chủ</a>
            <!--  -->
        </div>
    </nav>
</div>
<!-- Sidebar End -->

    <!-- main content -->
    <main class="main">
        <div class="container-fluid">
            <div class="row">
                <!-- main title -->
                <div class="col-12">
                    <div class="main__title">
                        <h2>Thông tin nguời dùng</h2>
                    </div>
                </div>
                <!-- end main title -->

                <!-- profile -->
                <div class="col-12">
                    <div class="profile__content">
                        <!-- profile user -->
                        <div class="profile__user">
                            <div class="profile__avatar">
                                <img src="img/user.svg" alt="">
                            </div>
                            <!-- or red -->
                            <div class="profile__meta profile__meta--green">
                                <h3>John Doe <span>(Approved)</span></h3>
                                <span>HotFlix ID: 23562</span>
                            </div>
                        </div>
                        <!-- end profile user -->

                        <!-- profile tabs nav -->
                        <ul class="nav nav-tabs profile__tabs" id="profile__tabs" role="tablist">
                            <li class="nav-item">
                                <a class="nav-link active" data-toggle="tab" href="#tab-1" role="tab" aria-controls="tab-1" aria-selected="true">Thông Tin</a>
                            </li>

                            <li class="nav-item">
                                <a class="nav-link" data-toggle="tab" href="#tab-2" role="tab" aria-controls="tab-2" aria-selected="false">Đơn Hàng</a>
                            </li>

                            <li class="nav-item">
                                <a class="nav-link" data-toggle="tab" href="#tab-3" role="tab" aria-controls="tab-3" aria-selected="false">Bình Luận</a>
                            </li>
                        </ul>
                        <!-- end profile tabs nav -->

                        <!-- profile mobile tabs nav -->
                        <div class="profile__mobile-tabs" id="profile__mobile-tabs">
                            <div class="profile__mobile-tabs-btn dropdown-toggle" role="navigation" id="mobile-tabs" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <input type="button" value="Profile">
                                <span></span>
                            </div>

                            <div class="profile__mobile-tabs-menu dropdown-menu" aria-labelledby="mobile-tabs">
                                <ul class="nav nav-tabs" role="tablist">
                                    <li class="nav-item"><a class="nav-link active" id="1-tab" data-toggle="tab" href="#tab-1" role="tab" aria-controls="tab-1" aria-selected="true">Thông Tin</a></li>

                                    <li class="nav-item"><a class="nav-link" id="2-tab" data-toggle="tab" href="#tab-2" role="tab" aria-controls="tab-2" aria-selected="false">Đơn Hàng</a></li>

                                    <li class="nav-item"><a class="nav-link" id="3-tab" data-toggle="tab" href="#tab-3" role="tab" aria-controls="tab-3" aria-selected="false">Bình Luận</a></li>
                                </ul>
                            </div>
                        </div>
                        <!-- end profile mobile tabs nav -->

                        <!-- profile btns -->
                        <div class="profile__actions">
                            <a href="#modal-status3" class="profile__action profile__action--banned open-modal"><i class="fa fa-lock"></i></a>
                            <a href="#modal-delete3" class="profile__action profile__action--delete open-modal"><i class="fa fa-trash"></i></a>
                        </div>
                        <!-- end profile btns -->
                    </div>
                </div>
                <!-- end profile -->

                <!-- content tabs -->
                <div class="tab-content" id="myTabContent">
                    <div class="tab-pane fade show active" id="tab-1" role="tabpanel" aria-labelledby="1-tab">
                        <div class="col-12">
                            <div class="row">
                                <!-- details form -->
                                <div class="col-12 col-lg-6">
                                    <form action="#" class="form form--profile">
                                        <div class="row row--form">
                                            <div class="col-12">
                                                <h4 class="form__title">Profile details</h4>
                                            </div>

                                            <div class="col-12 col-md-6 col-lg-12 col-xl-6">
                                                <div class="form__group">
                                                    <label class="form__label" for="username">Username</label>
                                                    <input id="username" type="text" name="username" class="form__input" placeholder="User 123">
                                                </div>
                                            </div>

                                            <div class="col-12 col-md-6 col-lg-12 col-xl-6">
                                                <div class="form__group">
                                                    <label class="form__label" for="email">Email</label>
                                                    <input id="email" type="text" name="email" class="form__input" placeholder="email@email.com">
                                                </div>
                                            </div>

                                            <div class="col-12 col-md-6 col-lg-12 col-xl-6">
                                                <div class="form__group">
                                                    <label class="form__label" for="firstname">First Name</label>
                                                    <input id="firstname" type="text" name="firstname" class="form__input" placeholder="John">
                                                </div>
                                            </div>

                                            <div class="col-12 col-md-6 col-lg-12 col-xl-6">
                                                <div class="form__group">
                                                    <label class="form__label" for="lastname">Last Name</label>
                                                    <input id="lastname" type="text" name="lastname" class="form__input" placeholder="Doe">
                                                </div>
                                            </div>

                                            <div class="col-12 col-md-6 col-lg-12 col-xl-6">
                                                <div class="form__group">
                                                    <label class="form__label" for="subscription">Subscription</label>
                                                    <select class="js-example-basic-single" id="subscription">
														<option value="Basic">Basic</option>
														<option value="Premium">Premium</option>
														<option value="Cinematic">Cinematic</option>
													</select>
                                                </div>
                                            </div>

                                            <div class="col-12 col-md-6 col-lg-12 col-xl-6">
                                                <div class="form__group">
                                                    <label class="form__label" for="rights">Rights</label>
                                                    <select class="js-example-basic-single" id="rights">
														<option value="User">User</option>
														<option value="Moderator">Moderator</option>
														<option value="Admin">Admin</option>
													</select>
                                                </div>
                                            </div>

                                            <div class="col-12">
                                                <button class="form__btn" type="button">Save</button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                                <!-- end details form -->

                                <!-- password form -->
                                <div class="col-12 col-lg-6">
                                    <form action="#" class="form form--profile">
                                        <div class="row row--form">
                                            <div class="col-12">
                                                <h4 class="form__title">Change password</h4>
                                            </div>

                                            <div class="col-12 col-md-6 col-lg-12 col-xl-6">
                                                <div class="form__group">
                                                    <label class="form__label" for="oldpass">Old Password</label>
                                                    <input id="oldpass" type="password" name="oldpass" class="form__input">
                                                </div>
                                            </div>

                                            <div class="col-12 col-md-6 col-lg-12 col-xl-6">
                                                <div class="form__group">
                                                    <label class="form__label" for="newpass">New Password</label>
                                                    <input id="newpass" type="password" name="newpass" class="form__input">
                                                </div>
                                            </div>

                                            <div class="col-12 col-md-6 col-lg-12 col-xl-6">
                                                <div class="form__group">
                                                    <label class="form__label" for="confirmpass">Confirm New Password</label>
                                                    <input id="confirmpass" type="password" name="confirmpass" class="form__input">
                                                </div>
                                            </div>

                                            <div class="col-12">
                                                <button class="form__btn" type="button">Change</button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                                <!-- end password form -->
                            </div>
                        </div>
                    </div>

                    <div class="tab-pane fade" id="tab-2" role="tabpanel" aria-labelledby="2-tab">
                        <!-- table -->
                        <div class="col-12">
                            <div class="main__table-wrap">
                                <table class="main__table">
                                    <thead>
                                        <tr>
                                            <th>Mã Đơn Hàng</th>
                                            <th>Tên Sản Phẩm</th>
                                            <th>Tên Khách Hàng</th>
                                            <th>Địa Chỉ Giao</th>
                                            <th>Ngày Tạo</th>
                                            <th>Tùy Chọn</th>
                                        </tr>
                                    </thead>

                                    <tbody>
                                        <tr>
                                            <td>
                                                <div class="main__table-text">23</div>
                                            </td>
                                            <td>
                                                <div class="main__table-text"><a href="#">I Dream in Another Language</a></div>
                                            </td>
                                            <td>
                                                <div class="main__table-text">TP.HCM</div>
                                            </td>
                                            <td>
                                                <div class="main__table-text">John Doe</div>
                                            </td>
<%--                                            <td>--%>
<%--                                                <div class="main__table-text">Lorem Ipsum is simply dummy text...</div>--%>
<%--                                            </td>--%>
<%--                                            <td>--%>
<%--                                                <div class="main__table-text">12 / 7</div>--%>
<%--                                            </td>--%>
                                            <td>
                                                <div class="main__table-text">24 Oct 2021</div>
                                            </td>
                                            <td>
                                                <div class="main__table-btns">
                                                    <a href="#modal-view" class="main__table-btn main__table-btn--view open-modal">
														<i class="fa fa-eye"></i>
													</a>
                                                    <a href="#modal-delete" class="main__table-btn main__table-btn--delete open-modal">
														<i class="fa fa-trash"></i>
													</a>
                                                </div>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <!-- end table -->

                        <!-- paginator -->
                        <div class="col-12">
                            <div class="paginator-wrap">
                                <span>10 from 23</span>

                                <ul class="paginator">
                                    <li class="paginator__item paginator__item--prev">
                                        <a href="#"><i class="fa fa-arrow-back"></i></a>
                                    </li>
                                    <li class="paginator__item"><a href="#">1</a></li>
                                    <li class="paginator__item paginator__item--active"><a href="#">2</a></li>
                                    <li class="paginator__item"><a href="#">3</a></li>
                                    <li class="paginator__item"><a href="#">4</a></li>
                                    <li class="paginator__item paginator__item--next">
                                        <a href="#"><i class="fa fa-arrow-forward"></i></a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                        <!-- end paginator -->
                    </div>

                    <div class="tab-pane fade" id="tab-3" role="tabpanel" aria-labelledby="3-tab">
                        <!-- table -->
                        <div class="col-12">
                            <div class="main__table-wrap">
                                <table class="main__table">
                                    <thead>
                                        <tr>
                                            <th>Mã Khách Hàng</th>
                                            <th>Sản Phẩm Đã Bình Luận</th>
                                            <th>Tên Khách Hàng</th>
                                            <th>Ngày Tạo</th>
                                            <th>Tùy Chọn</th>
                                        </tr>
                                    </thead>

                                    <tbody>
                                        <tr>
                                            <td>
                                                <div class="main__table-text">23</div>
                                            </td>
                                            <td>
                                                <div class="main__table-text"><a href="#">Bánh Cánh Đồng Hoa</a></div>
                                            </td>
                                            <td>
                                                <div class="main__table-text">John Doe</div>
                                            </td>
                                            <%--                                            <td>--%>
<%--                                                <div class="main__table-text">Lorem Ipsum is simply dummy text...</div>--%>
<%--                                            </td>--%>
<%--                                            <td>--%>
<%--                                                <div class="main__table-text main__table-text--rate"><i class="fa fa-star"></i> 7.9</div>--%>
<%--                                            </td>--%>
<%--                                            <td>--%>
<%--                                                <div class="main__table-text">12 / 7</div>--%>
<%--                                            </td>--%>
                                            <td>
                                                <div class="main__table-text">24 Oct 2021</div>
                                            </td>
                                            <td>
                                                <div class="main__table-btns">
                                                    <a href="#modal-view2" class="main__table-btn main__table-btn--view open-modal">
														<i class="fa fa-eye"></i>
													</a>
                                                    <a href="#modal-delete2" class="main__table-btn main__table-btn--delete open-modal">
														<i class="fa fa-trash"></i>
													</a>
                                                </div>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <!-- end table -->

                        <!-- paginator -->
                        <div class="col-12">
                            <div class="paginator-wrap">
                                <span>10 from 32</span>

                                <ul class="paginator">
                                    <li class="paginator__item paginator__item--prev">
                                        <a href="#"><i class="fa fa-arrow-back"></i></a>
                                    </li>
                                    <li class="paginator__item"><a href="#">1</a></li>
                                    <li class="paginator__item paginator__item--active"><a href="#">2</a></li>
                                    <li class="paginator__item"><a href="#">3</a></li>
                                    <li class="paginator__item"><a href="#">4</a></li>
                                    <li class="paginator__item paginator__item--next">
                                        <a href="#"><i class="fa fa-arrow-forward"></i></a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                        <!-- end paginator -->
                    </div>
                </div>
                <!-- end content tabs -->
            </div>
        </div>
    </main>
    <!-- end main content -->

    <!-- modal view -->
    <div id="modal-view" class="zoom-anim-dialog mfp-hide modal modal--view">
        <div class="comments__autor">
            <img class="comments__avatar" src="img/user.svg" alt="">
            <span class="comments__name">Nguyễn Văn A</span>
            <span class="comments__time">30.08.2018, 17:53</span>
        </div>
        <p class="comments__text">Bánh cánh đồng hoa.</p>
        <p class="comments__text">Giao tại: kp3, quận 7, tpHCM.</p>
        <p class="comments__text">Trạng thái: Giao Thành Công.</p>
        <div class="comments__actions">
            <div class="comments__rate">
                <span><i class="fa fa-thumbs-up"></i>12</span>

                <span>7<i class="fa fa-thumbs-down"></i></span>
            </div>
        </div>
    </div>
    <!-- end modal view -->

    <!-- modal delete -->
    <div id="modal-delete" class="zoom-anim-dialog mfp-hide modal">
        <h6 class="modal__title">Hủy Đơn Hàng</h6>

        <p class="modal__text">Bạn có chắc muốn hủy đơn hàng này?</p>

        <div class="modal__btns">
            <button class="modal__btn modal__btn--apply" type="button">Hủy</button>
            <button class="modal__btn modal__btn--dismiss" type="button">Quay lại</button>
        </div>
    </div>
    <!-- end modal delete -->

    <!-- modal view -->
    <div id="modal-view2" class="zoom-anim-dialog mfp-hide modal modal--view">
        <div class="reviews__autor">
            <img class="reviews__avatar" src="img/user.svg" alt="">
            <span class="reviews__name">Bánh cánh đồng hoa</span>
            <span class="reviews__time">24.08.2018, 17:53 by Thanh Tâm</span>

            <span class="reviews__rating"><i class="fa fa-star"></i>5</span>
        </div>
        <p class="reviews__text">Bánh ngon xuất sắc!.</p>
    </div>
    <!-- end modal view -->

    <!-- modal delete -->
    <div id="modal-delete2" class="zoom-anim-dialog mfp-hide modal">
        <h6 class="modal__title">Xóa Bình Luận</h6>

        <p class="modal__text">Bạn có chắc muốn xóa bình luận này?</p>

        <div class="modal__btns">
            <button class="modal__btn modal__btn--apply" type="button">Xóa</button>
            <button class="modal__btn modal__btn--dismiss" type="button">Quay lại</button>
        </div>
    </div>
    <!-- end modal delete -->

    <!-- modal status -->
    <div id="modal-status3" class="zoom-anim-dialog mfp-hide modal">
        <h6 class="modal__title">Chặn Người Dùng</h6>

        <p class="modal__text">Bạn có chắc muốn chặn người dùng này?</p>

        <div class="modal__btns">
            <button class="modal__btn modal__btn--apply" type="button">Chặn</button>
            <button class="modal__btn modal__btn--dismiss" type="button">Quay lại</button>
        </div>
    </div>
    <!-- end modal status -->

    <!-- modal delete -->
    <div id="modal-delete3" class="zoom-anim-dialog mfp-hide modal">
        <h6 class="modal__title">Xóa Người Dùng</h6>

        <p class="modal__text">Bạn có chắc muốn xóa người dùng này?</p>

        <div class="modal__btns">
            <button class="modal__btn modal__btn--apply" type="button">Xóa</button>
            <button class="modal__btn modal__btn--dismiss" type="button">Quay lại</button>
        </div>
    </div>
    <!-- end modal delete -->

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