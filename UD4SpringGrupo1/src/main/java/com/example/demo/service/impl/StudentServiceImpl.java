package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.entity.Student;
import com.example.demo.model.StudentModel;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.StudentService;

@Configuration
public class StudentServiceImpl implements StudentService {

	@Autowired
	@Qualifier("studentRepository")
	private StudentRepository studentRepository;
	
	private Student model2entity(StudentModel studentModel) {
		ModelMapper mapper = new ModelMapper();
		return mapper.map(studentModel, Student.class);
	}
	
	private StudentModel entity2model(Student student) {
		ModelMapper mapper = new ModelMapper();
		return mapper.map(student, StudentModel.class);
	}
	

	@Override
	public com.example.demo.entity.Student register(com.example.demo.model.StudentModel studentModel) {
		Student student = model2entity(studentModel);
		student.setPassword(passwordEncoder().encode(student.getPassword()));
		student.setEnabled(false);
		student.setRole("ROLE_USER");
		return studentRepository.save(student);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	public List<StudentModel> listAllStudents() {
		List<StudentModel> students = new ArrayList<>();
		for(Student s :studentRepository.findAll())
			students.add(entity2model(s));
		return students;
	}
	@Override
	public int deleteStudent(int id) {
		studentRepository.deleteById(id);
		return 0;
	}

	@Override
	public Student updateStudent(StudentModel studentModel) {
		return studentRepository.save(model2entity(studentModel));
	}


	@Override
	public Student enable(StudentModel studentModel) {
		studentModel.setEnabled(true);
		return studentRepository.save(model2entity(studentModel));
	}

}