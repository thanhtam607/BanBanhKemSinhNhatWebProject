
<%@ page import="java.util.List" %>
<%@ page import="vn.edu.hcmuaf.fit.model.Customer" %>
<%@ page import="vn.edu.hcmuaf.fit.model.Blog" %>
<%@ page import="java.util.List" %>
<%@ page import="vn.edu.hcmuaf.fit.model.Receipt" %>
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
  <link href="css/style.css" rel="stylesheet">


  <!-- Favicons -->
  <link rel="icon" type="image/png" href="icon/favicon-32x32.png" sizes="32x32">
  <link rel="apple-touch-icon" href="icon/favicon-32x32.png">

  <!-- boostrap -->
  <link href="css/bootstrap.min.css" rel="stylesheet">
  <%--    css --%>
  <link rel="stylesheet" href="css/admin.css">
  <!-- index css -->
  <link rel="stylesheet" href="./css/style.css" >

  <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
  <link href="lib/tempusdominus/css/tempusdominus-bootstrap-4.min.css" rel="stylesheet" />


  <meta name="description" content="">
  <meta name="keywords" content="">
  <meta name="author" content="Dmitry Volkov">
  <title>Admin | Shop Bánh Kem</title>
</head>

<body>
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
        <h6 class="mb-0">Thanh Thùy</h6>
        <span>Admin</span>
      </div>
    </div>
    <div class="navbar-nav w-100">
      <a href="./ListReceipt_Admin" class="nav-item nav-link "><i class="fa fa-tachometer-alt me-2"></i>Tổng quan</a>
      <a href="./ListProduct_Admin" class="nav-item nav-link"><i class="fa fa-th me-2"></i>DS Sản Phẩm</a>
      <a href="./ListCustomer" class="nav-item nav-link"><i class="fa fa-th me-2"></i>DS Khách Hàng</a>
      <a href="./ListBlog-admin" class="nav-item nav-link"><i class="fa fa-th me-2"></i>DS Tin Tức</a>
      <a href="./ListReceipt_full_Admin" class="nav-item nav-link"><i class="fa fa-th me-2"></i>DS Hóa Đơn</a>
      <a href="add-product.jsp" class="nav-item nav-link"><i class="fa fa-birthday-cake me-2"></i>Thêm Sản Phẩm</a>
      <a href="add-blog.jsp" class="nav-item nav-link"><i class="fa fa-blog me-2"></i>Thêm Tin Tức</a>
      <a href="../Index" class="nav-item nav-link"><i class="fa fa-arrow-alt-circle-right me-2"></i>Về trang chủ</a>
      <!--  -->
    </div>
  </nav>
</div>
<!-- Sidebar End -->

<!-- main content -->
<% List<Receipt> rcList = (List<Receipt>) request.getAttribute("listmahd");%>
<main class="main bg-white">
  <div class="container-fluid bg-white">
    <form class="row">
      <!-- main title -->
      <div class="col-12">
        <div class="main__title">
          <h2>Chi tiết đơn hàng</h2>
        </div>
      </div>
      <!-- end main title -->

      <!-- users -->
      <!-- end users -->

      <!-- paginator -->
      <div class="col-12">
        <form action="#" class="form">
          <div class="row row--form">
            <div class="col-12 form__content">
              <div class="row row--form margin-top-50px">
                <h4 class="border-bottom">Thông tin khách hàng</h4>
                <% List<Receipt> receipts = (List<Receipt>) request.getAttribute("listmahd");
                  for (int i = 0; i < 1; i++){
                    Receipt rc = receipts.get(i);%>
                <div class="col-12 border-bottom margin-top-50px">
                  <i class="fa fa-user-circle"></i> <label for="name" class="font-size-20">Tên khách hàng: </label> &ensp;<span class="text-uppercase text--green font-size-20" id="name"><%=rc.getNamecustomer()%></span>
                </div><br>
                <div class="col-12 border-bottom margin-top-50px">
                  <i class="fa fa-phone"></i> <label for="phone" class="font-size-20">Số điện thoại: </label> &ensp;<span class="text-uppercase text--green font-size-20" id="phone"><%=rc.getPhone()%></span>
                </div><br>
                <div class="col-12 border-bottom margin-top-50px">
                  <i class="fa fa-home"></i> <label for="address" class="font-size-20">Địa chỉ: </label> &ensp;<span class="text-uppercase text--green font-size-20" id="address"><%=rc.getAddress()%></span>
                </div>
                <% } %>
              </div>
              <h4 class="border-bottom margin-top-50px">Thông tin đặt hàng</h4>
            </div>
          </div>
        </form>
      </div>
      <div class="table-responsive margin-top-20px col-12 margin-right--20px">
        <table class="table text-start align-middle table-bordered table-hover mb-0">
          <thead>
          <tr class="text-black">
            <th scope="col">STT</th>
            <th scope="col">Tên sản phẩm</th>
            <th scope="col">Ngày đặt hàng</th>
            <th scope="col">Ngày giao hàng</th>
            <th scope="col">Ghi chú</th>
            <th scope="col">Thành tiền</th>
            <th scope="col">Trạng thái đơn hàng</th>
          </tr>
          </thead>
          <% for (int i = 0; i < receipts.size(); i++){
            Receipt rcs = receipts.get(i);%>
          <tbody>
          <tr>
            <td><%=i + 1%></td>
            <td><%=rcs.getNamecake()%></td>
            <td><%=rcs.getSdate()%></td>
            <td><%=rcs.getEdate()%></td>
            <td><%=rcs.getNote()%></td>
            <td><%=rcs.formatNum(rcs.getPrice())%> VND</td>
            <td> <select class="boder-none" id="rights" name="status">
              <%List<String> listStatus = (List<String>) request.getAttribute("listStatus");
                for(int j = 0; j < listStatus.size(); i++){
                  if(rcs.getStatus() == 0){%>
              <option selected value="<%=listStatus.get(j)%>"><%=listStatus.get(j)%></option>
              <% } else { %>
              <option  value="<%=listStatus.get(j)%>"><%=listStatus.get(j)%></option>
              <%}}%>
            </select>
            </td>
          </tr>
          </tbody>
          <% } %>
        </table>
      </div>
      <% int total = 0; %>
      <% for (int i = 0; i < receipts.size(); i++){
        Receipt rcs = receipts.get(i);
        total += rcs.getPrice(); }%>
      <div class="col-5 margin-top-20px">
        <% for (int i = 0; i < 1; i++){
          Receipt rcs = receipts.get(i);%>
        <i class="fa fa-money"></i> <label for="total">Tổng tiền: </label> &ensp;<span class="text-danger text-uppercase text-pink" id="total"><%=rcs.formatNum(total)%> VND</span>
      </div>
      <% } %>
      <div class="col-5">
        <button type="button" class="form__btn">Lưu kết quả</button>
      </div>
    </form>
  </div>
  <!-- end form -->
  </div>
  </div>
</main>
<!-- end modal delete -->

<!-- Back to Top -->
<a href="#" class="btn btn-lg btn-primary btn-lg-square back-to-top"><i class="bi bi-arrow-up"></i></a>

<!-- JS -->
<script src="js/jquery-3.5.1.min.js"></script>
<script src="js/bootstrap.bundle.min.js"></script>
<script src="js/jquery.magnific-popup.min.js"></script>
<script src="js/jquery.mousewheel.min.js"></script>
<script src="js/jquery.mCustomScrollbar.min.js"></script>
<script src="js/select2.min.js"></script>
<script src="js/admin.js"></script>


</body>

</html>