package com.biz.board.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.io.filefilter.FalseFileFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.biz.board.domain.AuthVO;
import com.biz.board.domain.BoardVO;
import com.biz.board.domain.CustomMember;
import com.biz.board.domain.MemberVO;
import com.biz.board.domain.PageVO;
import com.biz.board.service.BoardService;
import com.biz.board.service.FileUpService;
import com.biz.board.service.PageService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class BoardController {

	@Autowired
	BoardService boardService;
	
	@Autowired
	FileUpService fileupService;
	
	@Autowired
	PageService pageService;
	
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String boardList(Model model, 
				@RequestParam(value = "currentPageNo", defaultValue = "1", required = true) int currentPageNo,
				@RequestParam(value = "option", defaultValue = "", required = false) String option,
				@RequestParam(value = "search", defaultValue = "", required = false) String search
			) {
		
		int totalCount = boardService.countSelect(option, search);  
		PageVO pageVO = pageService.getPagination(totalCount, currentPageNo);
		
		List<BoardVO> boardList = boardService.selectList(option, search, pageVO.getLimit(), pageVO.getOffset());
		
		model.addAttribute("boardList", boardList);
		model.addAttribute("page", pageVO);
		model.addAttribute("option", option);
		model.addAttribute("search", search);
		
		return "board";
	}
	
	@RequestMapping(value = "detail", method = RequestMethod.GET)
	public String boardDetail(String number, Model model, Authentication authentication) {
		
		
		// 보여줄 게시글 검색
		BoardVO boardVO = boardService.findById(Long.valueOf(number));
		
		// 수정 삭제 버튼은 작성자이거나 ROLE_ADMIN 인 경우만 보여주기
		CustomMember cm = (CustomMember) authentication.getPrincipal();
		MemberVO memberVO = cm.getMemberVO();
		
		List<AuthVO> authVOList = memberVO.getAuthList();
		List<String> authList = new ArrayList<String>();
		
		for (AuthVO vo : authVOList) {
			authList.add(vo.getAuth());
		}
		
		if(boardVO.getB_writer().equals(memberVO.getUserid()) || authList.contains("ROLE_ADMIN")) {
			model.addAttribute("userCheck", true);
		}
			
		
		model.addAttribute("boardVO", boardVO);

		
		
		return "detail";
	}
	
	@RequestMapping(value = "write", method = RequestMethod.GET)
	public String boardWrite(BoardVO boardVO) {
	
		return "write";
	}
	
	@RequestMapping(value = "write", method = RequestMethod.POST)
	public String boardWrite(@Valid BoardVO boardVO, Errors errors, Authentication authentication) {
	
		if(errors.hasErrors()) {
			return "write";
		}
		
		// 글을 쓰는 유저의 아이디를 가져오기
		CustomMember cm = (CustomMember) authentication.getPrincipal();
		MemberVO memberVO = cm.getMemberVO();
		log.warn("글쓰기 작성한 유저의 정보 : " + memberVO.toString());
		
		// 유저의 아이디를 setting 해주고 db에 저장
		boardVO.setB_writer(memberVO.getUserid());
		boardService.boardWrite(boardVO);
		
		return "redirect:/list";
	}
	
	@RequestMapping(value = "update", method = RequestMethod.GET)
	public String boardUpdate(String b_id, Model model) {
		
		BoardVO boardVO =boardService.findById(Long.valueOf(b_id));
		
		model.addAttribute("boardVO", boardVO);
		
		return "write";
	}
	
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String boardUpdate(@Valid BoardVO boardVO, Errors errors) {
		
		if(errors.hasErrors()) {
			return "write";
		}
		
		boardService.boardUpdate(boardVO);
		
		return "redirect:/list";
		
	}
	
	@ResponseBody
	@RequestMapping(value = "delete", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String boardDelete(String id) {
		
		log.debug("삭제를 위해 날라온 id 값 : " + id);
		
		String result = boardService.boardDelete(Long.valueOf(id));
		
		return result;
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
