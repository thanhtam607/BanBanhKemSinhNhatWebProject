// changeIcon
function changeIcon() {
    var x = document.getElementById('value');
    var y1 = document.getElementById('icon1');
    var y2 = document.getElementById('icon2');
    var y3 = document.getElementById('icon3');
    var y4 = document.getElementById('icon4');

    if (x.value === 'value01') {
        y1.style.color = '#0edc29';
        y2.style.color = '#a49a9d';
        y3.style.color = '#a49a9d';
        y4.style.color = '#a49a9d';
    } else if (x.value === 'value02') {
        y2.style.color = '#0edc29';
        y1.style.color = '#a49a9d';
        y3.style.color = '#a49a9d';
        y4.style.color = '#a49a9d';
    } else if (x.value === 'value03') {
        y3.style.color = '#0edc29';
        y2.style.color = '#a49a9d';
        y1.style.color = '#a49a9d';
        y4.style.color = '#a49a9d';
    } else {
        y4.style.color = '#0edc29';
        y2.style.color = '#a49a9d';
        y3.style.color = '#a49a9d';
        y1.style.color = '#a49a9d';
    }
}
(function ($) {
    "use strict";

    // Spinner
    var spinner = function () {
        setTimeout(function () {
            if ($('#spinner').length > 0) {
                $('#spinner').removeClass('show');
            }
        }, 1);
    };
    spinner();
    
    
    // Back to top button
    $(window).scroll(function () {
        if ($(this).scrollTop() > 300) {
            $('.back-to-top').fadeIn('slow');
        } else {
            $('.back-to-top').fadeOut('slow');
        }
    });
    $('.back-to-top').click(function () {
        $('html, body').animate({scrollTop: 0}, 80, 'easeInOutExpo');
        return false;
    });


    // Sidebar Toggler
    $('.sidebar-toggler').click(function () {
        $('.sidebar, .content').toggleClass("open");
        return false;
    });


    // Progress Bar
    $('.pg-bar').waypoint(function () {
        $('.progress .progress-bar').each(function () {
            $(this).css("width", $(this).attr("aria-valuenow") + '%');
        });
    }, {offset: '80%'});


    // Calender
    $('#calender').datetimepicker({
        inline: true,
        format: 'L'
    });


    // Testimonials carousel
    $(".testimonial-carousel").owlCarousel({
        autoplay: true,
        smartSpeed: 1000,
        items: 1,
        dots: true,
        loop: true,
        nav : false
    });


    // Chart Global Color
    Chart.defaults.color = "#6C7293";
    Chart.defaults.borderColor = "#000000";


//    Worldwide Sales Chart
    var ctx1 = $("#worldwide-sales").get(0).getContext("2d");
    var myChart1 = new Chart(ctx1, {
        type: "bar",
        data: {
            labels: ["1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"],
            datasets: [{
                    label: "Sán phẩm bán được",
                    data: [15, 30, 55, 65, 60, 80, 95],
                    backgroundColor: "rgba(235, 22, 22, .7)"
                },

            ]
            },
        options: {
            responsive: true
        }
    });

})(jQuery);






   

