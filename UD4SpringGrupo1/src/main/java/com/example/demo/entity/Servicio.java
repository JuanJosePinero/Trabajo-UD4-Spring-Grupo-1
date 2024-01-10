package com.example.demo.entity;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;

@Entity
public class Servicio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String title;
    private String description;
    private Date registerDate;
    private Date happeningDate;

    @ManyToOne
    @JoinColumn(name = "studentId")
    private int studentId;

    @ManyToOne
    @JoinColumn(name = "businessId")
    private int businessId;

    @ManyToOne
    @JoinColumn(name = "profesionalFamilyId")
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
	@PrePersist
    protected void onCreate() {
        LocalDate currentDate = LocalDate.now();
        registerDate = Date.from(currentDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
	public Servicio(int id, String title, String description, Date registerDate, Date happeningDate, int studentId,
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
	public Servicio() {
		super();
	}
	public int getFinished() {
		return finished;
	}
	@Override
	public String toString() {
	    return "Servicio [id=" + id +
	            ", title=" + title +
	            ", description=" + description +
	            ", registerDate=" + registerDate +
	            ", happeningDate=" + (happeningDate != null ? happeningDate.toString() : "N/A") +
	            ", studentId=" + studentId +
	            ", businessId=" + businessId +
	            ", profesionalFamilyId=" + profesionalFamilyId +
	            ", valoration=" + valoration +
	            ", finished=" + finished +
	            ", comment=" + comment +
	            ", deleted=" + deleted +
	            "]";
	}

    
    
}
