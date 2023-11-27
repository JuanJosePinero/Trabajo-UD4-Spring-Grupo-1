package com.example.demo.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.ProfesionalFamily;

public interface ProFamilyRepository extends JpaRepository<ProfesionalFamily , Serializable>{
	
	public abstract ProfesionalFamily findByName(String name);

}
