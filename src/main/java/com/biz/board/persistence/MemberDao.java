package com.biz.board.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.biz.board.domain.MemberVO;

public interface MemberDao {

	
	public MemberVO read(String userid);
	public int insert(MemberVO memberVO);
	public void updateUserUpdateDate(String userid);
	public List<MemberVO> selectAll();
	public List<MemberVO> selectList(@Param("offset") int offset, @Param("limit") int limit, @Param("search") String search, @Param("option") String option, @Param("sort") String sort);
	public void updateUserEnabled(MemberVO memberVO);
	public int getTotalCount(String search);
	public void deleteMember(String userid);
	
	
}
