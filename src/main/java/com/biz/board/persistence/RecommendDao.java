package com.biz.board.persistence;

import org.apache.ibatis.annotations.Param;

import com.biz.board.domain.RecommendVO;

public interface RecommendDao {

	public int recommendCheck(@Param("b_id")String b_id, @Param("name")String name);

	public void insert(RecommendVO recommendVO);
	
}
