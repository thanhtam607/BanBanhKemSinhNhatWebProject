/*  ---------------------------------------------------mix
    Template Name: Ogani
    Description:  Ogani eCommerce  HTML Template
    Author: Colorlib
    Author URI: https://colorlib.com
    Version: 1.0
    Created: Colorlib
---------------------------------------------------------  */
/*Read more*/
function myFunction() {
    var x = document.getElementById('bld1');
    var btnrm = document.getElementById('readmore');
    if (x.style.display === 'block') {
        x.style.display = 'none';
        btnrm.innerText = "Xem thêm"
    } else {
        x.style.display = 'block';
        btnrm.innerText = "Thu gọn lại"
    }
}
'use strict';

(function ($) {
    /*------------------
        Preloader
    --------------------*/
    $(window).on('load', function () {
        $(".loader").fadeOut();
        $("#preloder").delay(200).fadeOut("slow");

        /*------------------
            Gallery filter
        --------------------*/
        $('.featured__controls li').on('click', function () {
            $('.featured__controls li').removeClass('active');
            $(this).addClass('active');
        });
        if ($('.featured__filter').length > 0) {
            var containerEl = document.querySelector('.featured__filter');
            var mixer = mixitup(containerEl);
        }
    });

    /*------------------
        Background Set
    --------------------*/
    $('.set-bg').each(function () {
        var bg = $(this).data('setbg');
        $(this).css('background-image', 'url(' + bg + ')');
    });

    //Humberger Menu
    $(".humberger__open").on('click', function () {
        $(".humberger__menu__wrapper").addClass("show__humberger__menu__wrapper");
        $(".humberger__menu__overlay").addClass("active");
        $("body").addClass("over_hid");
    });

    $(".humberger__menu__overlay").on('click', function () {
        $(".humberger__menu__wrapper").removeClass("show__humberger__menu__wrapper");
        $(".humberger__menu__overlay").removeClass("active");
        $("body").removeClass("over_hid");
    });

    /*------------------
		Navigation
	--------------------*/
    $(".mobile-menu").slicknav({
        prependTo: '#mobile-menu-wrap',
        allowParentLinks: true
    });

    /*-----------------------
        Categories Slider
    ------------------------*/
    $(".categories__slider").owlCarousel({
        loop: true,
        margin: 0,
        items: 4,
        dots: false,
        nav: true,
        navText: ["<span class='fa fa-angle-left'><span/>", "<span class='fa fa-angle-right'><span/>"],
        animateOut: 'fadeOut',
        animateIn: 'fadeIn',
        smartSpeed: 1200,
        autoHeight: false,
        autoplay: true,
        responsive: {

            0: {
                items: 1,
            },

            480: {
                items: 2,
            },

            768: {
                items: 3,
            },

            992: {
                items: 4,
            }
        }
    });


    $('.hero__categories__all').on('click', function(){
        $('.hero__categories ul').slideToggle(400);
    });

    /*--------------------------
        Latest Product Slider
    ----------------------------*/
    $(".latest-product__slider").owlCarousel({
        loop: true,
        margin: 0,
        items: 1,
        dots: false,
        nav: true,
        navText: ["<span class='fa fa-angle-left'><span/>", "<span class='fa fa-angle-right'><span/>"],
        smartSpeed: 1200,
        autoHeight: false,
        autoplay: true
    });

    /*-----------------------------
        Product Discount Slider
    -------------------------------*/
    $(".product__discount__slider").owlCarousel({
        loop: true,
        margin: 0,
        items: 3,
        dots: true,
        smartSpeed: 1200,
        autoHeight: false,
        autoplay: true,
        responsive: {

            320: {
                items: 1,
            },

            480: {
                items: 2,
            },

            768: {
                items: 2,
            },

            992: {
                items: 3,
            }
        }
    });

    /*---------------------------------
        Product Details Pic Slider
    ----------------------------------*/
    $(".product__details__pic__slider").owlCarousel({
        loop: true,
        margin: 20,
        items: 4,
        dots: true,
        smartSpeed: 1200,
        autoHeight: false,
        autoplay: true
    });

    /*-----------------------
		Price Range Slider
	------------------------ */
    var rangeSlider = $(".price-range"),
        minamount = $("#minamount"),
        maxamount = $("#maxamount"),
        minPrice = rangeSlider.data('min'),
        maxPrice = rangeSlider.data('max');
    rangeSlider.slider({
        range: true,
        min: minPrice,
        max: maxPrice,
        values: [minPrice, maxPrice],
        slide: function (event, ui) {
            minamount.val(ui.values[0]);
            maxamount.val(ui.values[1]);
        }
    });
    minamount.val(rangeSlider.slider("values", 0));
    maxamount.val(rangeSlider.slider("values", 1));

    /*--------------------------
        Select
    ----------------------------*/
    $("select").niceSelect();

    /*------------------
		Single Product
	--------------------*/
    $('.product__details__pic__slider img').on('click', function () {

        var imgurl = $(this).data('imgbigurl');
        var bigImg = $('.product__details__pic__item--large').attr('src');
        if (imgurl != bigImg) {
            $('.product__details__pic__item--large').attr({
                src: imgurl
            });
        }
    });

    /*-------------------
		Quantity change
	--------------------- */
    var proQty = $('.pro-qty');

    proQty.on('click', '.qtybtn', function () {
        var $button = $(this);
        var oldValue = $button.parent().find('input').val();
        var newVal=parseInt(oldValue);
        if ($button.hasClass('inc')) {
            newVal +=1;
        }
        else {
            // Don't allow decrementing below zero
            if (oldValue > 1) {
                newVal-=1;
            } else {
                newVal = 1;
            }
        }
        console.log(newVal);
        $button.parent().find('input').val(newVal);

    });

})(jQuery);

  // Back to top button
  $(window).scroll(function () {
    if ($(this).scrollTop() > 100) {
        $('.back-to-top').fadeIn('slow');
    } else {
        $('.back-to-top').fadeOut('slow');
    }
});
$('.back-to-top').click(function () {
    $('html, body').animate({scrollTop: 0}, 1000, 'easeInOutExpo');
    return false;
});
/*-------------------
   notification
  --------------------- */

function notLogged(){
    Swal.fire({
        text:'Đăng nhập để tiếp tục!',
        icon: 'error',
        showCancelButton: true,
        cancelButtonText:'Thoát',
        confirmButtonText: 'Đăng nhập',
        confirmButtonColor: '#ff96b7'}).then((result) => {
            if (result.isConfirmed) {
                location.href = "signin.jsp";}
        }
    );
};

/*-------------------
   addToCart
  --------------------- */
function addToCartI(id){

    var qty = 1;

    var url  ="AddToCart?masp=" +id+"&soluong="+ qty;
    var totalPro = parseInt(document.getElementById("totalPro").innerHTML);
    $.ajax({
        url: url,
        type: "GET",
        success: function (){
            totalPro+=1;
            document.getElementById("totalPro").innerHTML=totalPro.toString();
            document.getElementById("totalPro1").innerHTML=totalPro.toString();
            Swal.fire({
                text:'Sản phẩm đã được thêm vào giỏ hàng!',
                icon: 'success',
                confirmButtonColor: '#ff96b7'});
        }
    });
};
// xóa cart
function removeCart(id){

    var totalPro = parseInt(document.getElementById("totalPro").innerHTML);
    var qty = parseInt(document.getElementById('qty'+id).value);
    var url = "RemoveToCart?masp="+id;
    $.ajax({
        url: url,
        type: "GET",
        success: function (){
            totalPro-=qty;
            document.getElementById("totalPro").innerHTML=totalPro.toString();
            document.getElementById("totalPro1").innerHTML=totalPro.toString();
            document.getElementById(id).remove();
            if(totalPro == 0){
                document.getElementById("container-cart").remove();
                document.getElementById("shoping-cart").outerHTML=" <div class=\"cartEmpty\" name=\"cartEmpty\">\n" +
                    "            <img src=\"./img/null.png\" alt=\"Giỏ hàng của bạn đang trống\" class=\"d-block m-auto\" width=\"250\" />\n" +
                    "            <p class=\"text-center font-weight-bold\" style=\"opacity: .6;\">Giỏ hàng của bạn trống</p>\n" +
                    "        </div>\n" +
                    "        <div class=\"col-lg-12\">\n" +
                    "            <div class=\"shoping__cart__btns\" style=\"text-align: center\">\n" +
                    "                <a href=\"./ListProduct\" class=\"primary-btn cart-btn\" >Xem thêm sản phẩm </a>\n" +
                    "\n" +
                    "            </div>\n" +
                    "        </div>";

            }
        }
    });


}
/*-------------------
   FIND BY PRICE
  --------------------- */
function FilterbyPrice(){
    var pr1 = document.getElementById("minamount").value;
    var pr2 = document.getElementById("maxamount").value;
    var p1 = parseInt(pr1);
    var p2 = parseInt(pr2);
    var title = "Giá từ " + p1.toLocaleString('vi-VN')+" VND đến "+ p2.toLocaleString('vi-VN')+" VND";
    var url = "ProductFilter?pricemin=" + p1 +"&pricemax="+ p2+"&title="+ title;
    location.href=url;
}
/*-------------------
  update cart
  --------------------- */

function updateCart(id){
        var newQty = parseInt(document.getElementById('qty'+id).value)+1;
        var url = "UpdateCart?masp=" + id + "&soluong=" + newQty;
        var totalPro = parseInt(document.getElementById("totalPro").innerHTML);
        var item = document.getElementsByClassName("cart-item");

        $.ajax({
            url: url,
            type: "POST",
            success: function () {
                var total = 0;
                totalPro = 0;
                var totalMoney = 0;

                for(var i=0; i<item.length;i++) {
                    var row = item[i];
                var price = parseInt(row.getElementsByClassName("price")[0].value);
                var qty = parseInt(row.getElementsByClassName("qty")[0].value);

                totalPro+= qty;
                total = (price * qty);
                totalMoney += total;

                row.getElementsByClassName("shoping__cart__total")[0].innerHTML = total.toLocaleString('vi-VN') + " VND";

                }
                document.getElementById("totalPro").innerHTML = totalPro.toString();
                document.getElementById("totalPro1").innerHTML = totalPro.toString();
                document.getElementById("totalMoney").innerHTML = totalMoney.toLocaleString('vi-VN') + " VND";
                document.getElementById("payment").innerHTML = totalMoney.toLocaleString('vi-VN') + " VND";

            }
        });

};
/*-------------------
       add to favorites
   --------------------- */
function addToFav(id){
    var url = "Favorite?masp="+ id;
    console.log(id);
    var totalFav = parseInt(document.getElementById("totalFav").innerHTML);
    $.ajax({
        url: url,
        type: "GET",
        success: function () {
            totalFav+=1;
            Swal.fire({
                text:'Sản phẩm đã được thêm vào danh mục yêu thích!',
                icon: 'success',
                confirmButtonColor: '#ff96b7'});
            document.getElementById("totalFav").innerHTML=totalFav.toString();
            document.getElementById("totalFav1").innerHTML=totalFav.toString();
            document.getElementById("totalFav2").innerHTML=totalFav.toString();
        }

    });

};


function removeToFav(id){
    var url = "RemoveFavorites?masp="+ id;
    var totalFav = parseInt(document.getElementById("totalFav").innerHTML);
    $.ajax({
        url: url,
        type: "GET",
        success: function () {
            totalFav-=1;

            document.getElementById("fav"+id).remove();
            document.getElementById("totalFav").innerHTML=totalFav.toString();
            document.getElementById("totalFav1").innerHTML=totalFav.toString();
            document.getElementById("totalFav2").innerHTML=totalFav.toString();
        }
    });

};