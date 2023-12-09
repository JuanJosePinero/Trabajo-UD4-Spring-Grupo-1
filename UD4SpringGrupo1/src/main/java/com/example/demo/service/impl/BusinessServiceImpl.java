package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Business;
import com.example.demo.entity.ProFamily;
import com.example.demo.model.BusinessModel;
import com.example.demo.repository.BusinessRepository;
import com.example.demo.service.BusinessService;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.IOException;

@Configuration
@Service("businessService")
public class BusinessServiceImpl implements BusinessService {

	
	private final BusinessRepository businessRepository;

    @Autowired
    public BusinessServiceImpl(BusinessRepository businessRepository) {
        this.businessRepository = businessRepository;
    }
    
    private Business model2entity(BusinessModel businessModel) {
		ModelMapper mapper = new ModelMapper();
		return mapper.map(businessModel, Business.class);
	}

	private BusinessModel entity2model(Business business) {
		ModelMapper mapper = new ModelMapper();
		return mapper.map(business, BusinessModel.class);
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

	@Override
	public Business addBusiness(BusinessModel businessModel) {
		Business business = model2entity(businessModel);
		business.setName(businessModel.getName());
		business.setAddress(businessModel.getAddress());
		business.setEmail(businessModel.getEmail());
		business.setLogo(null);
		business.setPhone(businessModel.getPhone());
//		if (logoFile != null && !logoFile.isEmpty()) {
//            try {
//                byte[] bytes = logoFile.getBytes();
//                Path path = Paths.get("imgs/business/" + logoFile.getOriginalFilename());
//                Files.write(path, bytes);
//                business.setLogo(path.toString()); 
//            } catch (IOException e) {
//                // Manejar la excepción (por ejemplo, lanzarla o registrarla)
//            }
//        }
		return businessRepository.save(business);
	}

}
