package com.cos.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TempControllerTest {
	//http:localhost:8083/blog/tmp/home
	@GetMapping("/tmp/home")
	public String tempHome() {
		System.out.println("tempHome()");
		//파일 리턴 기본 경로 src/main/resources/static
		//리턴명:home.html
		//풀경로 :src/main/resources/static/home.html
		return "/home.html";
	}
	
	@GetMapping("/tmp/jsp")
	public String tempJsp() {
		//prefix: /WEB-INF/views/
		//suffix: /.jsp
		//풀넴 : /WEB-INF/views/test.jsp
		return "test";
	}
}
