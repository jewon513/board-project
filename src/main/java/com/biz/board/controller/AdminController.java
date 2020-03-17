package com.biz.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.biz.board.domain.MemberVO;
import com.biz.board.service.MemberService;

@RequestMapping(value = "admin")
@Controller
public class AdminController {

	@Autowired
	MemberService memberService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String manage(Model model) {
		
		List<MemberVO> memberList = memberService.selectAll();
		
		model.addAttribute("memberList", memberList);
		
		return "manage";
	}
	
}
