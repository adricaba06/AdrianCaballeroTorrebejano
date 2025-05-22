package com.salesianostriana.dam.AdrianCaballeroTorrebejano.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/main") 
public class MainController {

    @GetMapping("/cover")
    public String main() {
        return "cover";
    }

    @GetMapping("/aboutUs")
    public String about() {
        return "aboutUs"; 
    }

}
