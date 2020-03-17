package com.biz.board.domain;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class BoardVO {

	private long b_id;
	private String b_write_date;
	private String b_update_date;
	
	@NotBlank(message = "제목을 입력해주세요.")
	private String b_subject;

	private String b_content;
	
	private String b_writer;
	private int b_views;
	
	private int b_recommend;
	private int b_reply_count;

	
	
}
