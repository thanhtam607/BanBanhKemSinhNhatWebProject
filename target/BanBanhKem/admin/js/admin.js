$(document).ready(function() {
    "use strict"; // start of use strict

    /*==============================
    Menu
    ==============================*/
    $('.header__btn').on('click', function() {
        $(this).toggleClass('header__btn--active');
        $('.header').toggleClass('header--active');
        $('.sidebar').toggleClass('sidebar--active');
    });

    /*==============================
    Filter
    ==============================*/
    $('.filter__item-menu li').each(function() {
        $(this).attr('data-value', $(this).text().toLowerCase());
    });

    $('.filter__item-menu li').on('click', function() {
        var text = $(this).text();
        var item = $(this);
        var id = item.closest('.filter').attr('id');
        $('#' + id).find('.filter__item-btn input').val(text);
    });

    /*==============================
    Tabs
    ==============================*/
    $('.profile__mobile-tabs-menu li').each(function() {
        $(this).attr('data-value', $(this).text().toLowerCase());
    });

    $('.profile__mobile-tabs-menu li').on('click', function() {
        var text = $(this).text();
        var item = $(this);
        var id = item.closest('.profile__mobile-tabs').attr('id');
        $('#' + id).find('.profile__mobile-tabs-btn input').val(text);
    });

    /*==============================
    Modal
    ==============================*/
    $('.open-modal').magnificPopup({
        fixedContentPos: true,
        fixedBgPos: true,
        overflowY: 'auto',
        type: 'inline',
        preloader: false,
        focus: '#username',
        modal: false,
        removalDelay: 300,
        mainClass: 'my-mfp-zoom-in',
    });

    $('.modal__btn--dismiss').on('click', function(e) {
        e.preventDefault();
        $.magnificPopup.close();
    });

    /*==============================
    Select2
    ==============================*/
    $('#quality').select2({
        placeholder: "Choose quality",
        allowClear: true
    });

    $('#country').select2({
        placeholder: "Choose country / countries"
    });

    $('#genre').select2({
        placeholder: "Choose genre / genres"
    });

    $('#subscription, #rights').select2();

    /*==============================
    Upload cover
    ==============================*/
    function readURL(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();

            reader.onload = function(e) {
                $('#form__img').attr('src', e.target.result);
            }

            reader.readAsDataURL(input.files[0]);
        }
    }

    $('#form__img-upload').on('change', function() {
        readURL(this);
    });

    /*==============================
    Upload video
    ==============================*/
    $('.form__video-upload').on('change', function() {
        var videoLabel = $(this).attr('data-name');

        if ($(this).val() != '') {
            $(videoLabel).text($(this)[0].files[0].name);
        } else {
            $(videoLabel).text('Upload video');
        }
    });

    /*==============================
    Upload gallery
    ==============================*/
    $('.form__gallery-upload').on('change', function() {
        var length = $(this).get(0).files.length;
        var galleryLabel = $(this).attr('data-name');

        if (length > 1) {
            $(galleryLabel).text(length + " files selected");
        } else {
            $(galleryLabel).text($(this)[0].files[0].name);
        }
    });

    /*==============================
    Scroll bar
    ==============================*/
    $('.sidebar__nav-wrap').mCustomScrollbar({
        axis: "y",
        scrollbarPosition: "outside",
        theme: "custom-bar"
    });

    $('.main__table-wrap').mCustomScrollbar({
        axis: "x",
        scrollbarPosition: "outside",
        theme: "custom-bar2",
        advanced: {
            autoExpandHorizontalScroll: true
        }
    });

    $('.dashbox__table-wrap').mCustomScrollbar({
        axis: "x",
        scrollbarPosition: "outside",
        theme: "custom-bar3",
        advanced: {
            autoExpandHorizontalScroll: 2
        }
    });

    /*==============================
    Bg
    ==============================*/
    $('.section--bg').each(function() {
        if ($(this).attr("data-bg")) {
            $(this).css({
                'background': 'url(' + $(this).data('bg') + ')',
                'background-position': 'center center',
                'background-repeat': 'no-repeat',
                'background-size': 'cover'
            });
        }
    });

});
/*==============================
update product detail
==============================*/
function updateDetail(id){
    var sl = document.getElementById("soluong").value;
    var tonkho = document.getElementById("tonkho").value;
    var d1 = document.getElementById("ngaysx").value;
    var d2 = document.getElementById("ngayhh").value;
    var ngsx = null;
    var nghh=null;

    if(d1!= null){
        var ngaysx = new Date(document.getElementById("ngaysx").value);
        ngsx = ngaysx.getFullYear()+"-"+(ngaysx.getMonth()+1)+"-"+ ngaysx.getDate();
    }
    if(d2!= null){
        var ngayhh = new Date( document.getElementById("ngayhh").value);
        nghh = ngayhh.getFullYear()+"-"+(ngayhh.getMonth()+1)+"-"+ ngayhh.getDate();
    }
    console.log(ngsx);
    console.log(nghh)
    location.href="Product/UpdateDetails?masp="+id+"&soluong="+sl+"&tonkho="+tonkho+"&ngaysx="+ ngsx.toString()+"&ngayhh="+nghh.toString();

}

/*==============================
hiển thị file
==============================*/
$('.dandev_insert_attach').click(function() {
    if ($('.list_attach').hasClass('show-btn') === false) {
        $('.list_attach').addClass('show-btn');
    }
    var _lastimg = jQuery('.dandev_attach_view li').last().find('input[type="file"]').val();

    if (_lastimg != '') {
        var time = 0;
        var _html = '<li id="li_files_' + (time++) + '" class="li_file_hide">';
        _html += '<div class="img-wrap">';
        _html += '<span class="close" onclick="DelImg(this)">×</span>';
        _html += ' <div class="img-wrap-box"></div>';
        _html += '</div>';
        _html += '<div class="' + (time++) + '">';
        _html += '<input type="file" class="hidden" accept=".png, .jpg, .jpeg"  onchange="uploadImg(this)" name="upload"   />';
        _html += '</div>';
        _html += '</li>';
        jQuery('.dandev_attach_view').append(_html);
        jQuery('.dandev_attach_view li').last().find('input[type="file"]').click();
    } else {
        if (_lastimg == '') {
            jQuery('.dandev_attach_view li').last().find('input[type="file"]').click();
        } else {
            if ($('.list_attach').hasClass('show-btn') === true) {
                $('.list_attach').removeClass('show-btn');
            }
        }
    }
});


function uploadImg(el) {
    var file_data = $(el).prop('files')[0];
    var type = file_data.type;
    var fileToLoad = file_data;


    var fileReader = new FileReader();

    fileReader.onload = function(fileLoadedEvent) {
        var srcData = fileLoadedEvent.target.result;

        var newImage = document.createElement('img');
        newImage.src = srcData;
        var _li = $(el).closest('li');
        if (_li.hasClass('li_file_hide')) {
            _li.removeClass('li_file_hide');
        }
        _li.find('.img-wrap-box').append(newImage.outerHTML);



    }
    fileReader.readAsDataURL(fileToLoad);

}

function DelImg(el) {
    jQuery(el).closest('li').remove();

}
function chooseFile(fileInput){
    if(fileInput.files && fileInput.files[0]){
        var reader = new FileReader();
        reader.onload = function (e){
            $('#form__img').attr('src',e.target.result);
        }
        reader.readAsDataURL((fileInput.files[0]))
    }
}
function  changeHref(link){
    location.href=link;
}
let checkAll;
document.getElementById("select-all").onclick = function ()
{
    var checkboxes = document.getElementsByName("check");

    for (var i = 0; i < checkboxes.length; i++){
        if(checkAll==false || checkAll==null){
            checkboxes[i].checked = true;
        }
        else{
            checkboxes[i].checked = false;
        }
    }
    if( checkAll) checkAll= false;
    else checkAll=true;
};

function filterProduct(showMore, type){
    if(type ==null) type = document.getElementById("type").value;
    var page =parseInt(document.getElementById("page").value) ;
    if(showMore){
        ++page;
        document.getElementById("page").value=(page);
    }else{
        page=1;
        document.getElementById("page").value=(page);
    }

    $.ajax({
        url: "FilterProductForDiscount",
        type: "POST",
        data: {loaiBanh: type,  page: page},
        success: function (response){
            var productS = document.getElementById("product_table");
            if(showMore) {productS.innerHTML += response;
               }
            else productS.innerHTML = response;
        }
    });
    if(document.getElementById("select-all").checked== true){
        document.getElementById("select-all").checked=false;
        checkAll=false;
    }

}

function addDiscount(){
    var type = document.getElementById("type").value;
    var discount = document.getElementById("giamGia").value;
    var start = document.getElementById("start").value;
    var end = document.getElementById("end").value;

    if(checkAll){
        $.ajax({
            url: "Add_Discount",
            type: "POST",
            data: {loaiBanh: type,
            discount: discount,
            start: start, end: end},
            success: function (){
                Swal.fire({
                    icon: 'success',
                    title: 'Thêm khuyến mãi thành công!'
                }).then((result) => {
                    location.reload();
                });
            }

        });
    }
    else{
        var checkbox = document.getElementsByName('check');
        var listIdPros= new Array();
        var listId;
        for(let i=0; i<checkbox.length; i++){
            if(checkbox[i].checked==true){
                listIdPros.push(checkbox[i].value);
            }
        }
        listId = listIdPros.toString();
        $.ajax({
            url: "Add_Discount",
            type: "POST",
            data: {ids: listId,
                discount: discount,
                start: start, end: end},
            success: function (){
                Swal.fire({
                    icon: 'success',
                    title: 'Thêm khuyến mãi thành công!'
                }).then((result) => {
                    location.reload();
                });
            }

        });
    }
}


function showFilterDate(from, to, error) {
    if(error){
        document.getElementById("error").innerText = error.toString();
    }else{
        document.getElementById("error").innerText="";
        document.getElementById("from").value=from;
        document.getElementById("to").value=to;
        if(to ==0 && from!=0){
        document.getElementById("time").value = "Từ "+ from +" đến hiện tại";
        document.getElementById("date").value=0;

         }
        else if(to!=0 && from==0){
        document.getElementById("time").value = "Từ "+to + " trở về trước";
            document.getElementById("date").value=0;

        }
        else if(from && to){
            document.getElementById("time").value = "Từ "+ from +" đến "+to;
            document.getElementById("date").value=0;

        }

    }



}
async function filterLog(type, val) {
    var user = document.getElementById("user").value;
    var level = document.getElementById("level").value;
    var content = document.getElementById("content").value;
    var page = document.getElementById("pageNumber").value;
    var fDate =document.getElementById("from").value;
    var tDate =document.getElementById("to").value;
    var dateF=document.getElementById("date").value;
    if (type == 'user') {
        user = val;

    } else if (type == 'level') {
        level = val;
        document.getElementById("level").value = val;
    } else if (type == 'content') {
        content = val;
    }
    else if (type =='time') {
        if (val== 1) {
            const {value: formValues} = await Swal.fire({
                title: 'Lọc theo ngày',
                html:
                    '<label class="text-left col-5 mr-5" for="swal-input1">Từ ngày:</label>' +
                    '<label class="text-left col-5 " for="swal-input2">Đến ngày:</label>' +
                    '<input type="date" class="form__input col-5 mr-5" id="swal-input1">' +
                    '<input type="date" class="form__input col-5" id="swal-input2">',
                preConfirm: () => {
                    var from = document.getElementById("swal-input1").value;
                    var fromD = null;
                    var toD =null;
                    var error="";
                    if(from){
                      fromD = new Date(from);
                        fDate = fromD.getFullYear() +"-"+ (fromD.getMonth()+1)+"-"+ fromD.getDate();
                    }else{ fDate=0; }
                    var to =document.getElementById("swal-input2").value;
                    if(to){
                        toD = new Date(to);
                        tDate = toD.getFullYear() +"-"+ (toD.getMonth()+1)+"-"+ toD.getDate();
                    }else{ tDate=0;}
                    if(to && from && fromD>toD){
                        tDate=0;
                        fDate=0;
                        error = "Ngày sau phải lớn hơn ngày trước đó!"
                    }
                    return[fDate,tDate, error]
                }
            })
            showFilterDate(formValues[0], formValues[1],  formValues[2]);
            if(formValues){
                dateF=0;
            }
        }
        else if(val==2){
            const { value: date } = await Swal.fire({
                title: 'Lọc theo ngày',
                html:    '<label  class="col-12" for="swal-input1">Ngày cần lọc:</label>' +
                    '<input type="date" class="form__input col-9" id="swal-input1">',
                preConfirm: () => {
                    var date =null ;
                    if(document.getElementById("swal-input1").value){
                        date = new Date(document.getElementById("swal-input1").value);
                        date = date.getFullYear() +"-"+ (date.getMonth()+1)+"-"+ date.getDate();
                    }
                    dateF=date;
                    return dateF;
                }
            })
            if (date) {
                fDate=0;
                tDate=0;
               document.getElementById("time").value = date;
                document.getElementById("date").value = date;
                document.getElementById("from").value = 0;
                document.getElementById("to").value = 0;
                document.getElementById("error").innerText="";
            }
        }
        else {
            dateF = 0;
            fDate= 0;
            tDate=0;
            document.getElementById("time").value="Tất cả";
            document.getElementById("error").innerText="";
            document.getElementById("from").value=0;
            document.getElementById("to").value=0;
            document.getElementById("date").value=0;

        }
    }
    else {
        page = val;
    }
    var paginator = "";
    for (let i = page - 1; i <= (parseInt(page) + 2); i++) {
        console.log(i)
        if (i < 1) {
            continue;
        }
        if (i == page) {
            paginator+= '<li class="paginator__item paginator__item--active"><a onclick="filterLog(\'page\','+ i +')">'+i+' </a></li>';
        } else {
            paginator+=' <li class="paginator__item"><a onclick="filterLog(\'page\', '+i+')">'+i+'</a></li>';
        }
    }
    // var url = "LogFilter?level="+level+"&user="+user+"&content="+content+"&from="+ fDate+"&to="+tDate+"&page="+page+"&date="+dateF;
    $.ajax({
        url: "LogFilter",
        // url: url,
        type: "POST",
        data: {
            user: user,
            level: level,
            page: page,
            content: content,
            from: fDate,
            to: tDate,
            date: dateF
        },
        success: function (response) {
            document.getElementById("listLog").innerHTML = response;
            document.getElementById("paginator").innerHTML ='<li class="paginator__item paginator__item--prev">\n' +
                ' <input type="number" id="pageNumber" style="display: none" value="'+page+'">'+
                '                          <a onclick="filterLog(\'page\','+(page-1)+')"><i class="fa fa-chevron-left"></i></a>\n' +
                '                        </li>'+
                paginator +
                '<li class="paginator__item paginator__item--next">\n' +
                '                          <a onclick="filterLog(\'page\','+(page+1)+')"><i class="fa fa-chevron-right"></i></a>\n' +
                '                        </li>\n' +
                '                      </ul>';


        }
    });
}
function changePass(){
  var email = $('#email').val();
  var newpass = $('#newpass').val();
  var confirmpass = $('#confirmpass').val();
  var oldpass = $('#oldpass').val();
    $.ajax({
        url: "AdminChangePassWord",
        type: "GET",
        data:{
            email:email,
            oldpass: oldpass,
            confirmpass: confirmpass,
            newpass: newpass
        },
        success: function (response) {
            if (parseInt(response) === 0) {
                Swal.fire({
                    icon: 'success',
                    title: 'Đổi mật khẩu thành công!',
                    confirmButtonText: 'OK',
                    confirmButtonColor: '#ff96b7'
                }).then((result) => {
                    location.reload();
                });
            } else if (parseInt(response) === 1){
                Swal.fire({
                    icon: 'error',
                    text: 'Mật khẩu xác nhận không khớp!',
                    confirmButtonText: 'OK',
                    confirmButtonColor: '#ff96b7'
                }).then((result) => {
                    location.reload();
                });

            }else if (parseInt(response) === 2){
                Swal.fire({
                    icon: 'error',
                    text: 'Mật khẩu hiện tại không đúng!',
                    confirmButtonText: 'OK',
                    confirmButtonColor: '#ff96b7'
                }).then((result) => {
                    location.reload();
                });
            }

        }
    });
}
function changeProfileAdmin(){
  var username = $('#username').val();
  var phone = $('#phone').val();
  var email = $('#email').val();
  var address = $('#address').val();
    $.ajax({
        url: "AdminUpdateProfile",
        type: "POST",
        data:{
            email:email,
            username: username,
            phone: phone,
            address: address
        },
        success: function () {
                Swal.fire({
                    icon: 'success',
                    title: 'Lưu thay đổi thành công!',
                    confirmButtonText: 'OK',
                    confirmButtonColor: '#ff96b7'
                })

        }
    });
}
function editOrderUpdateCustomer(){
  var usernameRE = $('#usernameRE').val();
  var phoneRE = $('#phoneRE').val();
  var mailRE = $('#mailRE').val();
  var note = $('#note').val();
  var id = $('#idRec2').val();
  console.log(usernameRE, phoneRE, mailRE, note, id);
    $.ajax({
        url: "EditOrderUpdateCustomer",
        type: "POST",
        data:{
            usernameRE:usernameRE,
            phoneRE: phoneRE,
            mailRE: mailRE,
            note: note,
            id: id
        },
        success: function () {
                Swal.fire({
                    icon: 'success',
                    text: 'Lưu thay đổi thành công!',
                    confirmButtonText: 'OK',
                    confirmButtonColor: '#ff96b7'
                })

        }
    });
}
function editOrderUpdateDelivery(){
  var dayD = $('#dayD').val();
  var address = $('#address').val();
  var state = $('.state').val();
  var idR = $('#idR').val();
    $.ajax({
        url: "EditOrderUpdateDelivery",
        type: "POST",
        data:{
            dayD:dayD,
            address: address,
            state: state,
            id: idR
        },
        success: function () {
                Swal.fire({
                    icon: 'success',
                    text: 'Lưu thay đổi thành công!',
                    confirmButtonText: 'OK',
                    confirmButtonColor: '#ff96b7'
                })


        }
    });
}
function adminRemoveProInOrder(index){
  var id = $('#idRe').text();
  var i = $("tr#"+index).children().first().text();
  var msp = msp;
  var sl = 0;

    var sumtxt = $('#fee').text();
    var fee = parseInt(sumtxt.replace(/,/g, ''));

    var pro_bill = 0;

    // dung hang co stt đc nhan vao de lay msp
    $('#table_bill_details tr').each(function(rowIndex, row) {
        // Lặp qua từng hàng
        $(row).find("td:eq(0)").each(function(colIndex, col) {
            var value = $(col).text();
            if (value === i) {
               msp = $(row).find("td:eq(1)").text();

            }
        });

    });
    // dung hang chua msp de lay slg
    $('#table_bill_details tr').each(function(rowIndex, row) {
        // Lặp qua từng hàng
        $(row).find("td").each(function(colIndex, col) {
            var value = $(col).text();
            if (value === msp) {
                var slg_t = $(row).find("td:eq(4)").text();
                sl = parseInt(slg_t);
            }
        });

    });

    Swal.fire({
        text: 'Bạn có chắc muốn xóa sản phẩm này không?',
        icon: 'question',
        showCancelButton: true,
        cancelButtonText: 'Quay lại',
        confirmButtonText: 'Xóa',
        confirmButtonColor: '#ff96b7'
    }).then((result) => {
        if (result.isConfirmed) {
            $.ajax({
                url: "adminRemoveProInOrder",
                type: "GET",
                data:{
                    msp:msp,
                    slg: sl,
                    id: id
                },
                success: function (rep) {

                    if(parseInt(rep) === 1){
                        Swal.fire({
                            text: 'Xóa không thành công',
                            icon: 'error',
                            confirmButtonText: 'OK',
                            confirmButtonColor: '#ff96b7'
                        })
                    }else{
                        $('#table_bill_details tr').each(function(rowIndex, row) {
                            // Lặp qua từng hàng
                            $(row).find("td:eq(1)").each(function(colIndex, col) {
                                var value = $(col).text();
                                if (value === msp) {
                                    $(row).remove();
                                }
                            });

                        });
                        $("#table_bill_details tr").each(function(rowIndex, row) {
                            $(row).find("td:first").text(rowIndex);

                            $(row).find("td:eq(6)").each(function(colIndex, col) {
                                // Lấy giá trị từng cột
                                var value = $(col).text();
                                pro_bill = pro_bill + parseInt(value.replace(/,/g, ''));
                            });
                        });

                        Swal.fire({
                            text: 'Xóa thành công',
                            icon: 'success',
                            confirmButtonText: 'OK',
                            confirmButtonColor: '#ff96b7'
                        })
                        $('#pro_bill').text(pro_bill.toLocaleString('en-US')+ " VND") ;
                        var total = fee + pro_bill;
                        $('#total').text(total.toLocaleString('en-US')+ " VND");
                    }
                }, error: function (){
                    Swal.fire({
                        text: 'Xóa không thành công',
                        icon: 'error',
                        confirmButtonText: 'OK',
                        confirmButtonColor: '#ff96b7'
                    })
                }
            });
        }
    }
    );
}

function adminAddProInOrder(){
  var idRec = $('#idRec').val();
  var msp = $('#msp').val();
  var slg = $('#slg').val();
  var notes = $('#notes').val();

  var sumtxt = $('#fee').text();
  var fee = parseInt(sumtxt.replace(/,/g, ''));

  var pro_bill = 0;
    $.ajax({
        url: "adminAddProInOrder",
        type: "GET",
        data:{
            msp:msp,
            slg: slg,
            idRec: idRec,
            notes: notes
        },
        success: function (response) {
            if(parseInt(response) === 1) {
                $("#table_bill_details tr").each(function(rowIndex, row) {
                    // Lặp qua từng hàng
                    $(row).find("td").each(function(colIndex, col) {
                        // Lấy giá trị từng cột
                        var value = $(col).text();
                        if(value === msp){
                            var slg_td = $(row).find("td:eq(4)").text();
                            var price = $(row).find("td:eq(5)").text();
                            var new_slg = parseInt(slg_td) + parseInt(slg);
                            var new_price = parseInt(price.replace(/,/g, '')) * new_slg;
                            $(row).find("td:eq(4)").text(new_slg);
                            $(row).find("td:eq(6)").text(new_price.toLocaleString('en-US'));
                        }
                    });
                    $(row).find("td:eq(6)").each(function(colIndex, col) {
                        // Lấy giá trị từng cột
                        var value = $(col).text();
                        pro_bill = pro_bill + parseInt(value.replace(/,/g, ''));
                    });
                });

                $('#msp').val("");
                $('#notes').val("");
                $('#pro_bill').text(pro_bill.toLocaleString('en-US')+ " VND");
                var total = fee + pro_bill;
                $('#total').text(total.toLocaleString('en-US')+ " VND");
                $.magnificPopup.close();
            }else if (parseInt(response) === 2){
                $('#error').text("Thêm sản phẩm không thành công, xem lại trạng thái đơn hàng!");
                $('#msp').val("");
                $('#notes').val("");
            }
            else {
                $("#table_bill_details > tbody > tr:last").after(response);
                $("#table_bill_details tr").each(function(rowIndex, row) {
                    // Lặp qua từng hàng
                    $(row).find("td:eq(6)").each(function(colIndex, col) {
                        // Lấy giá trị từng cột
                        var value = $(col).text();
                        pro_bill = pro_bill + parseInt(value.replace(/,/g, ''));
                    });
                });
                $('#msp').val("");
                $('#notes').val("");
                $('#pro_bill').text(pro_bill.toLocaleString('en-US')+ " VND");
                var total = fee + pro_bill;
                $('#total').text(total.toLocaleString('en-US')+ " VND");
                $.magnificPopup.close();
            }

        },
        error: function (){
            $('#error').text("Không tìm thấy sản phẩm!");
            $('#msp').val("");
            $('#notes').val("");
        }
    });
}
function save(){
    location.reload();
}

// function showFilterDateForLog(value){
//
//     else if(value ==2){
//         Swal.fire({
//             title: 'Ngày cần lọc',
//             input: 'date'
//
//         })
//
//     }
// }

