<%@ page import="vn.edu.hcmuaf.fit.bean.User" %>
<%@ page import="vn.edu.hcmuaf.fit.model.Blog" %>
<%@ page import="java.util.List" %>
<%@ page import="vn.edu.hcmuaf.fit.service.BlogService" %>
<%@ page import="vn.edu.hcmuaf.fit.service.InforService" %>
<%@ page import="vn.edu.hcmuaf.fit.service.UserService" %>
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

    <!-- CSS -->
    <link rel="stylesheet" href="css/bootstrap-reboot.min.css">
    <link rel="stylesheet" href="css/bootstrap-grid.min.css">
    <link rel="stylesheet" href="css/magnific-popup.css">
    <link rel="stylesheet" href="css/jquery.mCustomScrollbar.min.css">
    <link rel="stylesheet" href="css/select2.min.css">
    <link rel="stylesheet" href="css/ionicons.min.css">

    <!-- Google Web Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;600&family=Roboto:wght@500;700&display=swap" rel="stylesheet">

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
    <title>Admin |  <%=InforService.getInformation("NameShop").get(0).getContent()%></title>
    <script src="libraries/ckeditor/ckeditor.js"></script>
</head>

<body>
<% User auth = (User) session.getAttribute("auth");%>
<jsp:include page="spinner.jsp"></jsp:include>
<!-- header -->
<header class="header">
    <div class="header__content">
        <!-- header logo -->
        <a href="./ListReceipt_Admin" class="header__logo">
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
                <h6 class="mb-0"><%= auth != null ? auth.getName():"ADMIN"%></h6>
                <span><%= auth != null ? URLDecoder.decode(auth.getRoleName(), "UTF-8"):"Admin"%></span>
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
<% User user = UserService.findById(auth.getId()); %>
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
                            <% if(user.getIsedit() == 1 || user.getRole() == 2) { %>
                            <div class="profile__avatar">
                                <img src="../<%=b.getImg()%>" alt="" href="#modal-update" class="open-modal">
                            </div>
                            <% } else { %>
                            <div class="profile__avatar">
                                <img src="../<%=b.getImg()%>" alt="">
                            </div>
                            <% } %>
                            <!-- or red -->
                            <div class="profile__meta profile__meta--green">
                                <h3>Tên bài viết: <%=b.getTitle()%></h3>
                                <span>ID: <%=b.getId()%></span>
                            </div>
                        </div>
                        <!-- modal update -->
                        <div id="modal-update" class="zoom-anim-dialog mfp-hide modal">
                            <form action="UpdateImgBlog"  method="POST" enctype="multipart/form-data">
                                <h6 class="modal__title">Thay đổi ảnh</h6>
                                <p class="modal__text">Chọn hình ảnh</p>
                                <input type="text" class="form__input" name="idblog" style="display: none" value="<%=b.getId()%>">
                                <input name = "oldImg" value="<%=b.getImg()%>" style="display: none">
                                <input type="file" class="form__input" name="img">
                                <div class="modal__btns">
                                    <button class="modal__btn modal__btn--apply" type="submit">Xong</button>
                                    <button class="modal__btn modal__btn--dismiss" type="button">Quay lại</button>
                                </div>
                            </form>
                        </div>
                        <!-- end modal update -->
                        <!-- end profile user -->


                        <!-- profile btns -->
                        <div class="profile__actions">
                            <%String main__btn ="";
                                if(BlogService.findById(b.getId()).getStatus() == -1){
                                    main__btn = "main__table-btn--delete";
                                }else{
                                    main__btn = "main__table-btn--banned";
                                }%>
                            <% if(user.getIsedit() == 1 || user.getRole() == 2) { %>
                            <%if(BlogService.findById(b.getId()).getStatus() == -1){%>
                            <a href="#modal-status-unlock" class="main__table-btn <%=main__btn%> open-modal">
                                <i class="fa fa-lock"></i>
                            </a>
                            <%}else{%>
                            <a href="#modal-status-lock" class="main__table-btn <%=main__btn%> open-modal">
                                <i class="fa fa-unlock"></i>
                            </a>
                            <%}%>
                            <a href="#modal-update-title" class="main__table-btn main__table-btn--edit open-modal"><i class="fa fa-edit"></i></a>
                            <% } else { %>
                            <% } %>
                            <% if(user.getIsdelete() == 1 || user.getRole() == 2) { %>
                            <a href="#modal-delete" class="main__table-btn main__table-btn--delete open-modal">
                                <i class="fa fa-trash"></i>
                            </a>
                            <% } else { %>
                            <% } %>
                            <!-- modal update title-->
                            <div id="modal-update-title" class="zoom-anim-dialog mfp-hide modal">
                                <form method="post" action="UpdateTitle">
                                    <h6 class="modal__title">Đổi tên tin tức</h6>
                                    <label class="form__label" for="update-name" style="color: white;">Tên danh mục</label>
                                    <input id="update-name" type="text" name="title" class="form__input" value="<%=b.getTitle()%>">
                                    <input name = "idb" value="<%=b.getId()%>" style="display: none">
                                    <div class="modal__btns">
                                        <button class="modal__btn modal__btn--apply" type="submit">Xác nhận</button>
                                        <button class="modal__btn modal__btn--dismiss" type="button">Quay lại</button>
                                    </div>
                                </form>
                            </div>
                            <!-- end modal update title-->
                            <!-- modal status lock-->
                            <div id="modal-status-lock" class="zoom-anim-dialog mfp-hide modal">
                                <form method="post" action="HideBlog">
                                    <h6 class="modal__title">Ẩn tin tức</h6>
                                    <p class="modal__text">Bạn có chắc muốn ẩn tin tức này này?</p>
                                    <input name = "mablog" value="<%=b.getId()%>" style="display: none">
                                    <input name = "stt" value="-1" style="display: none">
                                    <div class="modal__btns">
                                        <button class="modal__btn modal__btn--apply" type="submit">Ẩn</button>
                                        <button class="modal__btn modal__btn--dismiss" type="button">Quay lại</button>
                                    </div>
                                </form>
                            </div>
                            <!-- modal status unlock-->
                            <div id="modal-status-unlock" class="zoom-anim-dialog mfp-hide modal">
                                <form method="post" action="HideBlog">
                                    <h6 class="modal__title">Bỏ ẩn tin tức</h6>
                                    <p class="modal__text">Bạn có chắc muốn bỏ ẩn tin tức này?</p>
                                    <input name = "mablog" value="<%=b.getId()%>" style="display: none">
                                    <input name = "stt" value="0" style="display: none">
                                    <div class="modal__btns">
                                        <button class="modal__btn modal__btn--apply" type="submit">OK</button>
                                        <button class="modal__btn modal__btn--dismiss" type="button">Quay lại</button>
                                    </div>
                                </form>
                            </div>
                            <!-- modal delete -->
                            <div id="modal-delete" class="zoom-anim-dialog mfp-hide modal">
                                <form method="post" action="DeleteBlog">
                                <h6 class="modal__title">Xóa Bài Viết</h6>
                                <p class="modal__text">Bạn có chắc muốn xóa bài viết này?</p>
                                    <input name = "idblog" value="<%=b.getId()%>" style="display: none">
                                <div class="modal__btns">
                                    <button class="modal__btn modal__btn--apply" type="submit">Xóa</button>
                                    <button class="modal__btn modal__btn--dismiss" type="button">Quay lại</button>
                                </div>
                                </form>
                            </div>
                            <!-- end modal delete -->
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
                                            <div class="col-12 col-md-12 col-lg-12 col-xl-12">
                                                <input style="display: none;" type="text" name="idblog" class="form__input" value="<%=b.getId()%>">
                                            </div>
                                            <div class="col-12 col-md-12 col-lg-12 col-xl-12">
                                                <div class="form__group">
                                                    <label class="form__label" for="content">Nội dung</label>
                                                    <textarea class="form__input text-justify"  id="content" name="content" form="info-blog">
                                                    <% String[] rs = b.getCont().split("\\n");
                                                    for(int i = 0; i < rs.length; i++){%>
                                                        <%=rs[i] + '\n'%>&#10;
                                                    <% } %></textarea>
                                                </div>
                                            </div>
                                            <div class="col-12 col-md-6 col-lg-12 col-xl-6">
                                                <div class="form__group">
                                                    <label class="form__label">Danh mục</label>
                                                    <select class="form__input"  name="category">
                                                        <%List<String> listDm = BlogService.listcate();
                                                            for(String dm : listDm){
                                                                if(b.getCategory().equals(dm)){%>
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
                                                    <select class="form__input" name="season">
                                                        <%List<String> listCd = BlogService.listss();
                                                            for(String cd : listCd){
                                                                if(b.getSeason().equals(cd)){%>
                                                        <option selected value="<%=cd%>"><%=cd%></option>
                                                        <% } else {%>
                                                        <option value="<%=cd%>"><%=cd%></option>
                                                        <%}}%>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="col-12 col-md-12 col-lg-12 col-xl-12">
                                                <div class="form__group">
                                                    <label class="form__label" for="dateblog">Ngày đăng</label>
                                                    <input id="dateblog" type="datetime-local" name="dateblog" class="form__input" value="<%=b.getDate()%>">
                                                </div>
                                            </div>
                                            <% if(user.getIsedit() == 1 || user.getRole() == 2) { %>
                                            <div class="col-12">
                                                <button class="form__btn" type="submit"><a><i class="fa fa-save me-2"></i> </a> Lưu lại</button>
                                            </div>
                                            <% } else { %>
                                            <% } %>
                                        </div>
                                    </form>
                                </div>
                                <!-- end details form -->
                            </div>
                        </div>
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
    <script src="js/main.js"></script>
    <script src="js/admin.js"></script>
<script>
    CKEDITOR.replace('content');
</script>
</body>

</html>