define(['utils'], function (Utils) {

    var bindings = [{
        element: '#myRent',
        event: 'click',
        handler: function () {
            mainView.loadPage("/myRent.do");
        }
    },{
        element: '#personInfo',
        event: 'click',
        handler: function () {
            mainView.loadPage("/personInfo.do");
        }
    },{
        element: '#myCollect',
        event: 'click',
        handler: function () {
            mainView.loadPage("/collect.do");
        }
    },{
        element: '#notice',
        event: 'click',
        handler: function () {
            mainView.loadPage("/notice.do");
        }
    }];




    function init() {
        Utils.bindEvents(bindings);
    }


    return {
        init: init
    };
});
