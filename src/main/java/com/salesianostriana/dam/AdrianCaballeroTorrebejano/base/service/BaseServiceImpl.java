 package com.salesianostriana.dam.AdrianCaballeroTorrebejano.base.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public abstract class BaseServiceImpl<T, ID, R extends JpaRepository<T, ID>> implements BaseService<T, ID> {

    @Autowired
    protected R repository;

    // CRUD ---------------------------------------------------------------
    @Override
    public T save(T t) {
        return repository.save(t);
    }
    @Override
    public T edit(T t) {
    	return repository.save(t);
    }
    
    public Optional<T> findById(ID id) {
        return repository.findById(id);
    }
    
    @Override
    public List<T> findAll() {
        return repository.findAll();
    }
    

    @Override
    public void delete(T t) {
        repository.delete(t);
    }
    @Override
    public void deleteById(ID id) {
        repository.deleteById(id);
    }
    
    public double calculatePercent(double num1, double num2) {
    	return (num1/num2)*100;
    	
    }
    

    // Métodos adicionales personalizados pueden ir aquí si es necesario
    // Por ejemplo, un método para buscar por un campo específico

}

