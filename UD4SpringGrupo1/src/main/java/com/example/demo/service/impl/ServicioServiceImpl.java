package com.example.demo.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Business;
import com.example.demo.entity.Servicio;
import com.example.demo.model.BusinessModel;
import com.example.demo.model.ServicioModel;
import com.example.demo.repository.ServicioRepository;
import com.example.demo.service.ServicioService;

@Configuration
@Service("servicioService")
public class ServicioServiceImpl implements ServicioService {

    private final ServicioRepository servicioRepository;

    @Autowired
    public ServicioServiceImpl(ServicioRepository servicioRepository) {
        this.servicioRepository = servicioRepository;
    }
    
    private Business model2entity(ServicioModel servicioModel) {
		ModelMapper mapper = new ModelMapper();
		return mapper.map(servicioModel, Business.class);
	}

	private BusinessModel entity2model(Servicio servicio) {
		ModelMapper mapper = new ModelMapper();
		return mapper.map(servicio, BusinessModel.class);
	}
	
	@Override
	public List<Servicio> getAllServicios() {
		return servicioRepository.findAll();
	}
	
//    @Override
//    public List<Servicio> findByBusinessId(Long businessId) {
//        return servicioRepository.findByBusinessId(businessId);
//    }
}