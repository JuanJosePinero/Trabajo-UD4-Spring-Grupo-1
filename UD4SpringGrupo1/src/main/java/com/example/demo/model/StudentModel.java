package com.example.demo.model;

import java.util.List;

import com.example.demo.entity.ProFamily;
import com.example.demo.entity.Servicio;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class StudentModel {

	private int id;
	private String name, surname, email, password, role;
	private boolean enabled;
	private ProFamily profesionalFamily;
	private List<Servicio> servicios;
	
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

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public ProFamily getProfesionalFamily() {
		return profesionalFamily;
	}

	public void setProfesionalFamily(ProFamily profesionalFamily) {
		this.profesionalFamily = profesionalFamily;
	}

	public List<Servicio> getServicios() {
		return servicios;
	}

	public void setServicios(List<Servicio> servicios) {
		this.servicios = servicios;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public StudentModel(int id, String name, String surname, String email, String password, String role,
			ProFamily profesionalFamily, List<Servicio> servicios, boolean enabled) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
		this.role = role;
		this.profesionalFamily = profesionalFamily;
		this.servicios = servicios;
		this.enabled = enabled;
	}

	public StudentModel() {
		super();
	}
	
	
	
}

