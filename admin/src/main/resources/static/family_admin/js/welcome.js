layui.use('jquery', function() {
    var $ = layui.jquery;

    //获取当前时间
        function getFormatDate(){
            var nowDate = new Date();
            var year = nowDate.getFullYear();
            var month = nowDate.getMonth() + 1 < 10 ? "0" + (nowDate.getMonth() + 1) : nowDate.getMonth() + 1;
            var date = nowDate.getDate() < 10 ? "0" + nowDate.getDate() : nowDate.getDate();
            var hour = nowDate.getHours()< 10 ? "0" + nowDate.getHours() : nowDate.getHours();
            var minute = nowDate.getMinutes()< 10 ? "0" + nowDate.getMinutes() : nowDate.getMinutes();
            var second = nowDate.getSeconds()< 10 ? "0" + nowDate.getSeconds() : nowDate.getSeconds();
            var nowTime = year + "-" + month + "-" + date+" "+hour+":"+minute+":"+second;
            $('#nowTime').text(nowTime);
        }
    setInterval(function(){
        getFormatDate();
    },1000);

    //获取版本信息
    function version() {
        $.ajax({
            type: "Get",
            url: getVersion,
            success: function (result) {
                if(result.code == SUCCESS_CODE){
                    var str = "";
                    $.each(result.data,function (key, value) {
                        str = str + '<tr>';
                        str = str + '<th>'+key+'</th>';
                        str = str + '<td>'+value+'</td>';
                        str = str + '</tr>';
                    });
                    $('#tbody').html(str.toString());
                }
            }
        })
    }
    version();

});
