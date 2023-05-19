<%@ page import="vn.edu.hcmuaf.fit.model.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="vn.edu.hcmuaf.fit.model.ProductDetail" %>
<%@ page import="vn.edu.hcmuaf.fit.controller.ListProduct" %>
<%@ page import="vn.edu.hcmuaf.fit.bean.User" %>
<%@ page import="vn.edu.hcmuaf.fit.service.ProductService" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="vn.edu.hcmuaf.fit.model.Image" %>
<%@ page import="vn.edu.hcmuaf.fit.model.Discount" %>
<%@ page import="vn.edu.hcmuaf.fit.service.DiscountService" %>
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
                <h6 class="mb-0"><%=auth != null ? auth.getName() : "ADMIN"%></h6>
                <span><%=auth != null ? URLDecoder.decode(auth.getRoleName(), "UTF-8") : "Admin"%></span>
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
            <a href="List_Discounts" class="nav-item nav-link active"><i class="fa fa-birthday-cake me-2"></i>Khuyến mãi</a>
            <a href="../Index" class="nav-item nav-link"><i class="fa fa-arrow-alt-circle-right me-2"></i>Về trang chủ</a>
            <!--  -->
        </div>
    </nav>
</div>
<!-- Sidebar End -->

<!-- main content -->
<%%>
<main class="main bg-white">
    <div class="container-fluid bg-white">
        <div class="row">
            <!-- main title -->
            <div class="col-12">
                <div class="main__title">
                    <h2>Danh sách sản phẩm khuyến mãi</h2>
                    <% List<Product> productList = (List<Product>) request.getAttribute("listpro");%>
                    <span class="main__title-stat"><%=productList.size()%> sản phẩm</span>

                    <div class="main__title-wrap">
                        <!-- filter sort -->
                        <%--                        <div class="filter" id="filter__sort">--%>
                        <%--                            <span class="filter__item-label">Sắp xếp:</span>--%>

                        <%--                            <div class="filter__item-btn dropdown-toggle" role="navigation" id="filter-sort" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">--%>
                        <%--                                <input type="button" value="Sản phẩm">--%>
                        <%--                                <span></span>--%>
                        <%--                            </div>--%>

                        <%--                            <ul class="filter__item-menu dropdown-menu scrollbar-dropdown" aria-labelledby="filter-sort">--%>
                        <%--                                <%List<String> listOption = new ArrayList<String>();--%>
                        <%--                                    listOption.add("Mặc định");--%>
                        <%--                                    listOption.add("Giá từ thấp đến cao");--%>
                        <%--                                    listOption.add("Giá từ cao đến thấp");--%>
                        <%--                                    listOption.add("Sắp xếp theo tên");--%>
                        <%--                                    listOption.add("Sắp xếp theo loại");--%>
                        <%--                                    String val;--%>
                        <%--                                    for(String s: listOption){--%>
                        <%--                                        val = request.getParameter("sortValue");--%>
                        <%--                                        if(s.equals(val)){%>--%>
                        <%--                                <li><a class ="text-pink" href="ListProduct_Admin?sortValue=<%=val%>" selected="true" value="<%=val%>"><%=val%></a></li>--%>
                        <%--                                <%}--%>
                        <%--                                else{%>--%>
                        <%--                                <li><a id="sortValue" class ="text-pink" href="ListProduct_Admin?sortValue=<%=s%>" value="<%=s%>"><%=s%></a></li>--%>
                        <%--                                <%}%>--%>
                        <%--                                <%}%>--%>
                        <%--                            </ul>--%>
                        <%--                        </div>--%>
                        <!-- end filter sort -->

                        <!-- search -->
                        <%--                        <form action="#" class="main__title-form">--%>
                        <%--                            <input type="text" placeholder="Tìm sản phẩm..">--%>
                        <%--                            <button type="button">--%>
                        <%--                                <i class="fa fa-search"></i>--%>
                        <%--                            </button>--%>
                        <%--                        </form>--%>
                        <!-- end search -->
                    </div>
                </div>
            </div>
            <!-- end main title -->
            <div class="button">
                <% if(auth.getIsadd() == 1 || auth.getRole() == 2) { %>
                <div class="button_left">
                    <a class="button_product" href="Add_Discount">Thêm khuyến mãi</a>
                </div>
                <% } else { %>
                <% } %>
            </div>

            <div class="col-12 bg-pink">

                <div class="main__table-wrap">
                    <table class="main__table" style="min-width: 650px;">
                        <thead>
                        <tr>
                            <th>STT</th>
                            <th>Mã SP</th>
                            <th>Tên sản phẩm</th>
                            <th>Loại</th>
                            <th>Giá (VND)</th>
                            <th>Giảm giá</th>
                            <th>Tùy chọn</th>
                        </tr>
                        </thead>
                        <% List<Product> listPa = (List<Product>) request.getAttribute("listPa");
                            Product pro = null;
                            for (int i = 0; i < listPa.size(); i++) {
                                pro = listPa.get(i);
                                int tag = (int) request.getAttribute("tag") - 1;
                        %>
                        <tbody>
                        <tr>
                            <td>
                                <div class="main__table-text"><%=tag * 15 + 1 + i%></div>
                            </td>
                            <td>
                                <div class="main__table-text"><%=pro.getId()%></div>
                            </td>
                            <td>
                                <div class="main__user">
                                    <div class="main__avatar">

                                        <%for(Image img :pro.getListImg()){
                                            if(!img.delete()&& !img.isHide()){%>
                                        <img src="../<%=img.getImg()%>" alt="">
                                        <% break;
                                        }
                                        }%>
                                    </div>
                                    <div class="main__meta">
                                        <a href="Edit_Product?idP=<%=pro.getId()%>"><%=pro.getName()%></a>
                                    </div>


                                </div>
                            </td>
                            <td >
                                <div class="main__table-text">
                                    <%=pro.getType()%>
                                </div>
                            </td>
                            <td>
                                <div class="main__table-text">
                                    <div class="product__item__price"><%=pro.formatNum(pro.getPromotional())%> VND <span><%=pro.formatNum(pro.getPrice())%> VND</span></div>
                                </div>

                            </td>
                            <td>
                                <%Discount discount =pro.getDiscount(); %>
                                <div class="main__table-text"><%=discount.getDiscount()%></div>
                            </td>
                            <td>
                                <div class="main__table-btns">
                                    <% if(auth.getIsdelete() == 1 || auth.getRole() == 2) { %>
                                    <a href="#modal-delete<%=discount.getId()%>" class="profile__action profile__action--delete open-modal"><i class="fa fa-trash"></i></a>
                                    <% } else { %>
                                    <% } %>
                                    <div id="modal-delete<%=discount.getId()%>" class="zoom-anim-dialog mfp-hide modal">
                                        <h6 class="modal__title">Xóa khuyến mãi</h6>
                                        <p class="modal__text">Bạn có chắc muốn hủy khuyến mãi này?</p>
                                        <%String urlq = "RemoveDiscount?id=" + discount.getId()+"&idProduct="+ discount.getIdProduct();%>
                                        <div class="modal__btns">
                                            <button class="modal__btn modal__btn--apply" onclick="changeHref('<%=urlq%>')" type="button">Xóa</button>
                                            <button class="modal__btn modal__btn--dismiss" type="button">Quay lại</button>
                                        </div>
                                    </div>
                                </div>
                            </td>
                        </tr>
                        </tbody>
                        <% } %>
                    </table>
                </div>
            </div>
            <!-- end users -->

            <!-- paginator -->
            <div class="col-12">
                <div class="paginator-wrap">
                    <span><%=listPa.size()%>/<%=productList.size()%> sản phẩm</span>

                    <ul class="paginator">
                        <% int tag = (int) request.getAttribute("tag");%>
                        <li class="paginator__item paginator__item--prev">
                            <a href="List_Discounts?page=<%=tag - 1%>"><i class="fa fa-chevron-left"></i></a>
                        </li>
                        <% int endPage = (int) request.getAttribute("endPage");
                            for (int i = tag - 1; i <= tag + 2; i++) {
                                if (i < 1) {
                                    continue;
                                }
                                if (i == tag) {%>
                        <li class="paginator__item paginator__item--active"><a href="List_Discounts?page=<%=i%>">
                            <%=tag%></a></li>
                        <%} else {%>
                        <li class="paginator__item"><a href="List_Discounts?page=<%=i%>"><%=i%></a></li>
                        <%}
                        }%>
                        <li class="paginator__item paginator__item--next">
                            <a href="List_Discounts?page=<%=tag + 1%>"><i class="fa fa-chevron-right"></i></a>
                        </li>


                        </li>
                    </ul>
                </div>
            </div>
            <!-- end paginator -->
        </div>
    </div>
</main>
<!-- end main content -->

<!-- end profile -->

<!-- modal status -->
<div id="modal-status" class="zoom-anim-dialog mfp-hide modal">


    <p class="modal__text">Bạn có chắc muốn khóa sản phẩm này?</p>

    <div class="modal__btns">

        <button class="modal__btn modal__btn--dismiss" type="button">Quay lại</button>
    </div>
</div>
<!-- end modal status -->




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