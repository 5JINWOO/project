<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>
<div class="container">
    <div class="postUpdate-wrap">
        <h2>Update your post</h2>
        <form>
            <input type="hidden" id="id" value="${board.id }">
            <div class="form-group">
                <input value="${board.title}" type="text" class="form-control" placeholder="Update your title" id="title">
            </div>
            <div class="form-group">
                <textarea class="form-control summernote" rows="5" id="content">${board.content }</textarea>
            </div>
        </form>
	    <button id="btn-update" class="btn btn-primary">UPDATE</button>
	    <button id="btn-back" class="btn btn-outline-secondary btn-sm" onclick="history.back()">BACK</button>
	</div>
</div>
<br>

<script>
	$('.summernote').summernote({
		tabsize: 2,
		height: 300
	});
</script>
<br>
<script type="text/javascript" src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp" %>