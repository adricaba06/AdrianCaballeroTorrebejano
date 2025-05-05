package com.salesianostriana.dam.AdrianCaballeroTorrebejano.service;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Service;


import com.salesianostriana.dam.AdrianCaballeroTorrebejano.base.service.BaseService;
import com.salesianostriana.dam.AdrianCaballeroTorrebejano.repository.CourseRepository;
import com.salesianostriana.dam.AdrianCaballeroTorrebejano.model.Course;

@Service
public class CourseService extends BaseService<Course, Long, CourseRepository> {
	
	public double compareFinalDateToToday(LocalDate startDate, LocalDate endDate) {
		ZoneId zone = ZoneId.systemDefault();
		Long total, passed;
		double percent;
		
		LocalDate today = LocalDate.now(zone);
		total = ChronoUnit.DAYS.between(startDate, endDate) + 1;
		passed = ChronoUnit.DAYS.between(startDate, today) + 1;
		
		if(today.isAfter(endDate)) {
			passed = total;
		}
		
		percent = total > 0 ? calculatePercent(passed, total) : 100 ;
		return Math.round(percent);
		
	}
	//https://stackoverflow.com/questions/62101906/java-how-to-get-the-percentage-between-2-datetimes

}

