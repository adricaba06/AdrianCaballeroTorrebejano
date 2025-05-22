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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.salesianostriana.dam.AdrianCaballeroTorrebejano.model.Course;
import com.salesianostriana.dam.AdrianCaballeroTorrebejano.model.Fee;
import com.salesianostriana.dam.AdrianCaballeroTorrebejano.model.FeeSetting;
import com.salesianostriana.dam.AdrianCaballeroTorrebejano.model.Student;
import com.salesianostriana.dam.AdrianCaballeroTorrebejano.service.CourseService;
import com.salesianostriana.dam.AdrianCaballeroTorrebejano.service.FeeService;
import com.salesianostriana.dam.AdrianCaballeroTorrebejano.service.FeeSettingService;
import com.salesianostriana.dam.AdrianCaballeroTorrebejano.service.StudentService;

@Controller
public class FeeController {

	@Autowired
	private FeeService feeService;

	@Autowired
	private StudentService studentService;

	@Autowired
	private CourseService courseService;

	@Autowired
	private FeeSettingService feeSettingService;

	@GetMapping("/reports")
	public String showReports(Model model, @RequestParam(name = "sort", required = false) String sortBy,
			@RequestParam(required = false) String nameParam, @RequestParam(required = false) boolean complete,
			@RequestParam(required = false) Long courseId) {

		double total = 0, ocupationPercent = 0, averageGrade = 0;

		Course course = null;
	
		if (courseId != null) {
			ocupationPercent = Math.round(courseService.getPercentOfOcupation(courseId));
			Optional<Course> courseO = courseService.findById(courseId);
			if (courseO.isPresent()) {
				course = courseO.get();
			}
			averageGrade = studentService.calculateAverageGradeOfTheWholeAcademy(courseId);
		}

		List<Student> activeStudents = studentService.filterActiveStudents(sortBy, complete);
		List<Course> activeCourses = courseService.showActiveCourses();
		List<Student> studentFromCourse = studentService.filterStudentsByCourseId(courseId);
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

		model.addAttribute("ocupationPercent", ocupationPercent);
		
		
		if (studentFromCourse.isEmpty() || Double.isNaN(averageGrade) || Double.isInfinite(averageGrade)) {
		    model.addAttribute("average", null);
		} else {
		    model.addAttribute("average", averageGrade);
		}


		model.addAttribute("course", course);
		model.addAttribute("basePrice", feeService.roundTwoDecimals(feeSettingService.getCurrentSetting().getBasePrice()));
		model.addAttribute("siblingDis", feeService.roundTwoDecimals(feeSettingService.getCurrentSetting().getSiblingDiscount()));
		model.addAttribute("earlyDis", feeService.roundTwoDecimals(feeSettingService.getCurrentSetting().getEarlyRegistrationDiscount()));
		model.addAttribute("daysForDis", feeSettingService.getCurrentSetting().getDaysBeforeCourseStartsSetByUser());
		
		return "reports";
	}

	@PostMapping("/saveFeeData")
	public String saveFeeData(Fee fee) {
		feeService.save(fee);
		return "reports";

	}

	@GetMapping("showPricesForm")
	public String showPricesForm(Model model) {
		model.addAttribute("feePrices", feeSettingService.getCurrentSetting());
		return "pricesForm";
	}

	@PostMapping("/savePrices")
	public String setPrices(@ModelAttribute("feePrices") FeeSetting updatedRules) {
		feeSettingService.updateSetting(updatedRules);
		List<Student> students = studentService.findAll();
		long daysUntilCourseStarts;
		Fee newFee;
		for (Student student : students) {
			if (student.getCourse() != null && student.getCourse().getEndDate() != null && student.getFee().getId() != null) {

				daysUntilCourseStarts = ChronoUnit.DAYS.between(LocalDate.now(), student.getCourse().getStartDate());
				newFee = feeService.generateConvenientFee(student, daysUntilCourseStarts);
				studentService.save(student);
			} else {
				newFee = new Fee();
				newFee.setBasePrice(0);
				newFee.setFinalPrice(0);
			}
			newFee.setId(student.getFee().getId());
			newFee.setStudent(student);
			feeService.save(newFee);
			student.setFee(newFee);
		}

		return "redirect:/reports";
	}

}
