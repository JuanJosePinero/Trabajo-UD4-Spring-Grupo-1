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
	private String name, surname, username, password, role;
	private int enabled;
	private int deleted;
	private ProFamily profesionalFamily;
	private List<Servicio> servicios;
	
	public StudentModel(int id, String name, String surname, String username, String password, String role,
			ProFamily profesionalFamily, List<Servicio> servicios, int enabled, int deleted) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.username = username;
		this.password = password;
		this.role = role;
		this.profesionalFamily = profesionalFamily;
		this.servicios = servicios;
		this.enabled = enabled;
		this.deleted = deleted;
	}

	public StudentModel() {
		super();
	}
	
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}
	
	public int getDeleted() {
		return deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}
	
}

