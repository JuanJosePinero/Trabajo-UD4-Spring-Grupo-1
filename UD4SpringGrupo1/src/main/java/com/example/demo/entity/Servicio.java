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
    private ProfesionalFamily profesionalFamilyId;

    private int valoration;
    private boolean finished;
    private String comment;
}
