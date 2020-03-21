package com.biz.board.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biz.board.domain.AuthVO;
import com.biz.board.domain.MemberVO;
import com.biz.board.domain.PageVO;
import com.biz.board.persistence.AuthDao;
import com.biz.board.persistence.MemberDao;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MemberServiceImp implements MemberService{

	@Autowired
	MemberDao memberDao;
	
	@Autowired
	AuthDao authDao;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	// 회원 부분
	@Transactional
	@Override
	public String join(MemberVO memberVO) {

		memberVO.setUserpw(bCryptPasswordEncoder.encode(memberVO.getUserpw()));
		memberDao.insert(memberVO);
		
		
		AuthVO authVO = AuthVO.builder().userid(memberVO.getUserid()).auth("ROLE_MEMBER").build();
		authDao.insert(authVO);
			
		return "회원가입이 정상적으로 완료되었습니다.";
		
	
		
	}
	@Override
	public boolean duplicateCheck(String userid) {
		// TODO Auto-generated method stub
		
		MemberVO memberVO = memberDao.read(userid);
		
		if(memberVO == null) {
			return true;
		}
		
		return false;
	}
	
	
	
	// 관리자 부분
	@Override
	public List<MemberVO> selectAll() {
		// TODO Auto-generated method stub
		List<MemberVO> memberList = memberDao.selectAll();
		
		return memberList;
	}

	@Override
	public MemberVO findById(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String userEnabledUpdate(String userId) {
		// TODO 유저의 권한을 변경하는 method
		
		
		MemberVO memberVO =  memberDao.read(userId);
		
		List<AuthVO> authList = memberVO.getAuthList();
		List<String> userStringAuth = new ArrayList<String>();
		
		authList.forEach(vo -> userStringAuth.add(vo.getAuth()));
		if(userStringAuth.contains("ROLE_ADMIN")) {
			return "관리자 계정은 비활성화 시킬 수 없습니다.";
		}
		
		if(memberVO.getEnabled() > 0) {
			memberVO.setEnabled(0);
			memberDao.updateUserEnabled(memberVO);
			return "계정이 비활성화 되었습니다.";
		}else {
			memberVO.setEnabled(1);
			memberDao.updateUserEnabled(memberVO);
			return "계정이 활성화 되었습니다.";
		}

	}
	
	@Override
	public int getTotalCount(String search) {
		
		return memberDao.getTotalCount(search);
	}
	@Override
	public List<MemberVO> selectList(PageVO pageVO, String search, String option, String solt) {

		List<MemberVO> selectList = memberDao.selectList(pageVO.getOffset(), pageVO.getLimit(), search, option, solt);
		
		return selectList;
	}



	


}
