<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"  prefix="sec"%>


<c:forEach items="${commentList}" var="vo">
	<div class="comment-item m-2 
		<c:if test="${vo.c_p_id != 0}">ml-5</c:if>
	">

		<div class="d-flex align-items-center">
			<h5 class="mr-2">${vo.c_writer}</h5>
			<small class="mr-2">${vo.c_write_date}</small>
			<c:if test="${vo.c_writer == loginUserId || loginUserAuth}">
				<small class="text-danger mr-2 comment-item-delete" data-id="${vo.c_id}">삭제</small>
			</c:if>
			<c:if test="${vo.c_p_id eq 0}">
				<small class="text-info comment-item-reply" data-toggle="collapse" data-target="#reply-${vo.c_id}">대댓글쓰기</small>
			</c:if>
		</div>
		<div>
			${vo.c_comment}
		</div>
		<c:if test="${vo.c_p_id eq 0 }">
			<div id="reply-${vo.c_id}" class="p-3 collapse">
				<form class="comment-reply-form">
				
					<input type="hidden" name="c_b_id" value="${vo.c_b_id}">
					<input type="hidden" name="c_p_id" value="${vo.c_id}">
				
					<textarea name="c_comment" class="form-control" rows="3" cols="" placeholder="댓글을 입력하세요"></textarea>
					<div class="d-flex justify-content-end">
						<button class="mt-2 btn btn-primary btn-sm btn-comment-write" type="button">저장</button>
					</div>
				</form>
			</div>
		</c:if>

	</div>
</c:forEach>