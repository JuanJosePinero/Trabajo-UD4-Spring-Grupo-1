package com.example.demo.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Business;

@Repository("businessRepository")
public interface BusinessRepository extends JpaRepository<Business, Serializable>{
	
	public abstract Business findByName(String name);
	
	public abstract Business findByEmail(String email);

}