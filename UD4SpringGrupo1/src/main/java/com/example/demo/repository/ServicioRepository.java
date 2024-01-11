package com.example.demo.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Business;
import com.example.demo.entity.ProFamily;
import com.example.demo.entity.Report;
import com.example.demo.entity.Servicio;
import com.example.demo.entity.Student;

@Repository("servicioRepository")
public interface ServicioRepository  extends JpaRepository<Servicio, Serializable>{
	
	public abstract Servicio findByTitle(String title);
	Servicio findById(int id);
	List<Servicio> findByFinished(int finished);
	List<Servicio>findByProfesionalFamilyId(ProFamily proFamilyId);
	public abstract List<Servicio> findByStudentIdIsNull();
	public abstract List<Servicio> findByStudentIdIsNotNullAndFinishedIsNot(int i);
	public abstract List<Servicio> findByBusinessId(Business businessId);
	public abstract List<Report> getReportsForServicesByBusinessId(Business business);

}
