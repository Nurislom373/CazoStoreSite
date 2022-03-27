$(document).ready(function () {
    $('.popularity').on('click', function () {
        var popId = $(this).attr('popId');

        req = $.ajax({
            url: '/shop/setSortBy/1',
            type: 'GET',
        });

        req = $.ajax({
            url: '/shop/filter',
            type: 'GET',
        });

        $('#productSection').fadeOut(1000).fadeIn(1000);
    });
});