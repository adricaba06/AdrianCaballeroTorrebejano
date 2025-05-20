package com.salesianostriana.dam.AdrianCaballeroTorrebejano.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesianostriana.dam.AdrianCaballeroTorrebejano.base.service.BaseServiceImpl;
import com.salesianostriana.dam.AdrianCaballeroTorrebejano.repository.CourseRepository;
import com.salesianostriana.dam.AdrianCaballeroTorrebejano.model.Course;
import com.salesianostriana.dam.AdrianCaballeroTorrebejano.model.Student;

@Service
public class CourseService extends BaseServiceImpl<Course, Long, CourseRepository> {

	@Autowired
	CourseRepository cr;
	
	public double compareFinalDateToToday(LocalDate startDate, LocalDate endDate) {
		ZoneId zone = ZoneId.systemDefault();
		Long total, passed;
		double percent;

		LocalDate today = LocalDate.now(zone);
		total = ChronoUnit.DAYS.between(startDate, endDate) + 1;
		passed = ChronoUnit.DAYS.between(startDate, today) + 1;

		if (today.isBefore(startDate)) {
			passed = 0L;
		}

		if (today.isAfter(endDate)) {
			passed = total;
		}

		percent = total > 0 ? calculatePercent(passed, total) : 100;
		return Math.round(percent);

	}
	// https://stackoverflow.com/questions/62101906/java-how-to-get-the-percentage-between-2-datetimes
	
	public List<Course> showActiveCourses(){
		return findAll().stream().filter((c) -> c.isActive()).collect(Collectors.toList());
	}
	
	
	public double getPercentOfOcupation(Long courseId) {
	    return cr.findById(courseId)
	             .map(c -> calculatePercent(c.numOfStudents(), c.getMaxCapacity()))
	             .orElse(0.0);
	}

}
