package com.biz.board.service;

import java.util.List;

import com.biz.board.domain.CommentVO;

public interface CommentService {

	public String commentWrite(CommentVO commentVO);
	public String commentUpdate(CommentVO commentVO);
	public String commentDelete(long c_id);
	
	public List<CommentVO> selectAll();
	public CommentVO findByCId(long c_id);
	public List<CommentVO> findByBId(long b_id);
	
}
