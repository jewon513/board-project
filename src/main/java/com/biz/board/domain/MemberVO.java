package com.biz.board.domain;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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
	
	@NotBlank(message = "아이디는 필수입니다.")
	@Size(min = 5 , max = 20, message = "아이디는 5~20자 사이어야 합니다.")
	@Pattern(regexp = "(^[a-zA-Z0-9]*$)", message = "아이디는 영문과 숫자만 가능합니다.")
	private String userid;
	
	@Size(min = 8, message = "비밀번호는 8자리 이상이어야 합니다.")
	@NotBlank(message = "비밀번호는 필수입니다.")
	private String userpw;
	
	@NotBlank(message = "비밀번호를 한번 더 입력하세요.")
	private String userRepw;
	
	
	private String reg_date;
	private String update_date;
	private String enabled;
	
	private List<AuthVO> authList;

}
