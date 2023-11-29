package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.StudentModel;
import com.example.demo.service.StudentService;

@Controller
@RequestMapping("/")
public class RegisterController {
	
	private static final String REGISTER_VIEW = "auth/register.html";
	
	 @Autowired
	 private StudentService studentService;
	
	@GetMapping("/register")
	public String register() {
		return REGISTER_VIEW;
	}
	
	@PostMapping("/register")
    public String registerSubmit(@ModelAttribute("student") StudentModel studentModel) {
        studentService.register(studentModel);
        return "redirect:/login"; 
    }

}
