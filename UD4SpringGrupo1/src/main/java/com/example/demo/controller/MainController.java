package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {
	
	private static final String HOME_VIEW = "main/home.html";

	@GetMapping("/home")
	public String home() {
		return HOME_VIEW;
	}
	
	@GetMapping("/")
	public String redirect() {
		return "redirect:/home";
	}
	

}