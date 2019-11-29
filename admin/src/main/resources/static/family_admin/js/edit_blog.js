layui.config({
    base: '../lib/layui/lay/modules/inputTags.js'//模块存放的目录
}).use(['inputTags','jquery','form'],function(){
    let form = layui.form;
    let $ = layui.jquery;
    let inputTags = layui.inputTags;
    let url=window.location.search; //获取url中"?"符后的字串  
    let id = url.substr(url.indexOf("=")+1);
    let testEditor;
    let blogContent = {
        id:id,
        img:'',
        title:'',
        subTitle:'',
        categoryId:'',
        label:'',
        content:''
    };

    function getBlogById(id) {
        $.ajax({
            type: "GET",
            url: ctx + "blog/getBlogById/"+id,
            success: function (result) {
                if(result.code == SUCCESS_CODE){
                    testEditor.setMarkdown(result.data.content);
                    $('#title').attr("value",result.data.title);
                    $('#subTitle').attr("value",result.data.subTitle);
                    getCategory(result.data.categoryId);
                    //多模块tag
                    inputTags.render({
                        elem: '#inputTags',
                        aldaBtn: true,
                        content: result.data.labels
                    });
                }
            }
        })
    }
    getBlogById(id);

    //md富文本编辑器
    $(function() {
        testEditor = editormd("test-editormd", {
            width: "90%",
            height: 640,
            syncScrolling: "single",
            //这个lib路径和项目里面对应
            path: "lib/md/lib/",
            saveHTMLToTextarea: true
        });
    });

    //分类下拉框初始化
    function getCategory(id) {
        $.ajax({
            type: "GET",
            url: ctx + "blog/getCategoryIdAndName",
            success: function (result) {
                if(result.code == SUCCESS_CODE){
                    write(result.data,id);
                }
            }
        })
    }

    function write(rows,id) {
        var str = "";
        str = str + '<option value="">请选择分类</option>';
        $.each(rows,function (index, item) {
            if(item.id == id){
                str = str + '<option value="'+item.id+'" selected>'+item.name+'</option>';
            }else {
                str = str + '<option value="'+item.id+'">'+item.name+'</option>';
            }
        });
        $('#category').html(str.toString());
        form.render();
    }

    $('#submit').click(function () {
        let data = $('#tags').find('em');
        let str = '';
        for(let i=0;i<data.length;i++){
            if(i == data.length-1){
                str = str + data[i].textContent
            }else {
                str = str + data[i].textContent+','
            }
        }
        blogContent.title = $('#title').val();
        blogContent.subTitle = $('#subTitle').val();
        blogContent.categoryId = $('#category').val();
        blogContent.label = str.toString();
        blogContent.content = testEditor.getMarkdown();
        blogContent.img = window.sessionStorage.getItem("img_confirm");
        if(blogContent.title.length == 0){
            layer.msg("请输入主题",{icon: 5, shift: 6});
            return false;
        }
        if(blogContent.subTitle.length == 0){
            layer.msg("请输入副主题",{icon: 5, shift: 6});
            return false;
        }
        if(blogContent.categoryId.length == 0){
            layer.msg("请输入分类",{icon: 5, shift: 6});
            return false;
        }
        if(blogContent.content.length == 0){
            layer.msg("请输入内容",{icon: 5, shift: 6});
            return false;
        }

        $.ajax({
            type: "POST",
            url: ctx + "blog/updataBlog",
            data: blogContent,
            success: function (result) {
                if(result.code == SUCCESS_CODE){
                    layer.msg(result.data);
                    window.sessionStorage.removeItem("img_confirm");
                    window.sessionStorage.removeItem("img_refresh");
                    setTimeout(function(){
                        window.location.reload();
                    }, 2000);
                }else {
					layer.msg(result.msg,{icon: 5, shift: 6});
                    layer.msg("修改失败")
                }
            }
        })
    });
});