<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">

<%@ include file="/WEB-INF/views/inlcude/include-head.jsp"%>


<script>
	$(function() {
		$(".btn-write").click(function() {
			document.location.href = '${rootPath}/write'
		})

		$(".board-item").click(function() {

			let id = $(this).attr("data-id")

			document.location.href = '${rootPath}/detail?number=' + id

		})
	})
</script>

<style type="text/css">
.board-item:hover {
	background-color: #ccc;
	cursor: pointer;
}
</style>

</head>
<body>

	<!-- Navigation -->
	<%@ include file="/WEB-INF/views/inlcude/include-navigation.jsp"%>

	<div class="container">
		<h2>게시판</h2>
		<hr>

		<c:forEach items="${boardList}" var="vo">
			<div class="row m-1 board-item" data-id="${vo.b_id}">
				<div class="col-sm-9">
					<small class="text-info mr-2">#${vo.b_id}</small>
					<div>${vo.b_subject}</div>
				</div>
				<div class="col-sm-3">
					<div>${vo.b_writer}</div>
					<small class="text-success">${vo.b_write_date}</small>
				</div>
			</div>
		</c:forEach>

		<hr>

		<!-- pagination -->
		<div class="d-flex justify-content-center">
			<div>
				<ul class="pagination">
					<li class="page-item"><a class="page-link" href="${rootPath}/list?option=${option}&search=${search}&currentPageNo=${page.prePageNo}">&laquo;</a></li>
					<c:forEach begin="${page.startPageNo}" end="${page.endPageNo}" var="pageNo">
						<li class="page-item 
							<c:if test="${pageNo == page.currentPageNo}">active</c:if>
						"><a class="page-link" href="${rootPath}/list?option=${option}&search=${search}&currentPageNo=${pageNo}">${pageNo}</a></li>
					</c:forEach>
					<li class="page-item"><a class="page-link" href="${rootPath}/list?option=${option}&search=${search}&currentPageNo=${page.nextPageNo}">&raquo;</a></li>
				</ul>
			</div>
		</div>
		
		<!-- search bar -->
		<div class="d-flex justify-content-center mb-3">
			 <form class="form-inline justify-content-center">
			 	<select class="form-control col-5 mr-2 border" name="option">
			 		<option class="form-control" value="subject">제목</option>
			 		<option class="form-control" value="writer">작성자</option>
			 	</select>
				<input class="form-control col-6 border" placeholder="search" name="search">
			</form>
		</div>

		<div class="d-flex justify-content-end">
			<button class="btn btn-primary btn-write">글쓰기</button>
		</div>
	</div>

</body>
</html>