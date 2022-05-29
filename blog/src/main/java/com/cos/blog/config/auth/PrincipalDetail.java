package com.cos.blog.config.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cos.blog.model.Users;

import lombok.Getter;
import org.springframework.security.oauth2.core.user.OAuth2User;

//스프링 시큐리티가 로그인 요청을 가로채서 로그인을 진행하고 완료가 되면
//UserDetails 타입의 오브젝트를 스프링 시큐리티의 고유한 세션 저장소에 저장
//그 때 저장되는게 PrincipalDetail이 저장된다.
@Getter
public class PrincipalDetail implements UserDetails, OAuth2User {
	
	private Users user; //콤포지션(객체를 품고있음 상속과 다음)

	private Map<String ,Object> attributes;
	//일반 로그인
	public PrincipalDetail(Users user) {
		this.user=user;
	}
	//oAuth 로그인
	public PrincipalDetail(Users user,Map<String ,Object> attributes) {
		this.user=user;
		this.attributes=attributes;
	}
	
	//계정이 갖고있는 권한목록을 리턴한다.(권한이 여러개 있을 수 있어서 루프를 돌려야하는데 지금은 한개만)
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collectors = new ArrayList<>();
		collectors.add(()->{return "ROLE_"+user.getRoles();});
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUsername();
	}
	//계정이 만료되지 않았는지 리턴된다.(true:만료안됨)
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	//계정이 잠겨있지 않았는지 리턴된다.(true:잠기지않음)
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	//계정이 만료되지 않았는지 리턴된다.(true:만료안됨)
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	//계정이 활성화 되어있는지 리턴한다.(true :활성화)
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public Map<String, Object> getAttributes() {

		return attributes;
	}

	@Override
	public String getName() {
		return null;
	}
}
