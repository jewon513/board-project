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
	
		<h2>회원정보</h2>
		<hr/>
		<p>사용자 ID : <sec:authentication property="principal.username"/></p>
		<p>회원가입 날짜 : <sec:authentication property="principal.memberVO.reg_date"/></p>
		<p>마지막 로그인 날짜 : <sec:authentication property="principal.memberVO.update_date"/></p>
				
	</div>

</body>
</html>