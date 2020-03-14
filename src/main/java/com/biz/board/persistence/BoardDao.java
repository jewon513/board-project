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
	public int countAll();
	public List<BoardVO> selectList(PageVO pageVO);
	
}
