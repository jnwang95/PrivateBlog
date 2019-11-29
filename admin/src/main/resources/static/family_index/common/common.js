const common_ctx = "http://127.0.0.1:8089/common/";

//目录
function menu(){
    $.ajax({
        type:'get',
        url: common_ctx + 'menu',
        success:function(result){
            //获取该文件的文件名
            const web = location.href.split('/');
            const view = web[web.length - 1].split('?');
            let str = "";
            $.each(result.data, function (index, item) {
                if(view[0] == item.url){
                    str = str + '<li class="active"><a href=" '+item.url +' ">'+item.menuName+'</a></li>'
                }else {
                    str = str + '<li><a href=" '+item.url +' ">'+item.menuName+'</a></li>'
                }
            });
            $('.menu').html(str.toString());
        }
    });
}
//头部文字
function header() {
    $.ajax({
        type:'get',
        url: common_ctx + 'header',
        success:function(result){
            $('.header').html(result.data.header);
        }
    });
}

//banner
function banner(obj){
    $.ajax({
        type:'get',
        url: common_ctx + 'banner/'+obj,
        success:function(result){
            $('.jumbotron').css("background",'url('+result.data.url+')');
            $('.jumbotron').css("background-repeat","no-repeat");
            $('.jumbotron').css("background-size","100% 100%");
            $('.jumbotron').css("height","500px");
            $('.banner-content').html(result.data.content);
        }
    });
}