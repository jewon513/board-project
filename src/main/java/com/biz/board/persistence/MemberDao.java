package com.biz.board.persistence;

import java.util.List;

import com.biz.board.domain.MemberVO;

public interface MemberDao {

	
	public MemberVO read(String userid);
	public int insert(MemberVO memberVO);
	public void updateUserUpdateDate(String userid);
	public List<MemberVO> selectAll();
	
	
}
