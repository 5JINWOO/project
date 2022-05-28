package com.cos.blog.test;

import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.Users;
import com.cos.blog.repository.UserRepository;

@RestController
public class DummyControllerTest {
//	//http://local:8083/blog/dummy/join(요청)
//	//http의 body 에 username, password, email 데이터를 가지고 요청
//	@PostMapping("/dummy/join")
//	public String join(String username, String password, String email) {
//		System.out.println("username: " + username);
//		System.out.println("password: " + password);
//		System.out.println("email: " + email);
//		return "회원가입이 완료되었습니다.";
//	}
	//http://local:8083/blog/dummy/join(요청)
//	//http의 body 에 username, password, email 데이터를 가지고 요청
	
	@Autowired// 의존성 주입(DI)
	private UserRepository userRepository;
	
	@PostMapping("/dummy/join")
	public String join(Users users) {
		//key=value(약속된 규칙)
		System.out.println("id : " + users.getId());
		System.out.println("username: " + users.getUsername());
		System.out.println("password : " + users.getPassword());
		System.out.println("email : " + users.getEmail());
		System.out.println("role : " + users.getRoles());
		System.out.println("createDate : " + users.getCreateDate());
		
		users.setRoles(RoleType.USER); //디폴트값을 설정
		userRepository.save(users);
		return "회원가입이 완료 되었습니다.";
	}
	
	
	///선택 아이디만 출력
	@GetMapping("/dummy/user/{id}")
	public Users detail(@PathVariable int id) {
		//DB에 id 가 4까지 밖에 없는데 만약 주소에 5를 넣으면 null 이 리턴이 될것인데
		//그러면 에러가 발생하게 되니 optionnal로 감싸서 리턴된다.
		//그렇기 때문에 리턴전에 null인지 아닌지 판단이 필요하다.
		Users user =userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>(){
			@Override
			public IllegalArgumentException get() {
				return new IllegalArgumentException("해당 유저는 없습니다. id: "+id);
			}
		});
		return user;
	}
	
	// 모든 유저 출력
	//http://localhost:8083/blog/dummy/user
	@GetMapping("/dummy/user")
	public List<Users> list(){
		return userRepository.findAll();
	}
	
	//정해진 개수 만큼 select   //한페이지에 몇개의 게시글을 나타낼때쓰나?.
	//page?page=0
	@GetMapping("/dummy/user/page")
	public List<Users> pageList(@PageableDefault(size = 2 ,sort ="id", direction =Sort.Direction.DESC)Pageable pageable) {
		
		Page<Users> pagingUSer = userRepository.findAll(pageable);
		
		if (pagingUSer.isLast()) {
			System.out.println("마지막 요소입니다.");
		}
		
		List <Users> users = pagingUSer.getContent();
		
		return users;
	}
	
	//update 테스트 put 방식으로 요청
	@Transactional
	@PutMapping("/dummy/user/{id}")
	public Users updateUser(@PathVariable int id,@RequestBody Users requestUser) {
		System.out.println("id : " + id);
		System.out.println("password : " +requestUser.getPassword());
		System.out.println("email : " + requestUser.getEmail());
		
		Users user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("수정에 실패하였습니다.");
		});
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());
		user.setUsername(requestUser.getEmail());
//		userRepository.save(user); --@Transactional 성공하면 알아서 해준다.
		return null;
	}
	
	//delete 테스트
	@DeleteMapping("/dummy/user/{id}")
	public String delete(@PathVariable int id) {
		try {
			userRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			return "삭제 실패하였습니다.해당id는 없습니다. id: "+id;
		}
		return "삭제되었습니다. id: " +id;
	}
	
}
