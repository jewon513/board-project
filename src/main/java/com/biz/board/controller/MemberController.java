package com.biz.board.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.biz.board.domain.MemberVO;
import com.biz.board.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MemberController {
	
	@Autowired
	MemberService memberService;

	@RequestMapping(value = "login", method=RequestMethod.GET)
	public String login(String error, Model model, Authentication authentication) {
		
		if(error != null) {
			error = "로그인에 실패하였습니다.";
			model.addAttribute("error",error);
		}
		
		// 로그인을 했다면 redirect 해서 메인페이지로 이동
		try {
			if(authentication.getPrincipal() != null) {
				return "redirect:/";
			}
			// 로그인을 하지 않았다면 nullpointexception이 발생하고 login 페이지로 이동
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "login";
		}
		
		return "redirect:/";
		
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
		
		return "home";
	}
	
	@ResponseBody
	@RequestMapping(value ="idCheck", method = RequestMethod.POST)
	public String userDuplicateCheck(String userid) {
		
		boolean result = memberService.duplicateCheck(userid);
		
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
