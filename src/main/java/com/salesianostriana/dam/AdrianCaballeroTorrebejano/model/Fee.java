package com.salesianostriana.dam.AdrianCaballeroTorrebejano.model;

import java.util.ArrayList;
import java.util.List;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "fee")
@ToString(exclude = "student") 
public class Fee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private double basePrice;
	private double siblingDiscount;
	private double earlyRegistrationDiscount;
	private Long daysForDiscount;

	private double finalPrice;

	@OneToMany(mappedBy = "fee", cascade = CascadeType.PERSIST)
	private List<Student> students = new ArrayList<>();
	

	// helper methods
	
	public double calculateFinalPrice() {
		double base = basePrice;

		double siblingDiscountAmount = base * (siblingDiscount / 100.0);
		double earlyDiscountAmount = base * (earlyRegistrationDiscount / 100.0);

		return base - siblingDiscountAmount - earlyDiscountAmount;
	}
	
	  public void addStudent(Student student) {
	        students.add(student);
	        student.setFee(this);  
	    }

}
