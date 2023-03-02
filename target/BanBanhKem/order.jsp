<%@ page import="vn.edu.hcmuaf.fit.bean.User" %>
<%@ page import="java.util.List" %>
<<<<<<< HEAD
<%@ page import="vn.edu.hcmuaf.fit.service.ReceiptService" %>
<%@ page import="vn.edu.hcmuaf.fit.model.*" %>
=======
<%@ page import="vn.edu.hcmuaf.fit.model.*" %>
<%@ page import="vn.edu.hcmuaf.fit.service.*" %>

>>>>>>> 081561666f644247db31b685438f0cc71aa13b42
<!DOCTYPE html>
<%@ page contentType="text/html;charsetUTF-8" language="java" pageEncoding="utf-8"%>
<html>
<meta http-equiv="Content-Type" charset="UTF-8">

<head>

    <title>Shop Bánh Kem | Đơn hàng</title>

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
            <a href="<%=auth == null ?"signin.jsp":"#"%>"><i class="fa fa-user"></i></i><%= auth != null ? auth.getAccount_name():"Đăng nhập"%></a>
            <% if(auth != null) { %>
            <div class="header__top__right__auth__dropdown">
                <a onclick="checkPass('<%=auth.getAccount_email()%>', '<%=auth.getAccount_pass()%>')" class="dropdown-item">Đặt lại mật khẩu</a>
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
            <li class="active"><a href="Index">Trang chủ</a></li>
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
                        <li class="active"><a href="Index">Trang chủ</a></li>
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
                    <h2>Đơn hàng</h2>
                    <div class="breadcrumb__option">
                        <a href="./Index">Trang Chủ</a>
                        <span>Đơn hàng</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- Breadcrumb Section End -->
<%List<Receipt> receipts = (List<Receipt>) session.getAttribute("listRe");%>
    <div class="container-91 mx-auto">
        <div class="row">
            <div class="tab-content flex-sm-row mt-2">
                <%if(receipts.size() == 0){
                %>
                <div class="cartEmpty" name="cartEmpty">
                    <img src="./img/null.png" alt="Giỏ hàng của bạn đang trống" class="d-block m-auto" width="250" />
                    <p class="text-center font-weight-bold" style="opacity: .6;">Không có đơn hàng nào</p>
                </div>
                <div class="col-lg-12">
                    <div class="shoping__cart__btns" style="text-align: center">
                        <a href="./ListProduct" class="primary-btn cart-btn" >Xem thêm sản phẩm </a>
                    </div>
                </div>
                <%}else{%>
                <%for(Receipt r: receipts){
                %>
                <div class="my-2 center overflow-hidden">
                    <div class="row">
                        <div class="col-6">
                                    <small class="text-secondary d-inline-block pt-3 pl-3">Mã đơn hàng: </small>
                                    <small class="text-secondary d-inline-block pt-3 " style="padding: 0;margin: 0"><%=r.getId()%></small>
                                </div>
                        <div class="col-6 text-right  my-2 pt-2 ">
                            <small class="d-inline text-secondary">Trạng thái |</small>
                            <div id="statusName<%=r.getId()%>" class="d-inline pr-3 text-uppercase" style="color:#ee4d2d; font-size:14px">
                                <span><%=r.getStatusName()%></span>
                            </div>
                        </div>
                    </div>
                    <%List<Bill_Detail> billDetailList = ReceiptService.getcthdUser(r.getId());
                        for(Bill_Detail c: billDetailList){
                        %>
                    <div class="card mb-3 border-left-0 border-right-0 border-bottom-0 mx-3">
                        <div class="row my-3 pb-3 mx-2" style="border-bottom:1px solid #eee">
                            <div class="col-2">
                                <div class="vertical-center">
                                    <%for(Image img : ProductService.findById(c.getMasp()).getListImg()){
                                        if(!img.delete()&& !img.isHide()){%>
                                    <img class="d-block mx-auto" src="<%=img.getImg()%>" width="100">
                                    <% break;
                                    }
                                    }%>
                                </div>
                            </div>
                            <div class="col-7">
                                <div class="card-body my-0">
                                    <div class="card-text text-dark">
                                        <a href="#" class="text-decoration-none text-dark text-uppercase">
                                            <%=c.getNamePro()%>
                                        </a>
                                    </div>
                                    <div class="text-dark" style="font-size:14px">x <%=c.getSolg()%></div>
                                </div>
                            </div>
                            <div class="col-3 mt-3">
                                <small class="text-dark" >
                                    <%=c.formatNum(c.getToTalPrice())%> VND
                                </small>
                            </div>

                        </div>
                    </div>
                    <%}%>

                    <div class="xemthemdiachi card mb-3 border-left-0 border-right-0 border-bottom-0 mx-3">
                        <div class="row my-3 pb-3 mx-2" style="border-bottom:1px solid #eee">
                            <div class="col-12">
                                <div class="card-body my-0">
                                    <div class="card-text text-dark">
                                        <a href="" class="text-decoration-none text-dark text-uppercase">
                                            ĐỊA CHỈ GIAO HÀNG: <%=r.getAddress()%>
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row" style="margin-top: -20px">
                        <div class="col-6 pt-2 pb-3">
                            <div id="<%=r.getId()%>" class="text-left mr-3">
                                <%if(r.checkState()){%>
                                <button onclick="cancelOrder('<%=r.getId()%>')" type="submit" style="border:1px solid #ccc;" class="btn rounded-0 py-2 ml-2" >
                                            Hủy đơn hàng
                                </button>
                                <%}if(r.getStateInt() == 4){%>
                                <button onclick="buyAgain('<%=r.getId()%>')" type="submit" style="border:1px solid #ccc;" class="btn rounded-0 py-2 ml-2" >
                                    Mua lại
                                </button>
                                <%}%>

                            </div>
                        </div>
                        <div class="col-6 my-2">
                            <div class="text-right pr-5">
                                <h6 class="d-inline text-dark">Tổng số tiền: </h6>
                                <h3 class="d-inline" style="color:#ee4d2d">
                                    <%=r.formatNum(r.getMoney())%> VND
                                </h3>
                            </div>
                        </div>
                    </div>
                </div>
                <%}%>
                <%}%>
            </div>
        </div>
    </div>

<jsp:include page="footer.jsp"></jsp:include>

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
