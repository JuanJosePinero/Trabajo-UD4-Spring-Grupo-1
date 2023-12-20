package com.example.demo.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Student;

@Repository("studentRepository")
public interface StudentRepository extends JpaRepository<Student, Serializable>{
	
	Student findById(int id);
	public abstract Student findByEmail(String email);
	List<Student> findAllByEnabledIn(List<Integer> enabledValues);

}