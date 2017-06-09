define(['utils'], function (Utils) {

    var bindings = [{

    }];



    function init(query) {
        var hourseId = query.hourseId;
        getHourseDetail(hourseId);
    }

    function getHourseDetail(hourseId) {
        $$.ajax({
            url: '/getHourseDetail.do',
            data: {
                hourseId : hourseId
            },
            type: 'POST',
            dataType: 'json',
            success: function (data) {
                if (data.errorNo == "0") {
                    Utils.render('#rentDetailTemplate', data);
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
