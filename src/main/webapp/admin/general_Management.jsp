<%@ page import="vn.edu.hcmuaf.fit.bean.User" %>
<%@ page import="vn.edu.hcmuaf.fit.model.Blog" %>
<%@ page import="java.util.List" %>
<%@ page import="vn.edu.hcmuaf.fit.service.BlogService" %>
<%@ page import="vn.edu.hcmuaf.fit.model.General_information" %>
<%@ page import="vn.edu.hcmuaf.fit.service.InforService" %>
<%@ page contentType="text/html;charsetUTF-8" language="java" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

  <!-- Icon Font Stylesheet -->
  <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

  <!-- CSS -->
  <link rel="stylesheet" href="css/bootstrap-reboot.min.css">
  <link rel="stylesheet" href="css/bootstrap-grid.min.css">
  <link rel="stylesheet" href="css/magnific-popup.css">
  <link rel="stylesheet" href="css/jquery.mCustomScrollbar.min.css">
  <link rel="stylesheet" href="css/select2.min.css">
  <link rel="stylesheet" href="css/ionicons.min.css">


  <!-- Favicons -->
  <link rel="icon" type="image/png" href="icon/favicon-32x32.png" sizes="32x32">
  <link rel="apple-touch-icon" href="icon/favicon-32x32.png">

  <!-- boostrap -->
  <link href="css/bootstrap.min.css" rel="stylesheet">
  <%--  admin  css --%>
  <link rel="stylesheet" href="css/admin.css">
  <!-- index css -->
  <link rel="stylesheet" href="./css/style.css" >

  <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
  <link href="lib/tempusdominus/css/tempusdominus-bootstrap-4.min.css" rel="stylesheet" />


  <meta name="description" content="">
  <meta name="keywords" content="">
  <meta name="author" content="Dmitry Volkov">
  <title>Admin | Shop Bánh Kem</title>
  <script src="libraries/ckeditor/ckeditor.js"></script>
</head>

<body>
<% User auth = (User) session.getAttribute("auth");%>
<!-- header -->
<header class="header">
  <div class="header__content">
    <!-- header logo -->
    <a href="./ListReceipt_Admin" class="header__logo">
      <img src="../<%=InforService.getImgLogo().get(0).getContent()%>" alt="">
    </a>
    <!-- end header logo -->

    <!-- header menu btn -->
    <button class="header__btn" type="button">
      <span></span>
      <span></span>
      <span></span>
    </button>
    <!-- end header menu btn -->
  </div>
</header>
<!-- end header -->

<!-- Sidebar Start -->
<div class="sidebar pe-4 pb-3">
  <nav class="navbar bg-pink navbar-dark">

    <div class="d-flex align-items-center ms-4 mb-4">
      <div class="position-relative">
        <i class="fa fa-user icon__user"></i>
        <div class="bg-success rounded-circle border border-2 border-white position-absolute end-0 bottom-0 p-1"></div>
      </div>
      <div class="ms-3">
        <h6 class="mb-0"><%= auth != null ? auth.getName():"ADMIN"%></h6>
        <span><%= auth != null ? auth.getRoleName():"Admin"%></span>
      </div>
    </div>
    <div class="navbar-nav w-100">
      <a href="./ListReceipt_Admin" class="nav-item nav-link "><i class="fa fa-tachometer-alt me-2"></i>Tổng quan</a>
      <%--                    <a href="general_Management.jsp" class="nav-item nav-link"><i class="fa fa-user"></i>Quản lý chung</a>--%>
      <a href="./ListProduct_Admin" class="nav-item nav-link"><i class="fa fa-th me-2"></i>DS Sản Phẩm</a>

      <a href="./ListCustomer" class="nav-item nav-link"><i class="fa fa-th me-2"></i>DS Khách Hàng</a>
      <a href="./ListBlog-admin" class="nav-item nav-link active"><i class="fa fa-th me-2"></i>DS Tin Tức</a>
      <a href="./ListReceipt_full_Admin" class="nav-item nav-link "><i class="fa fa-th me-2"></i>DS Đơn Hàng</a>
      <a href="feedbacks.jsp" class="nav-item nav-link"><i class="fa fa-th me-2"></i>DS Đánh giá</a>

      <a href="catalog_Management.jsp" class="nav-item nav-link"><i class="fa fa-file me-2"></i>QL danh mục</a>
      <a href="List_Discounts" class="nav-item nav-link"><i class="fa fa-birthday-cake me-2"></i>Khuyến mãi</a>
      <a href="../Index" class="nav-item nav-link"><i class="fa fa-arrow-alt-circle-right me-2"></i>Về trang chủ</a>
      <!--  -->
    </div>
  </nav>
</div>
<!-- Sidebar End -->

<!-- main content -->
<main class="main">
  <div class="container-fluid">
    <div class="row">
      <!-- main title -->
      <div class="col-12">
        <div class="main__title">
          <h2>Quản lý cửa hàng</h2>
        </div>
      </div>
      <!-- end main title -->
      <% List<General_information> listInfor =   InforService.getImgSlideShow();%>
      <% List<General_information> listLogo =   InforService.getImgLogo();%>
      <!-- profile -->
      <div class="col-12">
        <div class="profile__content">
          <!-- profile user -->
          <div class="profile__user">
            <div class="profile__avatar">
              <img src="../<%=listLogo.get(0).getContent()%>" alt="" href="#modal-update" class="open-modal">
            </div>
            <!-- or red -->
            <div class="profile__meta profile__meta--green">
              <h3 class="text--green">Tên cửa hàng</h3>
            </div>
          </div>
          <!-- end profile user -->
          <!-- profile btns -->
          <div class="profile__actions">
            <a href="#modal-update" class="main__table-btn main__table-btn--edit open-modal"><i class="fa fa-edit"></i></a>
          </div>
        </div>
      </div>
      <!-- end profile -->

      <!-- content tabs -->
      <div class="tab-content" id="myTabContent">
        <div class="tab-pane fade show active" id="tab-1" role="tabpanel" aria-labelledby="1-tab">
          <div class="col-12">
            <div class="row">
              <!-- details form -->
              <div class="col-12">
                  <div class="row row--form">
                    <div class="col-12">
                    </div>
                    <%-- Image Slideshow--%>
                    <div class="d-flex align-items-center justify-content-between mb-4">
                      <h6 class="mb-0 text--green" style="margin-top: 20px;">Hình nền hiển thị trang chủ:</h6>
                    </div>
                    <div class="container-fluid p-0 mb-5 pb-5" style="width: 500px; height: 355px;">
                      <div id="header-carousel" class="carousel slide" data-ride="carousel">
                        <div class="carousel-inner">
                          <div class="carousel-item active">
                            <img class="w-100 rounded" src="../<%=listInfor.get(0).getContent()%>" alt="Image">
                            <div class="carousel-caption d-flex flex-column align-items-center justify-content-center distance">
                              <div class="col-12">
                                <div class="paginator-wrap" style="display: block; margin-left: auto; margin-right: auto; width: 120px">
                                  <span><a href="#modal-slideshow1" class="open-modal" style="color: white;">Đổi hình ảnh</a></span>
                                </div>
                              </div>
                            </div>
                          </div>
                          <div class="carousel-item">
                            <img class="w-100 rounded" src="../<%=listInfor.get(1).getContent()%>" alt="Image">
                            <div class="carousel-caption d-flex flex-column align-items-center justify-content-center distance">
                              <div class="col-12">
                                <div class="paginator-wrap" style="display: block; margin-left: auto; margin-right: auto; width: 120px">
                                  <span><a href="#modal-slideshow2" class="open-modal" style="color: white;">Đổi hình ảnh</a></span>
                                </div>
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
                    <form action="UpdateSloganIndex" method="post" class="form">
                    <div class="col-12 col-md-12 col-lg-12 col-xl-12">
                      <div class="form__group">
                        <input type="hidden" name="idslogan1" class="form__input" style="display: none" value="<%=InforService.getInformation("IndexSlogan").get(0).getId()%>">
                        <label class="form__label" for="slogan1">Khẩu hiệu trên hình ảnh 1 :</label>
                        <input id="slogan1" type="text" name="slogan1" class="form__input" value="<%=InforService.getInformation("IndexSlogan").get(0).getContent()%>">
                      </div>
                    </div>
                    <div class="col-12 col-md-12 col-lg-12 col-xl-12">
                      <div class="form__group">
                        <input type="hidden" name="idslogan2" class="form__input" style="display: none" value="<%=InforService.getInformation("IndexSlogan").get(1).getId()%>">
                        <label class="form__label" for="slogan2">Khẩu hiệu trên hình ảnh 2 :</label>
                        <input id="slogan2" type="text" name="slogan2" class="form__input" value="<%=InforService.getInformation("IndexSlogan").get(1).getContent()%>">
                      </div>
                    </div>
                    <div class="col-12">
                      <button class="form__btn" type="submit" style="background-color: pink">Lưu thay đổi</button>
                    </div>
                    </form>
                    <div class="col-12 col-md-12 col-lg-12 col-xl-12" style="margin-top: 20px">
                      <div class="form__group">
                        <input type="hidden" name="idslogan2" class="form__input" style="display: none" value="<%=InforService.getInformation("ImageMenu").get(0).getId()%>">
                        <label class="form__label text--green font-size-20" for="imgbordermenu">Hình ảnh khung menu:</label>
                        <img style="width: 842px; height: 200px;" id="imgbordermenu" src="../<%=InforService.getInformation("ImageMenu").get(0).getContent()%>">
                        <button class="btn-success col-12"><a href="#modal-imgmenu" class="open-modal  border-2" >Đổi hình ảnh</a></button>
                      </div>
                    </div>
                    <% List<General_information> listIntro = InforService.getIntroduce();%>
                    <div class="col-12 col-md-12 col-lg-12 col-xl-12">
                      <form action="UpdateIntroduce"  method="POST" id="infor-intro" class="form">
                        <div class="form__group">
                        <input type="text" class="form__input" name="idintro" style="display: none" value="<%=listIntro.get(0).getId()%>">
                        <label class="form__label text--green font-size-20" for="contentintro">Nội dung trang giới thiệu:</label>
                        <textarea class="form__input text-justify"  id="contentintro" name="contentintro"><%=listIntro.get(0).getContent()%></textarea>
                        <button class="modal__btn modal__btn--apply" type="submit" style="width: 200px; margin-top: 10px; color: black; background-color: pink" form="infor-intro">Lưu lại phần giới thiệu</button>
                      </div>
                    </form>
                    </div>
                  </div>
              </div>
              <!-- end details form -->
            </div>
          </div>
        </div>
      </div>
      <!-- end content tabs -->
    </div>
  </div>
</main>
<!-- end main content -->
<main class="main">
  <div class="container-fluid">
    <div class="row">
      <div class="col-12">
      <!-- content tabs -->
      <div class="tab-content">
        <div class="tab-pane fade show active" role="tabpanel" aria-labelledby="1-tab">
          <div class="col-12">
            <div class="row">
              <!-- details form -->
              <div class="col-12 col-lg-8">
                  <div class="row row--form">
                    <div class="col-12">
                      <h4 class="form__title text--green">Thông tin chung</h4>
                    </div>
                    <form action="UpdateGeneral_Infor" method="post" class="form">
                    <div class="col-12 col-md-12 col-lg-12 col-xl-12">
                      <div class="form__group">
                        <input id="idaddress" type="hidden" name="idaddress" class="form__input" style="display: none" value="<%=InforService.getInformation("Address").get(0).getId()%>">
                        <label class="form__label" >Địa chỉ shop:</label>
                        <input  type="text" name="address" class="form__input" value="<%=InforService.getInformation("Address").get(0).getContent()%>">
                      </div>
                    </div>

                    <div class="col-12 col-md-7 col-lg-12 col-xl-12">
                      <div class="form__group">
                        <input id="idemail" type="hidden" name="idemail" class="form__input" style="display: none" value="<%=InforService.getInformation("Email").get(0).getId()%>">
                        <label class="form__label" for="email">Email:</label>
                        <input id="email" type="text" name="email" class="form__input" value="<%=InforService.getInformation("Email").get(0).getContent()%>" >
                      </div>
                    </div>
                    <div class="col-12 col-md-5 col-lg-12 col-xl-5">
                      <div class="form__group">
                        <input id="idphone" type="hidden" name="idphone" class="form__input" style="display: none" value="<%=InforService.getInformation("PhoneNumber").get(0).getId()%>">
                        <label class="form__label" for="phone">Số điện thoại:</label>
                        <input id="phone" type="number" min="99999999" name="phone" class="form__input" value="<%=InforService.getInformation("PhoneNumber").get(0).getContent()%>">
                      </div>
                    </div>
                    <div class="col-12">
                      <input class="form__btn" type="submit" value="Lưu thông tin" style="background-color: pink">
                    </div>
                    </form>
                  </div>
              </div>
              <div class="col-12 col-lg-8" style="margin-top: 30px">
                <div class="row row--form">
                  <div class="col-12">
                    <h4 class="form__title text--green">Thông tin mạng xã hội</h4>
                  </div>
                  <form method="post" action="UpdateSocialNetwork" class="form">
                  <div class="col-12 col-md-12 col-lg-12 col-xl-12">
                    <div class="form__group">
                      <input id="idfacebook" type="hidden" name="idfacebook" class="form__input" style="display: none" value="<%=InforService.getInformation("SocialNetwork").get(0).getId()%>">
                      <label class="form__label" >Địa chỉ Facebook:</label>
                      <input  type="text" name="facebook" class="form__input" value="<%=InforService.getInformation("SocialNetwork").get(0).getContent()%>">
                    </div>
                  </div>
                  <div class="col-12 col-md-12 col-lg-12 col-xl-12">
                    <div class="form__group">
                      <input id="idmap" type="hidden" name="idmap" class="form__input" style="display: none" value="<%=InforService.getInformation("GoogleMap").get(0).getId()%>">
                      <label class="form__label" >Địa chỉ GoogleMap:</label>
                      <input  type="text" name="map" class="form__input" value="<%=InforService.getInformation("GoogleMap").get(0).getContent()%>">
                    </div>
                  </div>
                  <div class="col-12 col-md-7 col-lg-12 col-xl-12">
                    <div class="form__group">
                      <input id="idmess" type="hidden" name="idmess" class="form__input" style="display: none" value="<%=InforService.getInformation("SocialNetwork").get(1).getId()%>">
                      <label class="form__label" for="email">Địa chỉ Messenger:</label>
                      <input id="mess" type="text" name="mess" class="form__input" value="<%=InforService.getInformation("SocialNetwork").get(1).getContent()%>" >
                    </div>
                  </div>
                  <div class="col-12 col-md-5 col-lg-12 col-xl-12">
                    <div class="form__group">
                      <input id="idinsta" type="hidden" name="idinsta" class="form__input" style="display: none" value="<%=InforService.getInformation("SocialNetwork").get(2).getId()%>">
                      <label class="form__label" for="phone">Địa chỉ Instagram:</label>
                      <input id="insta" type="text" name="insta" class="form__input" value="<%=InforService.getInformation("SocialNetwork").get(2).getContent()%>">
                    </div>
                  </div>
                  <div class="col-12">
                    <input class="form__btn" type="submit" value="Lưu thông tin" style="background-color: pink">
                  </div>
                  </form>
                </div>
              </div>
              <div class="col-12 col-lg-8" style="margin-top: 30px">
                <div class="row row--form">
                  <div class="col-12">
                    <h4 class="form__title text--green">Thông tin giao hàng</h4>
                  </div>
                  <form action="UpdateDelivery" method="post">
                  <div class="col-12 col-md-12 col-lg-12 col-xl-12">
                    <div class="form__group">
                      <input id="iddelivery" type="hidden" name="iddelivery" class="form__input" style="display: none" value="<%=InforService.getInformation("Delivery").get(0).getId()%>">
                      <label class="form__label" >Khuyến mãi giao hàng:</label>
                      <input  type="text" name="delivery" class="form__input" value="<%=InforService.getInformation("Delivery").get(0).getContent()%>">
                    </div>
                  </div>
                  <div class="col-12">
                    <input class="form__btn" type="submit" value="Lưu thông tin" style="background-color: pink">
                  </div>
                  </form>
                </div>
              </div>
              <!-- end details form -->

              <!-- password form -->
              <div class="col-12 col-lg-4" style="margin-top: -1044px">
                <div class="form form--profile">
                  <div class="row row--form">
                    <div class="col-12">
                      <h4 class="form__title text--green">Thông tin cuối trang</h4>
                    </div>
                    <form action="UpdateShopInfor" method="post" class="form">
                    <div class="col-12 col-md-12 col-lg-12 col-xl-12">
                      <div class="form__group">
                        <input type="hidden" name="idopendate" class="form__input" style="display: none" value="<%=InforService.getInformation("TimeShop").get(0).getId()%>">
                        <label class="form__label" for="opendate">Ngày mở cửa:</label>
                        <input id="opendate" type="text" name="opendate" class="form__input" value="<%=InforService.getInformation("TimeShop").get(0).getContent()%>">
                      </div>
                    </div>
                    <div class="col-12 col-md-12 col-lg-12 col-xl-12">
                      <div class="form__group">
                        <input type="hidden" name="idtimeopen" class="form__input" style="display: none" value="<%=InforService.getInformation("TimeShop").get(1).getId()%>">
                        <label class="form__label" for="timeopen">Giờ mở cửa:</label>
                        <input id="timeopen" type="text" name="timeopen" class="form__input" value="<%=InforService.getInformation("TimeShop").get(1).getContent()%>">
                      </div>
                    </div>
                    <div class="col-12 col-md-12 col-lg-12 col-xl-12">
                      <div class="form__group">
                        <input type="hidden" name="idclosedate" class="form__input" style="display: none" value="<%=InforService.getInformation("TimeShop").get(2).getId()%>">
                        <label class="form__label" for="clodedate">Ngày đóng cửa (Hoặc ngoại lệ):</label>
                        <input id="clodedate" type="text" name="closedate" class="form__input" value="<%=InforService.getInformation("TimeShop").get(2).getContent()%>">
                      </div>
                    </div>
                    <div class="col-12 col-md-12 col-lg-12 col-xl-12">
                      <div class="form__group">
                        <input type="hidden" name="idtimeopen2" class="form__input" style="display: none" value="<%=InforService.getInformation("TimeShop").get(3).getId()%>">
                        <label class="form__label" for="timeopen2">Giờ mở cửa (Ngoại lệ):</label>
                        <input id="timeopen2" type="text" name="timeopen2" class="form__input" value="<%=InforService.getInformation("TimeShop").get(3).getContent()%>">
                      </div>
                    </div>
                    <div class="col-12 col-md-12 col-lg-12 col-xl-12">
                      <div class="form__group">
                        <input type="hidden" name="idoffer" class="form__input" style="display: none" value="<%=InforService.getInformation("SpecialOffer").get(0).getId()%>">
                        <label class="form__label" for="timeopen2">Khuyến mãi đặc biệt:</label>
                        <input id="offer" type="text" name="offer" class="form__input" value="<%=InforService.getInformation("SpecialOffer").get(0).getContent()%>">
                      </div>
                    </div>
                    <div class="col-12 col-md-12 col-lg-12 col-xl-12">
                      <div class="form__group">
                        <input type="hidden" name="idtimeoffer" class="form__input" style="display: none" value="<%=InforService.getInformation("SpecialOffer").get(1).getId()%>">
                        <label class="form__label" for="timeopen2">Thời gian diễn ra khuyến mãi đặc biệt:</label>
                        <input id="timeoffer" type="text" name="timeoffer" class="form__input" value="<%=InforService.getInformation("SpecialOffer").get(1).getContent()%>">
                      </div>
                    </div>
                    <div class="col-12 col-md-12 col-lg-12 col-xl-12">
                      <div class="form__group">
                        <input type="hidden" name="idimgfooter" class="form__input" style="display: none" value="<%=InforService.getInformation("SpecialOffer").get(2).getId()%>">
                        <label class="form__label" for="timeopen2">Hình ảnh thêm phần cuối trang:</label>
                        <input id="imgoffer" type="text" name="imgoffer" class="form__input" value="<%=InforService.getInformation("SpecialOffer").get(2).getContent()%>" style="display: none">
                        <img class="col-12" style="height: 80px" src="../<%=InforService.getInformation("SpecialOffer").get(2).getContent()%>">
                        <button class="btn-success col-12"><a href="#modal-imgfooter" class="open-modal  border-2" >Đổi hình ảnh</a></button>
                      </div>
                    </div>
                      <div class="col-12">
                        <button class="form__btn" type="submit" style="background-color: pink">Lưu thay đổi</button>
                      </div>
                    </form>
                  </div>
                </div>
              </div>
              <!-- end password form -->
            </div>
          </div>
        </div>

            </div>
          </div>
          <!-- end table -->
        </div>
      </div>
      <!-- end content tabs -->
</main>
<!-- end main content -->
<!-- modal update -->
<div id="modal-update" class="zoom-anim-dialog mfp-hide modal">
  <form action="UpdateImgLogo"  method="POST" enctype="multipart/form-data">
    <h6 class="modal__title">Thay đổi ảnh</h6>
    <p class="modal__text">Chọn hình ảnh</p>
    <input type="text" class="form__input" name="malogo" style="display: none" value="<%=listLogo.get(0).getId()%>">
    <input type="file" class="form__input" name="img">
    <div class="modal__btns">
      <button class="modal__btn modal__btn--apply" type="submit">Xong</button>
      <button class="modal__btn modal__btn--dismiss" type="button">Quay lại</button>
    </div>
  </form>
</div>
<!-- end modal update -->
<!-- modal imgfoodter -->
<div id="modal-imgfooter" class="zoom-anim-dialog mfp-hide modal">
  <form action="UpdateImgFooter"  method="POST" enctype="multipart/form-data">
    <h6 class="modal__title">Thay đổi ảnh</h6>
    <p class="modal__text">Chọn hình ảnh</p>
    <input type="text" class="form__input" name="mafooter" style="display: none" value="<%=InforService.getInformation("SpecialOffer").get(2).getId()%>">
    <input type="file" class="form__input" name="img">
    <div class="modal__btns">
      <button class="modal__btn modal__btn--apply" type="submit">Xong</button>
      <button class="modal__btn modal__btn--dismiss" type="button">Quay lại</button>
    </div>
  </form>
</div>
<!-- modal imgmenu -->
<div id="modal-imgmenu" class="zoom-anim-dialog mfp-hide modal">
  <form action="UpdateImgMenu"  method="POST" enctype="multipart/form-data">
    <h6 class="modal__title">Thay đổi ảnh</h6>
    <p class="modal__text">Chọn hình ảnh</p>
    <input type="text" class="form__input" name="maimgmenu" style="display: none" value="<%=InforService.getInformation("ImageMenu").get(0).getId()%>">
    <input type="file" class="form__input" name="img">
    <div class="modal__btns">
      <button class="modal__btn modal__btn--apply" type="submit">Xong</button>
      <button class="modal__btn modal__btn--dismiss" type="button">Quay lại</button>
    </div>
  </form>
</div>
<!-- end modal imgmenu -->
<!-- modal updateImg -->
<% for (int i = 0; i < listInfor.size(); i++){ %>
<div id="modal-slideshow<%=i+1%>" class="zoom-anim-dialog mfp-hide modal">
  <form action="UpdateImgSlide"  method="POST" enctype="multipart/form-data">
    <h6 class="modal__title">Thay đổi ảnh</h6>
    <p class="modal__text">Chọn hình ảnh</p>
    <input type="text" class="form__input" name="matt" style="display: none" value="<%=listInfor.get(i).getId()%>">
    <div class="form__img" style="height: 175px">
      <label for="form__img-upload">Thêm ảnh (270 x 160)</label>
      <input id="form__img-upload" onchange="chooseFile(this)" name="img" type="file" accept=".png, .jpg, .jpeg">
      <img id="form__img" alt=" ">
    </div>
    <div class="modal__btns">
      <input class="modal__btn modal__btn--apply" type="submit" style="background-color: #0d0d0d" value="Xong">
      <button class="modal__btn modal__btn--dismiss" type="button">Quay lại</button>
    </div>
  </form>
</div>
<% } %>
<!-- Back to Top -->
<a href="#" class="btn btn-lg btn-primary btn-lg-square back-to-top"><i class="bi bi-arrow-up"></i></a>
<!-- end modal updateImg -->

<!-- JS -->
<script src="js/jquery-3.5.1.min.js"></script>
<script src="js/bootstrap.bundle.min.js"></script>
<script src="js/jquery.magnific-popup.min.js"></script>
<script src="js/jquery.mousewheel.min.js"></script>
<script src="js/jquery.mCustomScrollbar.min.js"></script>
<script src="js/select2.min.js"></script>
<script src="js/admin.js"></script>
<script>
  CKEDITOR.replace('contentintro');
</script>
</body>

</html>