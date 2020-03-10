# 게시판 프로젝트

## 개요
* 기존에 연습했던 게시판 구현과 spring security를 적용한 project
* 매일 조금씩 기록하면서 완성할 계획

## 환경
* Spring
* Spring security
* oracle 11 xe
* mybatis

## 기능
* 회원가입 / 로그인
* 게시판 글 작성 (이미지 업로드 가능)
* 댓글 계층형으로 작성 가능
* 게시글 추천 기능

## 화면
* bootstrap4
* jQuery

## DB 모델 구성도
* 테이블 모델 <br>
![table_modeling](table_modeling.png)

* 사용 시퀸스 <br>
![sequence](sequence.png)

## 개발 일기

### 2020-03-06
* 테이블 모델링 구현

### 2020-03-07
* security encoder 사용해서 암호화 하여 유저 pw 저장
* 유저 회원가입시 트랜잭션 설정 (error page 새로 구성)
* 트랜잭션 설정시 servlet-context에 tx 설정 ( 안하면 Annotation 처리가 먹통이 됨.. 이유를 찾아봐야함 )<br>
  spring에서 transaction은 aop를 사용하고, 인터페이스를 기반으로 클래스를 구현하지 않으면 transaction이 작동하지 않음.<br>
  인터페이스를 사용하지 않을 경우 annotation-driven에서 proxy-target-class의 설정을 true로 변경하고 추가 dependency를 설정해야 한다.
* srping security CustomUser 구현 (userid, reg_date, update_date 를 가져와서 회원정보 페이지에서 보여줌)
* 로그인 실패시 로그인 실패 메세지 출력
* 아직 회원가입시 유효성 검사를 하지 않았음 (프로젝트 마지막에 작업을 할지 생각중)
* 이후 해야할 일<br>
	게시판 작업 ( summernote 적용해서 이미지 업로드까지 )
	파일 업로드 작업 

### 2020-03-08
* 파일 업로드 하기
* commons-io, commons-fileupload depedency 사용
* fileup-context에 CommonsMultipartResolver bean 등록
* servlet-context에 저장할 파일 경로 지정 (로컬에 저장)	
* spring security 사용 시 ajax로 요청하기 전에 setRequestHeader 값에 토큰을 설정해주어야 함 <br>

    <sec:csrfMetaTags/> 사용 시 
    
    <meta name="_csrf_parameter" content="_csrf" />
    <meta name="_csrf_header" content="X-CSRF-TOKEN" />
    <meta name="_csrf" content="c8ab0388-08f6-41a7-bf77-c646b6fc5f54" /> 
    
         처럼 meta에 설정 된다 

  
  이후 스크립트에서 아래와 같이 코드 작성 하여 ajax를 요청하기전 header값 세팅 ( 참조 : https://okky.kr/article/325838 ) <br>
  
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");

		$.ajaxSetup({

	           beforeSend: function(xhr) {

	        	xhr.setRequestHeader(header, token);

	          }

	    });
 
 
