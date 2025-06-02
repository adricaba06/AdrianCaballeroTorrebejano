package com.salesianostriana.dam.AdrianCaballeroTorrebejano.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.salesianostriana.dam.AdrianCaballeroTorrebejano.model.Email;
import com.salesianostriana.dam.AdrianCaballeroTorrebejano.service.EmailService;

@Controller
public class EmailController {

    @Autowired
    private EmailService emailService;
    
    @PostMapping("/enviar")
    public String enviarCorreo(@RequestBody Email email) {
        emailService.sendEmail(email);
        return "/reports";
    }
}
