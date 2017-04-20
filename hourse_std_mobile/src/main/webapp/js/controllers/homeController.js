define(['utils'], function (Utils) {
    var bindings = [{

    }];



    function init() {
     //Utils.bindEvents(bindings);
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
