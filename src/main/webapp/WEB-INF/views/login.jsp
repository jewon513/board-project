<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<%@ include file="/WEB-INF/views/inlcude/include-head.jsp"%>

</head>

<script type="text/javascript">
	$(function() {

		$(".btn-login").click(function() {
			login()
		})
		
		$("input").keydown(function(key){
			if(key.keyCode == 13){
				login()
			}
		})
		
		function login (){
			
			if ($("#username").val() == "") {
				alert("아이디를 입력하세요.")
				return false;
			}

			if ($("#password").val() == "") {
				alert("비밀번호를 입력하세요.")
				return false;
			}

			$("form").submit()
			
		}

	})
</script>

<style type="text/css">
.login-box {
	width: 500px;
}

@media ( min-width : 300px) {
	.login-box {
		max-width: 95%;
	}
}

</style>


<body>

	<!-- Navigation -->
	<%@ include file="/WEB-INF/views/inlcude/include-navigation.jsp"%>

	<!-- container -->
	<div class="container">

		<form action="${rootPath}/login" method="POST">
			<div class="login-box ml-auto mr-auto mt-5 mb-5 text-center border border-secondary pt-5 pb-5 pl-3 pr-3">
				<h2 class="m-3">Login</h2>
				<div class="form-group">
					<input class="form-control border border-secondary" type="text" placeholder="ID" name="username" id="username">
				</div>
				<div class="form-group">
					<input class="form-control border border-secondary" type="password" placeholder="Password" name="password" id="password">
				</div>
				<div class="form-group text-danger">
					<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
						<small>${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</small>
						<c:remove var="SPRING_SECURITY_LAST_EXCEPTION" scope="session" />
					</c:if>
				</div>
				<div class="d-flex justify-content-center">
					<button class="btn btn-primary btn-login" type="button">Login</button>
				</div>

				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">

			</div>
		</form>
	</div>

</body>
</html>