package com.salesianostriana.dam.AdrianCaballeroTorrebejano.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salesianostriana.dam.AdrianCaballeroTorrebejano.model.Fee;

@Repository
public interface FeeRepository extends JpaRepository<Fee, Long> {
	

}
