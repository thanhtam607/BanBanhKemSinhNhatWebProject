<%--
  Created by IntelliJ IDEA.
  User: maith
  Date: 2/23/2023
  Time: 7:46 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="vn.edu.hcmuaf.fit.model.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="vn.edu.hcmuaf.fit.model.ProductDetail" %>
<%@ page import="vn.edu.hcmuaf.fit.controller.ListProduct" %>
<%@ page import="vn.edu.hcmuaf.fit.model.TypeOfCake" %>
<%@ page import="vn.edu.hcmuaf.fit.service.ProductService" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="vn.edu.hcmuaf.fit.service.InforService" %>
<%@ page import="vn.edu.hcmuaf.fit.bean.User" %>
<%@ page import="vn.edu.hcmuaf.fit.service.UserService" %>
<%@ page import="java.net.URLDecoder" %>
<html>
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
    <title>Admin |  <%=InforService.getInformation("NameShop").get(0).getContent()%></title>
</head>

<body>
<jsp:include page="spinner.jsp"></jsp:include>
<!-- header -->
<% User auth = (User) session.getAttribute("auth");%>
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
                <span><%= auth != null ? URLDecoder.decode(auth.getRoleName(), "UTF-8"):"Admin"%></span>
            </div>
        </div>
        <div class="navbar-nav w-100">
            <a href="./ListReceipt_Admin" class="nav-item nav-link "><i class="fa fa-tachometer-alt me-2"></i>Tổng quan</a>
            <%--                    <a href="general_Management.jsp" class="nav-item nav-link"><i class="fa fa-user"></i>Quản lý chung</a>--%>
            <a href="./ListProduct_Admin" class="nav-item nav-link"><i class="fa fa-th me-2"></i>DS Sản Phẩm</a>

            <a href="./ListCustomer" class="nav-item nav-link"><i class="fa fa-th me-2"></i>DS Khách Hàng</a>
            <a href="./ListBlog-admin" class="nav-item nav-link "><i class="fa fa-th me-2"></i>DS Tin Tức</a>
            <a href="./ListReceipt_full_Admin" class="nav-item nav-link "><i class="fa fa-th me-2"></i>DS Đơn Hàng</a>
            <a href="feedbacks.jsp" class="nav-item nav-link"><i class="fa fa-th me-2"></i>DS Đánh giá</a>

            <a href="catalog_Management.jsp" class="nav-item nav-link active"><i class="fa fa-file me-2"></i>QL danh mục</a>
            <a href="List_Discounts" class="nav-item nav-link"><i class="fa fa-birthday-cake me-2"></i>Khuyến mãi</a>
            <a href="../Index" class="nav-item nav-link"><i class="fa fa-arrow-alt-circle-right me-2"></i>Về trang chủ</a>
            <!--  -->
        </div>
    </nav>
</div>
<!-- Sidebar End -->

<!-- main content -->
<% List<TypeOfCake> list = ProductService.getListType();%>
<% User user = UserService.findById(auth.getId()); %>
<main class="main bg-white">
    <div class="container-fluid bg-white">
        <div class="row">
            <!-- main title -->
            <div class="col-12">
                <div class="main__title">
                    <h2>Danh mục sản phẩm</h2>
                    <span class="main__title-stat"><%=list.size()%> danh mục</span>
                    <div class="main__title-wrap">
                        <!-- filter sort -->
                        <div class="filter" id="filter__sort">
                            <span class="filter__item-label">Sắp xếp:</span>

                            <div class="filter__item-btn dropdown-toggle" role="navigation" id="filter-sort" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <input type="button" value="">
                                <span></span>
                            </div>
                            <ul class="filter__item-menu dropdown-menu scrollbar-dropdown" aria-labelledby="filter-sort" style="width: 200px">
                                <%List<String> listOption = new ArrayList<String>();
                                    listOption.add("Mặc định");
                                    listOption.add("Theo loại");
                                    String val;
                                    for(String s: listOption){
                                        val = request.getParameter("sortValue");
                                        if(s.equals(val)){%>
                                <li><a class ="text-pink" href="Catalog_admin?sortValue=<%=val%>" selected="true" value="<%=val%>"><%=val%></a></li>
                                <%}
                                else{%>
                                <li><a id="sortValue" class ="text-pink" href="Catalog_admin?sortValue=<%=s%>" value="<%=s%>"><%=s%></a></li>
                                <%}%>
                                <%}%>
                            </ul>
                        </div>
                        <!-- end filter sort -->

                        <!-- search -->
                        <form action="#" class="main__title-form">
                            <input type="text" placeholder="Tìm sản phẩm..">
                            <button type="button">
                                <i class="fa fa-search"></i>
                            </button>
                        </form>
                        <!-- end search -->
                    </div>
                </div>
            </div>
            <!-- end main title -->
            <% if(user.getIsadd() == 1 || user.getRole() == 2) { %>
            <div class="paginator-wrap" style="margin-top: -20px">
                <span  href="#modal-add"  class="open-modal" type="button">Thêm danh mục</span>
            </div>
            <% } else { %>
            <% } %>
            <!-- users -->
            <div class="col-12 bg-pink">
                <div class="main__table-wrap">
                    <table class="main__table">
                        <thead>
                        <tr>
                            <th>STT</th>
                            <th>Tên danh mục</th>
                            <th>Tùy chọn</th>
                        </tr>
                        </thead>
                        <% for (int i = 0; i < list.size(); i++){ %>
                        <tbody>
                        <tr>
                            <td>
                                <div class="main__table-text"><%=i + 1%></div>
                            </td>
                            <td>
                                <div class="main__user">
                                    <div class="main__meta">
                                        <h3><%=list.get(i).getName()%></h3>
                                    </div>
                                </div>
                            </td>
                            <td>
                                <div class="main__table-btns">
                                    <% if(user.getIsedit() == 1 || user.getRole() == 2) { %>
                                    <a href="#modal-edit<%=i%>" class="main__table-btn main__table-btn--edit open-modal">
                                        <i class="fa fa-edit"></i>
                                    </a>
                                    <% } else { %>
                                    <% } %>
                                    <% if(user.getIsdelete() == 1 || user.getRole() == 2) { %>
                                    <a href="#modal-delete<%=i%>" class="main__table-btn main__table-btn--delete open-modal">
                                        <i class="fa fa-trash"></i>
                                    </a>
                                    <% } else { %>
                                    <% } %>
                                </div>
                            </td>
                        </tr>
                        <!-- modal delete -->
                        <div id="modal-delete<%=i%>" class="zoom-anim-dialog mfp-hide modal" style="height: 200px">
                            <form method="post" action="deleteTypeCake">
                            <h6 class="modal__title">Xóa danh mục</h6>
                            <p class="modal__text">Bạn có chắc muốn xóa danh mục này?</p>
                            <div class="modal__btns">
                                <input style="display: none" name="idType" value="<%=list.get(i).getIdType()%>">
                                <button class="modal__btn modal__btn--apply" type="submit">Xóa</button>
                                <button class="modal__btn modal__btn--dismiss" type="button">Quay lại</button>
                            </div>
                            </form>
                        </div>
                        <!-- end modal delete -->
                        <!-- modal edit -->
                        <div id="modal-edit<%=i%>" class="zoom-anim-dialog mfp-hide modal" style="height: 370px">
                            <form method="post" action="update_TypeCake">
                            <h6 class="modal__title">Sửa danh mục</h6>
                            <label class="form__label text-dark" for="name" style="color: white;">Tên danh mục</label>
                            <input id="name" type="text" name="nameType" class="form__input" value="<%=list.get(i).getName()%>">
                            <input style="display: none" name="idType" value="<%=list.get(i).getIdType()%>">
                            <label class="form__label text-dark" style="color: white">Trạng thái</label>
                            <select class="form-select form__input" name="status" >
                                <option>Còn hiệu lực</option>
                                <option>Hết hiệu lực</option>
                            </select>
                            <div class="modal__btns">
                                <button class="modal__btn modal__btn--apply" type="submit">Sửa</button>
                                <button class="modal__btn modal__btn--dismiss" type="button">Quay lại</button>
                            </div>
                            </form>
                        </div>
                        <!-- end modal edit -->
                        </tbody>
                        <!-- modal add -->
                        <div id="modal-add" class="zoom-anim-dialog mfp-hide modal" style="height: 370px">
                            <form method="post" action="addTypeCake">
                            <h6 class="modal__title">Thêm danh mục</h6>
                            <label class="form__label" for="add-name" style="color: white;">Tên danh mục</label>
                            <input id="add-name" type="text" name="nameType" class="form__input" placeholder="Thêm tên danh mục">
                            <label class="form__label" style="color: white">Trạng thái</label>
                            <select class="form-select form__input" name="status" >
                                <option>Còn hiệu lực</option>
                                <option>Hết hiệu lực</option>
                            </select>
                            <div class="modal__btns">
                                <button class="modal__btn modal__btn--apply" type="submit">Thêm</button>
                                <button class="modal__btn modal__btn--dismiss" type="button">Quay lại</button>
                            </div>
                            </form>
                        </div>
                        <!-- end modal add -->
                        <% } %>
                    </table>
                </div>
            </div>
            <!-- end users -->
        </div>
    </div>
</main>
<!-- end main content -->





<!-- JS -->
<script src="js/jquery-3.5.1.min.js"></script>
<script src="js/bootstrap.bundle.min.js"></script>
<script src="js/jquery.magnific-popup.min.js"></script>
<script src="js/jquery.mousewheel.min.js"></script>
<script src="js/jquery.mCustomScrollbar.min.js"></script>
<script src="js/select2.min.js"></script>
<script src="js/main.js"></script>
<script src="js/admin.js"></script>


</body>

</html>
