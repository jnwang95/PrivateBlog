//let ctx = 'http://47.103.121.146:8080/'; //url路劲，按个人配置
//let ctx = 'http://118.89.73.215:8080/';
let ctx = 'http://127.0.0.1:8088/';
//配置状态码
let SUCCESS_CODE = 200;
let FAIL_CODE = 500;
//接收登录后的token
let token = window.sessionStorage.getItem("access_token");
//配置传值通用字符串

//ajax全局配置
$.ajaxSettings.beforeSend = function(xhr,request){
	if(token != null){
		xhr.setRequestHeader('Authorization',token);
	}
}

$(document).ajaxComplete(function(event, xhr, settings) {
	if(xhr.responseJSON.code == FAIL_CODE){
		layer.msg(xhr.responseJSON.msg,{icon: 5, shift: 6});
	}
});

//所有的接口配置
let captcha = ctx + "captcha";
let login = ctx + "login";
let getUserName = ctx + "index/username";
let logout = ctx + "logout";
let getVersion = ctx + "welcome/version";
//blogController
let blog = "blog/";
let ctxblog = ctx + blog;
let deleteCategory = ctxblog + "deleteCategory";
let updataCategoryState = ctxblog + "updataCategoryState";
let batchDeleteCategory = ctxblog + "batchDeleteCategory";
let categorys = ctxblog + "getCategorys";