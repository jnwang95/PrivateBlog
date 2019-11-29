	let data = {
		username: '',
		password: '',
		verify: '',
		uuid:''
	};

$(document).ready(function(){
	getSrc();
	})
	//获取验证码
	function getSrc() {
		$.ajax({
			type: "GET",
			url: captcha,
			success: function (result) {
			 var src = result.data.src;
			 $('#imgVerify').attr("src",src);
			 data.uuid = result.data.uuid
			}
		});
	}
	//验证码点击刷新事件
$('#imgVerify').click(function(){
	getSrc();
});
	
layui.use('form', function(){
	let form = layui.form;	
	$('#submit').click(function(){
		data.username = $('#username').val();
		data.password = $('#password').val();
		data.verify = $('#verify').val();
			$.ajax({
				type: "POST",
				url: login,   
				data: data,
				success: function (result) {
				   if(result.code == SUCCESS_CODE){
					   //此方法用于同一用户不同页面传递值
					   window.sessionStorage.setItem("access_token", result.data);
					   //这种写法跳转页面后浏览器不会有返回按钮
					   window.location.replace("index.html");
				   }
				},
				error: function(result){
					layer.msg("登录失败！")
				}
			});
	})
		
});