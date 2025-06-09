package com.salesianostriana.dam.AdrianCaballeroTorrebejano.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller 
public class MainController {
	
    @GetMapping("/")
    public String main() {
        return "cover";
    }

    @GetMapping("main/aboutUs")
    public String about() {
        return "aboutUs"; 
    }

}
