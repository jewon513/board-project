package com.biz.board.persistence;

import java.util.List;

import com.biz.board.domain.CommentVO;

public interface CommentDao {

	public int commentWrite(CommentVO commentVO);
	public int commentUpdate(CommentVO commentVO);
	
	public int commentDelete(long c_id);
	public int commentDeleteUpdate(CommentVO commentVO);
	
	public List<CommentVO> selectAll();
	public CommentVO findByCId(long c_id);
	public List<CommentVO> findByBId(long c_b_id);
	
	// 대댓글이 있는지 없는지 체크하기 위해 사용
	public int countReplyById(long c_id);
	
}
