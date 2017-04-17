//常量JS
var MESSAGE_TIMEOUT 	= "网络或服务异常，请检查手机网络情况后重试！";
var MESSAGE_INITFAIL 	= "初始化失败，请注销后重新登陆再试！";
var MESSAGE_INITUN 		= "初始化未完成，请稍后再试！";
////////////////////////////////////////////////////////////////////

var CERT={};
var APP_video={};
var APP_cert={};
var USER={};
var APP_Yixin = {openid:"",appid:"",secret:"",code:"",access_token:""};
var mobileNo = "";

//判断浏览器
var browser={
	versions:function(){
        var u = navigator.userAgent, app = navigator.appVersion;
        return {         //移动终端浏览器版本信息
            trident: u.indexOf('Trident') > -1, //IE内核
            presto: u.indexOf('Presto') > -1, //opera内核
            webKit: u.indexOf('AppleWebKit') > -1, //苹果、谷歌内核
            gecko: u.indexOf('Gecko') > -1 && u.indexOf('KHTML') == -1, //火狐内核
            mobile: !!u.match(/AppleWebKit.*Mobile.*/), //是否为移动终端
            ios: !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/), //ios终端
            android: u.indexOf('Android') > -1 || u.indexOf('Linux') > -1, //android终端或uc浏览器
            iPhone: u.indexOf('iPhone') > -1 , //是否为iPhone或者QQHD浏览器
            iPad: u.indexOf('iPad') > -1, //是否iPad
            webApp: u.indexOf('Safari') == -1, //是否web应该程序，没有头部与底部
            YiXin: u.indexOf('YiXin') > -1 //是否YiXin手机开户
        };
     }(),
     language:(navigator.browserLanguage || navigator.language).toLowerCase()
}