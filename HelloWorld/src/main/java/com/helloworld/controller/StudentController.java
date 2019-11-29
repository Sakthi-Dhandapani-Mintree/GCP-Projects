package com.helloworld.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping(value="/welcome")
public class StudentController {
	@RequestMapping(value = "/hi")
	public String hi() {		
		return "Hi Student";
	}

	@GetMapping(value = "/action")
	public String action() {
		return "Student Action";
	}
}
