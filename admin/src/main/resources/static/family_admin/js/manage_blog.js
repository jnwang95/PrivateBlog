layui.use(['inputTags','jquery','form','util','laypage'],function() {
    let laypage = layui.laypage;
    let form = layui.form;
    let $ = layui.jquery;
    let util = layui.util;
    var page = {
        pageNum: 1,
        pageSize: 10,
        categoryId:'',
        state:'',
        author:''
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
                    blogs();
                    $('#all').prop("checked",false);
                    form.render('checkbox');
                }
            }
        });
    }

    function blogs() {
        $.ajax({
            type: "POST",
            data: page,
            url: ctx + "blog/getBlogs",
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
    blogs();

    function renderFile() {
        let str = '';
        str = str + '<tr>';
        str = str + '<td colspan="10" style="text-align: center">';
        str = str +'暂无数据';
        str = str + '</td>';
        str = str + '</tr>';
        $('#tbody').empty().html(str.toString());
        form.render();
    }
    function renderList(data) {
        let str = '';
        $.each(data,function (index,item) {
            str = str + '<tr>';
            str = str + '<td>';
            str = str + '<input type="checkbox" name='+item.id+' class="check"  lay-filter="one" lay-skin="primary" />';
            str = str + '</td>';
            str = str + '<td>'+ item.title +'</td>';
            str = str + '<td>'+ item.category +'</td>';
            str = str + '<td>';
            $.each(item.labels,function (sub_index,sub_item) {
                str = str + '<span style="color:#fff;background-color: #009688;line-height: 16px;padding: 4px 8px' +
                    ' 5px 8px;margin-right: 5px">\n' +
                    ' <em style="font-style: normal">'+sub_item+'</em></span>';
            });
            str = str + '</td>';
            str = str + '<td>'+ util.toDateString(item.entryTime) +'</td>';
            if(item.state == 1){
                str = str + '<td class="td-status"><input type="checkbox" lay-filter="switch" name="'+item.id+'"  lay-text="上线|下线" checked lay-skin="switch" /></td>';
            }else {
                str = str + '<td class="td-status"><input type="checkbox" lay-filter="switch" name="'+item.id+'"  lay-text="上线|下线"  lay-skin="switch" /></td>';
            }
            str = str + '<td class="td-manage">';
            str = str + '<button class="layui-btn layui-btn layui-btn-xs"  onclick="xadmin.open(\'编辑\',\'edit_blog.html?id='+item.id+'\',\'\',\'\',true)" >\n' +
                '<i class="layui-icon">&#xe642;</i>编辑</button>';
            str = str + '<button class="layui-btn layui-btn-warm layui-btn-xs"' +
                '  onclick="xadmin.open(\'阅览\',\'view_blog.html?id='+item.id+'\',\'\',\'\',true)" >\n' +
                '<i class="layui-icon">&#xe642;</i>阅览</button>';
            str = str + '<button class="layui-btn-danger layui-btn layui-btn-xs" onclick="blog_del(this,'+item.id+')" href="javascript:;" >\n' +
                '<i class="layui-icon">&#xe640;</i>删除</button>';
            str = str + '</td>';
            str = str + '<td>'+ item.author +'</td>';
            str = str + '</tr>';
        });
        $('#tbody').empty().html(str.toString());
        //加上这个才能渲染form
        form.render();
    }

    //分类下拉框初始化
    function getCategory() {
        $.ajax({
            type: "GET",
            url: ctx + "blog/getCategoryIdAndName",
            success: function (result) {
                if(result.code == 200){
                    var str = "";
                    str = str + '<option value="">请选择分类</option>';
                    $.each(result.data,function (index, item) {
                        str = str + '<option value="'+item.id+'">'+item.name+'</option>';
                    });
                    $('#category').html(str.toString());
                    form.render();
                }
            }
        })
    }
    getCategory();
    //搜索
    $('#search').click(function () {
        page.categoryId = $('#category').val();
        page.state = $('#state').val();
        page.author = $('#author').val();
        blogs();
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
            url: ctx + "blog/updataBlogState",
            success: function (result) {
                if(result.code == FAIL_CODE){
					layer.msg(result.msg,{icon: 5, shift: 6});
                    window.location.reload();
                }
            }
        })
    });

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
            layer.msg("未选择",{icon: 5, time: 1000});
            return false;
        }
        var data = {
            ids: JSON.stringify(ids)
        };
        $.ajax({
            type: "POST",
            data: data,
            url: ctx + "blog/batchDeleteBlog",
            success: function (result) {
                if(result.code == SUCCESS_CODE){
                    layer.msg("删除成功",{icon: 6, time: 1000});
                    window.location.reload();
                }
            }
        })
    });

});

//博客删除
function blog_del(obj,id){
    let data = {
        id: id
    };
    layer.confirm('确认要删除吗？',function(){
        //发异步删除数据
        $.ajax({
            type: "DELETE",
            data: data,
            url: ctx + "blog/deleteBlog",
            success: function (result) {
                if(result.code == SUCCESS_CODE){
                    $(obj).parents("tr").remove();
                    layer.msg('已删除!',{icon:1,time:1000});
                }
            }
        })
    });
}