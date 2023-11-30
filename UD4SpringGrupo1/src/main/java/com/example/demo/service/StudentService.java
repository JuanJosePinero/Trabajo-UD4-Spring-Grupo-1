package com.example.demo.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.demo.entity.Student;
import com.example.demo.model.StudentModel;

public interface StudentService {

	List<StudentModel> listAllStudents();

	public Student register(StudentModel studentModel);

	public boolean login(String email, String password);

	public boolean authenticate(String email, String password);

	int deleteStudent(int id);

	Student updateStudent(StudentModel studentModel);

	Student enable(StudentModel studentModel);

//	UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
