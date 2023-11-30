package com.example.demo.entity;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Servicio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String title;
    private String description;
    private Date registerDate;
    private Date happeningDate;

    @ManyToOne
    @JoinColumn(name = "studentId")
    private Student studentId;

    @ManyToOne
    @JoinColumn(name = "businessId")
    private Business businessId;

    @ManyToOne
    @JoinColumn(name = "profesionalFamilyId")
    private ProFamily profesionalFamilyId;

    private int valoration;
    private boolean finished;
    private String comment;
	public long getId() {
		return id;
	}
	public void setId(long id) {
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
	public Student getStudentId() {
		return studentId;
	}
	public void setStudentId(Student studentId) {
		this.studentId = studentId;
	}
	public Business getBusinessId() {
		return businessId;
	}
	public void setBusinessId(Business businessId) {
		this.businessId = businessId;
	}
	public ProFamily getProfesionalFamilyId() {
		return profesionalFamilyId;
	}
	public void setProfesionalFamilyId(ProFamily profesionalFamilyId) {
		this.profesionalFamilyId = profesionalFamilyId;
	}
	public int getValoration() {
		return valoration;
	}
	public void setValoration(int valoration) {
		this.valoration = valoration;
	}
	public boolean isFinished() {
		return finished;
	}
	public void setFinished(boolean finished) {
		this.finished = finished;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Servicio(long id, String title, String description, Date registerDate, Date happeningDate, Student studentId,
			Business businessId, ProFamily profesionalFamilyId, int valoration, boolean finished, String comment) {
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
	}
	public Servicio() {
		super();
	}
    
    
}
