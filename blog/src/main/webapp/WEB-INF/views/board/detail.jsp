<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<div class="container">


	<div class="board_detail_container">

		<div class="board_top_btnWrap">
			<button class="btn btn-outline-secondary btn-sm"
				onclick="history.back()">돌아가기</button>
			<div>
				<c:if test="${board.users.id==principal.user.id }">
					<button id="btn-delete" class="btn btn-outline-danger btn-sm">삭제</button>
					<a href="/board/${board.id }/updateForm"
						class="btn btn-outline-warning btn-sm">수정</a>
				</c:if>
			</div>
		</div>

		<div class="detail_title">
			<h3>${board.title }</h3>
			<div class="profile_info">
				글 번호 : <span id="id"><i>${board.id }</i></span>
				&nbsp;&nbsp;&nbsp;&nbsp; 작성자 : <span><i>${board.users.username }</i></span>
				&nbsp;&nbsp;&nbsp;&nbsp; 조회수 : <span><i>${board.count }</i></span>
			</div>
		</div>

		<div>
			<div>${board.content }</div>
		</div>


		<div class="reply_container">
			<ul id="reply--box" class="list-group">
				<c:forEach var="reply" items="${board.replys }">
					<li id="reply--1" class="list-group-item d-flex justify-content-between">
						<div>${reply.content }</div>
						<div class="d-flex">
							<div class="font-italic">
								<span id="replyId">
									${reply.id }
								</span>
								작성자 :${reply.user.username } &nbsp;
							</div>
							<c:if test="${reply.user.id==principal.user.id }">
								<button id="btn-reply-delete" class="btn btn-outline-danger btn-sm">삭제</button>
							</c:if>
						</div>
					</li>
				</c:forEach>
			</ul>
			<div >
				<form>
					<input type="hidden" id="userId" value="${principal.user.id }" /> <input
						type="hidden" id="boardId" value="${board.id }" />
					<div >
						<textarea id="reply-content" class="form-control" rows="3"></textarea>
					</div>
					<div class="reply_saveBtnCon">
						<button type="button" id="btn-reply-save" class="btn btn-primary btn-sm">등록</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<br>
<script type="text/javascript" src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp"%>