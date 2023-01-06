<%@ page import="vn.edu.hcmuaf.fit.bean.User" %>
<%@ page import="java.util.List" %>
<%@ page import="vn.edu.hcmuaf.fit.model.Receipt" %>
<!DOCTYPE html>
<%@ page contentType="text/html;charsetUTF-8" language="java" pageEncoding="utf-8"%>
<html lang="xzz">
<meta http-equiv="Content-Type" charset="UTF-8">

<head>
  <meta charset="utf-8">
  <title>Admin | Shop Bánh Kem</title>
  <meta content="width=device-width, initial-scale=1.0" name="viewport">
  <meta content="" name="keywords">
  <meta content="" name="description">

  <!-- Favicon -->
  <link href="img/favicon.ico" rel="icon">

  <!-- Google Web Fonts -->
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;600&family=Roboto:wght@500;700&display=swap" rel="stylesheet">

  <!-- Icon Font Stylesheet -->
  <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

  <!-- Libraries Stylesheet -->
  <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
  <link href="lib/tempusdominus/css/tempusdominus-bootstrap-4.min.css" rel="stylesheet" />

  <!-- Customized Bootstrap Stylesheet -->
  <link href="css/bootstrap.min.css" rel="stylesheet">

  <!-- Template Stylesheet -->
  <link href="css/style.css" rel="stylesheet">
</head>

<body>
<% User auth = (User) session.getAttribute("auth");%>
<div class="container-fluid position-relative d-flex p-0">
  <!-- Spinner Start -->
  <div id="spinner" class="show bg-pink position-fixed translate-middle w-100 vh-100 top-50 start-50 d-flex align-items-center justify-content-center">
    <div class="spinner-border text-primary" style="width: 3rem; height: 3rem;" role="status">
      <span class="sr-only">Loading...</span>
    </div>
  </div>
  <!-- Spinner End -->


  <!-- Sidebar Start -->
  <div class="sidebar pe-4 pb-3">
    <nav class="navbar bg-pink navbar-dark">
      <!--  -->
      <div class="d-flex align-items-center ms-4 mb-4">
        <div class="position-relative">
          <i class="fa fa-user icon__user"></i>
          <div class="bg-success rounded-circle border border-2 border-white position-absolute end-0 bottom-0 p-1"></div>
        </div>
        <div class="ms-3">
          <h6 class="mb-0"><%= auth != null ? auth.getTentk():"ADMIN"%></h6>
          <span>Admin</span>
        </div>
      </div>
      <div class="navbar-nav w-100">
        <a href="./ListReceipt_Admin" class="nav-item nav-link "><i class="fa fa-tachometer-alt me-2"></i>Tổng quan</a>
        <a href="./ListProduct_Admin" class="nav-item nav-link"><i class="fa fa-th me-2"></i>DS Sản Phẩm</a>
        <a href="./ListCustomer" class="nav-item nav-link"><i class="fa fa-th me-2"></i>DS Khách Hàng</a>
        <a href="./ListBlog-admin" class="nav-item nav-link"><i class="fa fa-th me-2"></i>DS Tin Tức</a>
        <a href="./ListReceipt_full_Admin" class="nav-item nav-link active"><i class="fa fa-th me-2"></i>DS Đơn Hàng</a>
        <a href="add-product.jsp" class="nav-item nav-link"><i class="fa fa-birthday-cake me-2"></i>Thêm Sản Phẩm</a>
        <a href="add-blog.jsp" class="nav-item nav-link"><i class="fa fa-blog me-2"></i>Thêm Tin Tức</a>
        <a href="../Index" class="nav-item nav-link"><i class="fa fa-arrow-alt-circle-right me-2"></i>Về trang chủ</a>
      </div>
    </nav>
  </div>
  <!-- Sidebar End -->


  <!-- Content Start -->
  <div class="content">
    <!-- Navbar Start -->
    <nav class="navbar navbar-expand bg-pink navbar-dark sticky-top px-4 py-0">
      <a href="admin-web.jsp" class="navbar-brand d-flex d-lg-none me-4">
        <h2 class="text-primary mb-0"><i class="fa fa-user-edit"></i></h2>
      </a>
      <a href="#" class="sidebar-toggler flex-shrink-0">
        <i class="fa fa-bars"></i>
      </a>
      <form class="d-none d-md-flex ms-4">
        <input class="form-control bg-pink " type="search" placeholder="Tìm Kiếm">
      </form>
      <div class="navbar-nav align-items-center ms-auto">

        <div class="nav-item dropdown">
          <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">
            <i class="fa fa-bell me-lg-2"></i>
            <span class="d-none d-lg-inline-flex">Thông báo</span>
          </a>
          <div class="dropdown-menu dropdown-menu-end bg-pink border-0 rounded-0 rounded-bottom m-0">
            <a href="#" class="dropdown-item">
              <h6 class="fw-normal mb-0">Cập nhật thông tin</h6>
              <small>15 phút trước</small>
            </a>
            <hr class="dropdown-divider">
            <a href="#" class="dropdown-item">
              <h6 class="fw-normal mb-0">Thêm người dùng mới</h6>
              <small>15 phút trước</small>
            </a>
            <hr class="dropdown-divider">
            <a href="#" class="dropdown-item">
              <h6 class="fw-normal mb-0">Đổi mật khẩu</h6>
              <small>15 phút trước</small>
            </a>
            <hr class="dropdown-divider">
            <a href="#" class="dropdown-item text-center">Xem tất cả</a>
          </div>
        </div>
        <div class="nav-item dropdown">
          <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">
            <i class="fa fa-user icon__user__small"></i>
            <span class="d-none d-lg-inline-flex"><%= auth != null ? auth.getTentk():"Đăng nhập"%></span>
          </a>
          <%if(auth != null) {%>
          <div class="dropdown-menu dropdown-menu-end bg-pink border-0 rounded-0 rounded-bottom m-0">
                      <a href="#" class="dropdown-item">Thông tin của tôi</a>
                      <a href="#" class="dropdown-item">Cài đặt</a>
                      <a href="/BanBanhKemSinhNhatWebProject/doSignOut" method="get" class="dropdown-item">Đăng xuất</a>
          </div>
          <%}%>
        </div>
      </div>
    </nav>
    <!-- Navbar End -->


    <!-- Sale & Revenue Start -->
    <div class="container-fluid pt-4 px-4">
      <div class="row g-4">

        <div class="col-sm-6 col-xl-3">
          <div class="bg-pink rounded d-flex align-items-center justify-content-between p-4">
            <i class="fa fa-chart-bar fa-3x text-primary"></i>
            <div class="ms-3">
              <p class="mb-2">Tất cả đơn hàng</p>
              <h6 class="mb-0">123 </h6>
            </div>
          </div>
        </div>

        <div class="col-sm-6 col-xl-3">
          <div class="bg-pink rounded d-flex align-items-center justify-content-between p-4">
            <i class="fa fa-chart-area fa-3x text-primary"></i>
            <div class="ms-3">
              <p class="mb-2">Sản phẩm bán được</p>
              <h6 class="mb-0">100 </h6>
            </div>
          </div>
        </div>
        <div class="col-sm-6 col-xl-3">
          <div class="bg-pink rounded d-flex align-items-center justify-content-between p-4">
            <i class="fa fa-chart-line fa-3x text-primary"></i>
            <div class="ms-3">
              <p class="mb-2">Doanh thu hôm nay</p>
              <h6 class="mb-0">1.234.000 </h6>
            </div>
          </div>
        </div>
        <div class="col-sm-6 col-xl-3">
          <div class="bg-pink rounded d-flex align-items-center justify-content-between p-4">
            <i class="fa fa-chart-pie fa-3x text-primary"></i>
            <div class="ms-3">
              <p class="mb-2">Doanh thu tháng này</p>
              <h6 class="mb-0">4.234.000 </h6>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- Sale & Revenue End -->

    <!-- Recent Sales Start -->
    <div class="container-fluid pt-4 px-4">
      <div class="bg-pink text-center rounded p-4">
        <div class="d-flex align-items-center justify-content-between mb-4">
          <h6 class="mb-0">Tất cả hóa đơn</h6>
        </div>
        <div class="table-responsive">
          <table class="table text-start align-middle table-bordered table-hover mb-0">
            <thead>
            <tr class="text-black">
              <th scope="col">STT</th>
              <th scope="col">Ngày đặt hàng</th>
              <th scope="col">Khách hàng</th>
              <th scope="col">Địa chỉ</th>
              <th scope="col">Thành tiền</th>
              <th scope="col">Trạng thái đơn hàng</th>
              <th scope="col">Xem chi tiết</th>
            </tr>
            </thead>
            <% List<Receipt> receiptList = (List<Receipt>) request.getAttribute("listreceipt-full");
              for(int i = 0; i < receiptList.size(); i++){
                Receipt rc = receiptList.get(i);%>
            <tbody>
            <tr>
              <td><%=i+1%></td>
              <td><%=rc.getSdate()%></td>
              <td><%=rc.getNamecustomer()%></td>
              <td><%=rc.getAddress()%></td>
              <td><%=rc.formatNum(rc.getTotal())%> VND</td>
              <td><select id="value" onclick="changeIcon()">
                <option  value="value01">Chờ xác nhận</option>
                <option  value="value02">Đang chuẩn bị</option>
                <option  value="value03">Đang vận chuyển</option>
                <option  value="value04">Giao hàng thành công</option>
              </select> &ensp; <a class="main__table-btn main__table-btn--edit">
                  <i id="icon1" class="fa fa-envelope-open"></i>
                  <i id="icon2" class="fa fa-cube"></i>
                  <i id="icon3"  class="fa fa-bus"></i>
                  <i id="icon4"  class="fa fa-check-circle"></i>
              </a>
              </td>
              <td> <div class="main__table-btns text-center">
                <a href="cthd_Admin?mahd=<%=rc.getId()%>" class=" main__table-btn main__table-btn--edit">
                  <i class="fa fa-eye"></i>
                </a>
              </div></td>
            </tr>
            </tbody>
              <% } %>
          </table>
        </div>
      </div>
    </div>
    <!-- Recent Sales End -->


    <!-- Widgets Start -->

    <!-- Widgets End -->


    <!-- Footer Start -->
    <div class="container-fluid pt-4 px-4">
      <div class="bg-pink rounded-top p-4">
        <div class="row">
          <div class="col-12 col-sm-6 text-center text-sm-start">
            &copy; <a href="#">Lập trình web thầy Phan Đình Long  Khoa CNTT - ĐH Nông Lâm TP HCM</a>
          </div>
          <div class="col-12 col-sm-6 text-center text-sm-end">
            <a href="https://htmlcodex.com">Nhóm 27</a>
          </div>
        </div>
      </div>
    </div>
    <!-- Footer End -->
  </div>
  <!-- Content End -->


  <!-- Back to Top -->
  <a href="#" class="btn btn-lg btn-primary btn-lg-square back-to-top"><i class="bi bi-arrow-up"></i></a>
</div>
<script>
  <% for(int i = 0; i < receiptList.size(); i++){
    Receipt rct = receiptList.get(i);%>
    function changeIcon() {
    var x = document.getElementById('value');
    var y1 = document.getElementById('icon1');
    var y2 = document.getElementById('icon2');
    var y3 = document.getElementById('icon3');
    var y4 = document.getElementById('icon4');

    if (x.value === 'value01') {
      y1.style.color = '#0edc29';
      y2.style.color = '#a49a9d';
      y3.style.color = '#a49a9d';
      y4.style.color = '#a49a9d';
    } else if (x.value === 'value02') {
      y2.style.color = '#0edc29';
      y1.style.color = '#a49a9d';
      y3.style.color = '#a49a9d';
      y4.style.color = '#a49a9d';
    } else if (x.value === 'value03') {
      y3.style.color = '#0edc29';
      y2.style.color = '#a49a9d';
      y1.style.color = '#a49a9d';
      y4.style.color = '#a49a9d';
    } else {
      y4.style.color = '#0edc29';
      y2.style.color = '#a49a9d';
      y3.style.color = '#a49a9d';
      y1.style.color = '#a49a9d';
    }
  }
  <% } %>
  </script>
<!-- JavaScript Libraries -->
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="lib/chart/chart.min.js"></script>
<script src="lib/easing/easing.min.js"></script>
<script src="lib/waypoints/waypoints.min.js"></script>
<script src="lib/owlcarousel/owl.carousel.min.js"></script>
<script src="lib/tempusdominus/js/moment.min.js"></script>
<script src="lib/tempusdominus/js/moment-timezone.min.js"></script>
<script src="lib/tempusdominus/js/tempusdominus-bootstrap-4.min.js"></script>

<!-- Template Javascript -->
<script src="./js/main.js"></script>
</body>

</html>