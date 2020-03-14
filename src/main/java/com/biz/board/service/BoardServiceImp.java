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
		// 지금은 글만 쓰지만, 이후에 파일도 같이 업로드 할 경우 트랜잭션 설정 필요
		
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

		return boardDao.findById(b_id);
		
	}

	// pagination
	@Override
	public List<BoardVO> selectList(PageVO pageVO) {
		// TODO Auto-generated method stub		
		
		return boardDao.selectList(pageVO);
	}

	@Override
	public int countAll() {
		
		return boardDao.countAll(); 
	}

}
