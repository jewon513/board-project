package com.biz.board.domain;

import java.util.List;

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
	private String b_subject;
	private String b_content;
	private String b_writer;
	private int b_views;
	
	private List<String> f_path;
	
	
}
