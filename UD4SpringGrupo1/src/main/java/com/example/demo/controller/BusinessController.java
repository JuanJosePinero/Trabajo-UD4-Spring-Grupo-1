package com.example.demo.controller;

import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Business;
import com.example.demo.entity.Servicio;
import com.example.demo.model.BusinessModel;
import com.example.demo.repository.BusinessRepository;
import com.example.demo.service.BusinessService;
import com.example.demo.service.FilesStorageService;
import com.example.demo.service.ServicioService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@Controller
@RequestMapping("business")
public class BusinessController {

	private static final String BUSINESS_VIEW = "/admin/business";
	private static final String EDIT_BUSINESS_VIEW = "/admin/editBusiness";
	private static final String ADD_BUSINESS_VIEW = "/admin/addBusiness";
	private static final String BUSINESS_HOME_VIEW = "/business/businessHome";
	
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
	@Qualifier("filesStorageService")
	 private FilesStorageService storageService;
	
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
	
//	@PostMapping("/addBusiness")
//	public String saveAddBusiness(@ModelAttribute BusinessModel businessModel, RedirectAttributes flash) {
//		businessService.addBusiness(businessModel);
//	    flash.addFlashAttribute("success", "Business registered succesfully!");
//	    return "redirect:/business/list";
//	}
	
	@PostMapping("/addBusiness")
	public String saveAddBusiness(@ModelAttribute BusinessModel businessModel,
	                              @RequestParam("logo") MultipartFile file,
	                              BindingResult result,
	                              RedirectAttributes flash,
	                              HttpServletResponse response) {

	    if (result.hasErrors()) {
	        flash.addFlashAttribute("error", "Validation failed");
	        return "redirect:/business/list";
	    }

	    try {
	        if (!file.isEmpty()) {
	            String imageName = storageService.save(file);
	            String imageUrl = MvcUriComponentsBuilder
	                .fromMethodName(FileUploadController.class, "serveFile", imageName)
	                .build().toUriString();
	            businessModel.setLogo(imageUrl);
	        }

	        businessService.addBusiness(businessModel);

	        flash.addFlashAttribute("success", "Business registered successfully!");
	    } catch (Exception e) {
	        e.printStackTrace();
	        flash.addFlashAttribute("error", "Error saving business");
	        System.out.println("LLEGAS A CATCH");
	    }

	    return "redirect:/business/list";
	}


	@GetMapping("/editBusiness/{businessId}")
	public String editBusiness(@PathVariable("businessId") int businessId, Model model) {
	    Business business = businessRepository.findById(businessId).orElse(null);

	    if (business != null) {
	        BusinessModel businessModel = new BusinessModel();
	        businessModel.setId(business.getId());
	        businessModel.setName(business.getName());
	        businessModel.setAddress(business.getAddress());
	        businessModel.setEmail(business.getEmail());
	        businessModel.setPhone(business.getPhone());
	        

	        model.addAttribute("businessModel", businessModel);
	    }

	    return EDIT_BUSINESS_VIEW;
	}

	@PostMapping("/editBusiness")
	public String saveEditedBusiness(@ModelAttribute BusinessModel businessModel, RedirectAttributes flash) {
	    if (businessModel.getId() > 0) {
	        Business business = businessRepository.findById(businessModel.getId()).orElse(null);

	        if (business != null) {
	            business.setName(businessModel.getName());
	            business.setAddress(businessModel.getAddress());
	            business.setEmail(businessModel.getEmail());
	            business.setPhone(businessModel.getPhone());
	            

	            Business updatedBusiness = businessService.updateBusiness(business);

	            if (updatedBusiness != null) {
	                flash.addFlashAttribute("success", "Business updated successfully!");
	                System.out.println("Success: Business updated. ID: " + updatedBusiness.getId() + ", Name: " + updatedBusiness.getName());
	            } else {
	                flash.addFlashAttribute("error", "Failed to update Business.");
	                System.out.println("Failed: Unable to update Business.");
	            }
	        } else {
	            flash.addFlashAttribute("error", "Business not found.");
	            System.out.println("Failed: Business not found.");
	        }
	    } else {
	        flash.addFlashAttribute("error", "Invalid Business ID.");
	        System.out.println("Failed: Invalid Business ID.");
	    }

	    return "redirect:/business/list";
	}

	@PostMapping("/deleteBusiness/{businessId}")
	public String delete(@PathVariable("businessId") int businessId, Model model) {
		businessService.deleteBusiness(businessId);
	    
	    return "redirect:/business/list";
	}
	
	
	@GetMapping("/home")
	public String Business(Model model) {
		List<Servicio> servicios = servicioService.getAllServicios();
        model.addAttribute("servicio", servicios);
	    return BUSINESS_HOME_VIEW;
	}

}
