package com.example.demo.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Business;
import com.example.demo.entity.ProFamily;
import com.example.demo.entity.Student;
import com.example.demo.model.BusinessModel;
import com.example.demo.model.ProFamilyModel;
import com.example.demo.model.StudentModel;


public interface BusinessService {
	
	List<Business> getAllBusiness();

	Business getBusinessById(int id);

	Business saveBusiness(Business business);

    int deleteBusiness(int id);

    Business updateBusiness(Business business);

    Business addBusiness(BusinessModel businessModel);
    
	public BusinessModel entity2model(Business	business);
	
	public Business model2entity(BusinessModel	businessModel);


}
