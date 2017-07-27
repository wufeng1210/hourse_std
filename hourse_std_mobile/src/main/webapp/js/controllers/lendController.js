define(['utils'], function (Utils) {

    var bindings = [{
        element: '.lend-next-button',
        event: 'click',
        handler: submit
    },{
        element: '#lendContent',
        target: '.delete',
        event: 'click',
        handler: deleteImage
    }];

    function deleteImage() {
        console.info("122");
        var _this = this;
        app.modal({
            title: '温馨提示',
            text: "是否删除此图片？",
            buttons: [
                {
                    text: '否',
                    onClick: function () {
                        app.closeModal();
                    }
                },
                {
                    text: '是',
                    onClick: function () {
                        $$(_this).parent().remove();
                        var image_base = "";
                        $$(".front").each(function () {
                            var this_image_base = $$(this).data("image_base");
                            var img_info = this_image_base.split(',');
                            image_base = encodeURIComponent(img_info[1]) + "," + image_base;
                        });

                        $$("input[name=imageBases]").val(image_base);
                    }
                }
            ]
        });
    }
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
                    app.alert("添加成功", function () {
                        mainView.loadPage("/myRent.do")
                    });
                }else{
                    app.alert(data.errorInfo);
                }
            }
        });
    }

    function init() {
        $$(".image_base").val("");
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
        $$("#uploadImage").on("change", function(){
            lrz(this.files[0])
                .then(function(result){
                    var imgArr = $$("#productView .image_base").val().split(",");

                    var img_info = result.base64.split(',');
                    if(imgArr.length == 4){
                        app.alert("图片上传限制最多三张,至少一张");
                        return;
                    }
                    // $$(".image_base").val(encodeURIComponent(img_info[1])+","+ $$(".image_base").val());
                    // $$(".upload").prepend("<img src='"+result.base64+"'  style='width: 40%'>");
                    addImage(result.base64);
                })
        })
    }

    function addImage(imageBase) {
        if($$("#productView .images").eq($$("#productView .images").length-1).find("#productView .image").length == 2){
            // var dl = document.getElementById("productView ").getElementById("images");
            var dl = $$("#productView #images")[0];
            dl.insertAdjacentHTML("afterend", "<div class='images'></div>");
        }
        Utils.render("#imagesList", {imageBase:imageBase}, "append",$$("#productView .images").eq($$("#productView .images").length-1));
        var img_info = imageBase.split(',');
        $$("#productView .image_base").val(encodeURIComponent(img_info[1])+","+ $$("#productView .image_base").val());
    }

    return {
        init: init
    };
});
