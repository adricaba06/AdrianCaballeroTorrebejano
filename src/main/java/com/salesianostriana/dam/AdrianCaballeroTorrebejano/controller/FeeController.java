package com.salesianostriana.dam.AdrianCaballeroTorrebejano.controller;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.salesianostriana.dam.AdrianCaballeroTorrebejano.model.Course;
import com.salesianostriana.dam.AdrianCaballeroTorrebejano.model.Fee;
import com.salesianostriana.dam.AdrianCaballeroTorrebejano.model.Student;
import com.salesianostriana.dam.AdrianCaballeroTorrebejano.service.CourseService;
import com.salesianostriana.dam.AdrianCaballeroTorrebejano.service.FeeService;
import com.salesianostriana.dam.AdrianCaballeroTorrebejano.service.StudentService;

@Controller
public class FeeController {

	@Autowired
	private FeeService feeService;

	@Autowired
	private StudentService studentService;

	@Autowired
	private CourseService courseService;

	@GetMapping("/reports")
	public String showReports(Model model, @RequestParam(name = "sort", required = false) String sortBy,
			@RequestParam(required = false) String nameParam, @RequestParam(defaultValue = "true") boolean ascending,
			Pageable pageable, @RequestParam(required = false) Long ocupacionCourseId,
			@RequestParam(required = false) Long mediaCourseId) {

		double total = 0, ocupationPercent = 0, averageGrade = 0;

		Course courseOcupacion = new Course();
		Course courseMedia = new Course();
		List<Student> students;

		if (nameParam != null && !nameParam.isEmpty()) {
			students = studentService.findByName(nameParam);
			Page<Student> fakePage = new PageImpl<>(students,
					PageRequest.of(0, students.size() == 0 ? 1 : students.size()), students.size());
			model.addAttribute("page", fakePage);
		} else {
			Page<Student> page = studentService.filterActiveStudents(pageable, sortBy, ascending);
			students = page.getContent();
			model.addAttribute("page", page);
		}

		if (ocupacionCourseId != null) {
			ocupationPercent = Math.round(courseService.getPercentOfOcupation(ocupacionCourseId));
			Optional<Course> courseO = courseService.findById(ocupacionCourseId);
			if (courseO.isPresent()) {
				courseOcupacion = courseO.get();
			}
		}

		if (mediaCourseId != null) {
			Optional<Course> courseO = courseService.findById(mediaCourseId);
			if (courseO.isPresent()) {
				courseMedia = courseO.get();
			}
		}

		List<Fee> allFees = feeService.findAllFeesExceptDefault();
		List<Course> activeCourses = courseService.showActiveCourses();
		total = feeService.calculateTotalEstimated(students);

		averageGrade = studentService.calculateAverageGradeOfTheWholeAcademy(mediaCourseId);

		model.addAttribute("students", students);
		model.addAttribute("activeCourses", activeCourses);
		model.addAttribute("fee", new Fee());
		model.addAttribute("total", total);
		model.addAttribute("fees", allFees);

		model.addAttribute("ocupationPercent", ocupationPercent);
		model.addAttribute("courseOcupacion", courseOcupacion);
		model.addAttribute("average", averageGrade);
		model.addAttribute("courseMedia", courseMedia);

		model.addAttribute("sortBy", sortBy);
		model.addAttribute("ascending", ascending);
		model.addAttribute("nameParam", nameParam);
		model.addAttribute("size", pageable.getPageSize());
		model.addAttribute("currentPage", pageable.getPageNumber());

		return "reports";
	}

	@PostMapping("/saveFeeData")
	public String saveFeeData(@ModelAttribute("fee") Fee fee) {
		feeService.save(fee);
		return "reports";

	}

	@GetMapping("showPricesForm/{id}")
	public String showPricesForm(@PathVariable Long id, Model model) {
		Optional<Fee> feeO = feeService.findById(id);
		Fee fee = null;
		if (feeO.isPresent()) {
			fee = feeO.get();
		}
		model.addAttribute("fee", fee);
		return "pricesForm";
	}

	@GetMapping("/showPricesForm")
	public String showPricesForm(Model model) {
		model.addAttribute("fee", new Fee());
		return "pricesForm";
	}

	@PostMapping("/savePrices")
	public String setPrices(@ModelAttribute("feePrices") Fee fee) {
		feeService.save(fee);
		return "redirect:/reports";
	}

	@PostMapping("/deleteFee/{id}")
	public String deleteFee(@PathVariable Long id) {
		Optional<Fee> feeO = feeService.findById(id);
		Fee fee = new Fee();

		if (feeO.isPresent()) {
			fee = feeO.get();
		}

		feeService.changeFee(id);

		feeService.delete(fee);
		return "redirect:/reports";

	}

}
