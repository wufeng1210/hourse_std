define(['utils'], function (Utils) {

    var bindings = [{
        element: 'li',
        event: 'click',
        handler: function () {
            var type = $$(this).data("type");
            var value = $$(this).find(".item-after").html();
            mainView.loadPage("/modifyInfo.do?type="+type + "&value="+value);
        }
    }];




    function init() {
        getUserInfo();
    }

    function getUserInfo() {
        $$.ajax({
            url: '/getUserInfo.do?v='+new Date().getTime(),
            type: 'POST',
            dataType: 'json',
            success: function (data) {
                app.hideIndicator();
                if (data.errorNo == "0") {
                    Utils.render('#personal-data-tpl', data);
                    Utils.bindEvents(bindings);
                } else {
                    app.alert(data.errorInfo);
                }
            }
        })
    }


    return {
        init: init
    };
});
