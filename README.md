# 게시판 프로젝트

## 개요
* 기존에 연습했던 게시판 구현과 spring security를 적용한 project
* 매일 조금씩 기록하면서 완성할 계획

## 목적
* 그동안 수업때 공부한 내용을 바탕으로 게시판을 직접 구현하고 경험해보는 것

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
https://www.erdcloud.com/p/xoheLd7aARu7KrfND

## 개발 일기

#### 2020-03-06
* 테이블 모델링 구현

#### 2020-03-07
* security encoder 사용해서 암호화 하여 유저 pw 저장	
* 유저 회원가입시 트랜잭션 설정 (error page 새로 구성)
* 트랜잭션 설정시 servlet-context에 tx 설정 ( 안하면 Annotation 처리가 먹통이 됨.. 이유를 찾아봐야함 )   
spring에서 transaction은 aop를 사용하고, 인터페이스를 기반으로 클래스를 구현하지 않으면 transaction이 작동하지 않음.   
인터페이스를 사용하지 않을 경우 annotation-driven에서 proxy-target-class의 설정을 true로 변경하고 추가 dependency를 설정해야 한다.
* srping security CustomUser 구현 (userid, reg_date, update_date 를 가져와서 회원정보 페이지에서 보여줌)
* 로그인 실패시 로그인 실패 메세지 출력
* 아직 회원가입시 유효성 검사를 하지 않았음 (프로젝트 마지막에 작업을 할지 생각중)
* 이후 해야할 일   
게시판 작업 ( summernote 적용해서 이미지 업로드까지 )   
파일 업로드 작업 

#### 2020-03-08
* 파일 업로드 하기
* commons-io, commons-fileupload depedency 사용
* fileup-context에 CommonsMultipartResolver bean 등록
* servlet-context에 저장할 파일 경로 지정 (로컬에 저장)	
* spring security 사용 시 ajax로 요청하기 전에 setRequestHeader 값에 토큰을 설정해주어야 함   

    <sec:csrfMetaTags/> 
    
    <meta name="_csrf_parameter" content="_csrf" />
    <meta name="_csrf_header" content="X-CSRF-TOKEN" />
    <meta name="_csrf" content="c8ab0388-08f6-41a7-bf77-c646b6fc5f54" /> 
    

  
  이후 스크립트에서 아래와 같이 코드 작성 하여 ajax를 요청하기전 header값 세팅 ( 참조 : https://okky.kr/article/325838 ) <br>
  
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$.ajaxSetup({
           beforeSend: function(xhr) {
        	xhr.setRequestHeader(header, token);
          }
    });
 
 
#### 2020-03-11
* 게시글 삭제시 파일 삭제는 미구현

#### 2020-03-14
* 게시글의 댓글은 ajax로 구현, ADMIN은 사용자의 모든 댓글을 다 삭제 수정 할 수 있고, 이외 사용자는 본인의 댓글만 수정 삭제 가능
* 대댓글은 2단으로 구성, 대댓글이 달려있는 댓글의 경우 삭제되었다는 내용만 보여줌. 대댓글이 없는 경우 댓글 자체를 삭제
* 페이지네이션 구현

#### 2020-03-15
* 검색 기능 구현 (동적 쿼리 사용)
* 회원가입, 로그인, 글쓰기, 댓글쓰기 유효성 검사 구현 (front단에서는 jquery를 이용하고, server에서는 validation의 구현체인 hibernate-vailidation을 사용)

#### 2020-03-16
* 해야 할 일    
로그인 실패 핸들러 구현 (로그인 실패시 발생하는 오류 부분 사용자에게 보여주기)    
게시판 추천 기능    
회원관리 페이지 (단순하게 계정 활성화, 비활성화, 탈퇴 부분)    
MYINFO에서 회원탈퇴 기능
에러페이지 만들기    

* 우선순위     
게시판 추천 기능 - 회원관리 페이지 - MYINFO에서 탈퇴기능 - 로그인 실패 핸들러 - 에러페이지

* 추천기능 완성    
먼저 bbs_recommend table에 추천한 기록이 남아있는지 체크 (있으면 추천 불가)    
기록이 없다면 bbs_recommend table에 insert를 수행    
이후 bbs_board에 b_recommend column을 update    

#### 2020-03-17
* 해야 할 일    
회원관리 페이지 - 탈퇴기능 - 로그인 실패 핸들러 - 에러 페이지

* 관리페이지 조회까지 완성
* 게시판 리스트에서 댓글 개수와 추천 개수 보이도록 수정


#### 2020-03-19
* 관리자 페이지에서 계정을 활성화 시키거나 비활성화 시킬수 있음. 관리자계정은 계정 상태 변경 불가능
* 이후 회원 검색과 활성화 비활성화 정렬 추가

#### 2020-03-20
* mybatis에서 #{} 과 ${} 차이점 https://logical-code.tistory.com/25
* ordery by 절에서 ${}를 사용하지 않으면 정렬이 되질 않음. 
* 관리자 페이지에서 칼럼명을 클릭했을때 그 칼럼 순으로 정렬되도록 구현 (이후 토글형식으로 asc, desc 변경 가능하도록 하기)
* 회원 검색 (아이디를 기준으로 like 검색)
