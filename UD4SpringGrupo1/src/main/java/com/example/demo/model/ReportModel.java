package com.example.demo.model;

import java.sql.Date;

import com.example.demo.entity.Servicio;
import com.example.demo.entity.Student;

import lombok.Data;

@Data
public class ReportModel {
	
	private int id;
	private Date fullDate;
	private int serviceTime;
	private String report;
	private int studentId;
	private int servicioId;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFullDate() {
		return fullDate;
	}

	public void setFullDate(Date fullDate) {
		this.fullDate = fullDate;
	}

	public int getServiceTime() {
		return serviceTime;
	}

	public void setServiceTime(int serviceTime) {
		this.serviceTime = serviceTime;
	}

	public String getReport() {
		return report;
	}

	public void setReport(String report) {
		this.report = report;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public int getServicioId() {
		return servicioId;
	}

	public void setServicioId(int servicioId) {
		this.servicioId = servicioId;
	}

	public ReportModel(int id, Date fullDate, int serviceTime, String report, int studentId, int servicioId) {
		super();
		this.id = id;
		this.fullDate = fullDate;
		this.serviceTime = serviceTime;
		this.report = report;
		this.studentId = studentId;
		this.servicioId = servicioId;
	}

	public ReportModel() {
		super();
	}
    
}
