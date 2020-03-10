package com.biz.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.biz.board.domain.BoardVO;
import com.biz.board.domain.CustomMember;
import com.biz.board.domain.MemberVO;
import com.biz.board.service.BoardService;
import com.biz.board.service.FileUpService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class BoardController {

	@Autowired
	BoardService boardService;
	
	@Autowired
	FileUpService fileupService;
	
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String boardList(Model model) {
		
		List<BoardVO> boardList = boardService.selectAll();
		
		model.addAttribute("boardList", boardList);
		
		return "board";
	}
	
	@RequestMapping(value = "detail", method = RequestMethod.GET)
	public String boardDetail(String number, Model model, Authentication authentication) {
		
		BoardVO boardVO = boardService.findById(Long.valueOf(number));
		
		String userId = authentication.getName();
		log.debug("로그인한 userID : " + userId);
		
		if(userId.equals(boardVO.getB_writer())) {
			model.addAttribute("userCheck", true);
		}
		
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
	
	@ResponseBody
	@RequestMapping(value = "image_up", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String fileUp(MultipartFile upFile) {
		
		log.debug("업로드 된 파일의 파일 이름 : " + upFile.getOriginalFilename());
		
		String returnFileName = fileupService.fileup(upFile);
		
		if(returnFileName == null) {
			return "FAIL";
		}
		
		return returnFileName;
	}
}
