package com.biz.board.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.biz.board.domain.BoardVO;
import com.biz.board.domain.PageVO;

public interface BoardDao {

	public int boardWrite(BoardVO boardVO);
	public int boardUpdate(BoardVO boardVO);
	public int boardDelete(long b_id);
	
	public List<BoardVO> selectAll();
	public BoardVO findById(long b_id);
	
	public int countSelect(@Param("option") String option, @Param("search") String search);
	public List<BoardVO> selectList(@Param("option") String option, @Param("search") String search, @Param("limit") int limit, @Param("offset") int offset);
	
	public int boardViewsUpdate(BoardVO boardVO);
	public void boardRecommendUpdate(BoardVO boardVO);
	public void boardReplyUpdate(BoardVO boardVO);
	
}
