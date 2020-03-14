package com.biz.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.biz.board.domain.AuthVO;
import com.biz.board.domain.MemberVO;
import com.biz.board.persistence.AuthDao;
import com.biz.board.persistence.MemberDao;

@Service
public class MemberServiceImp implements MemberService{

	@Autowired
	MemberDao memberDao;
	
	@Autowired
	AuthDao authDao;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Transactional
	@Override
	public String join(MemberVO memberVO) {

		memberVO.setUserpw(bCryptPasswordEncoder.encode(memberVO.getUserpw()));
		memberDao.insert(memberVO);
		
		
		AuthVO authVO = AuthVO.builder().userid(memberVO.getUserid()).auth("ROLE_MEMBER").build();
		authDao.insert(authVO);
			
		return "회원가입이 정상적으로 완료되었습니다.";
		
	}


}
