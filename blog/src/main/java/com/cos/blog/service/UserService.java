package com.cos.blog.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.Users;
import com.cos.blog.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Transactional
	public void 회원가입(Users user) {
		String rawPassword = user.getPassword();
		String encPassword = encoder.encode(rawPassword);
		user.setPassword(encPassword);
		user.setRoles(RoleType.USER);
		
		
		userRepository.save(user);
	}

	@Transactional
	public void 회원수정(Users user) {

		Users persistance = userRepository.findById(user.getId()).orElseThrow(() -> {
			return new IllegalArgumentException("회원 찾기 실패");
		});

		if(persistance.getOauth()==null || persistance.getOauth().equals("")) {
			String rawPassword =user.getPassword();
			String encPassword =encoder.encode(rawPassword);
			persistance.setPassword(encPassword);
			persistance.setEmail(user.getEmail());
			
		}

	}
	

	@Transactional(readOnly =true)
	public Users 회원찾기(String username) {
		Users user = userRepository.findByUsername(username).orElseGet(()->{
			return new Users();
		});
		return user;
	}

}
