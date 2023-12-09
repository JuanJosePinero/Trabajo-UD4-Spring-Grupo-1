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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Business;
import com.example.demo.model.BusinessModel;
import com.example.demo.repository.BusinessRepository;
import com.example.demo.service.BusinessService;

@Controller
@RequestMapping("business")
public class BusinessController {

	private static final String BUSINESS_VIEW = "/admin/business";
	private static final String EDIT_BUSINESS_VIEW = "/admin/editBusiness";
	private static final String ADD_BUSINESS_VIEW = "/admin/addBusiness";
	
	@Autowired
	@Qualifier("businessService")
	 private BusinessService businessService;
	
	@Autowired
	@Qualifier("businessRepository")
	 private BusinessRepository businessRepository;
	
	@GetMapping("/list")
	public String business(Model model) {
		List<Business> businessList = businessRepository.findAll();
		model.addAttribute("business1", businessList); 
	    return BUSINESS_VIEW;
	}
	
	@GetMapping("/addBusiness")
	public String addBusiness() {
	    return ADD_BUSINESS_VIEW;
	}
	
	@PostMapping("/addBusiness")
	public String saveAddBusiness(@ModelAttribute BusinessModel businessModel, RedirectAttributes flash) {
		businessService.addBusiness(businessModel);
	    flash.addFlashAttribute("success", "Business registered succesfully!");
	    return "redirect:/business/list";
	}
	
	@GetMapping("/editBusiness/{businessId}")
	public String editBusiness(@PathVariable("businessId") int businessId, Model model) {
		List<Business> businessList = businessRepository.findAll();
		model.addAttribute("business", businessList); 
	    return EDIT_BUSINESS_VIEW;
	}
	
//	@PostMapping("/editBusiness")
//    public String saveEditedStudent(@ModelAttribute BusinessModel businessModel) {
//        businessService.updateBusiness(businessModel);
//        return "redirect:/adminScreen";
//    }

	@PostMapping("/deleteBusiness/{businessId}")
	public String delete(@PathVariable("businessId") int businessId, Model model) {
		businessService.deleteBusiness(businessId);
	    
	    return "redirect:/business/list";
	}

}

