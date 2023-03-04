
<%@ page import="java.util.List" %>
<%@ page import="vn.edu.hcmuaf.fit.model.Customer" %>
<%@ page import="vn.edu.hcmuaf.fit.model.Blog" %>
<%@ page import="java.util.List" %>
<%@ page import="vn.edu.hcmuaf.fit.model.Receipt" %>
<%@ page import="vn.edu.hcmuaf.fit.model.Bill_Detail" %>
<%@ page import="vn.edu.hcmuaf.fit.bean.User" %>
<%@ page import="vn.edu.hcmuaf.fit.service.ReceiptService" %>
<%@ page import="vn.edu.hcmuaf.fit.model.Bill_Detail" %>
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
<% User auth = (User) session.getAttribute("auth");%>
<!-- header -->
<header class="header">
  <div class="header__content">
    <!-- header logo -->
    <a href="ListReceipt_Admin" class="header__logo">
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
        <h6 class="mb-0"><%= auth != null ? auth.getAccount_name():"ADMIN"%></h6>
        <span><%= auth != null ? auth.getRoleName():"Admin"%></span>
      </div>
    </div>
    <div class="navbar-nav w-100">
      <a href="./ListReceipt_Admin" class="nav-item nav-link "><i class="fa fa-tachometer-alt me-2"></i>Tổng quan</a>
      <a href="./ListProduct_Admin" class="nav-item nav-link"><i class="fa fa-th me-2"></i>DS Sản Phẩm</a>
      <a href="./ListCustomer" class="nav-item nav-link"><i class="fa fa-th me-2"></i>DS Khách Hàng</a>
      <a href="./ListBlog-admin" class="nav-item nav-link"><i class="fa fa-th me-2"></i>DS Tin Tức</a>
      <a href="./ListReceipt_full_Admin" class="nav-item nav-link"><i class="fa fa-th me-2"></i>DS Đơn Hàng</a>
      <a href="add-product.jsp" class="nav-item nav-link"><i class="fa fa-birthday-cake me-2"></i>Thêm Sản Phẩm</a>
      <a href="add-blog.jsp" class="nav-item nav-link"><i class="fa fa-blog me-2"></i>Thêm Tin Tức</a>
      <a href="feedbacks.jsp" class="nav-item nav-link"><i class="fa fa-th me-2"></i>DS Đánh giá</a>
      <a href="../Index" class="nav-item nav-link"><i class="fa fa-arrow-alt-circle-right me-2"></i>Về trang chủ</a>
      <!--  -->
    </div>
  </nav>
</div>
<!-- Sidebar End -->

<!-- main content -->
<% Receipt receipt = (Receipt) request.getAttribute("receipt");
  List<Bill_Detail> receiptsDt = (List<Bill_Detail>) request.getAttribute("listcthdOfKH");
  String tenkh =(String) request.getAttribute("tenkh");%>
<main class="main bg-white">
  <div class="container-fluid bg-white">
      <!-- main title -->
    <div class="col-12">
        <div class="main__title">
          <h2>Chỉnh sửa đơn hàng</h2>
        </div>
      </div>
<%--    <div class="col-12 pt-2 pb-2 rounded bg-pink">--%>
<%--        <h5 style="margin-bottom: 25px">Tên KH: <%=tenkh%> - SĐT: <%=receipt.getPhone()%></h5>--%>
<%--            <div class="col-12 d-flex form__content pl-0 pr-0">--%>
<%--              <div class="col-6 pl-0 pr-0">--%>
<%--                <p>Ngày Lập: <%=receipt.getExport_date()%></p>--%>
<%--                <p>Ngày Giao Hàng: <%=receipt.getDelivery_date()%></p>--%>
<%--              </div>--%>
<%--              <div class="col-6 pl-0 pr-0">--%>
<%--                <p>Địa Chỉ Giao: <%=receipt.getAddress()%></p>--%>
<%--                <p>Trạng Thái: <%=receipt.getStatusName()%></p>--%>
<%--              </div>--%>
<%--            </div>--%>
<%--        <p>Ghi Chú Chung: <%=receipt.getNote()%></p>--%>
<%--          <div class="table-responsive margin-top-20px col-12 pl-0">--%>
<%--            <table class="table text-start align-middle table-bordered table-hover mb-0">--%>
<%--              <thead>--%>
<%--              <tr class="text-black">--%>
<%--                <th scope="col">STT</th>--%>
<%--                <th scope="col">Tên sản phẩm</th>--%>
<%--                <th scope="col">Ghi chú</th>--%>
<%--                <th scope="col">Số lượng</th>--%>
<%--                <th scope="col">Đơn giá</th>--%>
<%--                <th scope="col">Tổng</th>--%>
<%--              </tr>--%>
<%--              </thead>--%>
<%--              <% for (int i = 0; i < receiptsDt.size(); i++){--%>
<%--                Bill_Detail rcs = receiptsDt.get(i);%>--%>
<%--              <tbody>--%>
<%--              <tr>--%>
<%--                <td><%=i + 1%></td>--%>
<%--                <td><%=rcs.getNamePro()%></td>--%>
<%--                <td><%=rcs.getNote()%></td>--%>
<%--                <td><%=rcs.getSolg()%></td>--%>
<%--                <td><%=rcs.formatNum(rcs.getPrice())%></td>--%>
<%--                <td><%=rcs.formatNum(rcs.getToTalPrice())%></td>--%>
<%--              </tr>--%>
<%--              </tbody>--%>
<%--              <% } %>--%>
<%--            </table>--%>
<%--          </div>--%>

<%--          <div class="col-5 margin-top-20px">--%>
<%--            <i class="fa fa-money"></i> <label for="total">Tổng tiền: </label> &ensp;--%>
<%--            <span class="text-danger text-uppercase text-pink" id="total"><%=receipt.formatNum(receipt.getTotal())%> VND</span>--%>
<%--          </div>--%>
<%--          <div class="main__table-btns">--%>
<%--        <div class="col-5">--%>
<%--          <a href="#" type="button" class="form__btn">In hóa đơn</a>--%>
<%--        </div>--%>
<%--      </div>--%>
<%--    </div>--%>
    <!-- content tabs -->
    <div class="tab-content bg-pink" id="myTabContent">
      <div class="tab-pane fade show active" id="tab-1" role="tabpanel" aria-labelledby="1-tab">
        <div class="col-12">
          <div class="row">
            <!-- details form -->
            <div class="col-12">
              <form action="UpdateRole" method="post" class="form form--profile">
                <div class="row row--form">
                  <div class="col-12">
                    <h4 class="form__title">Thông tin tài khoản</h4>
                  </div>
                  <input name = "makh" value="kh01" style="display: none">
                  <div class="col-12 col-md-6 col-lg-12 col-xl-6">
                    <div class="form__group">
                      <label class="form__label" for="username">Tên tài khoản</label>
                      <input id="username" type="text" name="username" class="form__input" value="thanh">
                    </div>
                  </div>

                  <div class="col-12 col-md-6 col-lg-12 col-xl-6">
                    <div class="form__group">
                      <label class="form__label" for="email">Email</label>
                      <input id="email" type="text" name="email" class="form__input text-lowercase" value="@gmail">
                    </div>
                  </div>
                  <div class="col-12 col-md-6 col-lg-12 col-xl-6">
                    <div class="form__group">
                      <label class="form__label" for="rights">Quyền Hạn</label>
                      <select class="form__input" id="rights" name="role">
                        <%List<String> listStatus = (List<String>) request.getAttribute("listStatus");
                          for(String r : listStatus){
                            if(r == receipt.getStatusName()){%>
                        <option selected value="<%=r%>"><%=r%></option>
                        <% } else {%>
                        <option value="<%=r%>"><%=r%></option>
                        <%}}%>
                      </select>
                    </div>
                  </div>
                  <div class="col-12">
                    <input id="" class="form__btn" type="submit"  value="Lưu thông tin">
                  </div>
                  <% }} %>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>



      </div>


      </div>
    </div>
    <!-- end content tabs -->
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