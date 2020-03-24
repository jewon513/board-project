package com.biz.board.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.biz.board.domain.AuthVO;
import com.biz.board.domain.CustomMember;
import com.biz.board.domain.MemberVO;
import com.biz.board.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MemberController {
	
	@Autowired
	MemberService memberService;

	@RequestMapping(value = "login", method=RequestMethod.GET)
	public String login(Model model, Authentication authentication) {
		
		if(authentication != null) {
			return "redirect:/";
		}
		
		return "login";
		
	}
	
	@RequestMapping(value = "join", method=RequestMethod.GET)
	public String join(MemberVO memberVO) {
		
		return "join";
	}
	
	@RequestMapping(value = "join", method=RequestMethod.POST)
	public String join(@Valid MemberVO memberVO, Errors errors ,Model model) {
		
		if(errors.hasErrors()) {
			return "join";
		}
		
		String result = memberService.join(memberVO);
		model.addAttribute("message", result);
		
		return "result";
	}
	
	@ResponseBody
	@RequestMapping(value = "deleteAccount", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String deleteAccount(String userid, Authentication authentication) {
		
		if(authentication==null) {
			return "로그인을 먼저 해주세요";
		}
		
		CustomMember cm = (CustomMember) authentication.getPrincipal();
		
		MemberVO memberVO = cm.getMemberVO();
		List<AuthVO> authList = memberVO.getAuthList();
		List<String> strAuthList = new ArrayList<String>();
		
		authList.forEach(vo -> strAuthList.add(vo.getAuth()));
		if(strAuthList.contains("ROLE_ADMIN")) {
			return "관리자는 탈퇴가 불가능합니다.";
		}
	
		String loginUserId = authentication.getName();
		if(!loginUserId.equals(userid)) {
			return "잘못 입력하셨습니다. 다시 입력해주세요.";
		}
		
		String result = memberService.deleteMember(loginUserId);
		
		return result;
		
	}
	
	@ResponseBody
	@RequestMapping(value ="idCheck", method = RequestMethod.POST)
	public String userDuplicateCheck(String userid) {
		
		boolean result = memberService.duplicateCheck(userid);
		
		log.debug("결과 값 " + result);
		
		if(result) {
			return "OK";
		}else {
			return "FALSE";
		}
			
		
	}
	
	@RequestMapping(value = "myInfo", method = RequestMethod.GET)
	public String myInfo() {
		
		return "myInfo";
	}
	

	
}
