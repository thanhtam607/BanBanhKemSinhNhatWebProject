<%@ page import="vn.edu.hcmuaf.fit.bean.User" %>
<%@ page import="java.util.List" %>

<%@ page import="vn.edu.hcmuaf.fit.model.*" %>
<%@ page import="vn.edu.hcmuaf.fit.service.*" %>
<%@ page import="java.net.URLDecoder" %>

<!DOCTYPE html>
<%@ page contentType="text/html;charsetUTF-8" language="java" pageEncoding="utf-8"%>
<html>
<meta http-equiv="Content-Type" charset="UTF-8">

<head>

    <title> <%=InforService.getInformation("NameShop").get(0).getContent()%> | Đơn hàng</title>

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
                <a href="admin/ListReceipt_Admin" class="dropdown-item">Vào trang quản lí</a>
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
                                    <small class="text-secondary d-inline-block pt-3 pl-3">Ngày đặt hàng: </small>
                                    <small class="text-secondary d-inline-block pt-3 " style="padding: 0;margin: 0"><%=r.getExport_date()%></small>
                                </div>
                        <div class="col-6 text-right  my-2 pt-2 ">
                            <small class="d-inline text-secondary">Trạng thái |</small>
                            <div id="statusName<%=r.getId()%>" class="d-inline pr-3 text-uppercase" style="color:#ee4d2d; font-size:14px">
                                <span><%=URLDecoder.decode(r.getStatusName())%></span>
                            </div>
                        </div>
                    </div>
                    <%List<Bill_Detail> billDetailList = ReceiptService.getBill_DetailUser(r.getId());
                        for(Bill_Detail c: billDetailList){
                        %>
                    <div class="card border-left-0 border-right-0 border-bottom-0 mx-3">
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
                                        <a href="ProductDetail?id=<%=c.getMasp()%>" class="text-decoration-none text-dark text-uppercase">
                                            <%=c.getNamePro()%>
                                        </a>
                                    </div>
                                    <div class="text-dark" style="font-size:14px">x <%=c.getSolg()%></div>
                                    <div class="text-dark" style="font-size:14px">Lời chúc trên bánh: <%=c.getNote() != null ? c.getNote(): "Không có"%></div>
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

                    <div class="col-12 mb-2">
                        <div class="card-body my-0">
                            <% if(r.checkEdited()){ %>
                            <div class="card-text pt-2">
                                <span class="text-danger font-italic small font-weight-bold">
                                    * Đơn hàng đã bị chỉnh sửa và không thể giao hàng, vui lòng huỷ đơn và đặt lại! Xin lỗi quý khách vì sự bất tiện này :((
                                </span>
                            </div>
                            <% }%>
                            <div class="card-text text-dark border-bottom pb-2">
                                <span class=" text-dark">
                                    GHI CHÚ CHUNG: <%=r.getNote() != null? r.getNote(): "Không có"%>
                                </span>
                            </div>
                            <div class="card-text text-dark border-bottom pb-2">
                                <span class=" text-dark text-uppercase">
                                    Dự kiến giao: <%=r.getDelivery_date()%>
                                </span>
                            </div>
                            <div class="card-text text-dark pt-2">
                                <span class=" text-dark text-uppercase">
                                    ĐỊA CHỈ GIAO HÀNG: <%=r.getAddress()%>
                                </span>
                            </div>

                        </div>
                    </div>
                    <div class="row mb-2 flex-row-reverse" style="margin-top: -20px">
                        <div class="col-12 my-2">
                            <div class="text-right pr-5">
                                <h6 class="d-inline text-dark">Tổng tiền hàng: </h6>
                                <span class="d-inline" style="font-weight:400">
                                    <%=r.formatNum((int) r.getPro_bill())%> VND
                                </span>
                            </div>
                        </div>
                        <div class="col-12 my-2">
                            <div class="text-right pr-5">
                                <h6 class="d-inline text-dark">Phí vận chuyển: </h6>
                                <span class="d-inline" style="font-weight: 400">
                                    <%=r.formatNum((int) r.getFee_bill())%> VND
                                </span>
                            </div>
                        </div>
                    </div>
                    <div class="row mb-2 mt-2" style="margin-top: -20px">
                        <div class="col-6 pt-2 pb-3">
                            <div id="<%=r.getId()%>" class="text-left mr-3">
                                <%if(r.checkState()){%>
                                <button onclick="cancelOrder('<%=r.getId()%>')" type="submit" style="border:1px solid #ccc;" class="btn rounded-0 py-2 ml-2" >
                                            Hủy đơn hàng
                                </button>
                                <%}%>
<%--                                    if(r.getStatus() == 4){%>--%>
<%--                                <button onclick="buyAgain('<%=r.getId()%>')" type="submit" style="border:1px solid #ccc;" class="btn rounded-0 py-2 ml-2" >--%>
<%--                                    Mua lại--%>
<%--                                </button>--%>
<%--                                <%}%>--%>

                            </div>
                        </div>
                        <div class="col-6 my-2">
                            <div class="text-right pr-5">
                                <h6 class="d-inline text-dark">Tổng thanh toán: </h6>
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
