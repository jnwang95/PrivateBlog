layui.use(['form', 'jquery'],function() {
    let $ = layui.jquery;
    let url=window.location.search; //获取url中"?"符后的字串  
    let id = url.substr(url.indexOf("=")+1);
    var vname;
    //打开页面展示名称
    function showName(){
        $.ajax({
            type: "GET",
            url: ctx + "blog/getNameById/"+id,
            success: function (result) {
                if(result.code == SUCCESS_CODE){
                    $('#name').attr("value",result.data);
                    vname = result.data;
                }
            }
        })
    }
    showName();

    $('#cancer').click(function () {
        xadmin.close();
    });

    $('#updata').click(function () {
        var name = $('#name').val();
        if(name == vname){
            layer.msg("名称重复");
            return false;
        }
        var msg = {
            id: id,
            name: name
        };
        $.ajax({
            type: "POST",
            data: msg,
            url: ctx + "blog/updataCategoryNameById",
            success: function (result) {
                if(result.code == SUCCESS_CODE){
                    xadmin.close();
                    xadmin.father_reload();
                }
            }
        })
    });
});