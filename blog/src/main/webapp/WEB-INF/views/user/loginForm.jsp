<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="container">
	<div class="login_wrap">
		<h2>로그인</h2>
		<form action="/auth/loginProc" method="POST"> 
			<div class="form-group">
				<label for="username">Username</label> <input type="text"
					class="form-control" placeholder="Enter Username" id="username"
					name="username">
			</div>
			<div class="form-group">
				<label for="password">Password</label> <input type="password"
					class="form-control" placeholder="Enter password" id="password"
					name="password">
			</div>
			<div class="login_btn_wrap">

				<div>
					<button type="submit" id="btn-login" class="btn btn-primary">로그인</button>
				</div>
				<div>
					<a
						href="https://kauth.kakao.com/oauth/authorize?client_id=af450c61d50cc918b7c49efd944263ea&redirect_uri=http://localhost:8083/auth/kakao/callback&response_type=code">
						<button type="button" id="btn_kakaoLogin" class="btn btn-warning">카카오
							로그인</button>
					</a>
				</div>

			</div>
		</form>
	</div>
</div>
<br>
<%@ include file="../layout/footer.jsp"%>