layui.use(['layer'], function() {
    let $ = layui.jquery;

    function getAllTitleImg() {
        $.ajax({
            type: "GET",
            url: ctx + "blog/getAllTitleImg",
            success: function (result) {
                if(result.code == 200){
                    let str = '';
                    $.each(result.data,function (index,item) {
                       str = str + '<div class="layui-col-xs6 layui-col-sm6 layui-col-md3">\n' +
                           '        <div class="layui-row" style="margin-bottom: 10px">\n' +
                           '        <div class="layui-col-md8">\n' +
                           '    <img style="width: 200px;height: 200px" src="'+item.url+'">\n' +
                           '        </div>\n' +
                           '        <div class="layui-col-md4">\n' +
                           '        <br><br>\n' +
                           '        <button class="layui-btn layui-btn-danger" value=" '+item.id+' " onclick="cencel(this,'+item.id+')">删除</button>\n' +
                           '        <br><br>';
                            if (item.state == 1){
                                str = str + '<button class="layui-btn layui-btn-normal" value=" '+item.state+' " onclick="change(this,'+item.id+')">使用</button>';
                            }else {
                                str = str + '<button class="layui-btn layui-btn-normal" value=" '+item.state+' " onclick="change(this,'+item.id+')">禁止</button>';
                            }
                           str = str + '</div>\n' +
                           '        </div>\n' +
                           '        </div>' ;
                    });
                    $('.imgs').html(str.toString());
                }
            }
        })
    }
    getAllTitleImg();
});

function cencel(obj,id) {
    $.ajax({
        type: "DELETE",
        url: ctx + "blog/deleteTitleImgById",
        data: {
            id:id
        },
        success: function (result) {
            if(result.code == SUCCESS_CODE){
                layer.msg("删除成功");
                location.reload();
            }
        }
    })
}
function change(obj,id) {
    let state;
    if(obj.value == 1){
        obj.value = 0;
        state = 0;
    }else {
        obj.value = 1;
        state = 1;
    }
    $.ajax({
        type: "POST",
        data:{
            id:id,
            state:state
        },
        url: ctx + "blog/updataTitleImgState",
        success: function (result) {
            if(result.code == SUCCESS_CODE){
                if(obj.value == 1){
                    obj.textContent = "使用";
                }else {
                    obj.textContent = "禁止";
                }

            }
        }
    })
}