package com.salesianostriana.dam.AdrianCaballeroTorrebejano.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.salesianostriana.dam.AdrianCaballeroTorrebejano.model.Course;
import com.salesianostriana.dam.AdrianCaballeroTorrebejano.model.Fee;
import com.salesianostriana.dam.AdrianCaballeroTorrebejano.model.Student;
import com.salesianostriana.dam.AdrianCaballeroTorrebejano.service.CourseService;
import com.salesianostriana.dam.AdrianCaballeroTorrebejano.service.FeeService;
import com.salesianostriana.dam.AdrianCaballeroTorrebejano.service.StudentService;

@Controller
public class FeeController {

	@Autowired
	FeeService feeService;

	@Autowired
	StudentService studentService;

	@Autowired
	CourseService courseService;

	@GetMapping("/reports")
	public String showReports(Model model, @RequestParam(name = "sort", required = false) String sortBy,
			@RequestParam(required = false) String nameParam, @RequestParam(required = false) boolean complete,
			@RequestParam(required = false) Long courseId) {
		double total = 0;
		double ocupationPercent = courseService.getPercentOfOcupation(courseId);

		List<Student> activeStudents = studentService.filterActiveStudents(sortBy, complete);
		List<Course> activeCourses = courseService.showActiveCourses();
		List<Student> searchStudent = nameParam != null && !nameParam.isEmpty()
				? studentService.findByNameAndSurname(nameParam)
				: new ArrayList<>();
		total = feeService.calculateTotalEstimated(studentService.filterActiveStudents(sortBy, true));

		model.addAttribute("complete", complete);
		model.addAttribute("searchStudent", searchStudent);
		model.addAttribute("activeStudents", activeStudents);
		model.addAttribute("fee", new Fee());
		model.addAttribute("total", total);
		model.addAttribute("ocupationPercent", ocupationPercent);
		model.addAttribute("activeCourse", activeCourses);
		return "reports";
	}

	@PostMapping("/saveFeeData")
	public String saveFeeData(Fee fee) {
		feeService.save(fee);
		return "reports";

	}

}
