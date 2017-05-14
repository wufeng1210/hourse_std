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
        getHourseInfo();
    }

    function getHourseInfo() {
        app.showIndicator();
        $$.ajax({
            url: '/getHourseInfo.do',
            type: 'POST',
            dataType: 'json',
            success: function (data) {
                app.hideIndicator();
                console.info(data);
                if(data.errorNo == "0"){
                    Utils.render('#rentTemplate', data);
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
