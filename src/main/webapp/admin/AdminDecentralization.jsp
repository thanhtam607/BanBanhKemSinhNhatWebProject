<%--
  Created by IntelliJ IDEA.
  User: maith
  Date: 4/6/2023
  Time: 11:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="vn.edu.hcmuaf.fit.service.InforService" %>
<%@ page import="vn.edu.hcmuaf.fit.bean.User" %>
<%@ page import="java.util.List" %>
<%@ page import="vn.edu.hcmuaf.fit.service.UserService" %>
<%@ page import="java.net.URLDecoder" %>
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

  <!-- Google Web Fonts -->
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;600&family=Roboto:wght@500;700&display=swap" rel="stylesheet">

  <!-- Favicons -->
  <link rel="icon" type="image/png" href="icon/favicon-32x32.png" sizes="32x32">
  <link rel="apple-touch-icon" href="icon/favicon-32x32.png">

  <!-- boostrap -->
  <link href="css/bootstrap.min.css" rel="stylesheet">
  <%--    css --%>
  <link rel="stylesheet" href="css/admin.css">
  <!-- index css -->
  <link rel="stylesheet" href="./css/style.css">

  <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
  <link href="lib/tempusdominus/css/tempusdominus-bootstrap-4.min.css" rel="stylesheet"/>


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
    <a href="ListReceipt_Admin" class="header__logo">
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
        <h6 class="mb-0"><%= auth != null ? auth.getName() : "ADMIN"%>
        </h6>
        <span><%= auth != null ? URLDecoder.decode(auth.getRoleName(), "UTF-8") : "Admin"%></span>
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
      <a href="../Index" class="nav-item nav-link"><i class="fa fa-arrow-alt-circle-right me-2"></i>Về trang
        chủ</a>
      <!--  -->
    </div>
  </nav>
</div>
<!-- Sidebar End -->

<!-- main content -->
<main class="main bg-white">
  <div class="container-fluid bg-white">
    <!-- main title -->
    <div class="col-12">
      <div class="main__title">
        <h2>Quản lý nhân viên</h2>
      </div>
    </div>
    <div class="tab-content">
      <div class="tab-pane fade show active" role="tabpanel">
        <div class="col-12">
          <div class="row">
            <!-- details form -->
            <% List<User> listuser = (List<User>) UserService.getListEmployee();
              for(int i = 0; i < listuser.size(); i++){
                User u = listuser.get(i);%>
            <div class="col-12 col-lg-6 ">
              <form method="post" action="UpdateAuthority">
                <div class="row row--form form">
                  <h4 class="form__title text-pink font-size-30 text-center">Thông tin nhân viên</h4>
                  <div class="col-12 col-md-12 col-lg-12 col-xl-12">
                    <div class="form__group">
                    </div>
                  </div>
                  <div class="col-12 col-md-12 col-lg-12 col-xl-12">
                    <div class="form__group">
                      <input name="idnv<%=i%>" value="<%=u.getId()%>" style="display: none">
                      <p>Tên nhân viên:<span>&nbsp;&nbsp;&nbsp;</span><span class = "text--green font-size-20 text-uppercase"><%=u.getName()%></span></p>
                      <%--                      <p>Tên nhân viên:<span>&nbsp;&nbsp;&nbsp;</span><span class = "text--green font-size-20 text-uppercase">ssssssss</span></p>--%>
                    </div>
                  </div>
                  <div class="col-12 col-md-12 col-lg-12 col-xl-12">
                    <div class="form__group">
                      <p>Địa chỉ Email:<span>&nbsp;&nbsp;&nbsp;</span> <span class = "text--green font-size-20"><%=u.getEmail()%></span></p>
                      <%--                      <p>Địa chỉ Email:<span>&nbsp;&nbsp;&nbsp;</span> <span class = "text--green font-size-20">aaaaaaaaaa</span></p>--%>
                    </div>
                  </div>
                  <div class="col-12 col-md-12 col-lg-12 col-xl-12">
                    <div class="form__group">
                      <p>Địa chỉ:<span>&nbsp;&nbsp;&nbsp;</span><span class = "text--green font-size-20">TP HCM</span></p>
                    </div>
                  </div>
                  <div class="col-12 col-md-12 col-lg-12 col-xl-12">
                    <div class="form__group">
                      <label class="form__label" >Quyền hạn: <span class="font-size-20 text--green text-uppercase"><%=u.getRoleName()%></span></label>
                      <% if(u.getIsadd() == 1) {%>
                      <input name="isadd<%=i%>" value="1" style="display: none">
                      <input class="form-check-input" type = "checkbox" checked = "true" name = "add<%=i%>">
                      <% } else { %>
                      <input name="isadd<%=i%>" value="0" style="display: none">
                      <input class="form-check-input" type = "checkbox" name = "add<%=i%>">
                      <% } %>
                      <label>Thêm</label>&ensp;
                      <% if(u.getIsedit() == 1) {%>
                      <input name="isedit<%=i%>" value="1" style="display: none">
                      <input class="form-check-input" type = "checkbox" checked = "true" name = "edit<%=i%>">
                      <% } else { %>
                      <input name="isedit<%=i%>" value="0" style="display: none">
                      <input class="form-check-input" type = "checkbox" name = "edit<%=i%>">
                      <% } %>
                      <label>Sửa</label>&ensp;
                      <% if(u.getIsdelete() == 1) {%>
                      <input name="isdelete<%=i%>" value="1" style="display: none">
                      <input class="form-check-input" type = "checkbox" checked = "true" name = "delete<%=i%>">
                      <% } else { %>
                      <input name="isdelete<%=i%>" value="0" style="display: none">
                      <input class="form-check-input" type = "checkbox" name = "delete<%=i%>">
                      <% } %>
                      <label>Xóa</label>&ensp;
                    </div>
                  </div>
                  <div class="col-6">
                    <input class="form__btn" type="submit" value="Lưu thông tin">
                  </div>
                </div>
              </form>
            </div>
            <% } %>
            <%--                <div class="col-6">--%>
            <%--                  <input class="form__btn" type="submit" value="Lưu thông tin">--%>
            <%--                </div>--%>
          </div>
        </div>
      </div>
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
<script>
  <% for(int i = 0; i < listuser.size(); i++){%>
  document.querySelector('input[name="add<%=i%>"]').addEventListener('change', function(event) {
    if (event.target.checked) {
      document.querySelector('input[name="isadd<%=i%>"]').value = 1;
    } else {
      document.querySelector('input[name="isadd<%=i%>"]').value = 0;
    }
  })
  document.querySelector('input[name="edit<%=i%>"]').addEventListener('change', function(event) {
    if (event.target.checked) {
      document.querySelector('input[name="isedit<%=i%>"]').value = 1;
    } else {
      document.querySelector('input[name="isedit<%=i%>"]').value = 0;
    }
  })
  document.querySelector('input[name="delete<%=i%>"]').addEventListener('change', function(event) {
    if (event.target.checked) {
      document.querySelector('input[name="isdelete<%=i%>"]').value = 1;
    } else {
      document.querySelector('input[name="isdelete<%=i%>"]').value = 0;
    }
  })
  <% } %>
</script>
</body>

</html>
