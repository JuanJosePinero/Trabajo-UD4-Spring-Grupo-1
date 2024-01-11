package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Business;
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

	public ServicioModel entity2model(Servicio servicio) {
		ModelMapper mapper = new ModelMapper();
		return mapper.map(servicio, ServicioModel.class);
	}
	
//	 @Override
//	    public List<ServicioModel> obtenerServiciosPorUsuario(int userId) {
//	        List<ServicioModel> serviciosDelUsuario = new ArrayList<>();
//
//	        // Obtener todos los servicios
//	        List<Servicio> todosLosServicios = servicioRepository.findAll();
//
//	        // Recorrer todos los servicios y agregar los del usuario al resultado
//	        for (Servicio servicio : todosLosServicios) {
//	        	if (servicio.getId() == userId) {
//	        	    serviciosDelUsuario.add(servicio);
//	        	}
//
//	        }
//
//	        return serviciosDelUsuario;
//	    }
	
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
		servicio.setComment(null);
		servicio.setDeleted(0);
		servicio.setFinished(servicioModel.getFinished());
		return servicioRepository.save(servicio);
	}

	@Override
	public Servicio rateServicio(int servicioId, float valoracion) {
	    Servicio servicio = servicioRepository.findById(servicioId);
	    servicio.setValoration(valoracion);
	    return servicioRepository.save(servicio);
	}
	
	@Override
	public Servicio commentServicio(int servicioId, String comment) {
	    Servicio servicio = servicioRepository.findById(servicioId);
	    servicio.setComment(comment);
	    return servicioRepository.save(servicio);
	}
	
	@Override
	public List<ServicioModel> getFinishedServicios() {
	    List<Servicio> finishedServicios = servicioRepository.findByFinished(1);
	    List<ServicioModel> servicioModels = new ArrayList<>();

	    for (Servicio servicio : finishedServicios) {
	        ServicioModel servicioModel = entity2model(servicio);
	        servicioModels.add(servicioModel);
	    }

	    return servicioModels;
	}
	
	@Override
	public List<ServicioModel> getUnassignedServicios() {
	    List<Servicio> unassignedServicios = servicioRepository.findByStudentIdIsNull();
	    List<ServicioModel> servicioModels = new ArrayList<>();

	    for (Servicio servicio : unassignedServicios) {
	        ServicioModel servicioModel = entity2model(servicio);
	        servicioModels.add(servicioModel);
	    }

	    return servicioModels;
	}
	
	@Override
	public List<ServicioModel> getAssignedButUncompletedServices() {
	    List<Servicio> assignedButUncompletedServices = servicioRepository.findByStudentIdIsNotNullAndFinishedIsNot(1);
	    List<ServicioModel> servicioModels = new ArrayList<>();

	    for (Servicio servicio : assignedButUncompletedServices) {
	        ServicioModel servicioModel = entity2model(servicio);
	        servicioModels.add(servicioModel);
	    }

	    return servicioModels;
	}


	@Override
	public List<ServicioModel> findServiciosByProFamily(String familyName) {
	    List<ServicioModel> servicioModels = new ArrayList<>();

	    for (Servicio servicio : servicioRepository.findAll()) {
	        if (servicio.getProfesionalFamilyId() != null && servicio.getProfesionalFamilyId().getName().equals(familyName)) {
	            servicioModels.add(entity2model(servicio));
	        }
	    }

	    return servicioModels;
	}

	@Override
	public List<ServicioModel> getAllServicios() {
	    List<Servicio> servicios = servicioRepository.findAll();
	    List<ServicioModel> servicioModels = new ArrayList<>();

	    for (Servicio servicio : servicios) {
	        ServicioModel servicioModel = entity2model(servicio);
	        servicioModels.add(servicioModel);
	    }

	    return servicioModels;
	}

	@Override
	public List<ServicioModel> getServicesByBusinessId(Business business) {
		// TODO Auto-generated method stub
		return null;
	}



}