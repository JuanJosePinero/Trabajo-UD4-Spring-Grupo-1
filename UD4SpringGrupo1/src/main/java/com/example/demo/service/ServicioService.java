package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Servicio;
import com.example.demo.model.ServicioModel;

public interface ServicioService {
	
	public Servicio addServicio(ServicioModel servicioModel);
	
	int deleteServicio(int id);
	
	Servicio updateServicio(ServicioModel servicioModel);
	
	Servicio rateServicio(int servicioId, float valoration);
	Servicio commentServicio(int servicioId, String comment);
	
	public List<ServicioModel> getFinishedServicios();

	List<ServicioModel> getUnassignedServicios();

	List<ServicioModel> getAssignedButUncompletedServices();
	
//	List<ServicioModel> obtenerServiciosPorUsuario(int userId);

}
