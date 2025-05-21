package com.salesianostriana.dam.AdrianCaballeroTorrebejano.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "course")
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private String description;
	private String teacher;

	private int maxCapacity;
	private boolean isFull;

	@Enumerated(EnumType.STRING)
	private Level level;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate startDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate endDate;

	private boolean active = true;

	private String imageUrl;

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
	private List<Student> studentList;

	// SE QUE PODRÍA HABER PUESTO VARIAS JUNTAS YA AHORRAR ESPACIO, PERO LUISMI DIJO
	// QUE ERA MEJOR ASI

	
	public int numOfStudents() {   					
		int num;
		num = (int) studentList.stream().filter((s) -> s.isActive()).count(); // .count DEVUELVE UN LONG PERO NO ME
																				// INTERESA QUE SEA UN LONG
		return num;

	}

	@Transient
	public boolean isFull() {
		return numOfStudents() >= maxCapacity;
	}
	
	public void removeStudent(Student student) {
	    if (studentList != null) {
	        studentList.remove(student);
	    }
	}

	

}
