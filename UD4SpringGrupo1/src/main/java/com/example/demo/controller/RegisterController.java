package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class RegisterController {
	
	private static final String REGISTER_VIEW = "auth/register.html";
	
	@GetMapping("/register")
	public String register() {
		return REGISTER_VIEW;
	}

}
