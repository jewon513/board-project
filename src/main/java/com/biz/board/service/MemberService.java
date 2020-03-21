package com.biz.board.service;

import java.util.List;

import com.biz.board.domain.MemberVO;
import com.biz.board.domain.PageVO;

public interface MemberService {

	public String join(MemberVO memberVO);
	
	
	public List<MemberVO> selectAll();
	public List<MemberVO> selectList(PageVO pageVO, String search, String option, String solt);
	public MemberVO findById(String userId);
	public int getTotalCount(String search);
	
	
	public String userEnabledUpdate(String userId);
	public boolean duplicateCheck(String userid);


	


	
	
}
