<%@ page import="vn.edu.hcmuaf.fit.bean.User" %>
<%@ page import="vn.edu.hcmuaf.fit.model.Order" %>
<%@ page import="vn.edu.hcmuaf.fit.model.FavoriteProduct" %>
<%@ page import="java.util.Map" %>
<%@ page import="vn.edu.hcmuaf.fit.model.ItemProductInCart" %>
<%@ page import="vn.edu.hcmuaf.fit.model.Customer" %>
<%@ page import="vn.edu.hcmuaf.fit.service.CustomerService" %>
<%@ page import="java.util.List" %>
<%@ page import="vn.edu.hcmuaf.fit.service.CartService" %>
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
    <title>Shop Bánh Kem | Thanh Toán</title>

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
<% User auth = (User) session.getAttribute("auth");
    Customer customer = CustomerService.getCusByIdAcc(auth.getAccount_id());%>
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
            <%List<ItemProductInCart> listItemCart = (List<ItemProductInCart>) session.getAttribute("itemCart");%>
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
                    <a href="Index"><img src="<%=InforService.getImgLogo().get(0).getContent()%>" alt="" class="header__logo_img"></a>
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
                        <li><a href="Favorite"><i class="fa fa-heart"></i> <span id="totalFav1"><%=listFavorite != null ? listFavorite.totalProduct() : "0"%></span></a></li>

                        <%}%>
                        <li><a href="shoping-cart.jsp"><i class="fa fa-shopping-bag"></i> <span id="totalPro1"><%= listItemCart != null ? listItemCart.size():"0"%></span></a></li>
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
      </div>
  </section>
    <!-- Hero Section End -->

    <!-- Breadcrumb Section Begin -->
<section class="breadcrumb-section set-bg" data-setbg="<%=InforService.getInformation("ImageMenu").get(0).getContent()%>">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <div class="breadcrumb__text">
                        <h2>Thanh Toán</h2>
                        <div class="breadcrumb__option">
                            <a href="./Index">Trang Chủ</a>
                            <span>Thanh Toán</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Breadcrumb Section End -->

    <!-- Checkout Section Begin -->
    <section class="checkout spad">
        <div class="container">
            <div class="checkout__form">
                <h4>Thông Tin Thanh Toán</h4>
                <p>Điền đầy đủ thông tin của bạn để theo dõi đơn hàng dễ dàng</p>
                <div>
                    <div class="row">
                        <div class="col-lg-8 col-md-6">
                            <div class="row">
                                <div class="col-lg-6">
                                    <div class="checkout__input">
                                        <p>Họ và tên<span>*</span></p>
                                        <input type="text" id="ten" value="<%=auth.getAccount_name()%>">
                                    </div>
                                </div>
                                <div class="col-lg-6">
                                    <div class="checkout__input">
                                        <p>Điện thoại<span>*</span></p>
                                        <input type="tel" id="phone" value="<%=customer.getSDT()%>">
                                    </div>
                                </div>
                            </div>
                            <div class="checkout__input">
                                <p>Email<span>*</span></p>
                                <input type="email" id="email" value="<%=auth.getAccount_email()%>">
                            </div>
                            <div class="checkout__input">
                                <p>Địa chỉ giao hàng<span>*</span></p>
                                <input type="text" placeholder="Số nhà, xã/phường, quận/huyện, tỉnh/thành phố..."
                                       id="diachi" value="<%=customer.getDIACHI()%>">
                            </div>

                            <%
                            List<ItemProductInCart> listItemC =(List<ItemProductInCart>) session.getAttribute("itemCart");
                            for (ItemProductInCart item : listItemC) {
                            %>
                            <div class="checkout__input">
                                <p style="color: #ff96b7; font-weight: 600"><%=item.getSp().getName()%></p>
                                <input type="text" name="noteD" placeholder="Lời chúc bạn muốn ghi lên bánh">
                            </div>

                            <%}%>

                            <div class="checkout__input">
                                <p>Ghi chú cho cửa hàng<span>*</span></p>
                                <input type="text"
                                    placeholder="Lời nhắn khác" id="ghichu">
                            </div>
                            <div class="col-lg-6 checkout__input__checkbox">
                                <label for="payment3" >
                                    Lấy dụng cụ ăn uống,...
                                    <input type="radio" id="payment3" name="yes" value="Lấy dụng cụ ăn uống,...">
                                    <span class="checkmark"></span>
                                </label>
                            </div>

                        </div>
                        <div class="col-lg-4 col-md-6">
                            <div id="emptyPro" class="checkout__order">
                                <h4>Đơn hàng</h4>
                                <div class="checkout__order__products">Sản Phẩm <span>Tổng</span></div>
                                <ul >
                                    <%for (ItemProductInCart item : listItemC) {%>
                                    <li>
                                        <span style="float: left" class="breaklineNamePro"><%=item.getSp().getName()%></span>
                                        <span ><%=item.formatNum(item.getPrice())%> VND</span>
                                    </li>
                                    <%}%>
                                </ul>
                                <div class="checkout__order__subtotal">Tạm tính <span><%= CartService.formatNum(CartService.totalPrice(listItemC))%> VND</span></div>
                                <div class="checkout__order__total">Tổng <span><%= CartService.formatNum(CartService.totalPrice(listItemC))%> VND</span></div>


                                <div class="checkout__input__checkbox">
                                    <label for="payment" >
                                       Thanh Toán Khi Nhận Hàng
                                        <input type="radio" id="payment" name="isPayment" value="Thanh Toán Khi Nhận Hàng">
                                        <span class="checkmark"></span>
                                    </label>
                                    <label for="payment_onl" >
                                       Thanh Toán Qua Chuyển Khoản
                                        <input type="radio" id="payment_onl" name="isPayment" value="Thanh Toán Qua Chuyển Khoản">
                                        <span class="checkmark"></span>
                                    </label>
                                </div>
                                <button onclick="addOrder()" type="submit" class="site-btn" >ĐẶT HÀNG</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Checkout Section End -->

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