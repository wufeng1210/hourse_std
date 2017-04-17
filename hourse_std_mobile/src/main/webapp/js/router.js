define([], function () {

    /**
     * Init router, that handle page events
     */
    function init() {
        $$(document).on('pageBeforeInit', function (e) {
            var page = e.detail.page;
            load(page.name, page.query);
        });

        window.mainView = workbenchView;
        workbenchView.loadPage('page/workbench.html');
        $$('#workbenchView').on('show', function () {
            window.mainView = workbenchView;
            workbenchView.loadPage('page/workbench.html');
        });

        $$('#customerView').on('show', function () {
            window.mainView = customerView;
            customerView.loadPage('page/customer.html');
        });

        $$('#productView').on('show', function () {
            window.mainView = productView;
            productView.loadPage('page/product.html');
        });

        $$('#myView').on('show', function () {
            window.mainView = myView;
            myView.loadPage('page/my.html');
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
