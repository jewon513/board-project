drop table bbs_board;

create table bbs_board (
    b_id	NUMBER		PRIMARY KEY,
    b_write_date	DATE	NOT NULL,	
    b_update_date	DATE	NOT NULL,	
    b_subject	nVARCHAR2(255)	NOT NULL	,
    b_comment	CLOB		,
    b_writer	VARCHAR2(50)	NOT NULL	,
    b_views	NUMBER		
);

create sequence seq_bbs_board
start with 1 increment by 1;

create table bbs_comment (
    c_id	NUMBER		PRIMARY KEY,
    c_b_id	NUMBER	NOT NULL	,
    c_p_id	NUMBER	NOT NULL	,
    c_write_date	VARCHAR2(30)	NOT NULL	,
    c_writer	nVARCHAR2(50)	NOT NULL	,
    c_comment	nVARCHAR2(125)	NOT NULL	,
    c_delete_YN	CHAR(1)	DEFAULT 'N' NOT NULL  
);

create sequence seq_bbs_comment
start with 1 increment by 1;

create table bbs_user (
    userid	VARCHAR2(50)		PRIMARY KEY,
    userpw	VARCHAR2(100)	NOT NULL	,
    reg_date	VARCHAR2(100)	NOT NULL,	
    update_date	DATE		DEFAULT SYSDATE,
    enabled	DATE		DEFAULT SYSDATE
);

create table bbs_user_auth (
    userid	VARCHAR2(50)		PRIMARY KEY,
    auth	VARCHAR2(50)	NOT NULL	
);

create table bbs_file (

    f_id	NUMBER		PRIMARY KEY,
    f_b_id	NUMBER	not null	,
    f_original_filename	VARCHAR2(100)	not null	,
    f_uuid_filename	VARCHAR2(100)	not null	,
    f_path	VARCHAR2(100)	not null	
    
);

create sequence seq_bbs_file
start with 1 increment by 1;

create table bbs_recommend (
    
    r_id	NUMBER		primary key,
    r_userid	VARCHAR2(50)	not null	,
    r_b_id	NUMBER	not null	,
    r_date	DATE	default sysdate not null	
    
);

create sequence seq_bbs_recommend
start with 1 increment by 1;


ALTER TABLE bbs_board ADD CONSTRAINT FK_BOARD_USER FOREIGN KEY (b_writer) REFERENCES BBS_USER(USERID);

alter table bbs_comment drop constraint fk_comment_board;
ALTER TABLE bbs_comment ADD CONSTRAINT FK_COMMENT_BOARD FOREIGN KEY (c_b_id) REFERENCES BBS_BOARD(b_id) on delete cascade;

alter table bbs_comment drop constraint fk_comment_user;
ALTER TABLE bbs_comment ADD CONSTRAINT FK_COMMENT_USER FOREIGN KEY (c_writer) REFERENCES BBS_USER(userid);

alter table bbs_user_auth drop constraint fk_auth_user;
ALTER TABLE bbs_user_auth add constraint fk_auth_user foreign key (userid) references bbs_user(userid) on delete cascade;

alter table bbs_file drop constraint fk_file_board;
ALTER TABLE bbs_file add constraint fk_file_board FOREIGN key (f_b_id) references bbs_board(b_id) on delete cascade;
ALTER TABLE bbs_recommend add constraint fk_recommend_user foreign key (r_userid) references bbs_user(userid);
ALTER TABLE bbs_recommend add constraint fk_recommend_board foreign key (r_b_id) references bbs_board(b_id);

alter table bbs_user modify ENABLED char(1) default '1';
alter table bbs_user modify reg_date date default sysdate;


