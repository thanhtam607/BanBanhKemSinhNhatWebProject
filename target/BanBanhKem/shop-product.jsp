
<%@ page import="java.util.List" %>
<%@ page import="vn.edu.hcmuaf.fit.bean.User" %>
<%@ page import="vn.edu.hcmuaf.fit.service.ProductService" %>
<%@ page import="com.mysql.cj.Session" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="vn.edu.hcmuaf.fit.model.*" %>

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
        <a href="#"><img src="img/logo_web.jpg" alt=""></a>
    </div>
    <div class="humberger__menu__cart">
        <ul>
            <% FavoriteProduct listFavorite = (FavoriteProduct) session.getAttribute("listFavorite");%>
            <%if(auth==null){%>
            <li><a onclick="notLogged()"><i class="fa fa-heart"></i> <span ><%=listFavorite != null ? listFavorite.totalProduct() : "0"%></span></a></li>
            <% }else {%>
            <li><a href="Favorite"><i class="fa fa-heart"></i> <span id="totalFav1"><%=listFavorite != null ? listFavorite.totalProduct() : "0"%></span></a></li>

            <%}%><%Order order = (Order) session.getAttribute("order");%>
            <li><a href="shoping-cart.jsp" ><i class="fa fa-shopping-bag"></i> <span id="totalPro"><%= order != null ? order.totalProduct():"0"%></span></a></li>
        </ul>
    </div>
    <div class="humberger__menu__widget">
        <div class="header__top__right__auth">
            <a href="<%=auth == null ?"signin.jsp":"#"%>"><i class="fa fa-user"></i></i><%= auth != null ? auth.getTentk():"Đăng nhập"%></a>
            <% if(auth != null) { %>
            <div class="header__top__right__auth__dropdown">
                <a onclick="checkPass('<%=auth.getEmail()%>','<%=auth.getPass()%>')" class="dropdown-item">Đặt lại mật khẩu</a>
                <% if(auth.checkRole()) { %>
                <a href="admin/Admin" class="dropdown-item">Vào trang quản lí</a>
                <%}%>
                <a href="doSignOut" method="get" class="dropdown-item">Đăng xuất</a>
            </div>
            <%}%>
        </div>
    </div>
    <nav class="humberger__menu__nav mobile-menu">
        <ul>
            <li class=""><a href="Index">Trang chủ</a></li>
            <li class=""><a href="about.jsp">Giới thiệu</a></li>
            <li class="active"><a href="ListProduct">Sản phẩm</a></li>
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
            <li><i class="fa fa-envelope"></i> tiembanhhanhphuc@gmail.com</li>
            <li>Miễn phí giao hàng nội thành TP HCM</li>
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
                    <a href="Index"><img src="./img/logo_web.jpg" alt="" class="header__logo_img"></a>
                </div>
            </div>
            <div class="col-lg-7 ">
                <nav class="header__menu">
                    <ul>
                        <li class=""><a href="Index">Trang chủ</a></li>
                        <li class=""><a href="about.jsp">Giới thiệu</a></li>
                        <li class="active"><a href="ListProduct">Sản phẩm</a></li>
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
                        <li><a href="Favorite"><i class="fa fa-heart"></i> <span id="totalFav"><%=listFavorite != null ? listFavorite.totalProduct() : "0"%></span></a></li>

                        <%}%> <li><a href="shoping-cart.jsp"><i class="fa fa-shopping-bag"></i> <span id="totalPro1"><%= order!=null ? order.totalProduct():"0"%></span></a></li>
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
                                <li class="text__loaibanh"><a class="text__loaibanh__active" href="/ListProduct">Tất cả</a></li>
                                <%List<TypeOfCake> ListType = ProductService.getListType();
                                    for(TypeOfCake lb: ListType){%>
                                <li class="text__loaibanh"><a href="ProductFilter?title=<%=lb.getName()%>&filter=<%=lb.getName()%>"><%=lb.getName()%></a></li>
                                <%}%>
                            </ul>
                        </div>
                        <div class="sidebar__item">
                            <h4>Giá (VNĐ)</h4>
                            <div class="price-range-wrap">
                                <div class="price-range ui-slider ui-corner-all ui-slider-horizontal ui-widget ui-widget-content"
                                    data-min="50000" data-max="1000000">
                                    <div class="ui-slider-range ui-corner-all ui-widget-header"></div>
<%--                                    <span tab./Index="0" class="ui-slider-handle ui-corner-all vnd-state-default"></span>--%>
<%--                                    <span tab./Index="0" class="ui-slider-handle ui-corner-all vnd-state-default"></span>--%>
                                </div>
                                <div class="range-slider">
                                    <div class="price-input">
                                        <input type="text" id="minamount">
                                        <input type="text" id="maxamount">
                                        <div class="sidebar__item__size">
                                            <label class="filterprice">
                                                <span type="button" class="blog__btn" onclick="FilterbyPrice()">Lọc</span>
                                            </label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="sidebar__item">
                            <h4>Kích thước</h4>
                            <div class="sidebar__item__size">
                                <label for="large">
                                    <a href="ProductFilter?title=Sản phẩm có kích thước lớn &filter=Lớn" id="large">Lớn</a>
                                </label>
                            </div>
                            <div class="sidebar__item__size">
                                <label  for="medium">
                                    <a href="ProductFilter?title=Sản phẩm có kích thước vừa &filter=Vừa" id="medium">Vừa</a>
                                </label>
                            </div>
                            <div  class="sidebar__item__size">
                                <label for="small">
                                    <a href="ProductFilter?title=Sản phẩm có kích thước nhỏ &filter=Nhỏ" id="small">Nhỏ</a>
                                </label>
                            </div>
                            <div class="sidebar__item__size">
                                <label for="tiny">
                                    <a href="ProductFilter?title=Sản phẩm có kích thước bé &filter=Bé" id="tiny">Bé</a>
                                </label>
                            </div>
                        </div>
                        <div class="sidebar__item">
                            <div class="latest-product__text">
                                <h4>Top Bán Chạy</h4>
                                <div class="latest-product__slider owl-carousel">
                                    <% List<Product> listhotproducts = (List<Product>) request.getAttribute("listBanChay"); %>
                                    <div class="latest-prdouct__slider__item">
                                        <% Product productL;
                                            for(int i = 0; i<3;i++){
                                            productL = listhotproducts.get(i);%>
                                        <a href="ProductDetail?id=<%=productL.getId() %>" class="latest-product__item">
                                            <div class="latest-product__item__pic">
                                                    <%for(Image img :productL.getListImg()){
                                                            if(!img.delete()&& !img.isHide()){%>
                                                                <img src="<%=img.getImg()%>" alt="">
                                                    <% break;
                                                    }
                                                    }%>

                                            </div>
                                            <div class="latest-product__item__text">
                                                <h6><%=productL.getName()%></h6>
                                                <span><%=productL.formatNum(productL.getPrice())%> VND</span>
                                            </div>
                                        </a>
                                        <% } %>
                                    </div>
                                    </a>
                                    <div class="latest-prdouct__slider__item">
                                        <% Product productR;
                                            for(int i = 3; i<6;i++){
                                            productR = listhotproducts.get(i);%>
                                        <a href="ProductDetail?id=<%=productR.getId() %>" class="latest-product__item">
                                            <div class="latest-product__item__pic">
                                                <%for(Image img :productR.getListImg()){
                                                    if(!img.delete()&& !img.isHide()){%>
                                                <img src="<%=img.getImg()%>" alt="">
                                                <% break;
                                                }
                                                }%>
                                            </div>
                                            <div class="latest-product__item__text">
                                                <h6><%=productR.getName()%></h6>
                                                <span><%=productR.formatNum(productR.getPrice())%> VND</span>
                                            </div>
                                        </a>
                                        <% } %>
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
                            <% List<ProductDiscount> listdiscount = (List<ProductDiscount>) request.getAttribute("listdiscount");%>
                            <div class="product__discount__slider owl-carousel">
                                <% int price=0;
                                    int discount=0;
                                    for(ProductDiscount pd : listdiscount){
                                        price = (int) (pd.getPrice() - pd.getPrice()*pd.getDiscount());
                                        discount = (int) (pd.getDiscount()*100);%>
                                <div class="col-lg-4">
                                    <div class="product__discount__item">
                                        <div class="product__discount__item__pic set-bg"
<%--                                            data-setbg="<%=pd.getListImg().get(0).getImg()%>">--%>
                                            <div class="product__discount__percent">-<%=discount%>%</div>
                                            <ul class="product__item__pic__hover">
                                                <%if(auth==null){%>
                                        <li><a onclick="notLogged()"><i class="fa fa-heart"></i></a></li>
                                        <li><a onclick="notLogged()"><i class="fa fa-shopping-cart"></i></a></li>
                                       <% } else{ %>
                                        <li><a onclick="addToFav('<%=pd.getId()%>')"><i class="fa fa-heart"></i></a></li>
                                        <li><a onclick="addToCartI('<%=pd.getId()%>')"><i class="fa fa-shopping-cart"></i></a></li>
                                   <%}%>
                                            </ul>
                                        </div>
                                        <div class="product__discount__item__text">
                                            <span><%=pd.getType()%></span>
                                            <h5><a href="ProductDetail?id=<%=pd.getId()%>"><%=pd.getName()%></a></h5>
                                            <div class="product__item__price"><%=pd.formatNum(price)%> VND<span><%=pd.formatNum(pd.getPrice())%> VND</span></div>
                                        </div>
                                    </div>
                                </div>
                            <% } %>
                            </div>
                        </div>
                    </div>
                    <form class="filter__item" >
                        <div class="row">
                            <div class="col-lg-4 col-md-5">
                                <div class="filter__sort">
                                    <span>Sắp Xếp</span>
                                    <select id="sortValue" name="sortValue" >
                                        <%List<String> listOption = new ArrayList<String>();
                                        listOption.add("Mặc định");
                                        listOption.add("Giá từ thấp đến cao");
                                        listOption.add("Giá từ cao đến thấp");
                                        String val;
                                        for(String s: listOption){
                                            val = request.getParameter("sortValue");
                                        if(s.equals(val)){%>
                                            <option href="ListProduct?sortValue=<%=val%>" selected="true" value="<%=val%>"><%=val%></option>
                                        <%}
                                        else{%>
                                        <option href="ListProduct?sortValue=<%=s%>" value="<%=s%>"><%=s%></option>
                                        <%}%>
                                        <%}%>
                                    </select>
                                </div>
                            </div>
                            <div class="col-lg-4 col-md-4">
                                <div class="filter__found">
                                    <% List<Product> list = (List<Product>) request.getAttribute("list"); %>
                                    <h6><span><%=list.size()%></span> Sản phẩm</h6>
                                </div>
                            </div>
                            <div class="col-lg-4 col-md-3">
                                <div class="filter__option">
                                    <input  class="btn" type="submit" value="Lọc">
<%--                                    <span class="icon_grid-2x2"></span>--%>
                                    <!-- <span class="icon_ul"></span> -->
                                </div>
                            </div>

                        </div>
                    </form>

                    <div class="row">
                        <% for(Product p: list){ %>
                        <div class="col-lg-4 col-md-6 col-sm-6">
                            <div class="product__item">
                                <%for(Image img :p.getListImg()){
                                    if(!img.delete()&& !img.isHide()){%>
                                <div class="product__item__pic set-bg" data-setbg="<%=img.getImg()%>">
                                <% break;
                                }
                                }%>
                                    <ul class="product__item__pic__hover">
                                        <%if(auth==null){%>
                                        <li><a onclick="notLogged()"><i class="fa fa-heart"></i></a></li>
                                        <li><a onclick="notLogged()"><i class="fa fa-shopping-cart"></i></a></li>
                                       <% } else{ %>
                                        <li><a onclick="addToFav('<%=p.getId()%>')"><i class="fa fa-heart"></i></a></li>
                                        <li><a onclick="addToCartI('<%=p.getId()%>')"><i class="fa fa-shopping-cart"></i></a></li>
                                   <%}%>
                                    </ul>
                                </div>
                                <div class="product__item__text">
                                    <h6><a href="ProductDetail?id=<%=p.getId()%>"><%= p.getName()%></a></h6>
                                    <h5><%= p.formatNum(p.getPrice())%> VND</h5>
                                </div>
                            </div>
                        </div>
                        <% } %>
                    </div>
                    <div class="product__pagination">
                        <% int tag = (int) request.getAttribute("tag");
                            int endPage = (int) request.getAttribute("endPage");
                            for(int i = 1; i <= endPage ; i++){ %>
                        <a class="<%=tag == i?"product__pagination__page2":""%>" href="ListProduct?sortValue=<%= request.getParameter("sortValue")%>&page=<%=i%>"><%=i%></a>
                        <%}%>
<%--                        <a href="#"><i class="fa fa-long-arrow-right"></i></a>--%>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Product Section End -->

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