package com.salesianostriana.dam.AdrianCaballeroTorrebejano.util;

import java.time.LocalDate;
import java.util.Comparator;

import com.salesianostriana.dam.AdrianCaballeroTorrebejano.model.Student;

public class StudentRegistrationDateComparator implements Comparator<Student>{

	@Override
	public int compare(Student s1, Student s2) {
		LocalDate date1 = s1.getRegistrationDate();
		LocalDate date2 = s2.getRegistrationDate();
		
		return date1.compareTo(date2);
	}

}
