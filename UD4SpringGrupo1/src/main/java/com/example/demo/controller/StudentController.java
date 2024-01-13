package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.ProFamily;
import com.example.demo.entity.Report;
import com.example.demo.entity.Servicio;
import com.example.demo.model.ReportModel;
import com.example.demo.model.ServicioModel;
import com.example.demo.model.StudentModel;
import com.example.demo.repository.BusinessRepository;
import com.example.demo.repository.ProFamilyRepository;
import com.example.demo.repository.ServicioRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.BusinessService;
import com.example.demo.service.ProFamilyService;
import com.example.demo.service.ReportService;
import com.example.demo.service.ServicioService;
import com.example.demo.service.StudentService;
import com.example.demo.service.impl.ServicioServiceImpl;

@Controller
@RequestMapping("/student")
public class StudentController {

	private static final String STUDENT_SERVICES = "/student/student.html";
	private static final String STUDENT_REPORT = "/student/studentReport.html";
	private static final String STUDENT_COMMENTS = "/student/studentComments.html";
	private static final String STUDENT_VALORATIONS = "/student/studentValorations.html";

	
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
	@Qualifier("servicioService")
	 private ServicioServiceImpl servicioServiceImpl;
	
	@Autowired
	@Qualifier("servicioRepository")
	 private ServicioRepository servicioRepository;
	
	@Autowired
	@Qualifier("reportService")
	private ReportService reportService;
	
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
	
	
	@GetMapping("/viewServices")
	public String student(@RequestParam("studentUsername") String name, Model model) {
	    System.out.println("Nombre: " + name);

	    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    String nameStudent = ((UserDetails) principal).getUsername();  
        StudentModel student=studentService.getStudentByName(nameStudent);
        int idStudent=student.getId();
        System.out.println("idStudent: "+idStudent);
   
	    ProFamily proFamily = student.getProfesionalFamily();
	    System.out.println("famlia: "+proFamily);
		if (proFamily != null) {
		    List<ServicioModel> serviceList = studentService.getServiceByStudentProfesionalFamily(idStudent);
		    
		    model.addAttribute("serviceList", serviceList);
		    model.addAttribute("idStudent",idStudent);
		} else {
		    model.addAttribute("error", "Student does not have any services.");
		}

	    return STUDENT_SERVICES;
	}
	
	@GetMapping("/viewComments")
	public String studentComments(@RequestParam("studentUsername") String name, Model model) {
	    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    String nameStudent = ((UserDetails) principal).getUsername();		       
        StudentModel student=studentService.getStudentByName(nameStudent);	        
        int idStudent=student.getId();
	    List<Servicio> serviceList = servicioRepository.findByFinishedAndStudentId(1 , studentRepository.findById(idStudent));			    
		model.addAttribute("serviceList", serviceList);
		model.addAttribute("idStudent",idStudent);
	    return STUDENT_COMMENTS;
	}
	
	@GetMapping("/viewValorations")
	public String studentValorations(@RequestParam("studentUsername") String name, Model model) {
	    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    String nameStudent = ((UserDetails) principal).getUsername();		       
        StudentModel student=studentService.getStudentByName(nameStudent);	        
        int idStudent=student.getId();
	    List<Servicio> serviceList = servicioRepository.findByFinishedAndStudentId(1 , studentRepository.findById(idStudent));			    
		model.addAttribute("serviceList", serviceList);
		model.addAttribute("idStudent",idStudent);
	    return STUDENT_VALORATIONS;
	}
	
	@GetMapping("/writeReport/{servicioId}")
	public String studentReport(@PathVariable("servicioId") int servicioId, Model model, @ModelAttribute("reportModel") ReportModel reportModel) {
		Servicio servicio = servicioRepository.findById(servicioId);
		model.addAttribute("servicio", servicio);
		model.addAttribute("reportModel", reportModel);
		model.addAttribute("servicioId", servicioId);
		return STUDENT_REPORT;
	}

	
	@PostMapping("/writeReport")
	public String sendReport(@ModelAttribute("servicioId") int serviceId,
	                        @ModelAttribute("reportModel") ReportModel reportModel,
	                        Model model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    String username = ((UserDetails) principal).getUsername();
	    System.out.println("UserId " + username);
	    StudentModel student = studentService.getStudentByName(username);
	    int studentId = student.getId();

	    Report newReport = servicioService.createReportByServicioId(serviceId, reportModel.getReport(), reportModel.getServiceTime(), studentId);

	    if (newReport != null) {
	        return "redirect:/student/viewServices";
	    } else {
	    	System.out.println("No te cambio nada tonto");
	    	return "redirect:/student/viewServices";
	    }
	}	
	
}
