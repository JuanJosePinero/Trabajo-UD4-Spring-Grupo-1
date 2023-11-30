package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Student;
import com.example.demo.model.StudentModel;
import com.example.demo.service.StudentService;

@Controller
@RequestMapping("/")
public class LoginController {

	private static final String LOGIN_VIEW = "auth/login.html";
	private static final String ADMIN_VIEW = "admin/adminScreen";
	
	@Autowired
	 private StudentService studentService;
	
	@GetMapping("/login")
	public String login(Model model, @RequestParam(name="error", required=false) String error, 
			@RequestParam(name="logout", required=false) String logout) {
		model.addAttribute("student", new Student());
		model.addAttribute("error", error);
		model.addAttribute("logout", logout);
		return LOGIN_VIEW;
	}
	
	@GetMapping("/adminScreen")
	public String adminScreen(Model model) {
	    List<StudentModel> students = studentService.listAllStudents();
	    model.addAttribute("students", students); 
	    return ADMIN_VIEW;
	}
	
//	@PostMapping("/login")
//    public String loginSubmit(@ModelAttribute("studentModel") StudentModel studentModel, Model model) {
//        if (studentService.login(studentModel.getEmail(), studentModel.getPassword())) {
//            return "redirect:/" + ADMIN_VIEW;
//        } else {
//            model.addAttribute("error", "Invalid credentials");
//            return LOGIN_VIEW;
//        }
//    }
	
}
