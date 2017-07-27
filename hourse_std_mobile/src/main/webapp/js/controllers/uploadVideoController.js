//https://khh5.eastmoney.com/Account/step8

define(function() {
    var getEl = document.getElementById,
        els = {'fn': '', 'fs': '', 'ft': '', 'pg': ''},
        cbs = {'up': undefined, 'uc': undefined, 'uf': undefined, 'ucl': undefined};

    function fileSelected(el) {
        var file = getEl(el).files[0];
        if (file) {
            var fileSize = 0;
            if (file.size > 1024 * 1024)
                fileSize = (Math.round(file.size * 100 / (1024 * 1024)) / 100).toString() + 'MB';
            else {
                fileSize = (Math.round(file.size * 100 / 1024) / 100).toString() + 'KB';
            }
            setTxt(this.getEls('fn'), file.name);
            setTxt(this.getEls('fs'), fileSize);
            setTxt(this.getEls('ft'), file.type);
        }
    }

    this.uploadFile = function (file, url) {
        var fd = new FormData();
        fd.append("fileToUpload", file);
        var xhr = new XMLHttpRequest();
        xhr.upload.addEventListener("progress", uploadProgress, false);
        xhr.addEventListener("load", uploadComplete, false);
        xhr.addEventListener("error", uploadFailed, false);
        xhr.addEventListener("abort", uploadCanceled, false);
        xhr.open("POST", url);
        xhr.send(fd);
    }

    this.setEls = function (k, n) {
        els[k] = n;
    }

    this.setFns = function (k, f) {
        cbs[k] = f;
    }

    this.setTxt = function (n, v) {
        getEls(n).innerHTML = v;
    }

    function getEls(k) {
        return els[k];
    }

    function getFns(k) {
        return cbs[k];
    }

    function uploadProgress(evt) {
        if (evt.lengthComputable) {
            var percentComplete = Math.round(evt.loaded * 100 / evt.total);
            var per = percentComplete.toString();
            // setTxt(getEls('pg'),per+'%');
        }
        getFns('up')(per);
    }

    function uploadComplete(evt) {
        getFns('uc')(evt.target.responseText);
    }

    function uploadFailed(evt) {
        getFns('uf')(evt);
    }

    function uploadCanceled(evt) {
        getFns('ucl')
    }
})

// 上传
define(['base', 'tips','xhr_upload'], function (base,tips,xhr) {
    this.init = function () {
        base.initFooter();
        $('.intend.card').find('img').each(function () {
            var h = $(this).width() * 1.33;
            $(this).css('height', h + 'px');
        })
        var v = $("video");
        // v.parent().css("height",v.height()+30+'px');

        $('.intend-item').click(function (e) {
            $(".upload").click();
        })

        $("#Recording").click(function () {
            $(".upload").click();
        })
        v.on('play', function () {
            var el = v.parent()[0];
            el.webkitRequestFullScreen &&
            el.webkitRequestFullScreen.call(el);
        })
        v.on('stop',function(){
            var el = v.parent()[0];
            el.webkitRequestFullScreen &&
            el.webkitExitFullScreen.call(el);
        })
        $(".upload").on('change',function(){
            var f = this.files[0];
            if(!f){
                return false;
            }
            if (!window._datouzhao) {
                base.showLoading();
                this.files[0] && compress(this.files[0], 640, function (src) {
                    $.ajax({
                        url: '/Submit/UploadBase64',
                        type: 'POST',
                        data: { 'uid': UserId, 'type': 3, 'data': src.substr(23) },
                        datatype: 'json',
                        success: function (r) {
                            r = JSON.parse(r);
                            base.hideLoading();
                            if (r.Status === 1) {
                                tips.showAlert('上传成功！');
                                $('#imgHead').attr('src', src).attr("isservice", "1");
                            } else {
                                tips.showAlert('上传失败！' + r.Message);
                            }
                        },
                        failure: function () {
                            base.hideLoading();
                            tips.showAlert('上传失败！');
                        }
                    })
                });
                $(this).val('');
                return;
            }
            if(f.size>20*1000*1000){
                tips.showAlert("视频太大，无法上传！");
                return false;
            }
            if(!checkVideo(f.name)){
                tips.showAlert("视频不支持，请使用mp4、ogg或webm视频格式！");
                return false;
            }
            xhr.setEls('pg','');
            xhr.setFns('up',function(p){
                tips.setProgress(p);
            });
            xhr.setFns('uc',function(r){
                tips.hideProgress();
                r = JSON.parse(r);
                if (r.Status === 1) {
                    tips.hideProgress();
                    var u = window.webkitURL.createObjectURL(f);
                    if(checkVideo2(f.name)&&u){
                        v.attr("src", u);
                    }else{
                        v.attr("src", r.Result);
                    }
                    v.show();
                    v.css("height", v.width() * .75 + "px");
                    $(".video img").hide();
                    v.attr("src", r.Result);
                    v.attr("server-src", r.Result);
                    //v.attr("controls", "controls");
                    //v.removeAttr("poster");
                    //v.css("background-color", "#000");
                    //tips.showAlert('上传成功！');
                }else{
                    tips.showAlert('上传失败！'+r.Message);
                }
            });
            xhr.setFns('uf',function(){
                tips.showAlert('上传失败！');
            });

            xhr.uploadFile(this.files[0],'/Submit/UploadVideo');
            tips.showProgress('正在上传');
            $(this).val('');
        })
        function checkVideo(f){
            var b = false,
                n = f.substr(f.lastIndexOf('.')+1).toLowerCase();
            if(n=='mp4'||n=='ogg'||n=='webm'||n=="mov"){
                b = !b;
            }
            return b;
        }
        function checkVideo2(f){
            var b = false,
                n = f.substr(f.lastIndexOf('.')+1).toLowerCase();
            if(n=='mp4'||n=='ogg'||n=='webm'){
                b = !b;
            }
            return b;
        }
        $("#NextStep").click(function () {
            var showerr = function (text) {
                tips.showToast(text);
                v.attr("src", "");
                v.hide();
                $(".video img").show();
            }
            if (!v.attr("server-src") || v.attr("server-src") == "") {
                tips.showToast("请先录制视频！");
                showerr("请先录制视频！");
                v.attr("src", "");
                v.hide();
                $(".video img").show();
                //v.removeAttr("controls");
                //v.attr("poster", "/images/videotip.png");
                //v.css("background-color", "#EDEDED");
            }
            else {
                var params = {
                    videoFlvUrl: v.attr("server-src"),
                    videoType: 1
                }
                //base.showLoading();
                base.ajax({
                    type: "POST",
                    url: '/Submit/FinishStep9',
                    data: params,
                    success: function (data) {
                        base.hideLoading();
                        if (data.Status == 1 && data.Result == true) {
                            window.location.href = "/Account/Status";
                        } else if (data.Status == -11) {
                            showerr("信息填写不完整,请补充信息");
                        } else if (data.Status == -40) {
                            tips.showConfirm(data.Message,function () {
                                window.location.href = "/Account/Step6";
                            });
                        }
                        else if (data.Message != "") {
                            showerr(data.Message);
                        } else {
                            showerr("信息验证有误，请重新录制！");
                        }
                    },
                    error: function (data) {
                        base.hideLoading();
                        showerr("网络不给力，请稍后再试！");
                    }
                });
            }
        })
        // 下一步事件
        $("#NextSet").click(function () {
            if ($('#imgHead').attr("isservice") == "1") {
                window._datouzhao = true;
                $("#div_1").hide();
                $("#div_2").show();
                $(".upload").attr('accept', 'video/*');
            }
            else {
                showAlert("请先拍摄大头照！");
                return false;
            }
        })
        function compress(f, w, cb) {
            var reader = new FileReader(),
                img = new Image();
            reader.readAsDataURL(f);
            reader.onload = function (e) {
                img.src = e.target.result;
                img.onload = function () {
                    var canvas = document.createElement('canvas'),
                        rate = this.naturalHeight / this.naturalWidth;
                    canvas.width = w;
                    canvas.height = w * rate;
                    var context = canvas.getContext('2d');
                    context.drawImage(this, 0, 0, w, w * rate);
                    var src = canvas.toDataURL('image/jpeg', rate);
                    // $("form img")[0].src = src;
                    cb && cb(src);
                }
            }
        }
    }
    return this;
});