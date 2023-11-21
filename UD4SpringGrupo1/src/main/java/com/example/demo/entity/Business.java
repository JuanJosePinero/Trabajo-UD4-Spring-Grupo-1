package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Business {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
//	@Column(unique=true, nullable=false)
	private String name, address,phone, email, logo;
	
}
