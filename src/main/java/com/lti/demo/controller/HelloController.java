package com.lti.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	// http://localhost:8080/hello

	@RequestMapping("/hello")
	public String hello() {
		System.out.println("hello");
		return "Hello world!";
	}

}
