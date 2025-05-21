package com.salesianostriana.dam.AdrianCaballeroTorrebejano.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.salesianostriana.dam.AdrianCaballeroTorrebejano.base.service.BaseServiceImpl;
import com.salesianostriana.dam.AdrianCaballeroTorrebejano.model.Fee;
import com.salesianostriana.dam.AdrianCaballeroTorrebejano.model.FeeSetting;
import com.salesianostriana.dam.AdrianCaballeroTorrebejano.model.Student;
import com.salesianostriana.dam.AdrianCaballeroTorrebejano.repository.FeeRepository;

@Service
public class FeeService extends BaseServiceImpl<Fee, Long, FeeRepository> {
	
	private FeeRepository feeRepository;
	
	private final FeeSettingService feeSettingService;

    public FeeService(FeeSettingService feeSettingService) {
        this.feeSettingService = feeSettingService;
    }
	
	public double calculateFinalPrice(Fee fee) {
	    double base = fee.getBasePrice();

	    double siblingDiscountAmount = base * (fee.getSiblingDiscount() / 100.0);
	    double earlyDiscountAmount = base * (fee.getEarlyRegistrationDiscount() / 100.0);

	    return base - siblingDiscountAmount - earlyDiscountAmount;
	}

	public Fee generateConvenientFee(Student student, Long daysUntilCourseStarts) {
	    FeeSetting fs = feeSettingService.getCurrentSetting();
	    Fee fee = new Fee();
	    
	    if(fs.getEarlyRegistrationDiscount() + fs.getSiblingDiscount() >= 100) {
	    	fs.setEarlyRegistrationDiscount(0);
	    	fs.setSiblingDiscount(0);
	    }

	    fee.setBasePrice(student.getAge() <= fs.getAge() ? fs.getBasePriceUnderAge() : fs.getBasePriceOverAge());

	    fee.setSiblingDiscount(student.isHasASibling() ? fs.getSiblingDiscount() : 0.0);

	    double earlyDiscount = (daysUntilCourseStarts != null && daysUntilCourseStarts >= fs.getDaysBeforeCourseStarts()) ? fs.getEarlyRegistrationDiscount() : 0.0;
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
		
	

