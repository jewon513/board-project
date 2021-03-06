<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"  %>
<c:set var="rootPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">

<sec:csrfMetaTags/>


<%@ include file="/WEB-INF/views/inlcude/include-head.jsp"%>

<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.16/dist/summernote.min.css" rel="stylesheet">

<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.16/dist/summernote.min.js"></script>

<script src="${rootPath}/resources/js/summernote-ko-KR.js"></script>

<script>

	$(function(){
		
		$("#b_content").summernote({
			lang:'ko-KR',
			placeholder:'본문을 입력하세요',
			width:'100%',
			height:'400px',
			disableDragAndDrop : false,
			// 이미지 업로드를 가로챈다.
			// 드래그 앤 드롭 하면 files에 파일 정보가 저장되고
			// editor에는 summernote 정보가 저장됨
			callbacks : {
				
				onImageUpload : function(files, editor, isEdite){
					
					for(let i = 0 ; i < files.length; i++){
						upFile(files[i],this) // this = editor
					}
					
				}
				
			}
			
		}) // end summer
		
		
		function upFile(file,editor){
			
			var formData = new FormData()
			// 앞에는 컨트롤러에서 받을 이름이고 뒤에는 업로드할 file이다.
			// upFile 변수에 file 정보를 담아서 보내기 위한 준비를 한다.
			formData.append('upFile', file)
			
			$.ajax({
				url : "${rootPath}/image_up",
				type : "POST",
				data : formData,
				contentType : false,
				processData : false,
				enctype : "multipart/form-data",
				success : function(result){
					
					if(result == 'FAIL'){
						alert("파일 업로드 중 오류가 발생하였습니다.")
						return false;
					}
					
					result = "${rootPath}/files/" + result
					
					$(editor).summernote('editor.insertImage',result)
					
				},
				error : function(){
					alert("서버 통신 오류")
				}
				
			})
			
			
		}
		
		$(".btn-save").click(function(){
			
			let b_subject = $("#b_subject").val()
			let b_content = $("#b_content").val()
			
			if(b_subject == ""){
				alert("제목을 입력해주세요.")
				return false;
			}
			
			if(b_content == ""){
				alert("본문을 입력해주세요")
				return false;
			}
			
			
			$("#write-form").submit()
			
		})
		
		
		
		
		
	})
	
	
</script>

</head>
<body>

	<!-- Navigation -->
	<%@ include file="/WEB-INF/views/inlcude/include-navigation.jsp"%>

	<div class="container">
		<h2>글쓰기</h2>
		<hr>
		<form:form id="write-form" modelAttribute="boardVO">
			<div class="form-group">
				<form:input class="form-control border" path="b_subject" type="text" placeholder="제목"></form:input>
				<form:errors class="text-danger" path="b_subject"/>
			</div>
			<div class="form-group">
				<form:textarea class="form-control border" path="b_content"></form:textarea>
			</div>
			<div class="d-flex justify-content-end">
				<button class="btn btn-primary btn-save" type="button">저장</button>
			</div>
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
		</form:form>
	</div>

</body>
</html>