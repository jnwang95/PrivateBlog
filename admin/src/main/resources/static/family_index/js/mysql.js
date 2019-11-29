
	 $('.mysql').click(function getMysql(){
		$.ajax({
			type:'get',//HTTP请求类型
			url:'http://127.0.0.1:8080/databases',
			success:function(result){
				listDatabase(result.data);
			}
		});
	})
	
	function listDatabase(data){
			let str = "";
			str = str + '<ul>';
			$.each(data, function (index, item) {
				str = str + '<li >';	
				str = str + '<button style = "width:180px;margin-bottom: 10px;" class="btn btn-info databaseList"  value = '+item+'>';
				str = str + item;	
				str = str + '</button>';
				str = str + '<ul id='+item+'>';
			
				str = str + '</ul>';
				str = str + '</li>';		
			});
			str = str + '</ul>';
			$('.dataleft').html(str.toString())
	}
	
	var twoData={
		database:'',
		table:''
	}
	
	$(document).on('click',".databaseList",function(e){ 
		var data = $(this).val();
		twoData.database = data;
		if($('#'+data+'').html() == ''){
			$.ajax({
				type:'get',//HTTP请求类型
				url:'http://127.0.0.1:8080/tables/'+data,
				success:function(result){
					let str = "";
					$.each(result.data, function (index, item) {
						str = str + '<li>';	
						str = str + '<a class="subTableList" href = "#">';
						str = str + item;	
						str = str + '</a>';
						str = str + '</li>';		
					});
					$('#'+data+'').html(str.toString());
				}
			});
		}else{
			$('#'+data+'').html('');
		}

	})
	
	$(document).on('click',".subTableList",function(e){ 
		$('.tableBottom').css("display","none");
		$('.oppen').css("display","block");
		$('.tableTop').css("display","block");
		var table = $(this).html();
		twoData.table = table;	
		//表头
		$.ajax({
			type:'get',//HTTP请求类型
			url:'http://127.0.0.1:8080/getTableName/'+twoData.database+'/'+twoData.table,
			success:function(result){
				let str = "";
				$.each(result.data, function (index, item) {
					str = str + '<th>';	
					str = str + item;	
					str = str + '</th>';		
				});
				$('#thead').html(str.toString());
			}
		});
		//表身
		$.ajax({
			type:'get',//HTTP请求类型
			url:'http://127.0.0.1:8080/getTableData/'+twoData.database+'/'+twoData.table,
			success:function(result){
				let str = "";
				$.each(result.data, function (index, item) {
					str = str + '<tr>';	
					$.each(item, function (index, subItem) {
					str = str + '<td>'+subItem+'</td>'
					})
					str = str + '</tr>';		
				});
				$('#tbody').html(str.toString());
			}
		});
		
	})
	
	$('.tableCss').click(function(){
		$('.tableBottom').css("display","block");
		$('.tableTop').css("display","none");
		$.ajax({
			type:'get',//HTTP请求类型
			url:'http://127.0.0.1:8080/getTableAttributes/'+twoData.database+'/'+twoData.table,
			success:function(result){
				let str = "";
				$.each(result.data, function (index, item) {
					str = str + '<tr>';	
					str = str + '<td>'+item.COLUMN_NAME+'</td>'
					str = str + '<td>'+item.DATA_TYPE+'</td>'
					str = str + '<td>'+item.COLUMN_TYPE+'</td>'
					str = str + '<td>'+item.COLUMN_DEFAULT+'</td>'
					str = str + '<td>'+item.IS_NULLABLE+'</td>'
					str = str + '<td>'+item.COLUMN_KEY+'</td>'
					str = str + '<td>'+item.EXTRA+'</td>'
					str = str + '<td>'+item.COLUMN_COMMENT+'</td>'
					str = str + '</tr>';		
				});
				$('#tableCss').html(str.toString());
			}
		});
	})
	
	$(function(){
	});
	