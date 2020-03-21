package com.biz.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.biz.board.domain.MemberVO;
import com.biz.board.domain.PageVO;
import com.biz.board.service.MemberService;
import com.biz.board.service.PageService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(value = "admin")
@Controller
public class AdminController {

	@Autowired
	MemberService memberService;
	
	@Autowired
	PageService pageService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String manage(Model model,  
			@RequestParam(value ="search", required = false, defaultValue = "") String search,
			@RequestParam(value ="currentPageNo", required = false, defaultValue ="1") int currentPageNo,
			@RequestParam(value ="option", required = false, defaultValue = "reg_date") String option,
			@RequestParam(value ="sort", required= false, defaultValue = "") String solt) {
		
		int totalCount = memberService.getTotalCount(search);
		
		PageVO pageVO = pageService.getPagination(totalCount, currentPageNo);
		
		List<MemberVO> memberList = null;
		if(pageVO != null) {
			memberList = memberService.selectList(pageVO, search, option, solt);
		}
		
		model.addAttribute("memberList", memberList);
		model.addAttribute("page", pageVO);
		model.addAttribute("search", search);
		model.addAttribute("currentPageNo", currentPageNo);
		model.addAttribute("option", option);
		model.addAttribute("solt", solt);
		
		return "manage";
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/enabled", method = RequestMethod.POST,  produces = "text/html;charset=UTF-8")
	public String toggleEnalbed(String userid, Authentication authentication) {
		
		String result = memberService.userEnabledUpdate(userid);
		
		return result;
	}
	
}
