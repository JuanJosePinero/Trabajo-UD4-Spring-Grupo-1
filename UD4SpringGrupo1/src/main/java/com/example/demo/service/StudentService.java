package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Student;
import com.example.demo.model.StudentModel;

public interface StudentService {
	
	List<StudentModel> listAllStudents();
	Student register(StudentModel studentModel);
	int deleteStudent(int id);
	Student updateStudent(StudentModel studentModel);
	Student enable(StudentModel studentModel);

}
