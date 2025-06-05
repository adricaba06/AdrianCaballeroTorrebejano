package com.salesianostriana.dam.AdrianCaballeroTorrebejano.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesianostriana.dam.AdrianCaballeroTorrebejano.base.service.BaseServiceImpl;
import com.salesianostriana.dam.AdrianCaballeroTorrebejano.model.Course;
import com.salesianostriana.dam.AdrianCaballeroTorrebejano.model.Fee;
import com.salesianostriana.dam.AdrianCaballeroTorrebejano.model.Student;
import com.salesianostriana.dam.AdrianCaballeroTorrebejano.repository.FeeRepository;
import com.salesianostriana.dam.AdrianCaballeroTorrebejano.repository.StudentRepository;

@Service
public class FeeService extends BaseServiceImpl<Fee, Long, FeeRepository> {

	@Autowired
	private FeeRepository feeRepository;
	
	@Autowired
	private StudentRepository studentRepository;

	public double calculateTotalEstimated(List<Student> students) {
		return students.stream().filter((s) -> s.getFee() != null).mapToDouble((s) -> s.getFee().calculateFinalPrice()).sum();
	}
	
	public List<Fee> findAllFees(){
		return feeRepository.findAll();
	}
	
	public List<Fee> findAllFeesExceptDefault(){
		return feeRepository.findAllExceptIdOne();
	}
	
	public void changeFee(Long id) {
		Optional<Fee> originFeeO = feeRepository.findById(id);
		Optional<Fee> defaultFeeO = feeRepository.findById(1L);

		if (originFeeO.isPresent() && defaultFeeO.isPresent()) {
			Fee originFee = originFeeO.get();
			Fee defaultFee = defaultFeeO.get();

			List<Student> studentsToMove = studentRepository.findAllByFeeId(id);

			for (Student student : studentsToMove) {
				student.setFee(defaultFee);
			}

			studentRepository.saveAll(studentsToMove);

			originFee.getStudents().clear();
			feeRepository.save(originFee);
			
		} else {
			throw new RuntimeException("not found");
		}
	}

}
