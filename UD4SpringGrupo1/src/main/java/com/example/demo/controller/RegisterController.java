package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.ProFamily;
import com.example.demo.model.StudentModel;
import com.example.demo.service.ProFamilyService;
import com.example.demo.service.StudentService;

@Controller
@RequestMapping("/")
public class RegisterController {
	
	private static final String REGISTER_VIEW = "auth/register.html";
	
	 @Autowired
	 private StudentService studentService;
	
	 @Autowired
	    private ProFamilyService proFamilyService;

	    @GetMapping("/register")
	    public String registerForm(@ModelAttribute("studentModel") StudentModel studentModel, Model model) {
	        // Agregar la lista de nombres de las familias profesionales al modelo
	        List<ProFamily> profesionalFamilies = proFamilyService.getAllProfesionalFamilies();
	        model.addAttribute("profesionalFamilies", profesionalFamilies);

	        return REGISTER_VIEW;
	    }
	
	    @PostMapping("/register")
	    public String registerSubmit(@ModelAttribute("studentModel") StudentModel studentModel, RedirectAttributes flash) {
	        studentService.register(studentModel);
	        flash.addFlashAttribute("success", "Student registered succesfully!");
	        return "redirect:/login"; 
	    }

}
