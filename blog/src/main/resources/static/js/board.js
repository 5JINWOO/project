let index = {
	
	init: function() {
		$("#btn-save").on("click", () => {
			//화살표 함수사용 이유: this를 바인딩하기 위해 사용
			this.save();
		});
		$("#btn-delete").on("click", () => {
			//화살표 함수사용 이유: this를 바인딩하기 위해 사용
			this.deleteById();
		});
		$("#btn-update").on("click", () => {
			//화살표 함수사용 이유: this를 바인딩하기 위해 사용
			this.update();
		});
		$("#btn-reply-save").on("click", () => {
			//화살표 함수사용 이유: this를 바인딩하기 위해 사용
			this.replySave();
		});
		$("#btn-reply-delete").on("click", () => {
			//화살표 함수사용 이유: this를 바인딩하기 위해 사용
			this.replyDelete();
		});
		
	},
	

	save: function() {
		//alert('user의 save함수 호출됨');
		let data = {
			title: $("#title").val(),
			content: $("#content").val()
		};
		
		console.log(data); //자바스크립트 오브젝트
		$.ajax({
			//회원가입 수행 요청 
			//(100초라 가정한다면 도중에 done이나 fail 실행 )
			type: "POST",
			url: "/api/board",
			data: JSON.stringify(data), //http body 데이터
			contentType: "application/json; charset=utf-8",
			dataType: "json" //자동으로 변환해주기 때문에 생략 가능
			//응답의 결과가 문자열이 아닌 json으로 변환
		}).done(function(resp) {
			alert("글쓰기가 완료되었습니다.");
			location.href = "/";
			//응답이 정상
		}).fail(function(error) {
			alert(JSON.stringify(error));
			//응답이 비정상
		});
		//ajax통신을 이용해서 3개의 데이터를 json으로 변경하여 insert요청!!
	},
	
	
	deleteById: function() {
		//alert('user의 save함수 호출됨');
		let id = $("#id").text();
		
		$.ajax({
			//회원가입 수행 요청 
			//(100초라 가정한다면 도중에 done이나 fail 실행 )
			type: "DELETE",
			url: "/api/board/"+id, ////////////////////////
			dataType: "json" //자동으로 변환해주기 때문에 생략 가능
			//응답의 결과가 문자열이 아닌 json으로 변환
		}).done(function(resp) {
			alert("삭제가 완료되었습니다.");
			location.href = "/";
			//응답이 정상
		}).fail(function(error) {
			alert(JSON.stringify(error));
			//응답이 비정상
		});
		//ajax통신을 이용해서 3개의 데이터를 json으로 변경하여 insert요청!!
	},
	
	update: function() {
		//alert('user의 save함수 호출됨');
		let id = $("#id").val();
		
		let data = {
			title: $("#title").val(),
			content: $("#content").val()
		};
		$.ajax({
			//회원가입 수행 요청 
			//(100초라 가정한다면 도중에 done이나 fail 실행 )
			type: "PUT",
			url: "/api/board/"+id,
			dataType: "json", //자동으로 변환해주기 때문에 생략 가능
			data: JSON.stringify(data), //http body 데이터
			contentType: "application/json; charset=utf-8"
			//응답의 결과가 문자열이 아닌 json으로 변환
		}).done(function(resp) {
			alert("수정이 완료되었습니다.");
			location.href = "/";
			//응답이 정상
		}).fail(function(error) {
			alert(JSON.stringify(error));
			//응답이 비정상
		});
		//ajax통신을 이용해서 3개의 데이터를 json으로 변경하여 insert요청!!
	},
	
	replySave: function() {
		let data = {
			userId: $("#userId").val(),
			boardId: $("#boardId").val(),
			content: $("#reply-content").val()
		};
		
		
		$.ajax({
			//회원가입 수행 요청 
			//(100초라 가정한다면 도중에 done이나 fail 실행 )
			type: "POST",
			url: `/api/board/${data.boardId}/reply`,
			data: JSON.stringify(data), //http body 데이터
			contentType: "application/json; charset=utf-8",
			dataType: "json" //자동으로 변환해주기 때문에 생략 가능
			//응답의 결과가 문자열이 아닌 json으로 변환
		}).done(function(resp) {
			alert("댓글작성이 완료되었습니다.");
			location.href = `/board/${data.boardId}`;
			//응답이 정상
		}).fail(function(error) {
			alert(JSON.stringify(error));
			//응답이 비정상
		});
		//ajax통신을 이용해서 3개의 데이터를 json으로 변경하여 insert요청!!
	},
	replyDelete: function() {
		//alert('user의 save함수 호출됨');
		let id = $("#replyId").text();
		
		$.ajax({
			//회원가입 수행 요청 
			//(100초라 가정한다면 도중에 done이나 fail 실행 )
			type: "DELETE",
			url: "/api/board/"+id+"/reply",
			dataType: "json"
			
		}).done(function(resp) {
			alert("삭제가 완료되었습니다.");
			location.href = "/";
			//응답이 정상
		}).fail(function(error) {
			alert(JSON.stringify(error));
			//응답이 비정상
		});
		//ajax통신을 이용해서 3개의 데이터를 json으로 변경하여 insert요청!!
	}
	
}
index.init();