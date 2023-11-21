package com.example.demo.entity;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Report {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
//	@NotNull(message = "Custom message")
	private Date fullDate;
	private int serviceTime;
	private String report;
	
	@ManyToOne
	@JoinColumn(name = "studentId_fk")
	private Student student;
	
	@OneToOne
	@JoinColumn(name = "serviceId_fk")
	private Servicio servicio;
	

}
