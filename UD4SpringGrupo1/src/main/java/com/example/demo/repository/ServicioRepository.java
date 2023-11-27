package com.example.demo.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Servicio;

@Service("servicioRepository")
public interface ServicioRepository  extends JpaRepository<Servicio, Serializable>{
	
	public abstract Servicio findByTitle(String title);

}
