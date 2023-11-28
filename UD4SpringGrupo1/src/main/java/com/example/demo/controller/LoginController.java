package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class LoginController {

	private static final String LOGIN_VIEW = "auth/login.html";
	
	@GetMapping("/login")
	public String login() {
		return LOGIN_VIEW;
	}
	
}
