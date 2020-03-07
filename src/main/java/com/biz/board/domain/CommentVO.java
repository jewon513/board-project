package com.biz.board.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CommentVO {

	private long c_id;
	private long c_b_id;
	private long c_p_id;
	private String c_write_date;
	private String c_writer;
	private String c_comment;
	private String c_delete_yn;
	
}
