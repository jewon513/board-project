# 게시판 프로젝트

## 개요
* 기존에 연습했던 게시판 구현과 spring security를 적용한 project

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
