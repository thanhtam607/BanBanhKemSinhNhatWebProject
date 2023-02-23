<%@ page import="vn.edu.hcmuaf.fit.bean.User" %>
<%@ page import="vn.edu.hcmuaf.fit.model.Blog" %>
<%@ page import="java.util.List" %>
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
            <a href="./ListCustomer" class="nav-item nav-link "><i class="fa fa-th me-2"></i>DS Khách Hàng</a>
            <a href="./ListBlog-admin" class="nav-item nav-link"><i class="fa fa-th me-2"></i>DS Tin Tức</a>
            <a href="./ListReceipt_full_Admin" class="nav-item nav-link"><i class="fa fa-th me-2"></i>DS Đơn Hàng</a>
            <a href="add-product.jsp" class="nav-item nav-link"><i class="fa fa-birthday-cake me-2"></i>Thêm Sản Phẩm</a>
            <a href="add-blog.jsp" class="nav-item nav-link"><i class="fa fa-blog me-2"></i>Thêm Tin Tức</a>
            <a href="../Index" class="nav-item nav-link"><i class="fa fa-arrow-alt-circle-right me-2"></i>Về trang chủ</a>
            <!--  -->
        </div>
    </nav>
</div>
<!-- Sidebar End -->

    <!-- main content -->
<% Blog b = (Blog) request.getAttribute("blg"); %>
    <main class="main">
        <div class="container-fluid">
            <div class="row">
                <!-- main title -->
                <div class="col-12">
                    <div class="main__title">
                        <h2>Chỉnh sửa tin tức</h2>
                    </div>
                </div>
                <!-- end main title -->

                <!-- profile -->
                <div class="col-12">
                    <div class="profile__content">
                        <!-- profile user -->
                        <div class="profile__user">
                            <div class="profile__avatar">
                                <img src="../<%=b.getImg()%>" alt="">
                            </div>
                            <!-- or red -->
                            <div class="profile__meta profile__meta--green">
                                <h3>Tên bài viết: <%=b.getCategory()%></h3>
                                <span>ID: <%=b.getId()%></span>
                            </div>
                        </div>
                        <!-- end profile user -->


                        <!-- profile btns -->
                        <div class="profile__actions">
                            <a href="#modal-delete2" class="profile__action profile__action--delete open-modal"><i class="fa fa-trash"></i></a>
                            <a href="#modal-status3" class="profile__action profile__action--banned open-modal"><i class="fa fa-lock"></i></a>
                        </div>
                    </div>
                </div>
                <!-- end profile -->

                <!-- content tabs -->
                <div class="tab-content" id="myTabContent">
                    <div class="tab-pane fade show active" id="tab-1" role="tabpanel" aria-labelledby="1-tab">
                        <div class="col-12">
                            <div class="row">
                                <!-- details form -->
                                <div class="col-12">
                                    <form action="Blog/UpdateBlog" method="post" class="form form--profile" id="info-blog">
                                        <div class="row row--form">
                                            <div class="col-12">
                                            </div>
                                            <% for (int i = 0; i < b.getListdemuc().size(); i++) { %>
                                            <div class="col-12 col-md-12 col-lg-12 col-xl-12">
                                                <input style="display: none;" type="text" name="<%="idB" + (i + 1)%>" class="form__input" value="<%=b.getId()%>">
                                                <input style="display: none;" type="text" name=" + <%="idctB" + (i + 1)%>" class="form__input" value="<%=b.getListid().get(i)%>">
                                                <div class="form__group">
                                                    <label class="form__label" >Tiêu đề <%=i + 1%></label>
                                                    <input  type="text" name="<%="topic" + (i + 1)%>" class="form__input" value="<%=b.getListdemuc().get(i)%>">
                                                    <input style="display: none;"  type="text" name="<%="topic_after" + (i + 1)%>" class="form__input" value="<%=b.getListdemuc().get(i)%>">
                                                </div>
                                            </div>
                                            <div class="col-12 col-md-12 col-lg-12 col-xl-12">
                                                <div class="form__group">
                                                    <label class="form__label" for="description">Nội dung <%=i +1%></label>
                                                    <textarea class="form__input "  id="description" name="<%="content" + (i + 1)%>" form="info-blog" ><%=b.getListchitiet().get(i)%></textarea>
                                                    <input style="display: none;" class="form__input "  name="<%="content_after" + (i + 1)%>" form="info-blog" value="<%=b.getListchitiet().get(i)%>">
                                                </div>
                                            </div>
                                            <% } %>
                                            <div class="col-12 col-md-6 col-lg-12 col-xl-6">
                                                <div class="form__group">
                                                    <label class="form__label">Danh mục</label>
                                                    <select class="form__input"  name="title">
                                                        <%List<String> listDm = (List<String>) request.getAttribute("listDm");
                                                            for(String dm : listDm){
                                                                if(dm == b.getListdanhmuc().get(0)){%>
                                                        <option selected value="<%=dm%>"><%=dm%></option>
                                                        <% } else {%>
                                                        <option value="<%=dm%>"><%=dm%></option>
                                                        <%}}%>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="col-12 col-md-6 col-lg-12 col-xl-6">
                                                <div class="form__group">
                                                    <label class="form__label">Chủ đề</label>
                                                    <select class="form__input" name="topic">
                                                        <%List<String> listCd = (List<String>) request.getAttribute("listCd");
                                                            for(String cd : listCd){
                                                                if(cd == b.getListchude().get(0)){%>
                                                        <option selected value="<%=cd%>"><%=cd%></option>
                                                        <% } else {%>
                                                        <option value="<%=cd%>"><%=cd%></option>
                                                        <%}}%>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="col-12">
                                                <button class="form__btn" type="submit"><a><i class="fa fa-save"></i> </a> Lưu lại</button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                                <!-- end details form -->
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
                                                <div class="main__table-text">John Doe</div>
                                            </td>
                                            <td>
                                                <div class="main__table-text"><a href="#">Bánh Cánh Đồng Hoa</a></div>
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
<%--    <div id="modal-view" class="zoom-anim-dialog mfp-hide modal modal--view">--%>
<%--        <div class="comments__autor">--%>
<%--            <img class="comments__avatar" src="img/user.svg" alt="">--%>
<%--            <span class="comments__name">Nguyễn Văn A</span>--%>
<%--            <span class="comments__time">30.08.2018, 17:53</span>--%>
<%--        </div>--%>
<%--        <p class="comments__text">Bánh cánh đồng hoa.</p>--%>
<%--        <p class="comments__text">Giao tại: kp3, quận 7, tpHCM.</p>--%>
<%--        <p class="comments__text">Trạng thái: Giao Thành Công.</p>--%>
<%--        <div class="comments__actions">--%>
<%--            <div class="comments__rate">--%>
<%--                <span><i class="fa fa-thumbs-up"></i>12</span>--%>

<%--                <span>7<i class="fa fa-thumbs-down"></i></span>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
    <!-- end modal view -->

    <!-- modal delete -->
<%--    <div id="modal-delete" class="zoom-anim-dialog mfp-hide modal">--%>
<%--        <h6 class="modal__title">Xóa Bài Viết</h6>--%>

<%--        <p class="modal__text">Bạn có chắc muốn xóa bài viết này?</p>--%>

<%--        <div class="modal__btns">--%>
<%--            <button class="modal__btn modal__btn--apply" type="button">Xóa</button>--%>
<%--            <button class="modal__btn modal__btn--dismiss" type="button">Quay lại</button>--%>
<%--        </div>--%>
<%--    </div>--%>
    <!-- end modal delete -->

    <!-- modal view -->
<%--    <div id="modal-view2" class="zoom-anim-dialog mfp-hide modal modal--view">--%>
<%--        <div class="reviews__autor">--%>
<%--            <img class="reviews__avatar" src="img/user.svg" alt="">--%>
<%--            <span class="reviews__name">Bánh cánh đồng hoa</span>--%>
<%--            <span class="reviews__time">24.08.2018, 17:53 by Thanh Tâm</span>--%>

<%--            <span class="reviews__rating"><i class="fa fa-star"></i>5</span>--%>
<%--        </div>--%>
<%--        <p class="reviews__text">Bánh ngon xuất sắc!.</p>--%>
<%--    </div>--%>
    <!-- end modal view -->

    <!-- modal delete -->
    <div id="modal-delete2" class="zoom-anim-dialog mfp-hide modal">
        <h6 class="modal__title">Xóa Bài Viết</h6>

        <p class="modal__text">Bạn có chắc muốn xóa bài viết này?</p>

        <div class="modal__btns">
            <button class="modal__btn modal__btn--apply" type="button">Xóa</button>
            <button class="modal__btn modal__btn--dismiss" type="button">Quay lại</button>
        </div>
    </div>
    <!-- end modal delete -->

    <!-- modal status -->
    <div id="modal-status3" class="zoom-anim-dialog mfp-hide modal">
        <h6 class="modal__title">Ẩn Bài Viết</h6>

        <p class="modal__text">Bạn có chắc muốn ẩn bài viết này?</p>

        <div class="modal__btns">
            <button class="modal__btn modal__btn--apply" type="button">Ẩn</button>
            <button class="modal__btn modal__btn--dismiss" type="button">Quay lại</button>
        </div>
    </div>
    <!-- end modal status -->

    <!-- modal delete -->
<%--    <div id="modal-delete3" class="zoom-anim-dialog mfp-hide modal">--%>
<%--        <h6 class="modal__title">Xóa Người Dùng</h6>--%>

<%--        <p class="modal__text">Bạn có chắc muốn xóa người dùng này?</p>--%>

<%--        <div class="modal__btns">--%>
<%--            <button class="modal__btn modal__btn--apply" type="button">Xóa</button>--%>
<%--            <button class="modal__btn modal__btn--dismiss" type="button">Quay lại</button>--%>
<%--        </div>--%>
<%--    </div>--%>
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