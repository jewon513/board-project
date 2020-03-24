<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />

<script>
	$(function() {

		$(".logout-link").click(function() {

			$("#logout-form").submit()

		})

	})
</script>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark static-top mb-5">
	<div class="container">
		<a class="navbar-brand" href="#" data-toggle="modal" data-target="#copyright">Board Project</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarResponsive">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item"><a class="nav-link" href="${rootPath}/list">Board</a></li>
				<sec:authorize access="isAnonymous()">
					<li class="nav-item"><a class="nav-link" href="${rootPath}/login">Login</a></li>
					<li class="nav-item"><a class="nav-link" href="${rootPath}/join">Join</a></li>
				</sec:authorize>
				<sec:authorize access="isAuthenticated()">
					<form id="logout-form" action="${rootPath}/logout" method="POST">
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
						<li class="nav-item"><a class="nav-link logout-link" href="#">Logout</a></li>
					</form>
					<li class="nav-item"><a class="nav-link" href="${rootPath}/myInfo">My info</a></li>
				</sec:authorize>
				<sec:authorize access="hasRole('ROLE_ADMIN')">
					<li class="nav-item"><a class="nav-link" href="${rootPath}/admin/">Manage</a></li>
				</sec:authorize>
			</ul>
		</div>
	</div>
</nav>

<div class="modal fade" id="copyright">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title font-weight-bold">Board Proejct</h5>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body text-center">
				<p>Copyright &copy; 2020 by Jewon513@gmail.com
				<p>
					<a href="https://github.com/jewon513/board-project"><i class="fab fa-github-square fa-4x"></i></a>
			</div>

			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
			</div>
		</div>
	</div>
</div>

