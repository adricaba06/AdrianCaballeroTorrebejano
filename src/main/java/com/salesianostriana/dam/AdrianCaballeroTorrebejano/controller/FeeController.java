package com.salesianostriana.dam.AdrianCaballeroTorrebejano.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.salesianostriana.dam.AdrianCaballeroTorrebejano.service.FeeService;

@Controller
public class FeeController {
	
	@Autowired 
	FeeService feeService;
	
	@GetMapping("/reports")
	public String showReports() {
		return "reports";
	}
	
	
	
}
