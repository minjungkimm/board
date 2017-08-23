<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@include file="../include/header.jsp" %>
	<form role="form" action="modifyPage" method="post">
		<input type="hidden" name="bno" value="${boardVO.bno}">
		<input type="hidden" name="page" value="${cri.page}">
		<input type="hidden" name="perPageNum" value="${cri.perPageNum}">
	</form>	
	<!-- 조회 하면의 경우 나중에 수정이나,삭제 작업위해서 반드시 원래 게시물의 bno 값을 갖고있어야한다
	그러므로, hidden처리된 bno값 넣음 -->
	<!-- 조회화면이므로, 사용자가 수정불가하게 readonly 처리함 -->
		<div class="box-body">
			<div class="form-group">
				<label for="exampleInputEmail1">Title</label>
				<input type="text" name="title" class="form-control"
				value="${boardVO.title}" readonly="readonly">
			</div>
			
			<div class="form-group">
				<label for="exampleInputPassword1">Content</label>
				<textarea class="form-control" name="content" row="3"
				readonly="readonly">${boardVO.content}</textarea>
			</div>
			
			<div class="form-group">
				<label for="exampleInputEmail1">Writer</label>
				<input type="text" name="writer" class="form-control"
				value="${boardVO.writer}" readonly="readonly">
			</div>
			
		</div>
		
		<div class="box-footer">
			<button type="submit" class="btn btn-warning modifyBtn">MODIFY</button>
			<button type="submit" class="btn btn-danger removeBtn">REMOVE</button>
			<button type="submit" class="btn btn-primary goListBtn">GO LIST</button>
		</div>
<%@include file="../include/footer.jsp" %>
</body>
<script>
$(document).ready(function(){
	var formObj=$("form[role='form']");
	console.log(formObj);
	$(".btn-warning").on("click",function(){
		formObj.attr("action","/board/modify");
		formObj.attr("method","get");
		formObj.submit();
	});
	$(".btn-danger").on("click",function(){
		var formObj=$("form[role='form']");
		formObj.attr("action","/board/removePage");
		formObj.submit();
	});
	
	$(".goListBtn").on("click",function(){
		formObj.attr("method","get");
		formObj.attr("action","/board/listPage");
		formObj.submit();
	});
	
	$(".modifyBtn").on("click",function(){
		formObj.attr("method","get");
		formObj.attr("action","/board/modifyPage");
		formObj.submit();
	});
	
});
</script>
</html>