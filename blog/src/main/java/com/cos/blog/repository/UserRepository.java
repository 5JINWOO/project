package com.cos.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.blog.model.Users;
	//jsp의 dao
	//자동으로 bean 동록이 된다.
	//@Repository 생략 가능
public interface UserRepository extends JpaRepository<Users,Integer> {
	//로그인을 위한 함수
	//jpa Naming전략
	//select * from users where username=? AND password=?;
////	Users findByUsernameAndPassword(String username, String password);
	
	//위와 같음
	//@Query(value="select *from users whrere username=?1
	//AND password?2",nativeQuery=true)
	//User login (String username, String password);
	
	//select * from users where username=? AND password=?;
	Optional<Users> findByUsername(String username); //리턴 받는타입 <Users>

	
}
