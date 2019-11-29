$(function(){
		let ctx = "http://127.0.0.1:8080/index/";
		let pagesize = '';
		let pageNum = 1;
		let url = "index.html";
		menu();
		header();
		banner(url);
		bottom();
		getBLogs(pageNum);
		page(pagesize,pageNum);

		//博客内容标题
		function getBLogs(pageNum){
			$.ajax({
				type:'get',
				async:false,
				url: ctx + 'getBLogs/'+pageNum,
				success:function(result){
					let str = "";
					$.each(result.data.data, function (index, item) {
						str = str + '<div class="col-sm-6 col-md-3" style="height: 350px">';
						str = str + '<div class="thumbnail" style="height: 300px" onclick="show('+item.id+')" ">';
						str = str + '<img src="'+item.img+'" alt="">';
						str = str + '<div class="caption">';
						str = str + '<h3>' + item.title+ '</h3>';
						str = str + '<p>'+ item.subTitle +'</p>';
						str = str + '</div>';
						str = str + '</div>';
						str = str + '</div>';
					});
					$('.blogTitle').html(str.toString());
					pagesize = result.data.totalPages;
				}
			});
		}

		function page(pagesize,pageNum){
			$("#wr-page").wrpage({
				pagesize: pagesize,
				wr_current: pageNum,
				cb: function(e) {
					getBLogs(e);
				}
			});
		}

	function bottom() {
		let top = $('.cro_bot').offset().top;
		if(top<600){
			$('.cro_bot').css("width","100%");
			$('#tbody').css("height","600px")
		}
	}

	});

	function show(id) {
		window.location.href = "blog.html?id="+id;
	}
