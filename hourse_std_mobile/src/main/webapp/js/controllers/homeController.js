define(['utils'], function (Utils) {
    var bindings = [{
        element: '#rent',
        event: 'click',
        handler: goHomeRent
    },{
        element: '#lend',
        event: 'click',
        handler: function () {
            mainView.loadPage("/homeLend.do");
        }
    },{
        element: '#map',
        event: 'click',
        handler: function () {
            mainView.loadPage("/map.do");
        }
    }];
    
    function  goHomeRent() {
        mainView.loadPage("/homeRent.do");
    }


    function init() {
     Utils.bindEvents(bindings);
     getActivity();
    }

    function getActivity() {
        $$.ajax({
            url: '/getActivity.do',
            type: 'POST',
            dataType: 'json',
            success: function (data) {
                app.hideIndicator();
                console.info(data);
                if(data.errorNo == "0" ){
                    Utils.render('#homeTemplate', {model:data});
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
