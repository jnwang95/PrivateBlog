layui.use(['jquery'],function() {
    let $ = layui.jquery;
    let url=window.location.search; //获取url中"?"符后的字串  
    let id = url.substr(url.indexOf("=")+1);

    let testEditormdView;

    function getBlogById(id) {
        $.ajax({
            type: "GET",
            url: ctx + "blog/getBlogContentById/"+id,
            success: function (result) {
                if(result.code == 200){
                        testEditormdView = editormd.markdownToHTML("test-editormd-view", {
                            markdown        : "\r\n" + result.data ,
                            htmlDecode      : "style,script,iframe",
                            tocm            : true,
                            emoji           : true,
                            taskList        : true,
                            tex             : true,
                            flowChart       : true,
                            sequenceDiagram : true,
                        });
                }
            }
        })
    }
    getBlogById(id);
});