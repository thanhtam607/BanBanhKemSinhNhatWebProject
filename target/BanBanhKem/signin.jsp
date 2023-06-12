<%@ page import="vn.edu.hcmuaf.fit.service.InforService" %>
<!DOCTYPE html>
<%@ page contentType="text/html;charsetUTF-8" language="java" pageEncoding="utf-8" %>
<html lang="xzz">
<meta http-equiv="Content-Type" charset="UTF-8">

<head>
    <meta charset="utf-8">
    <title><%=InforService.getInformation("NameShop").get(0).getContent()%> | Sign in </title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="" name="keywords">
    <meta content="" name="description">

    <!-- Favicon -->
    < <link rel="icon" href="img/favicon.ico" type="image/x-icon" />

    <!-- Google Web Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;600&family=Roboto:wght@500;700&display=swap"
          rel="stylesheet">

    <!-- Icon Font Stylesheet -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

    <!-- Libraries Stylesheet -->
    <link href="admin/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
    <link href="admin/lib/tempusdominus/css/tempusdominus-bootstrap-4.min.css" rel="stylesheet"/>

    <!-- Customized Bootstrap Stylesheet -->
    <link href="admin/css/bootstrap.min.css" rel="stylesheet">

    <!-- Template Stylesheet -->
    <link href="admin/css/style.css" rel="stylesheet">
</head>

<body>

<div class="container-fluid position-relative d-flex p-0">
    <!-- Spinner Start -->
    <div id="spinner"
         class="show bg-pink position-fixed translate-middle w-100 vh-100 top-50 start-50 d-flex align-items-center justify-content-center">
        <div class="spinner-border text-primary" style="width: 3rem; height: 3rem;" role="status">
            <span class="sr-only">Đang tải...</span>
        </div>
    </div>
    <!-- Spinner End -->
    <% String previousPageUrl = request.getHeader("Referer"); // lấy URL trang trước đó
        if (previousPageUrl != null) {
            session.setAttribute("previousPageUrl", previousPageUrl);
        }%>
    <% String error = (String) request.getAttribute("Error");%>
    <% String uname = request.getParameter("username"); %>
    <!-- Sign In Start -->
    <div class="container-fluid bg_signin_signup">
        <div class="row h-100 align-items-center justify-content-center" style="min-height: 100vh;">
            <div class="col-12 col-sm-8 col-md-6 col-lg-5 col-xl-4">
                <form action="./doSignin" method="post" class="bg-pink rounded p-4 p-sm-5 my-4 mx-3">
                    <div>
                        <h2 class="title-sg">Đăng nhập</h2>
                    </div>
                    <div class="form-floating mb-3">
                        <input type="email" class="form-control" name="username" id="floatingInput"
                               value="<%= uname != null ? uname:""%>" placeholder="name@example.com">
                        <label for="floatingInput">Địa chỉ email</label>
                    </div>
                    <div class="form-floating mb-4">
                        <input type="password" class="form-control pass" name="pass" id="floatingPassword"
                               placeholder="Password">
                        <label for="floatingPassword">Mật khẩu</label>
                    </div>
                    <span id="insertTextHere"><%= error != null ? error : ""%></span>
                    <div class="d-flex align-items-center justify-content-between mb-4 mt-4">
                        <div class="form-check">
                            <input type="checkbox" class="form-check-input toggle" id="exampleCheck1">
                            <label class="form-check-label" for="exampleCheck1">Lưu đăng nhập</label>
                        </div>
                        <script>
                            const toggle = document.querySelector(".toggle"),
                                pass = document.querySelector(".pass");

                            toggle.addEventListener("click", () => {
                                if (pass.type === "password") {
                                    pass.type = "text";
                                } else {
                                    pass.type = "password";
                                }
                            })
                        </script>

                        <a style="cursor: pointer" onclick="forgotPassword()">Quên mật khẩu?</a>
                    </div>
                    <button type="submit" id="login-button" class="btn btn-primary py-3 w-100 mb-4">Đăng nhập</button>
                    <div class="btn-google col-12">
                        <a href="https://accounts.google.com/o/oauth2/auth?scope=email%20profile&20openid&redirect_uri=http://localhost:8080/LoginGoogle&response_type=code
		   &client_id=344697106441-26mu1s28srer7nnpb5pqc7l502dodjv9.apps.googleusercontent.com&approval_prompt=force"
                           type="button" class="google col-12"><i class="fab fa-google"></i>Đăng nhập với Google</a>
                    </div>
                    <p class="text-center mb-0">Bạn chưa có tài khoản? <a href="signup.jsp">Đăng ký ngay</a></p>
                </form>
            </div>
        </div>
    </div>
    <!-- Sign In End -->
</div>


<!-- JavaScript Libraries -->
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="admin/lib/chart/chart.min.js"></script>
<script src="admin/lib/easing/easing.min.js"></script>
<script src="admin/lib/waypoints/waypoints.min.js"></script>
<script src="admin/lib/owlcarousel/owl.carousel.min.js"></script>
<script src="admin/lib/tempusdominus/js/moment.min.js"></script>
<script src="admin/lib/tempusdominus/js/moment-timezone.min.js"></script>
<script src="admin/lib/tempusdominus/js/tempusdominus-bootstrap-4.min.js"></script>
<script src="js/main.js"></script>


<!-- Template Javascript -->
<script src="admin/js/main.js"></script>
<script src="js/main.js"></script>

<!-- LOGIN ADMIN -->
<%--    <script>--%>
<%--        document.getElementById('login-button').onclick = function(){--%>
<%--        var myEmail = document.getElementById("floatingInput").value;--%>
<%--        var myPass = document.getElementById("floatingPassword").value;--%>

<%--        var listEmail = ["nhom27@gmail.com", "nhom27ltw@gmail.com"];--%>
<%--        var listPass = ["123", "1234"];--%>
<%--        var checkU = false;--%>
<%--        var indexU = 0;--%>
<%--        for (var i = 0; i < listEmail.length; i++) {--%>
<%--            if (myEmail == listEmail[i]) {--%>
<%--                checkU = true;--%>
<%--                indexU = i;--%>
<%--                break;--%>
<%--            } else {--%>
<%--                checkU = false;--%>
<%--            }--%>
<%--        }--%>
<%--        if (checkU === true) {--%>
<%--            if (myPass == listPass[indexU]) {--%>
<%--                window.location.href = "./admin-web.html";--%>
<%--                return false;--%>
<%--            } else {--%>
<%--                document.getElementById("insertTextHere").textContent="*Sai Password";--%>
<%--            }--%>
<%--        } else {--%>
<%--            document.getElementById("insertTextHere").textContent="*Username không hợp lệ hoặc sai";--%>
<%--        }--%>
<%--        --%>
<%--        }--%>
<%--    </script>--%>
</body>

</html>