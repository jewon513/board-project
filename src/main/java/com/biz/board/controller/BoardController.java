package com.biz.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.biz.board.domain.BoardVO;
import com.biz.board.domain.CustomMember;
import com.biz.board.domain.MemberVO;
import com.biz.board.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class BoardController {

	@Autowired
	BoardService boardService;
	
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String boardList(Model model) {
		
		List<BoardVO> boardList = boardService.selectAll();
		
		model.addAttribute("boardList", boardList);
		
		return "board";
	}
	
	@RequestMapping(value = "detail", method = RequestMethod.GET)
	public String boardDetail(String number, Model model) {
		
		BoardVO boardVO = boardService.findById(Long.valueOf(number));
		
		model.addAttribute("boardVO", boardVO);
		
		return "detail";
	}
	
	@RequestMapping(value = "write", method = RequestMethod.GET)
	public String boardWrite() {
	
		
		return "write";
	}
	
	@RequestMapping(value = "write", method = RequestMethod.POST)
	public String boardWrite(BoardVO boardVO, Authentication authentication) {
	
		// 글을 쓰는 유저의 아이디를 가져오기
		CustomMember cm = (CustomMember) authentication.getPrincipal();
		MemberVO memberVO = cm.getMemberVO();
		log.warn("글쓰기 작성한 유저의 정보 : " + memberVO.toString());
		
		// 유저의 아이디를 setting 해주고 db에 저장
		boardVO.setB_writer(memberVO.getUserid());
		boardService.boardWrite(boardVO);
		
		return "home";
	}
}
