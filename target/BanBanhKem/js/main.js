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
function removeAllCart(){
    Swal.fire({
        text:'Bạn có chắc muốn xóa tất cả sản phẩm trong giỏ hàng?',
        icon: 'question',
        showCancelButton: true,
        cancelButtonText:'Hủy',
        confirmButtonText: 'Xác nhận',
        confirmButtonColor: '#ff96b7'}).then((result) => {
            if (result.isConfirmed) {
                removeAll()}
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
                text:'Thêm sản phẩm thành công!',
                icon: 'success',
                showCancelButton: true,
                cancelButtonText: 'Mua tiếp',
                confirmButtonText: 'Đi đến giỏ hàng',
                confirmButtonColor: '#ff96b7'}).then((result) => {
                    if (result.isConfirmed) {
                        location.href = "shoping-cart.jsp";
                    }

                }
            );
        }
    });
};
// xóa cart
function removeCart(id){
    var totalPro = parseInt(document.getElementById("totalPro").innerHTML);
    var qty = parseInt(document.getElementById('qty'+id).value);
    var priceR = parseInt(document.getElementById("price"+id).value);
    var url = "RemoveToCart?masp="+id;
    $.ajax({
        url: url,
        type: "GET",
        success: function (){
            totalPro-=qty;
            var totalMoney = 0;
            var item = document.getElementsByClassName("cart-item");
            for(var i=0; i<item.length;i++) {
                var row = item[i];
                var price = parseInt(row.getElementsByClassName("price")[0].value);
                var qty1 = parseInt(row.getElementsByClassName("qty")[0].value);
                totalMoney += (price * qty1);

            }
            totalMoney-=(priceR*qty);
            console.log(totalMoney);
            document.getElementById("totalMoney").innerHTML = totalMoney.toLocaleString('en-US') + " VND";


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
function removeAll(){
    var item = document.getElementsByClassName("cart-item");
    for(var i=0; i<item.length;i++) {
        var row = item[i];
        var id = row.getElementsByClassName("idPro")[0].value;
        removeCart(id);
    }
    $.ajax({
        success: function (){


            document.getElementById("totalPro").innerHTML="0";
            document.getElementById("totalPro1").innerHTML="0";

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
    var title = "Giá từ " + p1.toLocaleString('en-US')+" VND đến "+ p2.toLocaleString('en-US')+" VND";
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

                row.getElementsByClassName("shoping__cart__total")[0].innerHTML = total.toLocaleString('en-US') + " VND";

                }
                document.getElementById("totalPro").innerHTML = totalPro.toString();
                document.getElementById("totalPro1").innerHTML = totalPro.toString();
                document.getElementById("totalMoney").innerHTML = totalMoney.toLocaleString('en-US') + " VND";
                document.getElementById("payment").innerHTML = totalMoney.toLocaleString('en-US') + " VND";

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
/*-------------------
   check email
  --------------------- */
function  checkEmail(x) {
    var atposition = x.indexOf("@");
    var dotposition = x.lastIndexOf(".");
    if (atposition < 1 || dotposition < (atposition + 2)
        || (dotposition + 2) >= x.length) {
        return false;
    }
        return true;
}
/*-------------------
   forgot password
  --------------------- */
async function forgotPassword() {
    const {value: email} = await Swal.fire({
        title: 'Quên mật khẩu',
        input: 'email',
        inputLabel: 'Email của bạn:',
        inputPlaceholder: 'Nhập email của bạn...',
        confirmButtonColor: '#ff96b7',
        showCancelButton: true,
        cancelButtonText:'Hủy',
        confirmButtonText:'Tiếp tục',
        inputValidator: (value) => {
            return new Promise((resolve) => {
                if (!checkEmail(value)) {
                    resolve('Email không hợp lệ!')

                } else {
                    resolve();
                }
            })
        }
    })
    if(email){
        var url="ForgotPassword?email="+ email.toString();
        $.ajax({
            url: url,
            type: "GET",
            success: async function (response) {
                if (parseInt(response) ===1) {

                    Swal.fire({
                        text:"Tài khoản không tồn tại!",
                        icon: 'error',
                        confirmButtonColor: '#ff96b7'});
                }
                else {
                    const {value: code} = await Swal.fire({
                        title: 'Xác minh tài khoản',
                        input: 'number',
                        inputLabel: 'Mã xác nhận',
                        inputPlaceholder: 'Nhập mã xác nhận...',
                        confirmButtonColor: '#ff96b7',
                        confirmButtonText:'Xác nhận',
                    })
                    if(checkCode(parseInt(code), parseInt(response))) {
                        removePass(email);
                    }
                }
            }
        });

    }

}

function checkCode(c1, c2) {
    if (c1 === c2) {
     return true;

    }else {
        Swal.fire({
            text: 'Mã xác nhận không đúng!',
            icon: 'error',
            confirmButtonColor: '#ff96b7'
        }).then((result) => {
            if (result.isConfirmed) {
                location.reload();

        }
            });
        return false;
    }
}


/*-------------------
   remove password
  --------------------- */
async function checkPass(email,pass) {
    const {value: password} = await Swal.fire({
        title: 'Mật khẩu hiện tại',
        input: 'password',
        inputLabel: 'Nhập vào mật khẩu hiện tại của bạn: ',
        inputPlaceholder: 'Nhập mật khẩu...',
        confirmButtonColor: '#ff96b7',
        inputAttributes: {
            autocapitalize: 'off',
            autocorrect: 'off'
        }
    })

    if (password) {
        var url = "UpdatePassword?oldPass=" +password.toString();
        $.ajax({
            url: url,
            type: "GET",
            success: function (response) {
                console.log(response.toString());

                if(parseInt(response) === 1){

                    removePass(email);
                }
                else{
                    Swal.fire({
                        text:'Mật khẩu đã nhập không đúng!',
                        icon: 'error',
                        confirmButtonColor: '#ff96b7'});
                }
            }
        });

    }
}
async function removePass(email) {
    const {value: formValues} = await Swal.fire({
        title: 'Đặt lại mật khẩu',
        html:
            '<label>Nhập mật khẩu mới:</label>' +
            '<input id="swal-input1" type="password" class="swal2-input" placeholder="Nhập mật khẩu mới...">' +
            '<label >Nhập lại mật khẩu:</label>' +
            '<input id="swal-input2" type="password" class="swal2-input" placeholder="Nhập lại mật khẩu...">',
        focusConfirm: false,
        confirmButtonColor: '#ff96b7',
        confirmButtonText: 'Xác nhận',
        preConfirm: () => {
            var p1 = document.getElementById('swal-input1').value;
            var p2 = document.getElementById('swal-input2').value;
            if (p1 == p2) {
                return [p1];

            }
            else{
                return 0;
            }
        }
    })
    if (formValues != 0) {
        var url = "UpdatePassword?password=" + formValues + "&email=" + email;
        $.ajax({
            url: url,
            type: "GET",
            success: function () {
                Swal.fire({
                    text: 'Đổi mật khẩu thành công!',
                    icon: 'success',
                    confirmButtonColor: '#ff96b7'
                });
            }
        });
    } else {
        Swal.fire({
            text: 'Đổi mật khẩu không thành công. Vui lòng kiểm tra lại!',
            icon: 'error',
            confirmButtonColor: '#ff96b7'
        });
    }

}
function check(email) {

    var url = "Signup?email=" + email;


    $.ajax({
        url: url,
        type: "POST",
        success: async function (response) {
            const {value: code} = await Swal.fire({
                title: 'Xác minh tài khoản',
                input: 'text',
                inputLabel: 'Mã xác nhận',
                inputPlaceholder: 'Nhập mã xác nhận...',
                confirmButtonColor: '#ff96b7',
                confirmButtonText: 'Xác nhận',
            })

            checkCode(parseInt(code), parseInt(response));

        }
    });
}

/*-------------------
   addNewOrder
  --------------------- */
function addOrder(){
    var ten = document.getElementById("ten").value;
    var diachi = document.getElementById("diachi").value;
    var phone = document.getElementById("phone").value;
    var email = document.getElementById("email").value;
    var ghichu = document.getElementById("ghichu").value;
    var url  ="AddNewOrder";
    $.ajax({
        url: url,
        type: "GET",
        data: {ten:ten,
            diachi:diachi,
            phone:phone,
            email:email,
            ghichu: ghichu},
        success: function (){
            document.getElementById("totalPro").innerHTML="0";
            document.getElementById("totalPro1").innerHTML="0";
            document.getElementById("emptyPro").innerHTML = "" +
                "                                <h4>Đơn hàng</h4>\n" +
                "                                <div class=\"checkout__order__products\">Sản Phẩm <span>Tổng</span></div>\n" +
                "                                <ul class=\"\">\n" +
                "                                    <li>\n" +
                "                                    </li>\n" +
                "                                </ul>\n" +
                "                                <div class=\"checkout__order__subtotal\">Tạm tính <span>0 VND</span></div>\n" +
                "                                <div class=\"checkout__order__total\">Tổng <span>0 VND</span></div>\n" +
                "\n" +
                "\n" +
                "                                <div class=\"checkout__input__checkbox\">\n" +
                "                                    <label for=\"payment\" >\n" +
                "                                       Thanh Toán Khi Nhận Hàng\n" +
                "                                        <input type=\"checkbox\" id=\"payment\">\n" +
                "                                        <span class=\"checkmark\"></span>\n" +
                "                                    </label>\n" +
                "                                </div>\n" +
                "                                <button onclick=\"cartEmpty()\" type=\"submit\" class=\"site-btn\" >ĐẶT HÀNG</button>\n" +
                "                            ";

            Swal.fire({
                text:'Đặt hàng thành công!',
                icon: 'success',
                showCancelButton: true,
                cancelButtonText:'Mua tiếp',
                confirmButtonText: 'Xem đơn đặt',
                confirmButtonColor: '#ff96b7'}).then((result) => {
                if (result.isConfirmed) {
                    location.href = "MyOrder";
                }
                else{
                    location.href = "ListProduct";
                }
            }
            );

        }
    });
}
function cartEmpty(){
    Swal.fire({
        text:'Giỏ hàng của bạn đang trống!',
        icon: 'error',
        confirmButtonText: 'Thêm sản phẩm',
        confirmButtonColor: '#ff96b7'}).then((result) => {
            if (result.isConfirmed) {
                location.href = "/ListProduct";
            }
        }
    );
}
function cancelOrder(mdh){
    var url  = "RemoveOrder";

    $.ajax({
        url: url,
        type: "GET",
        data: {mahd:mdh},
        success: function (){

            Swal.fire({
                text:'Bạn có chắc muốn hủy đơn hàng này không?',
                icon: 'question',
                showCancelButton: true,
                cancelButtonText:'Quay lại',
                confirmButtonText: 'Hủy Đơn Hàng',
                confirmButtonColor: '#ff96b7'}).then((result) => {
                    if (result.isConfirmed) {
                        document.getElementById(mdh).innerHTML = "<button onclick=\"buyAgain('"+mdh+"')\" type=\"submit\" style=\"border:1px solid #ccc;\" class=\"btn rounded-0 py-2 ml-2\" >\n" +
                            "                                    Mua lại\n" +
                            "                                </button>";
                        document.getElementById("statusName"+mdh).innerHTML = "<span>ĐÃ HỦY</span>";
                    }
                }
            );
        }
    });
}
function buyAgain(mdh){
    var url  = "BuyAgain";

    $.ajax({
        url: url,
        type: "GET",
        data: {mahd:mdh},
        success: function (){

            Swal.fire({
                text:'Bạn muốn mua lại đơn hàng này?',
                icon: 'question',
                showCancelButton: true,
                cancelButtonText:'Quay lại',
                confirmButtonText: 'Mua lại',
                confirmButtonColor: '#ff96b7'}).then((result) => {
                    if (result.isConfirmed) {
                        document.getElementById(mdh).innerHTML = " <button onclick=\"cancelOrder('"+mdh+"')\" type=\"submit\" style=\"border:1px solid #ccc;\" class=\"btn rounded-0 py-2 ml-2\" >\n" +
                            "                                            Hủy đơn hàng\n" +
                            "                                </button>";
                        document.getElementById("statusName"+mdh).innerHTML = "<span>CHỜ XÁC NHẬN</span>";
                    }
                }
            );
        }
    });
}

/*-------------------
   change profile user
  --------------------- */
function changeProfile(){
    var ten = document.getElementById("ten").value;
    var diachi = document.getElementById("diachi").value;
    var phone = document.getElementById("phone").value;
    var email = document.getElementById("email").value;
    var url  ="EditUserProfile";
    $.ajax({
        url: url,
        type: "GET",
        data: {ten:ten,
            diachi:diachi,
            phone:phone,
            email:email
            },
        success: function (){
            Swal.fire({
                text:'Lưu thay đổi thành công!',
                icon: 'success',
                confirmButtonText: 'OK',
                confirmButtonColor: '#ff96b7'})

        }
    });
}
// function to hide a blog
function hideBlog(blogID) {
    // get the relevant product element
    var productElement = document.getElementById(blogID);

    // set the visibility to hidden
    productElement.style.visibility = 'hidden';
}