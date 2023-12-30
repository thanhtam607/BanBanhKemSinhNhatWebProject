<%@ page import="vn.edu.hcmuaf.fit.bean.User" %>
<%@ page import="vn.edu.hcmuaf.fit.model.TypeOfCake" %>
<%@ page import="vn.edu.hcmuaf.fit.service.ProductService" %>
<%@ page import="java.util.List" %>
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
    <link rel="icon" href="../img/favicon.ico" type="image/x-icon" />
    
	<!-- CSS -->
    <link rel="stylesheet" href="css/bootstrap-reboot.min.css">
    <link rel="stylesheet" href="css/bootstrap-grid.min.css">
    <link rel="stylesheet" href="css/magnific-popup.css">
    <link rel="stylesheet" href="css/jquery.mCustomScrollbar.min.css">
    <link rel="stylesheet" href="css/select2.min.css">
    <link rel="stylesheet" href="css/ionicons.min.css">
	<!-- boostrap -->
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<!-- admin -->
    <link rel="stylesheet" href="css/admin.css">
	
	<!-- index css -->
	<link rel="stylesheet" href="css/style.css" >

	<link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
    <link href="lib/tempusdominus/css/tempusdominus-bootstrap-4.min.css" rel="stylesheet" />
    <!-- Google Web Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;600&family=Roboto:wght@500;700&display=swap" rel="stylesheet">

    <!-- Favicons -->
    <link rel="icon" type="image/png" href="icon/favicon-32x32.png" sizes="32x32">
    <link rel="apple-touch-icon" href="icon/favicon-32x32.png">
	<!-- <link href="img/favicon.ico" rel="icon"> -->

    <meta name="description" content="">
    <meta name="keywords" content="">
    <meta name="author" content="Dmitry Volkov">
	<title>Admin |  <%=InforService.getInformation("NameShop").get(0).getContent()%></title>
    <style>
        .row input:not(:placeholder-shown) {
            border-color: hsl(0, 76%, 50%);
        }

        /* Show green borders when valid */
        .row--form input:valid {
            border-color: hsl(120, 76%, 50%);
        }
    </style>

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
                    <a href="./ListBlog-admin" class="nav-item nav-link "><i class="fa fa-th me-2"></i>DS Tin Tức</a>
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
<% if(user.getIsadd() == 1 || user.getRole() == 2) { %>
    <main class="main bg-white">
        <div class="container-fluid bg-white">
            <div class="row">
                <!-- main title -->
                <div class="col-12">
                    <div class="main__title">
                        <h2>Thêm sản phẩm</h2>
                    </div>
                </div>
                <!-- end main title -->

                <!-- form -->
                <div class="col-12">
                    <form action="AddProduct"  method="post" class="form" enctype="multipart/form-data">
                        <div class="row row--form">
                            <div class="col-12 col-md-5 form__cover">
                                <div class="row row--form">
                                    <div class="col-12 col-sm-6 col-md-12">
                                        <div class="form__img">
                                            <label for="form__img-upload">Thêm ảnh </label>
                                            <input id="form__img-upload" onchange="chooseFile(this)" name="upload"  type="file" accept=".png, .jpg, .jpeg" required>
                                            <img id="form__img" src="" alt="">
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="col-12 col-md-7 form__content">
                                <div class="row row--form">
                                    <div class="col-12">
                                        <label class="form__label" >Tên bánh:</label>
                                        <input type="text" class="form__input" id="productName" name="tensp" placeholder="Tên bánh"  required pattern="\S+.*">
                                    </div>

                                    <div class="col-12">
                                        <label class="form__label" >Nội dung:</label>
                                        <input name="noidung" class="form__textarea-sp" placeholder="Nội dung" required pattern="\S+.*">
                                    </div>
                                    <div class="col-12">
                                        <label class="form__label" >Mô tả:</label>
                                        <input name="mota" class="form__textarea-sp" placeholder="Mô tả" required pattern="\S+.*">
                                    </div>
                                    <div class="col-12 col-sm-6 col-lg-3">
                                        <label class="form__label" >Giá bán:</label>
                                        <input type="number" min="0" class="form__input" name="gia" placeholder="Giá bán"  required pattern="\S+.*">
                                    </div>


                                    <div class="col-12 col-sm-6 col-lg-3">
                                        <label class="form__label" >Khối lượng:</label>
                                        <input type="number" name="khoiluong" min="0" class="form__input" placeholder="Khối lượng"  required pattern="\S+.*">
                                    </div>
                                    <div class="col-12">
                                        <div class="col-12 col-md-7 col-lg-12 col-xl-7">
                                            <div class="form__group">
                                                <label class="form__label" >Loại bánh:</label>
                                                <select class="form-select form__input" name="loaiBanh" >
                                                    <% List<TypeOfCake> ListType = ProductService.getListType();
                                                    for(TypeOfCake lb: ListType){%>
                                                    <option value="<%=lb.getIdType()%>"><%=lb.getName()%></option>
                                                    <%}%>
                                                </select>
                                            </div>
                                        </div>
                                        <ul class="form__radio">
                                            <li>
                                                <span>Kích thước:</span>
                                            </li>
                                            <li>
                                                <input id="type1" type="radio" name="type" value="Bé" checked="">
                                                <label for="type1">Bé</label>
                                            </li>
                                            <li>
                                                <input id="type2" type="radio" name="type" value="Nhỏ" checked="">
                                                <label for="type2">Nhỏ</label>
                                            </li>
                                            <li>
                                                <input id="type3" type="radio" value="Vừa" name="type">
                                                <label for="type3">Vừa</label>
                                            </li>
                                            <li>
                                                <input id="type4" type="radio" value="Lớn" name="type">
                                                <label for="type4">Lớn</label>
                                            </li>
                                        </ul>
                                    </div>
                                    <% String error = (String) request.getAttribute("Error");%>
                                    <span id="insertTextHere"><%= error != null ? error:""%></span>
                                    <div class="wrap">
                                        <div class="dandev-reviews">
                                            <div class="form__input form_upload">
                                                <label class="dandev_insert_attach">Thêm hình ảnh liên quan</label>
                                            </div>
                                            <div class="list_attach">
                                                <ul class="dandev_attach_view">
                                                </ul>
                                                <span class="dandev_insert_attach"><i class="dandev-plus">+</i></span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-12">
                                <button type="submit" class="form__btn" >Thêm sản phẩm</button>
                            </div>

                        </div>
                    </form>
                </div>
                <!-- end form -->
            </div>
        </div>
    </main>
<% } else { %>
<div class="w-100 h-100 ml-5">
    <h1 class="text-pink mt-5 text-center">Bạn không có quyền này</h1>
</div>
<% } %>
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
    <script src="js/admin.js"></script>

	<!-- ================ -->
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
<script src="js/main.js"></script>
<script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>

</body>

</html>