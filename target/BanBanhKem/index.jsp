<%@ page import="vn.edu.hcmuaf.fit.bean.User" %>
<%@ page import="java.util.List" %>
<%@ page import="vn.edu.hcmuaf.fit.service.BlogService" %>
<%@ page import="vn.edu.hcmuaf.fit.service.ProductService" %>
<%@ page import="vn.edu.hcmuaf.fit.model.*" %>
<%@ page import="vn.edu.hcmuaf.fit.service.InforService" %>
<%@ page import="vn.edu.hcmuaf.fit.service.CartService" %>
<!DOCTYPE html>
<%@ page contentType="text/html;charsetUTF-8" language="java" pageEncoding="utf-8"%>
<html lang="xzz">
<meta http-equiv="Content-Type" charset="UTF-8">

<head>

    <title> <%=InforService.getInformation("NameShop").get(0).getContent()%> | Trang chủ</title>

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
    <link rel="icon" href="https://anvientv.com.vn/uploads/upload/1653357850_an-chay-an-banh-kem-duoc-khong.jpg" type="image/x-icon" />
</head>

<body>

    <% User auth = (User) session.getAttribute("auth");
        if(session.getAttribute("userNeedsKey") != null){
            boolean userNeedsKey = (boolean) session.getAttribute("userNeedsKey");
        if(userNeedsKey == false && auth != null){

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
                if (result.isConfirmed) {
                    confirmGenKey('<%=auth.getId()%>', true);
                }
            });
        });
    </script>
    <%}} session.setAttribute("userNeedsKey", true);%>
<!-- Page Preloder -->
<div id="preloder">
    <div class="loader"></div>
</div>
<%if(auth != null){%>
    <div id="myModal" class="modal" onclick="closeModal()">
        <!-- Modal content -->
        <div class="modal-content">
            <h4 style="text-align: center; font-weight: bold">Cung cấp khóa công khai của bạn</h4>
            <div style="display: flex; justify-content: center;">
                <button id="fileButton" onclick="chooseFilePbK()"
                        style="width: 320px; height: 30px; margin-bottom: 20px; margin-top: 20px">Nhấn vào đây để tải file
                    lên
                </button>
            </div>
            <label for="fileInput" class="fileLabel">File:</label>
            <input type="text" id="fileInput" disabled>
            <input style="display: none" type="file" id="file" accept="*" style="display: none;">
            <input type="text" id="filePath" style="border: none" readonly>
            <p style="color: red; display: none" id="errorText">*Nội dung file không chứa khóa công khai, vui lòng thử
                lại*</p>
            <input id="idUser" style="display: none" value="<%= auth.getId() %>"/>
            <input id="publicKey" style="display: none"/>
            <textarea id="keyContent2" rows="10"></textarea>
            <div class="button-container">
                <div class="button-row">
                    <button onclick="goBack2()" class="back-btn"
                            style="width: 30%; margin-top: 10px; background-color: #6e7881">Quay lại
                    </button>
                    <button onclick="AddNewPublicKey()"
                            style="width: 30%; height: 40px; margin-top: 10px; background-color: #ff96b7" type="submit"
                            class="confirm-btn">Xác nhận
                    </button>
                </div>
            </div>
        </div>
    </div>
    <%}%>


<div class="humberger__menu__overlay"></div>
<div class="humberger__menu__wrapper">
    <div class="humberger__menu__logo">
        <a href="#"><img src="<%=InforService.getImgLogo().get(0).getContent()%>" alt=""></a>
    </div>
    <div class="humberger__menu__cart">
        <ul>

            <% FavoriteProduct listFavorite = (FavoriteProduct) session.getAttribute("listFavorite");
            if(auth==null){%>
            <li><a onclick="notLogged()"><i class="fa fa-heart"></i> <span ><%=listFavorite != null ? listFavorite.totalProduct() : "0"%></span></a></li>
               <% }else{ %>
            <li><a href="Favorite"><i class="fa fa-heart"></i> <span id="totalFav"><%=listFavorite != null ? listFavorite.totalProduct() : "0"%></span></a></li>

            <%}%>
            <%List<ItemProductInCart>listItemCart = (List<ItemProductInCart>) session.getAttribute("itemCart");%>
            <li><a href="shoping-cart.jsp"><i class="fa fa-shopping-bag"></i> <span id="totalPro"><%= listItemCart != null ? listItemCart.size():"0"%></span></a></li>
        </ul>
    </div>
    <div class="humberger__menu__widget">
        <div class="header__top__right__auth">
            <a href="<%=auth == null ?"signin.jsp":"#"%>"><i class="fa fa-user"></i></i><%= auth != null ? auth.getName():"Đăng nhập"%></a>
            <% if(auth != null) { %>
            <div class="header__top__right__auth__dropdown">
                <a onclick="checkPass('<%=auth.getEmail()%>', '<%=auth.getPass()%>')" class="dropdown-item">Đặt lại mật khẩu</a>
                <% if(auth.checkRole()) { %>
                <a href="admin/ListReceipt_Admin" class="dropdown-item">Vào trang quản lí của tôi</a>
                <%}%>
<%--                <a href="doSignOut" method="get" class="dropdown-item">Đăng xuất</a>--%>
                <a href="doSignOut" method="get" class="dropdown-item">Đăng xuất</a>
            </div>
            <%}%>
        </div>
    </div>
    <nav class="humberger__menu__nav mobile-menu">
        <ul>
            <li class="active"><a href="./">Trang chủ</a></li>
            <li class=""><a href="about.jsp">Giới thiệu</a></li>
            <li class=""><a href="ListProduct">Sản phẩm</a></li>
            <li class=""><a href="ListBlog">Tin tức</a></li>
            <li class=""><a href="contact.jsp">Liên hệ</a></li>
        </ul>
    </nav>
    <div id="mobile-menu-wrap"></div>
    <div class="header__top__right__social">
        <a href="<%=InforService.getInformation("SocialNetwork").get(0).getContent()%>" target="blank"><i class="fa fa-facebook"></i></a>
        <a href="<%=InforService.getInformation("SocialNetwork").get(1).getContent()%>" target="blank"><i class="fa fa-comment"></i></a>
        <a href="<%=InforService.getInformation("SocialNetwork").get(2).getContent()%>" target="blank"><i class="fa fa-instagram"></i></a>
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
                    <a href="./"><img src="<%=InforService.getImgLogo().get(0).getContent()%>" alt="" class="header__logo_img"></a>
                </div>
            </div>
            <div class="col-lg-7 ">
                <nav class="header__menu">
                    <ul>
                        <li class="active"><a href="./">Trang chủ</a></li>
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
                        <li><a href="shoping-cart.jsp"><i class="fa fa-shopping-bag"></i> <span id="totalPro1"><%= listItemCart != null? listItemCart.size():"0"%></span></a></li>
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
                                <button  type="submit" class="site-btn"><i class="fa fa-search"></i> <span class="text_search">TÌM KIẾM</span></button>
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
<% List <General_information> listInfor =   InforService.getImgSlideShow(); %>
    <div class="container-fluid p-0 mb-5 pb-5">
                <div id="header-carousel" class="carousel slide" data-ride="carousel">
                    <div class="carousel-inner">
                        <div class="carousel-item active">
                            <img class="w-100 rounded" src="<%=listInfor.get(0).getContent()%>" alt="Image">
                            <div class="carousel-caption d-flex flex-column align-items-center justify-content-center distance">
                                <div class="p-3" style="max-width: 900px;">
                                    <h4 class="text-white text-uppercase mb-md-3 slogun_slide">Truyền thống & hiện đại</h4>
                                    <h1 class="display-3 text-white font-weight-bold mb-md-4 slogun_slide"><%=InforService.getInformation("IndexSlogan").get(0).getContent()%></h1>
                                    <a href="./ListProduct" class="btn btn_pink py-md-3 px-md-5 mt-2">Đặt bánh ngay <i class="fa fa-hand-o-down"></i></a>
                                </div>
                            </div>
                        </div>
                        <div class="carousel-item">
                            <img class="w-100 rounded" src="<%=listInfor.get(1).getContent()%>" alt="Image">
                            <div class="carousel-caption d-flex flex-column align-items-center justify-content-center distance">
                                <div class="p-3" style="max-width: 900px;">
                                    <h4 class="text-white text-uppercase mb-md-3 slogun_slide">Truyền thống & hiện đại</h4>
                                    <h1 class="display-3 text-white font-weight-bold mb-md-4 slogun_slide"><%=InforService.getInformation("IndexSlogan").get(1).getContent()%></h1>
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
                    <% List<Product> list1 = ProductService.getHotProduct();
                        for(int i = 0; i<10;i++){
                            Product p = list1.get(i);%>
                    <div class="col-lg-3 col-md-4 col-sm-6 mix traditional lover">
                        <span class="icon-km icon-hot"></span>
                        <div class="featured__item">
                            <%for(Image img :p.getListImg()){
                                if(!img.delete()&& !img.isHide()){%>
                            <div class="featured__item__pic set-bg" data-setbg="<%=img.getImg()%>">
                                <% break;
                                }
                                }%>
                                <ul class="featured__item__pic__hover">
                                    <%if(auth==null){%>
                                    <li><a onclick="notLogged()"><i class="fa fa-heart"></i></a></li>
                                    <li><a onclick="notLogged()"><i class="fa fa-shopping-cart"></i></a></li>
                                    <% } else{ %>
                                    <li><a onclick="addToFav('<%=p.getId()%>')"><i class="fa fa-heart"></i></a></li>
                                    <%if( CartService.findItemCart(auth.getId(), p.getId()) != null && CartService.findItemCart(auth.getId(), p.getId()).getSoLgMua() >=5){%>
                                    <li><a onclick="validateQuantity('<%=p.getName()%>')"><i class="fa fa-shopping-cart"></i></a></li>
                                    <%}else{%>
                                    <li><a onclick="addToCartI('<%=p.getId()%>')"><i class="fa fa-shopping-cart"></i></a></li>

                                    <%}}%>
                                </ul>
                            </div>
                            <div class="featured__item__text">
                                <h6><a href="ProductDetail?id=<%=p.getId() %>"><%=p.getName()%></a></h6>
                                <%if(p.getPromotional()!=0){%>
                                <div class="product__discount__item__text" style="padding-top: 0px">
                                    <div class="product__item__price"><%=p.formatNum(p.getPromotional())%> VND<span><%=p.formatNum(p.getPrice())%> VND</span></div>
                                </div>
                                <%}else{%>
                                <h5><%= p.formatNum(p.getPrice())%> VND</h5>
                                <%}%>
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
                <% List<Product> list = ProductService.getNewProduct(ProductService.getListProduct());
                    for(int i = 0; i<8;i++){
                        Product p1 = list.get(i);%>
                <div class="col-lg-3 col-md-4 col-sm-6 mix traditional lover">
                    <span class="icon-km icon-sale"></span>
                    <div class="featured__item">
                        <%for(Image img :p1.getListImg()){
                            if(!img.delete()&& !img.isHide()){%>
                        <div class="featured__item__pic set-bg" data-setbg="<%=img.getImg()%>">
                            <% break;
                            }
                            }%>

                            <ul class="featured__item__pic__hover">
                                <%if(auth==null){%>
                                <li><a onclick="notLogged()"><i class="fa fa-heart"></i></a></li>
                                <li><a onclick="notLogged()"><i class="fa fa-shopping-cart"></i></a></li>
                                <% } else{ %>
                                <li><a onclick="addToFav('<%=p1.getId()%>')"><i class="fa fa-heart"></i></a></li>
                                <%if( CartService.findItemCart(auth.getId(), p1.getId()) != null && CartService.findItemCart(auth.getId(), p1.getId()).getSoLgMua() >=5){%>
                                <li><a onclick="validateQuantity('<%=p1.getName()%>')"><i class="fa fa-shopping-cart"></i></a></li>
                                <%}else{%>
                                <li><a onclick="addToCartI('<%=p1.getId()%>')"><i class="fa fa-shopping-cart"></i></a></li>

                                <%}}%>
                            </ul>
                        </div>
                        <div class="featured__item__text">
                            <h6><a href="ProductDetail?id=<%=p1.getId() %>"><%=p1.getName()%></a></h6>
                            <%if(p1.getPromotional()!=0){%>
                            <div class="product__discount__item__text" style="padding-top: 0px">
                                <div class="product__item__price"><%=p1.formatNum(p1.getPromotional())%> VND<span><%=p1.formatNum(p1.getPrice())%> VND</span></div>
                            </div>
                            <%}else{%>
                            <h5><%= p1.formatNum(p1.getPrice())%> VND</h5>
                            <%}%>
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
                <% List<Blog> list2 = BlogService.getData();
                    for(int i = 0; i<3;i++){
                        Blog b2 = list2.get(i);
                        String[] rs = b2.getCont().split("\\n");
                if(b2.getStatus() == 1){ %>
                <div class="col-lg-4 col-md-4 col-sm-6" style = "display: none">
                    <div class="blog__item">
                        <div class="blog__item__pic">
                            <img src="<%=b2.getImg()%>" alt="">
                        </div>
                        <div class="blog__item__text">
                            <ul>
                                <li><i class="fa fa-calendar-o"></i><%=b2.getDate()%></li>
                            </ul>
                            <h5><a href="BlogDetails?id=<%=b2.getId()%>"><%=b2.getTitle()%></a></h5>
                            <p class="text-justify"></p>
                            <a href="BlogDetails?id=<%=b2.getId()%>" class="blog__btn">Xem thêm <span class="arrow_right"></span> </a>
                        </div>
                    </div>
                </div>
                <%} else {%>
                <div class="col-lg-4 col-md-4 col-sm-6">
                    <div class="blog__item">
                        <div class="blog__item__pic">
                            <img src="<%=b2.getImg()%>" alt="">
                        </div>
                        <div class="blog__item__text">
                            <ul>
                                <li><i class="fa fa-calendar-o"></i><%=b2.getDate()%></li>
                            </ul>
                            <h5><a href="BlogDetails?id=<%=b2.getId()%>"><%=b2.getTitle()%></a></h5>
                            <% if(rs[0].length() < 60) {%>
                            <h6 class="text-justify text-bolder"><%=rs[0]%></h6>
                            <p class="text-justify"><%=rs[2]%></p>
                            <% } else {%>
                            <p class="text-justify"><%=rs[0]%></p>
                            <% } %>
                            <a href="BlogDetails?id=<%=b2.getId()%>" class="blog__btn">Xem thêm <span class="arrow_right"></span> </a>
                        </div>
                    </div>
                </div>
                <% } } %>
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
</body>
</html>