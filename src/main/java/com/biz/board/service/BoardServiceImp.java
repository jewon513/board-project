package com.biz.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biz.board.domain.BoardVO;
import com.biz.board.domain.PageVO;
import com.biz.board.persistence.BoardDao;

@Service
public class BoardServiceImp implements BoardService {

	@Autowired
	BoardDao boardDao;
	
	@Override
	public String boardWrite(BoardVO boardVO) {
		// TODO 글쓰기
		
		boardDao.boardWrite(boardVO);
		
		return "글이 저장되었습니다.";
	}

	@Override
	public String boardUpdate(BoardVO boardVO) {
		// TODO 글수정
		
		boardDao.boardUpdate(boardVO);
		
		return "글이 수정되었습니다.";
	}

	@Override
	public String boardDelete(long b_id) {
		// TODO 글삭제
		
		boardDao.boardDelete(b_id);
		
		return "글이 삭제되었습니다.";
	}

	@Override
	public List<BoardVO> selectAll() {
		// TODO Auto-generated method stub
		
		return boardDao.selectAll();
	}

	@Override
	public BoardVO findById(long b_id) {

		BoardVO boardVO = boardDao.findById(b_id);
		
		if(boardVO != null) {
			boardVO.setB_views(boardVO.getB_views()+1);
			boardDao.boardViewsUpdate(boardVO);
		}
		
		return boardVO;
		
	}

	@Override
	public int countSelect(String option, String search) {
	
		return boardDao.countSelect(option, search);
	}

	@Override
	public List<BoardVO> selectList(String option, String search, int limit, int offset) {
		// TODO Auto-generated method stub
		
		return boardDao.selectList(option, search, limit, offset);
	}

}
