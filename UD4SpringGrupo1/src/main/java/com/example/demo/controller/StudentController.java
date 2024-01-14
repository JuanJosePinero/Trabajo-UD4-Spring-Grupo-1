package com.example.demo.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.ProFamily;
import com.example.demo.entity.Report;
import com.example.demo.entity.Servicio;
import com.example.demo.model.ReportModel;
import com.example.demo.model.ServicioModel;
import com.example.demo.model.StudentModel;
import com.example.demo.service.BusinessService;
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
	@Qualifier("servicioService")
	 private ServicioService servicioService;
	
	
	
	@Autowired
	@Qualifier("servicioService")
	 private ServicioServiceImpl servicioServiceImpl;

	@Autowired
	@Qualifier("studentService")
	private StudentService studentService;
	
	
	
	@GetMapping("/viewServices")
	public String student(@RequestParam("studentUsername") String name, Model model) {

	    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    String nameStudent = ((UserDetails) principal).getUsername();  
        StudentModel student=studentService.getStudentByName(nameStudent);
        int idStudent=student.getId();
   
	    ProFamily proFamily = student.getProfesionalFamily();
		if (proFamily != null) {
		    List<ServicioModel> serviceList = studentService.getServiceByStudentProfesionalFamily(idStudent);
		    
		    model.addAttribute("serviceList", serviceList);
		    model.addAttribute("idStudent",idStudent);
		} else {
		    model.addAttribute("error", "Student does not have any services.");
		}

	    return STUDENT_SERVICES;
	}
	
	@GetMapping("/viewServices/assign/{serviceId}")
	public String studentAssigned(@PathVariable("serviceId") int serviceId, Model model, RedirectAttributes redirectAttributes) {
	    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    String nameStudent = ((UserDetails) principal).getUsername();  
        StudentModel student=studentService.getStudentByName(nameStudent);
        int idStudent=student.getId();   
        servicioService.assignStudent(serviceId, idStudent);
	    ProFamily proFamily = student.getProfesionalFamily();
		if (proFamily != null) {
		    List<ServicioModel> serviceList = studentService.getServiceByStudentProfesionalFamily(idStudent);
		    
		    model.addAttribute("serviceList", serviceList);
		    model.addAttribute("idStudent",idStudent);
		    redirectAttributes.addFlashAttribute("successMessage", "You assign correctly");
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
	    List<ServicioModel> serviceList = servicioService.findByFinishedAndStudentId(idStudent, studentService.model2entity(student));
	    System.out.println("QUEEEE: "+serviceList);
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
	    List<ServicioModel> serviceList = servicioService.findByFinishedAndStudentId(idStudent, studentService.model2entity(student));				    
		model.addAttribute("serviceList", serviceList);
		model.addAttribute("idStudent",idStudent);
	    return STUDENT_VALORATIONS;
	}
	
	@GetMapping("/writeReport/{servicioId}")
	public String studentReport(@PathVariable("servicioId") int servicioId, Model model, @ModelAttribute("reportModel") ReportModel reportModel) {
		Servicio servicio = servicioService.getServicioById(servicioId);
		model.addAttribute("servicio", servicio);
		model.addAttribute("reportModel", reportModel);
		model.addAttribute("servicioId", servicioId);
		return STUDENT_REPORT;
	}

	
	@PostMapping("/writeReport")
	public String sendReport(@ModelAttribute("servicioId") int serviceId,
	                        @ModelAttribute("reportModel") ReportModel reportModel,
	                        Model model, RedirectAttributes redirectAttributes) throws ParseException {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    String username = ((UserDetails) principal).getUsername();
	    StudentModel student = studentService.getStudentByName(username);
	    int studentId = student.getId();

	    Report newReport = servicioService.createReportByServicioId(serviceId, reportModel.getReport(), reportModel.getServiceTime(), studentId);
	    Date d = newReport.getFullDate();
	    Servicio s = servicioService.getServicioById(serviceId);
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    String formattedDate = dateFormat.format(d);
	    s.setHappeningDate(dateFormat.parse(formattedDate));
	    servicioService.updateServicio(servicioServiceImpl.entity2model(s));

	    redirectAttributes.addFlashAttribute("successMessage", "Service finished correctly");
	    return "redirect:/student/viewServices?studentUsername="+username;
	    //
	}
	
}