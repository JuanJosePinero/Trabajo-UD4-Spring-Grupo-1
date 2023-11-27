package com.example.demo.model;

import lombok.Data;

@Data
public class StudentModel {

	private int id;
	private String name, surname, email, password, role;
	private boolean enabled;
	
}

