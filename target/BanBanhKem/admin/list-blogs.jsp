<%@ page import="vn.edu.hcmuaf.fit.model.Blog" %>
<%@ page import="java.util.List" %>
<%@ page import="vn.edu.hcmuaf.fit.bean.User" %>
<%@ page import="vn.edu.hcmuaf.fit.service.BlogService" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="vn.edu.hcmuaf.fit.service.InforService" %>
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
    <% List<Blog> blogList = (List<Blog>) request.getAttribute("list");%>
<% User user = UserService.findById(auth.getId()); %>
    <main class="main bg-white">
        <div class="container-fluid bg-white">
            <div class="row">
                <!-- main title -->
                <div class="col-12">
                    <div class="main__title">
                        <h2>Danh sách tin tức</h2>
                        <span class="main__title-stat"><%=blogList.size()%></span>
                        <div class="main__title-wrap">
                            <!-- filter sort -->
                            <div class="filter" id="filter__sort">
                                <span class="filter__item-label">Sắp xếp:</span>

                                <div class="filter__item-btn dropdown-toggle" role="navigation" id="filter-sort" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    <input type="button" id="type">
                                    <span></span>
                                </div>

                                <ul class="filter__item-menu dropdown-menu scrollbar-dropdown" aria-labelledby="filter-sort" style="width: 200px">
                                    <%List<String> listOption = new ArrayList<String>();
                                        listOption.add("Mặc định");
                                        listOption.add("Theo tiêu đề");
                                        listOption.add("Theo danh mục");
                                        listOption.add("Theo ngày đăng");
                                        String val;
                                        for(String s: listOption){
                                            val = request.getParameter("sortValue");
                                            if(s.equals(val)){%>
                                    <li><a class ="text-pink" onclick="filterProduct(false, '<%=val%>')" href="ListBlog-admin?sortValue=<%=val%>" selected="true" value="<%=val%>"><%=val%></a></li>
                                    <%}
                                    else{%>
                                    <li><a id="sortValue" class ="text-pink" href="ListBlog-admin?sortValue=<%=s%>" onclick="filterProduct(false, '<%=s%>')" value="<%=s%>"><%=s%></a></li>
                                    <%}%>
                                    <%}%>
                                </ul>
                            </div>
                            <!-- end filter sort -->

                            <!-- search -->
                            <form action="#" class="main__title-form">
                                <input type="text" placeholder="Tìm kiếm..">
                                <button type="button">
									<i class="fa fa-search"></i>
								</button>
                            </form>
                            <!-- end search -->
                        </div>
                    </div>
                </div>
                <!-- end main title -->
                <div class="button">
                    <% if(user.getIsadd() == 1 || user.getRole() == 2) { %>
                    <div class="button_left">
                        <a class="button_product" href="add-blog.jsp">Thêm tin tức</a>
                    </div>
                    <% } else { %>
                    <% } %>
                    <% if(user.getIsdelete() == 1 || user.getRole() == 2) { %>
                    <div class="button_right">
                        <a class="button_product" href="ListBlogsRemoved.jsp">Tin tức đã xóa(<%=BlogService.getDataRemove().size()%>)</a>
                    </div>
                    <% } else { %>
                    <% } %>
                </div>
                <!-- users -->
                <div class="col-12 bg-pink">
                    <div class="main__table-wrap">
                        <table class="main__table">
                            <thead>
                                <tr>
                                    <th>STT</th>
                                    <th>Tiêu đề</th>
                                    <th>Danh mục</th>
                                    <th>Trạng thái</th>
                                    <th>Ngày đăng</th>
                                    <th>Tùy chọn</th>
                                </tr>
                            </thead>
                       <% for (int i = 0; i < blogList.size(); i++) {
                       Blog blog = blogList.get(i) ; %>
                            <tbody>
                                <tr>
                                    <td>
                                        <div class="main__table-text"><%=i+1%></div>
                                    </td>
                                    <td>
                                        <div class="main__user">
                                           
                                            <div class="main__meta">
                                                <h3><%=blog.getTitle()%></h3>
                                            </div>
                                        </div>
                                    </td>
                                    <td>
                                        <div class="main__table-text"><%=blog.getCategory()%></div>
                                    </td>
                                    
                                    <td>
                                        <div class="main__table-text main__table-text--green">Đã phê duyệt</div>
                                    </td>
                                    <td>
                                        <div class="main__table-text"><%=blog.getDate()%></div>
                                    </td>
                                    <td style="display: none">
                                        <%String main__btn ="";
                                            if(BlogService.findById(blog.getId()).getStatus() == 1){
                                                main__btn = "main__table-btn--delete";
                                            }else{
                                                main__btn = "main__table-btn--banned";
                                            }%>
                                    </td>

                                    <td>
                                        <div class="main__table-btns">
                                            <% if(user.getIsedit() == 1 || user.getRole() == 2) { %>
                                            <%if(BlogService.findById(blog.getId()).getStatus() == 1){%>
                                            <a href="#modal-status-unlock<%=i%>" class="main__table-btn <%=main__btn%> open-modal">
                                                <i class="fa fa-lock"></i>
                                            </a>
                                            <%}else{%>
                                            <a href="#modal-status-lock<%=i%>" class="main__table-btn <%=main__btn%> open-modal">
                                                <i class="fa fa-unlock"></i>
                                            </a>
                                            <%}%>
                                            <% } else { %>
                                            <% } %>
                                            <% if(user.getIsedit() == 1 || user.getRole() == 2) { %>
                                            <a href="EditBlog?idB=<%=blog.getId()%>" class="main__table-btn main__table-btn--edit">
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
                            </tbody>
                            <!-- modal status lock-->
                            <div id="modal-status-lock<%=i%>" class="zoom-anim-dialog mfp-hide modal">
                                <form method="post" action="HideBlog">
                                    <h6 class="modal__title">Ẩn tin tức</h6>
                                    <p class="modal__text">Bạn có chắc muốn ẩn tin tức này này?</p>
                                    <input name = "mablog" value="<%=blog.getId()%>" style="display: none">
                                    <input name = "stt" value="1" style="display: none">
                                    <div class="modal__btns">
                                        <button class="modal__btn modal__btn--apply" type="submit">Ẩn</button>
                                        <button class="modal__btn modal__btn--dismiss" type="button">Quay lại</button>
                                    </div>
                                </form>
                            </div>
                            <!-- modal status unlock-->
                            <div id="modal-status-unlock<%=i%>" class="zoom-anim-dialog mfp-hide modal">
                                <form method="post" action="HideBlog">
                                    <h6 class="modal__title">Bỏ ẩn tin tức</h6>
                                    <p class="modal__text">Bạn có chắc muốn bỏ ẩn tin tức này?</p>
                                    <input name = "mablog" value="<%=blog.getId()%>" style="display: none">
                                    <input name = "stt" value="0" style="display: none">
                                    <div class="modal__btns">
                                        <button class="modal__btn modal__btn--apply" type="submit">OK</button>
                                        <button class="modal__btn modal__btn--dismiss" type="button">Quay lại</button>
                                    </div>
                                </form>
                            </div>
                            <!-- modal delete -->
                            <div id="modal-delete<%=i%>" class="zoom-anim-dialog mfp-hide modal">
                                <form method="post" action="DeleteBlog">
                                <h6 class="modal__title">Xóa Tin Tức</h6>
                                <p class="modal__text">Bạn có chắc muốn xóa bài viết này?</p>
                                <input name = "idblog" value="<%=blog.getId()%>" style="display: none">
                                <div class="modal__btns">
                                    <button class="modal__btn modal__btn--apply" type="submit">Xóa</button>
                                    <button class="modal__btn modal__btn--dismiss" type="button">Quay lại</button>
                                </div>
                                </form>
                            </div>
                            <!-- end modal delete -->
                            <% } %>
                        </table>
                    </div>
                </div>
                <!-- end users -->

                <!-- paginator -->
                <div class="col-12">
                    <div class="paginator-wrap">
                        <span><%=blogList.size()%>/<%=blogList.size()%> Bài viết</span>

                        <ul class="paginator">
                            <li class="paginator__item paginator__item--prev">
                                <a href="#"><i class="fa fa-chevron-left"></i></a>
                            </li>
                            <li class="paginator__item"><a href="#">1</a></li>
                            <li class="paginator__item paginator__item--active"><a href="#">2</a></li>
                            <li class="paginator__item"><a href="#">3</a></li>
                            <li class="paginator__item"><a href="#">4</a></li>
                            <li class="paginator__item paginator__item--next">
                                <a href="#"><i class="fa fa-chevron-right"></i></a>
                            </li>
                        </ul>
                    </div>
                </div>
                <!-- end paginator -->
            </div>
        </div>
    </main>
    <!-- end main content -->

    <!-- Back to Top -->
    <a href="#" class="btn btn-lg btn-primary btn-lg-square back-to-top"><i class="bi bi-arrow-up"></i></a>

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