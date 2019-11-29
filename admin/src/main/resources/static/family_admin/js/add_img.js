layui.use('upload', function(){
    let $ = layui.jquery;
    let upload = layui.upload;

    //刷新页面后自定义图片不消失
    function refresh() {
       var url = window.sessionStorage.getItem("img_refresh");
       if(url != null){
           $('#img').attr("src",url);
           $('#img').css({
               "height":"150px",
               "width":"150px"
           })
       }
    }
    refresh();

    //执行实例
    upload.render({
        elem: '#uploadImg' ,//绑定元素
        ,url: ctx + 'blog/upload' //上传接口
        ,done: function(res){
            if(res.code == SUCCESS_CODE){
                layer.msg("上传成功");
                $('#img').attr("src",res.data);
            }
        }
        ,error: function(){
            //请求异常回调
            layer.msg("请求异常");
        }
    });
    $('#defaultButton').click(function () {
        getRandomUrl();
        $('#img').attr("src",url);
    });
    //确认
    $('#confirm').click(function () {
        if(url == ''){
            getRandomUrl();
            window.sessionStorage.setItem("img_confirm", url);
        }else {
            window.sessionStorage.setItem("img_confirm", url);
        }
        xadmin.close();
    });

    let url = '';
    function getRandomUrl() {
        $.ajax({
            type: "GET",
            async: false,
            url: ctx + "blog/getRandomUrl",
            success: function (result) {
                url = result.data;
            }
        })
    }
});