package com.example.demo.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Student;

@Repository("studentRepository")
public interface StudentRepository extends JpaRepository<Student, Serializable>{
	
	public abstract Student findByUsername(String username);
	List<Student> findAllByEnabledIn(List<Integer> enabledValues);

}