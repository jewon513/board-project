<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags"  prefix="sec" %>
<c:set var="rootPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">

<sec:csrfMetaTags/>

<%@ include file="/WEB-INF/views/inlcude/include-head.jsp"%>

<style>

	.comment-item-delete:hover{
		cursor: pointer;
	}
	
	.comment-item-reply:hover{
		cursor: pointer;
	}
	
</style>

</head>
<body>

	<!-- Navigation -->
	<%@ include file="/WEB-INF/views/inlcude/include-navigation.jsp"%>


	<!-- 글 본문 -->
	<div class="container mb-5">
		<h2>${boardVO.b_subject}</h2>
		<small class="mr-2">#${boardVO.b_id}</small>
		<small>작성자: </small><small class="text-info mr-2">${boardVO.b_writer}</small>
		<small>등록일자: </small><small class="text-success mr-2">${boardVO.b_write_date}</small>
		<c:if test="${boardVO.b_write_date ne boardVO.b_update_date}">
			<small>수정일자: </small><small class="text-success mr-2">${boardVO.b_update_date}</small>
		</c:if>
		<small class=""><i class="far fa-eye"></i>${boardVO.b_views}</small>
		<hr>
		<div class="b_content mt-3 mb-3">
			${boardVO.b_content}
		</div>
		<hr>
		<div class="d-flex justify-content-end">
			<sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_MEMBER')">
				<c:if test="${userCheck}"> 
					<button class="btn btn-info btn-update mr-2">수정</button>
					<button class="btn btn-danger btn-delete mr-2">삭제</button>
				</c:if>
			</sec:authorize>
			<button class="btn btn-primary btn-list">목록으로</button>
		</div>
	</div>
	
	<!-- comment list -->
	<div class="container mb-5">
		<h2>comment list</h2>
		<hr>
		<div class="comment-list">
			
			<%@ include file ="/WEB-INF/views/inlcude/include-comment-list.jsp" %>
		
		</div>
	</div>
	
	<!--  comment write -->
	<div class="container mb-5">
		<h2>comment write</h2>
		<hr>
		<form id="comment-form">
		
			<input type="hidden" name="c_b_id" value="${boardVO.b_id}">
		
			<div class="form-group">
				<textarea name="c_comment" class="form-control comment-content" rows="5" placeholder="댓글을 입력하세요.."></textarea>
			</div>
			<div class="d-flex justify-content-end">
				<button class="btn btn-primary btn-comment-write" type="button">저장</button>
			</div>	
		</form>
	</div>

</body>

<script>

	$(function(){
		
		getCommentList()
		
	
		$(".btn-update").click(function(){
			
			document.location.href = "${rootPath}/update?b_id=${boardVO.b_id}"
			
		})// end btn-update
		
		$(".btn-list").click(function(){
			
			document.location.href = "${rootPath}/list"
			
		})// end btn-list
		
		$(".btn-delete").click(function(){
			
			let id = '${boardVO.b_id}'
			
			if(confirm("삭제 하시겠습니까?")){
				$.ajax({
					
					url : '${rootPath}/delete',
					type : 'POST',
					data : {id:id},
					success : function(result){
						
						alert(result)
						
						document.location.href ="${rootPath}/list"
						
					},
					error : function(error){
						
						alert("서버통신 오류")
						
					}
					
				})
			}
			
			
		})// end btn-delete
		
		
		$(document).on("click",".btn-comment-write",function(){
			
			let data = $(this).closest("form").serialize()
			
			$.ajax({
				
				url : '${rootPath}/comment/write',
				data : data,
				type : 'POST',
				success : function(result){
					
					alert(result)
					getCommentList()
					
				},
				error : function(error){
					
					alert("서버 통신 오류")
					
				}
				
			})
			
		})// end btn-comment-write
		
		
		$(document).on("click",".comment-item-delete",function(){
		
			let c_id = $(this).attr("data-id")
			
			$.ajax({
				
				url : '${rootPath}/comment/delete',
				data : {c_id : c_id },
				type : 'POST',
				success : function(result){
					
					alert(result)
					getCommentList()
					
				},
				error : function(error){
					alert (" 댓글을 삭제하는 도중에 문제가 발생하였습니다.")
				}
				
			})
	
			
			
		})// end comment-item-delete
		
		
		// 댓글 리스트를 가져오는 ajax 요청을 함수로 선언
		function getCommentList() {
			
			$.ajax({
				
				url : '${rootPath}/comment/list',
				data : { b_id : '${boardVO.b_id}'},
				success : function(result){
					
					$(".comment-list").html(result)
					
				},
				error : function(error){
					
					alert("댓글 리스트를 가져오는데 문제가 발생하였습니다.")
					
				}
				
			})
			
		}// end function getCommentList
		
		
	})
	
	
</script>

</html>