define(['utils'], function (Utils) {

    var bindings = [{
        element: '.label-switch',
        event: 'click',
        handler: updateHourse
    }];


    function updateHourse(event) {
        event.preventDefault();
        var hourseId = $$(this).data("hourseId");
        var collect = "1";
        if($$("input[name=collect]")[0].checked){
            collect = "0";
        }
        app.showIndicator();
        $$.ajax({
            url: '/updateHourse.do?v='+new Date().getTime(),
            data: {
                hourseId: hourseId,
                collect : collect
            },
            type: 'POST',
            dataType: 'json',
            success: function (data) {
                app.hideIndicator();
                if (data.errorNo == "0") {
                    if(collect == '1'){
                        $$("input[name=collect]")[0].checked=true;
                        app.alert("收藏成功");
                    }else {
                        $$("input[name=collect]")[0].checked=false;
                        app.alert("取消收藏成功");
                    }

                } else {
                    app.alert(data.errorInfo);
                }
            }
        });
    }


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
