package com.salesianostriana.dam.AdrianCaballeroTorrebejano.controller;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
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
	public String showReports(
	    Model model,
	    @RequestParam(name = "sort", required = false) String sortBy,
	    @RequestParam(required = false) String nameParam,
	    @RequestParam(required = false) boolean complete,
	    @RequestParam(required = false) Long ocupacionCourseId,
	    @RequestParam(required = false) Long mediaCourseId
	) {
	    double total = 0, ocupationPercent = 0, averageGrade = 0;

	    Course courseOcupacion = new Course();
	    Course courseMedia = new Course();

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
	        averageGrade = studentService.calculateAverageGradeOfTheWholeAcademy(mediaCourseId);
	    }

	    List<Fee> allFees = feeService.findAllFeesExceptDefault();
	    List<Student> activeStudents = studentService.filterActiveStudents(sortBy, complete);
	    List<Course> activeCourses = courseService.showActiveCourses();
	    List<Student> studentFromCourse = studentService.filterStudentsByCourseId(ocupacionCourseId); // o null
	    List<Student> searchStudent = nameParam != null && !nameParam.isEmpty()
	            ? studentService.findByName(nameParam)
	            : new ArrayList<>();
	    total = feeService.calculateTotalEstimated(studentService.filterActiveStudents(sortBy, true));

	    model.addAttribute("complete", complete);
	    model.addAttribute("searchStudent", searchStudent);
	    model.addAttribute("activeStudents", activeStudents);
	    model.addAttribute("activeCourses", activeCourses);
	    model.addAttribute("fee", new Fee());
	    model.addAttribute("total", total);
	    model.addAttribute("fees", allFees);

	    model.addAttribute("ocupationPercent", ocupationPercent);
	    model.addAttribute("courseOcupacion", courseOcupacion);

	  
	    model.addAttribute("average", averageGrade);
	    
	    model.addAttribute("courseMedia", courseMedia);

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
		if(feeO.isPresent()) {
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
		
		if(feeO.isPresent()) {
			fee = feeO.get();
		}
		
		feeService.changeFee(id);
		
		
		feeService.delete(fee);
		return "redirect:/reports";
		
	}
	

}
