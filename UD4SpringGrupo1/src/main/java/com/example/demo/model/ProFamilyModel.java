package com.example.demo.model;

import lombok.Data;

@Data
public class ProFamilyModel {

	private int id;
	private String name;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ProFamilyModel(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public ProFamilyModel() {
		super();
	}
	
}
