package com.salesianostriana.dam.AdrianCaballeroTorrebejano.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.salesianostriana.dam.AdrianCaballeroTorrebejano.model.Course;
import com.salesianostriana.dam.AdrianCaballeroTorrebejano.service.CourseService;

import jakarta.persistence.EntityNotFoundException;

@Controller
public class CourseController {

	@Autowired
	private CourseService courseService;

	private double percent;

	// Controller para mostrar una lista

	@GetMapping("/dashboard")
	public String showDashboard(Model model) {
		model.addAttribute("course", new Course());

		List<Course> courses = courseService.listAll();
		model.addAttribute("courses", courses);

		return "dashboard";
	}

	@PostMapping("/newcourse")
	public String saveCourse(@ModelAttribute("course") Course course) {
		courseService.save(course);
		return "redirect:/dashboard";
	}

	@GetMapping("/course/{id}")
	public String viewCourse(@PathVariable Long id, Model model) {
		Course course = courseService.findById(id).get();
		percent = courseService.compareFinalDateToToday(course.getStartDate(), course.getEndDate());
		model.addAttribute("percent", percent);
		model.addAttribute("course", course);

		return "curso";
	}
	
	@PostMapping("/archiveCourse")
	public String archiveCourse(@PathVariable Long id, Model mode) {
		Course course = courseService.findById(id).get();
		course.setActive(false);
		return "redirect:/dashboard" ;
		
	}


}
