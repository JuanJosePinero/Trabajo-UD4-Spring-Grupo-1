package com.example.demo.model;

import java.sql.Date;

import com.example.demo.entity.Business;
import com.example.demo.entity.ProfesionalFamily;
import com.example.demo.entity.Student;

import lombok.Data;

@Data
public class ServicioModel {
	

	private long id;
	
	private String title;
	
	private String description;
	
	private Date registerDate;
	
	private Date happeningDate;


	private Student studentId;

	private Business businessId;
	

	private ProfesionalFamily familyId;
	
	private int valoration;
	
	private boolean finished;
	
	private String comment;
}
