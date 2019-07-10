package com.springboot.https.demo.hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloApp {
	
	@RequestMapping("/hello")
	public String sayHi()
	{
		return "HI";
	}

}
