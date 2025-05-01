package com.salesianostriana.dam.service;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.base.service.BaseService;
import com.salesianostriana.dam.model.Course;
import com.salesianostriana.dam.repository.CourseRepository;

@Service
public class CourseService extends BaseService<Course, Long, CourseRepository> {

}
