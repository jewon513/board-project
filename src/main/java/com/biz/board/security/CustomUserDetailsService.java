package com.biz.board.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.biz.board.domain.CustomMember;
import com.biz.board.domain.MemberVO;
import com.biz.board.persistence.MemberDao;

import lombok.extern.slf4j.Slf4j;

public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	MemberDao memberDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		
		MemberVO memberVO = memberDao.read(username);
		
		// TODO Auto-generated method stub
		return memberVO == null ? null : new CustomMember(memberVO);
		
	}

}
