package com.example.demo.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Student;

@Repository("studentRepository")
public interface StudentRepository extends JpaRepository<Student, Serializable>{
	
	public abstract Student findByUsername(String username);

//	@Modifying
//    @Query("UPDATE Student s SET s.enabled = :enabled WHERE s.id = :id")
//    void updateEnabled(@Param("id") int id, @Param("enabled") int enabled);
}