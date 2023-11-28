package com.example.demo.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.ProFamily;

@Repository("proFamilyRepository")
public interface ProFamilyRepository extends JpaRepository<ProFamily , Serializable>{
	
	public abstract ProFamily findByName(String name);

}
