<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"  prefix="sec"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>

<sec:csrfMetaTags/>
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
						<th>권한</th>
						<th scope="col">활성화 여부</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${memberList}" var="vo">
						<tr class="userRow" data-id="${vo.userid}">
							<td>${vo.userid}</td>
							<td>${vo.reg_date}</td>
							<td>${vo.update_date}</td>
							<td>
								<c:forEach items="${vo.authList}" var="auth">
									<c:if test="${auth.auth == 'ROLE_ADMIN'}">관리자</c:if>
									<c:if test="${auth.auth == 'ROLE_MEMBER'}">회원</c:if>
								</c:forEach>
							</td>
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
		
		$(".userRow").click(function(){
			
			
			if(confirm("계정 상태를 변경하시겠습니까?")){
				
				var user = $(this).attr("data-id")
				
				$.ajax({
				
				url : '${rootPath}/admin/enabled',
				data : { userid : user},
				type : 'POST',
				success : function(result){
					
					alert(result)
					location.reload()
					
				},
				error : function(error){
					
					alert("서버 통신 오류")
					
				}
				
			})
				
			}
			
		})
		
	})
	
</script>

</html>