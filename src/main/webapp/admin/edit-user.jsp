<%@ page import="vn.edu.hcmuaf.fit.bean.User" %>
<%@ page import="vn.edu.hcmuaf.fit.model.Customer" %>
<%@ page import="java.util.List" %>
<%@ page import="vn.edu.hcmuaf.fit.model.Receipt" %>
<%@ page import="vn.edu.hcmuaf.fit.model.Comment" %>
<%@ page import="vn.edu.hcmuaf.fit.service.ReceiptService" %>
<%@ page import="vn.edu.hcmuaf.fit.model.CTHD" %>
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
            <a href="./ListCustomer" class="nav-item nav-link active"><i class="fa fa-th me-2"></i>DS Khách Hàng</a>
            <a href="./ListBlog-admin" class="nav-item nav-link "><i class="fa fa-th me-2"></i>DS Tin Tức</a>
            <a href="./ListReceipt_full_Admin" class="nav-item nav-link "><i class="fa fa-th me-2"></i>DS Đơn Hàng</a>
            <a href="add-product.jsp" class="nav-item nav-link"><i class="fa fa-birthday-cake me-2"></i>Thêm Sản Phẩm</a>
            <a href="add-blog.jsp" class="nav-item nav-link"><i class="fa fa-blog me-2"></i>Thêm Tin Tức</a>
            <a href="../Index" class="nav-item nav-link"><i class="fa fa-arrow-alt-circle-right me-2"></i>Về trang chủ</a>
            <!--  -->
        </div>
    </nav>
</div>
<!-- Sidebar End -->
<% List<Receipt> listre = (List<Receipt>) request.getAttribute("listmakh");%>
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
                            <% for (int i = 0; i <= 0; i ++){
                                if(!listre.isEmpty()){
                                Receipt rc = listre.get(i); %>
                            <div class="profile__meta">
                                <% String main__table = " ";
                                String profile__text ="";
                                    if(rc.getRoleint() == -1){
                                        main__table = "main__table-text--red";
                                        profile__text = "profile__action--delete";
                                    } else if(rc.getRoleint() == 1){
                                        main__table = "main__table-text--green";
                                        profile__text = "profile__action--banned";
                                    }else{
                                        main__table = "main__table-text--black";
                                        profile__text = "profile__action--banned";
                                    }%>
                                <h3><%=rc.getNamecustomer()%> <span class="<%=main__table%>">(<%=rc.getRole()%>)</span></h3>
                                <span name = "makh" value="<%=rc.getMakh()%>"> ID: <%=rc.getMakh()%></span>
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
                            <a href="#modal-status" class="profile__action <%=profile__text%> open-modal"><i class="fa fa-lock"></i></a>
                            <a href="#modal-delete" class="profile__action profile__action--delete open-modal"><i class="fa fa-trash"></i></a>
                        </div>
                        <!-- end profile btns -->
                        <!-- modal status -->
                        <% String mkh = (String) request.getAttribute("mkh");%>
                        <div id="modal-status" class="zoom-anim-dialog mfp-hide modal">
                            <form method="post" action="AdminLockCus">
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
                                    <form action="UpdateRole" method="post" class="form form--profile">
                                        <div class="row row--form">
                                            <div class="col-12">
                                                <h4 class="form__title">Thông tin tài khoản</h4>
                                            </div>
                                           <input name = "makh" value="<%=rc.getMakh()%>" style="display: none">
                                            <div class="col-12 col-md-6 col-lg-12 col-xl-6">
                                                <div class="form__group">
                                                    <label class="form__label" for="username">Tên tài khoản</label>
                                                    <input id="username" type="text" name="username" class="form__input" value="<%=rc.getTenTK()%>">
                                                </div>
                                            </div>

                                            <div class="col-12 col-md-6 col-lg-12 col-xl-6">
                                                <div class="form__group">
                                                    <label class="form__label" for="email">Email</label>
                                                    <input id="email" type="text" name="email" class="form__input text-lowercase" value="<%=rc.getEmail()%>">
                                                </div>
                                            </div>
                                            <div class="col-12 col-md-6 col-lg-12 col-xl-6">
                                                <div class="form__group">
                                                    <label class="form__label" for="rights">Phân Quyền</label>
                                                    <select class="form__input" id="rights" name="role">
                                                        <%List<String> listRole = (List<String>) request.getAttribute("listRole");
                                                            for(String r : listRole){
                                                                if(r == rc.getRole()){%>
                                                        <option selected value="<%=r%>"><%=r%></option>
                                                        <% } else {%>
                                                        <option value="<%=r%>"><%=r%></option>
                                                        <%}}%>
                                                    </select>
                                            </div>
                                            </div>
                                            <div class="col-12">
                                            <input id="" class="form__btn" type="submit"  value="Lưu thông tin">
                                            </div>
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
                                                <div class="main__table-text"><%=r.getId()%></div>
                                            </td>
                                            <td>
                                                <div class="main__table-text"><%=r.getNamecustomer()%></div>
                                            </td>
                                            <td>
                                                <div class="main__table-text"><%=r.getAddress()%></div>
                                            </td>
                                            <td>
                                                <div class="main__table-text"><%=r.getSdate()%></div>
                                            </td>
                                            <td>
                                                <div class="main__table-btns">
                                                    <a href="#modal-viewcthd<%=j%>" class="main__table-btn main__table-btn--edit open-modal">
														<i class="fa fa-eye"></i>
													</a>
                                                    <a href="#modal-deletehd<%=j%>" class="main__table-btn main__table-btn--delete open-modal">
														<i class="fa fa-trash"></i>
													</a>
                                                </div>
                                                <!-- modal view cthd-->
                                                <div id="modal-viewcthd<%=j%>" class="zoom-anim-dialog mfp-hide modal modal--view">
                                                    <div class="comments__autor">
                                                        <img class="comments__avatar" src="img/user.svg" alt="">
                                                        <span class="comments__name"><%=r.getNamecustomer()%></span>

                                                        <span class="comments__time"><%=r.getEdate()%></span>
                                                    </div>
                                                    <%List<CTHD> cthds = ReceiptService.getcthdUser(r.getId());
                                                    for(CTHD cthd: cthds){
                                                     %>
                                                    <p class="comments__text">Tên Sản Phẩm: <%=cthd.getTensp()%></p>

                                                    <p class="comments__text">Số Lượng: <%=cthd.getSolg()%> </p>
                                                    <%}%>
                                                    <p class="comments__text">Địa chỉ giao: <%=r.getAddress()%></p>
                                                    <p class="comments__text">Trạng thái: <%=r.getState()%></p>
                                                </div>
                                                <!-- end modal view cthd-->
                                                <!-- modal delete hd-->
                                                <div id="modal-deletehd<%=j%>" class="zoom-anim-dialog mfp-hide modal">
                                                    <h6 class="modal__title">Hủy Đơn Hàng</h6>

                                                    <p class="modal__text">Bạn có chắc muốn hủy đơn hàng này?</p>

                                                    <div class="modal__btns">
                                                        <a href="adminRemoveOrderCTKH?mahd=<%=r.getId()%>&makhCTKH=<%=r.getMakh()%>" class="modal__btn modal__btn--apply" type="button">
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
                                    <%for(Receipt r: listre){%>
                                    <%List<Comment> listCmt = r.getCommentList();
                                        for(int i = 0; i<listCmt.size();i++){%>
                                    <tbody>
                                        <tr>
                                            <td>
                                                <div class="main__table-text"><%=r.getMakh()%></div>
                                            </td>
                                            <td>
                                                <div class="main__table-text"><a href="#"><%=r.getNamecake()%></a></div>
                                            </td>
                                            <td>
                                                <div class="main__table-text"><%=r.getNamecustomer()%></div>
                                            </td>
                                            <td>
                                                <div class="main__table-text"><%=r.getCommentList().get(i).getDate()%></div>
                                            </td>
                                            <td>
                                                <div class="main__table-btns">
                                                    <a href="#modal-view<%=i%>" class="main__table-btn main__table-btn--edit open-modal">
														<i class="fa fa-eye"></i>
													</a>
                                                    <a href="#modal-delete<%=i%>" class="main__table-btn main__table-btn--delete open-modal">
														<i class="fa fa-trash"></i>
													</a>

                                                </div>
                                                <!-- modal view -->
                                                <div id="modal-view<%=i%>" class="zoom-anim-dialog mfp-hide modal modal--view">
                                                    <div class="reviews__autor">
                                                        <img class="reviews__avatar" src="img/user.svg" alt="">
                                                        <span class="reviews__name"><%=r.getNamecake()%></span>
                                                        <span class="reviews__time"><%=r.getCommentList().get(i).getDate()%> by <%=r.getCommentList().get(i).getkhachhang()%></span>
                                                    </div>
                                                    <p class="reviews__text"><%=r.getCommentList().get(i).getBinhLuan()%></p>
                                                </div>
                                                <!-- end modal view -->
                                                <!-- end modal delete -->
                                                <div id="modal-delete<%=i%>" class="zoom-anim-dialog mfp-hide modal">
                                                    <h6 class="modal__title">Xóa Bình Luận</h6>

                                                    <p class="modal__text">Bạn có chắc muốn xóa bình luận này?</p>

                                                    <div class="modal__btns">
                                                        <% String url ="DeleteCommentListReceipt?makh="+r.getMakh()+"&idCmt="+listCmt.get(i).getIdcmt()+"&id="+ i; %>
                                                        <button class="modal__btn modal__btn--apply" type="button" onclick="changeHref('<%=url%>') ">Xóa</button>
                                                        <button class="modal__btn modal__btn--dismiss" type="button">Quay lại</button>
                                                    </div>
                                                </div>
                                            </td>
                                            <% } %>
                                        </tr>
                                    </tbody>
                                    <% } %>
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
    <script src="js/admin.js"></script>
</body>

</html>