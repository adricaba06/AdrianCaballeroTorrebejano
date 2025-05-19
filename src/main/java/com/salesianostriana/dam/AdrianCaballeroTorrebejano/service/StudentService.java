package com.salesianostriana.dam.AdrianCaballeroTorrebejano.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesianostriana.dam.AdrianCaballeroTorrebejano.base.service.BaseService;
import com.salesianostriana.dam.AdrianCaballeroTorrebejano.base.service.BaseServiceImpl;
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

	public List<Student> filterActiveStudents(String sortBy) {
		List<Student> activeStudents = findAll().stream().filter((s) -> s.isActive()).collect(Collectors.toList());

		if (sortBy == null) {
			return activeStudents;
		}

		switch (sortBy) {
		case "date":
			activeStudents.sort(Comparator.comparing(Student::getRegistrationDate));
			break;

		case "alfabeDes":
			activeStudents.sort(Comparator.comparing(Student::getName).reversed());
			break;

		case "grades":
			activeStudents.sort(Comparator.comparing(Student::getAverageGrade));
			break;

		case "alfabe":
			Collections.sort(activeStudents);

		case "max":

		default:
			Collections.sort(activeStudents);
		}

		return activeStudents;

	}

	public double getAverageGrade(Long id) {
		Optional<Student> studentO = findById(id);
		Student student = null;
		if (studentO.isPresent())
			student = studentO.get();

		return student.getAverageGrade();

	}

	public List<Student> findByNameAndSurname(String name) {
		return sr.findByStudentsNameAndSurname(name);

	}

	public double getPassPercent(Long id) {
		List<Student> students = filterStudentsByCourseId(id);
		double passingStudentsNumber = 0;

		passingStudentsNumber = students.stream().filter((s) -> s.getAverageGrade() >= 5).count();

		return calculatePercent(passingStudentsNumber, students.size());

	}

	public double getFailPercent(Long id) {
		List<Student> students = filterStudentsByCourseId(id);
		double failingStudentNumber = 0;

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
	
	public void  validateStudent(Student student, LocalDate registrationDate) {
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
	}
	
}
