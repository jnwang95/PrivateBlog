layui.use(['jquery','upload'],function(){
    let $ = layui.jquery;
    let upload = layui.upload;
    let data = {
        url:''
    };
    //执行实例
    upload.render({
        elem: '#uploadImg' ,//绑定元素
        ,url: ctx + 'blog/uploadTitleImg' //上传接口
        ,done: function(res){
            if(res.code == SUCCESS_CODE){
                layer.msg("上传成功");
                data.url = res.data;
                $('#img').attr("src",res.data);
                $('#img').css({
                    "height":"200px",
                    "width":"200px"
                })
        }
        ,error: function(){
            //请求异常回调
            layer.msg("请求异常");
        }
    });

    //确认
    $('#confirm').click(function () {
        if(data.url.length != 0){
            $.ajax({
                type: "POST",
                url: ctx + "blog/insertTitleImg",
                data: data,
                success: function (result){
                    if(result.code != SUCCESS_CODE){
                        layer.msg("添加失败")
                    }
                }
            })
        }
        xadmin.close();
        xadmin.father_reload();
    })

});