package com.salesianostriana.dam.AdrianCaballeroTorrebejano.model;

import java.time.LocalDate;
import java.util.EnumMap;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "student")
public class Student  implements Comparable<Student> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name, surname, email;
	private int age;
	private LocalDate registrationDate;
	private boolean active = true;
	
	@ManyToOne
	@JoinColumn(name = "course_id") 
	private Course course;
	
	private String photoPath ;


	private EnumMap<Grade, Double> grades;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
	
	

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}



	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
	
	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}
	//helper methods

	

	public void addToCourse(Course course) {
		this.course = course;
		course.getStudentList().add(this);
	}
	
	public void removeFromCourse(Course course) {
		course.getStudentList().remove(this);
		this.course = null;		
	}
	
	public double getAverageGrade() {
		double summary = 0;
		if(grades.isEmpty()) {
			return 0.0;
		}
		
		for (double grade : grades.values()) {
			summary += grade;
			
		}
		return summary / grades.size();
		
	}
	
	
	//order 
	
	public int compareTo(Student s) {
		return this.name.compareTo(s.name);
		
	}
	
	

}
