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

        //window.location.href= "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx0fcaa7eb18ec769e&redirect_uri=http%3a%2f%2fadmin.jingtianwangluo.com%2findex%2f&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
        window.mainView = workbenchView;
        mainView.loadPage('/home.do');
        $$('#workbenchView').on('show', function () {
            window.mainView = workbenchView;
            workbenchView.loadPage('/home.do');
        });

        $$('#customerView').on('show', function () {
            window.mainView = customerView;
             customerView.loadPage('/rent.do?v='+ new Date().getTime());
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
