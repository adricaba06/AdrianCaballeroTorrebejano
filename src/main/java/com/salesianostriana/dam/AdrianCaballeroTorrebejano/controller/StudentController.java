package com.salesianostriana.dam.AdrianCaballeroTorrebejano.controller;

import java.time.LocalDate;
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
import org.springframework.web.multipart.MultipartFile;

import com.salesianostriana.dam.AdrianCaballeroTorrebejano.model.Course;
import com.salesianostriana.dam.AdrianCaballeroTorrebejano.model.Email;
import com.salesianostriana.dam.AdrianCaballeroTorrebejano.model.Fee;
import com.salesianostriana.dam.AdrianCaballeroTorrebejano.model.Grade;
import com.salesianostriana.dam.AdrianCaballeroTorrebejano.model.Student;
import com.salesianostriana.dam.AdrianCaballeroTorrebejano.service.CourseService;
import com.salesianostriana.dam.AdrianCaballeroTorrebejano.service.EmailService;
import com.salesianostriana.dam.AdrianCaballeroTorrebejano.service.FeeService;
import com.salesianostriana.dam.AdrianCaballeroTorrebejano.service.StudentService;

@Controller
public class StudentController {

	@Autowired
	private StudentService studentService;
	@Autowired
	private CourseService courseService;
	@Autowired
	FeeService feeService;
	@Autowired
	EmailService emailService;

	@GetMapping("/students")
	public String showStudents(Model model, @RequestParam(name = "sort", required = false) String sortBy,
			@RequestParam(required = false) String nameParam, @RequestParam(required = false) boolean complete) {

		model.addAttribute("complete", complete);
		model.addAttribute("student", new Student());

		List<Student> students;
		if (nameParam != null && !nameParam.isEmpty()) {
			students = studentService.findByName(nameParam);
		} else {
			students = studentService.filterActiveStudents(sortBy, complete);
		}

		List<Course> courses = courseService.findAll();
		List<Student> activeStudents = studentService.filterActiveStudents(sortBy, complete);
		List<Student> searchStudent = nameParam != null && !nameParam.isEmpty() ? studentService.findByName(nameParam)
				: new ArrayList<>();

		students.stream().forEach((s) -> s.setAverage(studentService.getAverageGrade(s.getId())));

		model.addAttribute("searchStudent", searchStudent);
		model.addAttribute("activeStudents", activeStudents);
		model.addAttribute("students", students);
		model.addAttribute("courses", courses);
		model.addAttribute("fee", new Fee());

		return "students";

	}

	@PostMapping("/savestudent")
	public String saveStudent(@ModelAttribute("student") Student student, @RequestParam(required = false) Long courseId,
			@RequestParam("file") MultipartFile pic, @RequestParam("feeId") Long feeId,
			@RequestParam(value = "existingPhoto", required = false) String existingPhoto,
			@RequestParam(value = "resgDate", required = false) LocalDate registrationDate) {

		LocalDate today = LocalDate.now();
		Long daysUntilCourseStarts = 0L;

		studentService.validateStudent(student, registrationDate);

		if (feeId != null) {
			Optional<Fee> feeO = feeService.findById(feeId);
			feeO.ifPresent(student::setFee);
		} else {
			student.setFee(null);
		}

		if (courseId != null) {
			Optional<Course> courseO = courseService.findById(courseId);
			if (courseO.isPresent()) {
				Course course = courseO.get();
				student.addToCourse(course);
				courseService.checkOcupation(course);
				daysUntilCourseStarts = studentService.getDaysUntilCourseStart(student.getRegistrationDate(),
						student.getCourse().getStartDate());
			}
		} else {
			daysUntilCourseStarts = 0L;
		}

		studentService.addProfilePicture(pic, existingPhoto, student);
		studentService.save(student);
		return "redirect:/students";

	}

	@GetMapping("/student/{id}")
	public String viewStudent(@PathVariable Long id, Model model) {
		Optional<Student> studentO = studentService.findById(id);
		Student student = null;
		int numOfStudents;
		List<Course> courses = courseService.showActiveCourses();
		List<Fee> fees = feeService.findAllFees();

		numOfStudents = courses.size();
		if (studentO.isPresent()) {
			student = studentO.get();
		}

		model.addAttribute("numS", numOfStudents);
		model.addAttribute("student", student);
		model.addAttribute("courses", courses);
		model.addAttribute("fees", fees);
		model.addAttribute("average", studentService.getAverageGrade(id));

		return "student_detail";
	}

	@GetMapping("/editGradesOfStudent/{id}")
	public String showEditGradesForm(@PathVariable Long id, Model model) {
		Optional<Student> studentO = studentService.findById(id);
		Student student = null;

		if (studentO.isPresent()) {
			student = studentO.get();
		}

		model.addAttribute("student", student);
		model.addAttribute("grade", Grade.values());

		return "grades";

	}

	@PostMapping("/saveStudentsGrades")
	public String saveStudentsGrades(@ModelAttribute("student") Student student) {
		studentService.save(student);
		return "redirect:/courses/course/" + student.getCourse().getId();

	}

	@GetMapping("/students/archived")
	public String showArchivedStudents(Model model) {
		List<Student> archivedStudents = studentService.listInactiveStudents();
		model.addAttribute("archivedStudents", archivedStudents);
		return "archivedStudents";
	}

	@PostMapping("student/activate/{id}")
	public String activateStudent(@PathVariable Long id) {
		studentService.activateStudent(id);

		return "redirect:/students/archived";
	}

	@PostMapping("student/delete/{id}")
	public String deleteStudent(@PathVariable Long id) {
		studentService.deleteStudent(id);
		return "redirect:/students/archived";
	}

	@GetMapping("/sendEmail/{id}")
	public String sendEmail(@PathVariable Long id, Model model) {
		Email email;

		Optional<Student> studentO = studentService.findById(id);
		Student student = null;

		if (studentO.isPresent()) {
			student = studentO.get();
		}

		email = emailService.generateEmail(student);

		if (email != null) {
			emailService.sendEmail(email);
		}
		
		model.addAttribute(student);

		return "redirect:/courses/course/" + student.getCourse().getId();

	}

}
