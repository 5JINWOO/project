<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<div class="container">
	<div class="user_update">
		<h2>회원정보 수정</h2>
		<form>
			<input type="hidden" id="id" value="${principal.user.id }" />
			<div class="form-group">
				<label for="username">Username</label> <input type="text"
					class="form-control" placeholder="Enter Username" id="username"
					value="${principal.user.username }" name="username" readonly>
			</div>

			<c:if test="${empty principal.user.oauth }">
				<div class="form-group">
					<label for="password">Password</label> <input type="password"
						class="form-control" placeholder="Enter password" id="password"
						name="password">
				</div>
			</c:if>
			<div class="form-group">
				<label for="email">Email</label> <input type="email"
					class="form-control" placeholder="Enter Username" id="email"
					value="${principal.user.email }" name="username" readonly>
			</div>
		</form>
		<div id="userUpdate_btn_wrap">
			<button id="btn-update" class="btn btn-outline-primary btn-sm">수정 완료</button>
			<button id="btn-update" class="btn btn-outline-secondary btn-sm" onclick="history.back()">돌아가기</button>
		</div>
	</div>
</div>
<script type="text/javascript" src="/js/user.js"></script>
<br>
<%@ include file="../layout/footer.jsp"%>