<%@ page import="vn.edu.hcmuaf.fit.bean.User" %>
<%@ page import="vn.edu.hcmuaf.fit.model.Blog" %>
<%@ page import="java.util.List" %>
<%@ page import="vn.edu.hcmuaf.fit.service.BlogService" %>
<%@ page import="vn.edu.hcmuaf.fit.model.Product" %>
<%@ page import="vn.edu.hcmuaf.fit.service.ProductService" %>
<%@ page import="vn.edu.hcmuaf.fit.model.Order" %>
<%@ page import="vn.edu.hcmuaf.fit.model.FavoriteProduct" %>
<!DOCTYPE html>
<%@ page contentType="text/html;charsetUTF-8" language="java" pageEncoding="utf-8"%>
<html lang="xzz">
<meta http-equiv="Content-Type" charset="UTF-8">

<head>
    <meta charset="UTF-8">
    <meta name="description" content="Ogani Template">
    <meta name="keywords" content="Ogani, unica, creative, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Shop Bánh Kem | Trang Chủ</title>

    <!-- Google Font -->
    <link href="https://fonts.googleapis.com/css2?family=Cairo:wght@200;300;400;600;900&display=swap" rel="stylesheet">

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Dancing+Script:wght@400;500;600;700&family=Roboto:wght@100;300;400;500;700&display=swap" rel="stylesheet">
    <!-- ==================== -->


    <!-- Css Styles -->
    <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="css/font-awesome.min.css" type="text/css">
    <link rel="stylesheet" href="css/elegant-icons.css" type="text/css">
    <link rel="stylesheet" href="css/nice-select.css" type="text/css">
    <link rel="stylesheet" href="css/jquery-ui.min.css" type="text/css">
    <link rel="stylesheet" href="css/owl.carousel.min.css" type="text/css">
    <link rel="stylesheet" href="css/slicknav.min.css" type="text/css">
    <link rel="stylesheet" href="css/style.css" type="text/css">
</head>

<body>

    <% User auth = (User) session.getAttribute("auth");%>
<!-- Page Preloder -->
<div id="preloder">
    <div class="loader"></div>
</div>

<!-- Humberger Begin -->
<div class="humberger__menu__overlay"></div>
<div class="humberger__menu__wrapper">
    <div class="humberger__menu__logo">
        <a href="#"><img src="img/logo_web.jpg" alt=""></a>
    </div>
    <div class="humberger__menu__cart">
        <ul>

            <% FavoriteProduct listFavorite = (FavoriteProduct) session.getAttribute("listFavorite");
            if(auth==null){%>
            <li><a onclick="notLogged()"><i class="fa fa-heart"></i> <span ><%=listFavorite != null ? listFavorite.totalProduct() : "0"%></span></a></li>
               <% }else{ %>
            <li><a href="Favorite"><i class="fa fa-heart"></i> <span id="totalFav"><%=listFavorite != null ? listFavorite.totalProduct() : "0"%></span></a></li>

            <%}%>
            <%Order order = (Order) session.getAttribute("order");%>
            <li><a href="shoping-cart.jsp"><i class="fa fa-shopping-bag"></i> <span id="totalPro"><%= order != null ? order.totalProduct():"0"%></span></a></li>
        </ul>
    </div>
    <div class="humberger__menu__widget">
        <div class="header__top__right__auth">
            <a href="<%=auth == null ?"signin.jsp":"#"%>"><i class="fa fa-user"></i></i><%= auth != null ? auth.getTentk():"Đăng nhập"%></a>
            <% if(auth != null) { %>
            <div class="header__top__right__auth__dropdown">
                <a href="#" class="dropdown-item">Đặt lại mật khẩu</a>
                <% if(auth.checkRole(1)) { %>
                <a href="/BanBanhKemSinhNhatWebProject/admin/Admin" class="dropdown-item">Vào trang quản lí</a>
                <%}%>
                <a href="/BanBanhKemSinhNhatWebProject/doSignOut" method="get" class="dropdown-item">Đăng xuất</a>
            </div>
            <%}%>
        </div>
    </div>
    <nav class="humberger__menu__nav mobile-menu">
        <ul>
            <li class="active"><a href="/BanBanhKemSinhNhatWebProject/Index">Trang chủ</a></li>
            <li class=""><a href="about.jsp">Giới thiệu</a></li>
            <li class=""><a href="/BanBanhKemSinhNhatWebProject/ListProduct">Sản phẩm</a></li>
            <li class=""><a href="/BanBanhKemSinhNhatWebProject/ListBlog">Tin tức</a></li>
            <li class=""><a href="contact.jsp">Liên hệ</a></li>
        </ul>
    </nav>
    <div id="mobile-menu-wrap"></div>
    <div class="header__top__right__social">
        <a href="https://www.facebook.com/mai.thuan.52438/" target="blank"><i class="fa fa-facebook"></i></a>
        <a href="https://www.messenger.com/t/100017755062615" target="blank"><i class="fa fa-comment"></i></a>
        <a href="https://www.instagram.com/maizecorn1542/" target="blank"><i class="fa fa-instagram"></i></a>
    </div>
    <div class="humberger__menu__contact">
        <ul>
            <li><i class="fa fa-envelope"></i> tiembanhhanhphuc@gmail.com</li>
            <li>Miễn phí giao hàng nội thành TP HCM</li>
        </ul>
    </div>
</div>
<!-- Humberger End -->
<!-- Header Section Begin -->
<header class="header">
    <!-- rang cua -->

    <div class="rang_cua"></div>

    <!-- rang cua -->
    <div class="header__top">
        <div class="container">
            <div class="row">
                <div class="col-lg-6 col-md-6">
                    <div class="header__top__left">
                        <ul>
                            <li><i class="fa fa-envelope"></i>tiembanhhanhphuc@gmail.com</li>
                            <li>Miễn phí giao hàng nội thành TP.HCM</li>
                        </ul>
                    </div>
                </div>
                <div class="col-lg-6 col-md-6">
                    <div class="header__top__right">
                        <div class="header__top__right__social">
                            <a href="https://www.facebook.com/mai.thuan.52438/" target="blank"><i class="fa fa-facebook"></i></a>
                            <a href="https://www.messenger.com/t/100017755062615" target="blank"><i class="fa fa-comment"></i></a>
                            <a href="https://www.instagram.com/maizecorn1542/" target="blank"><i class="fa fa-instagram"></i></a>
                        </div>
                        <div class="header__top__right__auth">
                            <a href="<%=auth == null ?"signin.jsp":""%>"><i class="fa fa-user"></i></i><%= auth != null ? auth.getTentk():"Đăng nhập"%></a>
                            <% if(auth != null) { %>
                            <div class="header__top__right__auth__dropdown">
                                <% if(auth.checkRole(1)) { %>
                                <a href="/BanBanhKemSinhNhatWebProject/admin/Admin" class="dropdown-item">Vào trang quản lí</a>
                                <%}%>
                                <a href="/BanBanhKemSinhNhatWebProject/doSignOut" method="get" class="dropdown-item">Đăng xuất</a>
                            </div>
                            <%}%>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="container">
        <div class="row">
            <div class="col-lg-3">
                <div class="header__logo">
                    <a href="/BanBanhKemSinhNhatWebProject/Index"><img src="./img/logo_web.jpg" alt="" class="header__logo_img"></a>
                </div>
            </div>
            <div class="col-lg-7 ">
                <nav class="header__menu">
                    <ul>
                        <li class="active"><a href="/BanBanhKemSinhNhatWebProject/Index">Trang chủ</a></li>
                        <li class=""><a href="about.jsp">Giới thiệu</a></li>
                        <li class=""><a href="/BanBanhKemSinhNhatWebProject/ListProduct">Sản phẩm</a></li>
                        <li class=""><a href="/BanBanhKemSinhNhatWebProject/ListBlog">Tin tức</a></li>
                        <li class=""><a href="contact.jsp">Liên hệ</a></li>
                    </ul>
                </nav>
            </div>
            <div class="col-lg-2">
                <div class="header__cart">
                    <ul>
                        <%if(auth==null){%>
                        <li><a onclick="notLogged()"><i class="fa fa-heart"></i> <span ><%=listFavorite != null ? listFavorite.totalProduct() : "0"%></span></a></li>
                        <% }else {%>
                        <li><a href="Favorite"><i class="fa fa-heart"></i> <span id="totalFav1"><%=listFavorite != null ? listFavorite.totalProduct() : "0"%></span></a></li>

                        <%}%>
                        <li><a href="shoping-cart.jsp"><i class="fa fa-shopping-bag"></i> <span id="totalPro1"><%= order != null ? order.totalProduct():"0"%></span></a></li>
                    </ul>
                </div>
            </div>


        </div>
        <div class="humberger__open">
            <i class="fa fa-bars"></i>
        </div>
    </div>
</header>
<!-- Header Section End -->

    <!-- Hero Section Begin -->
    <section class="hero">
        <div class="container">
            <div class="row">
                <div class="col-lg-3"></div>
                <div class="col-lg-9">
                    <div class="hero__search">
                        <div class="hero__search__form ">
                            <form action="ProductFilter" method="get">
                                <input name="search" type="text" placeholder="Bạn cần bánh gì nè?">
                                <button href="ProductFilter" type="submit" class="site-btn"><i class="fa fa-search"></i> <span class="text_search">TÌM KIẾM</span></button>
                            </form>
                        </div>

                        <div class="hero__search__phone">
                            <div class="hero__search__phone__icon">
                                <a href="tel:0987654321" class="fa fa-phone cursor"></a>
                            </div>
                            <div class="hero__search__phone__text">
                                <h5>+84 987654321</h5>
                                <span>Mở cửa từ 8h - 22h</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="container-fluid p-0 mb-5 pb-5">
                <div id="header-carousel" class="carousel slide" data-ride="carousel">
                    <div class="carousel-inner">
                        <div class="carousel-item active">
                            <img class="w-100 rounded" src="img/hero/carousel-1.jpg" alt="Image">
                            <div class="carousel-caption d-flex flex-column align-items-center justify-content-center distance">
                                <div class="p-3" style="max-width: 900px;">
                                    <h4 class="text-white text-uppercase mb-md-3 slogun_slide">Truyền thống & hiện đại</h4>
                                    <h1 class="display-3 text-white font-weight-bold mb-md-4 slogun_slide">Tận hưởng thế giới ngọt ngào của bạn.</h1>
                                    <a href="./ListProduct" class="btn btn_pink py-md-3 px-md-5 mt-2">Đặt bánh ngay <i class="fa fa-hand-o-down"></i></a>
                                </div>
                            </div>
                        </div>
                        <div class="carousel-item">
                            <img class="w-100 rounded" src="img/hero/carousel-2.jpg" alt="Image">
                            <div class="carousel-caption d-flex flex-column align-items-center justify-content-center distance">
                                <div class="p-3" style="max-width: 900px;">
                                    <h4 class="text-white text-uppercase mb-md-3 slogun_slide">Truyền thống & hiện đại</h4>
                                    <h1 class="display-3 text-white font-weight-bold mb-md-4 slogun_slide">Không chỉ là bánh ngọt, đây là sự yêu thương.</h1>
                                    <a href="./ListProduct" class="btn btn_pink py-md-3 px-md-5 mt-2">Đặt bánh ngay <i class="fa fa-hand-o-down"></i> </a>
                            </div>
                        </div>
                        </div>
                        <a class="carousel-control-prev" href="#header-carousel" data-slide="prev">
                            <div class="btn btn-secondary btn_next_slide px-0" style="width: 45px; height: 45px;">
                                <span class="carousel-control-prev-icon mb-n1"></span>
                            </div>
                        </a>
                        <a class="carousel-control-next" href="#header-carousel" data-slide="next">
                            <div class="btn btn-secondary btn_next_slide px-0" style="width: 45px; height: 45px;">
                                <span class="carousel-control-next-icon mb-n1"></span>
                            </div>
                        </a>
                    </div>
                </div>
            </div> 
            <!-- Carousel End --> 
        </div>
        </div>
    </section>
    <!-- Hero Section End -->

    <!-- Categories Section Begin -->
    <section class="categories">
        <div class="section-title">
            <h2>Sản Phẩm Bán Chạy</h2>
        </div>
        <div class="container">
            <div class="row">
                <div class="categories__slider owl-carousel">
                    <% List<Product> list1 = (List<Product>) request.getAttribute("listBanChay");
                        for(int i = 0; i<10;i++){
                            Product p = list1.get(i);%>
                    <div class="col-lg-3 col-md-4 col-sm-6 mix traditional lover">
                        <span class="icon-km icon-hot"></span>
                        <div class="featured__item">
                            <div class="featured__item__pic set-bg" data-setbg="<%=p.getListImg().get(0)%>">
                                <ul class="featured__item__pic__hover">
                                    <%if(auth==null){%>
                                    <li><a onclick="notLogged()"><i class="fa fa-heart"></i></a></li>
                                    <li><a onclick="notLogged()"><i class="fa fa-shopping-cart"></i></a></li>
                                    <% } else{ %>
                                    <li><a onclick="addToFav('<%=p.getId()%>')"><i class="fa fa-heart"></i></a></li>
                                    <li><a onclick="addToCartI('<%=p.getId()%>')"><i class="fa fa-shopping-cart"></i></a></li>
                                    <%}%>
                                </ul>
                            </div>
                            <div class="featured__item__text">
                                <h6><a href="ProductDetail?id=<%=p.getId() %>"><%=p.getName()%></a></h6>
                                <h5><%=p.formatNum(p.getPrice()) %> VND</h5>
                            </div>
                        </div>
                    </div>
                    <%}%>
                </div>
            </div>
        </div>
    </section>
    <!-- Categories Section End -->

    <!-- Featured Section Begin -->
    <section class="featured spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="featured__controls">
                        <div class="section-title">
                            <h2>Sản Phẩm Mới</h2>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row featured__filter">
                <% List<Product> list = (List<Product>) request.getAttribute("listNewProduct");
                    for(int i = 0; i<8;i++){
                        Product p1 = list.get(i);%>
                <div class="col-lg-3 col-md-4 col-sm-6 mix traditional lover">
                    <span class="icon-km icon-sale"></span>
                    <div class="featured__item">
                        <div class="featured__item__pic set-bg" data-setbg="<%=p1.getListImg().get(0)%>">
                            <ul class="featured__item__pic__hover">
                                <%if(auth==null){%>
                                <li><a onclick="notLogged()"><i class="fa fa-heart"></i></a></li>
                                <li><a onclick="notLogged()"><i class="fa fa-shopping-cart"></i></a></li>
                                <% } else{ %>
                                <li><a onclick="addToFav('<%=p1.getId()%>')"><i class="fa fa-heart"></i></a></li>
                                <li><a onclick="addToCartI('<%=p1.getId()%>')"><i class="fa fa-shopping-cart"></i></a></li>
                                <%}%>
                            </ul>
                        </div>
                        <div class="featured__item__text">
                            <h6><a href="ProductDetail?id=<%=p1.getId() %>"><%=p1.getName()%></a></h6>
                            <h5><%= p1.formatNum(p1.getPrice())%> VND</h5>
                        </div>
                    </div>
                </div>
                <%}%>
                <a href="./ListProduct" type="button" class="btn btn_pink py-md-3 px-md-5 mt-2 btn-rm" >Xem tất cả</a>
            </div>
        </div>
    </section>
    <!-- Featured Section End -->

    <!-- Blog Section Begin -->
    <section class="from-blog spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="section-title from-blog__title">
                        <h2>Tin Tức</h2>
                    </div>
                </div>
            </div>
            <div class="row">
                <% List<Blog> list2 = (List<Blog>) request.getAttribute("list");
                    for(int i = 0; i<3;i++){
                        Blog b2 = list2.get(i);%>
                <div class="col-lg-4 col-md-4 col-sm-6">
                    <div class="blog__item">
                        <div class="blog__item__pic">
                            <img src="<%=b2.getImg()%>" alt="">
                        </div>
                        <div class="blog__item__text">
                            <ul>
                                <li><i class="fa fa-calendar-o"></i><%=b2.getDate()%></li>
                            </ul>
                            <h5><a href="BlogDetails?id=<%=b2.getId()%>"><%=b2.getCategory()%></a></h5>
                            <p class="text-justify"><%=b2.getCont()%></p>
                            <a href="BlogDetails?id=<%=b2.getId()%>" class="blog__btn">Xem thêm <span class="arrow_right"></span> </a>
                        </div>
                    </div>
                </div>
                <%}%>
            </div>
        </div>
    </section>
    <!-- Blog Section End -->
    <jsp:include page="footer.jsp"></jsp:include>

    <!-- Js Plugins -->
    <script src="js/jquery-3.3.1.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/jquery.nice-select.min.js"></script>
    <script src="js/jquery-ui.min.js"></script>
    <script src="js/jquery.slicknav.js"></script>
    <script src="js/mixitup.min.js"></script>
    <script src="js/owl.carousel.min.js"></script>
    <script src="js/main.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>

    <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>