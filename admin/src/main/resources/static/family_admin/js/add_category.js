layui.use(['form', 'layer'], function() {
    let $ = layui.jquery;
    let layer = layui.layer;

    //获取当前添加的分类的Id
    function getCategoryId(){
        $.ajax({
            type: "GET",
            url: ctx + "blog/getCategoryId",
            success: function (result) {
                if(result.code == SUCCESS_CODE){
                    $('#categoryId').attr("value",result.data)
                }else {
                    $('#categoryId').attr("value",result.msg)
                }
            }
        })
    }
    getCategoryId();

    //监听表单
    $('#add').click(function () {
        if($('#categoryName').val().length < 2 || $('#categoryName').val().length.length >30 ){
            layer.msg("名称不合法",icon5);
            return false;
        }
        var data = {
            id: $('#categoryId').val(),
            name: $('#categoryName').val(),
            state: $("input[name=state]").val()
        };
        $.ajax({
            type: "POST",
            url: ctx + "blog/addCategory",
            data: data,
            success: function (result) {
                if(result.code == SUCCESS_CODE){
                    layer.msg("增加成功", {icon: 6});
                    setTimeout(function(){
                        //关闭当前frame
                        xadmin.close();
                        // 可以对父窗口进行刷新
                        xadmin.father_reload();
                    }, 2000);
                    return false;
                }else {
                    layer.msg("添加失败",{icon:2})
                }
            }
        })
    });

    //退出
    $('#cancer').click(function () {
        xadmin.close();
    })

});