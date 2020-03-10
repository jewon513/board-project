package com.biz.board.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import com.biz.board.persistence.MemberDao;

public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler  {

	
	/*
	  	HttpServletRequest 객체: 웹에서 넘어온 Request 값을 가지고 있는 객체

		HttpServletResponse 객체: 출력을 정의할 수 있는 객체
		
		Authentication 객체: 인증에 성공한 사용자의 정보를 가지고 있는 객체
	 */
	
	// 사용자의 요청 기록을 꺼낼 수 있는 RequestCache 인터페이스를 이용해 SavedRequest에 사용자가 요청한 request를 저장한다.
	private RequestCache requestCache = new HttpSessionRequestCache(); 
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	@Autowired
	MemberDao memberDao;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		// 로그인이 성공했다면 update_date 칼럼을 업데이트 시킨다. (로그인 기록)
		memberDao.updateUserUpdateDate(authentication.getName());
		
		// 로그인이 성공했다면 실패 세션을 삭제한다.
		this.clearAuthenticationAttributes(request);

		// 아래 구현한 method를 통해 어디로 redirect 시킬지 결정한다.
		this.resultRedirectStrategy(request, response, authentication);
		
	}
	
	private void resultRedirectStrategy(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException{
				
		// 사용자의 request가 담긴 SavedRequest
		SavedRequest savedRequest = requestCache.getRequest(request, response);
		
		// 사용자의 request가 있다면 request한 url로 redirect 시킨다.
		if(savedRequest!=null) {
			String goURL = savedRequest.getRedirectUrl();
			
			redirectStrategy.sendRedirect(request, response, goURL);
			
		// 사용자가 로그인 페이지에서 로그인을 했다면 메인 페이지로 redirect 시킨다.
		}else {
			redirectStrategy.sendRedirect(request, response, "/");
		}
		
	}
	
	// 로그인에 성공했다면 로그인을 실패했을때 생기는 세션 기록을 삭제한다.
	private void clearAuthenticationAttributes(HttpServletRequest request) {
		
		HttpSession session = request.getSession(false);
		if(session==null) {
			return;
		}
		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
		
	}
	

}
