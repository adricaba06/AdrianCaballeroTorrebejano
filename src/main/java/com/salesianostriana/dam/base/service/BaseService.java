package com.salesianostriana.dam.base.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class BaseService<T, ID, R extends JpaRepository<T, ID>> {

	@Autowired
	protected R repository;
	
	//CRUD---------------------------------------------------------------

	public T save(T t) { // funcionaria tanto como añadir como editar ya que si ve que el id es el mismo
							// lo machaca
		return repository.save(t);
	}

	public T edit(T t) { // no es redundante?? es por organizacion??
		return repository.save(t);
	}

	public Optional<T> findById(ID id) {
		return repository.findById(id);
	}

	public List<T> listAll() {
		return repository.findAll();
	}

	// borrado
	public void delete(T t) {
		repository.delete(t);
	}

	public void deleteById(ID id) {
		repository.deleteById(id);
	}
	
	//-------------------------------
	
	
	

}
