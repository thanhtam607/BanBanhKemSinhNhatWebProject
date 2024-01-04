<%@ page import="vn.edu.hcmuaf.fit.model.Customer" %>
<%@ page import="vn.edu.hcmuaf.fit.bean.User" %>
<%@ page import="vn.edu.hcmuaf.fit.service.InforService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<% User auth = (User) session.getAttribute("auth");%>

<!-- rang cua -->
<div class="rang_cua"></div>
<!-- rang cua -->
<%--header-top--%>
<div class="header__top">
  <div class="container">
    <div class="row">
      <div class="col-lg-6 col-md-6">
        <div class="header__top__left">
          <ul>
            <li><i class="fa fa-envelope"></i>tiembanhhanhphuc@gmail.com</li>
            <li>Miễn phí giao hàng nội thành TP.HCM</li>
          </ul>
        </div>
      </div>
      <div class="col-lg-6 col-md-6">
        <div class="header__top__right">
          <div class="header__top__right__social">
            <a href="" target="blank"><i class="fa fa-facebook"></i></a>
            <a href="" target="blank"><i class="fa fa-comment"></i></a>
            <a href="" target="blank"><i class="fa fa-instagram"></i></a>
          </div>
          <div class="header__top__right__auth">
            <a href="<%=auth == null ?"signin.jsp":""%>"><i class="fa fa-user"></i></i><%= auth != null ? auth.getName():"Đăng nhập"%></a>
            <% if(auth != null) { %>
            <div class="header__top__right__auth__dropdown">
              <a href="MyOrder" method="get" class="dropdown-item">Đơn hàng của tôi</a>
              <a href="EditUserProfile" method="get" class="dropdown-item">Hồ sơ của tôi</a>
              <a onclick="checkPass('<%=auth.getEmail()%>','<%=auth.getPass()%>')" class="dropdown-item">Đặt lại mật khẩu</a>
              <% if(auth.checkRole()) { %>
              <a href="admin/ListReceipt_Admin" class="dropdown-item">Vào trang quản lí</a>
              <%}%>
              <a href="doSignOut" method="get" class="dropdown-item">Đăng xuất</a>
            </div>
            <%}%>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
<%--header-top--%>
