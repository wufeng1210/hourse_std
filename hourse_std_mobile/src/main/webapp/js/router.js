define([], function () {

    /**
     * Init router, that handle page events
     */
    function init() {
        $$(document).on('pageBeforeInit', function (e) {
            var page = e.detail.page;
            load(page.name, page.query);
            if (page.container.className.indexOf('no_toolbar') > -1) {
                $$('.toolbar').hide();
            } else {
                $$('.toolbar').show();
            }
        });
        var wechatLogin = "1";
        if(wechatLogin == "0"){
            mainView.loadPage('/goLogin.do');
        }else{
            getSignature();
        }
        //window.location.href= "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx0fcaa7eb18ec769e&redirect_uri=http%3a%2f%2fadmin.jingtianwangluo.com%2findex%2f&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
        window.mainView = workbenchView;

        $$('#workbenchView').on('show', function () {
            window.mainView = workbenchView;
            workbenchView.loadPage('/home.do');
        });

        $$('#customerView').on('show', function () {
            window.mainView = customerView;
             customerView.loadPage('/rent.do');
        });

        $$('#productView').on('show', function () {
            window.mainView = productView;
            productView.loadPage('/lend.do');
        });

        $$('#myView').on('show', function () {
            window.mainView = myView;
            myView.loadPage('/my.do');
        });
        window.mainView = window.workbenchView;//这个是当前公用view对象
    }

    function getSignature() {
        if(!code){
            window.location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx0fcaa7eb18ec769e&redirect_uri=http%3A%2F%2Fwww.jygozuba.com%2F&response_type=code&scope=snsapi_userinfo&state=STATE&connect_redirect=1#wechat_redirect";
        }else{
            $$.ajax({
                url: '/getSignature.do',
                type: 'POST',
                data:{
                    url:encodeURIComponent(location.href.split('#')[0]),
                    code: code
                },
                dataType: 'json',
                success: function (data) {
                    if(data.errorNo == "0"){
                        wx.config({
                            debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
                            appId: data.appId, // 必填，公众号的唯一标识
                            timestamp: data.timestamp, // 必填，生成签名的时间戳
                            nonceStr: data.nonceStr, // 必填，生成签名的随机串
                            signature: data.signature,// 必填，签名，见附录1
                            jsApiList: [
                                'getLocation'
                            ] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
                        });
                        if(data.openId){
                            login(data);
                        }
                    } else {
                        window.location.href = data.url;
                    }
                }
            });
        }
    }

    function login(json) {
        $$.ajax({
            url: '/login.do?v='+new Date().getTime(),
            type: 'POST',
            data: {
                userName : json.openId,
                userPassWord : "",
                nickName: json.nickName
            },
            dataType: 'json',
            success: function (data) {
                app.hideIndicator();
                if(data.errorNo == "0"){
                    mainView.loadPage("/home.do");
                }else{
                    app.alert(data.errorInfo);
                }
            }
        });
    }
    /**
     * Load (or reload) controller from js code (another controller) - call it's init function
     * @param  controllerName
     * @param  query
     */
    function load(controllerName, query) {
        if (!controllerName) {
            return;
        }
        if (controllerName.indexOf('smart-select') !== -1) {
            return;
        }
        require(['controllers/' + controllerName + 'Controller'], function (controller) {
            controller.init(query);
        });
    }

    return {
        init: init,
        load: load
    };
});
