<%@ page import="vn.edu.hcmuaf.fit.bean.User" %>
<%@ page import="vn.edu.hcmuaf.fit.model.Customer" %>
<%@ page import="java.util.List" %>
<%@ page import="vn.edu.hcmuaf.fit.model.Receipt" %>
<%@ page import="vn.edu.hcmuaf.fit.model.Comment" %>
<%@ page import="vn.edu.hcmuaf.fit.model.Bill_Detail" %>
<%@ page import="vn.edu.hcmuaf.fit.service.*" %>
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
    <link rel="icon" href="../img/favicon.ico" type="image/x-icon" />

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
    <!--  css -->
    <link rel="stylesheet" href="./css/style.css" >

    <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
    <link href="lib/tempusdominus/css/tempusdominus-bootstrap-4.min.css" rel="stylesheet" />


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
<%
    List<Receipt> listre = (List<Receipt>) request.getAttribute("listmakh");
    List<Comment> listcmt = (List<Comment>) request.getAttribute("listcmt");
String mkh = (String) request.getAttribute("mkh"); %>
    <!-- main content -->
<% User user = UserService.findById(auth.getId()); %>
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
                            <% for (int i = 0; i <= 0; i ++){
                                if(!listre.isEmpty()){
                                Receipt rc = listre.get(i);
                                User us = UserService.findById(mkh);%>
                            <div class="profile__meta">
                                <% String main__table = " ";
                                String profile__text ="";
                                    if(us.getStatus() == -1){
                                        main__table = "main__table-text--red";
                                        profile__text = "profile__action--delete";

                                    }else{
                                        main__table = "main__table-text--green";
                                        profile__text = "profile__action--banned";
                                    }%>
                                <h3><%=UserService.findById(mkh).getName()%> <span class="<%=main__table%>">(<%=URLDecoder.decode(us.getStatusName(), "UTF-8")%>)</span></h3>
                                <span name = "makh" value="<%=mkh%>"> ID: <%=mkh%></span>
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
                            <% if(user.getIsedit() == 1 || user.getRole() == 2) { %>
                            <%if(us.getStatus() == -1){%>
                            <a href="#modal-status-unlock" class="profile__action <%=profile__text%> open-modal">
                                <i class="fa fa-lock"></i>
                            </a>
                            <%}else{%>
                            <a href="#modal-status-lock" class="profile__action <%=profile__text%> open-modal">
                                <i class="fa fa-unlock"></i>
                            </a>
                            <%}%>
                            <% } else { %>
                            <% } %>
<%--                            <a href="#modal-delete" class="profile__action profile__action--delete open-modal"><i class="fa fa-trash"></i></a>--%>
                        </div>
                        <!-- end profile btns -->
                        <!-- modal status -->

                        <div id="modal-status-lock" class="zoom-anim-dialog mfp-hide modal">
                            <form method="post" action="AdminLockCusInEditUser">
                                <h6 class="modal__title">Chặn Người Dùng</h6>
                                <p class="modal__text">Bạn có chắc muốn chặn người dùng này?</p>

                                <input name = "makh" value="<%=mkh%>" style="display: none">
                                <div class="modal__btns">
                                    <button class="modal__btn modal__btn--apply" type="submit">Chặn</button>
                                    <button class="modal__btn modal__btn--dismiss" type="button">Quay lại</button>
                                </div>
                            </form>
                        </div>
                        <!-- end modal status -->
                        <!-- modal status -->
                        <div id="modal-status-unlock" class="zoom-anim-dialog mfp-hide modal">
                            <form method="post" action="AdminLockCusInEditUser">
                                <h6 class="modal__title">Bỏ Chặn Người Dùng</h6>
                                <p class="modal__text">Bạn có chắc muốn bỏ chặn người dùng này?</p>

                                <input name = "makh" value="<%=mkh%>" style="display: none">
                                <div class="modal__btns">
                                    <button class="modal__btn modal__btn--apply" type="submit">OK</button>
                                    <button class="modal__btn modal__btn--dismiss" type="button">Quay lại</button>
                                </div>
                            </form>
                        </div>
                        <!-- end modal status -->

                        <!-- modal delete -->
                        <div id="modal-delete" class="zoom-anim-dialog mfp-hide modal">
                            <h6 class="modal__title">Xóa Khách Hàng</h6>

                            <p class="modal__text">Bạn có chắc muốn xóa khách hàng này?</p>

                            <div class="modal__btns">
                                <a href="DeleteUser?makh=<%=mkh%>" class="modal__btn modal__btn--apply" type="button">
                                    Xóa
                                </a>
                                <button class="modal__btn modal__btn--dismiss" type="button">Quay lại</button>
                            </div>
                        </div>
                        <!-- end modal delete -->
                    </div>
                </div>
                <!-- end profile -->

                <!-- content tabs -->
                <div class="tab-content bg-pink" id="myTabContent">
                    <div class="tab-pane fade show active" id="tab-1" role="tabpanel" aria-labelledby="1-tab">
                        <div class="col-12">
                            <div class="row">
                                <!-- details form -->
                                <div class="col-12">
                                    <form action="AdminUpdateUser" method="post" class="form form--profile">
                                        <div class="row row--form">
                                            <div class="col-12">
                                                <h4 class="form__title">Thông tin tài khoản</h4>
                                            </div>
                                           <input name ="makh" value="<%=mkh%>" style="display: none">
                                            <div class="col-12 col-md-6 col-lg-12 col-xl-6">
                                                <div class="form__group">
                                                    <label class="form__label" for="username">Tên tài khoản</label>
                                                    <% if(user.getIsedit() == 1 || user.getRole() == 2) { %>
                                                    <input id="username" type="text" name="username" class="form__input" value="<%=UserService.findById(mkh).getName()%>">
                                                    <% } else { %>
                                                    <input id="username" type="text" name="username" class="form__input  bg-pink" value="<%=UserService.findById(mkh).getName()%>" disabled>
                                                    <% } %>
                                                </div>
                                                <div class="form__group">
                                                    <label class="form__label" for="phone">SĐT</label>
                                                    <% if(user.getIsedit() == 1 || user.getRole() == 2) { %>
                                                    <input id="phone" type="tel" name="phone" class="form__input text-lowercase" value="<%=CustomerService.getCusByIdAcc(mkh).getSDT()%>">
                                                    <% } else { %>
                                                    <input id="phone" type="tel" name="phone" class="form__input text-lowercase  bg-pink" value="<%=CustomerService.getCusByIdAcc(mkh).getSDT()%>" disabled>
                                                    <% } %>
                                                </div>
                                            </div>

                                            <div class="col-12 col-md-6 col-lg-12 col-xl-6">
                                                <div class="form__group">
                                                    <label class="form__label" for="email">Email</label>
                                                    <% if(user.getIsedit() == 1 || user.getRole() == 2) { %>
                                                    <input id="email" type="email" name="email" class="form__input text-lowercase" value="<%=UserService.findById(mkh).getEmail()%>">
                                                    <% } else { %>
                                                    <input id="email" type="email" name="email" class="form__input text-lowercase  bg-pink" value="<%=UserService.findById(mkh).getEmail()%>" disabled>
                                                    <% } %>
                                                </div>
                                                <div class="form__group">
                                                    <label class="form__label" for="address">Địa chỉ</label>
                                                    <% if(user.getIsedit() == 1 || user.getRole() == 2) { %>
                                                    <input id="address" type="text" name="address" class="form__input text-lowercase" value="<%=CustomerService.getCusByIdAcc(mkh).getDIACHI()%>">
                                                    <% } else { %>
                                                    <input id="address" type="text" name="address" class="form__input text-lowercase bg-pink" value="<%=CustomerService.getCusByIdAcc(mkh).getDIACHI()%>" disabled>
                                                    <% } %>
                                                </div>
                                            </div>
                                            <div class="col-12 col-md-6 col-lg-12 col-xl-6">
                                                <% if(user.getRole() == 2) { %>
                                                <div class="form__group">
                                                    <label class="form__label" for="rights">Quyền Hạn</label>
                                                    <select class="form__input" id="rights" name="role">
                                                        <%List<String> listRole = (List<String>) request.getAttribute("listRole");
                                                            for(String r : listRole){
                                                                if(URLDecoder.decode(r, "UTF-8").equals(URLDecoder.decode(UserService.findById(mkh).getRoleName(), "UTF-8"))){%>
                                                        <option selected value="<%=URLDecoder.decode(r, "UTF-8")%>"><%=URLDecoder.decode(r, "UTF-8")%></option>
                                                        <% } else {%>
                                                        <option value="<%=URLDecoder.decode(r, "UTF-8")%>"><%=URLDecoder.decode(r, "UTF-8")%></option>
                                                        <%}}%>
                                                    </select>
                                                </div>
                                                <% } else { %>
                                                <p>Quyền Hạn:  <span class="font-size-20 text--green text-uppercase"><%=UserService.findById(mkh).getRoleName()%></span></p>
                                                <% } %>
                                            </div>
                                            <% if(user.getIsedit() == 1 || user.getRole() == 2) { %>
                                            <div class="col-12">
                                            <input id="" class="form__btn" type="submit"  value="Lưu thông tin">
                                            </div>
                                            <% } else { %>
                                            <% } %>
                                            <% }} %>
                                        </div>
                                    </form>
                                </div>
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
                                            <th>Tên Khách Hàng</th>
                                            <th>Địa Chỉ Giao</th>
                                            <th>Ngày Tạo</th>
                                            <th>Tùy Chọn</th>
                                        </tr>
                                    </thead>

                                    <tbody>
                                    <% int j = 0;
                                        for(Receipt r: listre){%>
                                        <tr>
                                            <td>
                                                <div class="main__table-text"><%=r.getId()%>
                                                    <% if (r.isEdited()) { %>
                                                    <span class="text-danger font-italic small font-weight-bold">
                                                    * Đơn hàng đã bị chỉnh sửa !!!
                                                </span>
                                                    <% }%>
                                                </div>

                                            </td>
                                            <td>
                                                <div class="main__table-text"><%=UserService.findById(r.getMakh()).getName()%></div>
                                            </td>
                                            <td>
                                                <div class="main__table-text"><%=r.getAddress()%></div>
                                            </td>
                                            <td>
                                                <div class="main__table-text"><%=r.getExport_date()%></div>
                                            </td>
                                            <td>
                                                <div class="main__table-btns">
                                                    <a href="Bill_detail_Admin?mahd=<%=r.getId()%>&tenkh=<%=UserService.findById(r.getMakh()).getName()%>" class="main__table-btn main__table-btn--view">
                                                        <i class="fas fa-info"></i>
                                                    </a>
                                                    <% if(user.getIsdelete() == 1 || user.getRole() == 2) { %>
                                                    <a href="#modal-deletehd<%=j%>" class="main__table-btn main__table-btn--delete open-modal">
														<i class="fa fa-trash"></i>
													</a>
                                                    <% } else { %>
                                                    <% } %>
                                                </div>

                                                <!-- modal delete hd-->
                                                <div id="modal-deletehd<%=j%>" class="zoom-anim-dialog mfp-hide modal">
                                                    <h6 class="modal__title">Hủy Đơn Hàng</h6>

                                                    <p class="modal__text">Bạn có chắc muốn hủy đơn hàng này?</p>

                                                    <div class="modal__btns">
                                                        <a href="adminRemoveOrderCTKH?mahd=<%=r.getId()%>&makhCTKH=<%=mkh%>" class="modal__btn modal__btn--apply" type="button">
                                                            Hủy Đơn Hàng
                                                        </a>
                                                        <button class="modal__btn modal__btn--dismiss" type="button">Quay lại</button>
                                                    </div>
                                                </div>
                                                <!-- end modal delete hd-->
                                            </td>
                                        </tr>
                                    <% j++;
                                        }%>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <!-- end table -->

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
                                    <% for(int i = 0; i<listcmt.size();i++){
                                        if(listcmt.get(i).getStatus() != -1){ %>
                                    <tbody>
                                        <tr>
                                            <td>
                                                <div class="main__table-text"><%=mkh%></div>
                                            </td>
                                            <td>
                                                <div class="main__table-text"><a href="#"><%=ProductService.findById(listcmt.get(i).getIdProduct()).getName()%></a></div>
                                            </td>
                                            <td>
                                                <div class="main__table-text"><%=listcmt.get(i).getkhachhang()%></div>
                                            </td>
                                            <td>
                                                <div class="main__table-text"><%=listcmt.get(i).getDate()%></div>
                                            </td>
                                            <td>
                                                <div class="main__table-btns">
                                                    <a href="#modal-view<%=i%>" class="main__table-btn main__table-btn--edit open-modal">
														<i class="fa fa-eye"></i>
													</a>
                                                    <% if(user.getIsdelete() == 1 || user.getRole() == 2) { %>
                                                    <a href="#modal-delete<%=i%>" class="main__table-btn main__table-btn--delete open-modal">
														<i class="fa fa-trash"></i>
													</a>
                                                    <% } else { %>
                                                    <% } %>
                                                </div>
                                                <!-- modal view -->
                                                <div id="modal-view<%=i%>" class="zoom-anim-dialog mfp-hide modal modal--view">
                                                    <div class="reviews__autor">
                                                        <img class="reviews__avatar" src="img/user.svg" alt="">
                                                        <span class="reviews__name"><%=ProductService.findById(listcmt.get(i).getIdProduct()).getName()%> - <%=listcmt.get(i).getIdProduct()%></span>
                                                        <span class="reviews__time"><%=listcmt.get(i).getDate()%> by <%=listcmt.get(i).getkhachhang()%></span>
                                                    </div>
                                                    <p class="reviews__text"><%=listcmt.get(i).getBinhLuan()%></p>
                                                </div>
                                                <!-- end modal view -->
                                                <!--  modal delete -->
                                                <div id="modal-delete<%=i%>" class="zoom-anim-dialog mfp-hide modal">
                                                    <h6 class="modal__title">Xóa Bình Luận</h6>

                                                    <p class="modal__text">Bạn có chắc muốn xóa bình luận này?</p>

                                                    <div class="modal__btns">
                                                        <% String url ="DeleteCommentListReceipt?makh="+mkh+"&idCmt="+listcmt.get(i).getIdcmt()+"&id="+ i; %>
                                                        <button class="modal__btn modal__btn--apply" type="button" onclick="changeHref('<%=url%>') ">Xóa</button>
                                                        <button class="modal__btn modal__btn--dismiss" type="button">Quay lại</button>
                                                    </div>
                                                </div>
                                            </td>
                                            <% }} %>
                                        </tr>
                                    </tbody>
<%--                                    <% } %>--%>
                                </table>
                            </div>
                        </div>
                        <!-- end table -->

                    </div>
                </div>
                <!-- end content tabs -->
            </div>
        </div>
    </main>
    <!-- end main content -->






    <!-- JS -->
<script>
    function  changeHref(link){
        location.href=link;
    }
</script>
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