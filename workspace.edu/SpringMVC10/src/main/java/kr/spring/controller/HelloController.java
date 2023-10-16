package kr.spring.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	// 테스트용 컨트롤러
	
	
	
	@RequestMapping("/hello")
	public String hello() {
		
		return "Hello! Spring Boot!";
	}
	
	
	
	
	
	
	
	
	
}
