<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>
<div class="container">
    <div class="post-wrap">
        <h2>Post your item</h2>
        <form>
            <div class="form-group">
                <label for="title">TITLE</label>
                <input type="text" class="form-control" placeholder="Enter your title" id="title">
            </div>
            <div class="form-group">
                <label for="content">CONTENT</label>
                <textarea class="form-control summernote" rows="5" id="content"></textarea>
            </div>
        </form>
        <button id="btn-savePost" class="btn btn-primary">POST</button>
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