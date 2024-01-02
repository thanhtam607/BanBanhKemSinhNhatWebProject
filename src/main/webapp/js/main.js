
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


    $('.hero__categories__all').on('click', function () {
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
    // $("select").niceSelect();

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
        var newVal = parseInt(oldValue);
        if ($button.hasClass('inc')) {
            newVal += 1;
        } else {
            // Don't allow decrementing below zero
            if (oldValue > 1) {
                newVal -= 1;
            } else {
                newVal = 1;
            }
        }
        console.log(newVal);
        $button.parent().find('input').val(newVal);

    });
    /*-------------------
       get ward name by distID
      --------------------- */
    $("#inputGroupSelect02").change(function () {
        var distID = $(this).val();
        var url = "GetWardName";
        $.ajax({
            url: url,
            type: "POST",
            data: {
                distID: distID
            },
            success: function (data) {

                $("#inputGroupSelect03").html(data);
                // $("select").niceSelect();
                $("#inputGroupSelect03").removeAttr("disabled");
            }

        });
    });
    /*-------------------
           get fee and leadtime in bill
          --------------------- */
    $("#inputGroupSelect03").change(function () {
        var to_dist_id = $("#inputGroupSelect02").val();
        var to_ward_id = $(this).val();
        console.log(to_dist_id, to_ward_id);
        $.ajax({
            url: "GetFee",
            type: "POST",
            data: {
                to_dist_id: to_dist_id,
                to_ward_id: to_ward_id
            },
            success: function (data) {
                console.log(data)
                $(".checkout__order__fee span").text(data);
                var oldTotal = $(".checkout__order__subtotal span").text();
                var newTotal = parseInt(oldTotal.replace(/,/g, '')) + parseInt($(".checkout__order__fee span").text().replace(/,/g, ''));
                $(".checkout__order__total span").text(newTotal.toLocaleString('en-US'));

            }

        });
        $.ajax({
            url: "GetLeadTime",
            type: "POST",
            data: {
                to_dist_id: to_dist_id,
                to_ward_id: to_ward_id
            },
            success: function (data) {
                $("#leadTime").text(data);
            }

        });
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

function notLogged() {
    Swal.fire({
        text: 'Đăng nhập để tiếp tục!',
        icon: 'error',
        showCancelButton: true,
        cancelButtonText: 'Thoát',
        confirmButtonText: 'Đăng nhập',
        confirmButtonColor: '#ff96b7'
    }).then((result) => {
            if (result.isConfirmed) {
                location.href = "signin.jsp";
            }
        }
    );
};

function removeAllCart() {
    Swal.fire({
        text: 'Bạn có chắc muốn xóa tất cả sản phẩm trong giỏ hàng?',
        icon: 'question',
        showCancelButton: true,
        cancelButtonText: 'Hủy',
        confirmButtonText: 'Xác nhận',
        confirmButtonColor: '#ff96b7'
    }).then((result) => {
            if (result.isConfirmed) {
                removeAll()
            }
        }
    );
};

/*-------------------
   addToCart
  --------------------- */
function addToCartI(id) {

    var qty = 1;

    var url = "AddToCart?masp=" + id + "&soluong=" + qty;
    var totalPro = parseInt(document.getElementById("totalPro").innerHTML);
    $.ajax({
        url: url,
        type: "GET",
        success: function () {
            totalPro += 1;
            document.getElementById("totalPro").innerHTML = totalPro.toString();
            document.getElementById("totalPro1").innerHTML = totalPro.toString();
            Swal.fire({
                text: 'Thêm sản phẩm thành công!',
                icon: 'success',
                showCancelButton: true,
                cancelButtonText: 'Mua tiếp',
                confirmButtonText: 'Đi đến giỏ hàng',
                confirmButtonColor: '#ff96b7'
            }).then((result) => {
                    if (result.isConfirmed) {
                        location.href = "shoping-cart.jsp";
                    }

                }
            );
        }
    });
};

// xóa cart
function removeCart(id) {
    var totalPro = parseInt(document.getElementById("totalPro").innerHTML);
    var qty = parseInt(document.getElementById('qty' + id).value);
    var priceR = parseInt(document.getElementById("price" + id).value);
    var url = "RemoveToCart?masp=" + id;
    $.ajax({
        url: url,
        type: "GET",
        success: function () {
            totalPro -= qty;
            var totalMoney = 0;
            var item = document.getElementsByClassName("cart-item");
            for (var i = 0; i < item.length; i++) {
                var row = item[i];
                var price = parseInt(row.getElementsByClassName("price")[0].value);
                var qty1 = parseInt(row.getElementsByClassName("qty")[0].value);
                totalMoney += (price * qty1);

            }
            totalMoney -= (priceR * qty);
            console.log(totalMoney);
            document.getElementById("totalMoney").innerHTML = totalMoney.toLocaleString('en-US') + " VND";


            document.getElementById("totalPro").innerHTML = totalPro.toString();
            document.getElementById("totalPro1").innerHTML = totalPro.toString();
            document.getElementById(id).remove();
            if (totalPro == 0) {
                document.getElementById("container-cart").remove();
                document.getElementById("shoping-cart").outerHTML = " <div class=\"cartEmpty\" name=\"cartEmpty\">\n" +
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

function removeAll() {
    var url = "RemoveAllCart";
    $.ajax({
        url: url,
        type: "POST",
        success: function () {
            document.getElementById("totalPro").innerHTML = "0";
            document.getElementById("totalPro1").innerHTML = "0";

            document.getElementById("container-cart").remove();
            document.getElementById("shoping-cart").outerHTML = " <div class=\"cartEmpty\" name=\"cartEmpty\">\n" +
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
function FilterbyPrice() {
    var pr1 = document.getElementById("minamount").value;
    var pr2 = document.getElementById("maxamount").value;
    var p1 = parseInt(pr1);
    var p2 = parseInt(pr2);
    var title = "Giá từ " + p1.toLocaleString('en-US') + " VND đến " + p2.toLocaleString('en-US') + " VND";
    var url = "ProductFilter?pricemin=" + p1 + "&pricemax=" + p2 + "&title=" + title;
    location.href = url;
}

/*-------------------
  update cart
  --------------------- */

function updateCartInc(id) {
    var newQty = parseInt(document.getElementById('qty' + id).value) + 1;
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

            for (var i = 0; i < item.length; i++) {
                var row = item[i];
                var price = parseInt(row.getElementsByClassName("price")[0].value);
                var qty = parseInt(row.getElementsByClassName("qty")[0].value);

                totalPro += qty;
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

function updateCartDec(id) {
    var newQty = parseInt(document.getElementById('qty' + id).value) - 1;
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

            for (var i = 0; i < item.length; i++) {
                var row = item[i];
                var price = parseInt(row.getElementsByClassName("price")[0].value);
                var qty = parseInt(row.getElementsByClassName("qty")[0].value);

                totalPro += qty;
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
function addToFav(id) {
    var url = "Favorite?masp=" + id;
    console.log(id);
    var totalFav = parseInt(document.getElementById("totalFav").innerHTML);
    $.ajax({
        url: url,
        type: "GET",
        success: function () {
            totalFav += 1;
            Swal.fire({
                text: 'Sản phẩm đã được thêm vào danh mục yêu thích!',
                icon: 'success',
                confirmButtonColor: '#ff96b7'
            });
            document.getElementById("totalFav").innerHTML = totalFav.toString();
            document.getElementById("totalFav1").innerHTML = totalFav.toString();
            document.getElementById("totalFav2").innerHTML = totalFav.toString();
        }

    });

};


function removeToFav(id) {
    var url = "RemoveFavorites?masp=" + id;
    var totalFav = parseInt(document.getElementById("totalFav").innerHTML);
    $.ajax({
        url: url,
        type: "GET",
        success: function () {
            totalFav -= 1;

            document.getElementById("fav" + id).remove();
            document.getElementById("totalFav").innerHTML = totalFav.toString();
            document.getElementById("totalFav1").innerHTML = totalFav.toString();
            document.getElementById("totalFav2").innerHTML = totalFav.toString();
        }
    });

};

/*-------------------
   check email
  --------------------- */
function checkEmail(x) {
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
        cancelButtonText: 'Hủy',
        confirmButtonText: 'Tiếp tục',
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
    if (email) {
        var url = "ForgotPassword?email=" + email.toString();
        $.ajax({
            url: url,
            type: "GET",
            success: async function (response) {
                if (parseInt(response) === 1) {
                    Swal.fire({
                        text: "Tài khoản không tồn tại!",
                        icon: 'error',
                        confirmButtonColor: '#ff96b7'
                    });
                } else {
                    let count = 30;
                    // setTimeout(function (){count--;}, 500);
                    const {value: code} = await Swal.fire({
                        title: 'Xác minh tài khoản',
                        input: 'number',
                        inputLabel: 'Mã xác nhận',
                        inputPlaceholder: 'Nhập mã xác nhận...',
                        confirmButtonColor: '#ff96b7',
                        confirmButtonText: 'Xác nhận',
                        html: 'Mã xác nhận có hiệu lực trong: <b></b> s',
                        timer: 31000,
                        timerProgressBar: true,
                        didOpen: () => {
                            const b = Swal.getHtmlContainer().querySelector('b')
                            timerInterval = setInterval(() => {
                                --count;
                                b.textContent = count;
                            }, 1000)
                        },
                        willClose: () => {
                            clearInterval(timerInterval)
                        }
                    })

                    if (checkCode(parseInt(code), parseInt(response))) {
                        removePass(email);

                    } else {
                        if (count <= 0) {
                            Swal.fire({
                                text: 'Mã xác nhận hết hiệu lực!',
                                icon: 'error',
                                confirmButtonColor: '#ff96b7'
                            })
                        } else {
                            $.ajax({
                                url: "AddLog",
                                type: "POST",
                                data: {
                                    content: "Nhập sai mã xác thực", src: location.pathname.toString(),
                                    email: email, level: 2
                                },
                                success: function () {
                                    Swal.fire({
                                        text: 'Mã xác nhận không đúng! ',
                                        icon: 'error',
                                        confirmButtonColor: '#ff96b7'
                                    }).then((result) => {
                                        if (result.isConfirmed) {
                                            location.reload();

                                        }
                                    });
                                }
                            });
                        }
                    }
                }
            }
        });

    }

}

function checkCode(c1, c2) {
    if (c1 === c2) {
        return true;

    } else {

        return false;
    }
}


/*-------------------
   remove password
  --------------------- */
async function checkPass(email, pass) {
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
        var url = "UpdatePassword?oldPass=" + password.toString();
        $.ajax({
            url: url,
            type: "GET",
            success: function (response) {
                console.log(response.toString());
                if (parseInt(response) === 1) {
                    removePass(email);
                } else {
                    Swal.fire({
                        text: 'Mật khẩu đã nhập không đúng!',
                        icon: 'error',
                        confirmButtonColor: '#ff96b7'
                    });
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

            } else {
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

function check() {
    var email = document.getElementById("email").value;
    var user = document.getElementById("name").value;
    var pass = document.getElementById("pass").value;
    var repass = document.getElementById("repass").value;
    var saveLogin = document.getElementById("save-login").checked;
    var url = "Signup?email=" + email;
    var urlSignup = "Signup?email=" + email + "&pass=" + pass + "&saveLogin=" + saveLogin + "&name=" + user;
    if (repass == pass) {
        $.ajax({
            url: url,
            type: "POST",
            success: async function (response) {
                let count = 30;
                const {value: code} = await Swal.fire({
                    title: 'Xác minh tài khoản',
                    input: 'text',
                    inputLabel: 'Mã xác nhận',
                    inputPlaceholder: 'Nhập mã xác nhận...',
                    confirmButtonColor: '#ff96b7',
                    confirmButtonText: 'Xác nhận',
                    html: 'Mã xác nhận có hiệu lực trong: <b></b> s',
                    timer: 31000,
                    timerProgressBar: true,
                    didOpen: () => {
                        const b = Swal.getHtmlContainer().querySelector('b')
                        timerInterval = setInterval(() => {
                            --count;
                            b.textContent = count;
                        }, 1000)
                    },
                    willClose: () => {
                        clearInterval(timerInterval)
                    }
                })

                if (checkCode(parseInt(code), parseInt(response))) {
                    $.ajax({
                        url: urlSignup,
                        type: "POST",
                        success: async function (response) {
                            Swal.fire({
                                text: 'Đăng ký thành công!',
                                icon: 'success',
                                confirmButtonColor: '#ff96b7'
                            }).then((result) => {
                                location.href = response.toString();
                            });
                        }
                    });
                } else {
                    if (count <= 0) {
                        Swal.fire({
                            text: 'Mã xác nhận hêt hiệu lực!',
                            icon: 'error',
                            confirmButtonColor: '#ff96b7'
                        });
                    } else {
                        Swal.fire({
                            text: 'Mã xác nhận không đúng. Vui lòng kiểm tra lại!',
                            icon: 'error',
                            confirmButtonColor: '#ff96b7'
                        }).then((result) => {
                            location.reload();
                        });

                    }
                }
            }
        });
    } else {
        Swal.fire({
            text: 'Mật khẩu nhập lại không đúng. Vui lòng kiểm tra lại!',
            icon: 'error',
            confirmButtonColor: '#ff96b7'
        })
    }
}

/*-------------------
   addNewOrder
  --------------------- */

function addOrder() {
    var selectElement1 = document.getElementById("inputGroupSelect01");
    var selectElement2 = document.getElementById("inputGroupSelect02");
    var selectElement3 = document.getElementById("inputGroupSelect03");
    var ten = document.getElementById("ten").value;
    var diachitxt = "";

    if (document.getElementById("diachi").value !== "") {
        diachitxt =
            document.getElementById("diachi").value +
            " - " +
            selectElement3.options[selectElement3.selectedIndex].textContent +
            " - " +
            selectElement2.options[selectElement2.selectedIndex].textContent +
            " - " +
            selectElement1.options[selectElement1.selectedIndex].textContent;
    } else {
        diachitxt =
            selectElement3.options[selectElement3.selectedIndex].textContent +
            " - " +
            selectElement2.options[selectElement2.selectedIndex].textContent +
            " - " +
            selectElement1.options[selectElement1.selectedIndex].textContent;
    }

    var huyentxt = selectElement2.value;
    var xatxt = selectElement3.value;

    var huyen = huyentxt.replace(/"/g, "");
    var xa = xatxt.replace(/"/g, "");
    var diachi = diachitxt.replace(/"/g, "");

    var phone = document.getElementById("phone").value;
    var email = document.getElementById("email").value;
    var ghichu = document.getElementById("ghichu").value;

    var leadTime = document.getElementById("leadTime").innerText;

    var pro_bill_t = document.getElementById("pro_bill").innerText;
    var pro_bill = parseInt(pro_bill_t.replace(/,/g, ""));

    var feeTt = document.getElementById("fee").innerText;
    var fee = parseInt(feeTt.replace(/,/g, ""));

    var totalBilltext = document.getElementById("totalBill").innerText;
    var totalBill = parseInt(totalBilltext.replace(/,/g, ""));

    var length = document.getElementsByName("noteD").length;
    var note = new Array();

    var keyContent = document.getElementById("keyContent").value;
    for (var i = 0; i < length; i++) {
        note[i] = document.getElementsByName("noteD")[i].value + "/";
        if (i === length - 1) {
            note[i] = document.getElementsByName("noteD")[i].value;
        }
    }

    var haveDisk = document.getElementById("payment3").checked;
    var haveDiskName = document.getElementById("payment3").value;

    var url1 = "AddNewOrder";
    $.ajax({
        url: url1,
        type: "GET",
        data: {
            ten: ten,
            email: email,
            diachi: diachi,
            phone: phone,
            ghichu: ghichu,
            haveDisk: haveDisk,
            haveDiskName: haveDiskName,
            note: note.toString(),
            totalBill: totalBill,
            fee: fee,
            pro_bill: pro_bill,
            leadTime: leadTime,
            huyen: huyen,
            xa: xa,
            keyContent: keyContent,
        },
        success: function (response) {
            console.log(response);
            if (parseInt(response) === 1) {
                // Hiển thị modal lỗi
                Swal.fire({
                    icon: 'error',
                    title: 'Oops...',
                    text: 'Hãy nhập đúng định dạng khóa riêng tư của bạn!',
                    footer: 'Vui lòng thử lại hoặc liên hệ hỗ trợ.'
                });
            } else {

                // Xử lý khi thành công
                document.getElementById("totalPro").innerHTML = "0";
                document.getElementById("totalPro1").innerHTML = "0";
                document.getElementById("emptyPro").innerHTML =
                    "<h4>Đơn hàng</h4>\n" +
                    "<div class=\"checkout__order__products\">Sản Phẩm <span>Tổng</span></div>\n" +
                    "<ul class=\"\">\n" +
                    "    <li>\n" +
                    "    </li>\n" +
                    "</ul>\n" +
                    "<div class=\"checkout__order__subtotal\">Tạm tính <span>0 VND</span></div>\n" +
                    "<div class=\"checkout__order__total\">Tổng <span>0 VND</span></div>\n" +
                    "<div class=\"checkout__input__checkbox\">\n" +
                    "    <label for=\"payment\" >\n" +
                    "       Thanh Toán Khi Nhận Hàng\n" +
                    "        <input type=\"checkbox\" id=\"payment\">\n" +
                    "        <span class=\"checkmark\"></span>\n" +
                    "    </label>\n" +
                    "</div>\n" +
                    "<button onclick=\"cartEmpty()\" type=\"submit\" class=\"site-btn\" >ĐẶT HÀNG</button>";

                Swal.fire({
                    text: 'Đặt hàng thành công!',
                    icon: 'success',
                    showCancelButton: true,
                    cancelButtonText: 'Mua tiếp',
                    confirmButtonText: 'Xem đơn đặt',
                    confirmButtonColor: '#ff96b7'
                }).then((result) => {
                    if (result.isConfirmed) {
                        location.href = "MyOrder";
                    } else {
                        location.href = "ListProduct";
                    }
                });
            }
        }
    });
}

function isBase64(str) {
    try {
        return btoa(atob(str)) === str;
    } catch (err) {
        return false;
    }
}


function cartEmpty() {
    Swal.fire({
        text: 'Giỏ hàng của bạn đang trống!',
        icon: 'error',
        confirmButtonText: 'Thêm sản phẩm',
        confirmButtonColor: '#ff96b7'
    }).then((result) => {
            if (result.isConfirmed) {
                location.href = "/ListProduct";
            }
        }
    );
}

function cancelOrder(mdh) {
    var url = "RemoveOrder";
    Swal.fire({
        text: 'Bạn có chắc muốn hủy đơn hàng này không?',
        icon: 'question',
        showCancelButton: true,
        cancelButtonText: 'Quay lại',
        confirmButtonText: 'Hủy Đơn Hàng',
        confirmButtonColor: '#ff96b7'
    }).then((result) => {
            if (result.isConfirmed) {
                document.getElementById(mdh).innerHTML = "<button onclick=\"buyAgain('" + mdh + "')\" type=\"submit\" style=\"border:1px solid #ccc;\" class=\"btn rounded-0 py-2 ml-2\" >\n" +
                    "                                    Mua lại\n" +
                    "                                </button>";
                document.getElementById("statusName" + mdh).innerHTML = "<span>ĐÃ HỦY</span>";
                $.ajax({
                    url: url,
                    type: "GET",
                    data: {mahd: mdh},
                    success: function () {
                        Swal.fire({
                            text: 'Hủy đơn hàng thành công',
                            icon: 'success',
                            confirmButtonText: 'OK',
                            confirmButtonColor: '#ff96b7'
                        })

                    }
                });
            }
        }
    );

}

function buyAgain(mdh) {
    var url = "BuyAgain";
    Swal.fire({
        text: 'Bạn muốn mua lại đơn hàng này?',
        icon: 'question',
        showCancelButton: true,
        cancelButtonText: 'Quay lại',
        confirmButtonText: 'Mua lại',
        confirmButtonColor: '#ff96b7'
    }).then((result) => {
            if (result.isConfirmed) {
                document.getElementById(mdh).innerHTML = " <button onclick=\"cancelOrder('" + mdh + "')\" type=\"submit\" style=\"border:1px solid #ccc;\" class=\"btn rounded-0 py-2 ml-2\" >\n" +
                    "                                            Hủy đơn hàng\n" +
                    "                                </button>";
                document.getElementById("statusName" + mdh).innerHTML = "<span>CHỜ XÁC NHẬN</span>";

                $.ajax({
                    url: url,
                    type: "GET",
                    data: {mahd: mdh},
                    success: function () {
                        Swal.fire({
                            text: 'Xác nhận mua lại thành công!',
                            icon: 'success',
                            confirmButtonText: 'OK',
                            confirmButtonColor: '#ff96b7'
                        })

                    }
                });
            }
        }
    );

}

/*-------------------
   change profile user
  --------------------- */
function changeProfile() {
    var ten = document.getElementById("ten").value;
    var diachi = document.getElementById("diachi").value;
    var phone = document.getElementById("phone").value;
    var email = document.getElementById("email").value;
    var url = "EditUserProfile";
    $.ajax({
        url: url,
        type: "GET",
        data: {
            ten: ten,
            diachi: diachi,
            phone: phone,
            email: email
        },
        success: function () {
            Swal.fire({
                text: 'Lưu thay đổi thành công!',
                icon: 'success',
                confirmButtonText: 'OK',
                confirmButtonColor: '#ff96b7'
            })

        }

    });
}

// ========================================================= //
function confirmGenKey(userId, hasKey) {
    const swalConfig = {
        html: `
            <div>
                <p style="font-size: 20px; font-weight: bold">Bạn có chắc chắn muốn tạo lại khóa mới?</p>
                <p style="font-size: smaller; color: #092a79; margin-top: -10px;">Nếu bạn đã có khóa, hãy nhấn thêm để sử dụng khóa của bạn </p>
            </div>
        `,
        icon: 'question',
        showCancelButton: true,
        cancelButtonText: 'Quay lại',
        confirmButtonText: 'OK',
        confirmButtonColor: '#ff96b7',
        showDenyButton: true,
        denyButtonText: 'Thêm khóa',
        denyButtonColor: '#ff96b7',
    };

    Swal.fire(swalConfig).then((result) => {
        if (result.isConfirmed) {
            genKey(userId);
        } else if (result.isDenied) {
            openModal();
        }
    });
    function openModal() {
        document.getElementById('myModal').style.display = 'block';
    }
}


function genKey(userId) {
    Swal.fire({
        title: 'Yêu cầu đang được xử lý...',
        allowOutsideClick: false,
        showConfirmButton: false,
        didOpen: () => {
            Swal.showLoading();
        }
    });
    $.ajax({
        url: "Verify",
        type: "POST",
        data: {userId: userId},
        success: async function (response) {
            Swal.close();
            let count = 30;
            const {value: code} = await Swal.fire({
                title: 'Xác minh tài khoản',
                input: 'text',
                inputLabel: 'Mã xác nhận',
                inputPlaceholder: 'Nhập mã xác nhận...',
                confirmButtonColor: '#ff96b7',
                confirmButtonText: 'Xác nhận',
                html: 'Mã xác nhận có hiệu lực trong: <b></b> s',
                timer: 31000,
                timerProgressBar: true,
                didOpen: () => {
                    const b = Swal.getHtmlContainer().querySelector('b')
                    timerInterval = setInterval(() => {
                        --count;
                        b.textContent = count;
                    }, 1000)
                },
                willClose: () => {
                    clearInterval(timerInterval)
                }
            });
            $.ajax({
                url: "Verify",
                type: "POST",
                data: {otp: code},
                success: async function (response) {
                    if (parseInt(response) === 1) {
                        createKey(userId);
                    } else {
                        Swal.close();
                        if (count <= 0) {
                            Swal.fire({
                                text: 'Mã xác nhận hêt hiệu lực!',
                                icon: 'error',
                                confirmButtonColor: '#ff96b7'
                            });
                        } else {
                            Swal.fire({
                                text: 'Mã xác nhận không đúng. Vui lòng kiểm tra lại!',
                                icon: 'error',
                                confirmButtonColor: '#ff96b7'
                            }).then((result) => {
                                location.reload();
                            });
                        }
                    }
                }
            });

        }
    });
}

function createKey(userId) {
    Swal.fire({
        title: 'Vui lòng chờ...',
        allowOutsideClick: false,
        showConfirmButton: false,
        didOpen: () => {
            Swal.showLoading();
        }
    });
    $.ajax({
        url: "GenKey",
        type: "POST",
        data: {userId: userId},
        success: function () {
            Swal.close();
            Swal.fire({
                title: "Yêu cầu tạo khóa của bạn đã được thực hiện!",
                text: 'Khóa mới sẽ được tới email của bạn.',
                icon: 'success',
                confirmButtonText: 'OK',
                confirmButtonColor: '#ff96b7'
            }).then((result) => {
                location.reload();
            });

        }
    });

}

function requestKey(userId) {
    Swal.fire({
        title: "Yêu cầu tạo khóa",
        text: 'Tài khoản của bạn hiện chưa có khóa hoặc khóa trước đó đã bị vô hiệu hóa. Vui lòng tạo khóa để thực hiện đặt hàng!',
        icon: 'info',
        confirmButtonText: 'OK',
        confirmButtonColor: '#ff96b7',
        showCancelButton: true,
        cancelButtonText: 'Quay lại'
    }).then((result) => {
        if (result.isConfirmed) {
            genKey(userId);
        }
    });
}
function AddNewPublicKey(){
    var userId = document.getElementById("idUser").value;
    var keyContent2 = document.getElementById("keyContent2").value;
    var pbKey = document.getElementById("publicKey");
    var publicKeyCheck = extractRSAPublicKey(keyContent2);
    var getType = getKeyType(keyContent2);
    pbKey.value = publicKeyCheck;
    var publicKey = document.getElementById("publicKey").value;
    var url = "AddPublicKey";

    if (!publicKey) {
        Swal.fire({
            title: 'Oops...',
            text: 'Public key không đúng định dạng!',
            icon: 'error',
            confirmButtonText: 'OK',
            confirmButtonColor: '#ff96b7'
        });
        return;
    }
    if (getType === '1') {
        Swal.fire({
            title: 'Oops...',
            text: 'Đây không phải là 1 public key!',
            icon: 'error',
            confirmButtonText: 'OK',
            confirmButtonColor: '#ff96b7'
        });
        return;
    }

    $.ajax({
        url: url,
        type: "GET",
        data: {
            userId : userId,
            publicKey: publicKey,
        },
        success: function (response) {
            console.log(response);
            if (parseInt(response) === 1) {
                // Hiển thị modal lỗi
                Swal.fire({
                    icon: 'error',
                    title: 'Oops...',
                    text: 'Khóa công khai đã có trước đó!',
                });
            } else {
                Swal.fire({
                    text: 'Thêm khóa công khai thành công!',
                    icon: 'success',
                    confirmButtonText: 'OK',
                    confirmButtonColor: '#ff96b7'
                })
            }
            document.getElementById('myModal').style.display = 'none';
            document.getElementById('keyContent2').value = '';
            document.getElementById('publicKey').value = '';
            document.getElementById('filePath').value = '';
        },

        error: function () {
            Swal.fire({
                title: 'Oops...',
                text: 'Đã xảy ra lỗi khi thêm khóa công khai!',
                icon: 'error',
                confirmButtonText: 'OK',
                confirmButtonColor: '#ff96b7'
            });
        }
    });
}


// ========================================================= //

function openModal() {
    document.getElementById('myModal').style.display = 'block';
}

// Function to close the modal
function closeModal() {
    document.getElementById('myModal').style.display = 'none';
}

// Function to enable/disable file input based on checkbox
function chooseFile() {
    var fileButton = document.getElementById('fileButton');
    var fileInput = document.getElementById('fileInput');
    var keyContent = document.getElementById('keyContent');
    var errorText = document.getElementById('errorText');
    var file = document.getElementById('file');
    var filePath = document.getElementById('filePath');

    fileButton.style.color = 'black';
    fileInput.disabled = true;
    fileInput.value = '';
    keyContent.value = '';
    filePath.value = '';

    file.onchange = function (event) {
        var reader = new FileReader();
        filePath.value = event.target.value;

        reader.onload = function () {
            var fileContent = reader.result;
            var publicKey = extractRSAPublicKey(fileContent);
            if (publicKey) {
                keyContent.value = publicKey;
                keyContent.setAttribute('readonly', true);
                errorText.style.display = 'none';
            } else {
                errorText.style.display = 'block';
                keyContent.value = '';
                keyContent.removeAttribute('readonly');
            }
        };

        reader.readAsText(event.target.files[0]);
    };

    fileButton.style.opacity = '1';
    keyContent.removeAttribute('readonly');
    file.click();
}


document.addEventListener('DOMContentLoaded', (event) => {
    var modal = document.getElementById('myModal');
    var modalContent = document.querySelector('.modal-content');

    window.onclick = function (event) {
        if (event.target == modal) {
            closeModal();
        }
    };

    if (modalContent) {
        modalContent.onclick = function (event) {
            event.stopPropagation();
        };
    }
});
function goBack2() {
    var keyContent2 = document.getElementById('keyContent2');
    keyContent2.removeAttribute('readonly'); // Allow editing again
    keyContent2.value = '';
    var filePath = document.getElementById('filePath');
    var fileInput = document.getElementById('fileInput');
     var error =  document.getElementById('errorText');
     error.style.display = 'none';
    fileInput.value = '';
    filePath.value = '';
    var file = document.getElementById('file');
    file.value = '';

    closeModal();
}

// Function to go back in the modal
function goBack() {
    var keyContent = document.getElementById('keyContent');
    keyContent.removeAttribute('readonly'); // Allow editing again
    keyContent.value = '';
    var filePath = document.getElementById('filePath');
    var fileInput = document.getElementById('fileInput');
    var error =  document.getElementById('errorText');
    error.style.display = 'none';
    filePath.value = '';
    fileInput.value = '';
    var file = document.getElementById('file');
    file.value = '';

    closeModal();
}
function chooseFilePbK() {
    var fileButton = document.getElementById('fileButton');
    var fileInput = document.getElementById('fileInput');
    var keyContent2 = document.getElementById('keyContent2');
    var errorText = document.getElementById('errorText');
    var file = document.getElementById('file');
    var filePath = document.getElementById('filePath');

    fileButton.style.color = 'black';
    fileInput.disabled = true;
    fileInput.value = '';
    keyContent2.value = '';
    filePath.value = '';
    file.onchange = function (event) {
        var reader = new FileReader();
        filePath.value = event.target.value;

        reader.onload = function () {
            var fileContent = reader.result;

            var publicKey = extractRSAPublicKey(fileContent);
            if (publicKey) {
                keyContent2.value = publicKey;
                keyContent2.setAttribute('readonly', true);
                errorText.style.display = 'none';
            } else {
                errorText.style.display = 'block';
                keyContent2.value = '';
                keyContent2.removeAttribute('readonly');
            }
        };

        reader.readAsText(event.target.files[0]);
    };

    fileButton.style.opacity = '1';
    keyContent2.removeAttribute('readonly');
    file.click();
}




function extractRSAPublicKey(str) {
    // Xóa kí tự trống và kí tự xuống dòng
    const cleanedString = str.replace(/[\r\n\s]/g, '');

    // Tìm vị trí đầu tiên của "MII" trong chuỗi
    const matchStart = cleanedString.indexOf("MII");

    if (matchStart === -1) {
        return null;
    }

    // Tìm vị trí đầu tiên của kí tự không phải Base64 sau "MII"
    const base64Block = cleanedString.slice(matchStart);
    const endRegex = /[^A-Za-z0-9+/=]/;
    const matchEnd = base64Block.match(endRegex);

    // Tách từ "MII" đến kí tự không phải Base64 hoặc đến cuối chuỗi nếu không tìm thấy kí tự không phải Base64
    const extractedBlock = matchEnd ? base64Block.slice(0, matchEnd.index) : base64Block;

    // Kiểm tra chiều dài của chuỗi trích xuất và trả về null nếu không đủ độ dài của public key
    if (extractedBlock.length >= 256) {
        return extractedBlock;
    } else {
        return null;
    }
}
function getKeyType(keyString) {
    const cleanedKeyString = keyString.replace(/\r?\n|\r/g, '');
    const keyLength = cleanedKeyString.length;
    if (keyLength > 1000) {
        return '1';
    } else {
        return '0';
    }
}
