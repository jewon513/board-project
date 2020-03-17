package com.biz.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biz.board.domain.BoardVO;
import com.biz.board.domain.CommentVO;
import com.biz.board.persistence.BoardDao;
import com.biz.board.persistence.CommentDao;

@Service
public class CommentServiceImp implements CommentService{

	@Autowired
	CommentDao commentDao;
	
	@Autowired
	BoardDao boardDao;
	
	@Transactional
	@Override
	public String commentWrite(CommentVO commentVO) {
		// TODO Auto-generated method stub
		
		// 1. 댓글을 INSERT 하고
		commentDao.commentWrite(commentVO);
		
		// 2. 작성이 완료 됐으면 게시글의 b_reply 카운트를 1 증가
		BoardVO boardVO = boardDao.findById(commentVO.getC_b_id());
		boardVO.setB_reply_count(boardVO.getB_reply_count() + 1);
		boardDao.boardReplyUpdate(boardVO);
		
		return null;
	}

	@Override
	public String commentUpdate(CommentVO commentVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	@Override
	public String commentDelete(long c_id) {
		// TODO Auto-generated method stub
		
		
		CommentVO commentVO = commentDao.findByCId(c_id);
		// 대댓글이 달려 있는 원댓글의 경우 내용을 수정하여 삭제된 것을 알림.
		if(commentDao.countReplyById(c_id) > 0) {
			
			commentVO.setC_comment("[삭제된 댓글 입니다]");
			commentDao.commentDeleteUpdate(commentVO);
			
		// 그렇지 않은 경우 DB에서 삭제
		// 댓글 카운트는 DB에서 완전히 삭제된 경우만 카운트
		}else {			
			commentDao.commentDelete(c_id);
			BoardVO boardVO = boardDao.findById(commentVO.getC_b_id());
			boardVO.setB_reply_count(boardVO.getB_reply_count() - 1);
			boardDao.boardReplyUpdate(boardVO);
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
