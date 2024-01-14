package com.example.demo.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Business;
import com.example.demo.entity.ProFamily;
import com.example.demo.entity.Report;
import com.example.demo.entity.Servicio;
import com.example.demo.entity.Student;
import com.example.demo.model.BusinessModel;
import com.example.demo.model.ServicioModel;
import com.example.demo.model.StudentModel;
import com.example.demo.repository.BusinessRepository;
import com.example.demo.repository.ProFamilyRepository;
import com.example.demo.repository.ReportRepository;
import com.example.demo.repository.ServicioRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.BusinessService;
import com.example.demo.service.FilesStorageService;
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
	@Qualifier("proFamilyRepository")
	private ProFamilyRepository proFamilyRepository;
	
	@Autowired
	@Qualifier("proFamilyService")
	private ProFamilyService proFamilyService;

	@Autowired
	@Qualifier("studentRepository")
	private StudentRepository studentRepository;

	@Autowired
	@Qualifier("reportRepository")
	private ReportRepository reportRepository;

	@Autowired
	@Qualifier("studentService")
	private StudentService studentService;

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
	public String addBusiness(Model model) {
		model.addAttribute("businessModel", new BusinessModel());
		List<Student> studentEmails = studentRepository.findAll();
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
				Student student = studentRepository.findByEmail(businessModel.getEmail());
				if (student != null) {
					student.setRole("ROLE_BUSINESS");
					student.setEnabled(1);
					student.setDeleted(0);
					StudentModel studentBusiness = studentService.entity2model(student);
					studentService.updateStudent(studentBusiness);
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
			Business business = businessRepository.findById(businessModel.getId()).orElse(null);

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
					System.out.println("Success: Business updated. ID: " + updatedBusiness.getId() + ", Name: "
							+ updatedBusiness.getName());
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
	public String Business(@RequestParam(name="opcion", required=false, defaultValue="0") String opcion, 
	                       @RequestParam(name="filterBy", required=false, defaultValue="null") String filterBy, 
	                       Model model) {
	    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    String username = ((UserDetails) principal).getUsername();
	    System.out.println("UserId " + username);
	    StudentModel student = studentService.getStudentByName(username);
	    String email = student.getEmail();
	    System.out.println("EMAIL STUDENT" + email);
	    Business business = businessService.getIdByEmail(email);
	    System.out.println("BUSIUNESSSSSSSSSSSSSS" + business);

	    List<ProFamily> profesionalFamilies = proFamilyService.getAll();
	    model.addAttribute("profesionalFamilies", profesionalFamilies);

	    System.out.println("OPCION: " + opcion);
	    
	    if (!filterBy.equals("null")) {
	    	if (filterBy.equals("finishedServices")) {
	            List<ServicioModel> listServicios;
	            if (Integer.parseInt(opcion) != 0) {
	                ProFamily profam = proFamilyService.findById(Integer.parseInt(opcion));
	                String proFamName = profam.getName();
	                System.out.println("proFamName: " + proFamName);
	                listServicios = servicioService.getFinishedServiciosByProFamily(proFamName);
	            } else {
	                listServicios = servicioService.getFinishedServicios();
	            }
	            System.out.println("getFinishedServicios -- " + listServicios);
	            model.addAttribute("servicio", listServicios);
	        } else if (filterBy.equals("asignados_no_realizados")) {
	            List<ServicioModel> listServicios;
	            if (Integer.parseInt(opcion) != 0) {
	                ProFamily profam = proFamilyService.findById(Integer.parseInt(opcion));
	                String proFamName = profam.getName();
	                System.out.println("proFamName: " + proFamName);
	                listServicios = servicioService.getAssignedButUncompletedServiciosByProFamily(proFamName);
	            } else {
	                listServicios = servicioService.getAssignedButUncompletedServices();
	            }
	            System.out.println("getAssignedButUncompleted -- " + listServicios);
	            model.addAttribute("servicio", listServicios);
	        } else if (filterBy.equals("no_asignados")) {
	            List<ServicioModel> listServicios;
	            if (Integer.parseInt(opcion) != 0) {
	                ProFamily profam = proFamilyService.findById(Integer.parseInt(opcion));
	                String proFamName = profam.getName();
	                System.out.println("proFamName: " + proFamName);
	                listServicios = servicioService.getUnassignedServiciosByProFamily(proFamName);
	            } else {
	                listServicios = servicioService.getUnassignedServicios();
	            }
	            System.out.println("getUnassigned -- " + listServicios);
	            model.addAttribute("servicio", listServicios);
	        } else {
	            List<ServicioModel> servicios = servicioService.getAllServicios();
	            System.out.println("getAll 1er if -- "+servicios);
	            model.addAttribute("servicio", servicios);
	        }
	    } else if (Integer.parseInt(opcion) != 0) {
	        ProFamily profam = proFamilyService.findById(Integer.parseInt(opcion));
	        String proFamName = profam.getName();
	        System.out.println("proFamName: " + proFamName);
	        List<ServicioModel> proFamServicios = servicioService.findServiciosByProFamily(proFamName);
	        for (ServicioModel s : proFamServicios) {
	            System.out.println(" elseif 1 servicios de proFamServicios:" + s.getId());
	        }

	        List<ServicioModel> listServicios = servicioService.findServiciosByProFamily(proFamName);
	        for (ServicioModel f : listServicios) {
	        	System.out.println(" elseif 1 getFinishedServiciosByProFamily:" + f);
	        }

	        model.addAttribute("servicio", listServicios);
	    } else {
	        List<ServicioModel> servicios = servicioService.getAllServicios();
	        System.out.println("getAll ultimo else -- "+servicios);
	        model.addAttribute("servicio", servicios);
	    }
	    model.addAttribute("business", business);
	    return BUSINESS_HOME_VIEW;
	}

	@GetMapping("/reports")
	public String Reports(@RequestParam(name="opcion",required=false,defaultValue="0")String opcion,Model model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();

		StudentModel student = studentService.getStudentByName(username);
		String email = student.getEmail();
		Business business = businessService.getIdByEmail(email);
		
		List<ProFamily> profesionalFamilies = proFamilyService.getAll();
		model.addAttribute("profesionalFamilies", profesionalFamilies);
		
		System.out.println("OPCION: "+opcion);
		if(Integer.parseInt(opcion)!=0) {
			
			ProFamily profam=proFamilyService.findById(Integer.parseInt(opcion));
			List<Report>reports=reportService.findReportsByServiceProFamily(profam.getName(),business);
			model.addAttribute("report", reports);
			List<Servicio> businessIdServices=reports.stream().map(report->report.getServicioId()).collect(Collectors.toList());
			
			model.addAttribute("businessIdServices",businessIdServices);
			System.out.println("QUEEE: "+businessIdServices);
		}else {
			
			List<Report> reports = servicioService.getReportsForServicesByBusinessId(business);
			model.addAttribute("report", reports);
			List<Servicio> businessIdServices=reports.stream().map(report->report.getServicioId()).collect(Collectors.toList());
			model.addAttribute("businessIdServices",businessIdServices);
			System.out.println("QUEEE: "+businessIdServices);
		}
		model.addAttribute("username",username);

		return BUSINESS_REPORT_VIEW;
	}


	@GetMapping("/ratedServicios")
	public String ratedServicios(@RequestParam(name="opcion",required=false,defaultValue="0")String opcion, Model model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();

		StudentModel student = studentService.getStudentByName(username);
		String email = student.getEmail();
		Business business = businessService.getIdByEmail(email);
		List<ServicioModel> serviceList = servicioService.getServicesByBusinessId(business);		
		List<ProFamily> profesionalFamilies = proFamilyService.getAll();
		model.addAttribute("profesionalFamilies", profesionalFamilies);
		if(Integer.parseInt(opcion)!=0) {
			ProFamily profam = proFamilyService.findById(Integer.parseInt(opcion));
			List<Servicio> servicios = servicioRepository.findByValorationIsNotNullAndBusinessIdAndProfesionalFamilyId(business, profam);
			model.addAttribute("servicio", servicios);
		}else {	
			ProFamily profam = proFamilyService.findById(Integer.parseInt(opcion));
			List<Servicio> servicios =servicioRepository.findByValorationIsNotNullAndBusinessIdAndProfesionalFamilyId(business, profam);
			model.addAttribute("servicio", servicios);
		}
		return BUSINESS_RATED_SERVICES_VIEW;
	}

}
