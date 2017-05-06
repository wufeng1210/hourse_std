define(['utils'], function (Utils) {

    var bindings = [{
        element: '.rent a',
        event: 'click',
        handler: goRentDetail
    }];

    function goRentDetail() {
        mainView.loadPage("/rentDetail.do")
    }


    function init() {
        Utils.bindEvents(bindings);
    }


    return {
        init: init
    };
});
