package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.ProFamily;
import com.example.demo.entity.Servicio;
import com.example.demo.entity.Student;
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
	@GetMapping("/viewServices")
	public String student(@RequestParam("studentUsername") String name, Model model) {
	    System.out.println("Nombre: " + name);

	    // Aquí deberías verificar si el estudiante existe antes de intentar acceder a su familia profesional.
	    // Esto es para evitar NullPointerException si no se encuentra el estudiante en el repositorio.
	    Student student = studentRepository.findByName(name);
	    if (student != null) {
	        ProFamily proFamily = student.getProfesionalFamily();
	        if (proFamily != null) {
	            List<Servicio> serviceList = servicioRepository.findByProfesionalFamilyId(proFamily);
	            model.addAttribute("serviceList", serviceList);
	        } else {
	            // Manejar el caso en el que el estudiante no tiene una familia profesional asignada.
	            // Puedes agregar un mensaje al modelo o manejarlo de alguna otra manera según tus necesidades.
	            model.addAttribute("error", "Student does not have a professional family assigned.");
	        }
	    } else {
	        // Manejar el caso en el que el estudiante no se encuentra en el repositorio.
	        model.addAttribute("error", "Student not found.");
	    }

	    return STUDENT_SERVICES;
	}


	
	
}
