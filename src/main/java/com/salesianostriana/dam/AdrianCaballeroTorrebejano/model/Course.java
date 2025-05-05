package com.salesianostriana.dam.AdrianCaballeroTorrebejano.model;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Course {

	@Id
	@GeneratedValue
	private long id;

	private String name;
	private String description;
	private String teacher;

	private int maxCapacity;
	private int numOfStudents;
	
	@Enumerated(EnumType.STRING)
	private Level level;


	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate startDate;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate endDate;

	private boolean active = true;

	private String imageUrl;
	

	


	
	
	
}
