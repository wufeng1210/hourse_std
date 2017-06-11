define(['utils'], function (Utils) {

    var bindings = [{

    }];




    function init() {
        // Utils.bindEvents(bindings);
        //创建地图

        // 数据
        $$.ajax({
            url: '/getMapInfo.do',
            type: 'POST',
            dataType: 'json',
            success: function (data) {
                app.hideIndicator();
                if(data.errorNo == "0"){
                    var map = new AMap.Map("container", {
                        resizeEnable: true,
                        center: [120.2104870, 30.2051810],//地图中心点
                        zoom: 15 //地图显示的缩放级别
                    });
                    var markers = data.hourseList;
                    // 添加一些分布不均的点到地图上,地图上添加三个点标记，作为参照
                    markers.forEach(function(marker) {
                        new AMap.Marker({
                            map: map,
                            icon: "http://webapi.amap.com/theme/v1.3/markers/n/mark_b1.png",
                            position: [marker.longitude, marker.latitude],
                            offset: new AMap.Pixel(-12, -36),
                            draggable: false,
                            content:'<div class="marker-route marker-marker-bus-from">'+marker.hourseNum+'</div>'
                        });
                    });
                }else {
                    app.alert(data.errorInfo);
                }

            }
        });
        /*var markers = [{
            icon: 'http://webapi.amap.com/theme/v1.3/markers/n/mark_b1.png',
            position: [120.2104870, 30.2051810],
            content: '22'
        }, {
            icon: 'http://webapi.amap.com/theme/v1.3/markers/n/mark_b2.png',
            position: [120.2204870, 30.2251810],
            content: '22'
        }, {
            icon: 'http://webapi.amap.com/theme/v1.3/markers/n/mark_b3.png',
            position: [120.2304870, 30.2351810],
            content: '22'
        }];*/


    }


    return {
        init: init
    };
});
