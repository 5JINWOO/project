<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>


<div class="container">
	<div class="join_wrap">
		<h2>Register</h2>
		<form>
			<div class="form-group">
				<label for="username" id="red">※ USERNAME</label>
				<input type="text"
					class="form-control" placeholder="Enter your username." id="username"
					name="username">
			</div>
			<div class="form-group">
                <label for="nickname">NICKNAME</label>
                <input type="text"
                    class="form-control" placeholder="Enter your nickname." id="nickname"
                    name="nickname">
            </div>
			<div class="form-group">
				<label for="password" id="red">※ PASSWORD</label> <input type="password"
					class="form-control" placeholder="Enter your password." id="password"
					name="password">
			</div>
			<div class="form-group">
				<label for="password" id="red">※ PASSWORD CONFIRM</label> <input type="password"
					class="form-control" placeholder="Please enter your password once more." id="password2"
					name="password2">
			</div>
			<div class="form-group">
                <label for="email" id="red">※ EMAIL</label>
                <input type="email"
                    class="form-control" placeholder="Enter your email." id="email"
                    name="email">
            </div>
            <div class="form-group">
                <label for="mobile">MOBILE</label>
                <input type="number"
                    class="form-control" placeholder="Enter your mobile." id="mobile"
                    name="mobile">
            </div>
		</form>
		<div class="form-grouping-save">
		<button id="btn-save" class="btn btn-primary">SAVE</button>
		</div>
	</div>
</div>
<script src="/js/user.js"></script>
<br />
<%@ include file="../layout/footer.jsp"%>