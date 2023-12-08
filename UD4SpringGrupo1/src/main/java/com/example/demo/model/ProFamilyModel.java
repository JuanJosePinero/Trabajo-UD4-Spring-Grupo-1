package com.example.demo.model;

import lombok.Data;

@Data
public class ProFamilyModel {

	private int id;
	private String name;
	private int deleted;
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
	public int getDeleted() {
		return deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}
	public ProFamilyModel(int id, String name, int deleted) {
		super();
		this.id = id;
		this.name = name;
		this.deleted = deleted;
	}
	public ProFamilyModel() {
		super();
	}
	
}
