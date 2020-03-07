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
public class RecommendVO {

	private long r_id;
	private String r_userid; 
	private long r_b_id;
	private String r_date;
	
}
