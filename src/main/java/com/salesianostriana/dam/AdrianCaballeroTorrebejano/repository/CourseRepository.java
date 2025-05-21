package com.salesianostriana.dam.AdrianCaballeroTorrebejano.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.salesianostriana.dam.AdrianCaballeroTorrebejano.model.Course;
@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
	
	@Query("SELECT c FROM Course c WHERE c.active = false")
    List<Course> findInactiveCourses();
}
