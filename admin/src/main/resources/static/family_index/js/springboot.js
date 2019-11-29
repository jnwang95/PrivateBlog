$(function(){
		let ctx = "http://127.0.0.1:8080/springboot/";
		let pagesize = '';
		let pageNum = 1;
		menu();
		header();
		bottom();
		
		function bottom() {
			let top = $('.cro_bot').offset().top;
			if(top<600){
				$('.cro_bot').css("width","100%");
				$('#tbody').css("height","600px")
			}
		}

	});