<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="rootPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">

<%@ include file="/WEB-INF/views/inlcude/include-head.jsp"%>

<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.16/dist/summernote.min.css" rel="stylesheet">

<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.16/dist/summernote.min.js"></script>

<script src="${rootPath}/resources/js/summernote-ko-KR.js"></script>

<script>

	$(function(){
		
		$("#b_content").summernote({
			lang:'ko-KR',
			placeholder:'본문을 입력하세요',
			width:'100%',
			height:'400px',
			disableDragAndDrop : false,
			// 이미지 업로드를 가로챈다.
			// 드래그 앤 드롭 하면 files에 파일 정보가 저장되고
			// editor에는 summernote 정보가 저장됨
			
		})
		
		
		
	})
	
	
</script>

</head>
<body>

	<!-- Navigation -->
	<%@ include file="/WEB-INF/views/inlcude/include-navigation.jsp"%>

	<div class="container">
		<h2>글쓰기</h2>
		<hr>
		<form method="POST">
			<div class="form-group">
				<input class="form-control border" id="b_subject" name="b_subject" type="text" placeholder="제목">
			</div>
			<div class="form-group">
				<textarea class="form-control border" id="b_content"  name="b_content" rows="5"></textarea>
			</div>
			<div class="d-flex justify-content-end">
				<button class="btn btn-primary btn-save">저장</button>
			</div>
			
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
			
		</form>
	</div>

</body>
</html>