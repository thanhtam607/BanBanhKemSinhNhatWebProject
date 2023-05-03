<%@ page import="vn.edu.hcmuaf.fit.bean.User" %>
<%@ page import="java.util.List" %>


<%@ page import="vn.edu.hcmuaf.fit.service.InforService" %>
<%@ page import="vn.edu.hcmuaf.fit.model.Log" %>

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
        <span><%= auth != null ? auth.getRoleName():"Admin"%></span>
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
          <h2 class="col-12">Nhật ký hoạt động</h2>

          </div>



      </div>
      <!-- content tabs -->

      <div class="col-12">

        <div class="row">
          <div class="button_right">
            <a class="button_product" onclick="removeLog()"> Xóa nhật ký</a>
            </div>

            <div class="form form--profile">
              <div class="row row--form">
                <div class="col-12">
                  <table class="table table-striped log">
                    <div class="choose-all">
                      <div>
                        <input class="form-check-input" type="checkbox"  id="select-all" > Chọn tất cả</div>
                    </div>
                    <thead>
                    <tr>
                      <th scope="col">Thời gian
                        <div class="filter col-12" id="filter__time__log">
                          <span class="filter__item-label"></span>
                          <div class="filter__item-btn dropdown-toggle col-12" role="navigation" id="filter-time-log" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <input type="button" id="time" value="Tất cả">
                          </div> <div class="error" id="error"></div>
                          <ul class=" dropdown-menu scrollbar-dropdown" aria-labelledby="filter-sort">
                            <li><a class="dropdown-item" onclick="filterLog('time',0)" selected="true" value="0">Tất cả</a></li>
                            <li><a class ="dropdown-item" onclick="filterLog('time',1)" value="1">Từ ngày...đến ngày</a></li>
                            <li><a class="dropdown-item" onclick="filterLog('time',2)"  value="2">Theo ngày</a></li>
                          </ul>
                          <input type="hidden" id="from" value=0>
                          <input type="hidden"  id="to" value=0>
                          <input type="hidden"  id="date" value=0>
<%--                            <div class="dropdown-menu p-4" id="filter_time">--%>
<%--                              <div class="form-group">--%>
<%--                                <div>--%>
<%--                                  <input class="form-check-input" type="checkbox"  checked="true" id="all" > Tất cả--%>
<%--                                </div>--%>

<%--                                <label for="from">Từ ngày</label>--%>

<%--                              </div>--%>
<%--                              <div class="form-group">--%>
<%--                                <label for="to">Đến ngày</label>--%>

<%--                              </div>--%>
<%--
<%--                              <button class="button_product right col-6"  onclick="filterLog('time','')">Lọc</button>--%>
<%--                            </div>--%>
                          </div>
                        </th>
                      <th class="level-log" scope="col">Level
                        <div class="filter col-12" id="filter__level__log">
                          <span class="filter__item-label"></span>
                          <div class="filter__item-btn dropdown-toggle col-12" role="navigation" id="filter-level-log" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <input type="button"  value="Tất cả">

                          </div><input type="hidden" id="level" value="0">
                          <ul class="filter__item-menu list-group dropdown-menu scrollbar-dropdown" aria-labelledby="filter-sort">
                            <li><a class="dropdown-item" onclick="filterLog('level',0)" selected="true" value="0">Tất cả</a></li>
                            <li><a class ="text-success dropdown-item" onclick="filterLog('level',1)" value="1">Bình thường</a></li>
                            <li><a class ="text-warning dropdown-item" onclick="filterLog('level',2)" value="2">Cảnh báo</a></li>
                            <li><a class ="text-danger dropdown-item" onclick="filterLog('level',3)" value="3">Nguy hiểm</a></li>

                          </ul>
                        </div>
                      </th>
                      <th scope="col">Người dùng
                        <div class="filter col-12" id="filter__user__log">
                          <span class="filter__item-label"></span>
                          <div class="filter__item-btn dropdown-toggle" role="navigation" id="filter-user-log" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <input type="button" id="user" value="Tất cả">
                          </div>
                          <ul class="filter__item-menu list-group dropdown-menu scrollbar-dropdown" aria-labelledby="filter-sort">
                          <%List<String> listUser = (List<String>) request.getAttribute("listUser");
                          for(String s: listUser){%>
                            <li><a class ="dropdown-item" onclick="filterLog('user','<%=s%>')" ><%=s%></a></li>
                          <%}%>
                          </ul>
                        </div>
                      </th>
                      <th scope="col">Hoạt động
                        <div class="filter col-12" id="filter__content__log">
                          <span class="filter__item-label"></span>
                          <div class="filter__item-btn dropdown-toggle" role="navigation" id="filter-content-log" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <input type="button" id="content" value="Tất cả">
                          </div>
                          <ul class="filter__item-menu list-group dropdown-menu scrollbar-dropdown" aria-labelledby="filter-sort">
                            <%List<String> listContent = (List<String>) request.getAttribute("listContent");
                              for(String s: listContent){%>
                            <li><a class ="dropdown-item" onclick="filterLog('content','<%=s%>')" value="<%=s%>"><%=s%></a></li>
                            <%}%>
                          </ul>
                        </div>
                      </th>
                      <th class="bottom" scope="col">Source</th>
                      <th class="bottom" scope="col">Chọn</th>
                    </tr>
                    </thead>
                    <tbody id="listLog">
                    <% List<Log> listLog = (List<Log>) request.getAttribute("listPa");
                    for(Log log: listLog){%>
                    <tr >
                      <td><%=log.getCreateAt()%></td>
                      <td class="level-log"><%if(log.getLevel()==1){%>
                        <i class="bi bi-check-circle-fill text-success"></i>
                      <%}else if(log.getLevel()==2){%>
                        <i class="fas fa-exclamation-triangle text-warning"></i>
                        <%}else%> <i class="fas fa-skull-crossbones text-danger"></i>
                      </td>
                      <td><%=log.getUser()%></td>
                      <td><%=log.getContent()%></td>
                      <td><%=log.getSrc()%></td>
                      <td class="check" >
                        <input class="form-check-input" type="checkbox" name="check" onclick="cancelSelectAll()" value="<%=log.getId()%>"></td>
                      <td>
                    </tr>
                    <%}%>

                    </tbody>
                  </table>
                  <!-- paginator -->
                  <div class="col-12">
                    <div class="paginator-wrap wrap col-12" >

                      <ul class="paginator" id="paginator">
                        <% int tag =1;%>
                        <input type="number" id="pageNumber" style="display: none" value="<%=tag%>">
                        <li class="paginator__item paginator__item--prev">
                          <a onclick="filterLog('page',<%=tag-1%>)"><i class="fa fa-chevron-left"></i></a>
                        </li>
                        <%
                          for (int i = tag - 1; i <= tag + 2; i++) {
                            if (i < 1) {
                              continue;
                            }
                            if (i == tag) {%>
                        <li class="paginator__item paginator__item--active"><a onclick="filterLog('page',<%=i%>)">
                          <%=tag%></a></li>
                        <%} else {%>
                        <li class="paginator__item"><a onclick="filterLog('page', <%=i%>)"><%=i%></a></li>
                        <%}
                        }%>
                        <li class="paginator__item paginator__item--next">
                          <a onclick="filterLog('page', <%=tag+1%>)"><i class="fa fa-chevron-right"></i></a>
                        </li>
                      </ul>
                    </div>
                  </div>
                  <!-- end paginator -->
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

<!-- JS -->
<script src="js/jquery-3.5.1.min.js"></script>
<script src="js/bootstrap.bundle.min.js"></script>
<script src="js/jquery.magnific-popup.min.js"></script>
<script src="js/jquery.mousewheel.min.js"></script>
<script src="js/jquery.mCustomScrollbar.min.js"></script>
<script src="js/select2.min.js"></script>
<script src="js/admin.js"></script>
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>

</body>

</html>