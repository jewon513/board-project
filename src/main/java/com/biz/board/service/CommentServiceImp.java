package com.biz.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biz.board.domain.CommentVO;
import com.biz.board.persistence.CommentDao;

@Service
public class CommentServiceImp implements CommentService{

	@Autowired
	CommentDao commentDao;
	
	@Override
	public String commentWrite(CommentVO commentVO) {
		// TODO Auto-generated method stub
		
		commentDao.commentWrite(commentVO);
		
		return null;
	}

	@Override
	public String commentUpdate(CommentVO commentVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String commentDelete(long c_id) {
		// TODO Auto-generated method stub
		
		
		// 대댓글이 달려 있는 원댓글의 경우 내용을 수정하여 삭제된 것을 알림.
		if(commentDao.countReplyById(c_id) > 0) {
			
			CommentVO commentVO = commentDao.findByCId(c_id);
			commentVO.setC_comment("[삭제된 댓글 입니다]");
			commentDao.commentDeleteUpdate(commentVO);
			
		// 그렇지 않은 경우 DB에서 삭제
		}else {
			
			commentDao.commentDelete(c_id);
			
		}
		
		return null;
	}

	@Override
	public List<CommentVO> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommentVO findByCId(long c_id) {

		return commentDao.findByCId(c_id);
	}

	@Override
	public List<CommentVO> findByBId(long b_id) {
		
		return commentDao.findByBId(b_id);
	}

}
