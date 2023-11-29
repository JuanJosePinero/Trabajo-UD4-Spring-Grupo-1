package com.example.demo.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Student;
import com.example.demo.model.StudentModel;
import com.example.demo.repository.StudentRepository;

@Service
public class StudentService {
	
	List<StudentModel> listAllStudents() {
		return null;
	}
	
	@Autowired
    private StudentRepository studentRepository;

    public void register(StudentModel studentModel) {
    	ModelMapper mp = new ModelMapper();
    	Student student = mp.map(studentModel, Student.class);
        studentRepository.save(student);
    }
    
    public boolean login(String email, String password) {
        // Here you can implement authentication logic
        // For simplicity, I'll just return true for any credentials
        return true;
    }
    
    
    
	int deleteStudent(int id) {
		return 0;
	}
	Student updateStudent(StudentModel studentModel) {
		return null;
	}
	Student enable(StudentModel studentModel) {
		studentModel.setEnabled(true);
		ModelMapper mp = new ModelMapper();
		return mp.map(studentModel, Student.class);
	}
	
	

}
