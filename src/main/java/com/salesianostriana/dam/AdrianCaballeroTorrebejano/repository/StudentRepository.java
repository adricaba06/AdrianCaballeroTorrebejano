package com.salesianostriana.dam.AdrianCaballeroTorrebejano.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.salesianostriana.dam.AdrianCaballeroTorrebejano.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

	@Query("SELECT s FROM student where s.name = :name OR s.surname = :surname")
	public List<Student> findByStudentsNameAndSurname(@Param("name") String name, @Param("surname") String surname);
	
}
