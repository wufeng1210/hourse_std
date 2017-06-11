define(['utils'], function (Utils) {

    var bindings = [{
        element: '.login-btn',
        event: 'click',
        handler: submit
    }];

    function submit() {
        var userName = $$(".userName").val();
        if(!userName){
            app.alert("用户名不能为空");
            return;
        }
        var userPassWord = $$(".userPassWord").val();
        if(!userPassWord){
            app.alert("用户名不能为空");
            return;
        }
        $$.ajax({
            url: '/login.do?v='+new Date().getTime(),
            type: 'POST',
            data: {
                userName : userName,
                userPassWord : userPassWord
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

    function init() {
        Utils.bindEvents(bindings);
    }

    return {
        init: init
    };
});
