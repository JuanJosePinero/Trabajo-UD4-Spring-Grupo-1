package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Servicio;
import com.example.demo.repository.BusinessRepository;
import com.example.demo.repository.ServicioRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.BusinessService;
import com.example.demo.service.ServicioService;
import com.example.demo.service.StudentService;

@Controller
@RequestMapping("/student")
public class StudentController {

	private static final String STUDENT_SERVICES= "/student/student.html";
	
	@Autowired
	@Qualifier("businessService")
	 private BusinessService businessService;
	
	@Autowired
	@Qualifier("businessRepository")
	 private BusinessRepository businessRepository;
	
	@Autowired
	@Qualifier("servicioService")
	 private ServicioService servicioService;
	
	@Autowired
	@Qualifier("servicioRepository")
	 private ServicioRepository servicioRepository;
	
	@Autowired
	@Qualifier("studentRepository")
	private StudentRepository studentRepository;
	
	@Autowired
	@Qualifier("studentService")
	private StudentService studentService;
	
//	@GetMapping("/viewServices")
//	public String student(Model model) {
//		List<Servicio> serviceList = servicioRepository.findByProFamilyId();
//		model.addAttribute("business1", businessList); 
//	    return BUSINESS_VIEW;
//	}
}
