package com.example.demo.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Business;
import com.example.demo.entity.ProFamily;
import com.example.demo.entity.Report;
import com.example.demo.entity.Servicio;
import com.example.demo.model.BusinessModel;
import com.example.demo.model.ServicioModel;
import com.example.demo.model.StudentModel;
import com.example.demo.service.BusinessService;
import com.example.demo.service.ProFamilyService;
import com.example.demo.service.ReportService;
import com.example.demo.service.ServicioService;
import com.example.demo.service.StudentService;
import com.example.demo.service.impl.ServicioServiceImpl;

@Controller
@RequestMapping("business")
public class BusinessController {

	private static final String BUSINESS_VIEW = "/admin/business";
	private static final String EDIT_BUSINESS_VIEW = "admin/editBusiness";
	private static final String ADD_BUSINESS_VIEW = "admin/addBusiness";
	private static final String BUSINESS_HOME_VIEW = "/business/businessHome";
	private static final String BUSINESS_REPORT_VIEW = "/business/businessReport";
	private static final String BUSINESS_RATED_SERVICES_VIEW = "/business/ratedServices";

	@Autowired
	@Qualifier("businessService")
	private BusinessService businessService;

	@Autowired
	@Qualifier("reportService")
	private ReportService reportService;

	@Autowired
	@Qualifier("servicioService")
	private ServicioService servicioService;
	
	@Autowired
	@Qualifier("servicioService")
	private ServicioServiceImpl servicioServiceImpl;
	
	@Autowired
	@Qualifier("proFamilyService")
	private ProFamilyService proFamilyService;

	@Autowired
	@Qualifier("studentService")
	private StudentService studentService;

	@GetMapping("/list")
	public String business(Model model, @RequestParam(name="filterBy", required=false, defaultValue="null") String filterBy) {
		
		 List<Business> businessList = businessService.getBusinessList(filterBy);
		    model.addAttribute("business1", businessList);
		    return BUSINESS_VIEW;
	}
	
//	@GetMapping("/list/orderByServiceAmount")
//	public String orderByServiceAmount(Model model) {
//		List<Business> businessList = businessService.getBusinessOrderedByServiceAmount();
//		model.addAttribute("business1", businessList);
//		return BUSINESS_VIEW;
//	}
//	
//	@GetMapping("/list/orderByServiceFinished")
//	public String orderByServiceFinsihed(Model model) {
//		List<Business> businessList = businessService.getBusinessOrderedByServiceFinished();
//		model.addAttribute("business1", businessList);
//		return BUSINESS_VIEW;
//	}

	@GetMapping("/addBusiness")
	public String addBusiness(Model model) {
		model.addAttribute("businessModel", new BusinessModel());
		List<StudentModel> studentEmails = studentService.listAllStudents();
		model.addAttribute("studentEmails", studentEmails);
		return ADD_BUSINESS_VIEW;
	}

	@PostMapping("/addBusiness")
	public String addEmpresa(@ModelAttribute("empresa") BusinessModel businessModel,
			@RequestParam("logoImagen") MultipartFile file, @RequestParam("logo") String logoName,
			RedirectAttributes flash) {

		if (businessModel != null) {
			if (businessModel.getName().isEmpty() || businessModel.getAddress().isEmpty()
					|| businessModel.getEmail().isEmpty() || businessModel.getPhone().isEmpty()) {
				flash.addFlashAttribute("error", "Some fields are empty");
				return "redirect:/business/addBusiness";
			} else {

				String projectDir = System.getProperty("user.dir");

				String uploadDir = projectDir + "/src/main/resources/static/imgs/business/";

				try {

					File uploadDirFile = new File(uploadDir);
					if (!uploadDirFile.exists()) {
						uploadDirFile.mkdirs();
					}
					file.transferTo(new File(uploadDir + logoName));
				} catch (IOException e) {
					e.printStackTrace();
				}
				StudentModel student = studentService.getStudentByEmail(businessModel.getEmail());
				if (student != null) {
					student.setRole("ROLE_BUSINESS");
					student.setEnabled(1);
					student.setDeleted(0);
					
					studentService.updateStudent(student);
				}
				businessModel.setLogo(logoName);

				Business business = businessService.model2entity(businessModel);
				businessService.saveBusiness(business);
				flash.addFlashAttribute("success", "Business created succesfully");
			}
		}
		return "redirect:/business/list";
	}

	@GetMapping("/editBusiness/{businessID}")
	public String editBusiness(@PathVariable("businessID") int businessId, Model model) {
		Business business = businessService.getBusinessById(businessId);
		model.addAttribute("businessModel", business);
		return EDIT_BUSINESS_VIEW;
	}

	@PostMapping("/editBusiness")
	public String saveEditedBusiness(@ModelAttribute BusinessModel businessModel, RedirectAttributes flash,
			@RequestParam("logoImagen") MultipartFile file, @RequestParam("logo") String logoName) {
		if (businessModel.getId() > 0) {
			Business business = businessService.getBusinessById(businessModel.getId());

			if (business != null) {

				String oldLogoName = business.getLogo();

				business.setName(businessModel.getName());
				business.setAddress(businessModel.getAddress());
				business.setEmail(businessModel.getEmail());
				business.setPhone(businessModel.getPhone());

				String projectDir = System.getProperty("user.dir");
				String uploadDir = projectDir + "/src/main/resources/static/imgs/business/";

				try {
					File uploadDirFile = new File(uploadDir);
					if (!uploadDirFile.exists()) {
						uploadDirFile.mkdirs();
					}

					file.transferTo(new File(uploadDir + logoName));
				} catch (IOException e) {
					e.printStackTrace();
				}

				business.setLogo(logoName);

				Business updatedBusiness = businessService.updateBusiness(business);

				if (updatedBusiness != null) {

					if (oldLogoName != null && !oldLogoName.equals(logoName)) {
						String oldLogoPath = uploadDir + oldLogoName;
						File oldLogoFile = new File(oldLogoPath);
						if (oldLogoFile.exists()) {
							oldLogoFile.delete();
						}
					}

					flash.addFlashAttribute("success", "Business updated successfully!");
				} else {
					flash.addFlashAttribute("error", "Failed to update Business.");
				}
			} else {
				flash.addFlashAttribute("error", "Business not found.");
			}
		} else {
			flash.addFlashAttribute("error", "Invalid Business ID.");
		}

		return "redirect:/business/list";
	}

	@PostMapping("/deleteBusiness/{businessId}")
	public String delete(@PathVariable("businessId") int businessId, Model model, RedirectAttributes flash) {
		Business business = businessService.getBusinessById(businessId);

		if (business != null) {

			String imageName = business.getLogo();

			businessService.deleteBusiness(businessId);

			if (imageName != null && !imageName.isEmpty()) {
				String projectDir = System.getProperty("user.dir");
				String imagePath = projectDir + "/src/main/resources/static/imgs/business/" + imageName;
				File imageFile = new File(imagePath);

				if (imageFile.exists()) {
					imageFile.delete();
				}
			}
			flash.addFlashAttribute("success", "Business updated successfully!");
			return "redirect:/business/list";
		} else {

			return "redirect:/business/list";
		}
	}
	
	@GetMapping("/home")
    public String getBusiness(@RequestParam(name="opcion", required=false, defaultValue="0") String opcion, 
                              @RequestParam(name="filterBy", required=false, defaultValue="null") String filterBy,
                              @RequestParam(name="startDate", required=false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                              @RequestParam(name="endDate", required=false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
                              Model model) {
        return processBusiness(opcion, filterBy, startDate, endDate, model);
    }

    @PostMapping("/home")
    public String postBusiness(@RequestParam(name="opcion", required=false, defaultValue="0") String opcion, 
                               @RequestParam(name="filterBy", required=false, defaultValue="null") String filterBy,
                               @RequestParam(name="startDate", required=false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                               @RequestParam(name="endDate", required=false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
                               Model model) {
        return processBusiness(opcion, filterBy, startDate, endDate, model);
    }

    private String processBusiness(String opcion, String filterBy,Date startDate,Date endDate, Model model) {
    	
    	System.out.println("QUE PASA cn la opcion "+opcion);
    	System.out.println("QUE PASA cn el filtro "+filterBy);
    	
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) principal).getUsername();
        StudentModel student = studentService.getStudentByName(username);
        String email = student.getEmail();
        Business business = businessService.getIdByEmail(email);
        List<ProFamily> profesionalFamilies = proFamilyService.getAllNotEmpty();
        model.addAttribute("profesionalFamilies", profesionalFamilies);
        
        List<ServicioModel> listServicios = servicioService.getFilteredServices(opcion, filterBy,startDate,endDate);
        model.addAttribute("servicio", listServicios);
        model.addAttribute("business", business);
       
        return BUSINESS_HOME_VIEW;
    }
	
    @PostMapping("/reports")
    public String reportsPost(@RequestParam(name = "opcion", required = false, defaultValue = "0") String opcion, Model model) {
        return processReports(opcion, model);
    }

    @GetMapping("/reports")
    public String reports(@RequestParam(name = "opcion", required = false, defaultValue = "0") String opcion, Model model) {
        return processReports(opcion, model);
    }


    private String processReports(String opcion, Model model) {
    	
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) principal).getUsername();

        StudentModel student = studentService.getStudentByName(username);
        String email = student.getEmail();
        Business business = businessService.getIdByEmail(email);

        List<ProFamily> profesionalFamilies = proFamilyService.getAllNotEmpty();
        model.addAttribute("profesionalFamilies", profesionalFamilies);

        if (Integer.parseInt(opcion) != 0) {
            ProFamily profam = proFamilyService.findById(Integer.parseInt(opcion));
            List<Report> reports = reportService.findReportsByServiceProFamily(profam.getName(), business);
            model.addAttribute("report", reports);
            List<Servicio> businessIdServices = reports.stream().map(report -> report.getServicioId()).collect(Collectors.toList());
            model.addAttribute("businessIdServices", businessIdServices);

        } else {
            List<Report> reports = servicioService.getReportsForServicesByBusinessId(business);
            model.addAttribute("report", reports);
            List<Servicio> businessIdServices = reports.stream().map(report -> report.getServicioId()).collect(Collectors.toList());
            model.addAttribute("businessIdServices", businessIdServices);
        }
        model.addAttribute("username", username);

        return BUSINESS_REPORT_VIEW;
    }
    
    @GetMapping("/ratedServicios")
    public String ratedServicios(@RequestParam(name = "opcion", required = false, defaultValue = "0") String opcion, Model model) {
        return processRatedServicios(opcion, model);
    }

    @PostMapping("/ratedServicios")
    public String ratedServiciosPost(@RequestParam(name = "opcion", required = false, defaultValue = "0") String opcion, Model model) {
        return processRatedServicios(opcion, model);
    }

    private String processRatedServicios(String opcion, Model model) {
    	
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) principal).getUsername();

        StudentModel student = studentService.getStudentByName(username);
        String email = student.getEmail();
        Business business = businessService.getIdByEmail(email);
        List<ProFamily> profesionalFamilies = proFamilyService.getAllNotEmpty();
        model.addAttribute("profesionalFamilies", profesionalFamilies);

        if (Integer.parseInt(opcion) != 0) {
            ProFamily profam = proFamilyService.findById(Integer.parseInt(opcion));
            List<ServicioModel> servicios = servicioService.findByValorationIsNotNullAndBusinessIdAndProfesionalFamilyId(business, profam);
            model.addAttribute("servicio", servicios);
        } else {
            List<ServicioModel> servicios = servicioService.getServicesByBusinessId(business);
            model.addAttribute("servicio", servicios);
        }

        return BUSINESS_RATED_SERVICES_VIEW;
    }
	
	

}
