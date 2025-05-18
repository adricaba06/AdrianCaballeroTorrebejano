package com.salesianostriana.dam.AdrianCaballeroTorrebejano.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.AdrianCaballeroTorrebejano.base.service.BaseServiceImpl;
import com.salesianostriana.dam.AdrianCaballeroTorrebejano.model.Fee;
import com.salesianostriana.dam.AdrianCaballeroTorrebejano.model.Student;
import com.salesianostriana.dam.AdrianCaballeroTorrebejano.repository.FeeRepository;

@Service
public class FeeService extends BaseServiceImpl<Fee, Long, FeeRepository> {
	
	private FeeRepository feeRepository;
	
	public double calculateFinalPrice(Fee fee) {
	    double base = fee.getBasePrice();

	    double siblingDiscountAmount = base * (fee.getSiblingDiscount() / 100.0);
	    double earlyDiscountAmount = base * (fee.getEarlyRegistrationDiscount() / 100.0);

	    return base - siblingDiscountAmount - earlyDiscountAmount;
	}

	public Fee generateConvenientFee(Student student, Long daysUntilCourseStarts) {
	    Fee fee = new Fee();

	    fee.setBasePrice(student.getAge() <= 12 ? 45.0 : 60.0);

	    fee.setSiblingDiscount(student.isHasASibling() ? 15.0 : 0.0);

	    double earlyDiscount = (daysUntilCourseStarts != null && daysUntilCourseStarts >= 15) ? 5.0 : 0.0;
	    fee.setEarlyRegistrationDiscount(earlyDiscount);

	    fee.setFinalPrice(calculateFinalPrice(fee));

	    return fee;
	}

	public double calculateTotalEstimated(List<Student> students) {
		return students.stream()
				.filter((s) -> s.getFee() != null)
				.mapToDouble((s) -> s.getFee().getFinalPrice())
				.sum();
	}

	}
		
	

