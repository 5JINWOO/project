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
	private BCryptPasswordEncoder encodeer;
	
	@Transactional
	public void 회원가입(Users user) {
		String rawPassword = user.getPassword();
		String encPassword = encodeer.encode(rawPassword);
		user.setPassword(encPassword);
		user.setRoles(RoleType.USER);
		
		
		userRepository.save(user);
	}
	//회원정보수정
	@Transactional
	public void 회원수정(Users user) {
		//수정시에는 영속성 컨텍스트 Users오브젝트를 영속화 시키고,
		//영속화된 Users 오브젝트를 수정
		//select를 해서 Users오브젝트를 DB로 부터 가져오는 이뉴는 영속화를 하기위해서
		//영속화된 오브젝트를 변경하면 자동으로 DB에 update문을 날려준다.
		Users persistance = userRepository.findById(user.getId()).orElseThrow(() -> {
			return new IllegalArgumentException("회원 찾기 실패");
		});
		//Validate 체크 => oauth필드에 값이 없으면 수정가능
		if(persistance.getOauth()==null || persistance.getOauth().equals("")) {
			String rawPassword =user.getPassword();
			String encPassword =encodeer.encode(rawPassword);
			persistance.setPassword(encPassword);
			persistance.setEmail(user.getEmail());
			
		}
		//회원 수정 메서드종료 = 서비스 종료 = 트랜잭션 종료 = commit
		//영속화된 persistance 객체의 변화가 감지되면 더티체킹이 되어 update문 날림
	}
	
	//회원 찾기
	@Transactional(readOnly =true)
	public Users 회원찾기(String username) {
		Users user = userRepository.findByUsername(username).orElseGet(()->{
			return new Users();
		});
		return user;
	}
	
//	@Transactional(readOnly=true)
//	public Users 로그인(Users user) {
//		
//			return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
//					
//		
//	}
}
