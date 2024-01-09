package com.example.demo.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.ProFamily;
import com.example.demo.entity.Servicio;

@Repository("servicioRepository")
public interface ServicioRepository  extends JpaRepository<Servicio, Serializable>{
	
	public abstract Servicio findByTitle(String title);
	List<Servicio> findByProfesionalFamilyId(ProFamily proFamilyId);
	Servicio findById(int id);
	List<Servicio> findByFinished(int finished);
	
//	 @Query("SELECT s FROM Servicio s WHERE s.profesionalFamilyId.name = :familyName")
//	 List<Servicio> findServiciosByProFamilyName(@Param("familyName") String familyName);

}
