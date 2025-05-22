package com.salesianostriana.dam.AdrianCaballeroTorrebejano.base.service;

import java.util.List;
import java.util.Optional;

import java.util.List;
import java.util.Optional;

public interface BaseService<T, ID> {
	
	List<T> findAll();
	
	Optional<T> findById(ID id);
	
	T save(T t);
	
	T edit(T t);
	
	void delete(T t);
	
	void deleteById(ID id);
	
	public double calculatePercent(double num1, double num2);
	
	double roundTwoDecimals(double value);
	
	
	

}
