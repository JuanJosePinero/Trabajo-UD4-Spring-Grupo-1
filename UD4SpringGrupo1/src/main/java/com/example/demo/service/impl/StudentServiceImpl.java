package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.ProFamily;
import com.example.demo.entity.Servicio;
import com.example.demo.entity.Student;
import com.example.demo.model.ServicioModel;
import com.example.demo.model.StudentModel;
import com.example.demo.repository.ProFamilyRepository;
import com.example.demo.repository.ServicioRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.StudentService;

@Configuration
@Service("studentService")
public class StudentServiceImpl implements StudentService, UserDetailsService {

	@Autowired
	@Qualifier("studentRepository")
	private StudentRepository studentRepository;
	
	@Autowired
	@Qualifier("proFamilyRepository")
	private ProFamilyRepository proFamilyRepository;
	
	@Autowired
	@Qualifier("servicioRepository")
	private ServicioRepository servicioRepository;
	
	@Autowired
	@Qualifier("servicioService")
	private ServicioServiceImpl servicioService;

	public Student model2entity(StudentModel studentModel) {
		ModelMapper mapper = new ModelMapper();
		return mapper.map(studentModel, Student.class);
	}
	@Override
	public StudentModel entity2model(Student student) {
	    ModelMapper mapper = new ModelMapper();
	    mapper.createTypeMap(Student.class, StudentModel.class)
	            .addMapping(src -> ((Student) src).getEnabled(), StudentModel::setEnabled);
	    StudentModel studentModel = mapper.map(student, StudentModel.class);
	    return studentModel;
	}


	@Override
	public Student register(StudentModel studentModel) {
		Student student = model2entity(studentModel);
		student.setPassword(passwordEncoder().encode(student.getPassword()));
		student.setEnabled(0);
		student.setRole("ROLE_STUDENT");
		return studentRepository.save(student);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public List<StudentModel> listAllStudents() {
	    List<StudentModel> students = new ArrayList<>();
	    for (Student s : studentRepository.findAll()) {
	        students.add(entity2model(s));
	    }
	    return students;
	}


	@Override
	public int deleteStudent(int id) {
		Student student = studentRepository.findById(id);
    	student.setDeleted(1);
    	studentRepository.save(student);
    	return 1;
    }

	@Override
	public Student updateStudent(StudentModel studentModel) {
		 Student student = studentRepository.findById(studentModel.getId());
		 student.setName(studentModel.getName());
		 student.setSurname(studentModel.getSurname());
		 student.setEmail(studentModel.getEmail()); 
		 student.setProfesionalFamily(proFamilyRepository.findById(studentModel.getProfesionalFamily().getId())); 
		 return studentRepository.save(student);
	 }

	@Override
	public boolean login(String email, String password) {
		Student student = studentRepository.findByEmail(email);
		return student != null && passwordEncoder().matches(password, student.getPassword());
	}

	@Override
	public boolean authenticate(String email, String password) {
		return false;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		com.example.demo.entity.Student student = studentRepository.findByEmail(username);
		UserBuilder builder = null;
		
		if (student != null) {
			if (student.getEnabled() == 0) {
				return User.withUsername(student.getName())
	                    .disabled(true)
	                    .password(student.getPassword())
	                    .authorities(new SimpleGrantedAuthority(student.getRole()))
	                    .build();
	        
	        }
			builder = User.withUsername(student.getName());
			builder.disabled(false);
			builder.password(student.getPassword());
			builder.authorities(new SimpleGrantedAuthority(student.getRole()));
		} else
			throw new UsernameNotFoundException("Student not found or account is not activated");
		return builder.build();
	}
	
	
	public UserDetails loadUserByEmail(String email) throws UsernameNotFoundException {
		com.example.demo.entity.Student student = studentRepository.findByEmail(email);
		UserBuilder builder = null;

		if (student != null) {
			builder = User.withUsername(email);
			builder.disabled(false);
			builder.password(student.getPassword());
			builder.authorities(new SimpleGrantedAuthority(student.getRole()));
		} else
			throw new UsernameNotFoundException("Student not found");
		return builder.build();
	}

	@Override
	public StudentModel getStudentById(int id) {
		Student student = studentRepository.findById(id);
		return entity2model(student);
	}

	@Override
	public int enableStudent(int studentId) {
	    Student student = studentRepository.findById(studentId);
	        student.setEnabled(student.getEnabled() == 0 ? 1 : 0);
	        studentRepository.save(student);
	        return student.getEnabled();
	}


	@Override
	public List<StudentModel> listAllEnabledOrDisabledStudents() {
		List<Student> allStudents = studentRepository.findAllByEnabledIn(Arrays.asList(0, 1));
	    List<StudentModel> studentModels = allStudents.stream()
	            .map(this::entity2model)
	            .collect(Collectors.toList());
	    return studentModels;
	}

	@Override
	public boolean mailExists(String mail) {
		List<Student> students = new ArrayList<>();
		for (Student student : students) {
			if(student.getUsername() == mail)
				return true;
		}
		return false;
	}

	@Override
	public boolean isMailValid(String mail) {
		String mailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
		return mail.matches(mailRegex);
	}
	@Override
	public StudentModel getStudentByName(String name) {
		Student student=studentRepository.findByName(name);
		return entity2model(student);
	}
	@Override
	public List<ServicioModel> getServiceByStudentProfesionalFamily(int id) {
		Student student=studentRepository.findById(id);
		List<ServicioModel>servicioLista=new ArrayList<>();
		List<Servicio>services=servicioRepository.findByProfesionalFamilyId(student.getProfesionalFamily());
		for(Servicio servicio: services) {
			servicioLista.add(servicioService.entity2model(servicio));
		}
		return servicioLista;
	}
	@Override
	public StudentModel getStudentByEmail(String email) {
		return entity2model(studentRepository.findByEmail(email));
	}
	@Override
	public List<Student> getStudentsOrderedByValorationAsc() {  
        List<Student> students = studentRepository.findAll();
        List<Student> studentsWithRatedServices = new ArrayList<>();
        for (Student student : students) {
        	if(!student.getServicios().isEmpty())
        		studentsWithRatedServices.add(student);	
		}
        studentsWithRatedServices.sort(Comparator.comparingDouble(this::calculateAverageRating).reversed());
        return studentsWithRatedServices;
    }
	
	@Override
	public List<Student> getStudentsOrderedByValorationDesc() {  
        List<Student> students = studentRepository.findAll();
        List<Student> studentsWithRatedServices = new ArrayList<>();
        for (Student student : students) {
        	if(!student.getServicios().isEmpty())
        		studentsWithRatedServices.add(student);	
		}
        studentsWithRatedServices.sort(Comparator.comparingDouble(this::calculateAverageRating));
        return studentsWithRatedServices;
    }

    private double calculateAverageRating(Student student) {
    	List<Servicio> studentServices = student.getServicios();
    	double suma = 0;
    	double cont = 0;
    	for (Servicio servicio : studentServices) {
    		suma += servicio.getValoration();
    		cont++;	
		}
    	double average = suma / cont;
    	return average;
    }	
    
    @Override
    public List<Student>getStudentsOrderedByServiceAmount(){
    	List<Student> students = studentRepository.findAll();
        List<Student> studentsWithServices = new ArrayList<>();
        for (Student student : students) {
        	if(!student.getServicios().isEmpty())
        		studentsWithServices.add(student);	
		}
        studentsWithServices.sort(Comparator.comparingInt(this::getNumberOfServices).reversed());
        return studentsWithServices;
    	
    }
    
    private int getNumberOfServices(Student student) {
    	List<Servicio> studentServices = student.getServicios();
    	return studentServices.size();
    }	
    
    
    @Override
    public List<Student> getAdminScreenFilterBy(String filterBy, String opcion) {
        List<Student> students = new ArrayList<>();

        if (!opcion.equalsIgnoreCase("null")) {
            if ("MorePositiveValorations".equals(opcion)) {
                students = getStudentsOrderedByValorationAsc();
            } else if ("LessPositiveValorations".equals(opcion)) {
                students = getStudentsOrderedByValorationDesc();
            } else if ("NumberOfServices".equals(opcion)) {
                students = getStudentsOrderedByServiceAmount();
            } else {
                students = studentRepository.findAll();
            }
        } else {
        	  students = studentRepository.findAll();
        }

        if (!filterBy.equalsIgnoreCase("null")) {
            students = students.stream()
                    .filter(student -> student.getProfesionalFamily() != null &&
                            student.getProfesionalFamily().getId() == Integer.parseInt(filterBy))
                    .collect(Collectors.toList());
        }

        return students;
    }
    
	@Override
	public List<Student> getStudentsByProFamily(int proFamilyId) {
    	ProFamily proFam = proFamilyRepository.findById(proFamilyId);
    	System.out.println("Familia profesional "+proFam);
		List<Student> students = studentRepository.findByProfesionalFamily(proFam);
		return students;
	}



    
}



