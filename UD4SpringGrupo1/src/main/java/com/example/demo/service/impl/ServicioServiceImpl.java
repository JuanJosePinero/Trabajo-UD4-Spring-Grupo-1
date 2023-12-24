package com.example.demo.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Servicio;
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
    
    private Servicio model2entity(ServicioModel servicioModel) {
		ModelMapper mapper = new ModelMapper();
		return mapper.map(servicioModel, Servicio.class);
	}

	private ServicioModel entity2model(Servicio servicio) {
		ModelMapper mapper = new ModelMapper();
		return mapper.map(servicio, ServicioModel.class);
	}
	
	@Override
	public Servicio addServicio(ServicioModel servicioModel) {
		Servicio servicio = model2entity(servicioModel);
		servicio.setTitle(servicioModel.getTitle());
		servicio.setDescription(servicioModel.getDescription());
		servicio.setHappeningDate(servicioModel.getHappeningDate());
		servicio.setRegisterDate(servicioModel.getRegisterDate());
		servicio.setId(servicioModel.getId());
		servicio.setBusinessId(servicioModel.getBusinessId());
		servicio.setProfesionalFamilyId(servicioModel.getProfesionalFamilyId());
		servicio.setStudentId(servicioModel.getStudentId());
		servicio.setValoration(0);
		servicio.setComment(null);
		servicio.setDeleted(0);
		servicio.setFinished(0);
		return servicioRepository.save(servicio);	
	}

	@Override
	public int deleteServicio(int id) {
		Servicio servicio = servicioRepository.findById(id);

        if (servicio.getDeleted() == 0) {
        	servicio.setDeleted(1);
        	servicioRepository.save(servicio);
            return 1;
        } else
            return 0;
	}

	@Override
	public Servicio updateServicio(ServicioModel servicioModel) {
		Servicio servicio = servicioRepository.findById(servicioModel.getId());
		servicio.setTitle(servicioModel.getTitle());
		servicio.setDescription(servicioModel.getDescription());
		servicio.setHappeningDate(servicioModel.getHappeningDate());
		servicio.setRegisterDate(servicioModel.getRegisterDate());
		servicio.setId(servicioModel.getId());
		servicio.setBusinessId(servicioModel.getBusinessId());
		servicio.setProfesionalFamilyId(servicioModel.getProfesionalFamilyId());
		servicio.setStudentId(servicioModel.getStudentId());
		servicio.setValoration(servicioModel.getValoration());
		servicio.setComment(servicioModel.getComment());
		servicio.setDeleted(0);
//		servicio.setFinished(servicioModel.getFinished());
		return servicioRepository.save(servicio);
	}
}