package com.salesianostriana.dam.AdrianCaballeroTorrebejano.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.salesianostriana.dam.AdrianCaballeroTorrebejano.model.Course;
import com.salesianostriana.dam.AdrianCaballeroTorrebejano.model.Student;
import com.salesianostriana.dam.AdrianCaballeroTorrebejano.service.CourseService;
import com.salesianostriana.dam.AdrianCaballeroTorrebejano.service.StudentService;

import jakarta.persistence.EntityNotFoundException;

@Controller
@RequestMapping("/courses")
public class CourseController {

	@Autowired
	private CourseService courseService;
	@Autowired
	private StudentService studentService;

	@GetMapping("/dashboard")
	public String showDashboard(Model model) {
		model.addAttribute("course", new Course());
		List<Course> courses = courseService.showActiveCourses();
		model.addAttribute("courses", courses);

		return "dashboard";
	}

	@PostMapping("/newcourse")
	public String saveCourse(@ModelAttribute("course") Course course) {
		courseService.save(course);
		return "redirect:/courses/dashboard";
	}

	@GetMapping("/course/{id}")
	public String viewCourse(@PathVariable Long id, @RequestParam(name = "filter", required = false) String filter,
			Model model,
		    @RequestParam(name = "sort", required = false) String sortBy,
		    @RequestParam(name = "ascending", defaultValue = "true") boolean ascending,
		    @RequestParam(name = "courseId", required = false) Long courseId,
		    @RequestParam(required = false) String nameParam,
		    Pageable pageable
) {
		double percent, percentOfPassingStudents, percentOfFailingStudents;

		Optional<Course> courseO = courseService.findById(id);
		Course course = null;
		if (courseO.isPresent()) {
			course = courseO.get();
		}
		
		Page<Student> otherStudents = studentService.filterActiveStudentsFromOtherCourses(pageable, sortBy, ascending, id);
		List<Student> filteredStudentList = studentService.filterStudentsByCourseId(id);
		List<Student> maxOrMinStudents = studentService.filterMaxOrMinByParam(id, filter);
		filteredStudentList.forEach((s) -> s.setAverage(s.getAverageGrade()));

		percent = courseService.compareFinalDateToToday(course.getStartDate(), course.getEndDate());

		percentOfPassingStudents = studentService.getPassPercent(id);
		percentOfFailingStudents = studentService.getFailPercent(id);

		model.addAttribute("percent", percent);
		model.addAttribute("passingS", percentOfPassingStudents);
		model.addAttribute("failingS", percentOfFailingStudents);
		model.addAttribute("course", course);
		model.addAttribute("students", filteredStudentList);
		model.addAttribute("topOrLowestStudent", maxOrMinStudents);
		model.addAttribute("otherStudents", otherStudents);
		
		model.addAttribute("sortBy", sortBy); 
	    model.addAttribute("ascending", ascending);
	    model.addAttribute("nameParam", nameParam);
	    model.addAttribute("size", pageable.getPageSize()); 
	    model.addAttribute("currentPage", pageable.getPageNumber());

		return "curso";
	}

	@PostMapping("/archiveCourse/{id}")
	public String archiveCourse(@PathVariable Long id, Model mode) {
		Optional<Course> course = courseService.findById(id);
		if (course.isPresent()) {
			course.get().setActive(false);
			courseService.save(course.get());
			courseService.moveStudents(id);
			return "redirect:/courses/dashboard";
		} else {
			return "redirect:/courses/dashboard";
		}

	}

	@GetMapping("/showArchiveCourses")
	public String showArchiveCourses(Model model) {
		model.addAttribute("list", courseService.findInactiveCourses());
		return "archived";
	}
	
	@PostMapping("/course/delete/{id}")
	public String deleteCourse(@PathVariable Long id) {
	    courseService.deleteById(id);
	    return "redirect:/courses/showArchiveCourses";
	}
	
	@PostMapping("/course/reactivate/{id}")
	public String reactivateCourse(@PathVariable Long id) {
	    courseService.reactivateById(id);
	    return "redirect:/courses/showArchiveCourses";
	}
	
	@PostMapping("/course/changeStudentCourse")
	public String changeStudentCourse(@RequestParam Long courseId, @RequestParam Long studentId) {
		
		Optional<Student> studentO = studentService.findById(studentId);
		Student student = new Student();
		
		if(studentO.isPresent()) {
			student = studentO.get();
		}
		
		courseService.moveStudentsToAnotherCourse(courseId, student);
		return "redirect:/courses/course/" + student.getCourse().getId();
		
	}

}
