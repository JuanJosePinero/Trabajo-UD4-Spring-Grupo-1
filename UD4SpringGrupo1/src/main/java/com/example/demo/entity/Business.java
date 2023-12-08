package com.example.demo.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Business {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
//	@Column(unique=true, nullable=false)
	private String name, address,phone, email, logo;
	
	@OneToMany(mappedBy="businessId")
	private List<Servicio> servicioList;
	
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public List<Servicio> getServicioList() {
		return servicioList;
	}

	public void setServicioList(List<Servicio> servicioList) {
		this.servicioList = servicioList;
	}

	public int getDeleted() {
		return deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}
	
	public Business(int id, String name, String address, String phone, String email, String logo,
			List<Servicio> servicioList, int deleted) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.logo = logo;
		this.servicioList = servicioList;
		this.deleted = deleted;
		
	}

	public Business() {
		super();
	}
	
	
	
}
