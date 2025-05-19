package com.salesianostriana.dam.AdrianCaballeroTorrebejano.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.salesianostriana.dam.AdrianCaballeroTorrebejano.model.Fee;
import com.salesianostriana.dam.AdrianCaballeroTorrebejano.model.Student;
import com.salesianostriana.dam.AdrianCaballeroTorrebejano.service.FeeService;
import com.salesianostriana.dam.AdrianCaballeroTorrebejano.service.StudentService;


@Controller
public class FeeController {

	@Autowired
	FeeService feeService;
	
	@Autowired
	StudentService studentService;

	@GetMapping("/reports")
	public String showReports(Model model, @RequestParam(name = "sort", required = false) String sortBy, @RequestParam boolean complete){
		List<Student> activeStudents = studentService.filterActiveStudents(sortBy, complete);
		
		
		model.addAttribute("activeStudents", activeStudents);
		model.addAttribute("fee", new Fee());
		model.addAttribute("total", feeService.calculateTotalEstimated(activeStudents));
		return "reports";
	}

	@PostMapping("/saveFeeData")
	public String saveFeeData(Fee fee) {
		feeService.save(fee);
		return "reports";

	}

}


