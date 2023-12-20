package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.ProFamily;
import com.example.demo.entity.Servicio;
import com.example.demo.repository.BusinessRepository;
import com.example.demo.repository.ProFamilyRepository;
import com.example.demo.repository.ServicioRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.BusinessService;
import com.example.demo.service.ProFamilyService;
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
	@Qualifier("proFamilyRepository")
	private ProFamilyRepository proFamilyRepository;
	
	@Autowired
	@Qualifier("proFamilyService")
	private ProFamilyService proFamilyService;
	
	@Autowired
	@Qualifier("studentService")
	private StudentService studentService;
	
	@Autowired
	@Qualifier("studentRepository")
	private StudentRepository studentRepository;
	
	
	//Llamo a todos los servicios que sean de la misma familia profesional que el alumno que haya hecho login.
	@GetMapping("/viewServices/{studentId}")
	public String student(@PathVariable("studentId") int st, Model model) {
		ProFamily proFamily = studentRepository.findById(st).getProfesionalFamily();
		List<Servicio> serviceList = servicioRepository.findByProfesionalFamilyId(proFamily);
		model.addAttribute("serviceList", serviceList);
	    return STUDENT_SERVICES;
	}
	
	
}
