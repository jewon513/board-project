package com.biz.board.service;

import java.util.List;

import com.biz.board.domain.BoardVO;
import com.biz.board.domain.PageVO;

public interface BoardService {

	public String boardWrite(BoardVO boardVO);
	public String boardUpdate(BoardVO boardVO);
	public String boardDelete(long b_id);
	
	public List<BoardVO> selectAll();
	public BoardVO findById(long b_id);
	
	public List<BoardVO> selectList(PageVO pageVO);
	public int countAll();
	
	
}
