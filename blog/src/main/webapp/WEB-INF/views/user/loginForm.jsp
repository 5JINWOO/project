<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="container">
	<div class="login_wrap">
		<h2>Twohand Market</h2>
		<form action="/auth/loginProc" method="POST"> 
			<div class="form-grouping">
				<label for="username">USERNAME</label> <input type="text"
					class="form-control" placeholder="Enter Username" id="username"
					name="username">
			</div>
			<div class="form-grouping">
				<label for="password">PASSWORD</label> <input type="password"
					class="form-control" placeholder="Enter Password" id="password"
					name="password">
			</div>
			<div class="form-grouping-login">
				<div>
					<button type="submit" id="btn-login" class="btn btn-primary">LOGIN</button>
				</div>
				<div>
					<a
						href="https://kauth.kakao.com/oauth/authorize?client_id=af450c61d50cc918b7c49efd944263ea&redirect_uri=http://localhost:8083/auth/kakao/callback&response_type=code">
						<button type="button" id="btn_kakaoLogin" class="btn btn-warning">
						<img src="image/kakao_icon.png" /> Login with Kakao Account
						</button>
					</a>
				</div>
				<div class="form-grouping">
				    <h5><a href="/auth/joinForm">Create an Account?</a></h5>
				    <h6><a href="#">Forget your password?</a></h6>
				</div>
			</div>
		</form>
	</div>
</div>
<br>
<%@ include file="../layout/footer.jsp"%>