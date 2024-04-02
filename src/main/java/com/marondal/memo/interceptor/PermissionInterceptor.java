package com.marondal.memo.interceptor;

import java.io.IOException;

import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class PermissionInterceptor implements HandlerInterceptor {
	
	@Override
	public boolean preHandle(
			HttpServletRequest request
			, HttpServletResponse response
			, Object handler) throws IOException {
		
		HttpSession session = request.getSession();
		Integer userId = (Integer)session.getAttribute("userId");
		
		// /post/list-view
		String uri = request.getRequestURI();
		
		// 로그인이 안된 상태에서, 메모 목록, 메모 쓰기, 메모 보기 등등 로그인 기반 페이지 접근 불가 
		// 로그인 페이지로 이동
		if(userId == null) {
			// /post로 시작하는 주소 
			if(uri.startsWith("/post")) {
				
				// 로그인 페이지로 리다이렉트
				response.sendRedirect("/user/login-view");
				
				return false;
				
			}
		} else { // 로그인이 된 경우
			// 로그인, 회원 가입 등 회원정보와 관련된 페이지 접근 불가
			// /user로 시작하는 주소
			if(uri.startsWith("/user")) {
				// 메모 리스트 페이지로 이동
				response.sendRedirect("/post/list-view");
				return false;
			}
			
		}
		
		
		return true;
	}
	

}
