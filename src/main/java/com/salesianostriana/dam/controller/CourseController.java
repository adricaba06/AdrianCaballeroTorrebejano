package com.salesianostriana.dam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.salesianostriana.dam.model.Course;
import com.salesianostriana.dam.service.CourseService;

@Controller
public class CourseController {
	
	@Autowired
	private CourseService courseService;
	
	//Controller para mostrar una lista 
	
	@GetMapping("/")
	public String showDashboard (Model model) {
		model.addAttribute("courses", courseService.listAll());
		return "dashboard";
	}

	

	


}
