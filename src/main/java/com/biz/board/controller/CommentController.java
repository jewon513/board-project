package com.biz.board.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.biz.board.domain.AuthVO;
import com.biz.board.domain.CommentVO;
import com.biz.board.domain.CustomMember;
import com.biz.board.domain.MemberVO;
import com.biz.board.service.BoardService;
import com.biz.board.service.BoardServiceImp;
import com.biz.board.service.CommentService;

@Controller
@RequestMapping(value = "comment")
public class CommentController {

	@Autowired
	CommentService commentServiceImp;

	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String commentList(String b_id, Model model, Authentication authentication) {

		List<CommentVO> commentList = commentServiceImp.findByBId(Long.valueOf(b_id));

		CustomMember cm = (CustomMember) authentication.getPrincipal();
		MemberVO memberVO = cm.getMemberVO();
		
		List<AuthVO> authVOList = memberVO.getAuthList();
		List<String> authList = new ArrayList<String>();
		
		for (AuthVO vo : authVOList) {
			authList.add(vo.getAuth());
		}
		
		// admin 권한이 있는 사람은 모든 댓글을 삭제 수정 할 수 있음. jsp에서 true값으로 판단함.
		model.addAttribute("commentList", commentList);
		model.addAttribute("loginUserId", authentication.getName());
		model.addAttribute("loginUserAuth", authList.contains("ROLE_ADMIN"));
		

		return "inlcude/include-comment-list";
	}

	@ResponseBody
	@RequestMapping(value = "write", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String commentWrite(CommentVO commentVO, Authentication authentication) {
		
		if(commentVO.getC_comment().trim().isEmpty()) {
			return "댓글을 입력해주세요.";
		}

		String userId = authentication.getName();

		commentVO.setC_writer(userId);

		commentServiceImp.commentWrite(commentVO);

		return "OK";
	}

	@ResponseBody
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public String commentDelete(String c_id, Authentication authentication) {

		CommentVO commentVO = commentServiceImp.findByCId(Long.valueOf(c_id));

		CustomMember cm = (CustomMember) authentication.getPrincipal();
		MemberVO memberVO = cm.getMemberVO();

		List<AuthVO> authVOList = memberVO.getAuthList();
		List<String> authList = new ArrayList<String>();

		for (AuthVO vo : authVOList) {
			authList.add(vo.getAuth());
		}

		// 작성한 본인이거나, ADMIN만 댓글을 삭제 할 수 있음.
		if (commentVO.getC_writer().equals(authentication.getName()) || authList.contains("ROLE_ADMIN")) {
			commentServiceImp.commentDelete(Long.valueOf(c_id));
			return "OK";
		}

		return "NO";
	}

}
