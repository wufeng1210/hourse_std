define(['utils'], function (Utils) {
    var bindings = [{
        element: '#rent',
        event: 'click',
        handler: goHomeRent
    },{
        element: '#lend',
        event: 'click',
        handler: function () {
            mainView.loadPage("/homeLend.do");
        }
    }];
    
    function  goHomeRent() {
        mainView.loadPage("/homeRent.do");
    }


    function init() {
     Utils.bindEvents(bindings);
        app.swiper('.swiper', {
            pagination: '.swiper .swiper-pagination',
            spaceBetween: 10,
            autoplay: 3000
        });
    }

    return {
        init: init
    };
});
