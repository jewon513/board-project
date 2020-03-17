package com.biz.board.service;

import java.util.List;

import com.biz.board.domain.MemberVO;

public interface MemberService {

	public String join(MemberVO memberVO);
	
	
	public List<MemberVO> selectAll();
	public MemberVO findById(String userId);
	
	public String userEnabledUpdate(String userId);
	
}
