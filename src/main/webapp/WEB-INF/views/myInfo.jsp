<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<c:set var="rootPath" value="${pageContext.request.contextPath}"/>
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
	
		<h2>회원정보</h2>
		<hr/>
		<p>사용자 ID : <sec:authentication property="principal.username"/></p>
		<p>회원가입 날짜 : <sec:authentication property="principal.memberVO.reg_date"/></p>
		<p>마지막 로그인 날짜 : <sec:authentication property="principal.memberVO.update_date"/></p>
		<hr/>
		<div class="d-flex justify-content-end">
			<button class="btn btn-danger btn-delete" data-toggle="modal" data-target="#delete-user">회원탈퇴</button>
		</div>
		
		
		
		<!-- The Modal -->
		<div class="modal fade" id="delete-user">
			<div class="modal-dialog">
				<div class="modal-content">

					<!-- Modal Header -->
					<div class="modal-header">
						<h4 class="modal-title text-danger">정말 탈퇴를 하시겠습니까?</h4>
						<button type="button" class="close" data-dismiss="modal">&times;</button>
					</div>

					<!-- Modal body -->
					<div class="modal-body">
						
						<input class="form-control border" id="inputUserDelete" placeholder="당신의 아이디를 입력하고 탈퇴버튼을 눌러주세요...">						
						
					</div>

					<!-- Modal footer -->
					<div class="modal-footer">
						<button type="button" class="btn btn-danger btn-delete-user" >탈퇴</button>
					</div>

				</div>
			</div>
		</div>

	</div>

</body>

<script type="text/javascript">	
	$(function(){
		
		$(".btn-delete-user").click(function(){
		
			deleteUser()
			
		})
		
		$("#inputUserDelete").keydown(function(key){
			if(key.keyCode == 13){
				deleteUser()
			}
		})
		
		function deleteUser(){
			
			let userid = $("#inputUserDelete").val()
			
			$.ajax({
			
				url : "${rootPath}/deleteAccount",
				type : "POST",
				data : {userid : userid},
				success : function(result){
					
					if(result =="OK"){
						alert("그동안 이용해주셔서 감사합니다.")
						logout()
					}else{
						alert(result)
					}
					
				},
				error : function(error){
					alert("서버 통신 에러")
				}
				
			})
			
		}
		
		function logout () {
			
			$.ajax({
				
				url : "${rootPath}/logout",
				type : "POST",
				success : function(result){
					location.reload()
				},
				error : function(error){
					alert("서버 통신 에러")
				}
				
			})
			
		}
		
		
		
	})
	
</script>

</html>