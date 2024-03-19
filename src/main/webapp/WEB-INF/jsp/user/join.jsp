<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
<link rel="stylesheet" href="/static/css/style.css" type="text/css">
</head>
<body>

	<div id="wrap">
		<header class="d-flex align-items-center">
			<h1 class="ml-3">Memo</h1>
		</header>
		<section class="contents d-flex justify-content-center">
			<div class="user-box my-5">
				<h2 class="text-center">회원 가입</h2>
				<input type="text" placeholder="아이디" class="form-control mt-5" id="idInput">
				<input type="password" placeholder="비밀번호" class="form-control mt-3" id="passwordInput">
				<input type="password" placeholder="비밀번호 확인" class="form-control mt-3" id="passwordConfirmInput">
				<input type="text" placeholder="이름" class="form-control mt-3" id="nameInput">
				<input type="text" placeholder="이메일" class="form-control mt-3" id="emailInput">
				<button type="button" class="btn btn-secondary btn-block mt-3" id="joinBtn">가입</button>
			</div>
		</section>
		<footer class="d-flex justify-content-center align-items-center">
			<div>
				Copyright 2024. marondal All rights reserved.
			</div>
		</footer>
	</div>

<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.min.js" integrity="sha384-+sLIOodYLS7CIrQpBjl+C7nPvqq+FbNUBDunl/OZv93DB7Ln/533i8e/mZXLi/P+" crossorigin="anonymous"></script>
<script>
	$(document).ready(function() {
		$("#joinBtn").on("click", function() {
			
			let id = $("#idInput").val();
			let password = $("#passwordInput").val();
			let passwordConfirm = $("#passwordConfirmInput").val();
			let name = $("#nameInput").val();
			let email = $("#emailInput").val();
			
			if(id == "") {
				alert("아이디를 입력하세요");
				return ;
			}
			
			if(password == "") {
				alert("비밀번호를 입력하세요");
				return ;
			}
			
			if(password != passwordConfirm) {
				alert("비밀번호가 일치하지 않습니다");
				return ;
			}
			
			if(name == "") {
				alert("이름을 입력하세요");
				return ;
			}
			
			if(email == "") {
				alert("이메일을 입력하세요");
				return ;
			}
			
			$.ajax({
				type:"post"
				, url:"/user/join"
				, data:{"loginId":id, "password":password, "name":name, "email":email}
				, success:function(data) {
					if(data.result == "success") {
						location.href = "/user/login-view";
					} else {
						alert("회원가입 실패");
					}
				}
				, error:function() {
					alert("회원가입 에러!");
				}
			});
			
			
		});
		
	});
	

</script>

</body>
</html>