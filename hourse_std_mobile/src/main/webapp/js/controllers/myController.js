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
    },{
        element: '#noticeTip',
        event: 'click',
        handler: function () {
            mainView.loadPage("/noticeTip.do");
        }
    }];




    function init() {
        Utils.bindEvents(bindings);
        var suggest = $$("#suggest").val();
        if(suggest == "1"){
            $$("#noticeTip").show();
        }
    }


    return {
        init: init
    };
});
