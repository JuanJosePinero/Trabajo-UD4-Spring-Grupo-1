package com.example.demo.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Student;
import com.example.demo.model.StudentModel;
import com.example.demo.repository.StudentRepository;

public interface StudentService {

	List<StudentModel> listAllStudents();

	public Student register(StudentModel studentModel);

	public boolean login(String email, String password);

	public boolean authenticate(String email, String password);

	int deleteStudent(int id);

	Student updateStudent(StudentModel studentModel);

	Student enable(StudentModel studentModel);

}
