package com.example.demo.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Business extends User{

	
	
//	@Column(unique=true, nullable=false)
	private String name, address,phone, logo;
	
	@OneToMany(mappedBy="businessId")
	private List<Servicio> servicioList;
	
	private int deleted;

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
		super(id, email);
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.logo = logo;
		this.servicioList = servicioList;
		this.deleted = deleted;
		
	}

	public Business() {
		super();
	}

	@Override
	public String toString() {
		return "Business [name=" + name + ", address=" + address + ", phone=" + phone + ", logo=" + logo + ", deleted="
				+ deleted + "]";
	}
	
	
	
}
