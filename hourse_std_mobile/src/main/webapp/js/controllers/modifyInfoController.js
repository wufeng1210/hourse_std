define(['utils'], function (Utils) {
  var qurryType = ''; // qurry.type

  var Controller = {
    init: function (qurry) {
        console.log(qurry);
        qurryType = qurry.type;
        Utils.bindEvents(this.bindings);
        
        var value = qurry.value;
        switch (qurryType) {
        case 'name': // 修改用户名

          $$('.navbar .center').text('修改用户名');
          $$('.page-modifyInfo .user').attr('placeholder', '请输入您的姓名');
          $$('.page-modifyInfo .user').val(value);
          break;
        case 'mobile': //
          $$('.navbar .center').text('修改手机号');
          $$('.page-modifyInfo .user').attr('placeholder', '请输入您的手机号');
          $$('.page-modifyInfo .user').val(value);
          break;
        case 'qq': //
          $$('.navbar .center').text('修改QQ');
          $$('.page-modifyInfo .user').attr('placeholder', '请输入您的手机号');
          $$('.page-modifyInfo .user').val(value);
          break;
        case 'wechat': //
            $$('.navbar .center').text('修改微信号');
            $$('.page-modifyInfo .user').attr('placeholder', '请输入您的微信号');
            $$('.page-modifyInfo .user').val(value);
            break;
      }
    },
    bindings: [
      {
        element: '.mod-info-btn',
        event: 'click',
        handler: modInfo
      }
    ]
  };

  function modInfo() {
      var value = $$(".user").val();
      var json = {};
      switch (qurryType) {
        case 'name': // 修改用户名
            json.name = value;
          break;
        case 'mobile': // 修改邮箱
            json.mobile = value;
          break;
        case 'qq':
            json.qq = value;
            break;
        case 'wechat':
            json.wechat = value;
            break;
      }

      $$.ajax({
          url: '/updateUserInfo.do?v='+new Date().getTime(),
          type: 'POST',
          data: json,
          dataType: 'json',
          success: function (data) {
              app.hideIndicator();
              if (data.errorNo == "0") {
                  app.alert("更新成功");
              } else {
                  app.alert(data.errorInfo);
              }
          }
      });
  }
  return Controller;
});
