package com.salesianostriana.dam.AdrianCaballeroTorrebejano.model;

import java.time.LocalDate;
import java.util.EnumMap;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "student")
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String name, surname, email;
	private int age;
	private LocalDate registrationDate;
	
	
	@ManyToOne
	@JoinColumn(name = "course_id") 
	private Course course;
	
	private EnumMap<Grade, Double> grades;
	

	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(LocalDate registrationDate) {
		this.registrationDate = registrationDate;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
	
	//helper methods

	public void addToCurso(Course course) {
		this.course = course;
		course.getStudentList().add(this);
	}
	
	public void removeFromCurso(Course course) {
		course.getStudentList().remove(this);
		this.course = null;		
	}
	
	

}
