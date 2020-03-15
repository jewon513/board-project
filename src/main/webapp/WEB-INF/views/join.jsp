<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"  %>
<c:set var="rootPath" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">

<%@ include file="/WEB-INF/views/inlcude/include-head.jsp"%>

</head>

<script>

	$(function(){
	
		$(".btn-join").click(function(){
			
			let idCheck = /^[a-z0-9_]{5,20}$/;
			
			if($("#userid").val() == ''){
				alert("아이디는 필수입니다.")
				return false;
			}
			
			if(!idCheck.test($("#userid").val())){
				alert("아이디는 영문과 숫자로 5~20자리어야 합니다.")
				return false;
			}
			
			if($("#userpw").val() == ''){
				alert("비밀번호는 필수입니다.")
				return false;
			}
			
			if($("#userpw").val().length < 8){
				alert("비밀번호는 8자리 이상이어야 합니다.")
				return false;
			}
			
			if($("#userRepw").val() == ''){
				alert("비밀번호를 한번 더 입력하세요.")
				return false;
			}
			
			if($("#userpw").val() != $("#userRepw").val()){
				alert("입력한 비밀번호를 확인해주세요.")
				return false;
			}
			
			$("form").submit()
			
		})
		
		
		
	})
	

</script>

<style type="text/css">
.login-box {
	width: 500px;
}
</style>

<body>

	<!-- Navigation -->
	<%@ include file="/WEB-INF/views/inlcude/include-navigation.jsp"%>

	<!-- container -->
	<div class="container">

		<form:form modelAttribute="memberVO">
			<div class="login-box ml-auto mr-auto mt-5 mb-5 text-center border border-secondary pt-5 pb-5 pl-3 pr-3">
				<h2 class="m-3">Join</h2>
				<div class="form-group">
					<form:input class="form-control border border-secondary" type="text" path="userid" placeholder="ID"></form:input>
					<form:errors class="text-danger" path="userid"/>
				</div>
				<div class="form-group">
					<form:input class="form-control border border-secondary" type="password" path="userpw" placeholder="Password"></form:input>
					<form:errors class="text-danger"  path="userpw"/>
				</div>
				<div class="form-group">
					<form:input class="form-control border border-secondary" type="password" path="userRepw" placeholder="Re Password"></form:input>
					<form:errors class="text-danger"  path="userRepw"/>
				</div>
				<div class="d-flex justify-content-center">
					<button class="btn btn-primary btn-join" type="button">Welcome</button>
				</div>
			</div>
			
		</form:form>

	</div>

</body>
</html>