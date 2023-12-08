package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Business;
import com.example.demo.model.BusinessModel;


public interface BusinessService {
	
	List<Business> getAllBusiness();

	Business getBusinessById(int id);

	Business saveBusiness(Business business);

    int deleteBusiness(int id);

    Business updateBusiness(BusinessModel businessModel);

}
