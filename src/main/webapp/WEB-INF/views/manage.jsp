<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>

<sec:csrfMetaTags />
<meta charset="UTF-8">

<%@ include file="/WEB-INF/views/inlcude/include-head.jsp"%>

<style>

	tr{
		cursor: pointer;
	}

</style>

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
						<th width="20%" id="userid" data-id="userid">UserID</th>
						<th width="30%" id="reg_date" data-id="reg_date">가입일자</th>
						<th width="30%" id="update_date" data-id="update_date">최근로그인 일자</th>
						<th width="20%" id="enabled" data-id="enabled">상태</th>
					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${memberList eq null}">
							<tr>
								<td colspan="4" class="text-center">
									검색 결과가 없습니다.
								</td>
							<tr>
						</c:when>
						<c:otherwise>
							<c:forEach items="${memberList}" var="vo">
								<tr class="userRow" data-id="${vo.userid}">
									<td>${vo.userid}</td>
									<td>${vo.reg_date}</td>
									<td>${vo.update_date}</td>
									<td>
										<c:if test="${vo.enabled == 1}">사용</c:if>
										<c:if test="${vo.enabled == 0}">중지</c:if>
									</td>
								</tr>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</div>

		<!-- pagination -->
		<div class="d-flex justify-content-center">
			<div>
				<ul class="pagination">
					<li class="page-item"><a class="page-link" href="${rootPath}/admin/?search=${search}&option=${option}&sort=${sort}&currentPageNo=${page.prePageNo}">&laquo;</a></li>
					<c:forEach begin="${page.startPageNo}" end="${page.endPageNo}" var="pageNo">
						<li class="page-item 
							<c:if test="${pageNo == page.currentPageNo}">active</c:if>
						"><a class="page-link" href="${rootPath}/admin/?search=${search}&option=${option}&sort=${sort}&currentPageNo=${pageNo}">${pageNo}</a></li>
					</c:forEach>
					<li class="page-item"><a class="page-link" href="${rootPath}/admin/?&search=${search}&option=${option}&sort=${sort}&currentPageNo=${page.nextPageNo}">&raquo;</a></li>
				</ul>
			</div>
		</div>
		
		<div class="d-flex justify-content-center">
			<form class="form-group">
				<input class="form-control border" placeholder="ID로 검색..." name="search" id="search">
			</form>
		</div>

	</div>

</body>

<script>
	$(function() {
		
		$("#${option}").addClass("text-info")
		if("asc" == '${sort}'){
			$("#${option}").append("<i class='fas fa-angle-up ml-2'></i>")
		}else{
			$("#${option}").append("<i class='fas fa-angle-down ml-2'></i>")
		}
		
		$("#search").keypress(function(key){
			
			if(key.keyCode==13){
				
				let keyword = $("#search").val()
				
				if($.trim(keyword) == ""){
					
					return false;
				}
				
			}
			
		})
		

		$(".userRow").click(function() {

			if (confirm("계정 상태를 변경하시겠습니까?")) {

				var user = $(this).attr("data-id")

				$.ajax({

					url : '${rootPath}/admin/enabled',
					data : {
						userid : user
					},
					type : 'POST',
					success : function(result) {

						alert(result)
						location.reload()

					},
					error : function(error) {

						alert("서버 통신 오류")

					}

				})

			}

		})
		
		$("th").click(function(){
			
			let option = $(this).attr("data-id")
			let sort = "${sort}"
			
			if(option != "${option}"){
				sort = "asc"
			}
			
			if(option == "${option}"){
				if(sort == "asc"){
					sort = "desc"
				}else{
					sort = "asc"
				}
			}
			
			location.href="${rootPath}/admin/?search=${search}&currentPageNo=${currentPageNo}&option="+option+"&sort="+sort
			
		})

	})
</script>

</html>