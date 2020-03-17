<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
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
		<h2>관리자 페이지</h2>
		<hr>
		<div class="table-responsive">
			<table class="table table-hover table-sm">
				<thead class="thead-dark">
					<tr>
						<th scope="col">UserID</th>
						<th scope="col">가입일자</th>
						<th scope="col">최근로그인 일자</th>
						<th scope="col">활성화 여부</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${memberList}" var="vo">
						<tr>
							<td>${vo.userid}</td>
							<td>${vo.reg_date}</td>
							<td>${vo.update_date}</td>
							<td>
								<c:if test="${vo.enabled == 1}">사용중</c:if>
								<c:if test="${vo.enabled == 0}">비사용중</c:if>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>

	</div>

</body>

<script>
	
	$(function(){
		
		$("td").click(function(){
			
			
			
			if(confirm("이 계정을 비활성화 시키시겠습니까?")){
				
			}
			
		})
		
	})
	
</script>

</html>