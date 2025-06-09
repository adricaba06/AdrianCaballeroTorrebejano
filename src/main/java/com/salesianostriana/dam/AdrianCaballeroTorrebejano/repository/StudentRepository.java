package com.salesianostriana.dam.AdrianCaballeroTorrebejano.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;

import com.salesianostriana.dam.AdrianCaballeroTorrebejano.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

	@Query("SELECT s FROM Student s WHERE (LOWER(s.name) LIKE LOWER(CONCAT('%', :name, '%')) OR LOWER(s.surname) LIKE LOWER(CONCAT('%', :name, '%'))) AND s.active = true")
	List<Student> findByStudentsNameAndSurname(@Param("name") String name);
	
	List<Student> findByNameAndActiveTrue(String name);

	
	@Query("SELECT s FROM Student s WHERE s.active = false")
	List<Student> findInactiveStudents();
	
	@Query("SELECT s FROM Student s WHERE s.course.id <> :courseId AND s.active = true")
	Page<Student> findAllByCourseIdNotAndActiveTrue(@Param("courseId") Long courseId, Pageable pageable);
	
	@Query("SELECT s FROM Student s WHERE s.fee.id = :feeId")
	List<Student> findAllByFeeId(@Param("feeId") Long feeId);

	Page<Student> findByActiveTrue(Pageable paginacion);
	
	
}                                 
