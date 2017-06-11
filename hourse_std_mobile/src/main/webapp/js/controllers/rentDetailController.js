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
                    app.swiper('.swiper', {
                        pagination: '.swiper .swiper-pagination',
                        spaceBetween: 10,
                        autoplay: 3000
                    });
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
