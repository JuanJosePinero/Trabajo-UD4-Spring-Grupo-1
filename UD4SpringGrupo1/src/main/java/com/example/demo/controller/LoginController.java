package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Student;
import com.example.demo.model.StudentModel;
import com.example.demo.service.StudentService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/")
public class LoginController {

	private static final String LOGIN_VIEW = "auth/login.html";
	private static final String USER_VIEW = "main/userScreen.html";
	
	@Autowired
	@Qualifier("studentService")
	 private StudentService studentService;
	
	@GetMapping("/auth/login")
	public String login(Model model, @RequestParam(name = "error", required = false) String error,
	        @RequestParam(name = "logout", required = false) String logout) {
	    model.addAttribute("student", new Student());
	    
	    if (error != null) {
	        if (error.equals("notActivated")) {
	            model.addAttribute("error", "notActivated");
	        } else if (error.equals("badCredentials")) {
	            model.addAttribute("error", "badCredentials");
	        }
	    }
	    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

	    if (principal instanceof UserDetails) {
	        String userId = ((UserDetails) principal).getUsername();
	       
	        StudentModel student=studentService.getStudentByName(userId);
	        System.out.println("INFORMACION: "+student);
	        if (student != null) {
	            // El objeto student no es nulo, puedes acceder a sus propiedades de manera segura
	            System.out.println("INFORMACION: " + student);
	            int id=student.getId();
	            System.out.println("IDDDDD: "+id);
	        } else {
	            // El objeto student es nulo, manejar el caso en el que no se encuentra al estudiante
	            System.out.println("INFORMACION: Estudiante no encontrado para el nombre de usuario: " + userId);
	        }
	    }


	    model.addAttribute("logout", logout);
	    return LOGIN_VIEW;
	}


	
	@GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response, Model model) {
        org.springframework.security.core.Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        model.addAttribute("logoutMessage", "You have successfully logged out");
        return "redirect:/auth/login";
    }
	
	@GetMapping("/userScreen")
    public String userScreen(Model model) {
        return USER_VIEW;
    }
}
