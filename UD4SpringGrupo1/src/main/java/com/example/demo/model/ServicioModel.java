package com.example.demo.model;

import java.sql.Date;

import com.example.demo.entity.Business;
import com.example.demo.entity.ProFamily;
import com.example.demo.entity.Student;

import lombok.Data;

@Data
public class ServicioModel {
	

	private int id;
	
	private String title;
	
	private String description;
	
	private Date registerDate;
	
	private Date happeningDate;


	private int studentId;

	private int businessId;
	

	 private int profesionalFamilyId;
	
	private float valoration;
	
	private int finished;
	
	private String comment;
	
	private int deleted;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}
	public Date getHappeningDate() {
		return happeningDate;
	}
	public void setHappeningDate(Date happeningDate) {
		this.happeningDate = happeningDate;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public int getBusinessId() {
		return businessId;
	}
	public void setBusinessId(int businessId) {
		this.businessId = businessId;
	}
	public int getProfesionalFamilyId() {
		return profesionalFamilyId;
	}
	public void setProfesionalFamilyId(int profesionalFamilyId) {
		this.profesionalFamilyId = profesionalFamilyId;
	}
	public float getValoration() {
		return valoration;
	}
	public void setValoration(float valoration) {
		this.valoration = valoration;
	}
	public int isFinished() {
		return finished;
	}
	public void setFinished(int finished) {
		this.finished = finished;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getDeleted() {
		return deleted;
	}
	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}
	public ServicioModel(int id, String title, String description, Date registerDate, Date happeningDate, int studentId,
			int businessId, int profesionalFamilyId, float valoration, int finished, String comment, int deleted) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.registerDate = registerDate;
		this.happeningDate = happeningDate;
		this.studentId = studentId;
		this.businessId = businessId;
		this.profesionalFamilyId = profesionalFamilyId;
		this.valoration = valoration;
		this.finished = finished;
		this.comment = comment;
		this.deleted = deleted;
	}
	public ServicioModel() {
		super();
	}
}
