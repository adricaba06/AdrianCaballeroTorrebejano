package com.salesianostriana.dam.AdrianCaballeroTorrebejano.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.salesianostriana.dam.AdrianCaballeroTorrebejano.model.Course;
import com.salesianostriana.dam.AdrianCaballeroTorrebejano.model.Student;
import com.salesianostriana.dam.AdrianCaballeroTorrebejano.service.CourseService;
import com.salesianostriana.dam.AdrianCaballeroTorrebejano.service.StudentService;


@Controller
public class StudentController {

	@Autowired
	private StudentService studentService;
	@Autowired
	private CourseService courseService;

	@GetMapping("/students")
	public String showStudents(Model model, @RequestParam(name = "sort", required = false) String sortBy) {
		model.addAttribute("student", new Student());

		List<Student> students = studentService.findAll();  
		List<Course> courses = courseService.findAll();
		List<Student> activeStudents = studentService.filterActiveStudents(sortBy);
		
		
		model.addAttribute("activeStudents", activeStudents);
		model.addAttribute("students", students);
		model.addAttribute("courses", courses);
		
		return "students";

	}

	@PostMapping("/savestudent")
	public String saveStudent(@ModelAttribute("student") Student student, @RequestParam(required = false) Long courseId,
			@RequestParam("file") MultipartFile pic,
			@RequestParam(value = "existingPhoto", required = false) String existingPhoto, @RequestParam(value = "resgDate", required = false) LocalDate registrationDate) {
		
		LocalDate today = LocalDate.now();
		byte[] bytesImg;
		String absolutePath;
		Path imageDirectory;
		Path completePath;
		
		if (student.getId() == null) {
	        student.setRegistrationDate(today); 
	    } else {
	        if (registrationDate != null) {
	            student.setRegistrationDate(registrationDate); 
	        } else {
	            student.setRegistrationDate(today);
	        }
	    }

		if (courseId != null) {
			Optional<Course> courseO = courseService.findById(courseId);
			if (courseO.isPresent()) {
				Course course = courseO.get();
				student.addToCourse(course);

			}
		}

		if (!pic.isEmpty()) { // https://youtu.be/df67kmObW7M?si=d3cDkO18vU_Ni7wz

			imageDirectory = Paths.get("src//main//resources//static/pictures"); // relative path
			absolutePath = imageDirectory.toFile().getAbsolutePath();

			// Now i need to know the bytes of the picture
			try {
				bytesImg = pic.getBytes();
				completePath = Paths.get(absolutePath + "//" + pic.getOriginalFilename()); // specific path for the
																							// field
				Files.write(completePath, bytesImg); // this saves the files on the folder
				student.setPhotoPath(pic.getOriginalFilename().replaceAll("^/+", ""));
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else {
			if (pic.isEmpty() || pic == null) {
				student.setPhotoPath("defaultPicture.jpg");
			} else {
				student.setPhotoPath("existingPhoto");
			}
		}

		studentService.save(student);
		return "redirect:/students";

	}

	@GetMapping("/student/{id}")
	public String viewStudent(@PathVariable Long id, Model model) {
		Optional<Student> studentO = studentService.findById(id);
		Student student = null;
		if(studentO.isPresent()) {
			student = studentO.get();
		}
		
		List<Course> courses = courseService.findAll();
		
		model.addAttribute("student", student);
		model.addAttribute("courses", courses);
		
		
		return "student_detail";
	}

}
