package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Business;
import com.example.demo.model.BusinessModel;
import com.example.demo.repository.BusinessRepository;
import com.example.demo.service.BusinessService;

@Configuration
@Service("businessService")
public class BusinessServiceImpl implements BusinessService {

	
	private final BusinessRepository businessRepository;

    @Autowired
    public BusinessServiceImpl(BusinessRepository businessRepository) {
        this.businessRepository = businessRepository;
    }
    
	@Override
	public List<Business> getAllBusiness() {
		return businessRepository.findAll();
	}

	@Override
	public Business getBusinessById(int id) {
		Optional<Business> optionalBusiness = businessRepository.findById(id);
        return optionalBusiness.orElse(null);
	}

	@Override
	public Business saveBusiness(Business business) {
		return businessRepository.save(business);
	}

	@Override
	public int deleteBusiness(int id) {
		Optional<Business> optionalBusiness = businessRepository.findById(id);

        if (optionalBusiness.isPresent()) {
        	Business business = optionalBusiness.get();
        	business.setDeleted(1);
        	businessRepository.save(business);
            return 1;
        } else
            return 0;
	}

	@Override
	public Business updateBusiness(BusinessModel businessModel) {
		Business business = businessRepository.findById(businessModel.getId()) .orElseThrow(() -> new RuntimeException("Business not found"));
		business.setName(business.getName()); 
		 return businessRepository.save(business);
	}

}
