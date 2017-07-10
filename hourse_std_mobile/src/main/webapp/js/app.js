require.config({
	baseUrl: 'js',
	paths: {
		Framework7: 'libs/framework7',
		text: 'libs/text'
	},
	waitSeconds:0,
	shim: {
		Framework7: {
			exports: 'Framework7'
		}
	},
	urlArgs:"rnt=" + new Date().getTime()
});

require(['Framework7', 'router', 'utils'], function (Framework7, Router, Utils) {
  window.$$ = window.Dom7;

  window.isOpenNew;
  var device = Framework7.prototype.device;

	if (device.android) {
		window.app = new Framework7({
            cache: true,
            pushState: true,
            swipeBackPage: false,
            preloadPreviousPage: false,
            popupCloseByOutside: false,
            animateNavBackIcon: true,
			modalTitle: '系统消息',
			modalButtonOk: '确定',
			modalButtonCancel: '取消',
			smartSelectBackText: '返回',
			smartSelectBackTemplate: '<div class="left sliding"><a href="#" class="back link"><i class="icon icon-back"></i><span>{{backText}}</span></a></div>'
		});
	} else {
		window.app = new Framework7({
            cache: true,
            pushState: true,
            swipeBackPage: false,
            preloadPreviousPage: false,
            popupCloseByOutside: false,
            animateNavBackIcon: true,
			modalTitle: '系统消息',
			modalButtonOk: '确定',
			modalButtonCancel: '取消',
			smartSelectBackText: '返回',
			smartSelectBackTemplate: '<div class="left sliding"><a href="#" class="back link"><i class="icon icon-back"></i><span>{{backText}}</span></a></div>'
		});
	}

    window.workbenchView = window.app.addView('#workbenchView', {
        dynamicNavbar: true
    });
    window.customerView = window.app.addView('#customerView', {
        dynamicNavbar: true
    });
    window.productView = window.app.addView('#productView', {
        dynamicNavbar: true
    });
    window.myView = window.app.addView('#myView', {
        dynamicNavbar: true
    });
    window.mainView = window.workbenchView;//这个是当前公用view对象


    // Android 下的一些常见 Hack
  if (device.android) {

    // 重设 body 高度，默认的高度为 100%，在 Android 下会有问题
    Utils.resetBodyHeight();

    // 防止在 Android 下 Smart Select 组件点击穿透
    $$('body').on('touchend', '.smart-select-page li', function (e) {
      e.stopPropagation();
    });
  }

    Template7.registerHelper('countPrice', function (value, price) {
        var val = value * price;
        if((val/10000) >= 1){
            return val/10000 + "万";
        } else {
            return val;
        }
    });

  Router.init();
});
