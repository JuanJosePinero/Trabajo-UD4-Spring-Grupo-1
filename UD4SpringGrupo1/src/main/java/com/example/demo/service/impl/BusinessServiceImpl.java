package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Business;
import com.example.demo.entity.Servicio;
import com.example.demo.model.BusinessModel;
import com.example.demo.repository.BusinessRepository;
import com.example.demo.repository.ServicioRepository;
import com.example.demo.service.BusinessService;

@Configuration
@Service("businessService")
public class BusinessServiceImpl implements BusinessService {

	
	private final BusinessRepository businessRepository;
	
	@Autowired
	@Qualifier("servicioRepository")
	 private ServicioRepository servicioRepository;

    public BusinessServiceImpl(BusinessRepository businessRepository) {
        this.businessRepository = businessRepository;
    }
    
    @Override
    public Business model2entity(BusinessModel businessModel) {
		ModelMapper mapper = new ModelMapper();
		return mapper.map(businessModel, Business.class);
	}
    
    @Override
	public BusinessModel entity2model(Business business) {
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
    public Business updateBusiness(Business business) {
        Business existingBusiness = businessRepository.findById(business.getId())
                .orElseThrow(() -> new RuntimeException("Business not found"));

        existingBusiness.setName(business.getName());
        existingBusiness.setAddress(business.getAddress());
        existingBusiness.setEmail(business.getEmail());
        existingBusiness.setPhone(business.getPhone());
        existingBusiness.setLogo(business.getLogo());

        return businessRepository.save(existingBusiness);
    }

	@Override
	public Business addBusiness(BusinessModel businessModel) {
	    Business business = model2entity(businessModel);
	    business.setName(businessModel.getName());
	    business.setAddress(businessModel.getAddress());
	    business.setEmail(businessModel.getEmail());
	    business.setPhone(businessModel.getPhone());

	    business.setLogo(businessModel.getLogo());
	    

	    return businessRepository.save(business);
	}

	@Override
	public Business getIdByEmail(String email) {
		Business business=businessRepository.findByEmail(email);
		return business;
	}
	
	
	@Override
    public List<Business> getBusinessOrderedByServiceAmount(){
    	List<Business> businessList = businessRepository.findAll();
        List<Business> businessWithServices = new ArrayList<>();
        for (Business b : businessList) {
        	if(!b.getServicioList().isEmpty())
        		businessWithServices.add(b);	
		}
        businessWithServices.sort(Comparator.comparingInt(this::getNumberOfServices).reversed());
        return businessWithServices;
    	
    }
	
	@Override
    public List<Business> getBusinessOrderedByServiceFinished(){
    	List<Business> businessList = businessRepository.findAll();
        List<Business> businessWithServicesFinished = new ArrayList<>();
        
        for (Business b : businessList) {
        	if(!b.getServicioList().isEmpty())
        		businessWithServicesFinished.add(b);	
		}
	        
        businessWithServicesFinished.sort(Comparator.comparingInt(this::getNumberOfFinishedServices).reversed());
        System.out.println("getBusinessOrderedByServiceAmount: "+businessWithServicesFinished);
        return businessWithServicesFinished;
    	
    }
	
	@Override
	public List<Business> getBusinessList(String filterBy) {
	    List<Business> businessList;

	    if ("all".equals(filterBy)) {
	        businessList = getAllBusiness();
	    } else if ("NumberOfServices".equals(filterBy)) {
	        businessList = getBusinessOrderedByServiceAmount();
	    } else if ("NumberOfFinished".equals(filterBy)) {
	        businessList = getBusinessOrderedByServiceFinished();
	    } else {
	        businessList = getAllBusiness();
	    }

	    return businessList;
	}
	
	@Override
    public Map<Integer, Integer> getAllNumberOfServices(List<Business> business) {
        Map<Integer, Integer> numberOfServices = new HashMap<>();

        for (Business b : business) {
        	 List<Servicio> businessServices = b.getServicioList();
             int numService = businessServices.size();
             numberOfServices.put(b.getId(), numService);
        }
        
        return numberOfServices;
    }
	
	@Override
	public Map<Integer, Integer> getAllNumberOfFinishedServices(List<Business> businessList) {
	    Map<Integer, Integer> numberOfFinishedServices = new HashMap<>();

	    for (Business business : businessList) {
	        List<Servicio> businessServices = business.getServicioList();
	        int numFinishedService = 0;

	        for (Servicio servicio : businessServices) {
	            if (servicio.getFinished() == 1) {
	                numFinishedService++;
	            }
	        }

	        numberOfFinishedServices.put(business.getId(), numFinishedService);
	    }

	    return numberOfFinishedServices;
	}


	
	private int getNumberOfFinishedServices(Business business) {
    	List<Servicio> businessServices = business.getServicioList();
    	List<Servicio> finishedServices  = new ArrayList<>();
    	
    	for (Servicio s : businessServices) {
			if(s.getFinished() == 1)
				finishedServices.add(s);
		}
    	return finishedServices.size();
    }	
	
	
	private int getNumberOfServices(Business business) {
    	List<Servicio> businessServices = business.getServicioList();
    	return businessServices.size();
    }	

}
