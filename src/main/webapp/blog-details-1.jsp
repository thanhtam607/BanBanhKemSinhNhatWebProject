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
    <title>Shop Bánh Kem | Blog</title>

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
        <a href="#"><img src="./img/logo_web.jpg" alt="" class="header__logo_img"></a>
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
            <li><a href="about.jsp">Giới thiệu</a></li>
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
                            <li><i class="fa fa-envelope"></i> tiembanhhanhphuc@gmail.com</li>
                            <li>Miễn phí giao hàng nội thành TP HCM</li>
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
                    <a href="index.jsp"><img src="img/logo_web.jpg" alt="" class="header__logo_img"></a>
                </div>
            </div>
            <div class="col-lg-7">
                <nav class="header__menu">
                    <ul>
                        <li><a href="index.jsp">Trang chủ</a></li>
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
    
    </div>
</section>
<!-- Hero Section End -->

    <!-- Breadcrumb Section Begin -->
    <section class="breadcrumb-section set-bg" data-setbg="./img/banner/breadcrumb.jpg">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <div class="breadcrumb__text">
                        <h2>Giải mã sức hút của bánh kem hiện đại</h2>
                        <div class="breadcrumb__option">
                            <a href="index.jsp">Trang chủ</a>
                            <a href="./ListBlog">Tin tức</a>
                            <span>Giải mã sức hút của bánh kem hiện đại</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Breadcrumb Section End -->

    <!-- Blog Details Section Begin -->
    <section class="blog-details spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-4 col-md-5 order-md-1 order-2">
                    <div class="blog__sidebar">
                        <div class="blog__sidebar__search">
                            <form action="#">
                                <input type="text" placeholder="Tìm kiếm...">
                                <button type="submit"><span class="icon_search"></span></button>
                            </form>
                        </div>
                        <div class="blog__sidebar__item">
                            <h4>Danh mục</h4>
                            <ul>
                                <li><a href="./ListBlog">Tất cả</a></li>
                                <li><a href="./ListBlog">Đời sống(3)</a></li>
                                <li><a href="blog-details-3.jsp">Kỉ niệm(1)</a></li>
                            </ul>
                        </div>
                        <div class="blog__sidebar__item">
                            <h4>Tin mới nhất</h4>
                            <div class="blog__sidebar__recent">
                                <a href="#" class="blog__sidebar__recent__item">
                                    <div class="blog__sidebar__recent__item__pic">
                                        <img src="img/blog/sidebar/blog1.png" alt="">
                                    </div>
                                    <div class="blog__sidebar__recent__item__text">
                                        <h6>Giải mã sức hút<br />của bánh kem hiện đại</h6>
                                        <span>22-10-2022</span>
                                    </div>
                                </a>
                                <a href="blog-details-2.jsp" class="blog__sidebar__recent__item">
                                    <div class="blog__sidebar__recent__item__pic">
                                        <img src="img/blog/sidebar/blog2.jpg" alt="">
                                    </div>
                                    <div class="blog__sidebar__recent__item__text">
                                        <h6>Tổng hợp cách bảo quản <br />bánh kem cực đơn giản</h6>
                                        <span>22-10-2022</span>
                                    </div>
                                </a>
                                <a href="blog-details-3.jsp" class="blog__sidebar__recent__item">
                                    <div class="blog__sidebar__recent__item__pic">
                                        <img src="img/blog/sidebar/blog3.jpg" alt="">
                                    </div>
                                    <div class="blog__sidebar__recent__item__text">
                                        <h6>Chọn bánh kem cho những <br />ngày kỷ niệm ý nghĩa</h6>
                                        <span>22-10-2022</span>
                                    </div>
                                </a>
                            </div>
                        </div>
                        <div class="blog__sidebar__item">
                            <h4>Chủ đề</h4>
                            <div class="blog__sidebar__item__tags">
                                <a href="./ListBlog">Bánh kem</a>
                                <a href="./blog-details-1.jsp">Hiện đại</a>
                                <a href="blog-details-2.jsp">Bảo quản bánh</a>
                                <a href="blog-details-3.jsp">Chọn bánh</a>
                                <a href="blog-details-4.jsp">Ý nghĩa bánh</a>
                            </div>
                            <div class="blog__sidebar__item__tags">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-8 col-md-7 order-md-1 order-1">
                    <div class="blog__details__text">
                        <h3 class="text-center cochu-blog">GIẢI MÃ SỨC HÚT CỦA BÁNH KEM HIỆN ĐẠI.</h3>
                        <img src="img/blog/details/details1.jpg" alt="" class = "image-blog">
                        <h4>Vì sao bánh kem hiện đại được yêu thích đến vậy?</h4>
                        <p class="text-justify">Sở dĩ bánh kem được làm theo phong cách hiện đại được mọi người ưa chuộng vì nhiều lý do. Nhưng phải nhắc đến đó là hương vị bánh thơm ngon, kiểu dáng độc đáo, kích thước và màu sắc đa dạng. Bên cạnh đó phải kể đến những điểm đặc biệt sau đây.</p>
                        <h4>1. Tất cả nguyên liệu đều là tự nhiên.</h4>
                        <p class="text-justify">Kế thừa tinh hoa ẩm thực cũ được sáng tạo, thay đổi phù hợp với xu thế mới của thời đại, chiếc bánh kem còn là món ăn tốt cho sức khỏe. Dưới bàn tay khéo léo của những người thợ làm bánh cho ra đời sản phẩm đẹp và độc đáo. Bánh kem hiện đại sử dụng nguyên liệu tự nhiên không chứa chất độc hại. Màu sắc nhẹ nhàng không sử dụng hóa chất.<br>
                            Sử dụng một số loại quả có màu đậm sẽ tạo nên những màu sắc đẹp mắt. Chẳng hạn để tạo nên màu đỏ có thể dùng quả thanh long hoặc dâu tây. Tùy thuộc vào yêu cầu của bánh kem hãy chọn loại quả phù hợp. Và bạn yên tâm rằng chúng an toàn vì không chứa hóa chất. Đặc biệt hoa quả còn là thực phẩm cần thiết để tăng cường sức khỏe.</p>
                      <span id="bld1"> <h4>2. Tạo hình và trang trí bánh kem sáng tạo, độc đáo.</h4>
                        <p  class="text-justify">Khi nhìn ngắm bánh kem hiện đại chắc chắn bạn sẽ phải ngạc nhiên và ngưỡng mộ tài năng của những người thợ làm bánh. Mỗi mẫu bánh khác nhau về hình dáng, màu sắc và được tạo hình mới mẻ. Bạn có thể thấy sự sáng tạo không có giới hạn khi được chiêm ngưỡng những chiếc bánh kem đó.<br>
                            Đã bao giờ bạn thấy những chiếc bánh được trang trí với rất nhiều bông hoa, bánh in hình ảnh. Hay những chiếc bánh được tạo hình các con vật ngộ nghĩnh, nhân vật hoạt hình vui nhộn. Nếu lần đầu nhìn thấy những mẫu bánh kem như vậy bạn sẽ không nỡ ăn chúng. Bởi vì tạo hình của chúng không thể tự nhiên hơn.</p>
                      <h4>3. Hương vị đặc biệt thơm ngon</h4>
                          <p class="text-justify">Một lý do khiến bánh hiện đại được các tín đồ hảo ngọt yêu thích đó chính là hương vị. Nhiều người nghĩ rằng bánh kem ăn rất nhanh chán vị quá ngọt, quá béo. Thế nhưng bánh kem hiện đại đã hạn chế được những nhược điểm đó. Hương vị thơm ngon được tạo nên từ nguyên liệu tươi ngon, an toàn. Chỉ một lần thưởng thức chúng bạn sẽ nhớ mãi vị bánh.<br>

Với những người thích socola bạn có thể chọn bánh kem hương vị socola, hay vị bắp tốt cho sức khỏe. Các loại trái cây thanh mát kết hợp mang đến những chiếc bánh vẻ ngoài bắt mắt, hương vị độc lạ bên trong.<br>

Hãy đặt mua bánh kem hiện đại để những bữa tiệc trở nên ý nghĩa hơn. Đến với Tiệm bánh Hạnh Phúc quý khách sẽ được chọn lựa một trong số rất nhiều kiểu bánh đẹp. Được sản xuất từ nguyên liệu tươi ngon, không chứa chất bảo quản sẽ khiến bạn hài lòng. Nhấc máy gọi ngay cho chúng tôi theo số Hotline 0987654321 để được phục vụ.</p>
                      </span>
                      <span id="readmore" type="button" class="blog__btn" onclick="myFunction()">Xem thêm</span>
                    <div class="blog__details__content">
                        <div class="row">
                            <div class="col-lg-6">
                                <div class="blog__details__author">
                                    <div class="blog__details__author__pic">
                                        <img src="img/blog/details/github.png" alt="">
                                    </div>
                                    <div class="blog__details__author__text">
                                        <h6>Thành viên nhóm 27 LTW</h6>
                                        <span>Quản lý</span>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-6">
                                <div class="blog__details__widget">
                                    <ul>
                                        <li><span>Danh mục:</span> Đời sống</li>
                                        <li><span>Chủ đề:</span> Hiện đại, Bánh kem</li>
                                    </ul>
                                    <div class="blog__details__social">
                                        <a href="https://www.facebook.com/mai.thuan.52438/"><i class="fa fa-facebook"></i></a>
                                        <a href="https://www.messenger.com/t/100017755062615"><i class="fa fa-comment"></i></a>
                                        <a href="https://www.instagram.com/maizecorn1542/"><i class="fa fa-instagram"></i></a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        </div>
    </section>
    <!-- Blog Details Section End -->

    <!-- Related Blog Section Begin -->
                <section class="from-blog spad">
                    <div class="container">
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="section-title from-blog__title">
                                    <h2>Bảng tin</h2>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-4 col-md-4 col-sm-6">
                                <div class="blog__item">
                                    <div class="blog__item__pic">
                                        <img src="img/blog/blog1.jpg" alt="">
                                    </div>
                                    <div class="blog__item__text">
                                        <ul>
                                            <li><i class="fa fa-calendar-o"></i> 4/5/2022</li>
                                            <li><i class="fa fa-comment-o"></i> 5</li>
                                        </ul>
                                        <h5><a href="blog-details-1.jsp">Giải mã sức hút của bánh kem hiện đại</a></h5>
                                        <p class="text-justify">Trong những bữa tiệc quan trọng bánh kem góp phần mang đến cho không khí thêm phần hoàn hảo. Sự xuất hiện của bánh kem hiện đại như một bước ngoặt mới...  </p>
                                        <a href="blog-details-1.jsp" class="blog__btn">Xem thêm <span class="arrow_right"></span> </a>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-4 col-md-4 col-sm-6">
                                <div class="blog__item">
                                    <div class="blog__item__pic">
                                        <img src="img/blog/blog2.jpg" alt="">
                                    </div>
                                    <div class="blog__item__text">
                                        <ul>
                                            <li><i class="fa fa-calendar-o"></i> 31/7/2022</li>
                                            <li><i class="fa fa-comment-o"></i> 6</li>
                                        </ul>
                                        <h5><a href="blog-details-2.jsp">Tổng hợp cách bảo quản bánh kem cực đơn giản</a></h5>
                                        <p class="text-justify">Các loại bánh kem khi không dùng hết hoặc chưa dùng đến bạn đều phải bảo quản đúng cách, để giúp bánh giữ nguyên hương vị. Cùng tìm hiểu cách bảo quản bánh kem cực đơn giản... </p>
                                        <a href="blog-details-2.jsp" class="blog__btn">Xem thêm <span class="arrow_right"></span> </a>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-4 col-md-4 col-sm-6">
                                <div class="blog__item">
                                    <div class="blog__item__pic">
                                        <img src="img/blog/blog3.jpg" alt="">
                                    </div>
                                    <div class="blog__item__text">
                                        <ul>
                                            <li><i class="fa fa-calendar-o"></i>12/10/2022</li>
                                            <li><i class="fa fa-comment-o"></i> 4</li>
                                        </ul>
                                        <h5><a href="blog-details-3.jsp">Chọn bánh kem cho những ngày kỷ niệm ý nghĩa</a></h5>
                                        <p class="text-justify">Ngày nay, bánh kem không chỉ xuất hiện vào duy nhất sinh nhật mà bất cứ dịp kỷ niệm nào cũng có thể sử dụng. Cùng tìm hiểu các dịp kỷ niệm ý nghĩa nên sử dụng bánh kem và cách chọn sao cho phù hợp nhất...</p>
                                        <a href="blog-details-3.jsp" class="blog__btn">Xem thêm <span class="arrow_right"></span> </a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>
    <!-- Related Blog Section End -->

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
                                    </ul>
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
