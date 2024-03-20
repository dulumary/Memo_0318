package com.marondal.memo.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marondal.memo.common.EncrytUtils;
import com.marondal.memo.user.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public int addUser(String loginId, String password, String name, String email) {
		
		String encryptPassword = EncrytUtils.md5(password);
		
		return userRepository.insertUser(loginId, encryptPassword, name, email);
	}

}
