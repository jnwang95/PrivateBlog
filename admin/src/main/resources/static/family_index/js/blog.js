//获取博客内容
	function content(){
		 let url=window.location.search; //获取url中"?"符后的字串  
		    let id = url.substr(url.indexOf("=")+1);
			$.ajax({
				type:'get',//HTTP请求类型
				url:'http://127.0.0.1:8080/view/blog/getContent/'+id,
				success:function(data){
					let result =  data.data;
					$('#title').text(result.title);
					$('#subTitle').text(result.subTitle);
					// $('#contents').html(marked(result.content))
					editormd.markdownToHTML("contents", {
						markdown        : "\r\n" + result.content ,
						htmlDecode      : "style,script,iframe",
						tocm            : true,
						emoji           : true,
						taskList        : true,
						tex             : true,
						flowChart       : true,
						sequenceDiagram : true,
					});
				}
			});
	}
	
	//添加评论
	 function submit(){
		 $('.submit').click(function(){
			var content =  $('textarea').val();
			var length = content.length;
			if(length == 0 || length > 140){
				$('#error').html('<span style = "color:red">评论内容不合法，请重新输入。</span>')
				$('textarea').val("");
				return false;
			}
			var comment = {
				content: content
			};
			$.ajax({
				type:'post',//HTTP请求类型
				data: comment,
				url:'http://127.0.0.1:8080/comment',
				success:function(data){
					var result =  data.data;

				}
			});
		 })
	}

	//获取评论
	function getComment(){
		$.ajax({
			type:'get',
			url:'http://127.0.0.1:8080/getComment',
			success:function(data){
				var result =  data.data;
				let str = "";
				$.each(result, function (index, item) {
					str = str + '<p style="font-size: 12px;">第'+(index + 1)+'楼</p>';
					str = str + '<div class="well well-lg">'+item+'</div>';
				});
				$('#commentList').html(str.toString());
			}
		});
	}

	function writeCommnet(){
		$('textarea').focus(function(){
			$('#error').html('');
		})
	}
$(function(){
	content();
	submit();
	getComment();
	writeCommnet();
});