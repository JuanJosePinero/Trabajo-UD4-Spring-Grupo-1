package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.ProFamily;
import com.example.demo.model.StudentModel;
import com.example.demo.repository.ProFamilyRepository;
import com.example.demo.service.ProFamilyService;
import com.example.demo.service.StudentService;

@Controller
@RequestMapping("/")
public class RegisterController {
	
	private static final String REGISTER_VIEW = "auth/register.html";
	
	 @Autowired
	 private StudentService studentService;
	 
	 @Autowired
	 @Qualifier("proFamilyRepository")
	 private ProFamilyRepository proFamilyRepository;
	
	 @Autowired
	    private ProFamilyService proFamilyService;

	    @GetMapping("/auth/register")
	    public String registerForm(@ModelAttribute("studentModel") StudentModel studentModel, Model model) {
	        List<ProFamily> profesionalFamilies = proFamilyRepository.findAll();
	        model.addAttribute("profesionalFamilies", profesionalFamilies);

	        return REGISTER_VIEW;
	    }
	
	    @PostMapping("/auth/register")
	    public String registerSubmit(@ModelAttribute("studentModel") StudentModel studentModel, RedirectAttributes flash, @RequestParam("confirmPassword") String confirmPassword) {	
	    	System.out.println(studentModel.toString());
	    	if(studentModel.getName().isEmpty() || studentModel.getPassword().isEmpty() || studentModel.getSurname().isEmpty()){
	    		flash.addFlashAttribute("error", "Some fields might be empty");
	    		return "redirect:/auth/register";
	    	}else if(!studentModel.getName().matches("[a-zA-Z]+")) {
	    		flash.addFlashAttribute("error", "Name contains invalid character");
	    		return "redirect:/auth/register";
	    	}else if(!studentModel.getSurname().matches("[a-zA-Z]+")) {
	    		flash.addFlashAttribute("error", "Surname contains invalid character");
	    		return "redirect:/auth/register";
	    	}else if(!studentModel.getPassword().matches(confirmPassword)) {
	    		flash.addFlashAttribute("error", "Passwords do not match");
	    		return "redirect:/auth/register";
	    	}else if(studentModel.getPassword().length() < 6 || studentModel.getPassword().length() > 18) {
	    		flash.addFlashAttribute("error", "Password must be between 6 and 18 characters long");
	    		return "redirect:/auth/register";   		
	    	}else if(studentService.mailExists(studentModel.getEmail())) {
	    		flash.addFlashAttribute("error", "That email is already in use");
	    		return "redirect:/auth/register";	  
	    	}else if(!studentService.isMailValid(studentModel.getEmail())){ 
	    		flash.addFlashAttribute("error", "That email is invalid");
	    		return "redirect:/auth/register";	 
	    	}else{
	    		studentService.register(studentModel);
		        flash.addFlashAttribute("success", "Student registered succesfully!");
		        return "redirect:/login";	    		
	    	}  
	    }
	    

}
