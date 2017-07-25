define(['utils'], function (Utils) {
    var bindings = [{
        element: '#rent',
        event: 'click',
        handler: goHomeRent
    },{
        element: '#lend',
        event: 'click',
        handler: function () {
            mainView.loadPage("/homeLend.do?v="+new Date().getTime());
        }
    },{
        element: '#map',
        event: 'click',
        handler: function () {
            mainView.loadPage("/map.do");
        }
    },{
        element: '.spe .code-list',
        event: 'click',
        handler: goRentDetail
    }];
    function goRentDetail() {
        var hourseId = $$(this).data("hourseId");
        mainView.loadPage("/rentDetail.do?hourseId="+hourseId);
    }

    function  goHomeRent() {
        mainView.loadPage("/homeRent.do");
    }


    function init() {
        wx.getLocation({
            type: 'wgs84', // 默认为wgs84的gps坐标，如果要返回直接给openLocation用的火星坐标，可传入'gcj02'
            success: function (res) {
                var latitude = res.latitude; // 纬度，浮点数，范围为90 ~ -90
                var longitude = res.longitude; // 经度，浮点数，范围为180 ~ -180。
                // var speed = res.speed; // 速度，以米/每秒计
                // var accuracy = res.accuracy; // 位置精度
                getActivity(latitude, longitude);
            }
        });

    }

    function getActivity(latitude, longitude) {
        app.showIndicator();
        $$.ajax({
            url: '/getActivity.do',
            type: 'POST',
            data: {
                latitude: latitude,
                longitude: longitude
            },
            dataType: 'json',
            success: function (data) {
                app.hideIndicator();
                if(data.errorNo == "0" ){
                    Utils.render('#homeTemplate', {model:data});
                    Utils.bindEvents(bindings);
                    app.swiper('.swiper', {
                        pagination: '.swiper .swiper-pagination',
                        spaceBetween: 10,
                        autoplay: 3000
                    });
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
