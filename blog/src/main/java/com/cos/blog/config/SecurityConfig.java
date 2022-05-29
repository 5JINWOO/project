package com.cos.blog.config;

import com.cos.blog.config.oauth.PrincipalOauth2UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cos.blog.config.auth.PrincipalDetailService;

//빈등록 : 스프링 컨테이너에서 객체를 관리할 수 있게 해주는 것
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private PrincipalOauth2UserService principalOauth2UserService;
	@Autowired
	private PrincipalDetailService principalDetailService;
	
	@Bean //IoC가 된다.
	public BCryptPasswordEncoder encodePWD() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http
			.csrf().disable() //csrf 토큰 비활성화(테스트시 걸어두는게 좋음)
			.authorizeHttpRequests() //요청에 의한 보안검사 시작
			.antMatchers("/","/auth/**","/js/**","/css/**","/image/**")
			.permitAll()
			.anyRequest()//이게 아닌 다른 모든 요청은
			.authenticated()//인증이 필요
		.and()
			.formLogin()
<<<<<<< HEAD
			.loginPage("/auth/loginForm") //사용자 정의 로그인 페이지
			.loginProcessingUrl("/auth/loginProc")//로그인 form action url
		//스프링 시큐리티가 해당 주소로 요청오는 로그인을 가로채서 대신 로그인
			.defaultSuccessUrl("/")//로그인 성공후 이동페이지
		//로그인 성공 후에 요청되는 페이지
			//.failureUrl("로그인 실패 후 요청 페이지")
		.and()
				.oauth2Login()
				.loginPage("/login")
				.userInfoEndpoint()
				.userService(principalOauth2UserService);

		
=======
			.loginPage("/auth/loginForm")
			.loginProcessingUrl("/auth/loginProc")

			.defaultSuccessUrl("/");//로그인 성공후 이동페이지

>>>>>>> hyosung
	}
	

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD());
		
	}

}
