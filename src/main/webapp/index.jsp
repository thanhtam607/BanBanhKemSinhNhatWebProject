<%@ page import="vn.edu.hcmuaf.fit.bean.User" %>
<%@ page import="vn.edu.hcmuaf.fit.bean.Blog" %>
<%@ page import="java.util.List" %>
<%@ page import="vn.edu.hcmuaf.fit.service.BlogService" %>
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
                <li><a href="favorites.jsp"><i class="fa fa-heart"></i> <span>1</span></a></li>
                <li><a href="shoping-cart.jsp"><i class="fa fa-shopping-bag"></i> <span>3</span></a></li>
            </ul>
        </div>
        <div class="humberger__menu__widget">
            <div class="header__top__right__auth">
                <a href="signin.jsp"><i class="fa fa-user"></i></i><%= auth != null ? auth.getTentk():"Đăng nhập"%></a>
                <% if(auth != null) { %>
                <div class="header__top__right__auth__dropdown">
                    <a href="/BanBanhKemSinhNhatWebProject/admin/Admin" class="dropdown-item">Vào trang quản lí</a>
                    <a href="./signin.jsp" class="dropdown-item">Đăng xuất</a>
                </div>
                <%}%>
            </div>
        </div>
        <nav class="humberger__menu__nav mobile-menu">
            <ul>
                <li class="active"><a href="./index.jsp">Trang chủ</a></li>
                <li><a href="about.jsp">Giới thiệu</a></li>
                <li><a href="./ListProduct">Sản phẩm</a>
                   
                </li>
                <li><a href="./ListBlog">Tin tức</a></li>
                <li><a href="contact.jsp">Liên hệ</a></li>
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
                                <a href="signin.jsp"><i class="fa fa-user"></i></i><%= auth != null ? auth.getTentk():"Đăng nhập"%></a>

                                <% if(auth != null) { %>
                                <div class="header__top__right__auth__dropdown">
                                    <a href="/BanBanhKemSinhNhatWebProject/admin/Admin" class="dropdown-item">Vào trang quản lí</a>
                                    <a href="./signin.jsp" class="dropdown-item">Đăng xuất</a>
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
                        <a href="./index.jsp"><img src="./img/logo_web.jpg" alt="" class="header__logo_img"></a>
                    </div>
                </div>
                <div class="col-lg-7 ">
                    <nav class="header__menu">
                        <ul>
                            <li class="active"><a href="./index.jsp">Trang chủ</a></li>
                            <li><a href="about.jsp">Giới thiệu</a></li>
                            <li><a href="./ListProduct">Sản phẩm</a>
                             
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
                            <li><a href="shoping-cart.jsp"><i class="fa fa-shopping-bag"></i> <span>3</span></a></li>
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
                    <div class="col-lg-3">
                        <div class="categories__item set-bg" data-setbg="img/product/B006/banh1.jpg">
                            <h5><a href="shop-details.jsp">Bánh hoa hải đường ngũ sắc</a></h5>
                        </div>
                    </div>
                    <div class="col-lg-3">
                        <div class="categories__item set-bg" data-setbg="img/product/B020/banh1.jpg">
                            <h5><a href="#">Bánh hoa tulip</a></h5>
                        </div>
                    </div>
                    <div class="col-lg-3">
                        <div class="categories__item set-bg" data-setbg="img/product/B074/banh1.jpg">
                            <h5><a href="#">Bánh chữ T</a></h5>
                        </div>
                    </div>
                    <div class="col-lg-3">
                        <div class="categories__item set-bg" data-setbg="img/product/B068/banh1.jpg">
                            <h5><a href="shop-details.jsp">Bánh kem socola</a></h5>
                        </div>
                    </div>
                    <div class="col-lg-3">
                        <div class="categories__item set-bg" data-setbg="img/product/B047/banh2.jpg">
                            <h5><a href="shop-details.jsp">Bánh hình con sâu</a></h5>
                        </div>
                    </div>
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
                            <h2>Danh Sách Sản Phẩm</h2>
                        </div>
                        <ul>
                            <li class="active" data-filter="*">Tất cả</li>
                            <li data-filter=".traditional">Truyền thống</li>
                            <li data-filter=".modern">Hiện đại</li>
                            <li data-filter=".lover">Người yêu</li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="row featured__filter">
                <div class="col-lg-3 col-md-4 col-sm-6 mix traditional lover">
                    <div class="featured__item">
                        <div class="featured__item__pic set-bg" data-setbg="img/product/B012/banh1.jpg">
                            <ul class="featured__item__pic__hover">
                                <li><a href="#"><i class="fa fa-heart"></i></a></li>
                                <li><a href="#"><i class="fa fa-retweet"></i></a></li>
                                <li><a href="#"><i class="fa fa-shopping-cart"></i></a></li>
                            </ul>
                        </div>
                        <div class="featured__item__text">
                            <h6><a href="shop-details.jsp">Bánh sinh nhật</a></h6>
                            <h5>300,000 VND</h5>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 col-md-4 col-sm-6 mix traditional">
                    <div class="featured__item">
                        <div class="featured__item__pic set-bg" data-setbg="img/product/B013/banh1.jpg">
                            <ul class="featured__item__pic__hover">
                                <li><a href="#"><i class="fa fa-heart"></i></a></li>
                                <li><a href="#"><i class="fa fa-retweet"></i></a></li>
                                <li><a href="#"><i class="fa fa-shopping-cart"></i></a></li>
                            </ul>
                        </div>
                        <div class="featured__item__text">
                            <h6><a href="shop-details.jsp">Bánh sinh nhật</a></h6>
                            <h5>300,000 VND</h5>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 col-md-4 col-sm-6 mix traditional">
                    <div class="featured__item">
                        <div class="featured__item__pic set-bg" data-setbg="img/product/B014/banh2.jpg">
                            <ul class="featured__item__pic__hover">
                                <li><a href="#"><i class="fa fa-heart"></i></a></li>
                                <li><a href="#"><i class="fa fa-retweet"></i></a></li>
                                <li><a href="#"><i class="fa fa-shopping-cart"></i></a></li>
                            </ul>
                        </div>
                        <div class="featured__item__text">
                            <h6><a href="shop-details.jsp">Bánh sinh nhật</a></h6>
                            <h5>300,000 VND</h5>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 col-md-4 col-sm-6 mix traditional lover">
                    <div class="featured__item">
                        <div class="featured__item__pic set-bg" data-setbg="img/product/B015/banh1.jpg">
                            <ul class="featured__item__pic__hover">
                                <li><a href="#"><i class="fa fa-heart"></i></a></li>
                                <li><a href="#"><i class="fa fa-retweet"></i></a></li>
                                <li><a href="#"><i class="fa fa-shopping-cart"></i></a></li>
                            </ul>
                        </div>
                        <div class="featured__item__text">
                            <h6><a href="shop-details.jsp">Bánh sinh nhật</a></h6>
                            <h5>300,000 VND</h5>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 col-md-4 col-sm-6 mix lover modern">
                    <div class="featured__item">
                        <div class="featured__item__pic set-bg" data-setbg="img/product/B016/banh1.jpg">
                            <ul class="featured__item__pic__hover">
                                <li><a href="#"><i class="fa fa-heart"></i></a></li>
                                <li><a href="#"><i class="fa fa-retweet"></i></a></li>
                                <li><a href="#"><i class="fa fa-shopping-cart"></i></a></li>
                            </ul>
                        </div>
                        <div class="featured__item__text">
                            <h6><a href="shop-details.jsp">Bánh sinh nhật</a></h6>
                            <h5>300,000 VND</h5>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 col-md-4 col-sm-6 mix lover modern">
                    <div class="featured__item">
                        <div class="featured__item__pic set-bg" data-setbg="img/product/B017/banh1.jpg">
                            <ul class="featured__item__pic__hover">
                                <li><a href="#"><i class="fa fa-heart"></i></a></li>
                                <li><a href="#"><i class="fa fa-retweet"></i></a></li>
                                <li><a href="#"><i class="fa fa-shopping-cart"></i></a></li>
                            </ul>
                        </div>
                        <div class="featured__item__text">
                            <h6><a href="shop-details.jsp">Bánh sinh nhật</a></h6>
                            <h5>300,000 VND</h5>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 col-md-4 col-sm-6 mix modern">
                    <div class="featured__item">
                        <div class="featured__item__pic set-bg" data-setbg="img/product/B018/banh1.jpg">
                            <ul class="featured__item__pic__hover">
                                <li><a href="#"><i class="fa fa-heart"></i></a></li>
                                <li><a href="#"><i class="fa fa-retweet"></i></a></li>
                                <li><a href="#"><i class="fa fa-shopping-cart"></i></a></li>
                            </ul>
                        </div>
                        <div class="featured__item__text">
                            <h6><a href="shop-details.jsp">Bánh sinh nhật</a></h6>
                            <h5>300,000 VND</h5>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 col-md-4 col-sm-6 mix modern">
                    <div class="featured__item">
                        <div class="featured__item__pic set-bg" data-setbg="img/product/B019/banh1.jpg">
                            <ul class="featured__item__pic__hover">
                                <li><a href="#"><i class="fa fa-heart"></i></a></li>
                                <li><a href="#"><i class="fa fa-retweet"></i></a></li>
                                <li><a href="#"><i class="fa fa-shopping-cart"></i></a></li>
                            </ul>
                        </div>
                        <div class="featured__item__text">
                            <h6><a href="shop-details.jsp">Bánh sinh nhật</a></h6>
                            <h5>300,000 VND</h5>
                        </div>
                    </div>
                </div>
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
                    list2 = BlogService.getDanhMuc("Đời sống");
                    for(Blog b2: list2){ %>
                <div class="col-lg-4 col-md-4 col-sm-6">
                    <div class="blog__item">
                        <div class="blog__item__pic">
                            <img src="<%=b2.getImg()%>" alt="">
                        </div>
                        <div class="blog__item__text">
                            <ul>
                                <li><i class="fa fa-calendar-o"></i><%=b2.getDate()%></li>
                                <li><i class="fa fa-comment-o"></i> 5</li>
                            </ul>
                            <h5><a href="Blog1?id=<%=b2.getId()%>"><%=b2.getCategory()%></a></h5>
                            <p class="text-justify"><%=b2.getCont()%></p>
                            <a href="Blog1?id=<%=b2.getId()%>" class="blog__btn">Xem thêm <span class="arrow_right"></span> </a>
                        </div>
                    </div>
                </div>
                <%}%>
            </div>
        </div>
    </section>
    <!-- Blog Section End -->

    <!-- Footer Section Begin -->
    <footer class="footer spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-3 col-md-6 col-sm-6">
                    <div class="footer__about">
                        <div class="footer__about__logo">
                            <a href="./index.jsp"><img src="img/logo_web.jpg" alt=""></a>
                        </div>
                        <ul class="mt-5" id="about">
                            <li><i class="fa fa-home"></i> Địa chỉ: Khu phố 6, P. Linh Trung, TP. Thủ Đức, TP. HCM</li>
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