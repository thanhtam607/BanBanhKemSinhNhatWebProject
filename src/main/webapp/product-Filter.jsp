<%@ page import="java.util.List" %>
<%@ page import="vn.edu.hcmuaf.fit.bean.User" %>
<%@ page import="vn.edu.hcmuaf.fit.service.ProductService" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="vn.edu.hcmuaf.fit.model.*" %>
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
    <title> <%=InforService.getInformation("NameShop").get(0).getContent()%> | Sản phẩm</title>

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
    boolean userNeedsKey = (boolean) session.getAttribute("userNeedsKey");
    if(!userNeedsKey){
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
<%} session.setAttribute("userNeedsKey", true);%>
    <!-- Page Preloder -->
    <div id="preloder">
        <div class="loader"></div>
    </div>

    <!-- Humberger Begin -->
    <div class="humberger__menu__overlay"></div>
    <div class="humberger__menu__wrapper">
        <div class="humberger__menu__logo">
            <a href="#"><img src="<%=InforService.getImgLogo().get(0).getContent()%>" alt="" class="header__logo_img"></a>
        </div>
        <div class="humberger__menu__cart">
            <ul>
                <% FavoriteProduct listFavorite = (FavoriteProduct) session.getAttribute("listFavorite");%>
                <%if(auth==null){%>
                <li><a onclick="notLogged()"><i class="fa fa-heart"></i> <span ><%=listFavorite != null ? listFavorite.totalProduct() : "0"%></span></a></li>
                <% }else {%>
                <li><a href="Favorite"><i class="fa fa-heart"></i> <span id="totalFav1"><%=listFavorite != null ? listFavorite.totalProduct() : "0"%></span></a></li>

                <%}List<ItemProductInCart>listItemCart = (List<ItemProductInCart>) session.getAttribute("itemCart");%>
                <li><a href="shoping-cart.jsp"><i class="fa fa-shopping-bag"></i> <span id="totalPro"><%= listItemCart != null ? listItemCart.size():"0"%></span></a></li>
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
                <li><a href="Index">Trang chủ</a></li>
                <li><a href="about.jsp">Giới thiệu</a></li>
                <li class="active"><a href="./ListProduct">Sản phẩm</a>
                   
                </li>
                <li><a href="ListBlog">Tin tức</a></li>
                <li><a href="contact.jsp">Liên hệ</a></li>
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
                <li><i class="fa fa-envelope"></i> <%=InforService.getInformation("Email").get(0).getContent()%></li>
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
                        <a href="./Index"><img src="<%=InforService.getImgLogo().get(0).getContent()%>" alt="" class="header__logo_img"></a>
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
                            <%if(auth==null){%>
                            <li><a onclick="notLogged()"><i class="fa fa-heart"></i> <span ><%=listFavorite != null ? listFavorite.totalProduct() : "0"%></span></a></li>
                            <% }else {%>
                            <li><a href="Favorite"><i class="fa fa-heart"></i> <span id="totalFav"><%=listFavorite != null ? listFavorite.totalProduct() : "0"%></span></a></li>

                            <%}%><li><a href="shoping-cart.jsp"><i class="fa fa-shopping-bag"></i> <span id="totalPro1"><%= listItemCart != null ? listItemCart.size():"0"%></span></a></li>
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
                            <form action="ProductFilter" method="get" >
                              
                                <input name="search" id="search" type="text" placeholder="Bạn cần bánh gì nè?">
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
        </div>
    </section>
    <!-- Hero Section End -->

    <!-- Breadcrumb Section Begin -->
<section class="breadcrumb-section set-bg" data-setbg="<%=InforService.getInformation("ImageMenu").get(0).getContent()%>">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <div class="breadcrumb__text">
                        <h2><%=request.getAttribute("title")%></h2>
                        <div class="breadcrumb__option">
                            <a href="./Index">Trang Chủ</a>
                            <a href="./ListProduct">Sản phẩm</a>
                            <span><%=request.getAttribute("title")%></span>
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
                                <li class="text__loaibanh"><a href="./ListProduct">Tất cả</a></li>
                                <%List<TypeOfCake> ListType = ProductService.getListType();
                                    for(TypeOfCake lb: ListType){
                                        if(lb.getName().equals(request.getParameter("filter"))){%>
                                             <li class="text__loaibanh"><a class="text__loaibanh__active"  href="ProductFilter?title=<%=lb.getName()%> &filter=<%=lb.getName()%>"><%=lb.getName()%></a></li>
                                        <%} else{ %>
                                                <li class="text__loaibanh"><a href="ProductFilter?title=<%=lb.getName()%> &filter=<%=lb.getName()%>"><%=lb.getName()%></a></li>
                                <%}}%>

                            </ul>
                        </div>
                        <div class="sidebar__item">
                            <h4>Giá (VND)</h4>
                            <div class="price-range-wrap">
                                <div class="price-range ui-slider ui-corner-all ui-slider-horizontal ui-widget ui-widget-content"
                                    data-min="50000" data-max="1000000">
                                    <div class="ui-slider-range ui-corner-all ui-widget-header"></div>
                                    <span tabindex="0" class="ui-slider-handle ui-corner-all vnd-state-default"></span>
                                    <span tabindex="0" class="ui-slider-handle ui-corner-all vnd-state-default"></span>
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
                            <%for(String size: ProductService.getListSize()){
                                if(size.equals(request.getParameter("filter"))){%>
                                    <div class="sidebar__item__size">
                                        <label class="size__active">
                                            <a href="ProductFilter?title=Sản phẩm có kích thước <%=size%> &filter=<%=size%>" id="<%=size%>"><%=size%></a>
                                        </label>
                                    </div>
                            <% }
                            else{%>
                            <div class="sidebar__item__size">
                                <label for="<%=size%>">
                                    <a href="ProductFilter?title=Sản phẩm có kích thước <%=size%> &filter=<%=size%>" id="<%=size%>"><%=size%></a>
                                </label>
                            </div>
                            <%}}%>
                        </div>
                        <div class="sidebar__item">
                            <div class="latest-product__text">
                                <h4>Top Bán Chạy</h4>
                                <div class="latest-product__slider owl-carousel">
                                    <% List<Product> listhotproducts = (List<Product>) request.getAttribute("listBanChay"); %>
                                    <div class="latest-prdouct__slider__item">
                                        <% for(int i = 0; i<3;i++){
                                            Product productL = listhotproducts.get(i);%>
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
                                                <%if(productL.getPromotional()!=0){%>
                                                <span><%=productL.formatNum(productL.getPromotional())%> VND</span>
                                                <%}else{%>
                                                <span><%=productL.formatNum(productL.getPrice())%> VND</span>
                                                <%}%>
                                            </div>
                                        </a>
                                        <% } %>
                                    </div>
                                        </a>
                                    <div class="latest-prdouct__slider__item">
                                        <% for(int i = 3; i<6;i++){
                                            Product productR = listhotproducts.get(i);%>
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
                                                <%if(productR.getPromotional()!=0){%>
                                                <span><%=productR.formatNum(productR.getPromotional())%> VND</span>
                                                <%}else{%>
                                                <span><%=productR.formatNum(productR.getPrice())%> VND</span>
                                                <%}%>
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
                    <div class="product__discount product__discount_page_detail">
                        <% List<Product> listFilter = (List<Product>) request.getAttribute("listFilter");%>
                        <div class="section-title product__discount__title">
                            <h2><%= request.getAttribute("title")%></h2>
                        </div>
                    </div>
                    <div class="filter__item" >
                        <div class="row">
                            <div class="col-lg-4 col-md-5">
                                <div class="filter__sort ">
                                    <span>Sắp Xếp</span>
                                    <select id="sortValue" name="sortValue" >
                                        <%List<String> listOption = new ArrayList<String>();
                                            listOption.add("Mặc định");
                                            listOption.add("Giá từ thấp đến cao");
                                            listOption.add("Giá từ cao đến thấp");
                                            for(String s: listOption){
                                                String val = request.getParameter("sortValue");
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
                                    <h6><span><%=listFilter.size()%></span> Sản phẩm</h6>
                                </div>
                            </div>
                            <div class="col-lg-4 col-md-3">
                                <div class="filter__option">
                                    <button class="btn" type="submit" onclick="sortProduct()">Lọc</button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row" id="list-pro">
                        <%for(Product p: listFilter){%>
                        <div class="col-lg-4 col-md-6 col-sm-6" id = "product-items">
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
                        <% } %>
                    </div>
                    <div class="product__pagination">
                        <% int tag = (int) request.getAttribute("tagPage");
                            int endPage = (int) request.getAttribute("endPageFt");
                            for(int i = 1; i <= endPage ; i++){ %>
                        <a class="<%=tag == i?"product__pagination__page2":""%>" onclick="changeHref(<%=i%>)"><%=i%></a>
                        <%}%>
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
<%session.setAttribute("filter", request.getParameter("filter"));%>
<%session.setAttribute("pricemin", request.getParameter("pricemin"));%>
<%session.setAttribute("pricemax", request.getParameter("pricemax"));%>
<%session.setAttribute("search", request.getParameter("search"));%>
<script>

    function sortProduct(){
        var sort = document.getElementById("sortValue").value ;


        var urlP= "Sort?sortValue=" + sort;
        $.ajax({
            url: urlP,
            type: "POST",
            success: function (response){
               var productS = document.getElementById("list-pro");
                productS.innerHTML = response;
            }
        });
    }


</script>
<script>
    function changeHref(index){
        var sort = document.getElementById("sortValue").value ;
        var key = document.getElementById("search").value;
       var url = "ProductFilter?title=<%=request.getParameter("title")%>&filter=<%=request.getParameter("filter")%>&sortValue="+sort+"&pageName="+index+"&search="+ key+"&pricemin=" + <%=request.getParameter("pricemin")%> +"&pricemax=" + <%=request.getParameter("pricemax")%>;
        location.href=url;
    }
</script>

</body>

</html>