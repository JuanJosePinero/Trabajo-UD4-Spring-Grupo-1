package com.example.demo.model;

import java.sql.Date;

import com.example.demo.entity.Business;
import com.example.demo.entity.ProfesionalFamily;
import com.example.demo.entity.Student;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class ServicioModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String title;
	
	private String description;
	
	private Date registerDate;
	
	private Date happeningDate;

	@ManyToOne
	@JoinColumn(name = "studentId_fk")
	private Student studentId;
	
	@ManyToOne
	@JoinColumn(name = "businessId_fk")
	private Business businessId;
	
	@ManyToOne
	@JoinColumn(name = "profesionalFamilyId_fk")
	private ProfesionalFamily familyId;
	
	private int valoration;
	
	private boolean finished;
	
	private String comment;
}
