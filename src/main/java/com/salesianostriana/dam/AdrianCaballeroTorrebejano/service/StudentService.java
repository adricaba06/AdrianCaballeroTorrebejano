package com.salesianostriana.dam.AdrianCaballeroTorrebejano.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesianostriana.dam.AdrianCaballeroTorrebejano.base.service.BaseService;
import com.salesianostriana.dam.AdrianCaballeroTorrebejano.model.Student;
import com.salesianostriana.dam.AdrianCaballeroTorrebejano.repository.StudentRepository;

@Service
public class StudentService extends BaseService<Student, Long, StudentRepository> {

	@Autowired
	private StudentRepository sr;
	

	public List<Student> filterStudentsByCourseId(Long id) {
		
		return listAll().stream()
				.filter((s) -> s.getCourse() != null && s.getCourse().getId().equals(id) && s.isActive())
				.collect(Collectors.toList());
	}

	public List<Student> filterActiveStudents() {
		return sr.findAll().stream().filter((s) -> s.isActive()).collect(Collectors.toList());

	}
}
