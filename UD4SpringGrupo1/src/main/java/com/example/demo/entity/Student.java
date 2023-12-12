package com.example.demo.entity;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
public class Student extends User implements UserDetails{
   
    private int enabled;
    private int deleted;
    
    private String name, surname, password, role;
   
    @ManyToOne
    @JoinColumn(name = "profesionalFamilyId")
    private ProFamily profesionalFamily;

    @OneToMany(mappedBy = "studentId")
    private List<Servicio> servicios;
    
    

	

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

	public Student(int id, String name, String surname, String email, String password, String role,
			ProFamily profesionalFamily, List<Servicio> servicios, int enabled, int deleted) {
		super(id , email);
		this.name = name;
		this.surname = surname;
		this.password = password;
		this.role = role;
		this.profesionalFamily = profesionalFamily;
		this.servicios = servicios;
		this.enabled = enabled;
		this.deleted = deleted;
	}

	public Student() {
		super();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		return "Student [enabled=" + enabled + ", deleted=" + deleted + ", name=" + name + ", surname=" + surname
				+ ", password=" + password + ", role=" + role + ", profesionalFamily=" + profesionalFamily
				+ ", servicios=" + servicios + ", getId()=" + getId() + ", getEmail()=" + getEmail() + "]";
	}
    
}
