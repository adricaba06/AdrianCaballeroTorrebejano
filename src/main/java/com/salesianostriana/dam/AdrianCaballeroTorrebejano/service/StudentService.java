package com.salesianostriana.dam.AdrianCaballeroTorrebejano.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.salesianostriana.dam.AdrianCaballeroTorrebejano.base.service.BaseService;
import com.salesianostriana.dam.AdrianCaballeroTorrebejano.base.service.BaseServiceImpl;
import com.salesianostriana.dam.AdrianCaballeroTorrebejano.model.Course;
import com.salesianostriana.dam.AdrianCaballeroTorrebejano.model.Grade;
import com.salesianostriana.dam.AdrianCaballeroTorrebejano.model.Student;
import com.salesianostriana.dam.AdrianCaballeroTorrebejano.repository.StudentRepository;

@Service
public class StudentService extends BaseServiceImpl<Student, Long, StudentRepository> {

	@Autowired
	StudentRepository sr;

	public List<Student> filterStudentsByCourseId(Long id) {

		return findAll().stream()
				.filter((s) -> s.getCourse() != null && s.getCourse().getId().equals(id) && s.isActive())
				.collect(Collectors.toList());
	}

	/*
	 * public List<Student> filterActiveStudents(String sortBy, boolean complete) {
	 * List<Student> activeStudents; activeStudents =
	 * findAll().stream().filter(Student::isActive).collect(Collectors.toList()); if
	 * (sortBy == null) { sortBy = "alfabe"; }
	 * 
	 * switch (sortBy) { case "date":
	 * activeStudents.sort(Comparator.comparing(Student::getRegistrationDate));
	 * break;
	 * 
	 * case "alfabeDes":
	 * activeStudents.sort(Comparator.comparing(Student::getName).reversed());
	 * break;
	 * 
	 * case "grades":
	 * activeStudents.sort(Comparator.comparing(Student::getAverageGrade)); break;
	 * 
	 * case "alfabe": Collections.sort(activeStudents); break;
	 * 
	 * default: Collections.sort(activeStudents); } if (!complete) { return
	 * activeStudents.stream().limit(8).collect(Collectors.toList()); }
	 * 
	 * return activeStudents;
	 * 
	 * }
	 */

	// he refactorizado el metodo
	public Page<Student> filterActiveStudents(Pageable page, String sortBy, boolean ascending) {
		Sort sort;

		if (sortBy == null || sortBy.isBlank()) {
			sortBy = "name";
		}

		switch (sortBy) {
		case "date":
			sort = Sort.by("registrationDate");
			break;
		case "email":
			sort = Sort.by("email");
			break;
		case "name":
			sort = Sort.by("name");
			break;
		case "surname":
		default:
			sort = Sort.by("surname");
			break;
		}

		if (!ascending) {
			sort = sort.descending();
		}

		Pageable sortedPageable = PageRequest.of(page.getPageNumber(), 7, sort);

		return sr.findByActiveTrue(sortedPageable);
	}

	public Page<Student> filterActiveStudentsFromOtherCourses(Pageable page, String sortBy, boolean ascending,
			Long courseId) {
		Sort sort;

		if (sortBy == null || sortBy.isBlank()) {
			sortBy = "name";
		}

		switch (sortBy) {
		case "date":
			sort = Sort.by("registrationDate");
			break;
		case "email":
			sort = Sort.by("email");
			break;
		case "name":
			sort = Sort.by("name");
			break;
		case "surname":
		default:
			sort = Sort.by("surname");
			break;
		}

		if (!ascending) {
			sort = sort.descending();
		}

		Pageable sortedPageable = PageRequest.of(page.getPageNumber(), 7, sort);

		return sr.findAllByCourseIdNotAndActiveTrue(courseId, sortedPageable);
	}

	public Page<Student> filterActiveStudents(Pageable pageable) {
		return sr.findAll(pageable);
	}

	public double getAverageGrade(Long id) {
		Optional<Student> studentO = findById(id);
		Student student = null;
		if (studentO.isPresent())
			student = studentO.get();

		return student.getAverageGrade();

	}

	public List<Student> findByName(String name) {
	    if (name == null || name.isBlank()) {
	        return List.of();
	    }

	    String likeParam = "%" + name.toLowerCase().trim() + "%";
	    return sr.findByStudentsNameAndSurname(likeParam);
	}

	public double getPassPercent(Long id) {
		List<Student> students = filterStudentsByCourseId(id);
		double passingStudentsNumber = 0;
		if (students.isEmpty()) {
			return 0.0;
		}

		passingStudentsNumber = students.stream().filter((s) -> s.getAverageGrade() >= 5).count();

		return calculatePercent(passingStudentsNumber, students.size());

	}

	public double getFailPercent(Long id) {
		List<Student> students = filterStudentsByCourseId(id);
		double failingStudentNumber = 0;
		if (students.isEmpty()) {
			return 0.0;
		}

		failingStudentNumber = students.stream().filter((s) -> s.getAverageGrade() < 5).count();

		return calculatePercent(failingStudentNumber, students.size());

	}

	public List<Student> getStudentWithHighestGrade(Long id) {

		Optional<Double> maxGradeO = filterStudentsByCourseId(id).stream().map(Student::getAverageGrade)
				.max(Double::compare);
		final double maxGrade = maxGradeO.orElse(Double.MIN_VALUE);

		return findAll().stream().filter(s -> s.isActive() && s.getAverageGrade() == maxGrade)
				.collect(Collectors.toList());

	}

	public List<Student> getStudentWithLowestGrade(Long id) {

		Optional<Double> maxGradeO = filterStudentsByCourseId(id).stream().map(Student::getAverageGrade)
				.min(Double::compare);
		final double maxGrade = maxGradeO.orElse(Double.MAX_VALUE);

		return findAll().stream().filter(s -> s.isActive() && s.getAverageGrade() == maxGrade)
				.collect(Collectors.toList());

	}

	public Student changeStudentCourse(Student student, Course course) {
		student.setCourse(course);
		return student;

	}

	public List<Student> filterMaxOrMinByParam(Long id, String maxOrMin) {
		List<Student> filteredStudent = null;
		if (maxOrMin == null) {
			maxOrMin = "all";
		}
		filteredStudent = switch (maxOrMin) {
		case "max" -> getStudentWithHighestGrade(id);
		case "min" -> getStudentWithLowestGrade(id);
		case "all" -> filterStudentsByCourseId(id);
		default -> throw new IllegalArgumentException("Unexpected value: " + maxOrMin);

		};

		return filteredStudent;

	}

	public Long getDaysUntilCourseStart(LocalDate isncriptionDate, LocalDate startDate) {
		return ChronoUnit.DAYS.between(isncriptionDate, startDate);

	}

	public void validateStudent(Student student, LocalDate registrationDate) {
		LocalDate today = LocalDate.now();

		if (student.getId() == null) {
			student.setRegistrationDate(today);
		} else {
			if (registrationDate != null) {
				student.setRegistrationDate(registrationDate);
			} else {
				student.setRegistrationDate(today);
			}
		}

		if (student.getId() == null) {
			student.getGrades().put(Grade.SPEAKING, 0.0);
			student.getGrades().put(Grade.LISTENING, 0.0);
			student.getGrades().put(Grade.READING, 0.0);
			student.getGrades().put(Grade.UOE, 0.0);
			student.getGrades().put(Grade.WRITING, 0.0);

		}
	}

	public void addProfilePicture(Student student) {
		if (student.getPhotoPath() == null || student.getPhotoPath().isEmpty()) {
			student.setPhotoPath("https://i.pinimg.com/736x/d5/c8/9b/d5c89bbffb0336f8409e0c91b9cb1f09.jpg");
		}
	}

	public double calculateAverageGradeOfTheWholeAcademy(Long id) {
		double summary = 0;
		long totalOfStudents = 1;
		List<Student> students = filterStudentsByCourseId(id);
		totalOfStudents = students.stream().count();

		for (Student s : students) {
			summary += s.getAverageGrade();
		}

		if (summary == 0) {
			return 0;
		} else {
			return (summary / totalOfStudents);
		}
	}

	public List<Student> listInactiveStudents() {
		return sr.findInactiveStudents();
	}

	public void activateStudent(Long id) {
		sr.findById(id).ifPresent(student -> {
			student.setActive(true);
			sr.save(student);
		});
	}

	public void deleteStudent(Long id) {
		if (sr.existsById(id)) {
			sr.deleteById(id);
		}
	}
}
