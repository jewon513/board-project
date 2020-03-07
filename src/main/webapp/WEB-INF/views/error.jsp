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
		<h2>Error Page</h2>
		<hr>		
		<h3>Failed URL : ${url}</h3>
		<h3>${exception.message}</h3>
		<hr>
		<c:forEach items="${exception.stackTrace}" var="ste">
			${ste}
		</c:forEach>
	</div>

</body>
</html>