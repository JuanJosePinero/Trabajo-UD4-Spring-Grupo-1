package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Servicio;
import com.example.demo.entity.Student;
import com.example.demo.model.ServicioModel;
import com.example.demo.model.StudentModel;

public interface ServicioService {

	List<Servicio> getAllServicios();
	
	public Servicio addServicio(ServicioModel servicioModel);
	
	int deleteServicio(int id);
	
	Servicio updateServicio(ServicioModel servicioModel);

//	List<Servicio> getServiciosByProFamilyId(int proFamilyId);
	
//	 List<Servicio> findByBusinessId(Long businessId);

}
