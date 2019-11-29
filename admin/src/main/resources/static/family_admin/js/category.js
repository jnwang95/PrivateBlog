layui.use(['form','jquery','laypage'],function() {
    let laypage = layui.laypage;
    let form = layui.form;
    let $ = layui.jquery;

    let page = {
        pageNum: 1,
        pageSize: 10,
        id:'',
        name:''
    };

    //分页
    function getPagination(count) {
        laypage.render({
            elem: 'pagination'
            , curr: page.pageNum
            , count: count
            , layout:['count', 'prev', 'page', 'next']
            , jump: function (obj, first) {
                page.pageNum = obj.curr;
                //首次不执行
                if (!first) {
                    getCategorys();
                    $('#all').prop("checked",false);
                    form.render('checkbox');
                }
            }
        });
    }

    //获取分类列表
    function getCategorys() {
        $.ajax({
            type: "POST",
            data: page,
            url: categorys,
            success: function (result) {
                if(result.code == SUCCESS_CODE){
                    if(result.data.data == null || result.data.data == ''){
                        renderFile();
                    }else {
                        getPagination(result.data.total);
                        renderList(result.data.data);
                    }
                }else {
                    renderFile();
                }
            }
        })
    }

    function renderList(data) {
        let str = '';
        $.each(data,function (index,item) {
            str = str + '<tr>';
            str = str + '<td>';
            str = str + '<input type="checkbox" name='+item.id+' class="check" lay-filter="one" lay-skin="primary" />';
            str = str + '</td>';
            str = str + '<td>'+ (index+1) +'</td>';
            str = str + '<td>'+ item.name +'</td>';
            if(item.state == 1){
                str = str + '<td class="td-status"><input type="checkbox" lay-filter="switch" name='+item.id+'  lay-text="开启|停用" checked lay-skin="switch" /></td>';
            }else {
                str = str + '<td class="td-status"><input type="checkbox" lay-filter="switch" name='+item.id+'  lay-text="开启|停用"  lay-skin="switch" /></td>';
            }
            str = str + '<td class="td-manage">';
            str = str + '<button class="layui-btn layui-btn layui-btn-xs"  onclick="xadmin.open(\'编辑\',\'edit_category.html?id='+item.id+'\',500,210)" >\n' +
                '<i class="layui-icon">&#xe642;</i>编辑</button>';
            str = str + '<button class="layui-btn-danger layui-btn layui-btn-xs" onclick="category_del(this,'+item.id+')" href="javascript:;" >\n' +
                '<i class="layui-icon">&#xe640;</i>删除</button>';
            str = str + '</td>';
            str = str + '</tr>';
        });
        $('#tbody').empty().html(str.toString());
        //加上这个才能渲染form
        form.render();
    }

    function renderFile() {
        let str = '';
        str = str + '<tr>';
        str = str + '<td colspan="5" style="text-align: center">';
        str = str +'暂无数据';
        str = str + '</td>';
        str = str + '</tr>';
        $('#tbody').empty().html(str.toString());
        form.render();
    }
    getCategorys();

    //全选
    form.on('checkbox(all)', function(data){
            if(data.elem.checked){
                $('.check').prop("checked",true);
                form.render('checkbox');
            }else {
                $('.check').prop("checked",false);
                form.render('checkbox');
            }
    });
    //一个没选，取消全选，
    form.on('checkbox(one)', function () {
        let item = $(".check");
        for (let i = 0; i < item.length; i++) {
            if (item[i].checked == false) {
                $("#all").prop("checked", false);
                form.render('checkbox');
                break;
            }
        }
        //如果都勾选了  勾上全选
        let  all=item.length;
        for (let j = 0; j < item.length; j++) {
            if (item[j].checked == true) {
                all--;
            }
        }
        if(all==0){
            $("#all").prop("checked", true);
            form.render('checkbox');
        }
    });

//批量删除
    $('#batchDelete').click(function () {
        let ids = new Array();
        let item = $(".check");
        for (let i = 0; i < item.length; i++) {
            if (item[i].checked == true) {
                ids.push(item[i].name)
            }
        }
        if(ids.length == 0){
            layer.msg("未选择",{icon: 5, shift: 6});
            return false;
        }
        var data = {
            ids: JSON.stringify(ids)
        };
        $.ajax({
            type: "POST",
            data: data,
            url: batchDeleteCategory,
            success: function (result) {
                if(result.code == SUCCESS_CODE){
                    layer.msg("删除成功",{icon: 6, shift: 6});
                    window.location.reload();
                }
            }
        })

    });

//状态转换
    form.on('switch(switch)', function(data){
        let msg = {
            id: data.elem.name,
            state:''
        };
        if(data.elem.checked){
            msg.state = 1
        }else {
            msg.state = 0
        }
        $.ajax({
            type: "POST",
            data: msg,
            url: updataCategoryState,
            success: function (result) {
                if(result.code == FAIL_CODE){
					layer.msg(result.msg,{icon: 5, shift: 6});
                    window.location.reload();
                }
            }
        })
    });

    //搜索
    $('.search').click(function () {
        page.id = $('#categoryId').val();
        page.name = $('#categoryName').val();
        getCategorys();
    })
});

//分类删除
function category_del(obj,id){
    let data = {
        id: id
    };
    layer.confirm('确认要删除吗？',function(){
        //发异步删除数据
        $.ajax({
            type: "DELETE",
            data: data,
            url: deleteCategory,
            success: function (result) {
                if(result.code == SUCCESS_CODE){
                    $(obj).parents("tr").remove();
                    layer.msg('已删除!',{icon:1,time:1000});
                }
            }
        })
    });
}