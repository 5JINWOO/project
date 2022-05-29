<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="container">
	<div id="list_searchWrap">
	    <h2>Item List</h2>
		<form class="row g-3 d-fex justify-content-end">
			<div class="col-auto">
				<input type="text" class="form-control" name="searchText"
					value="${param.searchText}" placeholder="Title or Content">
			</div>
			<div class="col-auto">
				<button type="submit" id="search-btn" class="btn btn-primary mb-3">SEARCH</button>
			</div>
		</form>
	</div>
	<table class="table">
		<thead>
			<tr>
				<th colspan="2" class="board_title">TITLE</th>
				<th class="board_name">WRITER</th>
				<th class="board_date">DATE</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="board" items="${boardsEntity.content }">
				<tr>
					<td style="font-size: 13px; text-align: center;">${board.id }</td>
					<td style="font-size: 15px;"> <a id="titleCss" href="/board/${board.id }">${board.title }</a></td>
					<td style="font-size: 13px; text-align: center;">${board.users.username }</td>
					<td style="font-size: 13px; text-align: center;"><fmt:formatDate pattern="yyyy-MM-dd" value="${board.createDate }" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<div id="list_writeBtn" class="row g-3 d-fex justify-content-end">
		<a href="/board/Form"><button id="post-btn" class="btn btn-success btn-sm">POST NEW ITEM</button></a>
	</div>

	<ul class="pagination d-flex justify-content-center">
		<c:choose>
			<c:when test="${boardsEntity.first}">
				<li class="page-item disabled"><a class="page-link"
					href="/board/list?page=${boardsEntity.number - 1}&searchText=${param.searchText}">Prev</a></li>
			</c:when>
			<c:otherwise>
				<li class="page-item"><a class="page-link"
					href="/board/list?page=${boardsEntity.number - 1}&searchText=${param.searchText}">Prev</a></li>
			</c:otherwise>
		</c:choose>


		<c:forEach begin="${startPage}" end="${endPage}" var="idx">
			<c:choose>
				<c:when test="${idx eq nowPage}">
					<li class="page-item disabled"><a
						class="page-link page-active" href="/board/list?page=${idx-1}&searchText=${param.searchText}">${idx}</a></li>
				</c:when>
				<c:otherwise>
					<li class="page-item"><a class="page-link"
						href="/board/list?page=${idx-1}&searchText=${param.searchText}">${idx}</a></li>
				</c:otherwise>
			</c:choose>
		</c:forEach>

		<c:choose>
			<c:when test="${boardsEntity.last}">
				<li class="page-item disabled"><a class="page-link"
					href="/board/list?page=${param.page + 1}&searchText=${param.searchText}">Next</a></li>
			</c:when>
			<c:otherwise>
				<li class="page-item"><a class="page-link"
					href="/board/list?page=${param.page + 1}&searchText=${param.searchText}">Next</a></li>
			</c:otherwise>
		</c:choose>

	</ul>
</div>
<br>
<script type="text/javascript" src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp"%>
