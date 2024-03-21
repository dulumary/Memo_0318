package com.marondal.memo.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.marondal.memo.user.domain.User;
import com.marondal.memo.user.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RequestMapping("/user")
@RestController // @Controller + @ReseponseBody
public class UserRestController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/join")
	public Map<String, String> join(
			@RequestParam("loginId") String loginId
			, @RequestParam("password") String password
			, @RequestParam("name") String name
			, @RequestParam("email") String email) {
		
		int count = userService.addUser(loginId, password, name, email);
		
		Map<String, String> resultMap = new HashMap<>();
		
		if(count == 1) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		
		return resultMap;
	}
	
	@PostMapping("/login")
	public Map<String, String> login(
			@RequestParam("loginId") String loginId
			, @RequestParam("password") String password
			, HttpServletRequest request) {
		
		User user = userService.getUserByLoginIdAndPassword(loginId, password);
		
		Map<String, String> resultMap = new HashMap<>();
		
		if(user != null) {
			// 로그인 성공
			resultMap.put("result", "success");
			// 조회된 사용자 정보를 세션에 저장하는 과정
			HttpSession session = request.getSession();
			// user 의 primary key 
			session.setAttribute("userId", user.getId());
			session.setAttribute("userName", user.getName());
			
		} else {
			// 로그인 실패
			resultMap.put("result", "fail");
		}
		
		return resultMap;
		
	}

}
