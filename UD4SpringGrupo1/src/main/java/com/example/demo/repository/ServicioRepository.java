package com.example.demo.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Servicio;

@Repository("servicioRepository")
public interface ServicioRepository  extends JpaRepository<Servicio, Serializable>{
	
	public abstract Servicio findByTitle(String title);
	List<Servicio> findByProFamilyId(int proFamilyId);

}
