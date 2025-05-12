 package com.salesianostriana.dam.AdrianCaballeroTorrebejano.base.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public abstract class BaseService<T, ID, R extends JpaRepository<T, ID>> {

    @Autowired
    protected R repository;

    // CRUD ---------------------------------------------------------------
    public T save(T t) {
        // Funciona tanto para añadir como para editar
        return repository.save(t);
    }

    public Optional<T> findById(ID id) {
        return repository.findById(id);
    }

    public List<T> listAll() {
        return repository.findAll();
    }

    public void delete(T t) {
        repository.delete(t);
    }

    public void deleteById(ID id) {
        repository.deleteById(id);
    }
    
    public double calculatePercent(double num1, double num2) {
    	return (num1/num2)*100;
    	
    }
    

    // Métodos adicionales personalizados pueden ir aquí si es necesario
    // Por ejemplo, un método para buscar por un campo específico

}

