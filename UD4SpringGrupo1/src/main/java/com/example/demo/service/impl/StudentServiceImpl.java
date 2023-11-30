package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Student;
import com.example.demo.model.StudentModel;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.StudentService;

@Configuration
@Service("studentService")
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
	public Student register(StudentModel studentModel) {
		Student student = model2entity(studentModel);
		student.setPassword(passwordEncoder().encode(student.getPassword()));
		student.setEnabled(false);
		student.setRole("u");
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

	@Override
	public boolean login(String email, String password) {
        Student student = studentRepository.findByEmail(email);

        // Verifica si el estudiante existe y si la contraseña coincide
        return student != null && passwordEncoder().matches(password, student.getPassword());
    }
//	public boolean login(String email, String password) {
//		 Student student = studentRepository.findByEmail(email);
//		 System.out.println("HOL");
//		    if (student != null && passwordEncoder().matches(password, student.getPassword())) {
//		        // Passwords match, login successful
//		    	System.out.println("Passwords match, login successful");
//		        return true;
//		    } else {
//		        // Either the email is not found or the passwords do not match
//		    	System.out.println("Either the email is not found or the passwords do not match");
//		        return false;
//		    }
//	}

	@Override
	public boolean authenticate(String email, String password) {
		// TODO Auto-generated method stub
		return false;
	}
	
//	@Override
//	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
//	    Student student = studentRepository.findByUsername(name);
//	    
//	    if (student != null) {
//	        return User.builder()
//	                .username(student.getName())
//	                .password(student.getPassword())
//	                .disabled(!student.isEnabled())
//	                .roles(student.getRole())
//	                .build();
//	    } else {
//	        throw new UsernameNotFoundException("Student not found");
//	    }
//	}


}