define(['utils'], function (Utils) {

    var bindings = [{
        element: '#myRent',
        event: 'click',
        handler: function () {
            mainView.loadPage("/myRent.do");
        }
    }];




    function init() {
        Utils.bindEvents(bindings);

    }


    return {
        init: init
    };
});
