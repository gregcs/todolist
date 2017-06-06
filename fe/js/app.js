(function(window) {

	$(document).ready(function() {
		
		//전체 목록 조회
		findAll();
		
		//할일 목록 조회
		active();
		
		//완료 목록 조회
		completed();
		
		//전체 목록 조회
		selected();
		
		//할일 삽입
		insert();
		
		//완료한 일 선택
		update();
		
		//삭제할 일 선택
		remove();

		//완료한 목록 전체 삭제
		deleteCompleted();
	});






	//전체 목록 조회
	var findAll= function() {

		$('.todo-list').empty();

		$.ajax({

			type : "GET",
			async : false, 
			url : "http://localhost:8080/api/todos/findall",
			contentType: "application/json",
			processData: false,
			success : function(response) {

				$.each(response, function(index, obj){

					if (obj.completed == 0)
					{
						$('.todo-list').append('<li> <div class="view"> <input class="toggle" type="checkbox"><label>'
							+ obj.todo +'</label>'+'<input type="hidden" id="id" value="'
							+ obj.id +'"/><button class="destroy"></button></div><input class="edit"></li>');
					}
					else
					{
						$('.todo-list').append('<li class="completed"> <div class="view"> <input class="toggle" type="checkbox" checked><label>'
							+ obj.todo +'</label>'+'<input type="hidden" id="id" value="'
							+ obj.id +'"/><button class="destroy"></button></div><input class="edit"></li>');
					}



				});//end of each
			}//end of success
		});//end of ajax
		count();
	}//end of function






	//전체 목록 조회
	var selected= function() {

		$('.selected').bind("click",function(){

			$('.todo-list').empty();

			$.ajax({

				type : "GET",
				async : false,
				url : "http://localhost:8080/api/todos/findall",
				contentType: "application/json",
				processData: false,
				success : function(response) {

					$.each(response, function(index, obj){

						if (obj.completed == 0)
						{
							$('.todo-list').append('<li> <div class="view"> <input class="toggle" type="checkbox"><label>'
								+ obj.todo +'</label>'+'<input type="hidden" id="id" value="'
								+obj.id+'"/><button class="destroy"></button></div><input class="edit"></li>');
						}
						else
						{
							$('.todo-list').append('<li class="completed"> <div class="view"> <input class="toggle" type="checkbox" checked><label>'
								+obj.todo+'</label>'+'<input type="hidden" id="id" value="'
								+obj.id+'"/><button class="destroy"></button></div><input class="edit"></li>');
						}
					});//end of each

					count();
			

				}//success
			});//end of ajax
		});//end of click
	}//end of function


	

	//할일 목록 조회
	var active = function() {
		
		$('.active').bind("click",function(){

			$('.todo-list').empty();

			$.ajax({

				type : "GET",
				async : false, 
				url : "http://localhost:8080/api/todos/active",
				contentType: "application/json",
				processData: false,
				success : function(response) {

					$.each(response, function(index, obj){


						$('.todo-list').append('<li> <div class="view"> <input class="toggle" type="checkbox"><label>'
							+obj.todo +'</label>'+'<input type="hidden" id="id" value="'
							+obj.id+'"/><button class="destroy"></button></div><input class="edit"></li>');

					});
					count();
				


				}//end of success
			});//end of ajax
		});//end of click
	}//end of function




	//완료 목록 조회
	var completed = function() {

		$('.completed').bind("click",function(){

			$('.todo-list').empty();

			$.ajax({

				type : "GET",
				async : false, 
				url : "http://localhost:8080/api/todos/completed",
				contentType: "application/json",
				processData: false,
				success : function(response) {

					$.each(response, function(index, obj){

						$('.todo-list').append('<li class="completed"> <div class="view"> <input class="toggle" type="checkbox" checked><label>'
							+obj.todo+'</label>'+'<input type="hidden" id="id" value="'
							+obj.id+'"/><button class="destroy"></button></div><input class="edit"></li>');
					});

					$('.todo-count').text("0 item left");
				}//end of success
			});//end of ajx
		});//end of click
   }//end of function




   //할일 삽입
	var insert = function() {
		var input = $('.new-todo');
		input.keydown(function(e) {
			if (e.which == 13) 
			{
				if (input.val() != "") 
				{

					var json = {
						'todo' : input.val(),
						'completed' : 0,
						'date' : new Date().toISOString()
					};
					$.ajax({
						type : "POST",
						async : true,
						url : "http://localhost:8080/api/todos",
						data : JSON.stringify(json),
						contentType : "application/json",
						processData : false,
						success : function(result) {
							
							if (result.completed == 0)
							{
								$('.todo-list').append('<li> <div class="view"> <input class="toggle" type="checkbox"><label>'
									+ result.todo 
									+'</label><button class="destroy"></button></div><input class="edit"></li>');
							}
							else
							{
								$('.todo-list').append('<li class="completed"> <div class="view"> <input class="toggle" type="checkbox" checked><label>'
									+ result.todo
									+ '</label><button class="destroy"></button></div><input class="edit"></li>');
							}
							count();


						}//end of success
					});//end of ajax
				}//end of if
			}//end of if
		});//end of keydown
	}//end of function



	//완료한 일 선택
	var update = function() {

		var input = $('.new-todo');
		
		$(".todo-list").delegate('.toggle','click', function(){

			var num = $(this).closest('li').prevAll().length;
			var id = $('.todo-list li div input#id')[num].value;
			var label = $('.todo-list li div ').text().split(' ');
			var checked = $('.todo-list li div input.toggle')[num].checked? 1:0;

			var json = {
				'todo' : id,
				'completed' : checked,
				'date' : new Date().toISOString()
			};
			$.ajax({
				type : "PUT",
				async : true,
				url : "http://localhost:8080/api/todos/"+id,
				data : JSON.stringify(json),
				contentType : "application/json",
				processData : false,
				success : function(response) {
						//alert("update완료");
				}
	        });	//end of ajax

			findAll();


		}); //end of delefate
	} //end of function




	//할일 삭제
	var remove = function(){

		$(document).delegate(".destroy","click",function(){

			var num = $(this).closest('li').prevAll().length;
			var id = $('.todo-list li div input#id')[num].value;

			$.ajax({

				type : "DELETE",
				async : false, 
				url : "api/todos/" + id,
				success : function(response) {
						//alert("remove완료");
				}});//end of ajax

			findAll();


		});//end of click
	}//end of function




	//완료한 목록 삭제 
	var deleteCompleted = function(){

		$(document).delegate(".clear-completed","click",function(){

			$.ajax({

				type : "DELETE",
				async : false, 
				url : "api/todos/deleteCompleted",
				success : function(response) {
							//alert("deleteCompleted완료");
				}}); //end of ajax

			findAll();


		});//end of click
	}//end of function


	//완료한 목록 삭제 
	var count = function() {
		$.ajax({
			url : "http://localhost:8080/api/todos/count",
		}).then(function(data) {
			
			$('.todo-count').text(data + " item left");

		});//end of ajax
	}//end of function

})(window); //end
