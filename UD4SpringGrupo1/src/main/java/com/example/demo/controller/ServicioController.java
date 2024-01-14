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

import com.example.demo.entity.Business;
import com.example.demo.entity.ProFamily;
import com.example.demo.entity.Servicio;
import com.example.demo.model.ServicioModel;
import com.example.demo.model.StudentModel;
import com.example.demo.repository.ProFamilyRepository;
import com.example.demo.repository.ServicioRepository;
import com.example.demo.service.BusinessService;
import com.example.demo.service.ProFamilyService;
import com.example.demo.service.ServicioService;
import com.example.demo.service.StudentService;

@Controller
@RequestMapping("/servicio")
public class ServicioController {

	private static final String ADD_SERVICIO_VIEW = "business/addServicio";
	private static final String EDIT_SERVICIO_VIEW = "business/editServicio";
	private static final String RATE_SERVICIO_VIEW = "business/rateServicio";
	private static final String COMMENT_SERVICIO_VIEW = "business/commentServicio";
	@Autowired
	@Qualifier("servicioService")
	 private ServicioService servicioService;

	@Autowired
	@Qualifier("studentService")
    private StudentService studentService;
	
	@Autowired
	@Qualifier("businessService")
    private BusinessService businessService;
	
	@Autowired
	@Qualifier("proFamilyRepository")
	 private ProFamilyRepository proFamilyRepository;
	
	@Autowired
	@Qualifier("servicioRepository")
	 private ServicioRepository servicioRepository;
	
	@GetMapping("/addServicio")
	public String addServicio(Model model) {
	    List<ProFamily> profesionalFamilies = proFamilyRepository.findAll();
	    model.addAttribute("servicioModel", new ServicioModel());
	    model.addAttribute("profesionalFamilies", profesionalFamilies);
	    return ADD_SERVICIO_VIEW;
	}

	@PostMapping("/addServicio")
	public String addServicioPost(@ModelAttribute ServicioModel servicioModel, Model model) {
	    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    String username = ((UserDetails) principal).getUsername();
	    System.out.println("UserId "+username);
	    StudentModel student = studentService.getStudentByName(username);
	    String email = student.getEmail();
	    System.out.println("EMAIL STUDENT"+email);
	    
	    Business business = businessService.getIdByEmail(email);
	    System.out.println("BUSIUNESSSSSSSSSSSSSS"+business);

	    servicioModel.setBusinessId(business);

	    model.addAttribute("servicioModel", servicioModel);

	    model.addAttribute("business", business);

	    servicioService.addServicio(servicioModel);

	    return "redirect:/business/home";
	}

	@GetMapping("/rateServicio/{servicioId}")
	public String rateStudent(@PathVariable("servicioId") int servicioId, Model model) {
	    Servicio servicio = servicioRepository.findById(servicioId);
	    model.addAttribute("servicio", servicio);
	    model.addAttribute("servicioId", servicioId);
	    return RATE_SERVICIO_VIEW;
	}

	@PostMapping("/rateServicio")
	public String saveRatedServicio(@ModelAttribute("servicioId") int servicioId, @ModelAttribute("valoration") float valoration) {
	    servicioService.rateServicio(servicioId, valoration);
	    return "redirect:/business/home";
	}
	
	@GetMapping("/commentServicio/{servicioId}")
	public String commentServicio(@PathVariable("servicioId") int servicioId, Model model) {
	    Servicio servicio = servicioRepository.findById(servicioId);
	    model.addAttribute("servicio", servicio);
	    model.addAttribute("servicioId", servicioId);
	    return COMMENT_SERVICIO_VIEW;
	}

	@PostMapping("/commentServicio")
	public String saveCommentServicio(@ModelAttribute("servicioId") int servicioId, @ModelAttribute("comment") String comment) {
	    servicioService.commentServicio(servicioId, comment);
	    return "redirect:/business/home";
	}
	@GetMapping("/editServicio/{servicioId}")
	public String editStudent(@PathVariable("servicioId") int servicioId, Model model) {
		Servicio servicio = servicioRepository.findById(servicioId);
		List<ProFamily> profesionalFamilies = proFamilyRepository.findAll();
	    model.addAttribute("profesionalFamilies", profesionalFamilies);
		model.addAttribute("servicio", servicio); 
	    return EDIT_SERVICIO_VIEW;
	}
	
	@PostMapping("/editServicio")
    public String saveEditedServicio(@ModelAttribute ServicioModel servicioModel) {
		servicioService.updateServicio(servicioModel);
        return "redirect:/business/home";
    }
	
	@PostMapping("/deleteServicio/{servicioId}")
	public String delete(@PathVariable("servicioId") int servicioId, Model model) {
		servicioService.deleteServicio(servicioId);
	    return "redirect:/business/home";
	}
	
}
