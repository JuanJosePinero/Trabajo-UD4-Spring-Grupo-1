package com.example.demo.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name, surname, email, password;

    @ManyToOne
    @JoinColumn(name = "profesionalFamilyId")
    private ProfesionalFamily profesionalFamily;

    @OneToMany(mappedBy = "studentId")
    private List<Servicio> servicios;
}
