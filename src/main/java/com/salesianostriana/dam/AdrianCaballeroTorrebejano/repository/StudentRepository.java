package com.salesianostriana.dam.AdrianCaballeroTorrebejano.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salesianostriana.dam.AdrianCaballeroTorrebejano.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
