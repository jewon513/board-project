package com.biz.board.persistence;

import java.util.List;

import com.biz.board.domain.BoardVO;

public interface BoardDao {

	public int boardWrite(BoardVO boardVO);
	public int boardUpdate(BoardVO baordVO);
	public int boardDelete(long b_id);
	
	public List<BoardVO> selectAll();
	public BoardVO findById(long b_id);
	
}
