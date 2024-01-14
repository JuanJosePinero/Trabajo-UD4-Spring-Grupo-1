package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.ProFamily;
import com.example.demo.model.StudentModel;
import com.example.demo.service.ProFamilyService;
import com.example.demo.service.StudentService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	private static final String ADMIN_VIEW = "admin/adminScreen";
	private static final String EDIT_STUDENT_VIEW = "admin/editStudent";
	
	@Autowired
	@Qualifier("studentService")
	 private StudentService studentService;
	
	@Autowired
	@Qualifier("proFamilyService")
	 private ProFamilyService proFamilyService;
	
	@GetMapping("/adminScreen")
	public String adminScreen(Model model) {
	    List<StudentModel> students = studentService.listAllStudents();
	    model.addAttribute("students", students);
	    return ADMIN_VIEW;
	}

	
	@GetMapping("/editStudent/{studentId}")
	public String editStudent(@PathVariable("studentId") int studentId, Model model) {
		List<ProFamily> proFamilyList = proFamilyService.getAll();
		model.addAttribute("profesionalFamilies", proFamilyList); 
		StudentModel student = studentService.getStudentById(studentId);
		model.addAttribute("student", student); 
	    return EDIT_STUDENT_VIEW;
	}
	
	@PostMapping("/editStudent")
    public String saveEditedStudent(@ModelAttribute StudentModel studentModel) {
        studentService.updateStudent(studentModel);
        return "redirect:/admin/adminScreen";
    }
	
	@PostMapping("/enabled/{studentId}")
	public String enable(@PathVariable("studentId") int studentId, Model model) {
		
		studentService.enableStudent(studentId);
		    
	    return "redirect:/admin/adminScreen";
	}
	
	@PostMapping("/deleteStudent/{studentId}")
	public String delete(@PathVariable("studentId") int studentId, Model model) {
		studentService.deleteStudent(studentId);
	    
	    return "redirect:/admin/adminScreen";
	}

}
