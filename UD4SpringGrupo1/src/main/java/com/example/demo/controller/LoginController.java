package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.StudentModel;
import com.example.demo.service.LoginService;
import com.example.demo.service.StudentService;

@Controller
@RequestMapping("/")
public class LoginController {

	private static final String LOGIN_VIEW = "auth/login.html";
	private static final String MAIN_SCREEN_VIEW = "main/mainScreen";
	
	@Autowired
	 private StudentService studentService;
	
	@GetMapping("/login")
	public String login() {
		return LOGIN_VIEW;
	}
	
	@GetMapping("/mainScreen")
	public String mainScreen() {
		return MAIN_SCREEN_VIEW;
	}
	
	@PostMapping("/login")
    public String loginSubmit(@ModelAttribute("studentModel") StudentModel studentModel, Model model) {
        if (studentService.login(studentModel.getEmail(), studentModel.getPassword())) {
            return MAIN_SCREEN_VIEW;
        } else {
            model.addAttribute("error", "Invalid credentials");
            return LOGIN_VIEW;
        }
    }
	
}
