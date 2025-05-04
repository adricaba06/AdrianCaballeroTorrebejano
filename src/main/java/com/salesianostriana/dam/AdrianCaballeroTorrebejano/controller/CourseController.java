package com.salesianostriana.dam.AdrianCaballeroTorrebejano.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.salesianostriana.dam.AdrianCaballeroTorrebejano.model.Course;
import com.salesianostriana.dam.AdrianCaballeroTorrebejano.service.CourseService;

@Controller
public class CourseController {
	
	@Autowired
    private CourseService courseService;
	
	//Controller para mostrar una lista 
	
	@GetMapping("/dashboard")
	public String showDashboard(Model model) {
	    model.addAttribute("course", new Course()); 

	    List<Course> courses = courseService.listAll(); 
	    model.addAttribute("courses", courses); 

	    return "dashboard";
	}
	
	  @PostMapping("/newcourse")
	    public String createCourse(@ModelAttribute("course") Course course) {
	        courseService.save(course); 
	        return "redirect:/dashboard"; 
	    }
	
	

}
