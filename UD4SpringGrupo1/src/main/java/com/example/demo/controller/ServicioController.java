package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.ProFamily;
import com.example.demo.model.ServicioModel;
import com.example.demo.service.ProFamilyService;
import com.example.demo.service.ServicioService;

@Controller
@RequestMapping("/servicio")
public class ServicioController {

	private static final String ADD_SERVICIO_VIEW = "business/addServicio";
	private static final String EDIT_SERVICIO_VIEW = "business/editServicio";
	
	@Autowired
	@Qualifier("servicioService")
	 private ServicioService servicioService;
	
	@Autowired
	@Qualifier("proFamilyService")
    private ProFamilyService proFamilyService;
	
	@GetMapping("/addServicio")
	public String addServicio(Model model) {
		List<ProFamily> profesionalFamilies = proFamilyService.getAllProfesionalFamilies();
		model.addAttribute("servicioModel", new ServicioModel());
		model.addAttribute("profesionalFamilies", profesionalFamilies);
		return ADD_SERVICIO_VIEW;
	}
	
	@PostMapping("/addServicio")
	public String addServicioPost(Model model, ServicioModel servicioModel) {
		model.addAttribute("servicioModel", new ServicioModel());
		servicioService.addServicio(servicioModel);
		return ADD_SERVICIO_VIEW;
	}
	
//	@GetMapping("/editStudent/{studentId}")
//	public String editStudent(@PathVariable("studentId") int studentId, Model model) {
//		List<ProFamily> proFamilyList = proFamilyRepository.findAll();
//		model.addAttribute("profesionalFamilies", proFamilyList); 
//		StudentModel student = studentService.getStudentById(studentId);
//		model.addAttribute("student", student); 
//	    return EDIT_STUDENT_VIEW;
//	}
//	
//	@PostMapping("/editStudent")
//    public String saveEditedStudent(@ModelAttribute StudentModel studentModel) {
//        studentService.updateStudent(studentModel);
//        return "redirect:/admin/adminScreen";
//    }
	
	@PostMapping("/deleteServicio/{servicioId}")
	public String delete(@PathVariable("servicioId") int servicioId, Model model) {
		servicioService.deleteServicio(servicioId);
	    return "redirect:/business/home";
	}
	
}
