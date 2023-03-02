<%@ page import="vn.edu.hcmuaf.fit.bean.User" %>
<%@ page import="java.util.*" %>
<%@ page import="vn.edu.hcmuaf.fit.model.*" %>
<%@ page import="vn.edu.hcmuaf.fit.service.CartService" %>
<!DOCTYPE html>
<%@ page contentType="text/html;charsetUTF-8" language="java" pageEncoding="utf-8" %>
<html lang="xzz">
<meta http-equiv="Content-Type" charset="UTF-8">

<head>
    <meta charset="UTF-8">
    <meta name="description" content="Ogani Template">
    <meta name="keywords" content="Ogani, unica, creative, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Shop Bánh Kem | Giỏ hàng</title>

    <!-- Google Font -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Dancing+Script:wght@400;500;600;700&family=Roboto:wght@100;300;400;500;700&display=swap"
          rel="stylesheet">
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

            <%}
                List<ItemProductInCart>listItemCart = (List<ItemProductInCart>) session.getAttribute("itemCart");
            int status=0 ;
            if(listItemCart==null){
                status=1;
            }else{
                status=0;
            }%>
            <li><a href="shoping-cart.jsp"><i class="fa fa-shopping-bag"></i> <span id="totalPro"><%= listItemCart != null ? listItemCart.size():"0"%></span></a></li>
        </ul>
    </div>
    <div class="humberger__menu__widget">
        <div class="header__top__right__auth">
            <a href="<%=auth == null ?"signin.jsp":"#"%>"><i class="fa fa-user"></i></i><%= auth != null ? auth.getAccount_name():"Đăng nhập"%></a>
            <% if(auth != null) { %>
            <div class="header__top__right__auth__dropdown">
                <a onclick="checkPass('<%=auth.getAccount_email()%>','<%=auth.getAccount_pass()%>')" class="dropdown-item">Đặt lại mật khẩu</a>
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
                    <h2>Giỏ hàng</h2>
                    <div class="breadcrumb__option">
                        <a href="./Index">Trang chủ</a>

                        <span>Giỏ hàng</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- Breadcrumb Section End -->

<!-- Shoping Cart Section Begin -->
<section class="shoping-cart spad" id="shoping-cart">
    <div class="container" id="container-cart">
        <%if(status==1){%>
        <div class="cartEmpty" name="cartEmpty">
            <img src="./img/null.png" alt="Giỏ hàng của bạn đang trống" class="d-block m-auto" width="250" />
            <p class="text-center font-weight-bold" style="opacity: .6;">Giỏ hàng của bạn trống</p>
        </div>
        <div class="col-lg-12">
            <div class="shoping__cart__btns" style="text-align: center">
                <a href="./ListProduct" class="primary-btn cart-btn" >Xem thêm sản phẩm </a>

            </div>
        </div>
        <%}else {%>
        <div class="row">
            <div class="col-lg-12">
                <div class="shoping__cart__table">
                    <div style="width: 100%; text-align: right; margin-bottom: 20px">
                    <button class="btn" onclick="removeAllCart()" style="background-color: #ff96b7; color: white; font-style: italic; font-weight: 500;">
                        Xóa tất cả
                    </button>
                    </div>
                    <table>
                        <thead>
                        <tr>
                            <th class="shoping__product">Sản phẩm</th>
                            <th>Đơn giá</th>
                            <th>Số lượng</th>
                            <th>Thành tiền</th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>
                        <%for(ItemProductInCart item : CartService.findItemCartByIdUser(auth.getAccount_id())){
                        %>
                            <tr id="<%=item.getSp().getId()%>" class="cart-item">
                                <td  class="shoping__cart__item">
                                    <%for(Image img :item.getSp().getListImg()){
                                        if(!img.delete()&& !img.isHide()){%>
                                    <img src="<%=img.getImg()%>" alt="">
                                    <% break;
                                    }
                                    }%>
                                    <h5><%=item.getSp().getName()%>
                                    </h5>
                                    <input class="idPro" id="idProduct" type="text" value="<%=item.getSp().getId()%>" style="display: none">
                                </td>
                                <td class="shoping__cart__price">
                                    <input id="<%="price"+item.getSp().getId()%>" class="price" type="number" value="<%=item.getSp().getPrice()%>" style="display: none">
                                    <%=item.getSp().formatNum(item.getSp().getPrice())%> VND
                                </td>
                                <td class="shoping__cart__quantity" >
                                    <div class="quantity">
                                        <div class="pro-qty">
                                            <span class="dec qtybtn" onclick="updateCart('<%=item.getSp().getId()%>')">-</span>
                                            <input id="<%="qty"+item.getSp().getId()%>" class="qty" name="solgmua" value="<%=item.getSoLgMua()%>" onchange="updateCart('<%=item.getSp().getId()%>')" >
                                            <span class="inc qtybtn" onclick="updateCart('<%=item.getSp().getId()%>')">+</span>
                                        </div>
                                    </div>
                                </td>
                                <td  class="shoping__cart__total">
                                    <%=item.formatNum(item.giaSanPhamTrongGioHang()) %> VND
                                </td>
                                <td class="shoping__cart__item__close" >
                                    <span ><a onclick="removeCart('<%=item.getSp().getId()%>')" class="remove_prod_style icon_close" ></a></span>
                                </td>
                            </tr>

                        <%}%>

                        </tbody>

                    </table>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-lg-6">
                <div class="shoping__cart__btns">
                    <a href="./ListProduct" class="primary-btn cart-btn">Xem thêm sản phẩm </a>

                </div>
            </div>


            <div class="col-lg-6">
                <div class="shoping__checkout">
                    <h5>Tổng cộng</h5>
                    <hr>
                    <ul>
                        <li>Tổng thanh toán:<span id="totalMoney"><%= 0%> VND</span></li>
                    </ul>
                    <hr>
                    <a href="checkout.jsp" class="primary-btn">THANH TOÁN NGAY</a>
                </div>
            </div>

        </div>
        <%}%>
    </div>
</section>
<!-- Shoping Cart Section End -->

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