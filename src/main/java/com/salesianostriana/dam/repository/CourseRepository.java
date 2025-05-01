package com.salesianostriana.dam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.salesianostriana.dam.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
}
