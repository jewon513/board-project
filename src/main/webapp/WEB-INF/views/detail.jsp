<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="rootPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">

<%@ include file="/WEB-INF/views/inlcude/include-head.jsp"%>

</head>
<body>

	<!-- Navigation -->
	<%@ include file="/WEB-INF/views/inlcude/include-navigation.jsp"%>

	<div class="container">
		<h2>${boardVO.b_subject}</h2>
		<small class="mr-2">#${boardVO.b_id}</small>
		<small>작성자: </small><small class="text-info mr-2">${boardVO.b_writer}</small>
		<small>등록일자: </small><small class="text-success mr-2">${boardVO.b_write_date}</small>
		<small class=""><i class="far fa-eye"></i>${boardVO.b_views}</small>
		<hr>
		<div class="b_content mt-3 mb-3">
			${boardVO.b_content}
		</div>
		<hr>
		<div class="d-flex justify-content-end">
			<c:if test="${userCheck}"> 
				<button class="btn btn-info mr-2">수정</button>
				<button class="btn btn-danger mr-2">삭제</button>
			</c:if>
			<button class="btn btn-primary">목록으로</button>
		</div>
	</div>

</body>
</html>