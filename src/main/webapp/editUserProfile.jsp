<%@ page import="vn.edu.hcmuaf.fit.bean.User" %>
<%@ page import="vn.edu.hcmuaf.fit.service.CustomerService" %>
<%@ page import="java.util.List" %>
<%@ page import="vn.edu.hcmuaf.fit.service.InforService" %>
<%@ page import="vn.edu.hcmuaf.fit.security.KeyManager" %>
<%@ page import="java.security.Signature" %>
<%@ page import="vn.edu.hcmuaf.fit.model.*" %>
<%@ page import="vn.edu.hcmuaf.fit.service.UserService" %>
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
  <title><%=InforService.getInformation("NameShop").get(0).getContent()%> | Thông Tin</title>

  <!-- Google Font -->
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Dancing+Script:wght@400;500;600;700&family=Roboto:wght@100;300;400;500;700&display=swap"
        rel="stylesheet">
  <link rel="icon" href="img/favicon.ico" type="image/x-icon"/>
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
  Customer customer = CustomerService.getCusByIdAcc(auth.getId());%>
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
      <%if (auth == null) {%>
      <li><a onclick="notLogged()"><i class="fa fa-heart"></i>
        <span><%=listFavorite != null ? listFavorite.totalProduct() : "0"%></span></a></li>
      <% } else {%>
      <li><a href="Favorite"><i class="fa fa-heart"></i> <span
              id="totalFav"><%=listFavorite != null ? listFavorite.totalProduct() : "0"%></span></a></li>

      <%
        }
        List<ItemProductInCart> listItemCart = (List<ItemProductInCart>) session.getAttribute("itemCart");
      %>
      <li><a href="shoping-cart.jsp"><i class="fa fa-shopping-bag"></i>
        <span><%= listItemCart != null ? listItemCart.size() : "0"%></span></a></li>
    </ul>
  </div>
  <div class="humberger__menu__widget">
    <div class="header__top__right__auth">
      <a href="<%=auth == null ?"signin.jsp":"#"%>"><i
              class="fa fa-user"></i></i><%= auth != null ? auth.getName() : "Đăng nhập"%>
      </a>
      <% if (auth != null) { %>
      <div class="header__top__right__auth__dropdown">
        <a onclick="checkPass('<%=auth.getEmail()%>','<%=auth.getPass()%>')" class="dropdown-item">Đặt lại mật
          khẩu</a>
        <% if (auth.checkRole()) { %>
        <a href="admin/ListReceipt_Admin" class="dropdown-item">Vào trang quản lí</a>
        <%}%>
        <a href="doSignOut" method="get" class="dropdown-item">Đăng xuất</a>
      </div>
      <%}%>
    </div>
  </div>
  <nav class="humberger__menu__nav mobile-menu">
    <ul>
      <li class=""><a href="./">Trang chủ</a></li>
      <li class=""><a href="about.jsp">Giới thiệu</a></li>
      <li class=""><a href="ListProduct">Sản phẩm</a></li>
      <li class=""><a href="ListBlog">Tin tức</a></li>
      <li class=""><a href="contact.jsp">Liên hệ</a></li>
    </ul>
  </nav>
  <div id="mobile-menu-wrap"></div>
  <div class="header__top__right__social">
    <a href="<%=InforService.getInformation("SocialNetwork").get(0).getContent()%>" target="blank"><i
            class="fa fa-facebook"></i></a>
    <a href="<%=InforService.getInformation("SocialNetwork").get(1).getContent()%>" target="blank"><i
            class="fa fa-comment"></i></a>
    <a href="<%=InforService.getInformation("SocialNetwork").get(2).getContent()%>" target="blank"><i
            class="fa fa-instagram"></i></a>
  </div>
  <div class="humberger__menu__contact">
    <ul>
      <li><i class="fa fa-envelope"></i><%=InforService.getInformation("Email").get(0).getContent()%>
      </li>
      <li><%=InforService.getInformation("Delivery").get(0).getContent()%>
      </li>
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
          <a href="./"><img src="<%=InforService.getImgLogo().get(0).getContent()%>" alt=""
                            class="header__logo_img"></a>
        </div>
      </div>
      <div class="col-lg-7 ">
        <nav class="header__menu">
          <ul>
            <li class=""><a href="./">Trang chủ</a></li>
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
            <%if (auth == null) {%>
            <li><a onclick="notLogged()"><i class="fa fa-heart"></i>
              <span><%=listFavorite != null ? listFavorite.totalProduct() : "0"%></span></a></li>
            <% } else {%>
            <li><a href="Favorite"><i class="fa fa-heart"></i> <span
                    id="totalFav1"><%=listFavorite != null ? listFavorite.totalProduct() : "0"%></span></a>
            </li>

            <%}%>
            <li><a href="shoping-cart.jsp"><i class="fa fa-shopping-bag"></i>
              <span><%= listItemCart != null ? listItemCart.size() : "0"%></span></a></li>
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
              <button href="ProductFilter" type="submit" class="site-btn"><i class="fa fa-search"></i>
                <span class="text_search">TÌM KIẾM</span></button>
            </form>
          </div>

          <div class="hero__search__phone">
            <div class="hero__search__phone__icon">
              <a href="tel:<%=InforService.getInformation("PhoneNumber").get(0).getContent()%>"
                 class="fa fa-phone cursor"></a>
            </div>
            <div class="hero__search__phone__text">
              <h5><%=InforService.getInformation("PhoneNumber").get(0).getContent()%>
              </h5>
              <span><%=InforService.getInformation("TimeShop").get(1).getContent()%></span>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</section>
<!-- Breadcrumb Section Begin -->
<section class="breadcrumb-section set-bg"
         data-setbg="<%=InforService.getInformation("ImageMenu").get(0).getContent()%>">
  <div class="container">
    <div class="row">
      <div class="col-lg-12 text-center">
        <div class="breadcrumb__text">
          <h2>Hồ Sơ Của Tôi</h2>
          <div class="breadcrumb__option">
            <span>Quản lý thông tin hồ sơ để bảo mật tài khoản</span>
          </div>
        </div>
      </div>
    </div>
  </div>
</section>
<!-- Breadcrumb Section End -->

<!-- Contact Section Begin -->
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
<!-- Checkout Section Begin -->
<section class="checkout spad">
  <div class="container">
    <div style="
                        display: flex;
                        justify-content: space-between;
                        padding-bottom: 20px;
                        margin-bottom: 25px;
                        border-bottom: 1px solid #e1e1e1;">
      <h4 style="
                        color: #1c1c1c;
	                    font-weight: 700;">
        Thông Tin Tài Khoản
      </h4>
      <div>
        <%if (!KeyManager.userIsHasKeyActive(auth.getId())) {%>
        <button class="button_product" onclick="confirmGenKey('<%=auth.getId()%>', <%=auth.hasKey()%>)">Tạo
          khóa
        </button>
        <%
        } else {
        %>
        <button class="button_product" onclick="reportKey('<%= auth.getId()%>')">Mất khóa</button>
        <button class="button_product" onclick="confirmGenKey('<%=auth.getId()%>', <%=auth.hasKey()%>)">Tạo
          khóa
        </button>
        <%}%>
      </div>
    </div>

    <div>
      <div class="row">
        <div class="col-lg-6 col-md-6">
          <div class="row">
            <div class="col-lg-12">
              <div class="checkout__input">
                <p>Tên Đăng Nhập</p>
                <input type="text" id="ten" value="<%=auth.getName() %>">
              </div>

            </div>
          </div>
          <div class="checkout__input">
            <p>Địa chỉ</p>
            <input type="text" placeholder="Cập nhật số nhà, xã/phường, quận/huyện, tỉnh/thành phố..."
                   id="diachi" value="<%=customer.getDIACHI()%>">
          </div>

          <div class="row">
            <div class="col-lg-6">
              <div class="checkout__input">
                <p>Điện Thoại</p>
                <input type="tel" placeholder="Cập nhật số điện thoại..." id="phone"
                       value="<%=customer.getSDT()%>">
              </div>
            </div>
            <div class="col-lg-6">
              <div class="checkout__input">
                <p>Email</p>
                <input type="email" id="email" value="<%=auth.getEmail()%>">
              </div>
            </div>
          </div>

          <button class="button_product" onclick="changeProfile()">
            Lưu Thay Đổi
          </button>
        </div>
        <%
          List<SignUser> lu = UserService.getListKey(auth.getId());
        %>

        <div class="col-lg-6 col-md-6" style="">
          <p style="font-weight: bold; font-size: 20px; color: black">Quản lý khóa</p>
          <div class="row"
               style="border-left: 2px solid rgba(83,81,86,0.10); border-right: 2px solid rgba(83,81,86,0.10); overflow-x: auto; max-height: 350px; overflow-y: auto;">
            <%if (!lu.isEmpty()) {%>
            <div>
              <table class="table">
                <thead>
                <tr>
                  <th colspan="3">STT</th>
                  <th colspan="3">Khóa</th>
                  <th>Ngày tạo</th>
                  <th>Ngày hết hạn</th>
                  <th>Trạng Thái</th>
                </tr>
                </thead>
                <tbody>
                <% int i = 1;
                  for (SignUser su : lu) {%>
                <tr>
                  <td colspan="3" style="overflow-x: auto; white-space: nowrap;"><%=i%>
                  </td>
                  <td colspan="3"
                      style="overflow-x: auto; white-space: nowrap;"><%=su.getKeySecret(su.getPbkey())%>
                  </td>
                  <td style="overflow-x: auto; white-space: nowrap;"><%=su.getCreateDate()%>
                  </td>
                  <td style="overflow-x: auto; white-space: nowrap;"><%=su.getExpireDate()%>
                  </td>
                  <% if (su.getStatus() == 1) {%>
                  <td style="color: #28a745; overflow-x: auto; white-space: nowrap;"><%=su.getStatusString()%>
                  </td>
                  <% } else if (su.getStatus() == 2) { %>
                  <td style="overflow-x: auto; white-space: nowrap;"><%=su.getStatusString()%>
                  </td>
                  <% } else { %>
                  <td style="color: #bb1813; overflow-x: auto; white-space: nowrap;"><%=su.getStatusString()%>
                  </td>
                  <% }%>
                </tr>
                <% i++;
                } %>
                </tbody>
              </table>
            </div>
            <%} else {%>
            <div style="align-items: center; overflow: hidden;">
              <img src="https://thoibaonganhang.vn/stores/news_dataimages/minhvl/112021/16/20/2752_at6_Copy.jpg">
            </div>
            <%}%>
          </div>
        </div>
      </div>
    </div>
  </div>
</section>

<!-- Contact Section End -->


<jsp:include page="footer.jsp"></jsp:include>
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
  function sendFb() {
    var newFeedback = document.getElementById("feedback").value;
    var yourName = document.getElementById("yourName").value;
    var yourEmail = document.getElementById("yourEmail").value;
    var today = new Date();
    var date = today.getFullYear() + "-" + (today.getMonth() + 1) + "-" + today.getDate() + " " + today.getHours() + ":" + today.getMinutes() + ":" + today.getSeconds();
    var urlc = "AddFeedback?yourName=" + yourName + "&yourEmail=" + yourEmail + "&date=" + date + "&feedback=" + newFeedback;
    $.ajax({
      url: urlc,
      type: "POST",
      success: function () {
        document.getElementById("yourName").value = null;
        document.getElementById("yourEmail").value = null;
        document.getElementById("feebback").value = null;
      }
    });
  }

  // function checkTextarea() {
  //     var textareaValue = document.getElementById("keyContent2").value.trim();
  //     var confirmButton = document.querySelector(".confirm-btn");
  //
  //     // Kiểm tra nếu có dữ liệu trong textarea thì enable button, ngược lại disable
  //     if (textareaValue.length > 0) {
  //         confirmButton.removeAttribute("disabled");
  //         confirmButton.style.backgroundColor = "#ff96b7";
  //
  //     } else {
  //         confirmButton.setAttribute("disabled", true);
  //         confirmButton.style.backgroundColor = "rgba(11, 11, 11, 0.5)";
  //     }
  // }
  //
  // // Gọi hàm checkTextarea khi textarea thay đổi
  // document.getElementById("keyContent2").addEventListener("input", checkTextarea);
</script>


</body>
</html>