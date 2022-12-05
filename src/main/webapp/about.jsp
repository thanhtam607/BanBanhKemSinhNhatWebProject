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
                <a href="admin/signin.jsp"><i class="fa fa-user"></i> Đăng nhập</a>
            </div>
        </div>
        <nav class="humberger__menu__nav mobile-menu">
            <ul>
                <li><a href="index.jsp">Trang chủ</a></li>
                <li class="active"><a href="./about.html">Giới thiệu</a></li>
                <li><a href="./ListProduct">Sản phẩm</a>
                  
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
                                <a href="https://www.facebook.com/mai.thuan.52438/"><i class="fa fa-facebook"></i></a>
                                <a href="https://www.messenger.com/t/100017755062615"><i class="fa fa-comment"></i></a>
                                <a href="https://www.instagram.com/maizecorn1542/"><i class="fa fa-instagram"></i></a>
                            </div>
                            <div class="header__top__right__auth">
                                <a href="admin/signin.jsp"><i class="fa fa-user"></i>Đăng nhập</a>
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
                        <a href="index.jsp"><img src="./img/logo_web.jpg" alt="" class="header__logo_img"></a>
                    </div>
                </div>
                <div class="col-lg-7 ">
                    <nav class="header__menu">
                        <ul>
                            <li ><a href="index.jsp">Trang chủ</a></li>
                            <li class="active"><a href="./about.html">Giới thiệu</a></li>
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
    </section>
    <!-- Hero Section End -->

    <!-- Breadcrumb Section Begin -->
    <section class="breadcrumb-section set-bg" data-setbg="img/banner/breadcrumb.jpg">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <div class="breadcrumb__text">
                        <h2>Giới thiệu</h2>
                        <div class="breadcrumb__option">
                            <a href="index.jsp">Trang chủ</a>
                            <span>Giới thiệu</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Breadcrumb Section End -->

    <!-- about Section Begin -->
    <section class="about spad">
        <div class="container">
            <div class="row">
                <div class="about__text">
                        <h4>1. Chúng tôi là Tiệm bánh hạnh phúc</h4>
                        <p class="text-justify">Được thành lập vào năm 2022 bởi một đội ngũ đầy nhiệt huyết, bánh sinh nhật của tiệm bánh chúng tôi
                             là những chiếc bánh tươi ngon được nướng hàng ngày và chau chuốt tỉ mỉ đến từng chi tiết.
                            Với mong muốn mang đến những chiếc bánh không chỉ ngon miệng mà còn đẹp mắt, 
                            với nguồn nguyên liệu chất lượng cao, 
                            công thức chế biến điều chỉnh phù hợp với thị hiếu, và một vẻ ngoài hợp xu hướng và độc đáo.</p>
                        <img src=" img/about/anh2.jpg">
                        <h4>2. Điểm khác biệt</h4>
                        <p> Với thợ bếp đam mê và giàu kinh nghiệm, chúng tôi cũng đáp ứng những yêu cầu đặc biệt của khách hàng để tạo nên những chiếc bánh đặc biệt cho mỗi sự kiện quan trọng.
                            Sản phẩm của Tiệm bánh hạnh phúc thích hợp cho mọi dịp đặc biệt như sinh nhật, khai trương, kỉ niệm, lễ cầu hôn, tiệc cưới, 
                            lễ đầy tháng, và cả những ngày thường bỗng dưng thèm một chút ngọt ngào và tươi tắn.</p>
                         <img src=" img/about/anh1.jpg">  
                            <h4>3. Trải nghiệm online và tại cửa hàng</h4>  
                        <p class="text-justify"> Website của chúng tôi luôn được cập nhật các mẫu bánh mới nhất và phù hợp với nhiều khách hàng. 
                            Chúng tôi mang đến trải nghiệm mua sắm và chọn bán tuyệt vời dù là bạn đang liên hệ với chúng tôi qua website.</p>

                           <p class="text-justify">  Hiện tại, chúng tôi có cửa hàng tại Khu phố 6, P. Linh Trung, TP. Thủ Đức, TP. HCM. 
                            Chúng tôi nhận đặt hàng trước với các loại bánh sinh nhật, giao trực tiếp tại cửa hàng 
                            hoặc giao hàng tận nơi và một số loại bánh có thể mua trực tiếp.</p>   
                            <p>Hãy ghé thăm cửa hàng hoặc liên hệ Hotline +84 987654321 để chúng tôi tư vấn bạn nhanh nhất nhé!</p> 
                            <p style="color: #ff96b7; font-weight: 600;">Xin trân trọng cảm ơn!</p>
                            <p style="color: rgb(88, 88, 88);">Cảm ơn quý khách đã lựa chọn và đồng hành cùng chúng tôi trong những chặng đường qua!</p>
                        </div>
                </div>
            </div>
        </div>
    </section>
    <!-- About Section End -->

    

    <!-- Footer Section Begin -->
    <footer class="footer spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-3 col-md-6 col-sm-6">
                    <div class="footer__about">
                        <div class="footer__about__logo">
                            <a href="index.jsp"><img src="img/logo_web.jpg" alt=""></a>
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
                            Bản quyền &copy;<script>document.write(new Date().getFullYear());</script> thuộc Nhóm 27 | Lập trình web thầy  Phan Đình Long <i class="fa fa-heart" aria-hidden="true"> </i> Khoa CNTT - ĐH Nông Lâm TP HCM</a>
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