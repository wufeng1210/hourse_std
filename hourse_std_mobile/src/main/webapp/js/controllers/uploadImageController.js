define(['utils'], function (Utils) {

    var bindings = [{
        element: '.submit',
        event: 'click',
        handler: submit
    }];

    function submit() {
        var request = new XMLHttpRequest();
        request.open("POST", "/upload.do");
        var form = new FormData(); // FormData 对象
        var fileObj = document.getElementById("uploadImage").files[0];
        console.info(fileObj);
        // request.setRequestHeader("Content-Type", "multipart/form-data");
        form.append("myFiles", fileObj); // 文件对象
        request.send(form);
    }


    function init() {
        Utils.bindEvents(bindings);
        $$("body").click();
        $$("input[type=file]").on("change",  function(){
            lrz(this.files[0])
                .then(function(){
                    submit();
                })
        })
    }



    return {
        init: init
    };
});
