package com.example.demo.model;

import java.sql.Date;

import lombok.Data;

@Data
public class ReportModel {
	
	private int id;
	private Date fullDate;
	private int serviceTime;
	private String report;
	
}
