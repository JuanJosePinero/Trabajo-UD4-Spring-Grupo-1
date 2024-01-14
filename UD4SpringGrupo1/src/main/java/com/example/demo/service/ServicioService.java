package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Business;
import com.example.demo.entity.ProFamily;
import com.example.demo.entity.Report;
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

	List<ServicioModel> findServiciosByProFamily(String familyName);

	List<ServicioModel> getAllServicios();
	
	public List<ServicioModel>getServicesByBusinessId(Business business);
	
	public abstract List<Report> getReportsForServicesByBusinessId(Business business);
	
	Servicio getServicioById(int serviceId);

	Servicio finishServicio(int servicioId);

	public List<ServicioModel> getFinishedServiciosByProFamily(String familyName);

	List<ServicioModel> getUnassignedServiciosByProFamily(String familyName);

	List<ServicioModel> getAssignedButUncompletedServiciosByProFamily(String familyName);

	Report createReportByServicioId(int servicioId, String reportText, int serviceTime, int studentId);

	Servicio assignStudent(int servicioId, int studentId);

}
