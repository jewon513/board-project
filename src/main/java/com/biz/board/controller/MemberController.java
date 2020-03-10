package com.biz.board.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.biz.board.domain.MemberVO;
import com.biz.board.service.MemberService;

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
	public String join() {
		
		return "join";
	}
	
	@RequestMapping(value = "join", method=RequestMethod.POST)
	public String join(MemberVO memberVO, Model model) {
		
		String result = memberService.join(memberVO);
		model.addAttribute("message", result);
		
		return "home";
	}
	
	@RequestMapping(value = "myInfo", method = RequestMethod.GET)
	public String myInfo() {
		
		return "myInfo";
	}
	
	
	//	 이 컨트롤러에서 exception이 발생하면 이 메서드가 실행되고 error page로 이동
	@ExceptionHandler
	public String exceptionHandler(HttpServletRequest req,Exception exception, Model model) {
		
		exception.printStackTrace();
		
		model.addAttribute("exception", exception);
		model.addAttribute("url", req.getRequestURL());
		
		return "error";
	}
	

	
}
