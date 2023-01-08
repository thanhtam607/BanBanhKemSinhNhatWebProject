<%@ page import="vn.edu.hcmuaf.fit.bean.User" %>
<%@ page import="vn.edu.hcmuaf.fit.model.Product" %>
<%@ page import="vn.edu.hcmuaf.fit.model.LoaiBanh" %>
<%@ page import="java.util.List" %>
<%@ page import="vn.edu.hcmuaf.fit.service.ProductService" %>
<%@ page import="vn.edu.hcmuaf.fit.model.Comment" %>
<%@ page import="java.util.ArrayList" %>

<!DOCTYPE html>
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
            <a href="./ListProduct_Admin" class="nav-item nav-link active"><i class="fa fa-th me-2"></i>DS Sản Phẩm</a>
            <a href="./ListCustomer" class="nav-item nav-link "><i class="fa fa-th me-2"></i>DS Khách Hàng</a>
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

    <!-- main content -->
    <main class="main">
        <div class="container-fluid">
            <div class="row">
                <!-- main title -->
                <div class="col-12">
                    <div class="main__title">
                        <h2>Chỉnh sửa sản phẩm</h2>
                    </div>
                </div>
                <!-- end main title -->
<%Product p = (Product) request.getAttribute("product");%>
                <!-- profile -->
                <div class="col-12">
                    <div class="profile__content">
                        <!-- profile user -->
                        <div class="profile__user">
                            <div class="profile__avatar">

                                <img src="../<%=p.getListImg().get(0)%>" alt="">
                            </div>
                            <!-- or red -->
                            <div class="profile__meta profile__meta--green">
                                <h3> <%=p.getName()%></h3>
                                <span><%=p.getStatus()%></span>
                            </div>
                        </div>
                        <!-- end profile user -->

                        <!-- profile tabs nav -->
                        <ul class="nav nav-tabs profile__tabs" id="profile__tabs" role="tablist">
                            <li class="nav-item">
                                <a class="nav-link active" data-toggle="tab" href="#tab-1" role="tab" aria-controls="tab-1" aria-selected="true">Thông Tin</a>
                            </li>

                            <li class="nav-item">
                                <a class="nav-link" data-toggle="tab" href="#tab-2" role="tab" aria-controls="tab-2" aria-selected="false">Hình ảnh (<%=p.getListImg().size()%>)</a>
                            </li>

                            <li class="nav-item">
                                <a class="nav-link" data-toggle="tab" href="#tab-3" role="tab" aria-controls="tab-3" aria-selected="false">Bình Luận (<%=p.getComments().size()%>)</a>
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

                                        <li class="nav-item"><a class="nav-link" id="2-tab" data-toggle="tab" href="#tab-2" role="tab" aria-controls="tab-2" aria-selected="false">Hình ảnh</a></li>

                                    <li class="nav-item"><a class="nav-link" id="3-tab" data-toggle="tab" href="#tab-3" role="tab" aria-controls="tab-3" aria-selected="false">Bình Luận</a></li>
                                </ul>
                            </div>
                        </div>
                        <!-- end profile mobile tabs nav -->

                        <!-- profile btns -->
                        <div class="profile__actions">

                            <a href="#modal-deleteA" class="profile__action profile__action--delete open-modal"><i class="fa fa-trash"></i></a>
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
                                <div class="col-12 col-lg-8">
                                    <form action="Product/UpdateProduct" method="post" class="form form--profile" id="info-product">
                                        <div class="row row--form">
                                            <div class="col-12">
                                                <h4 class="form__title">Thông tin sản phẩm</h4>
                                            </div>
                                            <input id="proID" type="hidden" name="proId" class="form__input" value="<%=p.getId()%>">

                                            <div class="col-12 col-md-12 col-lg-12 col-xl-12">
                                                <div class="form__group">
                                                    <label class="form__label" >Tên sản phẩm</label>
                                                    <input  type="text" name="proname" class="form__input" value="<%=p.getName()%>">
                                                </div>
                                            </div>

                                            <div class="col-12 col-md-7 col-lg-12 col-xl-7">
                                                <div class="form__group">
                                                    <label class="form__label" >Loại bánh</label>
                                                    <select class="form-select form__input" name="loaiBanh" >
                                                    <%List<LoaiBanh> ListType = ProductService.getListType();
                                                        for(LoaiBanh lb: ListType){
                                                    if(lb.getTenLB().equals(p.getLoaiBanh())){%>
                                                        <option selected value="<%=lb.getMaLB()%>"><%=lb.getTenLB()%></option>
                                                        <%} else {%>


                                                        <option value="<%=lb.getMaLB()%>"><%=lb.getTenLB()%></option>



                                                    <%}}%></select>
                                                </div>
                                            </div>
                                            <div class="col-12 col-md-5 col-lg-12 col-xl-5">
                                                <div class="form__group">
                                                    <label class="form__label" for="weight">Khối lượng (g)</label>
                                                    <input id="weight" type="number" min="10" name="weight" class="form__input" value="<%=p.getKhoiLuong()%>">
                                                </div>
                                            </div>
                                            <div class="col-12 col-md-6 col-lg-12 col-xl-6">
                                                <div class="form__group">
                                                    <label class="form__label" for="price">Giá (VND)</label>
                                                    <input id="price" type="number" min="1000" name="price" class="form__input" value="<%=p.getPrice()%>" >
                                                </div>
                                            </div>
                                            <div class="col-12 col-md-6 col-lg-12 col-xl-6">
                                                <div class="form__group">
                                                    <label class="form__label" for="rights">Kích thước</label>
                                                    <select class="form__input" id="rights" name="size">
                                                        <%List<String> listSize = (List<String>) request.getAttribute("listSize");
                                                        for(String s: listSize){
                                                            if(s.equals(p.getKichThuoc())){%>
                                                        <option selected value="<%=s%>"><%=s%></option>
                                                           <% }else{%>
                                                        <option value="<%=s%>"><%=s%></option>
                                                        <%}}%>
													</select>
                                                </div>
                                            </div>
                                            <div class="col-12 col-md-12 col-lg-12 col-xl-12">
                                                <div class="form__group">
                                                    <label class="form__label" for="description">Mô tả sản phẩm</label>
                                                    <textarea class="form__input "  id="description" name="description" form="info-product" ><%=p.getMoTa()%></textarea>
                                                </div>
                                            </div>
                                            <div class="col-12 col-md-12 col-lg-12 col-xl-12">
                                                <div class="form__group">
                                                    <label class="form__label" for="description">Giới thiệu sản phẩm</label>
                                                    <textarea class="form__input "  id="introduce" name="intro" form="info-product" ><%=p.getMoTa()%></textarea>
                                                </div>
                                            </div>

                                            <div class="col-12">
                                                <input class="form__btn" type="submit" value="Lưu thông tin"></input>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                                <!-- end details form -->

                                <!-- password form -->
                                <div class="col-12 col-lg-4">
                                    <div class="form form--profile">
                                        <div class="row row--form">
                                            <div class="col-12">
                                                <h4 class="form__title">Chi tiết sản phẩm</h4>
                                            </div>

                                            <div class="col-12 col-md-12 col-lg-12 col-xl-12">
                                                <div class="form__group">
                                                    <label class="form__label" for="ngaysx">Ngày sản xuất</label>
                                                    <input id="ngaysx" type="date" name="ngaysx" class="form__input" >
                                                </div>
                                            </div>

                                            <div class="col-12 col-md-12 col-lg-12 col-xl-12">
                                                <div class="form__group">
                                                    <label class="form__label" for="ngayhh">Ngày hết hạn</label>
                                                    <input id="ngayhh" type="date" name="ngayhh" class="form__input">
                                                </div>
                                            </div>

                                            <div class="col-12 col-md-12 col-lg-12 col-xl-12">
                                                <div class="form__group">
                                                    <label class="form__label" for="soluong">Số lượng</label>
                                                    <input id="soluong" type="number" min="1" class="form__input" value="<%=p.getDetail().getQuantity()%>">
                                                </div>
                                            </div>
                                            <div class="col-12 col-md-12 col-lg-12 col-xl-12">
                                                <div class="form__group">
                                                    <label class="form__label" for="tonkho">Tồn kho</label>
                                                    <input id="tonkho" type="number" min="1" class="form__input" value="<%=p.getDetail().getInventory()%>">
                                                </div>
                                            </div>

                                            <div class="col-12">
                                                <button class="form__btn" type="button" onclick="updateDetail('<%=p.getId()%>')">Lưu thay đổi</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!-- end password form -->
                            </div>
                        </div>
                    </div>

                    <div class="tab-pane fade" id="tab-2" role="tabpanel" aria-labelledby="2-tab">
                        <!-- table -->
                        <div class="col-11" style="text-align: right; font-size: 25px; color: #e83e8c"><a href="#modal-deleteimg<%=(listSize.size()+1)%>" class="open-modal"><i class="bi bi-plus-square-dotted " ></i></a></div>
                        <!-- modal delete -->
                        <div id="modal-deleteimg<%=(listSize.size()+1)%>" class="zoom-anim-dialog mfp-hide modal">
                            <form action="AddImage"  method="POST" enctype="multipart/form-data">
                                <h6 class="modal__title">Thêm ảnh</h6>
                                <p class="modal__text">Chọn hình ảnh</p>
                                <input type="text" class="form__input" name="masp" style="display: none" value="<%=p.getId()%>">

                                <input type="file" class="form__input" name="newImg">
                                <div class="modal__btns">
                                    <input class="modal__btn modal__btn--apply" type="submit" value="Xong">
                                    <button class="modal__btn modal__btn--dismiss" type="button">Quay lại</button>
                                </div>
                            </form>
                        </div>
                        <!-- end modal delete -->
                        <div class="col-11 ">
                            <div class="main__table-wrap">
                                <table class="main__table">
                                    <thead>
                                        <tr >
                                            <th>STT</th>
                                            <th>Ảnh Sản Phẩm</th>
                                            <th>Vị trí</th>
                                            <th>Tùy Chọn</th>

                                        </tr>

                                    </thead>
                                    <%List<String> listImg = p.getListImg();
                                    for(int i = 0; i< listImg.size();i++){%>
                                    <tbody>

                                        <tr style="border-bottom: 3px solid #ff96b7;">
                                            <td>
                                                <div class="main__table-text"><%=i+1%></div>
                                            </td>

                                            <td>
                                                <div class="main__table-text"><img src="../<%=listImg.get(i)%>" ></div>
                                            </td>
                                            <td>
                                                <div class="main__table-text"><%=listImg.get(i)%></div>
                                            </td>

                                            <td>
                                                <div class="main__table-btns">
                                                    <a href="#modal-deleteimg<%=i%>" class="main__table-btn main__table-btn--edit open-modal">
                                                        <i class="fa fa-edit"></i>
                                                    </a>
                                                    <!-- modal delete -->
                                                    <div id="modal-deleteimg<%=i%>" class="zoom-anim-dialog mfp-hide modal">
                                                        <form action="Product/UpdateImg"  method="POST" enctype="multipart/form-data">
                                                        <h6 class="modal__title">Thay đổi ảnh</h6>
                                                        <p class="modal__text">Chọn hình ảnh</p>
                                                            <input type="text" class="form__input" name="masp" style="display: none" value="<%=p.getId()%>">
                                                            <input type="text" class="form__input" name="oldImg" style="display: none" value="<%=listImg.get(i)%>">
                                                        <input type="file" class="form__input" name="img">
                                                        <div class="modal__btns">
                                                            <input class="modal__btn modal__btn--apply" type="submit" value="Xong">
                                                            <button class="modal__btn modal__btn--dismiss" type="button">Quay lại</button>
                                                        </div>
                                                        </form>
                                                    </div>
                                                    <!-- end modal delete -->
                                                    <a href="#modal-deleteA<%=i%>" class="main__table-btn main__table-btn--delete open-modal">
														<i class="fa fa-trash"></i>
													</a>
                                                    <!-- modal delete -->
                                                    <div id="modal-deleteA<%=i%>" class="zoom-anim-dialog mfp-hide modal">
                                                        <h6 class="modal__title">Xóa hình ảnh</h6>
                                                        <p class="modal__text">Bạn có chắc muốn xóa hình ảnh này?</p>
                                                        <div class="modal__btns">
                                                            <%String url = "Product/DeleteImage?masp="+p.getId()+"&img="+listImg.get(i);%>
                                                            <button class="modal__btn modal__btn--apply" type="button" onclick="changeHref('<%=url%>')">Xóa</button>
                                                            <button class="modal__btn modal__btn--dismiss" type="button">Quay lại</button>
                                                        </div>
                                                    </div>
                                                    <!-- end modal delete -->
                                                </div>
                                            </td>
                                        </tr >
                                    </tbody>
                                    <%}%>
                                </table>
                            </div>
                        </div>
                        <!-- end table -->
                    </div>
                    <div class="tab-pane fade" id="tab-3" role="tabpanel" aria-labelledby="3-tab">
                        <!-- table -->
                        <div class="col-12 bg-pink">
                            <div class="main__table-wrap">
                                <table class="main__table">
                                    <thead>
                                        <tr>
                                            <th>STT</th>
                                            <th>Tên khách hàng</th>
                                            <th>Bình luận</th>
                                            <th>Ngày tạo</th>
                                            <th>Tùy Chọn</th>
                                        </tr>
                                    </thead>
                                    <%List<Comment> listCmt = p.getComments();
                                    for(int i = 0; i<listCmt.size();i++){%>
                                    <tbody>
                                        <tr>
                                            <td>
                                                <div class="main__table-text"><%=i+1%></div>
                                            </td>
                                            <td>
                                                <div class="main__table-text"><%=listCmt.get(i).getkhachhang()%></div>
                                            </td>
                                            <td>
                                                <div class="main__table-text"><a href="#"><%=listCmt.get(i).getBinhLuan()%></a></div>
                                            </td>
                                            <td>
                                                <div class="main__table-text"><a href="#"><%=listCmt.get(i).getDate()%></a></div>
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
                                                <div class="main__table-btns">
                                                    <a href="#modal-delete<%=i%>" class="main__table-btn main__table-btn--delete open-modal">
														<i class="fa fa-trash"></i>
													</a>
                                                    <!-- modal delete -->
                                                    <div id="modal-delete<%=i%>" class="zoom-anim-dialog mfp-hide modal">
                                                        <h6 class="modal__title">Xóa Bình Luận</h6>

                                                        <p class="modal__text">Bạn có chắc muốn xóa bình luận này?</p>

                                                        <div class="modal__btns">
                                                            <% String url ="Product/DeleteComment?masp="+p.getId()+"&idCmt="+listCmt.get(i).getIdcmt()+"&id="+ i; %>
                                                            <button class="modal__btn modal__btn--apply" type="button" onclick="changeHref('<%=url%>') ">Xóa</button>
                                                            <button class="modal__btn modal__btn--dismiss" type="button">Quay lại</button>
                                                        </div>
                                                    </div>
                                                    <!-- end modal delete -->
                                                </div>
                                            </td>
                                        </tr>
                                    </tbody>
                                    <%}%>
                                </table>
                            </div>
                        </div>
                        <!-- end table -->
                    </div>
                </div>
                <!-- end content tabs -->
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
    <div id="modal-deleteb1" class="zoom-anim-dialog mfp-hide modal">
        <h6 class="modal__title">Thay đổi ảnh</h6>
        <p class="modal__text">Chọn hình ảnh</p>
        <input type="file" class="form__input">
        <div class="modal__btns">
            <button class="modal__btn modal__btn--apply" type="button">Xong</button>
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



    <!-- JS -->
    <script src="js/jquery-3.5.1.min.js"></script>
    <script src="js/bootstrap.bundle.min.js"></script>
    <script src="js/jquery.magnific-popup.min.js"></script>
    <script src="js/jquery.mousewheel.min.js"></script>
    <script src="js/jquery.mCustomScrollbar.min.js"></script>
    <script src="js/select2.min.js"></script>
    <script src="js/admin.js"></script>
<script>
function  changeHref(link){
    location.href=link;
}
</script>
</body>

</html>