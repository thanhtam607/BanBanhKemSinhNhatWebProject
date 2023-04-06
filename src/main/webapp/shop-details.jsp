<%@ page import="vn.edu.hcmuaf.fit.bean.User" %>
<%@ page import="java.util.List" %>
<%@ page import="vn.edu.hcmuaf.fit.service.ProductService" %>
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
        <a href="#"><img src="<%=InforService.getImgLogo().get(0).getContent()%>" alt=""></a>
    </div>
    <div class="humberger__menu__cart">
        <ul>
            <% FavoriteProduct listFavorite = (FavoriteProduct) session.getAttribute("listFavorite");%>
            <%if(auth==null){%>
            <li><a onclick="notLogged()"><i class="fa fa-heart"></i> <span ><%=listFavorite != null ? listFavorite.totalProduct() : "0"%></span></a></li>
            <% }else {%>
            <li><a href="Favorite"><i class="fa fa-heart"></i> <span id="totalFav"><%=listFavorite != null ? listFavorite.totalProduct() : "0"%></span></a></li>

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
<% Product pro = (Product) request.getAttribute("getDetail");%>
<!-- Breadcrumb Section Begin -->
<section class="breadcrumb-section set-bg" data-setbg="<%=InforService.getInformation("ImageMenu").get(0).getContent()%>">
    <div class="container">
        <div class="row">
            <div class="col-lg-12 text-center">
                <div class="breadcrumb__text">
                    <h2><%=pro.getName()%></h2>
                    <div class="breadcrumb__option">
                        <a href="./Index">Trang chủ</a>
                        <a href="./ListProduct">Sản phẩm</a>
                        <span><%= pro.getName()%></span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- Breadcrumb Section End -->

<!-- Product Details Section Begin -->
<section class="product-details spad">
    <div class="container">
        <div class="row">
            <div class="col-lg-6 col-md-6">
                <div class="product__details__pic">
                    <div class="product__details__pic__item">
                        <img class="product__details__pic__item--large"
                             <%for(Image img :pro.getListImg()){
                                 if(!img.delete()&& !img.isHide()){%>
                                    src="<%= img.getImg()%>" alt="">
                                <% break;
                                 }
                             }%>

                    </div>
                    <div class="product__details__pic__slider owl-carousel">
                        <% for (Image img : pro.getListImg()){
                        if(!img.delete() && !img.isHide()){%>
                        <img data-imgbigurl="<%= img.getImg()%>"
                             src="<%= img.getImg()%>" alt="">
                        <%}}%>
                    </div>
                </div>
            </div>
            <div class="col-lg-6 col-md-6">
                <div class="product__details__text" >
                    <h3><%= pro.getName()%></h3>
                    <%if(pro.getPromotional()!=0){%>
                    <div class="product__details__price"><%= pro.formatNum(pro.getPromotional())%> VND
                            <span><%=pro.formatNum(pro.getPrice())%> VND</span>
                    </div>
                    <%}else{%>
                    <div class="product__details__price"><%= pro.formatNum(pro.getPrice())%> VND</div>
                    <%}%>

                    <p><%= pro.getIntroduction()%></p>
                    <div class="product__details__quantity">
                        <div class="quantity">
                            <div class="pro-qty" >
                                <span class="dec qtybtn">-</span>
                                <input id="qty" type="text"  value="1">
                                <span class="inc qtybtn" >+</span>
                            </div>
                        </div>
                    </div>
                    <% if(auth==null){%>
                    <a onclick="notLogged()" class="primary-btn" style="color: #FFFFFF">Thêm vào giỏ hàng</a>
                    <a onclick="notLogged()"  class="heart-icon"><span class="icon_heart_alt"></span></a>
                    <%} else{%>
                    <a onclick="addToCart('<%= pro.getId()%>')" class="primary-btn" style="color: #FFFFFF">Thêm vào giỏ hàng</a>
                    <a onclick="addToFav('<%=pro.getId()%>')"  class="heart-icon"><span class="icon_heart_alt"></span></a>
                    <%}%>
                     <ul>
                        <li><b>Tình trạng</b> <span><%=pro.getStatus()%></span></li>
                        <li><b>Giao hàng</b> <span>Giao ngay trong ngày</span></li>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="col-lg-12">
                <div class="product__details__tab">
                    <ul class="nav nav-tabs" role="tablist">
                        <li class="nav-item">
                            <a class="nav-link active" data-toggle="tab" href="#tabs-1" role="tab"
                               aria-selected="true">Mô tả</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" data-toggle="tab" href="#tabs-2" role="tab"
                               aria-selected="false">Thông tin</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" data-toggle="tab" href="#tabs-3" role="tab"
                               aria-selected="false">Bình luận(<span id="qt-cmt"><%= pro.getComments().size()%></span>)</a>
                        </li>
                    </ul>
                    <div class="tab-content">
                        <div class="tab-pane active" id="tabs-1" role="tabpanel">
                            <div class="product__details__tab__desc">
                                <h6>Mô tả sản phẩm</h6>
                                <p><%= pro.getDescription()%></p>
                            </div>
                        </div>
                        <div class="tab-pane" id="tabs-2" role="tabpanel">
                            <div class="product__details__tab__desc">
                                <h6>Đặc điểm:</h6>
                                <p>- Loại bánh: <%= pro.getType()%></p>
                                <p>- Kích thước: <%= pro.getSize()%></p>
                                <p>- Khối lượng: <%= pro.getWeight()%>g</p>
                            </div>
                        </div>

                        <div class="tab-pane" id="tabs-3" role="tabpanel">

                            <div class="product__details__tab__desc" id="list-comments">
                                <%List<Comment> cmtList = pro.getComments();
                                    for(Comment cmt : cmtList){%>
                                <div class="comment">
                                <h6 style="margin-bottom: 10px;"><%= cmt.getkhachhang()%></h6>
                                <i class="fa fa-calendar-o"></i> <span style="font-size: 13px; color: rgb(179, 178, 178);"><%=cmt.getDate()%></span>
                                <p ><%=cmt.getBinhLuan()%></p>
                                </div>

                            <%}%>
                            </div>
                            <% if(auth!= null){%>
                            <div class = "input-comment">
                                <div class = "input-item">
                                    <input id="comment" type="text" placeholder="   Viết bình luận...">
                                    <span class="add-comment">
                                        <button onclick="sendC()"  type="submit" class="btn btn-add-comment" >Gửi</button>
                                    </span>
                                </div>
                                <%}%>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- Product Details Section End -->

<!-- Related Product Section Begin -->
<section class="related-product">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <div class="section-title related__product__title">
                    <h2>Sản phẩm liên quan</h2>
                </div>
            </div>
        </div>
        <div class="row">
            <% String type = pro.getType(); %>
            <% List<Product> listproduct = (List<Product>) request.getAttribute("splq");
            if (listproduct.size() < 4) {
             for(Product product : listproduct){ %>
            <div class="col-lg-3 col-md-4 col-sm-6">
                <div class="product__item">
                    <%for(Image img :product.getListImg()){
                        if(!img.delete()&& !img.isHide()){%>
                    <div class="product__item__pic set-bg" data-setbg="<%=img.getImg()%>">
                    <% break;
                    }
                    }%>

                        <ul class="product__item__pic__hover">
                            <% if(auth==null){%>
                            <li><a onclick="notLogged()"><i class="fa fa-heart"></i></a></li>
                            <li><a onclick="notLogged()"><i class="fa fa-shopping-cart"></i></a></li>
                            <%} else{%>
                            <li><a onclick="addToFav('<%=product.getId()%>')"><i class="fa fa-heart"></i></a></li>
                            <li><a onclick="addToCartI('<%=product.getId()%>')"><i class="fa fa-shopping-cart"></i></a></li>
                            <%}%>
                        </ul>
                    </div>
                    <div class="product__item__text">
                        <h6><a href="ProductDetail?id=<%=product.getId() %>"><%=product.getName()%></a></h6>
                        <%if(product.getPromotional()!=0){%>
                        <div class="product__discount__item__text" style="padding-top: 0px">
                            <div class="product__item__price"><%=product.formatNum(product.getPromotional())%> VND<span><%=product.formatNum(product.getPrice())%> VND</span></div>
                        </div>
                        <%}else{%>
                        <h5><%= product.formatNum(product.getPrice())%> VND</h5>
                        <%}%>
                    </div>
                </div>
            </div>
            <% }
            } else {
                Product product1;
                for(int i = 0; i < 4; i++ ){
                    product1 = listproduct.get(i);%>
            <div class="col-lg-3 col-md-4 col-sm-6">
                <div class="product__item">
                    <%for(Image img :product1.getListImg()){
                        if(!img.delete()&& !img.isHide()){%>
                    <div class="product__item__pic set-bg" data-setbg="<%=img.getImg()%>">
                        <% break;
                        }
                        }%>
                        <ul class="product__item__pic__hover">
                            <% if(auth==null){%>
                            <li><a onclick="notLogged()"><i class="fa fa-heart"></i></a></li>
                            <li><a onclick="notLogged()"><i class="fa fa-shopping-cart"></i></a></li>
                            <%} else{%>
                            <li><a onclick="addToFav('<%=product1.getId()%>')"><i class="fa fa-heart"></i></a></li>
                            <li><a onclick="addToCartI('<%=product1.getId()%>')"><i class="fa fa-shopping-cart"></i></a></li>
                            <%}%></ul>
                    </div>
                    <div class="product__item__text">
                        <h6><a href="ProductDetail?id=<%=product1.getId() %>"><%=product1.getName()%></a></h6>
                        <h5><%=product1.formatNum(product1.getPrice())%> VND</h5>
                    </div>
                </div>
            </div>
            <% }
            }%>
            <%List<TypeOfCake> ListType = ProductService.getListType();
                for(TypeOfCake lb: ListType){%>
            <a href="ProductFilter?title=<%=lb.getName()%> &filter=<%=lb.getName()%>" type="button" class="btn btn_pink py-md-3 px-md-5 mt-2 btn-rm" >Xem nhiều hơn</a>
            <% break;} %>
        </div>
    </div>
</section>
<!-- Related Product Section End -->

<jsp:include page="footer.jsp"></jsp:include>

<%session.setAttribute("idProduct", pro.getId()); %>
<!-- Js Plugins -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>

<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script src="js/jquery-3.3.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/jquery.nice-select.min.js"></script>
<script src="js/jquery-ui.min.js"></script>
<script src="js/jquery.slicknav.js"></script>
<script src="js/mixitup.min.js"></script>
<script src="js/owl.carousel.min.js"></script>
<script src="js/main.js"></script>

<script>
    var soluong = parseInt(<%=pro.getComments().size()%>);
    document.getElementById("qt-cmt").innerHTML= soluong;
    function sendC(){
        var newComment = document.getElementById("comment").value;
        var today = new Date();
        var date = today.getFullYear() + "-" + (today.getMonth()+1)+"-"+ today.getDate()+" "+ today.getHours()+":"+today.getMinutes()+":"+ today.getSeconds();
       var urlc= "AddComment?date=" + date+"&cmt=" + newComment ;
        $.ajax({
            url: urlc,
            type: "POST",
            success: function (response){
                var comment = document.getElementById("list-comments");
                comment.innerHTML += response;
                soluong++;
                document.getElementById("qt-cmt").innerHTML= soluong;
                document.getElementById("comment").value=null;
            }
        });
    }
    function addToCart(id){

        var qty =parseInt(document.getElementById("qty").value);

        var url  ="AddToCart?masp=" +id+"&soluong="+ qty;
        var totalPro = parseInt(document.getElementById("totalPro").innerHTML);

        $.ajax({
            url: url,
            type: "GET",
            success: function (){
                totalPro+=qty;
                console.log(totalPro);
                document.getElementById("totalPro").innerHTML=totalPro.toString();
                document.getElementById("totalPro1").innerHTML=totalPro.toString();
                Swal.fire({
                    text:'Thêm sản phẩm thành công!',
                    icon: 'success',
                    showCancelButton: true,
                    cancelButtonText: 'Mua tiếp',
                    confirmButtonText: 'Đi đến giỏ hàng',
                    confirmButtonColor: '#ff96b7'}).then((result) => {
                        if (result.isConfirmed) {
                            location.href = "shoping-cart.jsp";
                        }

                    }
                );
            }
        });

    };
</script>
</body>
</html>