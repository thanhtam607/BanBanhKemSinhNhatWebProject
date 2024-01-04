<%@ page import="vn.edu.hcmuaf.fit.bean.User" %>
<%@ page import="vn.edu.hcmuaf.fit.model.Product" %>
<%@ page import="vn.edu.hcmuaf.fit.model.TypeOfCake" %>
<%@ page import="java.util.List" %>
<%@ page import="vn.edu.hcmuaf.fit.service.ProductService" %>
<%@ page import="vn.edu.hcmuaf.fit.model.Comment" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="vn.edu.hcmuaf.fit.model.Image"%>
<%@ page import="vn.edu.hcmuaf.fit.service.InforService" %>
<%@ page import="vn.edu.hcmuaf.fit.service.UserService" %>

<%@ page import="java.net.URLDecoder" %>

<!DOCTYPE html>
<%@ page contentType="text/html;charsetUTF-8" language="java" pageEncoding="utf-8"%>
<html lang="xzz">
<meta http-equiv="Content-Type" charset="UTF-8">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

  <!-- Icon Font Stylesheet -->
  <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">
    <link rel="icon" href="../img/favicon.ico" type="image/x-icon" />

  <!-- CSS -->
  <link rel="stylesheet" href="css/bootstrap-reboot.min.css">
  <link rel="stylesheet" href="css/bootstrap-grid.min.css">
  <link rel="stylesheet" href="css/magnific-popup.css">
  <link rel="stylesheet" href="css/jquery.mCustomScrollbar.min.css">
  <link rel="stylesheet" href="css/select2.min.css">
  <link rel="stylesheet" href="css/ionicons.min.css">

    <!-- Google Web Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;600&family=Roboto:wght@500;700&display=swap" rel="stylesheet">

  <%--    <!-- Favicons -->--%>
  <%--    <link rel="icon" type="image/png" href="icon/favicon-32x32.png" sizes="32x32">--%>
  <%--    <link rel="apple-touch-icon" href="icon/favicon-32x32.png">--%>

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

  <title>Admin |  <%=InforService.getInformation("NameShop").get(0).getContent()%></title>

</head>

<body>
<% User auth = (User) session.getAttribute("auth");%>
<jsp:include page="spinner.jsp"></jsp:include>
<!-- header -->
<header class="header">
  <div class="header__content">
    <!-- header logo -->
    <a href="./ListReceipt_Admin" class="header__logo">
      <img src="../img/logo_web.jpg" alt="">
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
            <span><%= auth != null ? URLDecoder.decode(auth.getRoleName(), "UTF-8"):"Admin"%></span>
        </div>

    </div>
      <div class="navbar-nav w-100">
          <a href="./ListReceipt_Admin" class="nav-item nav-link "><i class="fa fa-tachometer-alt me-2"></i>Tổng quan</a>
          <%--                    <a href="general_Management.jsp" class="nav-item nav-link"><i class="fa fa-user"></i>Quản lý chung</a>--%>
          <a href="./ListProduct_Admin" class="nav-item nav-link"><i class="fa fa-th me-2"></i>DS Sản Phẩm</a>

          <a href="./ListCustomer" class="nav-item nav-link"><i class="fa fa-th me-2"></i>DS Khách Hàng</a>
          <a href="./ListBlog-admin" class="nav-item nav-link"><i class="fa fa-th me-2"></i>DS Tin Tức</a>
          <a href="./ListReceipt_full_Admin" class="nav-item nav-link "><i class="fa fa-th me-2"></i>DS Đơn Hàng</a>
          <a href="feedbacks.jsp" class="nav-item nav-link"><i class="fa fa-th me-2"></i>DS Đánh giá</a>

          <a href="catalog_Management.jsp" class="nav-item nav-link"><i class="fa fa-file me-2"></i>QL danh mục</a>
          <a href="List_Discounts" class="nav-item nav-link"><i class="fa fa-birthday-cake me-2"></i>Khuyến mãi</a>
          <a href="../" class="nav-item nav-link"><i class="fa fa-arrow-alt-circle-right me-2"></i>Về trang chủ</a>
          <!--  -->
      </div>
  </nav>
</div>
<!-- Sidebar End -->

<!-- main content -->
<% User user = UserService.findById(auth.getId()); %>
<% if(user.getIsadd() == 1 || user.getRole() == 2) { %>
<main class="main">
  <div class="container-fluid">
    <div class="row">
      <!-- main title -->
      <div class="col-12">
        <div class="main__title">
          <h2>Thêm khuyến mãi</h2>
        </div>
      </div>
      <!-- content tabs -->

      <div class="col-12">
        <div class="row">
          <!-- details form -->
          <div class="col-3">
            <div  class="form form--profile" id="info-product">
              <div class="row row--form">
                <div class="col-12">
                  <h4 class="form__title">Chi tiết khuyến mãi</h4>
                </div>

                <div class="col-10">
                  <div class="form__group">
                    <label class="form__label" >Giảm giá (%)</label>
                    <input  type="number" min="1" max="100" id="giamGia" class="form__input">
                  </div>
                </div>
                <div class="col-12">
                  <div class="form__group">
                    <label class="form__label" for="start">Ngày bắt đầu</label>
                    <input id="start" type="date" id="start" class="form__input" >
                  </div>
                </div>

                <div class="col-12">
                  <div class="form__group">
                    <label class="form__label" for="end">Ngày hết hạn</label>
                    <input id="end" type="date" id="end" class="form__input">
                  </div>
                </div>

<%--                <div class="col-4">--%>

<%--                </div>--%>
<%--              </div>--%>

            </div>
          </div>
              <%String type= (String) request.getAttribute("loaiBanh");
                  int tag = (int) request.getAttribute("tag");%>
          <div class="col-3">

            <button class="form__btn" type="button" onclick="addDiscount()" >Thêm khuyến mãi</button>
          </div>
      </div>
      <!-- end details form -->

      <!-- password form -->
      <div class="col-9">
        <div class="form form--profile">
          <div class="row row--form">
            <div class="col-12">
              <div class="col-12">
                <div class="main__title">
                  <h4 class="form__title">Sản phẩm áp dụng</h4>
                  <div class="main__title-wrap col-9">
                    <!-- filter sort -->
                    <div class="filter" id="filter__sort">
                      <span class="filter__item-label">Lọc sản phẩm:</span>

                      <div class="filter__item-btn dropdown-toggle" role="navigation" id="filter-sort" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <input type="button"   value="Tất cả" id="type">

                      </div>

                      <ul class="filter__item-menu dropdown-menu scrollbar-dropdown" aria-labelledby="filter-sort" onchange="filterProduct(false)">
                        <%List<TypeOfCake> ListType = ProductService.getListType();
                            ListType.add(new TypeOfCake("Tất cả"));

                          for(TypeOfCake lb: ListType){
                            if(lb.equals(type)){
                        %>
                        <li><a class ="text-pink" onclick="filterProduct(false, '<%=lb.getName()%>')" href="#" selected="true" value="<%=lb.getName()%>"><%=lb.getName()%></a></li>
                        <%}
                        else{%>
                        <li><a class ="text-pink" href="#" onclick="filterProduct(false,'<%=lb.getName()%>')" value="<%=lb.getName() %>"><%=lb.getName()%></a></li>
                        <%}}%>
                      </ul>
                    </div>
                    <!-- end filter sort -->

<%--                    <!-- search -->--%>
<%--                    <div  class="col-6 main__title-form">--%>
<%--                      <input type="text" placeholder="Tìm sản phẩm.." onchange="filterProduct('<%=type%>', <%=tag%>)" id="search">--%>
<%--                      <button  type="button">--%>
<%--                        <i class="fa fa-search"></i>--%>
<%--                      </button>--%>
<%--                    </div>--%>
<%--                    <!-- end search -->--%>
                  </div>
                </div>
              </div>
            </div>
              <div class="col-12 col-md-12 col-lg-12 col-xl-12" >
                  <div class="select-all">
                      <div>
                          <input class="form-check-input" type="checkbox" onclick="cancelSelectAll()" id="select-all"> Chọn tất cả</div>
                  </div>

                  <table class="table table-striped">
                      <thead>
                      <tr>
                          <th class="check">Chọn</th>
                          <th scope="col">Tên bánh</th>
                          <th scope="col">Loại bánh</th>
                          <th scope="col">Giá</th>
                      </tr>
                      </thead>

                      <tbody id="product_table">
                      <%
                          List<Product> listPa = (List<Product>) request.getAttribute("listPa");
                          for (Product p: listPa) {%>
                      <tr>
                          <td class="check" >
                              <input class="form-check-input" type="checkbox" name="check" value="<%=p.getId()%>"></td>
                          <td>
                                      <%for(Image img :p.getListImg()){
                                          if(!img.delete()&& !img.isHide()){%>
                                      <img src="../<%=img.getImg()%>" alt="">
                                      <% break;
                                      }
                                      }%>
                                      <a href="Edit_Product?idP=<%=p.getId()%>"><%=p.getName()%></a>
                              </td>
                          <td><%=p.getType()%></td>
                          <td><%=p.getPrice()%></td>
                      </tr>
                      <%}%>
                      </tbody>

                  </table>

                                                        <!-- paginator -->
                  <div class="col-12 show-more" >
                      <input type="number" id="page" style="display: none" value="<%=1%>">
                      <button onclick="filterProduct(true)" >Xem thêm</button>
                  </div>
            <!-- end paginator -->
              </div>


          </div>
        </div>
      </div>
      <!-- end password form -->
      </div>
    </div>
    <!-- end content tabs -->
  </div>
  </div>
</main>
<% } else { %>
<div class="w-100 h-100 ml-5">
    <h1 class="text-pink mt-5 text-center">Bạn không có quyền này</h1>
</div>
<% } %>

<!-- JS -->
<script src="js/jquery-3.5.1.min.js"></script>
<script src="js/bootstrap.bundle.min.js"></script>
<script src="js/jquery.magnific-popup.min.js"></script>
<script src="js/jquery.mousewheel.min.js"></script>
<script src="js/jquery.mCustomScrollbar.min.js"></script>
<script src="js/select2.min.js"></script>
<script src="js/main.js"></script>
<script src="js/admin.js"></script>
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</body>

</html>