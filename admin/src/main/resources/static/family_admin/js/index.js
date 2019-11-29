layui.use('jquery', function(){
	let $ = layui.jquery;
	
	//admin名称
    function username() {
        $.ajax({
            type: "GET",
            url: getUserName,
            success: function (result) {
                if(result.code == SUCCESS_CODE){
                    $('.admin').text(result.data)
                }
            }
        })
    }
    username();

    //退出账号
    $('#logout').click(function () {
        $.ajax({
            type: "GET",
            url: logout,
            success: function (result) {
                if(result.code == SUCCESS_CODE){
                    window.location.replace("login.html");
					window.sessionStorage.removeItem("access_token");
                }
            }
        })
    })
});