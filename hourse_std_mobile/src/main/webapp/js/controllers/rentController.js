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


    function init(query) {
        var residentialQuarters = "";
        if(query.residentialQuarters){
            residentialQuarters = query.residentialQuarters;
        }
        wx.getLocation({
            type: 'wgs84', // 默认为wgs84的gps坐标，如果要返回直接给openLocation用的火星坐标，可传入'gcj02'
            success: function (res) {
                var latitude = res.latitude; // 纬度，浮点数，范围为90 ~ -90
                var longitude = res.longitude; // 经度，浮点数，范围为180 ~ -180。
                // var speed = res.speed; // 速度，以米/每秒计
                // var accuracy = res.accuracy; // 位置精度
                getHourseInfo(latitude, longitude, residentialQuarters);
            }
        });

    }
    function getHourseInfo(latitude, longitude, residentialQuarters) {
        app.showIndicator();
        $$.ajax({
            url: '/getHourseInfo.do',
            type: 'POST',
            data: {
                longitude: longitude,
                latitude : latitude,
                residentialQuarters: residentialQuarters
            },
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
