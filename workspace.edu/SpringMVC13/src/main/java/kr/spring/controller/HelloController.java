package kr.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/hello/*")
public class HelloController {
	
	@RequestMapping("/hi")
	public String hello() {
		return "hi";
	}

}
