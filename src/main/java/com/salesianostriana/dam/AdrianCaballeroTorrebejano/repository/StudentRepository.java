package com.salesianostriana.dam.AdrianCaballeroTorrebejano.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.salesianostriana.dam.AdrianCaballeroTorrebejano.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

	@Query("SELECT s FROM Student s WHERE s.name = :name AND s.active = true")
	List<Student> findByStudentsNameAndSurname(@Param("name") String name);
	
	@Query("SELECT s FROM Student s WHERE s.active = false")
	List<Student> findInactiveStudents();
	
}                                 
