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
	}/*,
	urlArgs:"rnt=" + new Date().getTime()*/
});

require(['Framework7', 'router', 'utils'], function (Framework7, Router, Utils) {
  window.$$ = window.Dom7;

  window.isOpenNew;
  var device = Framework7.prototype.device;

	if (device.android) {
		window.App = new Framework7({
            cache: false,
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
		window.App = new Framework7({
            cache: false,
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

    window.workbenchView = window.App.addView('#workbenchView', {
        dynamicNavbar: true
    });
    window.customerView = window.App.addView('#customerView', {
        dynamicNavbar: true
    });
    window.productView = window.App.addView('#productView', {
        dynamicNavbar: true
    });
    window.myView = window.App.addView('#myView', {
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

  Router.init();
});
