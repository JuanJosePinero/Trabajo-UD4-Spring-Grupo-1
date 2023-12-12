package com.example.demo.model;

public class UserModel {
	
	private int id;
	private String email;
	public UserModel(int id, String email) {
		super();
		this.id = id;
		this.email = email;
	}
	public UserModel() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}