<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="rootPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">

<%@ include file="/WEB-INF/views/inlcude/include-head.jsp"%>


<script>

	$(function(){
		$(".btn-write").click(function(){
			document.location.href = '${rootPath}/write'
		})
		
		$(".board-item").click(function(){
			
			let id = $(this).attr("data-id")
			
			document.location.href = '${rootPath}/detail?number=' + id
			
		})
	})

</script>

<style type="text/css">

	.board-item:hover{
		background-color: #ccc;
		cursor: pointer;
	}

</style>

</head>
<body>

	<!-- Navigation -->
	<%@ include file="/WEB-INF/views/inlcude/include-navigation.jsp"%>

	<div class="container">
		<h2>게시판</h2>
		<hr>
		
		<c:forEach items="${boardList}" var="vo">
		 	<div class="row m-1 board-item" data-id="${vo.b_id}">
		 		<div class="col-sm-9">
		 			<small class="text-info">#${vo.b_id}</small>
		 			<div>
		 				${vo.b_subject}
		 			</div>
		 		</div>
		 		<div class="col-sm-3">
		 			<div>
		 				${vo.b_writer}
		 			</div>
		 			<small class="text-success">${vo.b_write_date}</small>
		 		</div>
			</div>
		</c:forEach>
		
		<hr>
		<div class="d-flex justify-content-end">
			<button class="btn btn-primary btn-write">글쓰기</button>
		</div>
	</div>

</body>
</html>