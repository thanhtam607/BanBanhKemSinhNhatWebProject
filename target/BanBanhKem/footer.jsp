<%@ page import="vn.edu.hcmuaf.fit.service.InforService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- Footer Section Begin -->
<footer class="footer spad">
  <div class="container">
    <div class="row">
      <div class="col-lg-3 col-md-6 col-sm-6">
        <div class="footer__about">
          <div class="footer__about__logo">
            <a href="./"><img src="<%=InforService.getImgLogo().get(0).getContent()%>" alt=""></a>
          </div>
          <ul class="mt-5" id="about">
            <li><i class="fa fa-home"></i> Địa chỉ: <%=InforService.getInformation("Address").get(0).getContent()%></li>
            <li><i class="fa fa-phone"></i> SĐT:<%=InforService.getInformation("PhoneNumber").get(0).getContent()%></li>
            <li><i class="fa fa-send-o"></i> Mail: <%=InforService.getInformation("Email").get(0).getContent()%></li>
          </ul>
        </div>
      </div>
      <div class="col-lg-4 col-md-6 col-sm-6 offset-lg-1">
        <div class="footer__widget">
          <h6>Giờ mở cửa</h6>
          <ul>
            <li><a href="#"><%=InforService.getInformation("TimeShop").get(0).getContent()%></a></li>
            <li><a href="#"><%=InforService.getInformation("TimeShop").get(2).getContent()%></a></li>
          </ul>
          <ul>
            <li><a href="#"><%=InforService.getInformation("TimeShop").get(1).getContent()%></a></li>
            <li><a href="#"><%=InforService.getInformation("TimeShop").get(3).getContent()%></a></li>
          </ul><br><br>
          <p class="cake-footer"><h5 class="freecake text-animation"><%=InforService.getInformation("SpecialOffer").get(0).getContent()%></h5><a class ="fontsize-14"><%=InforService.getInformation("SpecialOffer").get(1).getContent()%></a></p>
          <img src = "<%=InforService.getInformation("SpecialOffer").get(2).getContent()%>">
        </div>
      </div>
      <div class="col-lg-4 col-md-12">
        <div class="footer__widget">
          <h6>Theo dõi bảng tin của chúng tôi</h6>
          <p>Nhận thông tin cập nhật e-mail về sản phẩm mới nhất và các ưu đãi đặc biệt của chúng tôi.</p>
          <form action="#">
            <input type="text" placeholder="Địa chỉ email của bạn...">
            <button type="submit" class="site-btn">Đăng ký</button>
          </form>
          <div class="footer__widget__social">
            <a href="<%=InforService.getInformation("SocialNetwork").get(0).getContent()%>"><i class="fa fa-facebook"></i></a>
            <a href="<%=InforService.getInformation("SocialNetwork").get(1).getContent()%>"><i class="fa fa-comment"></i></a>
            <a href="<%=InforService.getInformation("SocialNetwork").get(2).getContent()%>"><i class="fa fa-instagram"></i></a>
          </div>
        </div>
      </div>
    </div>
    <div class="row">
      <div class="col-lg-12">
        <div class="footer__copyright">
          <div class="footer__copyright__text"><p><!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
            Bản quyền &copy;<script>document.write(new Date().getFullYear());</script> thuộc Nhóm 17 | Lập trình web thầy Phan Đình Long <i class="fa fa-heart" aria-hidden="true"> </i> Khoa CNTT - ĐH Nông Lâm TP HCM</a>
            <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. --></p></div>
          <div class="footer__copyright__payment"></div>
        </div>
      </div>
    </div>
  </div>
</footer>
<!-- Footer Section End -->
<!-- back to top btn -->
<a href="#" class="btn-to-top back-to-top"><i class="fa fa-angle-double-up"></i></a>
