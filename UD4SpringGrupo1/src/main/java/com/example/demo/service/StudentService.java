package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Student;
import com.example.demo.model.ServicioModel;
import com.example.demo.model.StudentModel;

public interface StudentService {

	List<StudentModel> listAllStudents();
	
	List<StudentModel> listAllEnabledOrDisabledStudents();
	
	StudentModel getStudentById(int id);
	
	Student model2entity(StudentModel studentModel);

	public Student register(StudentModel studentModel);

	public boolean login(String email, String password);

	public boolean authenticate(String email, String password);

	int deleteStudent(int id);

	Student updateStudent(StudentModel studentModel);
	
	public int enableStudent(int studentId);
	
	public boolean mailExists(String mail);
	
	public boolean isMailValid(String mail);
	
	public StudentModel entity2model(Student student);
	
	public StudentModel getStudentByName(String name);
	
	public List<ServicioModel> getServiceByStudentProfesionalFamily(int id);
	
	public StudentModel getStudentByEmail(String email);
	
	public List<Student> getStudentsOrderedByValorationAsc();
	
	public List<Student> getStudentsOrderedByValorationDesc();
	
	public List<Student> getStudentsOrderedByServiceAmount();

	List<Student> getAdminScreenFilterBy(String opcion);

}
