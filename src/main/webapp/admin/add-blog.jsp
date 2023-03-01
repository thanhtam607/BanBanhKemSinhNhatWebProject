<%@ page import="vn.edu.hcmuaf.fit.bean.User" %>
<%@ page import="java.util.List" %>
<%@ page import="vn.edu.hcmuaf.fit.service.BlogService" %>
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
	<!-- boostrap -->
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<!-- admin -->
    <link rel="stylesheet" href="css/admin.css">
	
	<!-- index css -->
	<link rel="stylesheet" href="css/style.css" >

	<link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
    <link href="lib/tempusdominus/css/tempusdominus-bootstrap-4.min.css" rel="stylesheet" />

    <!-- Favicons -->
    <link rel="icon" type="image/png" href="icon/favicon-32x32.png" sizes="32x32">
    <link rel="apple-touch-icon" href="icon/favicon-32x32.png">
	<!-- <link href="img/favicon.ico" rel="icon"> -->

    <meta name="description" content="">
    <meta name="keywords" content="">
    <meta name="author" content="Dmitry Volkov">
	<title>Admin | Shop Bánh Kem</title>
    <script src="libraries/ckeditor/ckeditor.js"></script>

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
                        <span><%= auth != null ? auth.getRoleName():"Admin"%></span>
                    </div>
                </div>
                <div class="navbar-nav w-100">
                    <a href="./ListReceipt_Admin" class="nav-item nav-link"><i class="fa fa-tachometer-alt me-2"></i>Tổng quan</a>
                    <a href="./ListProduct_Admin" class="nav-item nav-link"><i class="fa fa-th me-2"></i>DS Sản Phẩm</a>
                    <a href="./ListCustomer" class="nav-item nav-link"><i class="fa fa-th me-2"></i>DS Khách Hàng</a>
                    <a href="./ListBlog-admin" class="nav-item nav-link"><i class="fa fa-th me-2"></i>DS Tin Tức</a>
                    <a href="./ListReceipt_full_Admin" class="nav-item nav-link"><i class="fa fa-th me-2"></i>DS Đơn Hàng</a>
                    <a href="add-product.jsp" class="nav-item nav-link"><i class="fa fa-birthday-cake me-2"></i>Thêm Sản Phẩm</a>
                    <a href="./add-blog.html" class="nav-item nav-link active"><i class="fa fa-blog me-2"></i>Thêm Tin Tức</a>
                    <a href="feedbacks.jsp" class="nav-item nav-link"><i class="fa fa-th me-2"></i>DS Đánh giá</a>
                    <a href="../Index" class="nav-item nav-link"><i class="fa fa-arrow-alt-circle-right me-2"></i>Về trang chủ</a>
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
                        <h2>Thêm tin tức mới</h2>
                    </div>
                </div>
                <!-- end main title -->

                <!-- form -->
                <div class="col-12">
                    <form action="AddBlog" class="form" method="post" enctype="multipart/form-data">
                        <div class="row row--form">
                            <div class="col-12">
                                <input type="text" class="form__input" placeholder="Tiêu đề" name="title">
                            </div>
                            <div class="col-12 col-md-5 form__cover">
                                <div class="row row--form">
                                    <div class="col-12 col-sm-6 col-md-12">
                                        <div class="form__img" style="height: 175px">
                                            <label for="form__img-upload">Thêm ảnh (270 x 400)</label>
                                            <input id="form__img-upload" onchange="chooseFile(this)" name="img" type="file" accept=".png, .jpg, .jpeg">
                                            <img id="form__img" src="" alt=" ">
                                        </div>
                                    </div>
                                        <div class="form__group">
                                            <label class="form__label" for="dateblog">Ngày đăng:</label>
                                            <input id="dateblog" type="datetime-local" name="datetime" class="form__input" value="">
                                        </div>
                                    <div class="col-12 col-sm-6 col-md-12">
                                        <label class="text-dark" for="textdm">Danh mục:</label>
                                        <% List<String> listcate = BlogService.listcate();
                                           List<String> listss = BlogService.listss();%>
                                        <select id = "textdm" class="form__input"  name="category">
                                            <%for(int i = 0; i < listcate.size(); i++){
                                                String cate = listcate.get(i);%>
                                            <option value="<%=cate%>"><%=cate%></option>
                                            <% } %>
                                        </select>
                                    </div>
                                    <div class="col-12 col-sm-6 col-md-12">
                                        <label class="text-dark" for="textcd">Chủ đề:</label>
                                        <select id="textcd" class="form__input"  name="season">
                                            <%for(int i = 0; i < listss.size(); i++){
                                                String season = listss.get(i);%>
                                            <option value="<%=season%>"><%=season%></option>
                                            <% } %>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="col-12 col-md-7 form__content">
                                <div class="row row--form">
                                    <div class="col-12">
                                        <textarea id="content" name="content" class="form__textarea-blog">Thêm mô tả</textarea>
                                    </div>
                                </div>
                            </div>
                            <div class="col-12">
                                <button class="form__btn" type="submit">Thêm</button>
                            </div>
                        </div>
                    </form>
                </div>
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
<script>
    CKEDITOR.replace('content');
</script>
</body>

</html>