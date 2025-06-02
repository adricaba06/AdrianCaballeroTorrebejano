package com.salesianostriana.dam.AdrianCaballeroTorrebejano.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.salesianostriana.dam.AdrianCaballeroTorrebejano.model.Fee;
import com.salesianostriana.dam.AdrianCaballeroTorrebejano.model.Student;

@Repository
public interface FeeRepository extends JpaRepository<Fee, Long> {

	List<Fee> findAll();

}
