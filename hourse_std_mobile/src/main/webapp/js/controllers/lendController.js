define(['utils'], function (Utils) {

    var bindings = [{
        element: '.lend-next-button',
        event: 'click',
        handler: submit
    }];

    function submit() {
        var param = app.formToJSON("#hourseForm");
        app.showIndicator();
        $$.ajax({
            url: '/addHourse.do',
            type: 'POST',
            data: param,
            dataType: 'json',
            success: function (data) {
                app.hideIndicator();
                if(data.errorNo == "0"){
                    app.alert("添加成功");
                }else{
                    app.alert(data.errorInfo);
                }
            }
        });
    }

    function init() {
        imageBases = "";
        chooseAddress();
    }

    function chooseAddress() {
        var area1 = new LArea();
        area1.init({
            'trigger': '#address', //触发选择控件的文本框，同时选择完毕后name属性输出到该位置
            // 'valueTo': '#value1', //选择完毕后id属性输出到该位置
            'keys': {
                id: 'id',
                name: 'name'
            }, //绑定数据源相关字段 id对应valueTo的value属性输出 name对应trigger的value属性输出
            'type': 1, //数据源类型
            'data': LAreaData //数据源
        });

        Utils.bindEvents(bindings);
        $$(document).on("change", "input[type=file]", function(){
            lrz(this.files[0])
                .then(function(result){

                    var img_info = result.base64.split(',');
                    $$("input[name=imageBases]").val(encodeURIComponent(img_info[1])+","+ $$("input[name=imageBases]").val());
                    $$(".upload").prepend("<img src='"+result.base64+"'  style='width: 40%'>");
                })
        })
    }
    return {
        init: init
    };
});
