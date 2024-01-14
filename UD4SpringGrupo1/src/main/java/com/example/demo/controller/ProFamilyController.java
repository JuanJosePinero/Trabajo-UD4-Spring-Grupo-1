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

import com.example.demo.entity.ProFamily;
import com.example.demo.model.ProFamilyModel;
import com.example.demo.repository.ProFamilyRepository;
import com.example.demo.service.ProFamilyService;

@Controller
@RequestMapping("proFamily")
public class ProFamilyController {

	private static final String PRO_FAMILY_VIEW = "/admin/proFamily";
	private static final String EDIT_PRO_FAMILY_VIEW = "/admin/editProFamily";
	private static final String ADD_PRO_FAMILY_VIEW = "/admin/addProFamily";
	
	@Autowired
	@Qualifier("proFamilyService")
	 private ProFamilyService proFamilyService;
	
	@Autowired
	@Qualifier("proFamilyRepository")
	 private ProFamilyRepository proFamilyRepository;
	
	@GetMapping("/list")
	public String proFamily(Model model) {
		List<ProFamily> proFamilyList = proFamilyRepository.findAll();
		model.addAttribute("profesionalFamilies", proFamilyList); 
	    return PRO_FAMILY_VIEW;
	}
	
	@GetMapping("/addProFamily")
	public String addProFamily() {
	    return ADD_PRO_FAMILY_VIEW;
	}
	
	@PostMapping("/addProFamily")
	public String saveAddProFamily(@ModelAttribute ProFamilyModel proFamilyModel, RedirectAttributes flash) {
	    proFamilyService.addProFamily(proFamilyModel);
	    flash.addFlashAttribute("success", "ProFamily registered succesfully!");
	    return "redirect:/proFamily/list";
	}
	
	@GetMapping("/editProFamily/{proFamilyId}")
	public String editProFamily(@PathVariable("proFamilyId") int proFamilyId, Model model) {
	    ProFamily proFamily = proFamilyRepository.findById(proFamilyId);

	    if (proFamily != null) {
	        ProFamilyModel proFamilyModel = new ProFamilyModel();
	        proFamilyModel.setId(proFamily.getId());
	        proFamilyModel.setName(proFamily.getName());
	        model.addAttribute("proFamilyModel", proFamilyModel);
	    }

	    return EDIT_PRO_FAMILY_VIEW;
	}

	@PostMapping("/editProFamily")
	public String saveEditedProFamily(@ModelAttribute ProFamilyModel proFamilyModel, RedirectAttributes flash) {
	    if (proFamilyModel.getId() > 0) {
	        ProFamily proFamily = proFamilyRepository.findById(proFamilyModel.getId());

	        if (proFamily != null) {
	            proFamily.setName(proFamilyModel.getName());
	            ProFamily updatedProFamily = proFamilyService.updateProFamily(proFamily);

	            if (updatedProFamily != null) {
	                flash.addFlashAttribute("success", "ProFamily updated successfully!");
	            } else {
	                flash.addFlashAttribute("error", "Failed to update ProFamily.");
	            }
	        } else {
	            flash.addFlashAttribute("error", "ProFamily not found.");
	        }
	    } else {
	        flash.addFlashAttribute("error", "Invalid ProFamily ID.");
	    }

	    return "redirect:/proFamily/list";
	}
	@PostMapping("/deleteProFamily/{proFamilyId}")
	public String delete(@PathVariable("proFamilyId") int proFamilyId, Model model) {
		proFamilyService.deleteProFamily(proFamilyId);
	    
	    return "redirect:/proFamily/list";
	}

}