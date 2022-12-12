
<%@ page import="java.util.List" %>
<%@ page import="vn.edu.hcmuaf.fit.model.Product" %>
<%@ page import="vn.edu.hcmuaf.fit.bean.User" %>
<%@ page import="vn.edu.hcmuaf.fit.model.Order" %>
<!DOCTYPE html>
<%@ page contentType="text/html;charsetUTF-8" language="java" pageEncoding="utf-8"%>
<html lang="xzz">
<meta http-equiv="Content-Type" charset="UTF-8">
<head>

    <title>Shop Bánh Kem | Sản phẩm</title>

   <!-- Google Font -->
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
            <a href="#"><img src="./img/logo_web.jpg" alt="" class="header__logo_img"></a>
        </div>
        <div class="humberger__menu__cart">
            <ul>
                <li><a href="favorites.jsp"><i class="fa fa-heart"></i> <span>1</span></a></li>
                <%Order order = (Order) session.getAttribute("order");%>
                <li><a href="/BanBanhKemSinhNhatWebProject/CartController"><i class="fa fa-shopping-bag"></i> <span><%= order != null ? order.getData().size():"0"%></span></a></li>
            </ul>
        </div>
        <div class="humberger__menu__widget">

            <div class="header__top__right__auth">
                <a href="signin.jsp"><i class="fa fa-user"></i></i><%= auth != null ? auth.getTentk():"Đăng nhập"%></a>
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
        <nav class="humberger__menu__nav mobile-menu">
            <ul>
                <li><a href="./Index">Trang chủ</a></li>
                <li><a href="about.jsp">Giới thiệu</a></li>
                <li class="active"><a href="././ListProduct">Sản phẩm</a>
                    <ul class="header__menu__dropdown">
                        <li><a href="shop-details.jsp">Chi tiết sản phẩm</a></li>

                    </ul> -->
                </li>
                <li><a href="./ListBlog">Tin tức</a></li>
                <li><a href="contact.jsp">Liên hệ</a></li>
            </ul>
        </nav>
        <div id="mobile-menu-wrap"></div>
        <div class="header__top__right__social">
            <a href="https://www.facebook.com/mai.thuan.52438/"><i class="fa fa-facebook"></i></a>
            <a href="https://www.messenger.com/t/100017755062615"><i class="fa fa-comment"></i></a>
            <a href="https://www.instagram.com/maizecorn1542/"><i class="fa fa-instagram"></i></a>
        </div>
        <div class="humberger__menu__contact">
            <ul>
                <li><i class="fa fa-envelope"></i> tiembanhhanhphuc@gmail.com</li>
                <li>Miễn phí giao hàng nội thành TP.HCM</li>
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
                                <a href="https://www.facebook.com/mai.thuan.52438/"><i class="fa fa-facebook"></i></a>
                                <a href="https://www.messenger.com/t/100017755062615"><i class="fa fa-comment"></i></a>
                                <a href="https://www.instagram.com/maizecorn1542/"><i class="fa fa-instagram"></i></a>
                            </div>
                            <div class="header__top__right__auth">
                                <a href="signin.jsp"><i class="fa fa-user"></i></i><%= auth != null ? auth.getTentk():"Đăng nhập"%></a>
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
                        <a href="./Index"><img src="./img/logo_web.jpg" alt="" class="header__logo_img"></a>
                    </div>
                </div>
                <div class="col-lg-7">
                    <nav class="header__menu">
                        <ul>
                            <li><a href="./Index">Trang chủ</a></li>
                            <li><a href="about.jsp">Giới thiệu</a></li>
                            <li class="active"><a href="./ListProduct">Sản phẩm</a>
                            </li>
                            <li><a href="./ListBlog">Tin tức</a></li>
                            <li><a href="contact.jsp">Liên hệ</a></li>
                        </ul>
                    </nav>
                </div>
                <div class="col-lg-2">
                    <div class="header__cart">
                        <ul>
                            <li><a href="favorites.jsp"><i class="fa fa-heart"></i> <span>1</span></a></li>
                            <li><a href="/BanBanhKemSinhNhatWebProject/CartController"><i class="fa fa-shopping-bag"></i> <span><%= order != null ? order.getData().size():"0"%></span></a></li>
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
    <section class="hero hero-normal">
        <div class="container">
            <div class="row">
                <div class="col-lg-3"></div>
                <div class="col-lg-9">
                    <div class="hero__search">
                        <div class="hero__search__form ">
                            <form action="#" >

                                <input type="text" placeholder="Bạn cần bánh gì nè?">
                                <button type="submit" class="site-btn"><i class="fa fa-search"></i> <span class="text_search">TÌM KIẾM</span></button>
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
            </div>
        </div>
    </section>
    <!-- Hero Section End -->

    <!-- Breadcrumb Section Begin -->
    <section class="breadcrumb-section set-bg" data-setbg="././img/banner/breadcrumb.jpg">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <div class="breadcrumb__text">
                        <h2>Sản phẩm</h2>
                        <div class="breadcrumb__option">
                            <a href="./Index">Trang Chủ</a>
                            <span>Sản Phẩm</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Breadcrumb Section End -->

    <!-- Product Section Begin -->
    <section class="product spad">
        <div class="container">
            <div class="row">

                <div class="col-lg-3 col-md-5">
                    <div class="sidebar">
                        <div class="sidebar__item">
                            <h4>Các Loại Bánh</h4>
                            <ul class="slidebar__loaibanh">
                                <li class="text__loaibanh"><a class="text__loaibanh__active" href="./shop-product.html">Tất cả</a></li>
                                <li class="text__loaibanh"><a href="shop-product-banh-hoa.jsp">Bánh hoa</a></li>
                                <li class="text__loaibanh"><a href="./shop-product-trang-tri-don-gian.html">Bánh trang trí đơn giản</a></li>
                                <li class="text__loaibanh"><a href="#">Bánh trang trí hình thú</a></li>
                                <li class="text__loaibanh"><a href="#">Bánh trái cây</a></li>
                                <li class="text__loaibanh"><a href="#">Bánh hoa mousse</a></li>

                            </ul>
                        </div>
                        <div class="sidebar__item">
                            <h4>Giá (VNĐ)</h4>
                            <div class="price-range-wrap">
                                <div class="price-range ui-slider ui-corner-all ui-slider-horizontal ui-widget ui-widget-content"
                                    data-min="50" data-max="1000">
                                    <div class="ui-slider-range ui-corner-all ui-widget-header"></div>
<%--                                    <span tab./Index="0" class="ui-slider-handle ui-corner-all vnd-state-default"></span>--%>
<%--                                    <span tab./Index="0" class="ui-slider-handle ui-corner-all vnd-state-default"></span>--%>
                                </div>
                                <div class="range-slider">
                                    <div class="price-input">
                                        <input type="text" id="minamount">
                                        <input type="text" id="maxamount">
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="sidebar__item">
                            <h4>Kích thước</h4>
                            <div class="sidebar__item__size">
                                <label for="large">
                                    <a href="./ProductFilter?size=Lớn" id="large">Lớn</a>
                                </label>
                            </div>
                            <div class="sidebar__item__size">
                                <label  for="medium">
                                    <a href="./ProductFilter?size=Vừa" id="medium">Vừa</a>
                                </label>
                            </div>
                            <div  class="sidebar__item__size">
                                <label for="small">
                                    <a href="./ProductFilter?size=Nhỏ" id="small">Nhỏ</a>
                                </label>
                            </div>
                            <div class="sidebar__item__size">
                                <label for="tiny">
                                    <a href="./ProductFilter?size=Bé" id="tiny">Bé</a>
                                </label>
                            </div>
                        </div>
                        <div class="sidebar__item">
                            <div class="latest-product__text">
                                <h4>Top Bán Chạy</h4>
                                <div class="latest-product__slider owl-carousel">
                                    <div class="latest-prdouct__slider__item">
                                        <a href="#" class="latest-product__item">
                                            <div class="latest-product__item__pic">
                                                <img src="./img/product/B019/banh1.jpg" alt="">
                                            </div>
                                            <div class="latest-product__item__text">
                                                <h6>Bánh Sinh Nhật</h6>
                                                <span>200,000 VND</span>
                                            </div>
                                        </a>
                                        <a href="#" class="latest-product__item">
                                            <div class="latest-product__item__pic">
                                                <img src="./img/product/B022/banh1.jpg" alt="">
                                            </div>
                                            <div class="latest-product__item__text">
                                                <h6>Bánh Sinh Nhật</h6>
                                                <span>250,000 VND</span>
                                            </div>
                                        </a>
                                        <a href="#" class="latest-product__item">
                                            <div class="latest-product__item__pic">
                                                <img src="./img/product/B023/banh1.jpg" alt="">
                                            </div>
                                            <div class="latest-product__item__text">
                                                <h6>Bánh Sinh Nhật</h6>
                                                <span>300,000 VND</span>
                                            </div>
                                        </a>
                                    </div>
                                    <div class="latest-prdouct__slider__item">
                                        <a href="#" class="latest-product__item">
                                            <div class="latest-product__item__pic">
                                                <img src="./img/product/B024/banh1.jpg" alt="">
                                            </div>
                                            <div class="latest-product__item__text">
                                                <h6>Bánh Sinh Nhật</h6>
                                                <span>300,000 VND</span>
                                            </div>
                                        </a>
                                        <a href="#" class="latest-product__item">
                                            <div class="latest-product__item__pic">
                                                <img src="./img/product/B025/banh1.jpg" alt="">
                                            </div>
                                            <div class="latest-product__item__text">
                                                <h6>Bánh Sinh Nhật</h6>
                                                <span>300,000 VND</span>
                                            </div>
                                        </a>
                                        <a href="#" class="latest-product__item">
                                            <div class="latest-product__item__pic">
                                                <img src="./img/product/B026/banh1.jpg" alt="">
                                            </div>
                                            <div class="latest-product__item__text">
                                                <h6>Bánh Sinh Nhật</h6>
                                                <span>300,000 VND</span>
                                            </div>
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-9 col-md-7">
                    <div class="product__discount">
                        <div class="section-title product__discount__title">
                            <h2>Khuyến Mãi</h2>
                        </div>
                        <div class="row">
                            <div class="product__discount__slider owl-carousel">
                                <div class="col-lg-4">
                                    <div class="product__discount__item">
                                        <div class="product__discount__item__pic set-bg"
                                            data-setbg="./img/product/B001/banh1.jpg">
                                            <div class="product__discount__percent">-20%</div>
                                            <ul class="product__item__pic__hover">
                                                <li><a href="#"><i class="fa fa-heart"></i></a></li>
                                                <li><a href="#"><i class="fa fa-retweet"></i></a></li>
                                                <li><a href="#"><i class="fa fa-shopping-cart"></i></a></li>
                                            </ul>
                                        </div>
                                        <div class="product__discount__item__text">
                                            <span>Bánh hoa</span>
                                            <h5><a href="shop-details.jsp">Bánh cánh đồng hoa</a></h5>
                                            <div class="product__item__price">300,000 VND <span>360,000 VND</span></div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-4">
                                    <div class="product__discount__item">
                                        <div class="product__discount__item__pic set-bg"
                                            data-setbg="./img/product/B067/banh2.jpg">
                                            <div class="product__discount__percent">-20%</div>
                                            <ul class="product__item__pic__hover">
                                                <li><a href="#"><i class="fa fa-heart"></i></a></li>
                                                <li><a href="#"><i class="fa fa-retweet"></i></a></li>
                                                <li><a href="#"><i class="fa fa-shopping-cart"></i></a></li>
                                            </ul>
                                        </div>
                                        <div class="product__discount__item__text">
                                            <span>Bánh maccaron và socola</span>
                                            <h5><a href="shop-details.jsp">Bánh maccaron hoa hồng</a></h5>
                                            <div class="product__item__price">300,000 VND <span>360,000 VND</span></div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-4">
                                    <div class="product__discount__item">
                                        <div class="product__discount__item__pic set-bg"
                                            data-setbg="./img/product/B026/banh1.jpg">
                                            <div class="product__discount__percent">-20%</div>
                                            <ul class="product__item__pic__hover">
                                                <li><a href="#"><i class="fa fa-heart"></i></a></li>
                                                <li><a href="#"><i class="fa fa-retweet"></i></a></li>
                                                <li><a href="#"><i class="fa fa-shopping-cart"></i></a></li>
                                            </ul>
                                        </div>
                                        <div class="product__discount__item__text">
                                            <span>Bánh trái cây</span>
                                             <h5><a href="shop-details.jsp">Bánh kem dứa caramel</a></h5>
                                            <div class="product__item__price">300,000 VND <span>360,000 VND</span></div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-4">
                                    <div class="product__discount__item">
                                        <div class="product__discount__item__pic set-bg"
                                            data-setbg="./img/product/B012/banh1.jpg">
                                            <div class="product__discount__percent">-20%</div>
                                            <ul class="product__item__pic__hover">
                                                <li><a href="#"><i class="fa fa-heart"></i></a></li>
                                                <li><a href="#"><i class="fa fa-retweet"></i></a></li>
                                                <li><a href="#"><i class="fa fa-shopping-cart"></i></a></li>
                                            </ul>
                                        </div>
                                        <div class="product__discount__item__text">
                                            <span>Bánh hoa</span>
                                             <h5><a href="shop-details.jsp">Bánh hoa xanh trang nhã</a></h5>
                                            <div class="product__item__price">300,000 VND <span>360,000 VND</span></div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-4">
                                    <div class="product__discount__item">
                                        <div class="product__discount__item__pic set-bg"
                                            data-setbg="././img/product/B021/banh1.jpg">
                                            <div class="product__discount__percent">-20%</div>
                                            <ul class="product__item__pic__hover">
                                                <li><a href="#"><i class="fa fa-heart"></i></a></li>
                                                <li><a href="#"><i class="fa fa-retweet"></i></a></li>
                                                <li><a href="#"><i class="fa fa-shopping-cart"></i></a></li>
                                            </ul>
                                        </div>
                                        <div class="product__discount__item__text">
                                            <span>Bánh trái cây</span>
                                             <h5><a href="shop-details.jsp">Bánh oreo việt quất</a></h5>
                                            <div class="product__item__price">300,000 VND <span>360,000 VND</span></div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-4">
                                    <div class="product__discount__item">
                                        <div class="product__discount__item__pic set-bg"
                                            data-setbg="./img/product/B022/banh1.jpg">
                                            <div class="product__discount__percent">-20%</div>
                                            <ul class="product__item__pic__hover">
                                                <li><a href="#"><i class="fa fa-heart"></i></a></li>
                                                <li><a href="#"><i class="fa fa-retweet"></i></a></li>
                                                <li><a href="#"><i class="fa fa-shopping-cart"></i></a></li>
                                            </ul>
                                        </div>
                                        <div class="product__discount__item__text">
                                            <span>Bánh trái cây</span>
                                             <h5><a href="shop-details.jsp">Bánh kem trái cây hạnh nhân</a></h5>
                                            <div class="product__item__price"> 300,000 VND <span>360,000 VND</span></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="filter__item">
                        <div class="row">
                            <div class="col-lg-4 col-md-5">
                                <div class="filter__sort">
                                    <span>Sắp Xếp</span>
                                    <select>
                                        <option value="0">Mặc định</option>
                                        <option value="0">Giá từ cao tới thấp</option>
                                        <option value="0">Giá từ thấp đến cao</option>
                                    </select>
                                </div>
                            </div>
                            <div class="col-lg-4 col-md-4">
                                <div class="filter__found">
                                    <h6><span>12</span> Sản phẩm</h6>
                                </div>
                            </div>
                            <div class="col-lg-4 col-md-3">
                                <div class="filter__option">
                                    <span class="icon_grid-2x2"></span>
                                    <!-- <span class="icon_ul"></span> -->
                                </div>
                            </div>3
                        </div>
                    </div>
                    <div class="row">
                        <% List<Product> list = (List<Product>) request.getAttribute("list");
                            for(Product p: list){ %>
                        <div class="col-lg-4 col-md-6 col-sm-6">
                            <div class="product__item">
                                <div class="product__item__pic set-bg" data-setbg="<%=p.getListImg().get(0)%>">
                                    <ul class="product__item__pic__hover">
                                        <li><a href="#"><i class="fa fa-heart"></i></a></li>
                                        <li><a href="#"><i class="fa fa-retweet"></i></a></li>
                                        <li><a href="AddToCart?masp=<%=p.getId()%>"><i class="fa fa-shopping-cart"></i></a></li>
                                    </ul>
                                </div>
                                <div class="product__item__text">
                                    <h6><a href="ProductDetail?id=<%=p.getId() %>"><%= p.getName()%></a></h6>
                                    <h5><%= p.formatNum(p.getPrice())%> VND</h5>
                                </div>
                            </div>
                        </div>
                        <% } %>
                    </div>
                    <div class="product__pagination">
                        <a class="product__pagination__page2" href="#">1</a>
                        <a href="./shop-product-1.html">2</a>
                        <a href="#">3</a>
                        <a href="#"><i class="fa fa-long-arrow-right"></i></a>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Product Section End -->

    <!-- Footer Section Begin -->
    <footer class="footer spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-3 col-md-6 col-sm-6">
                    <div class="footer__about">
                        <div class="footer__about__logo">
                            <a href="./Index"><img src="img/logo_web.jpg" alt=""></a>
                        </div>
                        <ul class="mt-5" id="about">
                            <li><font size = "4"><i  class="fa fa-home"></i></font> Địa chỉ: Khu phố 6, P. Linh Trung, TP. Thủ Đức, TP. HCM</li>
                            <li><i class="fa fa-phone"></i> SĐT: +84 987654321</li>
                            <li><i class="fa fa-send-o"></i> Mail: tiembanhhanhphuc@gmail.com</li>
                        </ul>
                    </div>
                </div>
                <div class="col-lg-4 col-md-6 col-sm-6 offset-lg-1">
                    <div class="footer__widget">
                        <h6>Giờ mở cửa</h6>
                        <ul>
                            <li><a href="#">T2 - T6</a></li>
                            <li><a href="#">T7 & CN</a></li>
                        </ul>
                        <ul>
                            <li><a href="#">08:00 sáng - 22:00 tối</a></li>
                            <li><a href="#">13:00 chiều - 18:00 tối</a></li>
                        </ul><br><br>
                        <p class="cake-footer"><h5 class="freecake text-animation">Thưởng thức bánh miễn phí</h5><a class ="fontsize-14">Thứ sáu hàng tuần tại cửa hàng vào lúc 14h00-14h30</a></p>
                        <img src = "img/footer/img-footer.jpeg">                     
                    </div>
                </div>
                <div class="col-lg-4 col-md-12">
                    <div class="footer__widget">
                        <h6>Theo dõi bảng tin của chúng tôi</h6>
                        <p>Nhận thông tin cập nhật e-mail về sản phẩm mới nhất và các ưu đãi đặc biệt của chúng tôi.</p>
                        <form action="#">
                            <input type="text" placeholder="Địa chỉ email của bạn...">
                            <button type="submit" class="site-btn">Đăng ký</button>
                        </form>
                        <div class="footer__widget__social">
                            <a href="https://www.facebook.com/mai.thuan.52438/"><i class="fa fa-facebook"></i></a>
            <a href="https://www.messenger.com/t/100017755062615"><i class="fa fa-comment"></i></a>
            <a href="https://www.instagram.com/maizecorn1542/"><i class="fa fa-instagram"></i></a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <div class="footer__copyright">
                        <div class="footer__copyright__text"><p><!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
                            Bản quyền &copy;<script>document.write(new Date().getFullYear());</script> thuộc Nhóm 27 | Lập trình web thầy Phan Đình Long <i class="fa fa-heart" aria-hidden="true"> </i> Khoa CNTT - ĐH Nông Lâm TP HCM</a>
                            <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. --></p></div>
                        <div class="footer__copyright__payment"></div>
                    </div>
                </div>
            </div>
        </div>
    </footer>
    <!-- Footer Section End -->
    <!-- back to top btn -->
    <a href="#" class="btn-to-top back-to-top"><i class="fa fa-angle-double-up"></i></a>
    <!-- Js Plugins -->
    <script src="js/jquery-3.3.1.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/jquery.nice-select.min.js"></script>
    <script src="js/jquery-ui.min.js"></script>
    <script src="js/jquery.slicknav.js"></script>
    <script src="js/mixitup.min.js"></script>
    <script src="js/owl.carousel.min.js"></script>
    <script src="js/main.js"></script>



</body>

</html>