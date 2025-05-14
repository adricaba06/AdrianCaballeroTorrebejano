package com.salesianostriana.dam.AdrianCaballeroTorrebejano.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesianostriana.dam.AdrianCaballeroTorrebejano.base.service.BaseService;
import com.salesianostriana.dam.AdrianCaballeroTorrebejano.base.service.BaseServiceImpl;
import com.salesianostriana.dam.AdrianCaballeroTorrebejano.model.Student;
import com.salesianostriana.dam.AdrianCaballeroTorrebejano.repository.StudentRepository;

@Service
public class StudentService extends BaseServiceImpl<Student, Long, StudentRepository> {
	
	@Autowired
	StudentRepository sr;
	
	public List<Student> filterStudentsByCourseId(Long id) {
		
		return findAll().stream()
				.filter((s) -> s.getCourse() != null && s.getCourse().getId().equals(id) && s.isActive())
				.collect(Collectors.toList());
	}

	public List<Student> filterActiveStudents(String sortBy) {
		List<Student> activeStudents = findAll().stream().filter((s) -> s.isActive()).collect(Collectors.toList());
		
		   if (sortBy == null) {
		        return activeStudents;
		    }
		   
		switch(sortBy) {
		case "date":
			activeStudents.sort(Comparator.comparing(Student::getRegistrationDate));
			break;
			
		case "alfabeDes":
			activeStudents.sort(Comparator.comparing(Student::getName).reversed());
			break;
		
		
		case "grades":
			activeStudents.sort(Comparator.comparing(Student::getAverageGrade));
			break;
		
		default: 
			Collections.sort(activeStudents);
		}

		return activeStudents;

	}
	
	public double getAverageGrade(Long id) {
		double summary = 0;
		Optional<Student> studentO = findById(id);
		Student student = null;
		if(studentO != null) {
			student = studentO.get();
		}
		
		if(student.getGrades().isEmpty()) {
			return 0.0;
		}
		
		for (double grade : student.getGrades().values()) {
			summary += grade;
			
		}
		return summary / student.getGrades().size();
		
	}
	
	public List<Student> findByNameAndSurname(String name, String surname){
		return sr.findByStudentsNameAndSurname(name, surname);
		
	}
	
}
