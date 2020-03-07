package com.biz.board.domain;

import java.util.List;

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
public class MemberVO {
	
	private String userid;
	private String userpw;
	private String reg_date;
	private String update_date;
	private String enabled;
	
	private List<AuthVO> authList;

}
