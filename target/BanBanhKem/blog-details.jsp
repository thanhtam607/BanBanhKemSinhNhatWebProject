<%@ page import="vn.edu.hcmuaf.fit.model.Blog" %>
<%@ page import="java.util.List" %>
<%@ page import="vn.edu.hcmuaf.fit.bean.User" %>
<%@ page import="vn.edu.hcmuaf.fit.service.BlogService" %>
<%@ page import="vn.edu.hcmuaf.fit.model.Order" %>
<%@ page import="vn.edu.hcmuaf.fit.model.FavoriteProduct" %>
<%@ page import="vn.edu.hcmuaf.fit.model.ItemProductInCart" %>
<%@ page import="vn.edu.hcmuaf.fit.service.InforService" %>
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
    <title> <%=InforService.getInformation("NameShop").get(0).getContent()%> | Blog</title>

     <!-- Google Font -->
     <link rel="preconnect" href="https://fonts.googleapis.com">
     <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
     <link href="https://fonts.googleapis.com/css2?family=Dancing+Script:wght@400;500;600;700&family=Roboto:wght@100;300;400;500;700&display=swap" rel="stylesheet">
     <link rel="icon" href="img/favicon.ico" type="image/x-icon" />
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

<% User auth = (User) session.getAttribute("auth");
    if(session.getAttribute("userNeedsKey") != null){
    boolean userNeedsKey = (boolean) session.getAttribute("userNeedsKey");
    if(!userNeedsKey auth!= null){
%>
<script>
    // Hiển thị thông báo khi người dùng cần tạo khóa
    document.addEventListener("DOMContentLoaded", function() {
        // Hiển thị thông báo khi người dùng cần tạo khóa
        const Toast = Swal.mixin({
            // toast: true,
            timer: 6000,
            timerProgressBar: true,
            didOpen: (toast) => {
                toast.onmouseenter = Swal.stopTimer;
                toast.onmouseleave = Swal.resumeTimer;
            }
        });
        Toast.fire({
            icon: "info",
            title: "Tài khoản của bạn hiện chưa có khóa bảo mật!",
            text: "Bạn có muốn tạo khóa cho tài khoản của mình?",
            showConfirmButton: true,
            confirmButtonText: 'Tạo khóa',
            confirmButtonColor: '#ff96b7',
            showCancelButton: true,
            cancelButtonText: 'Thoát'
        }).then((result) => {
            if (result.isConfirmed) { confirmGenKey('<%=auth.getId()%>', true);
            }
        });
    });
</script>
<%} }session.setAttribute("userNeedsKey", true);%>
<!-- Page Preloder -->
<div id="preloder">
    <div class="loader"></div>
</div>

<!-- Humberger Begin -->
<div class="humberger__menu__overlay"></div>
<div class="humberger__menu__wrapper">
    <div class="humberger__menu__logo">
        <a href="#"><img src="<%=InforService.getImgLogo().get(0).getContent()%>" alt=""></a>
    </div>
    <div class="humberger__menu__cart">
        <ul>
            <% FavoriteProduct listFavorite = (FavoriteProduct) session.getAttribute("listFavorite");%>
            <%if(auth==null){%>
            <li><a onclick="notLogged()"><i class="fa fa-heart"></i> <span ><%=listFavorite != null ? listFavorite.totalProduct() : "0"%></span></a></li>
            <% }else {%>
            <li><a href="Favorite"><i class="fa fa-heart"></i> <span id="totalFav"><%=listFavorite != null ? listFavorite.totalProduct() : "0"%></span></a></li>

            <%}%>
            <%List<ItemProductInCart>listItemCart = (List<ItemProductInCart>) session.getAttribute("itemCart");%>
            <li><a href="shoping-cart.jsp"><i class="fa fa-shopping-bag"></i> <span><%= listItemCart != null ? listItemCart.size():"0"%></span></a></li>
        </ul>
    </div>
    <div class="humberger__menu__widget">
        <div class="header__top__right__auth">
            <a href="<%=auth == null ?"signin.jsp":"#"%>"><i class="fa fa-user"></i></i><%= auth != null ? auth.getName():"Đăng nhập"%></a>
            <% if(auth != null) { %>
            <div class="header__top__right__auth__dropdown">
                <a onclick="checkPass('<%=auth.getEmail()%>','<%=auth.getPass()%>')" class="dropdown-item">Đặt lại mật khẩu</a>
                <% if(auth.checkRole()) { %>
                <a href="admin/ListReceipt_Admin" class="dropdown-item">Vào trang quản lí</a>
                <%}%>
                <a href="doSignOut" method="get" class="dropdown-item">Đăng xuất</a>
            </div>
            <%}%>
        </div>
    </div>
    <nav class="humberger__menu__nav mobile-menu">
        <ul>
            <li class=""><a href="/">Trang chủ</a></li>
            <li class=""><a href="about.jsp">Giới thiệu</a></li>
            <li class=""><a href="ListProduct">Sản phẩm</a></li>
            <li class=""><a href="ListBlog">Tin tức</a></li>
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
            <li><i class="fa fa-envelope"></i><%=InforService.getInformation("Email").get(0).getContent()%></li>
            <li><%=InforService.getInformation("Delivery").get(0).getContent()%></li>
        </ul>
    </div>
</div>
<!-- Humberger End -->
<!-- Header Section Begin -->
<header class="header">
    <jsp:include page="hearder-top.jsp"></jsp:include>
    <div class="container">
        <div class="row">
            <div class="col-lg-3">
                <div class="header__logo">
                    <a href="/"><img src="<%=InforService.getImgLogo().get(0).getContent()%>" alt="" class="header__logo_img"></a>
                </div>
            </div>
            <div class="col-lg-7 ">
                <nav class="header__menu">
                    <ul>
                        <li class=""><a href="/">Trang chủ</a></li>
                        <li class=""><a href="about.jsp">Giới thiệu</a></li>
                        <li class=""><a href="ListProduct">Sản phẩm</a></li>
                        <li class=""><a href="ListBlog">Tin tức</a></li>
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
                        <li><a href="shoping-cart.jsp"><i class="fa fa-shopping-bag"></i> <span><%= listItemCart != null ? listItemCart.size():"0"%></span></a></li>
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
                            <a href="tel:<%=InforService.getInformation("PhoneNumber").get(0).getContent()%>" class="fa fa-phone cursor"></a>
                        </div>
                        <div class="hero__search__phone__text">
                            <h5><%=InforService.getInformation("PhoneNumber").get(0).getContent()%></h5>
                            <span><%=InforService.getInformation("TimeShop").get(1).getContent()%></span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    
    </div>
</section>
<!-- Hero Section End -->
<% Blog b = (Blog) request.getAttribute("blog");%>
    <!-- Breadcrumb Section Begin -->
<section class="breadcrumb-section set-bg" data-setbg="<%=InforService.getInformation("ImageMenu").get(0).getContent()%>">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <div class="breadcrumb__text">
                        <h2><%=b.getCategory()%></h2>
                        <div class="breadcrumb__option">
                            <a href="./">Trang chủ</a>
                            <a href="./ListBlog">Tin tức</a>
                            <span><%=b.getCategory()%></span>
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
                                <input type="text" placeholder="Tin tức bạn cần tìm?">
                                <button type="submit"><span class="icon_search"></span></button>
                            </form>
                        </div>
                        <div class="blog__sidebar__item">
                            <h4>Danh mục</h4>
                            <ul>
                                <li><a href="./ListBlog">Tất cả</a></li>
                                <%List<String> listDm = BlogService.listcate();
                                    for(String dm : listDm){%>
                                <li><a href="BlogCategory?category=<%=dm%>"><%=dm%></a></li>
                                <% } %>
                            </ul>
                        </div>
                        <div class="blog__sidebar__item">
                            <h4>Tin mới nhất</h4>
                            <%List<Blog> list = (List<Blog>) request.getAttribute("list");
                                for(Blog menublog: list){ %>
                            <div class="blog__sidebar__recent">
                               <% if(menublog.getStatus() == -1 ) { %>
                                <a href="BlogDetails?id=<%=menublog.getId()%>" class="blog__sidebar__recent__item" style="display: none">
                                    <div class="blog__sidebar__recent__item__pic">
                                        <img src="<%=menublog.getImg()%>" alt="">
                                    </div>
                                    <div class="blog__sidebar__recent__item__text">
                                        <h6><%=menublog.getTitle()%></h6>
                                        <span><%=menublog.getDate()%></span>
                                    </div>
                                </a>
                                <% } else { %>
                                <a href="BlogDetails?id=<%=menublog.getId()%>" class="blog__sidebar__recent__item">
                                    <div class="blog__sidebar__recent__item__pic">
                                        <img src="<%=menublog.getImg()%>" alt="">
                                    </div>
                                    <div class="blog__sidebar__recent__item__text">
                                        <h6><%=menublog.getTitle()%></h6>
                                        <span><%=menublog.getDate()%></span>
                                    </div>
                                </a>
                                <%}%>
                            </div>
                            <%}%>
                        </div>
                        <div class="blog__sidebar__item">
                            <h4>Chủ đề</h4>
                            <div class="blog__sidebar__item__tags">
                                <a href="./ListBlog">Bánh kem</a>
                                <%List<String> listCd = BlogService.listss();
                                    for(String cd : listCd){%>
                                <a href="BlogSeason?season=<%=cd%>"><%=cd%></a>
                                <% } %>
                            </div>
                            <div class="blog__sidebar__item__tags">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-8 col-md-7 order-md-1 order-1">
                    <div class="blog__details__text">
                        <h3 class="text-center cochu-blog"><%= b.getTitle()%></h3>
                        <img src="<%=b.getImg()%>" alt="" class = "image-blog">
                        <p class="text-justify"><%=b.getCont()%></p>
<%--                        <span id="bld1">--%>
<%--                         <p class="text-justify"></p>--%>
<%--                       </span>--%>
<%--                      <span id="readmore" type="button" class="blog__btn" onclick="myFunction()">Xem thêm</span>--%>
                    <div class="blog__details__content">
                        <div class="row">
                            <div class="col-lg-6">
                                <div class="blog__details__author">
                                    <div class="blog__details__author__pic">
                                        <img src="img/blog/details/github.png" alt="">
                                    </div>
                                    <div class="blog__details__author__text">
                                        <h6>Thành viên nhóm 17 LTW</h6>
                                        <span>Quản lý</span>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-6">
                                <div class="blog__details__widget">
                                    <ul>
                                        <li><span>Danh mục:</span><%=b.getCategory()%></li>
                                        <li><span>Chủ đề:</span><%=b.getSeason()%> </li>
                                    </ul>
                                    <div class="blog__details__social">
                                        <a href="<%=InforService.getInformation("SocialNetwork").get(0).getContent()%>"><i class="fa fa-facebook"></i></a>
                                        <a href="<%=InforService.getInformation("SocialNetwork").get(1).getContent()%>"><i class="fa fa-comment"></i></a>
                                        <a href="<%=InforService.getInformation("SocialNetwork").get(2).getContent()%>"><i class="fa fa-instagram"></i></a>
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
                            <%List<Blog> listhotblog = (List<Blog>) request.getAttribute("list");
                                for(int i = 0; i<3;i++){
                                    Blog bloghot = listhotblog.get(i);
                                    String[] rs = bloghot.getCont().split("\\n");
                            if(bloghot.getStatus() == 1) { %>
                            <div class="col-lg-4 col-md-4 col-sm-6" style="display: none">
                                <div class="blog__item">
                                    <div class="blog__item__pic">
                                        <img src="<%=bloghot.getImg()%>" alt="">
                                    </div>
                                    <div class="blog__item__text">
                                        <ul>
                                            <li><i class="fa fa-calendar-o"></i><%=bloghot.getDate()%></li>
                                        </ul>
                                        <h5><a href="blog-details.jsp"><%=bloghot.getTitle()%></a></h5>
                                        <% if(rs[0].length() < 60) {%>
                                        <h6 class="text-justify text-bolder"><%=rs[0]%></h6>
                                        <p class="text-justify"><%=rs[2]%></p>
                                        <% } else {%>
                                        <p class="text-justify"><%=rs[0]%></p>
                                        <% } %>
                                        <a href="BlogDetails?id=<%=bloghot.getId()%>" class="blog__btn">Xem thêm <span class="arrow_right"></span> </a>
                                    </div>
                                </div>
                            </div>
                            <%} else {%>
                            <div class="col-lg-4 col-md-4 col-sm-6">
                                <div class="blog__item">
                                    <div class="blog__item__pic">
                                        <img src="<%=bloghot.getImg()%>" alt="">
                                    </div>
                                    <div class="blog__item__text">
                                        <ul>
                                            <li><i class="fa fa-calendar-o"></i><%=bloghot.getDate()%></li>
                                        </ul>
                                        <h5><a href="blog-details.jsp"><%=bloghot.getTitle()%></a></h5>
                                        <% if(rs[0].length() < 60) {%>
                                        <h6 class="text-justify text-bolder"><%=rs[0]%></h6>
                                        <p class="text-justify"><%=rs[2]%></p>
                                        <% } else {%>
                                        <p class="text-justify"><%=rs[0]%></p>
                                        <% } %>
                                        <a href="BlogDetails?id=<%=bloghot.getId()%>" class="blog__btn">Xem thêm <span class="arrow_right"></span> </a>
                                    </div>
                                </div>
                            </div>
                            <% } } %>
                        </div>
                    </div>
                </section>
    <!-- Related Blog Section End -->

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
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>


</body>

</html>
