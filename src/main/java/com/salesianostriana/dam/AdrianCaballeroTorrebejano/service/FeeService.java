package com.salesianostriana.dam.AdrianCaballeroTorrebejano.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesianostriana.dam.AdrianCaballeroTorrebejano.base.service.BaseServiceImpl;
import com.salesianostriana.dam.AdrianCaballeroTorrebejano.model.Fee;
import com.salesianostriana.dam.AdrianCaballeroTorrebejano.model.FeeSetting;
import com.salesianostriana.dam.AdrianCaballeroTorrebejano.model.Student;
import com.salesianostriana.dam.AdrianCaballeroTorrebejano.repository.FeeRepository;

@Service
public class FeeService extends BaseServiceImpl<Fee, Long, FeeRepository> {

	@Autowired
	private FeeRepository feeRepository;

	public double calculateFinalPrice(Fee fee) {
		double base = fee.getBasePrice();

		double siblingDiscountAmount = base * (fee.getSiblingDiscount() / 100.0);
		double earlyDiscountAmount = base * (fee.getEarlyRegistrationDiscount() / 100.0);

		return base - siblingDiscountAmount - earlyDiscountAmount;
	}


	public double calculateTotalEstimated(List<Student> students) {
		return students.stream().filter((s) -> s.getFee() != null).mapToDouble((s) -> s.getFee().getFinalPrice()).sum();
	}
	
	public List<Fee> findAllFees(){
		return feeRepository.findAll();
	}

}
