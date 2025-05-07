package com.salesianostriana.dam.AdrianCaballeroTorrebejano.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.salesianostriana.dam.AdrianCaballeroTorrebejano.model.Course;
@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
	
}
