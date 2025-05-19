package com.salesianostriana.dam.AdrianCaballeroTorrebejano.model;

import java.time.LocalDate;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapKeyEnumerated;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString.Exclude;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "student")
public class Student implements Comparable<Student> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name, surname, email, alternativeText;
	private int age;
	private LocalDate registrationDate;
	
	private boolean active = true;
	@Column(name = "has_a_sibling")
	private boolean hasASibling;

	@ManyToOne
	@JoinColumn(name = "course_id")
	private Course course;

	private String photoPath;

	@ElementCollection
	@CollectionTable(name = "student_grades")
	@MapKeyEnumerated(EnumType.STRING)
	@Column(name = "grade")
	private Map<Grade, Double> grades = new HashMap<>();

	private double average;
	
	@OneToOne
	@Exclude
	private Fee fee;


	// helper methods-----------------------------

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
		if (grades.isEmpty()) {
			return 0.0;
		}

		for (double grade : grades.values()) {
			summary += grade;

		}
		return summary / grades.size();

	}
	
	//-----------------------------------------------
	
	public void addFee(Fee fee) {
	    this.fee = fee;
	    fee.setStudent(this);
	}

	// order
	@Override
	public int compareTo(Student s) {
		return this.name.compareTo(s.name);

	}

}
