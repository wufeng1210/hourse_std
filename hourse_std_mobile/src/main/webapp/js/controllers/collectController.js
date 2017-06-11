define(['utils'], function (Utils) {

    var bindings = [{
        element: '.rent a',
        event: 'click',
        handler: goRentDetail
    }];

    function goRentDetail() {
        var hourseId = $$(this).data("hourseId");
        mainView.loadPage("/rentDetail.do?hourseId="+hourseId);
    }


    function init() {
        getHourseInfo();
    }

    function getHourseInfo() {
        app.showIndicator();
        $$.ajax({
            url: '/getCollectHourse.do',
            type: 'POST',
            dataType: 'json',
            success: function (data) {
                app.hideIndicator();
                if(data.errorNo == "0"){
                    Utils.render('#rentTemplate', data);
                    Utils.bindEvents(bindings);
                }else {
                    app.alert(data.errorInfo);
                }

            }
        });
    }

    return {
        init: init
    };
});
